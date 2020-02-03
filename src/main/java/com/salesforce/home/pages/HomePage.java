package com.salesforce.home.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.common.BasePage;

public class HomePage extends BasePage{

	@FindBy(xpath = "//a[@title='Accounts']/following-sibling::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button/a/lightning-icon/lightning-primitive-icon")
	WebElement link_Accounts;

	@FindBy(xpath = "//span[text()='New Account']/../lightning-icon[contains(@class,'add')]/lightning-primitive-icon")
	WebElement link_AddNewAccount;

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void NavigateToAccount(String Action) {
		if(isElementPresent(driver, link_Accounts, 30)) {
			switch (Action.toUpperCase()) {
			case "NEW ACCOUNT":
					clickOnElement(driver, link_Accounts, "Success :- Clicked on Accounts Link", "Failed :- Unable to Click on Accounts Link");
					clickOnElement(driver, link_AddNewAccount, "Success :- Clicked on New Account Link", "Failed :- Unable to Click on New Account Link");
				break;
			case "MY ACCOUNTS":
				 // HERE COMES MY ACCOUNT
				break;
			default: // HERE COMES RECENTLY VIEWED TAB
				break;
			}
		} else {
			Assert.fail("APP ERROR :: Unable to find Accounts Link on Home Page");
		}
		
	}

}
