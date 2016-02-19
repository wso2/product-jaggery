/*
  Copyright 2005-2015 WSO2, Inc. (http://wso2.com)

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
package org.wso2.jaggery.integration.tests.session;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.*;

public class LazySessionCreationTestCase {
    private static final Log log = LogFactory.getLog(LazySessionCreationTestCase.class);

    @Test(groups = {"jaggery"},
            description = "test get method of session when lazy session creation if there is no existing session it " +
                    "will not create a new one")
    public void testGetlazySessionCreation() {
        ClientConnectionUtil.waitForPort(9763);
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/get-lazysessioncreation.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));
            assertNull(in.readLine());
            assertNull(jaggeryServerConnection.getHeaderField("Set-Cookie"));

        } catch (Exception e) {
            log.debug(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    @Test(groups = {"jaggery"},
            description = "Test put and get method of session when lazy session creation.Initially create a session " +
                    "and try to get session back")
    public void testGetPutLazySessionCreation() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/put-get-lazysessioncreation.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            assertEquals(finalOutput, "test session value");
            assertNotNull(jaggeryServerConnection.getHeaderField("Set-Cookie"));

        } catch (IOException e) {
            log.debug(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    @Test(groups = {"jaggery"},
            description = "Test get method of session when lazy session creating after invalidating all sessions, " +
                    "When after invalidating all sessions it should not create a new session")
    public void testGetlazySessionCreationAfterInvalidate() {
        ClientConnectionUtil.waitForPort(9763);
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/get-lazysessioncreation_afterinvalidate.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));
            assertNull(in.readLine());
            assertNull(jaggeryServerConnection.getHeaderField("Set-Cookie"));

        } catch (IOException e) {
            log.debug(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    @Test(groups = {"jaggery"},
            description = "Test get method of session when lazy session creating after invalidating all sessions, " +
                    "When after invalidating all sessions it should not create a new session")
    public void testPutGetlazySessionCreationAfterInvalidate() {
        ClientConnectionUtil.waitForPort(9763);
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/put-get-lazysessioncreation_afterinvalidate.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));
            assertNull(in.readLine());
            assertNotNull(jaggeryServerConnection.getHeaderField("Set-Cookie"));

        } catch (IOException e) {
            log.debug(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

}
