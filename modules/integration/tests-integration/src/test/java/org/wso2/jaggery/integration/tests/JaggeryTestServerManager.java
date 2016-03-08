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
            log.error("Error while creating the deployment folder : "
                    + deploymentPath);
        }

        String[] fileNames = {"jaggery.conf", "testhtml.html", "multipleheaders.jag", "jsonTest.jag", "email.jag",
                "database.jag", "feed.jag", "file.jag", "testfile.txt", "log.jag", "wsrequest.jag", "request.jag",
                "response.jag", "session.jag", "cookie.jag", "application.jag", "xmlhttprequest.jag", "syntax.jag",
                "require.jag", "put.jag", "post.jag", "get.jag", "delet.jag", "uri.jag", "inculde.jag", "entry.jag",
                "wsstub.jag", "metadata.jag", "resources.jag", "collection.jag", "oauth.jag", "oauth-linkedin.jag",
                "oauth-saml.jag", "server.jag", "client.jag", "clientTester.jag", "client.html", "process.jag",
                "nativejson.jag"};
        // copy files
        for (String fileName: fileNames) {
            copyFile(fileName, carbonHome);
        }

        // jaggery configuration files
        String[] jagFileNames = {"init.js", "jaggery.conf"};
        // copy files
        for (String fileName: jagFileNames) {
            copyConfigFile(fileName, carbonHome);
        }
    }

    private void copyFile(String fileName, String carbonHome) {
        String sourcePath = computeSourcePath(fileName);
        String destinationPath = computeDestPath(carbonHome, fileName);
        copySampleFile(sourcePath, destinationPath);
    }

    private void copyConfigFile(String fileName, String carbonHome) {
        String sourcePath = computeSourcePath(JAGGERY_TEST_APP, fileName);
        String destinationPath = computeDestPath(carbonHome, JAGGERY_TEST_APP, fileName);
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

    private String computeSourcePath(String appName, String fileName) {
        String samplesDir = System.getProperty("samples.dir");
        return samplesDir + File.separator + appName + File.separator + fileName;
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

    private String computeDestPath(String carbonHome, String Appname, String fileName) {
        String deploymentPath = carbonHome + File.separator + "apps"
                + File.separator + Appname + File.separator;
        File depFile = new File(deploymentPath);
        if (!depFile.exists() && !depFile.mkdir()) {
            log.error("Error while creating the deployment folder : "
                    + deploymentPath);
        }
        return deploymentPath + File.separator + fileName;
    }
}
