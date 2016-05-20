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

package org.wso2.jaggery.integration.tests.hostObjects.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

/**
 * Test cases for File Host Object
 */
public class FileHostObjectTestCase {

    private static final Log log = LogFactory.getLog(FileHostObjectTestCase.class);

    @Test(groups = { "jaggery" }, description = "Test for file host object")
    public void testFileExist() throws IOException {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/file.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            in.close();
        } catch (IOException e) {
            log.error(e);
        } finally {
            in.close();
            assertNotNull(finalOutput, "Result cannot be null");
        }
    }

    @Test(groups = { "jaggery" }, description = "Test for file host object read")
    public void testFile() throws IOException {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/file.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            in.close();
        } catch (IOException e) {
            log.error(e);
        } finally {
            in.close();
            assertEquals(finalOutput, "Successfully read testfile.txt");
        }
    }

    @Test(groups = { "jaggery" }, description = "Test for file host object write")
    public void testFileWrite() throws IOException {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/file.jag?action=write");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            in.close();
        } catch (IOException e) {
            log.error(e);
        } finally {
            in.close();
            assertEquals(finalOutput, "write was success");
        }
    }

    @Test(groups = { "jaggery" }, description = "Test for file host object operations")
    public void testFileOpertions() throws IOException {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/file.jag?action=test");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            in.close();
        } catch (IOException e) {
            log.error(e);
        } finally {
            in.close();
            assertEquals(finalOutput, "length : 30, exists : true, stream : Successfully read testfile.txt");
        }
    }

    @Test(groups = { "jaggery" }, description = "Test for file host object read char")
    public void testFileReadChars() throws IOException {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/file.jag?action=read");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            in.close();
        } catch (IOException e) {
            log.error(e);
        } finally {
            in.close();
            assertEquals(finalOutput, "Success");
        }
    }

    @Test(groups = { "jaggery" }, description = "Test for get file name operation")
    public void testGetFileName()
            throws IOException {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/file.jag?action=checkName");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
            in.close();
        } catch (IOException e) {
            log.error(e);
        } finally {
            in.close();
            assertEquals(finalOutput, "name : testfile.txt");
        }
    }
}
