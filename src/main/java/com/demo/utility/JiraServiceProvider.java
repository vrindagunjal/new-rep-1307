package com.demo.utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import net.rcarz.jiraclient.BasicCredentials;


import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	private JiraClient Jira;
	private String project;
	private String JiraUrl;
	private String createdIssueId;
	public JiraServiceProvider(String JiraUrl, String username, String password, String project) {
		this.JiraUrl = JiraUrl;
		BasicCredentials creds = new BasicCredentials(username, password);
		Jira = new JiraClient(JiraUrl, creds);
		this.project = project;
	}

	public void createJiraIssue()
			throws JiraException, UnsupportedEncodingException {

		String host = com.demo.configuration.Configuration.urlJira;
		// int port = 8080;
		String userName = com.demo.configuration.Configuration.email;
		String password = com.demo.configuration.Configuration.password;
		String ResponseData;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String jsonObj = com.demo.common.Common.readJsonFile("Create");

		HttpHost targetHost = new HttpHost(com.demo.configuration.Configuration.host, -1, "https");

		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope(targetHost.getHostName(), targetHost.getPort(), targetHost.getSchemeName()),
				new UsernamePasswordCredentials(userName, password));

		HttpPost httpPost = new HttpPost(com.demo.configuration.Configuration.urlJira + "rest/api/2/issue");
		StringEntity entity = new StringEntity(jsonObj);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();

		authCache.put(targetHost, basicAuth);

		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

		try {

			HttpResponse httpResponse = httpClient.execute(httpPost, localcontext);
			HttpEntity entitydata = httpResponse.getEntity();
			ResponseData = new String(EntityUtils.toByteArray(entitydata));
			
			
			 createdIssueId=ResponseData.split(",")[0].toString().split(":")[1];
			 System.out.println("createdIssueId: " + createdIssueId);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
			System.out.println("Created: "+createdIssueId);
		}
		
		
		httpClient.getConnectionManager().shutdown();
	}

	public void editJiraIssue() throws UnsupportedEncodingException {
		
		
		createdIssueId=createdIssueId.split("\"")[1].split("\"")[0];
		System.out.println("Issue id:"+createdIssueId);
		String host = com.demo.configuration.Configuration.urlJira;
		// int port = 8080;
		String userName = com.demo.configuration.Configuration.email;
		String password = com.demo.configuration.Configuration.password;
		String ResponseData;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String jsonObj = com.demo.common.Common.readJsonFile("Edit");
			
		HttpHost targetHost = new HttpHost(com.demo.configuration.Configuration.host, -1, "https");

		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope(targetHost.getHostName(), targetHost.getPort(), targetHost.getSchemeName()),
				new UsernamePasswordCredentials(userName, password));
String editURL=com.demo.configuration.Configuration.urlJira+"rest/api/2/issue/"+createdIssueId+"/transitions";

System.out.println("editURL"+editURL);
		HttpPost httpPost = new HttpPost(editURL);
		StringEntity entity = new StringEntity(jsonObj);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();

		authCache.put(targetHost, basicAuth);

		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

		try {

			HttpResponse httpResponse = httpClient.execute(httpPost, localcontext);
			//HttpEntity entitydata = httpResponse.getEntity();
		//	ResponseData = new String(EntityUtils.toByteArray(entitydata));
			//System.out.println("Response: " + ResponseData);
			//String issueId = ResponseData.split(",")[0].toString().split(":")[1];
			//System.out.println(issueId);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpClient.getConnectionManager().shutdown();
		
		
		httpClient.getConnectionManager().shutdown();
	}
}
