package com.salesforce.home.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import com.salesforce.Account.pages.CreateNewAccountPage;
import com.salesforce.common.Initiation;
import com.salesforce.home.pages.HomePage;
import com.salesforce.home.pages.LoginPage;

public class CreateNewAccount extends Initiation {

	@Test
	public void NewAccount() {
		try {
			launchBrowser();
			navigateToSalesforce(Constants.getProperty("URL"));
			login();
			AddNewAccount();
		}catch(Throwable e) {
			String test = e.getMessage();
			if(test.contains("Test")) { // you can customize error message using throwable as both Exception and Error extend Throwable.
				Assert.fail("Element Not Found Issue");
			}
		}
	}
	
	public void login() {
		LoginPage objLoginPage = new LoginPage(getDriver());
		objLoginPage.login();
	}
	
	public void AddNewAccount() {
		HomePage homepage = new HomePage(getDriver());
		CreateNewAccountPage createNewAccount = new CreateNewAccountPage(getDriver());
		homepage.NavigateToAccount("NEW ACCOUNT"); // this should come from excel/mongo(any db, mongo is preferable due to nosql behavior)
		createNewAccount.AccountInformation();
	}
	
}