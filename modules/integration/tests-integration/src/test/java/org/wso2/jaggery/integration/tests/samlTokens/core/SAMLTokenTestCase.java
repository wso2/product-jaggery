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

package org.wso2.jaggery.integration.tests.samlTokens.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

/**
 * Test cases for OAuth SAML Object
 */
public class SAMLTokenTestCase {

	public boolean testIsOnline(){
		boolean out=false;
		return out;
	}
    
    @Test(groups = {"jaggery"},
            description = "Test OAuth access token")
    public void testOauthAccessToken() {
        ClientConnectionUtil.waitForPort(9763);
        
        String finalOutput = null;
        
        try {
        	URL jaggeryURL = new URL("http://localhost:9763/testapp/oauth-saml.jag?param=1");
        	URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
        	BufferedReader in = new BufferedReader(new InputStreamReader(
        			jaggeryServerConnection.getInputStream()));
        
          String inputLine;
    			while ((inputLine = in.readLine()) != null) {
    				finalOutput = inputLine;
    			}
			    
          in.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			assertEquals(finalOutput, "e5b31a9bbbb9f967e7e7c17fc9d26ec");
    		}
        
    }

    @Test(groups = {"jaggery"},
            description = "Test OAuth refresh token")
    public void testOauthRefreshToken() {
        ClientConnectionUtil.waitForPort(9763);
        
        String finalOutput = null;
        
        try {
          URL jaggeryURL = new URL("http://localhost:9763/testapp/oauth-saml.jag?param=2");
          URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(
              jaggeryServerConnection.getInputStream()));
        
          String inputLine;
          while ((inputLine = in.readLine()) != null) {
            finalOutput = inputLine;
          }
          
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          assertEquals(finalOutput, "ef50c2981ac46f248150253cc7d8b78b");
        }
        
    }

    @Test(groups = {"jaggery"},
            description = "Test OAuth token type")
    public void testOauthTokenType() {
        ClientConnectionUtil.waitForPort(9763);
        
        String finalOutput = null;
        
        try {
          URL jaggeryURL = new URL("http://localhost:9763/testapp/oauth-saml.jag?param=3");
          URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(
              jaggeryServerConnection.getInputStream()));
        
          String inputLine;
          while ((inputLine = in.readLine()) != null) {
            finalOutput = inputLine;
          }
          
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          assertEquals(finalOutput, "Bearer");
        }
        
    }

    @Test(groups = {"jaggery"},
            description = "Test OAuth expires in time")
    public void testOauthExpiresIn() {
        ClientConnectionUtil.waitForPort(9763);
        
        String finalOutput = null;
        
        try {
          URL jaggeryURL = new URL("http://localhost:9763/testapp/oauth-saml.jag?param=4");
          URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(
              jaggeryServerConnection.getInputStream()));
        
          String inputLine;
          while ((inputLine = in.readLine()) != null) {
            finalOutput = inputLine;
          }
          
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          assertEquals(finalOutput, "3600");
        }
        
    }
    

}
