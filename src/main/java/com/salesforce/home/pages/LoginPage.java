package com.salesforce.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.salesforce.common.BasePage;

public class LoginPage extends BasePage {

	WebDriver driver;

	@FindBy(id = "username")
	WebElement textbox_UserName;

	@FindBy(id = "password")
	WebElement textbox_Password;

	@FindBy(id = "Login")
	WebElement btn_Login;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login() {
		if (isElementPresent(driver, textbox_UserName)) {
			setElement(driver, textbox_UserName, Constants.getProperty("UserName"),
					"Success :- Entered User Name as " + Constants.getProperty("UserName"),
					"Failed :- Unable to Enter Username");
			setElement(driver, textbox_Password, Constants.getProperty("Password"),
					"Success :- Entered Password as " + Constants.getProperty("UserName"),
					"Failed :- Unable to Enter Password");
			clickOnElement(driver, btn_Login, "Success :- Clicked on Login Button",
					"Failed :- Unable to Click on Login Button");
		} else {
			Assert.fail("APP ERROR :: Unable to find Salesforce Login Screen");
		}
	}

}
