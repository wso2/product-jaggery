/*
 *  Copyright (c) 2005-2012, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.wso2.carbon.integration.framework.TestServerManager;
import org.wso2.carbon.utils.FileManipulator;

import java.io.File;
import java.io.IOException;

/**
 * Prepares the Jaggery for test runs, starts the server, and stops the server
 * after test runs
 */
public class JaggeryTestServerManager extends TestServerManager {

	private static final Log log = LogFactory
			.getLog(JaggeryTestServerManager.class);
	private static final String JAGGERY_ADMIN_CONTEXT = "admin";
	private static final String JAGGERY_SERVER_SCRIPT_NAME = "server";
	private static final String JAGGERY_TEST_APP = "testapp2";

	@Override
	@BeforeSuite(timeOut = 300000)
	public String startServer() throws IOException {

		String carbonHome = super.startServerInCarbonFolder(
				JAGGERY_ADMIN_CONTEXT, JAGGERY_SERVER_SCRIPT_NAME);
		String carbonFolder = "";
		if (carbonHome != null) {
			carbonFolder = carbonHome + File.separator + "carbon";
		}
		System.setProperty("carbon.home", carbonFolder);

		return carbonFolder;
	}

	@Override
	@AfterSuite(timeOut = 60000)
	public void stopServer() throws Exception {
		super.stopServer(JAGGERY_ADMIN_CONTEXT);
	}

	protected void copyArtifacts(String carbonHome) throws IOException {

		String secVerifierDir = System.getProperty("sec.verifier.dir");
		File srcFile = new File(secVerifierDir + File.separator
				+ "SecVerifier.aar");

		String deploymentPath = carbonHome + File.separator + "axis2services";
		File depFile = new File(deploymentPath);
		if (!depFile.exists() && !depFile.mkdir()) {
			System.err.println("Error while creating the deployment folder : "
					+ deploymentPath);
		}
		File dstFile = new File(depFile.getAbsolutePath() + File.separator
				+ "SecVerifier.aar");
		try {
			log.info("Copying " + srcFile.getAbsolutePath() + " => "
					+ dstFile.getAbsolutePath());
			FileManipulator.copyFile(srcFile, dstFile);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Error while creating the deployment folder : "
					+ deploymentPath);
		}

		// Copying jaggery configuration file
		String fileName = "jaggery.conf";
		String sourcePath = computeSourcePath(fileName);
		String destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// test html file
		fileName = "testhtml.html";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "multipleheaders.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "jsonTest.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);
		
		// email host object
		fileName = "email.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// database host object
		fileName = "database.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// org.jaggeryjs.hostobjects.feed1 host object
		fileName = "feed.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// file host object
		fileName = "file.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// sample file to read
		fileName = "testfile.txt";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// log host object
		fileName = "log.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// wsrequest host object
		fileName = "wsrequest.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// request object
		fileName = "request.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// response object
		fileName = "response.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// session object
		fileName = "session.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// application object
		fileName = "application.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// xmlhttprequest object
		fileName = "xmlhttprequest.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// jaggery syntax checker
		fileName = "syntax.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// require object
		fileName = "require.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		// Http client test files
		fileName = "put.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "post.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "get.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "delet.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);
		
		//uri object 
		fileName = "uri.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		//inculde object
		fileName = "inculde.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);
		
		//entry object
		fileName = "entry.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);
		
		//wsstub object
		fileName = "wsstub.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);
		
        //metadata object
        fileName = "metadata.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);

        //resources object
        fileName = "resources.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);

        //collection object
        fileName = "collection.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        //oauth object with twitter api
        fileName = "oauth.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        fileName = "oauth-linkedin.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        //Web Socket object test resources files 
        fileName = "server.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
		fileName = "i18n.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "locale_es.json";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		fileName = "locale_default.json";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

        fileName = "client.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        fileName = "clientTester.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        //frontend js jquery file 
        /*
        fileName = "jquery-1.8.3.min.js";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        */
        
        //front html
        fileName = "client.html";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        //process object test file
        fileName = "process.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);

		//getSession lazy session creation object testcase
		fileName = "get-lazysessioncreation.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		//get- put Session lazy session creation object testcase
		fileName = "put-get-lazysessioncreation.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		//getSession lazy session creation after invalidate session object testcase
		fileName = "get-lazysessioncreation_afterinvalidate.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);

		//put and getSession lazy session creation after invalidate session object testcase
		fileName = "put-get-lazysessioncreation_afterinvalidate.jag";
		sourcePath = computeSourcePath(fileName);
		destinationPath = computeDestPath(carbonHome, fileName);
		copySampleFile(sourcePath, destinationPath);
        
        //native json object test file
        fileName = "nativejson.jag";
        sourcePath = computeSourcePath(fileName);
        destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
        
        //Build testapp2 for testing 
        
		// Copying jaggery configuration file
        fileName = "init.js";
		sourcePath = computeSourcePath(JAGGERY_TEST_APP,fileName);
		destinationPath = computeDestPath(carbonHome,JAGGERY_TEST_APP, fileName);
		copySampleFile(sourcePath, destinationPath);
		
		fileName = "jaggery.conf";
		sourcePath = computeSourcePath(JAGGERY_TEST_APP,fileName);
		destinationPath = computeDestPath(carbonHome,JAGGERY_TEST_APP, fileName);
		copySampleFile(sourcePath, destinationPath);
		
		
	}

	private void copySampleFile(String sourcePath, String destPath) {
		File sourceFile = new File(sourcePath);
		File destFile = new File(destPath);
		try {
			FileManipulator.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			log.error("Error while copying the sample into Jaggery server", e);
		}
	}

	private String computeSourcePath(String fileName) {
		String samplesDir = System.getProperty("samples.dir");
		return samplesDir + File.separator + fileName;
	}
	
	private String computeSourcePath(String appName,String fileName) {
		String samplesDir = System.getProperty("samples.dir");
		return samplesDir + File.separator + appName + File.separator +  fileName;
	}
	private String computeDestPath(String carbonHome, String fileName) {
		String deploymentPath = carbonHome + File.separator + "apps"
				+ File.separator + "testapp";
		File depFile = new File(deploymentPath);
		if (!depFile.exists() && !depFile.mkdir()) {
			log.error("Error while creating the deployment folder : "
					+ deploymentPath);
		}
		return deploymentPath + File.separator + fileName;
	}
	private String computeDestPath(String carbonHome, String Appname,String fileName) {
		String deploymentPath = carbonHome + File.separator + "apps"
				+ File.separator + Appname + File.separator ;
		File depFile = new File(deploymentPath);
		if (!depFile.exists() && !depFile.mkdir()) {
			log.error("Error while creating the deployment folder : "
					+ deploymentPath);
		}
		return deploymentPath + File.separator + fileName;
	}
}
