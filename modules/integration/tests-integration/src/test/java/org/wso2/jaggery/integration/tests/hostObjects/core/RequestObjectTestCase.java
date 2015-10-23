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

import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for Request Object
 */
public class RequestObjectTestCase {

	@Test(groups = { "jaggery" }, description = "Test request object")
	public void testRequest() {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL("http://localhost:9763/testapp/request.jag?param=test");
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
			assertNotNull(finalOutput, "Result cannot be null");
		}

	}

	@Test(groups = { "jaggery" }, description = "Test request object params")
	public void testReadRequestParams() {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/request.jag?param=test");
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
			assertEquals(finalOutput, "Param : test");
		}

	}
	
	@Test(groups = { "jaggery" }, description = "Test request object for Local")
	public void testReadLocalAllRequestParams() {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/request.jag?param=getAllLocales");
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
			assertEquals(finalOutput, "getAllLocales true");
		}

	}
		
		@Test(groups = { "jaggery" }, description = "Test request object for Local")
		public void testReadLocalRequestParams() {
			ClientConnectionUtil.waitForPort(9763);

			String finalOutput = null;

			try {
				URL jaggeryURL = new URL(
						"http://localhost:9763/testapp/request.jag?param=getLocale");
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
				assertEquals(finalOutput, "en");
			}

		}

	@Test(groups = { "jaggery" }, description = "Test request object ")
	public void testReadRequest() {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/request.jag?test=hi");
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
			assertEquals(
					finalOutput,
					"Method : GET, Protocol : HTTP/1.1, QueryString : test=hi,"
					+" URI : /testapp/request.jag, URL : http://localhost:9763/testapp/request.jag,"
					+" LocalPort : 9763, ContentLength : -1, ContextPath : /testapp");
		}

	}
	
	@Test(groups = { "jaggery" }, description = "Test request object for getMappedPath")
	public void testgetMappedPathRequestParams() {
		ClientConnectionUtil.waitForPort(9763);

		String finalOutput = null;

		try {
			URL jaggeryURL = new URL(
					"http://localhost:9763/testapp/request.jag?param=getMappedPath");
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
			assertEquals(finalOutput, "getMappedPath : /request.jag");
		}

	}

	@Test(groups = { "jaggery" }, description = "Test request object for parseMultiPart")
	public void testparseMultiPart(){
		ClientConnectionUtil.waitForPort(9763);
		String finalOutput = null;
		String expectedOutput = null;

		try{
			URL jaggeryURL = new URL("http://localhost:9763/testapp/multipart.json");
			URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
			String inputLineExpected;
			while((inputLineExpected = in.readLine()) != null){
				expectedOutput = inputLineExpected;
			}

			jaggeryURL = new URL("http://localhost:9763/testapp/request.jag?param=getAllParameters");
			jaggeryServerConnection = jaggeryURL.openConnection();
			in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
			String inputLineActual;
			while((inputLineActual = in.readLine()) != null){
				finalOutput = inputLineActual;
			}
			in.close();

		}catch(IOException e){
			e.printStackTrace();
		}finally {
			assertEquals(finalOutput, expectedOutput);
		}
	}
}
