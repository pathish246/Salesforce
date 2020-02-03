package com.salesforce.common;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class Initiation extends BasePage {

	WebDriver driver;

	@BeforeClass
	public void OnStart() throws IOException {
		loadConstantsFile();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void launchBrowser() throws IOException {
		String dir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",
				dir + "\\src\\main\\resources\\DriverExecutables\\chromedriver.exe");
		driver = new ChromeDriver();
		setDriver(driver);
		driver.manage().window().maximize();
	}

	public void navigateToSalesforce(String URL) {
		 driver.get(URL);
	}

}
