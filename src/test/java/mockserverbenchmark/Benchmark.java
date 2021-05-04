package mockserverbenchmark;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.mockserver.mock.Expectation;
import org.mockserver.model.ClearType;
import org.mockserver.model.ExpectationId;
import org.mockserver.model.Format;
import org.mockserver.verify.VerificationTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Benchmark {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String host = "localhost";
    private final int port = 1080;
    private final HttpHost httpHost = new HttpHost(host, port);
    private final MockServerClient mockClient = new MockServerClient(host, port);
    private final HttpClient httpClient = HttpClients.createDefault();

    @Test
    void benchmark() {
        var numEnquiries = 25;
        var batchSize = 50;
        var start = System.currentTimeMillis();
        for (int i = 0; i < numEnquiries; i++) {
            var now = System.currentTimeMillis();
            var batchIDs = setupExpectationBatch(batchSize);
            log.info("{} expectations took {}ms", batchSize, System.currentTimeMillis() - now);

            now = System.currentTimeMillis();
            batchIDs.forEach(id -> {
                var result = makeXmlApiCall(id);
                assertThat(result, containsString(id));
            });

            log.info("{} API calls took {}ms", batchSize, System.currentTimeMillis() - now);
            clearLogs(batchIDs);
        }
        log.info("total {} calls took {}ms", numEnquiries * batchSize, System.currentTimeMillis() - start);
    }

    @Test
    void singleTest() {
        var start = System.currentTimeMillis();
        var uuid = UUID.randomUUID().toString();
        var path = "/quote/" + uuid;
        var requestBody = XmlFixture.request("UUID_HERE", uuid);
        var responseBody = XmlFixture.response("UUID_HERE", uuid);

        createExpectationWithID(path, uuid, requestBody, responseBody);


        var apiResponse = makeXmlApiCall(uuid);
        assertTrue(apiResponse.contains(uuid));

        log.info("{} took {}ms", path, System.currentTimeMillis() - start);

//        mockClient.verify(request().withPath(path), VerificationTimes.exactly(1));
        mockClient.verify(new ExpectationId().withId(uuid), VerificationTimes.exactly(1));
        clearLogs(List.of(uuid));

    }

    private List<String> setupExpectationBatch(int batchCount) {
        var batchIDs = new ArrayList<String>();
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
            var entity = new StringEntity(requestBody);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/xml");
            var response = httpClient.execute(httpHost, httpPost);
            return EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void clearLogs(List<String> batchIDs) {
        // clear by ID does NOT work - throws error?
        // batchIDs.forEach(id -> mockClient.clear(new ExpectationId().withId(id), ClearType.LOG));
        var start = System.currentTimeMillis();
        mockClient.clear(request().withPath("/quote/.*"), ClearType.ALL);
        log.info("clearing logs took {}ms", System.currentTimeMillis() - start);
    }
}
