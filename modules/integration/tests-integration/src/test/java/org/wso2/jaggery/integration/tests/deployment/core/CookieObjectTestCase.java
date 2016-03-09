/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.jaggery.integration.tests.deployment.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaggeryjs.integration.common.utils.JaggeryIntegrationTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for Cookie Object
 */
public class CookieObjectTestCase extends JaggeryIntegrationTest {

    private static final Log log = LogFactory.getLog(CookieObjectTestCase.class);

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        try {
            init();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test(groups = {"jaggery"}, description = "Test Cookie object")
    public void testCookie() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/cookie.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertNotNull(finalOutput);
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    @Test(groups = {"jaggery"}, description = "Test cookie object no resource")
    public void testCookieNoResource() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = "";
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/cookie.jag?action=noresource");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertEquals(finalOutput, "false");
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }
}
