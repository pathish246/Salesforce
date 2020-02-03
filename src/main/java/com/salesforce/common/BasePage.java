package com.salesforce.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class BasePage {

	WebDriver driver;
	
	public static Logger logger = LogManager.getLogger(BasePage.class);

	public static Properties Constants = new Properties();

	/*
	 * Below Method is used to read file and use it anywhere in the project just by
	 * Constants.getProperty("Key")
	 */

	public void loadConstantsFile() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/main/resources/Properties/constants.properties");
		FileInputStream fs = new FileInputStream(file);
		Constants.load(fs);
	}

	/*
	 * public BasePage(WebDriver driver) { this.driver = driver; }
	 */
	/*
	 * Below Method is used to click on any element
	 */

	public void clickOnElement(WebDriver driver, WebElement element, String SuccessMessage, String FailureMessage) {
		try {
			fluentWait(driver, element);
			highlightElement(driver, element);
			element.click();
			logger.debug(SuccessMessage);
		} catch (Exception e) {
			Assert.fail(FailureMessage);
		}
	}

	/*
	 * Below Method is used to enter text on any input element
	 */

	public void setElement(WebDriver driver, WebElement element, String textToEnter, String SuccessMessage,
			String FailureMessage) {
		try {
			fluentWait(driver, element);
			highlightElement(driver, element);
			element.sendKeys(textToEnter);
			logger.debug(SuccessMessage);
		} catch (Exception e) {
			Assert.fail(FailureMessage + e);
		}
	}

	/*
	 * Below Method is used to wait up to a default 60 seconds for an element
	 */

	public void fluentWait(WebDriver driver, WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(60, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS)
		.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class).until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * Below Method is used to wait up to amount of time in seconds (that is passed
	 * as parameter) for an element
	 */

	public void fluentWait(WebDriver driver, WebElement element, int TimeInSeconds) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(TimeInSeconds, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * Below Method is used to return boolean w.r.t presence of an element (up to
	 * default 60)
	 */

	public boolean isElementPresent(WebDriver driver, WebElement element) {
		try {
			fluentWait(driver, element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Below Method is used to return boolean w.r.t presence of an element (up to
	 * dynamic time passed)
	 */

	public boolean isElementPresent(WebDriver driver, WebElement element, int TimeInSeconds) {
		try {
			fluentWait(driver, element, TimeInSeconds);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Below Method is used to highlight element
	 */

	public void highlightElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid yellow;');", element);
			Thread.sleep(1000);
			js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
		} catch (Exception e) {
			logger.debug("Exception Highlighing Element");
		}
	}

	/*
	 * Below Method is used to hover on a particlar element
	 */

	public void hoverOnElement(WebDriver driver, WebElement element, String SuccessMessage, String FailureMessage) {
		fluentWait(driver, element);
		highlightElement(driver, element);
		Actions objActions = new Actions(driver);
		objActions.moveToElement(element).perform();
		logger.debug(SuccessMessage);

	}

	public boolean switchToWindow(String Title) {
		Set<String> windows = driver.getWindowHandles();
		String windowTitle = "";
		boolean switched = false;
		for (String window : windows) {
			driver.switchTo().window(window);
			windowTitle = driver.getTitle();
			if (windowTitle.equalsIgnoreCase(Title)) {
				switched = true;
				break;
			}
		}
		return switched;
	}

}
