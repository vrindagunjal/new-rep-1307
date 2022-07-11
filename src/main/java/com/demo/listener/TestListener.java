//TestListener.java

package com.demo.listener;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.exception.ExceptionUtils;

import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.demo.utility.JiraServiceProvider;

import net.rcarz.jiraclient.JiraException;

public class TestListener implements ITestListener {

	@Override

	public void onTestFailure(ITestResult result) {

		JiraServiceProvider JiraServiceProvider = new JiraServiceProvider(com.demo.configuration.Configuration.urlJira,

				com.demo.configuration.Configuration.email, com.demo.configuration.Configuration.password, com.demo.configuration.Configuration.project);

		try {
			JiraServiceProvider.createJiraIssue();
			JiraServiceProvider.editJiraIssue();
			
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override

	public void onTestSkipped(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onStart(ITestContext context) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onFinish(ITestContext context) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onTestStart(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onTestSuccess(ITestResult result) {

		// TODO Auto-generated method stub

	}

}
