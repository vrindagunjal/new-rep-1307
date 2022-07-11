// HomePageTest.java

package com.demo.project;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.io.File;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.listener.TestListener;

@Listeners(TestListener.class)
public class HomePageTest {

	private WebDriver driver;

	private String baseUrl;

	@BeforeClass

	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver",com.demo.configuration.Configuration.browserPath);

		driver = new ChromeDriver();

		baseUrl = com.demo.configuration.Configuration.url;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

//Custom annotation explained in following sections

	@Test

	public void verifyHomepageHeaderText() throws Exception {

		driver.get(baseUrl);

		String header=driver.getTitle();
		assertEquals(header, "Orange HRM1", "Wrong header text displayed in Home page");

	}

	

	@AfterClass

	public void tearDown() throws Exception {

		driver.quit();

	}

}
