package org.wso2.jaggery.integration.tests.deployment.core;

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
public class CookieObjectTestCase {
    @Test(groups = {"jaggery"},
            description = "Test Cookie object")
    public void testCookie() {
        ClientConnectionUtil.waitForPort(9763);

        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/cookie.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assertNotNull(finalOutput, "general");
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test(groups = {"jaggery"},
            description = "Test cookie object no resource")
    public void testCookieNoResource() {
        ClientConnectionUtil.waitForPort(9763);

        String finalOutput = null;
        BufferedReader in = null;
        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/cookie.jag?action=noresource");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput = inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assertEquals(finalOutput, "false");
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
