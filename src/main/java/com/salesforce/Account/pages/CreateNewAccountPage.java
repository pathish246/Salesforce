package com.salesforce.Account.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.common.BasePage;

public class CreateNewAccountPage extends BasePage {

	WebDriver driver;

	@FindBy(xpath = "//h2[contains(text(),'New Account') and contains(@class,'Title')]")
	WebElement title_NewAccount;

	@FindBy(xpath = "//span[contains(text(),'Account Name')]/../following-sibling::div/div/div/div/input")
	WebElement textbox_AccountName;

	@FindBy(xpath = "//span[contains(text(),'Website')]/../following-sibling::input")
	WebElement textbox_Website;

	@FindBy(xpath = "//span[contains(text(),'Phone')]/../following-sibling::input")
	WebElement textbox_Phone;

	@FindBy(xpath = "//span[contains(text(),'Description')]/../following-sibling::textarea")
	WebElement textbox_Description;

	@FindBy(xpath = "//span[contains(text(),'Employees')]/../following-sibling::input")
	WebElement textbox_Employees;

	@FindBy(xpath = "//span[contains(text(),'Type')]/../following-sibling::div//a")
	WebElement dropdownLink_AccountType;

	@FindBy(xpath = "//span[contains(text(),'Industry')]/../following-sibling::div//a")
	WebElement dropdownLink_Industry;

	public CreateNewAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* Option_AccountType, Option_Industry - 
	 * this should come from excel/mongo(any db, mongo is preferable due to nosql behavior)
	 *
	 * Number of employees as well if needed should come from test data
	 */

	public void AccountInformation() {
		WebElement Option_AccountType = null;
		WebElement Option_Industry = null;
		if (isElementPresent(driver, title_NewAccount, 30)) {
			setElement(driver, textbox_AccountName, "Account Name 1", "Success :- Entered Account Name",
					"Failed :- Unable to Enter Account Name");
			clickOnElement(driver, dropdownLink_AccountType, "Success :- Clicked on Account Type",
					"Failed :- Unable to Click on Account Type");
			Option_AccountType = driver.findElement(By.xpath("(//a[@title='Partner'])"));
			clickOnElement(driver, Option_AccountType, "Success :- Clicked on Partner Account Type",
					"Failed :- Unable to Click on Partner Account Type");
			setElement(driver, textbox_Website, "Website Name 1", "Success :- Entered Website Name",
					"Failed :- Unable to Enter Website Name");
			setElement(driver, textbox_Phone, "1112221112", "Success :- Entered Phone Number",
					"Failed :- Unable to Enter Phone Number");
			setElement(driver, textbox_Description, "Description 1", "Success :- Entered Description",
					"Failed :- Unable to Description");
			clickOnElement(driver, dropdownLink_Industry, "Success :- Clicked on Account Type",
					"Failed :- Unable to Click on Account Type");
			Option_Industry = driver.findElement(By.xpath("(//a[@title='Engineering'])"));
			clickOnElement(driver, Option_Industry, "Success :- Clicked on Engineering",
					"Failed :- Unable to Click on Engineering");
			setElement(driver, textbox_Employees, "100", "Success :- Entered Employees Count",
					"Failed :- Unable to Employees Count");
		} else {
			Assert.fail("APP ERROR :: New Account Pop Up is not diplayed");
		}
	}

}
