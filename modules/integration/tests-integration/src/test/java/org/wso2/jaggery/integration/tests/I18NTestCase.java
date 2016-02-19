/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.jaggery.integration.tests;

import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class I18NTestCase {

    @Test(groups = {"jaggery"}, description = "test i18n translation using es_local json")
    public void testSpanishTranslation() throws Exception {
        String finalOutput = loadOutput("in-json", "es");
        assertEquals(finalOutput, "hola");
    }

    @Test(groups = {"jaggery"}, description = "test i18n translation using default json when es_local fails")
    public void testDefaultTranslationAsFallback() throws Exception {
        String finalOutput = loadOutput("in-json-default-only", "es");
        assertEquals(finalOutput, "wso2");
    }

    @Test(groups = {"jaggery"}, description = "test i18n translation using default json")
    public void testDefaultTranslation() throws Exception {
        String finalOutput = loadOutput("in-json", "en");
        assertEquals(finalOutput, "hello");
    }

    @Test(groups = {"jaggery"}, description = "test i18n translation using fallback string")
    public void testFallbackTranslation() throws Exception {
        String finalOutput = loadOutput("fallback", "en");
        assertEquals(finalOutput, "fallback");
    }

    @Test(groups = {"jaggery"}, description = "test i18n translation using fallback string")
    public void testNullTranslation() throws Exception {
        String finalOutput = loadOutput("not-in-json", "en");
        assertEquals(finalOutput, "null");
    }


    private String loadOutput(String testType, String lang) throws IOException {
        ClientConnectionUtil.waitForPort(9763);

        URL testURL = new URL("http://localhost:9763/testapp/i18n.jag?test=" + testType);
        URLConnection connection = testURL.openConnection();
        connection.setRequestProperty("Accept-Language", lang);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String finalOutput = in.readLine();
        in.close();
        assertNotNull(finalOutput, "Output form i18n.jag is not available");
        return finalOutput;
    }


}
