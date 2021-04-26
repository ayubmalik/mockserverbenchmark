package mockserverbenchmark;

class XmlFixture {

    private static final String REQUEST = "<SaveContactRestRequest xmlns=\"http://schemas.datacontract.org/2004/07/SmashFly.WebServices.ContactManagerService.v2\">\n" +
            "    <Contact>\n" +
            "        <Address1>1 Main Street</Address1>\n" +
            "        <Address2>Unit 2</Address2>\n" +
            "        <City>Boston</City>\n" +
            "        <Company>3COM</Company>\n" +
            "        <ContactListId>999999999</ContactListId>\n" +
            "        <Country>US</Country>\n" +
            "        <Education>\n" +
            "            <ContactEducation>\n" +
            "                <Degree>Bachelors</Degree>\n" +
            "                <GradYear>1988</GradYear>\n" +
            "                <HasGraduated>true</HasGraduated>\n" +
            "                <Major>Electrical Engineering</Major>\n" +
            "                <School>MIT</School>\n" +
            "            </ContactEducation>\n" +
            "            <ContactEducation>\n" +
            "                <Degree>Masters</Degree>\n" +
            "                <GradYear>1992</GradYear>\n" +
            "                <HasGraduated>true</HasGraduated>\n" +
            "                <Major>Business</Major>\n" +
            "                <School>Harvard</School>\n" +
            "            </ContactEducation>\n" +
            "        </Education>\n" +
            "        <EducationCurrent>BachelorofScience</EducationCurrent>\n" +
            "        <Email>gwashington7@invalidemail.com</Email>\n" +
            "        <EventListId>999999999</EventListId>\n" +
            "        <Experience>\n" +
            "            <ContactExperience>\n" +
            "                <Company>3COM</Company>\n" +
            "                <EndMonth>2</EndMonth>\n" +
            "                <EndYear>1998</EndYear>\n" +
            "                <JobTitle>Senior Engineer</JobTitle>\n" +
            "                <StartMonth>1</StartMonth>\n" +
            "                <StartYear>1992</StartYear>\n" +
            "                <State>MA</State>\n" +
            "                <Supervisor>Bob Smith</Supervisor>\n" +
            "                <SupervisorTitle>CTO</SupervisorTitle>\n" +
            "            </ContactExperience>\n" +
            "            <ContactExperience>\n" +
            "                <Company>EMC</Company>\n" +
            "                <EndMonth>7</EndMonth>\n" +
            "                <EndYear>2005</EndYear>\n" +
            "                <JobTitle>Business Analyst</JobTitle>\n" +
            "                <StartMonth>1</StartMonth>\n" +
            "                <StartYear>2003</StartYear>\n" +
            "            </ContactExperience>\n" +
            "        </Experience>\n" +
            "        <ExperienceCurrent>Experienced</ExperienceCurrent>\n" +
            "        <ExternalResumeId>9999999</ExternalResumeId>\n" +
            "        <FacebookProfile>https://www.facebook.com/smashflytechnologies</FacebookProfile>\n" +
            "        <FileToFolderPath>Specialized Sourcing/Veterans</FileToFolderPath>\n" +
            "        <FirstName>George</FirstName>\n" +
            "        <HomeEmail>gwashington@aol.com</HomeEmail>\n" +
            "        <IsEmployee>true</IsEmployee>\n" +
            "        <JobCode>19</JobCode>\n" +
            "        <JobId>LOS99710</JobId>\n" +
            "        <JobListStatus>Interviewing</JobListStatus>\n" +
            "        <JobListStatusNote>Great candidate</JobListStatusNote>\n" +
            "        <JobTitle>Senior Engineer</JobTitle>\n" +
            "        <LastName>Washington</LastName>\n" +
            "        <MobileNumber>9787931633</MobileNumber>\n" +
            "        <Notes>\n" +
            "            <ContactNote>\n" +
            "                <Note>George was a good president</Note>\n" +
            "                <Reminder>2013-08-31T11:20:00</Reminder>\n" +
            "            </ContactNote>\n" +
            "        </Notes>\n" +
            "        <Phone>978-342-3442</Phone>\n" +
            "        <PostalCode>01775</PostalCode>\n" +
            "        <ProfileURL>https://www.linkedin.com/company/846055</ProfileURL>\n" +
            "        <Resume>\n" +
            "            <![CDATA[GEORGE WASHINGTON<br /> 3200 Mount Vernon Hwy, Mt Vernon, VA<br />SUMMARY:<br />&bull;Former General<br />\n" +
            "           &bull; First president of United States of America<br />\n" +
            "           &bull; Distiller<br />Remainder of HTML Resume]]>        \n" +
            "        </Resume>\n" +
            "        <ResumeBin>\n" +
            "            <Content xmlns=\"http://schemas.datacontract.org/2004/07/SmashFly.WebServices.ContactManagerService.v2.DataContracts\">\n" +
            "            R0VPUkdFIFdBU0hJTkdUT04NCjMyMDAgTW91bnQgVmVybm9uIEh3eSwgTXQgVmVybm9uLCBWQQ0KU1VNTUFSWToNCuKAokZvcm1lciBHZW5lcmFsDQrigKIgRmlyc3QgcHJlc2lkZW50IG9mIFVuaXRlZCBTdGF0ZXMgb2YgQW1lcmljYQ0K4oCiIERpc3RpbGxlcg0KUmVtYWluZGVyIG9mIEhUTUwgUmVzdW1l\n" +
            "            </Content>\n" +
            "            <Name xmlns=\"http://schemas.datacontract.org/2004/07/SmashFly.WebServices.ContactManagerService.v2.DataContracts\">Resume.txt</Name>\n" +
            "        </ResumeBin>\n" +
            "        <SecondarySource>Indeed</SecondarySource>\n" +
            "        <State>MA</State>\n" +
            "        <TDSearchFolder>Text goes here</TDSearchFolder>\n" +
            "        <Tags>\n" +
            "            <ContactTag>\n" +
            "                <Access>Public</Access>\n" +
            "                <Tag>Engineering</Tag>\n" +
            "            </ContactTag>\n" +
            "            <ContactTag>\n" +
            "                <Access>Public</Access>\n" +
            "                <Tag>Java</Tag>\n" +
            "            </ContactTag>\n" +
            "        </Tags>\n" +
            "        <TwitterProfile>https://twitter.com/smashfly</TwitterProfile>\n" +
            "        <UDF>\n" +
            "            <ContactUDFData>\n" +
            "                <FieldName>ShortTextField4</FieldName>\n" +
            "                <Values>\n" +
            "                    <string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">CivilEngineering</string>\n" +
            "                    <string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">ElectricalEngineering</string>\n" +
            "                </Values>\n" +
            "            </ContactUDFData>\n" +
            "            <ContactUDFData>\n" +
            "                <FieldName>ShortTextField7</FieldName>\n" +
            "                <Values>\n" +
            "                    <string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">Construction</string>\n" +
            "                    <string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">Facilities_Mgmt</string>\n" +
            "                </Values>\n" +
            "            </ContactUDFData>\n" +
            "        </UDF>\n" +
            "        <UserScore>2.56743233E+15</UserScore>\n" +
            "        <WebURL>http://www.smashfly.com</WebURL>\n" +
            "        <WorkPhone>781-222-4422</WorkPhone>\n" +
            "        <_CanReceiveEmailImpl>true</_CanReceiveEmailImpl>\n" +
            "        <_CanReceiveSMSImpl>true</_CanReceiveSMSImpl>\n" +
            "        <_IsApplicantImpl>true</_IsApplicantImpl>\n" +
            "        <CanReceiveEmail>true</CanReceiveEmail>\n" +
            "        <CanReceiveSMS>true</CanReceiveSMS>\n" +
            "        <IsApplicant>true</IsApplicant>\n" +
            "    </Contact>\n" +
            "    <ContactDBId>99999999</ContactDBId>\n" +
            "    <Password>YourPassword</Password>\n" +
            "    <UserName>UUID_HERE</UserName>\n" +
            "</SaveContactRestRequest>";

    private static final String RESPONSE = "<SaveContactResponse xmlns=\"http://schemas.datacontract.org/2004/07/SmashFly.WebServices.ContactManagerService.v2\">\n" +
            "    <ContactId>UUID_HERE</ContactId>\n" +
            "    <Errors i:nil=\"true\" xmlns:a=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\"/>\n" +
            "    <HasErrors>false</HasErrors>\n" +
            "</SaveContactResponse>";

    static String request(String oldValue, String newValue) {
        return REQUEST.replace(oldValue, newValue);
    }

    static String response(String oldValue, String newValue) {
        return RESPONSE.replace(oldValue, newValue);
    }
}
