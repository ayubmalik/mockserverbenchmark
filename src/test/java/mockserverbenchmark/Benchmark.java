package mockserverbenchmark;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.mockserver.mock.Expectation;
import org.mockserver.model.ClearType;
import org.mockserver.verify.VerificationTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Benchmark {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String host = "localhost";
    private final int port = 1080;
    private final HttpHost httpHost = new HttpHost(host, port);
    private final MockServerClient mockClient = new MockServerClient(host, port);
    private final HttpClient httpClient = HttpClients.createMinimal();

    @Test
    void benchmark() {
        var numEnquiries = 20;
        var batchSize = 100;
        var startTotal = System.currentTimeMillis();
        clearLogs();
        for (int i = 0; i < numEnquiries; i++) {
            var start = System.currentTimeMillis();
            var batchIDs = setupExpectationBatch(batchSize);
            log.info("{} expectations took {}ms", batchSize, System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            batchIDs.forEach(id -> {
                var result = makeXmlApiCall(id);
                assertThat(result, containsString(id));
            });
            log.info("{} API calls took {}ms", batchSize, System.currentTimeMillis() - start);
        }
        long total = System.currentTimeMillis() - startTotal;
        log.info("total {} calls took {}ms. AVG = {}ms", numEnquiries * batchSize, total, total / (numEnquiries * batchSize));
    }

    @Test
    void singleTest() {
        var uuid = UUID.randomUUID().toString();
        var path = "/quote/" + uuid;

        var start = System.currentTimeMillis();
        var requestBody = XmlFixture.request("UUID_HERE", uuid);
        var responseBody = XmlFixture.response("UUID_HERE", uuid);
        logTask("fixtures", start);

        start = System.currentTimeMillis();
        createExpectationWithID(path, uuid, requestBody, responseBody);
        logTask("createExpectationWithID", start);

        start = System.currentTimeMillis();
        var apiResponse = makeXmlApiCall(uuid);
        assertTrue(apiResponse.contains(uuid));
        logTask("makeXmlApiCall " + path, start);

        start = System.currentTimeMillis();
        mockClient.verify(request().withPath(path), VerificationTimes.exactly(1));
        logTask("verify " + uuid, start);
        clearLogs();
    }

    private void logTask(String name, long start) {
        log.info("{} took {}ms", name, System.currentTimeMillis() - start);
    }

    private List<String> setupExpectationBatch(int batchCount) {
        var batchIDs = new ArrayList<String>(batchCount);
        for (int i = 0; i < batchCount; i++) {
            var uuid = UUID.randomUUID().toString();
            // replace some content with UUID so XML is different each time
            var requestBody = XmlFixture.request("UUID_HERE", uuid);
            var responseBody = XmlFixture.response("UUID_HERE", uuid);

            var path = "/quote/" + uuid;
            createExpectationWithID(path, uuid, requestBody, responseBody);
            batchIDs.add(uuid);
        }
        return batchIDs;
    }

    private void createExpectationWithID(String path, String id, String requestBody, String responseBody) {
        var expectation = new Expectation(
                request().withPath(path).withBody(requestBody),
                Times.exactly(1),
                TimeToLive.exactly(TimeUnit.SECONDS, 40L),
                100
        ).withId(id);
        mockClient.upsert(expectation.thenRespond(response().withBody(responseBody)));
    }

    private String makeXmlApiCall(String uuid) {
        try {
            var httpPost = new HttpPost("/quote/" + uuid);
            var requestBody = XmlFixture.request("UUID_HERE", uuid);
            httpPost.setEntity(new StringEntity(requestBody));
            httpPost.setHeader("Content-type", "application/xml");
            var response = httpClient.execute(httpHost, httpPost);
            return EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void clearLogs() {
        // clear by ID does NOT work - throws error?
        // batchIDs.forEach(id -> mockClient.clear(new ExpectationId().withId(id), ClearType.LOG));
        var start = System.currentTimeMillis();
        mockClient.clear(request().withPath("/quote/.*"), ClearType.ALL);
        log.info("clearing logs took {}ms", System.currentTimeMillis() - start);
    }
}
