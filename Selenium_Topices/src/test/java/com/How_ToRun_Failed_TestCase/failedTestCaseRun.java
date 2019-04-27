package com.How_ToRun_Failed_TestCase;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class failedTestCaseRun {
	
	
	/**
	 * How to execute the failure Test Cases in TestNG
	 * @param args
	 * Why test fails 
	 * Application down
	 * server down 
	 * Network issue
	 * Screption issue ( maybe locator is changed or there is some updates)
	 * Application issue (when we excuteing our test some validation fails it is real issue, then report as bug)
	 */
	public static void main(String[] args) {

		TestNG run = new TestNG();
		List<String> failedXML = new ArrayList<String>();
				failedXML.add(
				"C:\\Users\\aboba\\eclipse-workspace\\jQuery_Project\\Broken_Link\\test-output\\Default suite\\testng-failed.xml");
		run.setTestSuites(failedXML);
		run.run();

	}

}
