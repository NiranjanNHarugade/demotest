package Utilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.gson.Gson;

import Pages.Accounts;
import Pages.CST_Routing;
import Pages.Cases;
import Pages.CreateCloneOrders;
import Pages.CreateSalesOrder;
import Pages.HomePage;
import Pages.Login;
import Pages.SalesForce_Inspector;


public class CommonFunctions extends BaseTest {

	// Created By: Abhishek Palankar on 15-May-2023
	// Description: Method to login to the specific user
	// Parameter names that needs to be passed: Alias User name
	public static void aliasLogin(String aliasUser) throws Exception {
		String actualaliasUserName = "";
		try {

			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				if (driver.findElement(By.xpath("//h2[text()='Sorry to interrupt']")).isDisplayed()) {

					javaClickByLocator(By.xpath(
							"//h2[text()='Sorry to interrupt']/ancestor::div[@class='modal-container slds-modal__container']//button[@title='OK']"));
				}
			} catch (Exception e) {

			}
			Thread.sleep(2000);
			driver.findElement(Login.setUpGearLogo).click();
			driver.findElement(Login.setUpOption).click();
			String winHandleBefore = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '100%'");
			/*
			 * CommonFunctions.elementIsDisplayed( driver.findElement(By.xpath(
			 * "//div[@class='setup-header-element']//span[text()='Home']")));
			 */
			try {
				new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@class='setup-header-element']//span[text()='Home']")));
				if (driver.findElement(By.xpath("//div[@class='setup-header-element']//span[text()='Home']"))
						.isDisplayed()) {
					assertTrue(
							driver.findElement(By.xpath("//div[@class='setup-header-element']//span[text()='Home']"))
									.isDisplayed(),
							"<b>Expected Result:</b> Set Up page should be displayed. <br /> <b>Actual Result:</b> Set up page displayed successfully.");
					logger.pass(
							"<b>Expected Result:</b> Set Up page should be displayed. <br /> <b>Actual Result:</b> Set up page displayed successfully.");

				}

			} catch (Exception e) {
				// TODO: handle exception
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b> Set Up page should be displayed. <br /> <b>Actual Result:</b> Set up page is not displayed.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				Assert.fail(
						"<b>Expected Result:</b> Set Up page should be displayed. <br /> <b>Actual Result:</b> Set up page is not displayed.");
			}
			Thread.sleep(2000);
			driver.findElement(Login.globalSearchTxtBox).click();
			Thread.sleep(2000);
			driver.findElement(Login.globalSearchTxtBox).sendKeys(aliasUser);
			Thread.sleep(2000);
			driver.findElement(Login.globalSearchTxtBox).sendKeys(Keys.ENTER);
			CommonFunctions.elementIsDisplayed(
					driver.findElement(By.xpath("//table//tbody//tr//td//a[text()='" + aliasUser + "']")));
			javaClickByLocator(By.xpath("//table//tbody//tr//td//a[text()='" + aliasUser + "']"));

			/*
			 * Thread.sleep(10000);
			 * driver.findElement(Login.globalSearchTxtBox).sendKeys(Keys.TAB);
			 */
			WebElement frame = driver
					.findElement(By.xpath("//*[@id=\"setupComponent\"]/div[2]/div/div/force-aloha-page/div/iframe"));
			driver.switchTo().frame(frame);
			// CommonFunctions.elementIsDisplayed(driver.findElement(Login.aliasLoginButton));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(Login.aliasLoginButton));
			javaClickByLocator(Login.aliasLoginButton);

			Thread.sleep(5000);
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='slds-notify_alert system-message level-info slds-theme_info']//span")));
			actualaliasUserName = driver
					.findElement(By
							.xpath("//div[@class='slds-notify_alert system-message level-info slds-theme_info']//span"))
					.getText();

			if (!actualaliasUserName.contains(aliasUser)) {
				driver.navigate().refresh();
				Thread.sleep(2000);
			}
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='slds-notify_alert system-message level-info slds-theme_info']//span")));
			actualaliasUserName = driver
					.findElement(By
							.xpath("//div[@class='slds-notify_alert system-message level-info slds-theme_info']//span"))
					.getText();

			try {
				int size = driver.findElements(Constants.dialogueMessage).size();

				if (size > 0) {
					for (int i = 0; i < size; i++) {
						// driver.findElements(Constants.dialogueMessage).get(i).click();
						javaClickByLocator(Constants.dialogueMessage);
						Thread.sleep(2000);
						// javaClickByWebElement(driver.findElements(Constants.dialogueMessage).get(i));
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			closeMultipleTabs();
			if (actualaliasUserName.contains(aliasUser)) {
				assertTrue(actualaliasUserName.contains(aliasUser),
						"<b>Expected Result:</b> User should Login as <b>'" + aliasUser
								+ "'</b>. <br /> <b>Actual Result:</b>  User is successfully Logged in as <b>'"
								+ actualaliasUserName + "'</b>.");
				logger.pass("<b>Expected Result:</b> User should Login as <b>'" + aliasUser
						+ "'</b>. <br /> <b>Actual Result:</b>  User is successfully Logged in as <b>'"
						+ actualaliasUserName + "'</b>.");

			} else {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b> User should Login as <b>'" + aliasUser
								+ "'</b>. <br /> <b>Actual Result:</b> User is failed to Login as <b>'"
								+ actualaliasUserName + "'</b>.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				Assert.fail("<b>Expected Result:</b> User should Login as <b>'" + aliasUser
						+ "'</b>. <br /> <b>Actual Result:</b> User is failed to Login as <b>'" + actualaliasUserName
						+ "'</b>.");
			}

		} catch (Exception e) {

			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b> User should Login as <b>'" + aliasUser
							+ "'</b>. <br /> <b>Actual Result:</b> User is failed to Login as <b>'"
							+ actualaliasUserName + "'</b>. <br />" + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

			Assert.fail("<b>Expected Result:</b> User should Login as <b>'" + aliasUser
					+ "'</b>. <br /> <b>Actual Result:</b> User is failed to Login as <b>'" + actualaliasUserName
					+ "'</b>.");

		}

		status = logger.getStatus();

	}

	/*
	 * // Created by Abhishek Palankar on 25-May-2023 // This method is used to
	 * select app/obj from app launcher public static void
	 * selectAppOrObjFromAppLauncher(String type, String value) throws Exception {
	 * String actual = ""; String type1 = type.toLowerCase(); switch (type1) { case
	 * "object": new WebDriverWait(driver, explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.appLauncher));
	 * driver.findElement(HomePage.appLauncher).click(); new WebDriverWait(driver,
	 * explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.viewAllBtn));
	 * WebElement ViewAllbutton = driver.findElement(HomePage.viewAllBtn);
	 * javaClickByWebElement(ViewAllbutton); new WebDriverWait(driver,
	 * explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.
	 * appLauncherSearchTxtBox));
	 * fillFieldByLocator(HomePage.appLauncherSearchTxtBox, value); new
	 * WebDriverWait(driver, explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//mark[text()='" + value + "']")));
	 * javaClickByLocator(By.xpath("//mark[text()='" + value + "']"));
	 * Thread.sleep(2000); if (driver.findElements(By.xpath(
	 * "//div[@class='slds-breadcrumb__item slds-line-height--reset']//span[text()='"
	 * + value + "']")) .size() > 0) { actual = GetText(By.xpath(
	 * "//div[@class='slds-breadcrumb__item slds-line-height--reset']//span[text()='"
	 * + value + "']")); } else if
	 * (driver.findElements(By.xpath("//li//span[text()='" + value + "']")).size() >
	 * 0) { actual = GetText(By.xpath("//li//span[text()='" + value + "']")); } if
	 * (actual.equalsIgnoreCase(value)) {
	 * loggerPass("User should be able to open object <b>" + value + "</b>",
	 * "User has successfully launched application <b>" + actual + "</b>", "No"); }
	 * else { loggerFail("User should be able to open object <b>" + value + "</b>",
	 * "User is failed to open the object <b>" + actual + "</b>", true); } break;
	 * case "app": new WebDriverWait(driver, explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.appName));
	 * WebElement applicationname = driver.findElement(HomePage.appName); if
	 * (applicationname.getText().contains(value) == false) {
	 * 
	 * new WebDriverWait(driver, explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.appLauncher));
	 * driver.findElement(HomePage.appLauncher).click(); new WebDriverWait(driver,
	 * explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.viewAllBtn));
	 * WebElement ViewAllbutton1 = driver.findElement(HomePage.viewAllBtn);
	 * javaClickByWebElement(ViewAllbutton1); new WebDriverWait(driver,
	 * explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.
	 * appLauncherSearchTxtBox));
	 * fillFieldByLocator(HomePage.appLauncherSearchTxtBox, value); new
	 * WebDriverWait(driver, explicitWaitTimeOut).until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath("//mark[text()='" +
	 * value + "']"))); javaClickByLocator(By.xpath("//mark[text()='" + value +
	 * "']")); new WebDriverWait(driver, explicitWaitTimeOut)
	 * .until(ExpectedConditions.visibilityOfElementLocated(HomePage.appName)); }
	 * 
	 * actual = GetText(HomePage.appName);
	 * 
	 * if (actual.equalsIgnoreCase(value)) {
	 * loggerPass("User should be able to launch application <b>" + value + "</b>",
	 * "User has successfully launched application <b>" + actual + "</b>", "No"); }
	 * else { loggerFail("User should be able to launch application <b>" + value +
	 * "</b>", "User is failed launch the application <b>" + actual + "</b>", true);
	 * }
	 * 
	 * break; default: System.out.println("Given value is incorrect");
	 * 
	 * break; } }
	 */

	// Created by Abhishek Palankar on 25-May-2023
	// This method is used to select app/obj from app launcher
	public static void selectAppOrObjFromAppLauncher(String type, String value) throws Exception {
		String actual = "";

		String type1 = type.toLowerCase();
		switch (type1) {
		case "object":
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(HomePage.appLauncher));
			driver.findElement(HomePage.appLauncher).click();
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(HomePage.searchTxtBox));

			fillFieldByLocator(HomePage.searchTxtBox, value);
			
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label ='" + value + "']")));
			javaClickByLocator(By.xpath("//a[@data-label ='" + value + "']"));
			Thread.sleep(2000);
			if (driver.findElements(By.xpath(
					"//div[@class='slds-breadcrumb__item slds-line-height_reset slds-line-height--reset']//span[text()='" + value + "']"))
					.size() > 0) {
				actual = GetText(By.xpath(
						"//div[@class='slds-breadcrumb__item slds-line-height_reset slds-line-height--reset']//span[text()='" + value + "']"));
			} else if (driver.findElements(By.xpath("//a/span[text()='" + value + "']")).size() > 0) {
				actual = GetText(By.xpath("//a/span[text()='" + value + "']"));
			}
			if (actual.equalsIgnoreCase(value)) {
				loggerPass("User should be able to open object <b>" + value + "</b>",
						"User is able to open object <b>" + actual + "</b>", "No");
			} else {
				loggerFail("User should be able to open object <b>" + value + "</b>",
						"User is failed to open the object <b>" + actual + "</b>", true);
			}
			break;
		case "app":
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(HomePage.appName));
			WebElement applicationname = driver.findElement(HomePage.appName);
			if (applicationname.getText().contains(value) == false) {

				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(HomePage.appLauncher));
				driver.findElement(HomePage.appLauncher).click();
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(HomePage.searchTxtBox));

				fillFieldByLocator(HomePage.searchTxtBox, value);
				new WebDriverWait(driver, explicitWaitTimeOut).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label ='" + value + "']")));
				javaClickByLocator(By.xpath("//a[@data-label ='" + value + "']"));

				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(HomePage.appName));
			}

			actual = GetText(HomePage.appName);

			if (actual.equalsIgnoreCase(value)) {
				loggerPass("User should be able to launch application <b>" + value + "</b>",
						"User has successfully launched application <b>" + actual + "</b>", "No");
			} else {
				loggerFail("User should be able to launch application <b>" + value + "</b>",
						"User is failed launch the application <b>" + actual + "</b>", true);
			}

			break;
		default:
			System.out.println("Given value is incorrect");

			break;
		}
	}

	// Created by Abhishek Palankar on 14-Dec-2022 
	// This method is used to select application from app launcher

	public static void selectAppsFromAppLauncher(String appName) throws Exception {

		String actualAppName = "";

		try {

			if (driver.findElement(By.xpath("//span[@title='" + appName + "']")).isDisplayed()) {
				actualAppName = driver.findElement(By.xpath("//span[@title='" + appName + "']")).getText();
				assertTrue(driver.findElement(By.xpath("//span[@title='" + appName + "']")).isDisplayed(),
						"<b>Expected Result:</b> User should be able to launch application <b>'" + appName
								+ "'</b>. <br /> <b>Actual Result:</b> User has successfully launched application <b>'"
								+ actualAppName + "'</b>.");

				logger.pass("<b>Expected Result:</b> User should be able to launch application <b>'" + appName
						+ "'</b>. <br /> <b>Actual Result:</b> User has successfully launched application <b>'"
						+ actualAppName + "'</b>.");
				if (driver.findElements(By.xpath("//h2[contains(text(),'Save changes')]")).size() > 0) {
					javaClickByLocator(By.xpath("//button[text()='Discard Changes']"));
				}

				if (driver
						.findElements(By.xpath(
								"//div[@class='modal-body scrollable slds-modal__content slds-p-around--medium']"))
						.size() > 0) {
					CommonFunctions.javaClickByLocator(By.xpath("//button[@title='Close this window']"));
				}

			}

		}

		catch (Exception e) {
			try {

				driver.findElement(HomePage.appLauncher).click();
				try {
					if (driver.findElements(HomePage.searchTxtBox).size() > 0
							|| driver.findElements(HomePage.appLauncherSearchTxtBox).size() > 0) {

					} else {
						driver.findElement(HomePage.appLauncher).click();
						Thread.sleep(3000);
					}

				}

				catch (Exception f) {
					driver.findElement(HomePage.appLauncher).click();
					Thread.sleep(3000);

				}

				if (driver.findElements(HomePage.searchTxtBox).size() > 0) {
					javaClickByLocator(HomePage.searchTxtBox);
					Thread.sleep(1000);
					driver.findElement(HomePage.searchTxtBox).sendKeys(appName);
					Thread.sleep(2000);
					javaClickByWebElement(driver.findElement(By.xpath("//a[@data-label = '" + appName + "']")));
					if (driver.findElements(By.xpath("//h2[contains(text(),'Save changes')]")).size() > 0) {
						javaClickByLocator(By.xpath("//button[text()='Discard Changes']"));
					}
				}
				if (driver.findElements(HomePage.appLauncherSearchTxtBox).size() > 0) {

					javaClickByLocator(HomePage.appLauncherSearchTxtBox);
					Thread.sleep(1000);
					driver.findElement(HomePage.appLauncherSearchTxtBox).sendKeys(appName);
					Thread.sleep(4000);
					// driver.findElement(By.xpath("//div[@data-name='" +appName + "']")).click();
					// // javaClickByLocator(By.xpath("//div[@data-name='"+ appName + "']"));
					javaClickByLocator(By.xpath("//mark[text()='" + appName + "']/parent::a"));
					if (driver.findElements(By.xpath("//h2[contains(text(),'Save changes')]")).size() > 0) {
						javaClickByLocator(By.xpath("//button[text()='Discard Changes']"));
					}
				}

				// Thread.sleep(5000);
				new WebDriverWait(driver, explicitWaitTimeOut).until(

						ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + appName + "']")));
				driver.findElement(By.xpath("//a[@title='" + appName + "']")).isDisplayed();
				assertTrue(driver.findElement(By.xpath("//a[@title='" + appName + "']")).isDisplayed(),
						"<b>Expected Result:</b> User should be able to launch application <b>'" + appName
								+ "'</b>. <br /> <b>Actual Result:</b> User has successfully launched application <b>'"
								+ actualAppName + "'</b>.");
				actualAppName = driver.findElement(By.xpath("//a[@title='" + appName + "']")).getText();

				logger.pass("<b>Expected Result:</b> User should be able to launch application <b>'" + appName
						+ "'</b>. <br /> <b>Actual Result:</b> User has successfully launched application <b>'"
						+ actualAppName + "'</b>.");
				if (driver.findElements(By.xpath("//h2[contains(text(),'Save changes')]")).size() > 0) {
					javaClickByLocator(By.xpath("//button[text()='Discard Changes']"));
				}

				if (driver
						.findElements(By.xpath(
								"//div[@class='modal-body scrollable slds-modal__content slds-p-around--medium']"))
						.size() > 0) {
					CommonFunctions.javaClickByLocator(By.xpath("//button[@title='Close this window']"));
				}

			} catch (Exception e2) {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b> User should be able lunch application <b>'" + appName
								+ "'</b>. <br /> <b>Actual Result:</b> User is failed launch the application <b>'"
								+ actualAppName + "'</b>." + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				Assert.fail("<b>Expected Result:</b> User should be able lunch application <b>'" + appName
						+ "'</b>. <br /> <b>Actual Result:</b> User is failed launch the application <b>'"
						+ actualAppName + "'</b>.");
			}

		}

		status = logger.getStatus();

	}

	// Created By: Abhishek Palankar on 15-May-2023
	// Description: Verifying List view option displayed
	// Parameter names that needs to be passed: List view Option value
	public static void verifyListViewSelection(String listViewOption) throws InterruptedException, IOException {
		String actualListViewOption = "";
		try {
			if (driver.findElement(By.xpath("//span[text()= '" + listViewOption + "']")).isDisplayed()) {
				assertTrue(driver.findElement(By.xpath("//span[text()= '" + listViewOption + "']")).isDisplayed(),
						"<b>Expected Result:</b> The list view option should be <b>'" + listViewOption
								+ "'</b>. <br /> <b>Actual Result:</b> The list view option is displayed as expected <b>'"
								+ actualListViewOption + "'</b>.");
				actualListViewOption = driver.findElement(By.xpath("//span[text()= '" + listViewOption + "']"))
						.getText();
				logger.pass("<b>Expected Result:</b> The list view option should be <b>'" + listViewOption
						+ "'</b>. <br /> <b>Actual Result:</b> The list view option is displayed as expected <b>'"
						+ actualListViewOption + "'</b>.");

			}
		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail("<b>Expected Result:</b> The list view option should be <b>'" + listViewOption
					+ "'</b>. <br /> <b>Actual Result:</b> The list view option is not displayed as expected <b>'"
					+ actualListViewOption + "'</b>.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail("<b>Expected Result:</b> The list view option should be <b>'" + listViewOption
					+ "'</b>. <br /> <b>Actual Result:</b> The list view option is not displayed as expected <b>'"
					+ actualListViewOption + "'</b>.");

		}
		status = logger.getStatus();

	}

	// Created By: Abhishek Palankar on 15-May-2023
	// Description: used to click on certain fields using Javascript
	// Parameter names that needs to be passed: Locator of the Element
	public static void javaClickByLocator(By locator) {
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
		int attempts = 0;
		while (attempts < 10) {
			try {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	// Created By: Abhishek Palankar on 15-May-2023
	// Description: used to click on certain fields using Javascript
	// Parameter names that needs to be passed: WebElement of the Element
	public static void javaClickByWebElement(WebElement element) {
		int attempts = 0;
		while (attempts < 10) {
			try {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	// Created By: Abhishek Palankar on 25-May-2023
	// Decription :Used to close the multiple tabs
	public static void closeMultipleTabs() throws InterruptedException {
		
		
		List<WebElement> multipleclosebuttons = driver.findElements(By.xpath(
					"//button[@class='slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container']"));
		
		if(multipleclosebuttons.size()>0)
		{
			for (int i = 0; i < multipleclosebuttons.size(); i++) {
				javaClickByWebElement(multipleclosebuttons.get(i));
			}
		
			if (driver.findElements(CreateSalesOrder.discardBtn).size() > 0) {
				CommonFunctions.javaClickByLocator(CreateSalesOrder.discardBtn);}
			
			System.out.println("All tabs are closed");
	
		}else {
			System.out.println("Tabs are not Open");	
		}
	
	
//			
		
		/*
		 * try { handleReviewPopUp(); int size = driver.findElements(By.xpath(
		 * "//div[@class='tabBar slds-grid']//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container']"
		 * )) .size();
		 * 
		 * for (int i = size; i > 0; i--) {
		 * 
		 * driver.findElement(By.xpath(
		 * "(//div[@class='tabBar slds-grid']//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])["
		 * + i + "]")) .click(); }
		 * 
		 * if (driver.findElements(CreateSalesOrder.saveChangesPopup).size() > 0) {
		 * javaClickByLocator(CreateSalesOrder.discardBtn); }
		 * 
		 * 
		 * } catch (Exception e) { }
		 */
	}
	
	// Created By: Vishwajeet Shabadi on 11-July-2023
	// Decription :Used to close the multiple tabs for clone orders
	public static void closeMultipleTabsCloneOrders() {
		try {
			List<WebElement> multipleclosebuttons = driver.findElements(By.xpath("//div[@class='tabBar slds-grid']"));
			for (int i = 0; i <= multipleclosebuttons.size(); i++) {
				multipleclosebuttons.get(i).click();
			}
			Thread.sleep(2000);
			if (driver.findElements(CreateSalesOrder.saveChangesPopup).size() > 0) {
				CommonFunctions.javaClickByLocator(CreateSalesOrder.discardBtn);
			}	
			System.out.println("All tabs are closed");
		} catch (Exception e) {
			System.out.println("Tabs are not Open");
		}
	}

	// Created By: Abhishek Palankar on 15-May-2023
	public static void popupVerify(String popup) throws IOException {
		String addr = popup;

		if (driver.findElements(By.xpath("//h2[contains(text(),'" + popup + "')]")).size() != 0
				|| driver.findElements(By.xpath("//h1[contains(text(),'" + popup + "')]")).size() != 0) {

			assertTrue(
					driver.findElements(By.xpath("//h2[contains(text(),'" + popup + "')] ")).size() != 0
							|| driver.findElements(By.xpath("//h1[contains(text(),'" + popup + "')]")).size() != 0,
					"<b>Expected Result: User should be able to open '" + popup
							+ "'</b> Popup  <br /> <b>Actual Result: User is able to open '" + popup + "'</b> Popup ");

			logger.pass("<b>Expected Result: User should be able to open '" + popup
					+ "'</b> Popup <br /> <b>Actual Result: User should be able to open '" + popup + "'</b> Popup ");

		} else {
			screenshot = capture(driver);
			logger.fail("<b>Expected Result: User should be able to open '" + popup
					+ "'</b> Popup. <br /> <b>Actual Result: User is not able to open '" + popup + "'</b>  Popup ",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(
					"<b>Expected Result: '" + popup + "'</b>  Template Popup Should appear. <br /> <b>Actual Result: '"
							+ popup + "'</b>  Popup has not appeared.");
		}
		status = logger.getStatus();

	}

	// Created By: Abhishek Palankar on 15-May-2023
	// Description: Used to verify Toast message confirmation alert.
	// Parameter names that needs to be passed: Expected Toast Message
	public static void verifyToastMessage(String expectedMessage) throws IOException, InterruptedException {

		String actaulFoastMessage = "";
		String actualMessage = "";
		Thread.sleep(1000);
		try {
			if (driver.findElement(Constants.foastMessageClassName).isDisplayed()) {
				screenshot = capture(driver);

				actaulFoastMessage = driver.findElement(Constants.foastMessageClassName).getText();
				if(actaulFoastMessage.contains(expectedMessage)) {
					
				}
				String Updated = actaulFoastMessage.replaceFirst("Success!", "");
				actualMessage = Updated.replace("Close", "");
				actaulFoastMessage = actualMessage.replace("info", "");
				assertTrue(driver.findElement(Constants.foastMessageClassName).isDisplayed(),
						"<b> Expected Result: </b> " + expectedMessage
								+ " message should be displayed. <br /> <b> Actual Result: </b> " + actaulFoastMessage
								+ " message displayed successfully.");
				logger.pass(
						"<b> Expected Result: </b> <b>" + expectedMessage
								+ "</b> message should be displayed. <br /> <b> Actual Result: </b> <b>"
								+ actaulFoastMessage + "</b> message displayed successfully.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

			}
		} catch (Exception e) {
			screenshot = capture(driver);

			logger.fail(
					"<b>Expected Result: '" + expectedMessage + "' <b/>should be displayed <br /> <b>Actual Result: "
							+ actualMessage + "'</b>  message not displayed.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail("<b>Expected Result: <b/>'" + expectedMessage + "' <br /> <b>Actual Result: <b/>"
					+ actualMessage + "'</b>  message not displayed.");

		}

		status = logger.getStatus();

	}

	// Created by and Date: Abhishek palankar on 25-May-2023
	// Description: Used to verify toast message confirmation alert.
	// Parameter names that needs to be passed: Expected Foast Message
	public static void verifyToastMessage(By locator, String toastMessage) throws IOException, InterruptedException {

		// String actaulToastMessage = "";
		String actualMessage = "";

		Thread.sleep(2000);
		try {
			if (driver.findElement(locator).getText().contains(toastMessage)) {
				screenshot = capture(driver);

				logger.pass("<b> Expected Result: </b> <b>" + toastMessage
						+ "</b> Toast: message should be displayed. <br /> <b> Actual Result: </b> <b>" + toastMessage
						+ "</b> Toast message displayed successfully.");
				Reporter.log("PASS:" + toastMessage + " Toast message displayed successfully");
			} else {
				Reporter.log("FAIL:Toast: message is not displayed", true);
				softAssert.fail("<b>Expected Result: <b/>'" + toastMessage + "' <br /> <b>Actual Result: <b/>"
						+ actualMessage + "'</b> message not displayed.");
			}
		} catch (Exception e) {
			screenshot = capture(driver);
			System.out.println(e);
			logger.fail(
					"<b>Expected Result: '" + toastMessage
							+ "' <b/> Toast: message should be displayed <br /> <b>Actual Result: " + actualMessage
							+ "'</b> toast: message not displayed.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail("<b>Expected Result: <b/>'" + toastMessage + "' <br /> <b>Actual Result: <b/>"
					+ actualMessage + "'</b> message not displayed.");

		}

		status = logger.getStatus();

	}

	// Created by and Date: Abhishek Palankar on 25-May-2023
	// Description: Used to generate random integer
	public static int generateRandomInteger() {
		Random random = new Random();
		int randomNum = random.nextInt(10000);

		return randomNum;
	}

	// Created by and Date: Abhishek Palankar on 25-May-2023
	// Decription : Used to scroll down the page
	public static void salesforce_ScrollDown(int seconds) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		for (int second = 0;; second++) {
			if (second >= seconds) { // seconds can be varied according
				break;
			}
			jse.executeScript("window.scrollBy(0,500)", ""); // y value '800' can be altered
		}
	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Used to Validate the Pick List Values
	public static void picklistValidation(String element, List<String> field) throws IOException {
		logger.info("" + element + " Picklist Validation:");
		SoftAssert softAssert = new SoftAssert();
		javaClickByLocator(By.xpath("//span[text()='" + element
				+ "']/ancestor::div[@class='slds-form-element__control']//a|//label[text()='" + element
				+ "']/ancestor::lightning-picklist//button"));
		for (int i = 0; i < field.size(); i++) {

			if (driver.findElements(By.xpath("//a[text() = '" + field.get(i) + "']")).size() != 0) {
				assertTrue(driver.findElements(By.xpath("//a[text() = '" + field.get(i) + "']")).size() != 0,
						"<b>Expected Result:</b>  PickList values  - " + field.get(i)
								+ " values should be displayed <br /> <b>Actual Result:</b> Fields - " + field.get(i)
								+ " values displayed");
				logger.pass("<b>Expected Result:</b>  PickList values  - " + field.get(i)
						+ " values should be displayed <br /> <b>Actual Result:</b> Fields - " + field.get(i)
						+ " values displayed");

			} else {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b>  PickList values  - " + field.get(i)
								+ " values should be displayed <br /> <br /><b>Actual Result:</b> Fields - "
								+ field.get(i) + " values Not displayed",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				softAssert.fail("<b>Expected Result:</b>  PickList values  - " + field.get(i)
						+ " values should be displayed <br /> <br /><b>Actual Result:</b> Fields - " + field.get(i)
						+ " values Not displayed");

			}
		}

		status = logger.getStatus();
	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Decription:Used to Generate the time
	static DateTimeFormatter globalFormat = DateTimeFormatter.ofPattern("M/d/yyyy, hh:mm a z");
	static DateTimeFormatter etFormat = DateTimeFormatter.ofPattern("M/d/yyyy, hh:mm a 'EDT'");
	static DateTimeFormatter pstFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy, hh:mm a 'PST'");

	protected static ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
	static ZoneId etZoneId = ZoneId.of("America/New_York");
	static ZoneId pstZoneId = ZoneId.of("America/Los_Angeles");
	public static ZoneId mdtZoneId = ZoneId.of("America/Denver");

	public static String timeConverter(String Zone) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		ZonedDateTime currentISTime = currentDateTime.atZone(istZoneId); // India Time
		String time = "";

		switch (Zone) {
		case "IST":
			time = (globalFormat.format(currentISTime));
			break;
		case "EDT":
			ZonedDateTime currentETime = currentISTime.withZoneSameInstant(etZoneId); // ET Time
			time = (globalFormat.format(currentETime));
			break;

		case "PST":
			ZonedDateTime currentPSTTime = currentISTime.withZoneSameInstant(pstZoneId); // PST Time
			time = (pstFormat.format(currentPSTTime));
			break;

		case "MDT":
			ZonedDateTime currentMDTTime = currentISTime.withZoneSameInstant(mdtZoneId); // MDT Time
			time = (globalFormat.format(currentMDTTime));
			break;

		}

		return time;

	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Decription:Used to Uplode the file
	public void uploadFile(String file) throws Throwable {
		String path = System.getProperty("user.dir") + "/" + file + "";
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath(
				"//button[@class='slds-button slds-button--neutral attachButton slds-button slds-button-neutral slds-truncate uiButton']"));
		element.sendKeys(path);
	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Decription : Used to Select the Dropdown Value
	public static void select_dropdownValue(String dropDownValue, By locator) throws Throwable {
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOf(driver.findElement((locator))));
		javaClickByLocator(locator);
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//span[@title= '" + dropDownValue + "']"))));
		javaClickByLocator(By.xpath("//span[@title= '" + dropDownValue + "']"));

	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Decription : Used to scroll to perticular element
	public static void salesforce_ScrollToElement(String filterbutton) {
		WebElement xpath = driver.findElement(By.xpath(filterbutton));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", xpath);
	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Decription : Used to click on the tab
	public static void Click_Tab(String button) throws InterruptedException {
		if ((driver.findElements(By.xpath("//button[@title= '" + button + "']")).size() > 0)
				|| driver.findElements(By.xpath("//a[text()= '" + button + "']")).size() > 0) {
			try {
				javaClickByLocator(By.xpath("//button[@title= '" + button + "']"));

			} catch (Exception e) {
				javaClickByLocator(By.xpath("//a[text()='" + button + "']"));

			}
		}
		status = logger.getStatus();
	}

	// Created by and Date: Abhishek Palankar on 26-May-2023
	// Decription :Used to click on the buttons
	public static void Click_button(String button) throws InterruptedException {
		if ((driver.findElements(By.xpath("//button[@title= '" + button + "']")).size() != 0)
				|| driver.findElements(By.xpath("//button[text()= '" + button + "']")).size() != 0) {
			try {
				javaClickByLocator(By.xpath("//button[@title= '" + button + "']"));

			} catch (Exception e) {
				javaClickByLocator(By.xpath("//button[text()='" + button + "']"));

			}
		}
		status = logger.getStatus();
	}

	// Created By: Abhishek Palankar
	// Decription : Used to validate the fields Present or not

	public static void fieldValidation(String type, List<String> fields) throws Throwable {
		List<String> present = new ArrayList<String>();
		List<String> notpresent = new ArrayList<String>();

		for (String fieldName : fields) {
			if (driver.findElements(By.xpath("//*[text()='" + fieldName + "']")).size() != 0) {
				present.add(fieldName);
			} else {
				notpresent.add(fieldName);
			}
		}
		fieldValidation(type, present, notpresent);
		System.out.println("Present: "+present);
		System.out.println("Not present: "+notpresent);
		present.clear();
		notpresent.clear();
	}

	// Created by and Date: Abhishek Palankar on 16-May-2023
	// Decription :Used to print the fields which are present and not present
	public static void fieldValidation(String type, List<String> present, List<String> notPresent) throws Throwable {
		String preField = "";
		String nonPreField = "";
		if (!present.isEmpty()) {

			for (String field : present) {
				preField += field + ", ";
			}
			logger.pass("<b>Expected Result:</b> <b>" + type + "</b> - " + preField.substring(0, preField.length() - 2)
					+ " Fields should be displayed <br /> <b>Actual Result: " + type + "</b> - "
					+ preField.substring(0, preField.length() - 2) + " Fields displayed");
			assertTrue(!present.isEmpty(),
					"<b>Expected Result:</b> <b>" + type + "</b> - " + preField.substring(0, preField.length() - 2)
							+ " should be displayed <br /> <b>Actual Result: " + type + "</b> - "
							+ preField.substring(0, preField.length() - 2) + " Fields displayed");
		}

		if (!notPresent.isEmpty()) {

			for (String field : notPresent) {
				nonPreField += field + ", ";
			}
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b> <b>" + type + "</b> - "
							+ nonPreField.substring(0, nonPreField.length() - 2)
							+ "Fields should be displayed <br /> <b>Actual Result: " + type + "</b> - "
							+ nonPreField.substring(0, nonPreField.length() - 2) + " Fields Not displayed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

			softAssert.fail("<b>Expected Result:</b> <b>" + type + "</b> - "
					+ nonPreField.substring(0, nonPreField.length() - 2)
					+ " should be displayed <br /> <b>Actual Result:</b> " + type + " - "
					+ nonPreField.substring(0, nonPreField.length() - 2) + " Fields Not displayed");

		}

		status = logger.getStatus();
	}

	// Created by and Date: Abhishek Palankar on 16-May-2023
	// Used to remove the specific tab
	public static void closeSpecificTab(String TabName) {
		javaClickByLocator(By.xpath("//span[text() = 'Close " + TabName + "']/parent::button"));
	}

	// Created by: Abhishek Palankar on 16-May-2023
	// Used to generate the date
	public static String data(int data) {
		Date dt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, data);
		dt = calendar.getTime();
		String departdate = new SimpleDateFormat("yyyy-MM-dd").format(dt);

		return departdate;

	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void fillField(String Xpath, String Value) {
		// used to enter field based on xpath and given value
		if (Value == null) {
			Value = "   ";
		}
		int attempts = 0;
		while (attempts < 10) {
			try {
				driver.findElement(By.xpath(Xpath)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(Xpath)).sendKeys(Value);
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void getListView(String listviewValue) throws Throwable {

		if (driver.findElements(By.xpath("//button[contains(@title,'Select a List View:')]")).size() != 0) {

			javaClickByLocator(By.xpath("//button[contains(@title,'Select a List View:')]"));
			if (driver.findElements(By.xpath(
					"//input[@placeholder='Search lists...']"))
					.size() != 0) {
				fillField(
						"//input[@placeholder='Search lists...']",
						listviewValue);
			}
			try {
				javaClickByLocator(By.xpath("(//span[text()='" + listviewValue + "']/ancestor::a)[1]"));
			} catch (Exception e) {
			}

			Thread.sleep(2000);
			try {
				javaClickByLocator(By.xpath("(//mark[text()='" + listviewValue + "']/ancestor::a)[1]"));
			} catch (Exception e) {
			}
			Thread.sleep(2000);
		} else {

			logger.info(" List view is not selected");
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	// Decription :Used to click on the buttons
	public void button_Click(String button) throws InterruptedException {
		if ((driver.findElements(By.xpath("//span[text()='" + button + "']/parent::button")).size() != 0)) {
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//span[text()='" + button + "']/parent::button"));
		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static String getPlusDays(ZoneId zoneID, long plusDays, String dateFormat) {
		LocalDateTime localDateTime = LocalDateTime.now(zoneID).plusDays(plusDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return localDateTime.format(formatter);
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static String getMinusDays(ZoneId zoneID, long minusDays, String dateFormat) {
		LocalDateTime localDateTime = LocalDateTime.now(zoneID).minusDays(minusDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return localDateTime.format(formatter);
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void columnNameValidation(List<String> columnNames) throws Throwable {
		List<String> present = new ArrayList<String>();
		List<String> notPresent = new ArrayList<String>();

		for (String columnName : columnNames) {
			if (driver.findElements(By.xpath("//div[@class='slds-cell-fixed']//span[@title='" + columnName + "']"))
					.size() > 0) {
				present.add(columnName);
			} else {
				notPresent.add(columnName);
			}
		}
		String preColumn = "";
		String nonPreColumn = "";

		if (!present.isEmpty()) {

			for (String column : present) {
				preColumn += column + ", ";
			}
			assertTrue(!present.isEmpty(),
					"<b>Expected Result:</b> Column name's - <b>'" + preColumn.substring(0, preColumn.length() - 2)
							+ "'</b> should be displayed <br /> <b>Actual Result:</b> Column name's - <b>'"
							+ preColumn.substring(0, preColumn.length() - 2) + "'</b> are displayed successfully");
			logger.pass("<b>Expected Result:</b> Column name's - <b>'" + preColumn.substring(0, preColumn.length() - 2)
					+ "'</b> should be displayed <br /> <b>Actual Result:</b> Column name's - <b>'"
					+ preColumn.substring(0, preColumn.length() - 2) + "'</b> are displayed successfully");

		}

		if (!notPresent.isEmpty()) {

			for (String column : notPresent) {
				nonPreColumn += column + ", ";
			}
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b> Column name's - <b>'"
							+ nonPreColumn.substring(0, nonPreColumn.length() - 2)
							+ "</b> should be displayed <br /> <b>Actual Result:</b>  Column name's - <b>'"
							+ nonPreColumn.substring(0, preColumn.length() - 2) + "' </b> are not displayed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail("<b>Expected Result:</b> Column name's - <b>'"
					+ nonPreColumn.substring(0, nonPreColumn.length() - 2)
					+ "</b> should be displayed <br /> <b>Actual Result:</b>  Column name's - <b>'"
					+ nonPreColumn.substring(0, preColumn.length() - 2) + "' </b> are not displayed");
			/*
			 * assertFalse(notPresent.isEmpty(),
			 * "<b>Expected Result:</b> Column name's - <b>'" + nonPreColumn.substring(0,
			 * nonPreColumn.length() - 2) +
			 * "</b> should be displayed <br /> <b>Actual Result:</b>  Column name's - <b>'"
			 * + nonPreColumn.substring(0, preColumn.length() - 2) +
			 * "' </b> are not displayed");
			 */
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void detailsDataValidation(List<String> detailType, List<String> detailValues) throws Throwable {
		List<String> present = new ArrayList<String>();
		List<String> notPresent = new ArrayList<String>();

		for (int i = 0; i < detailType.size(); i++) {

			if (driver.findElement(By.xpath("//span[text()='" + detailType.get(i)
					+ "']/ancestor::div[contains(@class,'slds-form-element slds-hint-parent test-id__output-root slds-form-element_edit slds-form-element_readonly slds-form-element')]//lightning-formatted-text"))
					.getText().contains(detailValues.get(i))) {

				present.add(detailType.get(i) + ": " + detailValues.get(i));
			} else {
				notPresent.add(detailType.get(i) + ": Detail Not Found");
			}
		}

		String prevalue = "";
		String nonPrevalue = "";

		if (!present.isEmpty()) {

			for (String value : present) {
				prevalue += value + ", ";
			}
			assertTrue(!present.isEmpty(), "<b>Expected Result:</b> Newly created object details - <b>'"
					+ prevalue.substring(0, prevalue.length() - 3)
					+ "'</b> should be displayed <br /> <b>Actual Result:</b> Newly created object details - <b>'"
					+ prevalue.substring(0, prevalue.length() - 3) + "'</b> are displayed successfully");
			logger.pass("<b>Expected Result:</b> Newly created object details - <b>'"
					+ prevalue.substring(0, prevalue.length() - 3)
					+ "'</b> should be displayed <br /> <b>Actual Result:</b> Newly created object details - <b>'"
					+ prevalue.substring(0, prevalue.length() - 3) + "'</b> are displayed successfully");

		}

		if (!notPresent.isEmpty()) {

			for (String value : notPresent) {
				nonPrevalue += value + ", ";
			}
			screenshot = capture(driver);

			logger.fail("<b>Expected Result:</b> Newly created object details - <b>'"
					+ nonPrevalue.substring(0, nonPrevalue.length() - 2)
					+ "</b> should be displayed <br /> <b>Actual Result:</b>  Newly created object details - <b>'"
					+ nonPrevalue.substring(0, nonPrevalue.length() - 2) + "' </b> are not displayed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail("<b>Expected Result:</b> Newly created object details - <b>'"
					+ nonPrevalue.substring(0, nonPrevalue.length() - 2)
					+ "</b> should be displayed <br /> <b>Actual Result:</b>  Newly created object details - <b>'"
					+ nonPrevalue.substring(0, nonPrevalue.length() - 2) + "' </b> are not displayed");
			/*
			 * assertFalse(notPresent.isEmpty(),
			 * "<b>Expected Result:</b> Newly created object details - <b>'" +
			 * nonPrevalue.substring(0, nonPrevalue.length() - 2) +
			 * "</b> should be displayed <br /> <b>Actual Result:</b>  Newly created object details - <b>'"
			 * + nonPrevalue.substring(0, nonPrevalue.length() - 2) +
			 * "' </b> are not displayed");
			 */
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void clickOnEditIcon(String editButtonFieldName) {

		javaClickByLocator(By.xpath("//button[@title='" + editButtonFieldName + "']"));

	}

	// Created by: Abhishek Palankar on 16-May-2023
	// Decription : Used to scroll Up the page
	public static void salesforce_ScrollUp(int seconds) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		for (int second = 0;; second++) {
			if (second >= seconds) { // seconds can be varied according
				break;
			}
			jse.executeScript("window.scrollBy(0,-250)", "");
		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	// Decription : Used to validate the fields is editable or not
	public static void editFieldValidation(List<String> fields, String fieldss) throws Throwable {
		List<String> present = new ArrayList<String>();
		List<String> notpresent = new ArrayList<String>();

		for (String fieldName : fields) {

			if (driver.findElements(By.xpath("//button[@title='Edit " + fieldName + "']")).size() != 0
					|| driver.findElements(By.xpath("//button[@title='Change " + fieldName + "']")).size() != 0) {
				present.add(fieldName);

			} else {
				notpresent.add(fieldName);

			}
		}
		editFieldValidation(present, notpresent, fieldss);
		present.clear();
		notpresent.clear();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	// Decription :Used to print the fields which are present and not present
	public static void editFieldValidation(List<String> present, List<String> notPresent, String fieldss)
			throws Throwable {
		String preField = "";
		String nonPreField = "";
		int size2 = notPresent.size();

		if (!present.isEmpty()) {
			// logger.info("Fields Present are : ");
			for (String field : present) {
				preField += field + ", ";
			}
			assertTrue(!present.isEmpty(),
					"<b>Expected Result:</b> <b>" + fieldss + "</b>- " + preField.substring(0, preField.length() - 2)
							+ " should be Editable <br /> <b>Actual Result:</b> <b>" + fieldss + "</b>- "
							+ preField.substring(0, preField.length() - 2) + " Fields are Editable");
			logger.pass(
					"<b>Expected Result:</b> <b>" + fieldss + "</b>- " + preField.substring(0, preField.length() - 2)
							+ " should be Editable <br /> <b>Actual Result:</b> <b>" + fieldss + "</b>- "
							+ preField.substring(0, preField.length() - 2) + " Fields are Editable");
		}

		if (!notPresent.isEmpty() && size2 > 1) {
			// logger.info("Missing Fields are : ");
			for (String field : notPresent) {
				nonPreField += field + ", ";
			}
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b><b>" + fieldss + "</b>-  "
							+ nonPreField.substring(0, nonPreField.length() - 2)
							+ " should be Editable <br /> <b>Actual Result:</b><b>" + fieldss + "</b> - "
							+ nonPreField.substring(0, nonPreField.length() - 2) + " Fields are Not Editable",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail("<b>Expected Result:</b><b>" + fieldss + "</b>-  "
					+ nonPreField.substring(0, nonPreField.length() - 2)
					+ " should be Editable <br /> <b>Actual Result:</b><b>" + fieldss + "</b> - "
					+ nonPreField.substring(0, nonPreField.length() - 2) + " Fields are Not Editable");

		}

		if (!notPresent.isEmpty() && size2 == 1) {
			// logger.info("Missing Fields are : ");
			for (String field : notPresent) {
				nonPreField += field + ", ";
			}
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b><b>" + fieldss + "</b>-  "
							+ nonPreField.substring(0, nonPreField.length() - 2)
							+ " should be Editable <br /> <b>Actual Result:</b><b>" + fieldss + "</b> - "
							+ nonPreField.substring(0, nonPreField.length() - 2) + " Field is Not Editable",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail("<b>Expected Result:</b><b>" + fieldss + "</b>-  "
					+ nonPreField.substring(0, nonPreField.length() - 2)
					+ " should be Editable <br /> <b>Actual Result:</b><b>" + fieldss + "</b> - "
					+ nonPreField.substring(0, nonPreField.length() - 2) + " Field is Not Editable");

		}

		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void taskDetailsValidation(List<String> detailType, List<String> detailValues) throws Throwable {
		List<String> present = new ArrayList<String>();
		List<String> notPresent = new ArrayList<String>();

		for (int i = 0; i < detailType.size(); i++) {

			if (driver.findElement(By.xpath("//span[text()='" + detailType.get(i)
					+ "']/ancestor::div[@class='slds-form-element slds-form-element_readonly slds-form-element_edit slds-grow slds-hint-parent override--slds-form-element']//span[@class='test-id__field-value slds-form-element__static slds-grow ']/span"))
					.getText().contains(detailValues.get(i))) {

				present.add(detailType.get(i) + ": " + detailValues.get(i));
			} else {
				notPresent.add(detailType.get(i) + ": Detail Not Found");
			}
		}

		String prevalue = "";
		String nonPrevalue = "";

		if (!present.isEmpty()) {

			for (String value : present) {
				prevalue += value + ", ";
			}
			assertTrue(!present.isEmpty(),
					"<b>Expected Result:</b> Task details - <b>'" + prevalue.substring(0, prevalue.length() - 2)
							+ "'</b> should be displayed <br /> <b>Actual Result:</b> Task details - <b>'"
							+ prevalue.substring(0, prevalue.length() - 2) + "'</b> are displayed successfully");
			logger.pass("<b>Expected Result:</b> Task details - <b>'" + prevalue.substring(0, prevalue.length() - 2)
					+ "'</b> should be displayed <br /> <b>Actual Result:</b> Task details - <b>'"
					+ prevalue.substring(0, prevalue.length() - 2) + "'</b> are displayed successfully");

		}

		if (!notPresent.isEmpty()) {

			for (String value : notPresent) {
				nonPrevalue += value + ", ";
			}
			screenshot = capture(driver);

			logger.fail(
					"<b>Expected Result:</b> Task details - <b>'" + nonPrevalue.substring(0, nonPrevalue.length() - 2)
							+ "</b> should be displayed <br /> <b>Actual Result:</b>  Task details - <b>'"
							+ nonPrevalue.substring(0, nonPrevalue.length() - 2) + "' </b> are not displayed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			assertFalse(notPresent.isEmpty(),
					"<b>Expected Result:</b> Task details - <b>'" + nonPrevalue.substring(0, nonPrevalue.length() - 2)
							+ "</b> should be displayed <br /> <b>Actual Result:</b>  Task details - <b>'"
							+ nonPrevalue.substring(0, nonPrevalue.length() - 2) + "' </b> are not displayed");
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static String createCaseNumber(String NewCaseType, List<String> values) throws InterruptedException {
		String caseNumber = "";
		javaClickByLocator(By.xpath("//li[@class='slds-button slds-button--neutral slds-button_neutral']//a[@title='New']"));
		Thread.sleep(2000);
		switch (NewCaseType) {
		case "Customer Service Ticket":
			String accountname = values.get(0);
			String callType = values.get(1);
			String CSTProblemReported = values.get(2);
			String detailDescription = values.get(3);
			driver.findElement(By.xpath(
					"//h2[text()='New Case']/ancestor::div[@class='inlinePanel oneRecordActionWrapper']//span[text() = '"
							+ NewCaseType + "']"))
					.click();
			Thread.sleep(2000);
			javaClickByLocator(By.xpath(
					"//button[@class='slds-button slds-button_neutral slds-button slds-button_brand uiButton']"));
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//label[text()='Account Name']/ancestor::records-record-layout-item[@field-label='Account Name']//input"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//label[text()='Account Name']/ancestor::records-record-layout-item[@field-label='Account Name']//input"))
					.sendKeys(accountname);
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']"));
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + accountname + "']")));
			driver.findElement(By.xpath("//a[@title='" + accountname + "']")).click();

			Thread.sleep(2000);
			javaClickByLocator(By.xpath(
					"//label[text()='Priority']/ancestor::records-record-layout-item[@field-label='Priority']//button"));

			javaClickByLocator(By.xpath("//span[@title='High']/ancestor::lightning-base-combobox-item"));

			javaClickByLocator(By.xpath(
					"//label[contains(text(),'Call Type')]/ancestor::records-record-layout-item[@field-label='Call Type']//button"));

			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));

			driver.findElement(By.xpath("//textarea[@class='slds-textarea']")).click();

			driver.findElement(By.xpath("//textarea[@class='slds-textarea']")).sendKeys(detailDescription);

			javaClickByLocator(By.xpath(
					"//label[contains(text(),'CST Problem Reported')]/ancestor::records-record-layout-item[@field-label='CST Problem Reported']//button"));

			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + CSTProblemReported + "']"));

			javaClickByLocator(By.xpath("//button[@name='SaveEdit']"));
			Thread.sleep(8000);
			caseNumber = driver.findElement(By.xpath(
					"//span[text()='Case Number']/ancestor::records-record-layout-item[@field-label='Case Number']//lightning-formatted-text"))
					.getText();
			break;
		case "Email":

			break;
		case "Email Order":

			break;
		case "Equipment Confirmation Service Ticket":

			break;
		case "Equipment Service Ticket":
			String accountName = values.get(0);
			String equipmentType = values.get(1);
			String estProblemReported = values.get(2);
			String detailedDescription = values.get(3);
			String equipmentTypeDetail = values.get(4);
			driver.findElement(By.xpath(
					"//h2[text()='New Case']/ancestor::div[@class='inlinePanel oneRecordActionWrapper']//span[text() = '"
							+ NewCaseType + "']"))
					.click();
			driver.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//label[text()='Account Name']/ancestor::records-record-layout-item[@field-label='Account Name']//input"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//label[text()='Account Name']/ancestor::records-record-layout-item[@field-label='Account Name']//input"))
					.sendKeys(accountName);
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']"));
			new WebDriverWait(driver, explicitWaitTimeOut).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + accountName + "']")));

			driver.findElement(By.xpath("//a[@title='" + accountName + "']")).click();
			Thread.sleep(2000);
			javaClickByLocator(By.xpath(
					"//label[contains(text(),'Equipment Type')]/ancestor::records-record-layout-item[@field-label='Equipment Type']//button"));

			Thread.sleep(2000);
			driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + equipmentType + "']")).click();
			javaClickByLocator(By.xpath(
					"//label[contains(text(),'Equipment Type Detail')]/ancestor::records-record-layout-item[@field-label='Equipment Type Detail']//button"));

			Thread.sleep(1000);
			// driver.findElement(By.xpath("(//a[@title='" + equipmentTypeDetail +
			// "'])[1]")).click();
		    scrollToElement(By.xpath("//lightning-base-combobox-item[@data-value='" + equipmentTypeDetail +"']"));
			driver.findElement(
					By.xpath("//lightning-base-combobox-item[@data-value='" + equipmentTypeDetail + "']")).click();
			javaClickByLocator(By.xpath(
					"//label[contains(text(),'EST Problem Reported')]/ancestor::records-record-layout-item[@field-label='EST Problem Reported']//button[contains(@aria-label,'EST Problem Reported')]"));
			 scrollToElement(By.xpath("//lightning-base-combobox-item[@data-value='" + estProblemReported + "']"));
				javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + estProblemReported + "']"));
			driver.findElement(By.xpath(
					"//label[contains(text(),'Detailed Description')]/ancestor::records-record-layout-item[@field-label='Detailed Description']//textarea"))
					.click();
			driver.findElement(By.xpath(
					"//label[contains(text(),'Detailed Description')]/ancestor::records-record-layout-item[@field-label='Detailed Description']//textarea"))
					.sendKeys(detailedDescription);
			driver.findElement(By.xpath(
					"//label[contains(text(),'Detailed Description')]/ancestor::records-record-layout-item[@field-label='Detailed Description']//textarea"))
					.sendKeys(Keys.TAB);
			javaClickByLocator(By.xpath("//button[@name='SaveEdit']"));
			Thread.sleep(8000);
			caseNumber = driver.findElement(By.xpath(
					"//span[text()='Case Number']/ancestor::records-record-layout-item[@field-label='Case Number']//lightning-formatted-text"))
					.getText();
			break;
		case "Outbound":
			String outBoundaccountName = values.get(0);
			driver.findElement(By.xpath(
					"//h2[text()='New Case']/ancestor::div[@class='inlinePanel oneRecordActionWrapper']//span[text() = '"
							+ NewCaseType + "']"))
					.click();
			Thread.sleep(2000);
			javaClickByLocator(By.xpath(
					"//button[@class='slds-button slds-button--neutral slds-button slds-button_brand uiButton']"));
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//label[text()='Account Name']/ancestor::records-record-layout-item[@field-label='Account Name']//input"))
					.click();
			driver.findElement(By.xpath(
					"//label[text()='Account Name']/ancestor::records-record-layout-item[@field-label='Account Name']//input"))
					.sendKeys(outBoundaccountName);
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']"));
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[@title='" + outBoundaccountName + "']")));

			driver.findElement(By.xpath("//a[@title='" + outBoundaccountName + "']")).click();
			Thread.sleep(2000);
			javaClickByLocator(By.xpath("//button[@name='SaveEdit']"));
			Thread.sleep(8000);
			caseNumber = driver.findElement(By.xpath(
					"//span[text()='Case Number']/ancestor::records-record-layout-item[@field-label='Case Number']//lightning-formatted-text"))
					.getText();

			break;
		case "Placement":

			break;
		case "Removal":

			break;
		default:
			break;

		}

		return caseNumber;
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void nonEditFieldValidation(List<String> fields, String fieldss) throws Throwable {

		List<String> present = new ArrayList<String>();
		List<String> notpresent = new ArrayList<String>();

		for (String fieldName : fields) {

			if (driver.findElements(By.xpath("//*[text()='" + fieldName
					+ "']/ancestor::div[contains(@class,'slds-form-element slds-hint-parent test-id__output-root slds-form-element_readonly')]//span[contains(@class,'is-read-only')]"))
					.size() != 0
					|| driver.findElements(By.xpath("//span[text()='" + fieldName
							+ "']/ancestor::div[@class='slds-col slds-grid slds-has-flexi-truncate  full forcePageBlockItem forcePageBlockItemView']//span[contains(@class,'is-read-only')]"))
							.size() != 0) {
				present.add(fieldName);

			} else {
				notpresent.add(fieldName);

			}

		}

		noneditFieldValidation(present, notpresent, fieldss);
		present.clear();
		notpresent.clear();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void noneditFieldValidation(List<String> present, List<String> notPresent, String fieldss)
			throws Throwable {
		String preField = "";
		String nonPreField = "";
		if (!present.isEmpty()) {
			// logger.info("Fields Present are : ");
			for (String field : present) {
				preField += field + ", ";
			}
			assertTrue(!present.isEmpty(),
					"<b>Expected Result:</b> " + fieldss + "- " + preField.substring(0, preField.length() - 2)
							+ " should not be Editable <br /> <b>Actual Result:</b> Fields - "
							+ preField.substring(0, preField.length() - 2) + " Fields are not Editable");
			logger.pass("<b>Expected Result:</b> " + fieldss + "- " + preField.substring(0, preField.length() - 2)
					+ " should not be Editable <br /> <b>Actual Result:</b> Fields - "
					+ preField.substring(0, preField.length() - 2) + " Fields are not Editable");

		}

		if (!notPresent.isEmpty()) {
			// logger.info("Missing Fields are : ");
			for (String field : notPresent) {
				nonPreField += field + ", ";
			}
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b>" + fieldss + "-  " + nonPreField.substring(0, nonPreField.length() - 2)
							+ " should not be Editable <br /> <b>Actual Result:</b> Fields - "
							+ nonPreField.substring(0, nonPreField.length() - 2) + " Fields are Editable",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			softAssert.fail(
					"<b>Expected Result:</b>" + fieldss + "-  " + nonPreField.substring(0, nonPreField.length() - 2)
							+ " should not be Editable <br /> <b>Actual Result:</b> Fields - "
							+ nonPreField.substring(0, nonPreField.length() - 2) + " Fields are Editable");
		}

		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static boolean comp_Dates(String dataFormat, String fromDate, String toDate, String actualDate,
			String comparisionType) {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(dataFormat);

			String FromDate = fromDate;
			String ToDate = toDate;

			java.util.Date Fdate = fmt.parse(FromDate);

			java.util.Date Tdate = fmt.parse(ToDate);

			java.util.Date ActualDate = fmt.parse(actualDate);

			switch (comparisionType) {
			case "Date between From and To date":
				if (ActualDate.compareTo(Fdate) >= 0 && ActualDate.compareTo(Tdate) <= 0) {
					return true;
				}
				break;

			}

		} catch (Exception ex) {

		}
		return false;
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void isSelectedUsingAttribute(By locator, String expectedResult, String attributeType,
			String expectedMessage, String actualMessage) throws Exception {

		try {
			WebElement ele = null;

			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
			String isSelected = driver.findElement(locator).getAttribute(attributeType);
			if (isSelected.equals("true")) {

				logger.pass("<b>Expected Result:</b> The '" + expectedResult + "' " + expectedMessage
						+ " should be selected. <br /> <b>Actual Result:</b> The '" + expectedResult + "' "
						+ actualMessage + " is selected. <br />");
				assertTrue(isSelected.equals("true"),
						"<b>Expected Result:</b> The '" + expectedResult + "' " + expectedMessage
								+ " should be selected. <br /> <b>Actual Result:</b> The '" + expectedResult + "' "
								+ actualMessage + " is selected. <br />");

			} else {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b> The '" + expectedResult + "' " + expectedMessage
								+ " should be selected. <br /> <b>Actual Result:</b> The '" + expectedResult + "' "
								+ actualMessage + " is not selected. <br />",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				Assert.fail("<b>Expected Result:</b> The '" + expectedResult + "' " + expectedMessage
						+ " should be selected. <br /> <b>Actual Result:</b> The '" + expectedResult + "' "
						+ actualMessage + " is not selected. <br />");
			}
		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail(expectedMessage + " element not found",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(expectedMessage + "element not found");
		}
		status = logger.getStatus();
	}

	/**
	 * Asserts that the two strings are equal.
	 * 
	 * @param Actual            the actual value
	 * @param expected          the expected value
	 * @param Message           the message printed with the fail or pass assertions
	 * @param hardAssertionFlag flag to indicate hard assertion
	 * @throws IOException
	 */
	// @Step("Assertion point")
	// Created by: Abhishek Palankar on 16-May-2023
	public static void Assert(String Actual, String Expected, String ExpectedMessage, String ActualMessage,
			boolean hardAssertionFlag) throws IOException {
		Actual = Actual.toString().toLowerCase();
		Expected = Expected.toString().toLowerCase();

		if (Actual.trim().equals(Expected.trim())) {
			assertTrue(Actual.trim().equals(Expected.trim()),
					"<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
							+ "</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "'.");
			logger.pass(
					"<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
							+ "</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "'.");

		}

		else {
			screenshot = capture(driver);

			logger.fail("<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
					+ ".</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "' " + "'.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

			softAssert.fail("<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
					+ ".</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "' " + "'.");
			// Reporter.log("<font color=\"red\">FAILED</font> : "+ExpectedMessage+";
			// Expected was '"+Expected+"', Actual is '"+Actual+"'", true);
			// Stopping the execution if hardAssertionFlag is true
			if (hardAssertionFlag) {
				assertEquals(Actual, Expected);
			}
		}

	}

	/**
	 * Asserts that the actual string contains the expected string.
	 * 
	 * @param Actual            the actual value
	 * @param expected          the expected value
	 * @param Message           the assertion error message
	 * @param hardAssertionFlag flag to indicate hard assertion
	 * @param Scenario          the given scenario
	 * @throws IOException
	 */
	// @Step("Assertion point")
	// Created by: Abhishek Palankar on 16-May-2023
	public static void AssertContains(String Actual, String Expected, String ExpectedMessage, String ActualMessage,
			boolean hardAssertionFlag) throws IOException {

		Actual = Actual.toString().toLowerCase();
		Expected = Expected.toString().toLowerCase();

		if (Actual.trim().contains(Expected.trim())) {
			assertTrue(Actual.trim().contains(Expected.trim()),
					"<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
							+ "</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "' "
							+ "'.");
			logger.pass("<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
					+ "</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "' " + "'.");

		} else {
			screenshot = capture(driver);

			logger.fail("<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
					+ "</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "' " + "'.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

			softAssert.fail("<b> Expected Result: </b> " + ExpectedMessage + " " + "<b> '" + Expected + "' "
					+ "</b><br /> <b> Actual Result: </b> " + ActualMessage + " " + "<b> '" + Actual + "' " + "'.");
			// Reporter.log("<font color=\"red\">FAILED</font> : "+Message+"; Expected was
			// '"+Expected+"', Actual is '"+Actual+"'", true);
			// Stopping the execution if hardAssertionFlag is true
			if (hardAssertionFlag) {
				assertEquals(Actual, Expected);
			}
		}

	}

	/**
	 * Returns whether or not the element is displayed
	 * 
	 * @param XPath is the data which contains the locating mechanism
	 * @return whether the element is displayed
	 * @throws IOException
	 * @throws Exception   throws an exception if the selector type is not available
	 *                     or if the element no longer appears on the window and
	 *                     FileNotFoundException
	 */
	// Created by: Abhishek Palankar on 16-May-2023
	public static void isDisplayed(By locator, String ElementName) throws IOException {
		int attempts = 0;

		while (attempts < 4) {
			try {
				WebElement ele = null;

				ele = new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.presenceOfElementLocated(locator));

				if (ele.isDisplayed()) {
					assertTrue(ele.isDisplayed(),
							"<b>Expected Result:</b> The <b>'" + ElementName
									+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
									+ "'</b> is displayed. <br />");
					logger.pass("<b>Expected Result:</b> The <b>'" + ElementName
							+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
							+ "'</b> is displayed. <br />");

					break;
				}
			} catch (Exception e) {

			}

			attempts++;
			if (attempts >= 4) {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b> The <b>'" + ElementName
								+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
								+ "'</b> is not displayed. <br />",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				Assert.fail("<b>Expected Result:</b> The <b>'" + ElementName
						+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
						+ "'</b> is not displayed. <br />");

			}

		}

	}

	/**
	 * increase or decrease the date by a specified number of days, months or years
	 *
	 * @param date            the date in the 'MM/dd/yyyy' format
	 * @param yearMonthDay    'day', 'month' or 'year'
	 * @param numberToincDesc the specified number
	 * @return the increased or the decreased date
	 * @throws Exception
	 */
	// Created by: Abhishek Palankar on 16-May-2023
	public static String increaseOrDecreaseDate(String date, String yearMonthDay, int numberToincDesc)
			throws Exception { // pass the date in 'MM/dd/yyyy' format

		yearMonthDay = yearMonthDay.toLowerCase();
		String newDate = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(date));

		switch (yearMonthDay) {
		case "year":
			cal.add(cal.YEAR, numberToincDesc);
			newDate = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
			break;

		case "month":
			cal.add(cal.MONTH, numberToincDesc);
			newDate = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
			break;

		case "day":
			cal.add(cal.DAY_OF_MONTH, numberToincDesc);
			newDate = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
			break;

		}

		return newDate;
	}

	/**
	 * Returns List of elements for the defined XPath
	 *
	 * @param XPath is the data which contains the locating mechanism
	 * @return The List of elements is displayed
	 */
	// Created by: Abhishek Palankar on 16-May-2023
	public static List<String> getListElements(String Xpath) {

		List<WebElement> webElements = driver.findElements(By.xpath(Xpath));
		// create an empty list in which the label texts will be stored
		List<String> labelsList = new ArrayList<>();
		// iterate through all the webElements
		for (WebElement webElement : webElements) {
			// add each webElements label to the labelsList
			labelsList.add(webElement.getText());
		}
		// return all the label texts that are visible in the dropdown
		return labelsList;
	}

	// Created by: Abhishek Palankar on 16-May-2023
	// Modified By - Niranjan H on 01-03-24
	public static boolean readMetaData(String className, String jsonFileName, String suiteName) throws IOException {
		// boolean flag = false;
		BufferedReader bufferedReader;
		if(userName.contains(Username_Leonardo)) 
		{	File inputFile = new File("G://opt//caf//json//CONA//ICEPL11//Regression Suite "+ suiteName + "//Data_Input_JSON.json");
			 bufferedReader = new BufferedReader(new FileReader(inputFile));
			 System.out.println("Executing over Leonardo");
		}else {
			String inputFile = System.getProperty("user.dir") + "//Test_Data//" + jsonFileName + ".json";
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			System.out.println("Executing Locally");
		}	
//		String inputFile = System.getProperty("user.dir") + "//Test_Data//" + jsonFileName + ".json";
//		File inputFile = new File("G://opt//caf//json//CONA//ICEPL11//Regression Suite "+ suiteName + "//Data_Input_JSON.json");
//		BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));	
		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		Map<String, Integer> meta = gson.fromJson(bufferedReader, Map.class);
		for (Map.Entry<?, ?> entry : meta.entrySet()) {
			if (entry.getKey().toString().contentEquals(className)
					& entry.getValue().toString().contentEquals("true")) {
				// flag = (String) (entry.getValue());
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns List of elements for the defined XPath
	 *
	 * @param XPath is the data which contains the locating mechanism
	 * @return The List of elements is displayed
	 */
	// Created by: Abhishek Palankar on 16-May-2023
	public static List<String> getListElementsListTitle(String Xpath) {

		List<WebElement> webElements = driver.findElements(By.xpath(Xpath));
		// create an empty list in which the label texts will be stored
		List<String> labelsList = new ArrayList<>();
		// iterate through all the webElements
		for (WebElement webElement : webElements) {
			// add each webElements label to the labelsList
			labelsList.add(webElement.getAttribute("title"));
		}
		// return all the label texts that are visible in the dropdown
		return labelsList;
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public void accountSelectRecordType(String record) throws Throwable {
		if (driver
				.findElements(By.xpath(
						"//span[@class='slds-form-element__label topdown-radio--label' and text()='" + record + "']"))
				.size() != 0) {
			javaClickByLocator(By.xpath(
					"//span[@class='slds-form-element__label topdown-radio--label' and text()='" + record + "']"));
			button_Click("Next");
			logger.pass("<b> Expected Result:</b>  Record type  <b>" + record
					+ "<b> Should be displayed  <br /> <b> Actual Result: </b> Record type  <b>" + record
					+ "</b> is  displayed ");
		} else {
			screenshot = capture(driver);
			logger.fail(
					"<b> Expected Result:</b>  Record type  <b>" + record
							+ "</b> Should be displayed  <br /> <b> Actual Result: </b> Record type  " + record
							+ " is not  displayed ",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail("<b> Expected Result:</b>  Record type  <b>" + record
					+ "</b> Should be displayed  <br /> <b> Actual Result: </b> Record type  <b>" + record
					+ "</b> is not  displayed ");

		}

	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static String dateToday() {
		SimpleDateFormat now = new SimpleDateFormat("MM/dd/yyyy");
		Date Date = new Date();

		String date = (now.format(Date)).toString();
		return date;
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void subTabValidation(String subTab) throws Exception {
		String actualSubTab = "";
		try {

			Thread.sleep(3000);
			if (driver.findElement(By.xpath(
					"//div[@class='tabBar slds-grid slds-tabs--default slds-sub-tabs']//a[@title='" + subTab + "']"))
					.isDisplayed()) {
				actualSubTab = driver.findElement(
						By.xpath("//div[@class='tabBar slds-grid slds-tabs--default slds-sub-tabs']//a[@title='"
								+ subTab + "']"))
						.getText();
				logger.pass("<b>Expected Result: '" + subTab + "'</b> "
						+ "sub tab should be displayed. <br /> <b>Actual Result: '" + actualSubTab
						+ "'</b> sub tab is displayed.");
				assertTrue(
						driver.findElement(
								By.xpath("//div[@class='tabBar slds-grid slds-tabs--default slds-sub-tabs']//a[@title='"
										+ subTab + "']"))
								.isDisplayed(),
						"<b>Expected Result: '" + subTab + "'</b> "
								+ "sub tab should be displayed. <br /> <b>Actual Result: '" + actualSubTab
								+ "'</b> sub tab is displayed.");

			}
		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result: '" + subTab + "'</b> "
							+ "sub tab should be displayed. <br /> <b>Actual Result: '" + actualSubTab
							+ "'</b> sub tab is not displayed.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail("<b>Expected Result: '" + subTab + "'</b> "
					+ "sub tab should be displayed. <br /> <b>Actual Result: '" + actualSubTab
					+ "'</b> sub tab is not displayed.");
		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void FieldsAndDataValidation(String fieldType, String parentClassName, List<String> fieldName)
			throws Throwable {
		List<String> present = new ArrayList<String>();
		List<String> notPresent = new ArrayList<String>();

		for (String field : fieldName) {

			if (driver
					.findElements(
							By.xpath("//div[@class='" + parentClassName + "']//*[contains(text(),'" + field + "')]"))
					.size() > 0) {
				present.add(field);

			} else {

			}
		}
		String preField = "";
		String nonPreField = "";

		if (!present.isEmpty()) {

			for (String field : present) {
				preField += field + ", ";
			}
			assertTrue(!present.isEmpty(),
					"<b>Expected Result:</b> " + fieldType + " - <b>'" + preField.substring(0, preField.length() - 2)
							+ "'</b> should be displayed <br /> <b>Actual Result:</b> " + fieldType + " - <b>'"
							+ preField.substring(0, preField.length() - 2) + "'</b> are displayed successfully");
			logger.pass(
					"<b>Expected Result:</b> " + fieldType + " - <b>'" + preField.substring(0, preField.length() - 2)
							+ "'</b> should be displayed <br /> <b>Actual Result:</b> " + fieldType + " - <b>'"
							+ preField.substring(0, preField.length() - 2) + "'</b> are displayed successfully");

		}

		if (!notPresent.isEmpty()) {

			for (String field : notPresent) {
				nonPreField += field + ", ";
			}
			screenshot = capture(driver);
			logger.fail(
					"<b>Expected Result:</b> " + fieldType + " - <b>'"
							+ nonPreField.substring(0, nonPreField.length() - 2)
							+ "</b> should be displayed <br /> <b>Actual Result:</b> " + fieldType + "- <b>'"
							+ nonPreField.substring(0, nonPreField.length() - 2) + "' </b> are not displayed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			assertFalse(notPresent.isEmpty(),
					"<b>Expected Result:</b> " + fieldType + " - <b>'"
							+ nonPreField.substring(0, nonPreField.length() - 2)
							+ "</b> should be displayed <br /> <b>Actual Result:</b> " + fieldType + "- <b>'"
							+ nonPreField.substring(0, nonPreField.length() - 2) + "' </b> are not displayed");
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void selectClassPickList(By pickListLocator, String OptionType, String pickListOption)
			throws Exception {

		Select pickList = new Select(driver.findElement(pickListLocator));
		switch (OptionType) {
		case "value":

			pickList.selectByValue(pickListOption);
			break;
		case "Visible Text":

			pickList.selectByVisibleText(pickListOption);
			break;

		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void elementIsDisplayed(WebElement element) {
		int count = 0;
		while (count < 60) {
			try {

				if (element.isDisplayed()) {

					break;

				}
			} catch (Exception e) {

			}
			count++;
		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void uploadFiles(String Path) throws Exception {

		// creating object of Robot class
		Robot rb = new Robot();

		// copying File path to Clipboard
		StringSelection str = new StringSelection(Path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.delay(3000);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void tableLoadingWait() throws InterruptedException {
		int i = 0;
		while (i < 120) {
			try {
				if (driver
						.findElement(By.xpath("//div[@class='slds-spinner_container slds-grid slds-hide']/parent::div"))
						.isDisplayed()) {

					break;

				}
			} catch (Exception e) {

			}
			i++;
		}

	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void isEditable(String xpath, String field) throws Throwable {
		if (driver.findElement(By.xpath(xpath)).isEnabled()) {
			logger.info("<b>Expected Result:" + field + "</b> Field should be in Edit mode <br /> <b>Actual Result: "
					+ field + "</b> is editable");
		} else {
			screenshot = capture(driver);
			logger.info(
					" <b>Expected Result:" + field + "</b> Field should be in Edit mode <br /> <b>Actual Result: "
							+ field + "</b> is not editable",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
		status = logger.getStatus();
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void isElementDisplayed(By element) {
		int count = 0;
		while (count < 60) {
			try {

				if (driver.findElement(element).isDisplayed()) {

					break;

				}
			} catch (Exception e) {

			}
			count++;
		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void sendAccountName(String accountName) {
		try {
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']")));
			javaClickByLocator(
					By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']"));
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).click();
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(accountName);
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(Keys.ENTER);
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//a[text()='Accounts']/ancestor::div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small']//a[@title='"
							+ accountName + "']")));
			javaClickByLocator(By.xpath(
					"//a[text()='Accounts']/ancestor::div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small']//a[@title='"
							+ accountName + "']"));
			Thread.sleep(1000);
			closeSpecificTab("" + accountName + " - Search");
		} catch (Exception e) {

		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void sendCaseNumber(String caseNumber) {
		try {
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']")));
			javaClickByLocator(
					By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']"));
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//input[@aria-autocomplete='list']"));
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='FILTER:Case:Cases']"));
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).click();
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(caseNumber);
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(Keys.ENTER);
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[text()='Cases']/ancestor::div[@class='searchScrollerWrapper']//table//tbody//tr//th//a[@title='"
							+ caseNumber + "']")));
			javaClickByLocator(By.xpath(
					"//div[text()='Cases']/ancestor::div[@class='searchScrollerWrapper']//table//tbody//tr//th//a[@title='"
							+ caseNumber + "']"));
			Thread.sleep(1000);
			closeSpecificTab("" + caseNumber + " - Search");
		} catch (Exception e) {

		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void sendOrderNumber(String orderNumber) {
		try {
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']")));
			javaClickByLocator(
					By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']"));
			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//input[@aria-autocomplete='list']"));

			Thread.sleep(1000);
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='ALL:Order:Orders']"));
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).click();
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(orderNumber);
			driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(Keys.ENTER);
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[text()='Orders']/ancestor::div[@class='searchScrollerWrapper']//table//tbody//tr//td//a[@title='"
							+ orderNumber + "']")));
			javaClickByLocator(By.xpath(
					"//div[text()='Orders']/ancestor::div[@class='searchScrollerWrapper']//table//tbody//tr//td//a[@title='"
							+ orderNumber + "']"));
			Thread.sleep(1000);
			closeSpecificTab("" + orderNumber + " - Search");
		} catch (Exception e) {

		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void scrollToElement(WebElement WebElement) {
		// used to select certain field by scrolling the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", WebElement);
		driver.manage().timeouts().implicitlyWait(explicitWaitTimeOut);
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void notificationDismiss() {
		try {
			if (driver.findElement(By.xpath("//button[@title='Dismiss notification']")).isDisplayed()) {
				int size = driver.findElements(By.xpath("//button[@title='Dismiss notification']")).size();

				for (int i = 0; i < size; i++) {
					javaClickByLocator(By.xpath("//button[@title='Dismiss notification']"));
				}
			}
		} catch (Exception e) {

		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	// add products from Product catalog to Shopping cart
	public static void addFromCatalog() throws Throwable {
		driver.findElement(By.xpath("//button[@title='Product Catalog']")).click();
		int[] qty = { 25, 35 };
		int i;
		int j = 1;
		for (i = 0; i < 2; i++) {
			driver.findElement(By.xpath("(//input[@name='quantity'])[" + j + "]")).click();
			driver.findElement(By.xpath("(//input[@name='quantity'])[" + j + "]")).clear();
			driver.findElement(By.xpath("(//input[@name='quantity'])[" + j + "]")).sendKeys(String.valueOf(qty[i]));
			driver.findElement(By.xpath("(//input[@name='quantity'])[" + j + "]")).sendKeys(Keys.TAB);
			j++;
		}
		driver.findElement(By.xpath("//button[@title='Update Shopping Cart']")).click();
		driver.findElement(By.xpath("//button[text()='Close']")).click();
		Thread.sleep(2000);
		javaClickByLocator(By.xpath(
				"//div[@class='slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage']//button[@title='Close']"));

	}

	/**
	 * Asserts that the actual string contains the expected string.
	 * 
	 * @param Actual            the actual value
	 * @param expected          the expected value
	 * @param Message           the assertion error message
	 * @param hardAssertionFlag flag to indicate hard assertion
	 * @param Scenario          the given scenario
	 * @throws IOException
	 */
	// Created by Abhishek Palankar on 09-Dec-2022
	public static void loggerFail(String ExpectedMessage, String ActualMessage, boolean hardAssertionFlag)
			throws IOException {
		screenshot = capture(driver);
		logger.fail(
				"<b> Expected Result: </b> " + ExpectedMessage + "</b><br /> <b> Actual Result: </b> " + ActualMessage,
				MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		// Stopping the execution if hardAssertionFlag is true
		if (hardAssertionFlag) {
			Assert.fail("<b> Expected Result: </b> " + ExpectedMessage + "</b><br /> <b> Actual Result: </b> "
					+ ActualMessage);
		} else {

			softAssert.fail("<b> Expected Result: </b> " + ExpectedMessage + "</b><br /> <b> Actual Result: </b> "
					+ ActualMessage);
		}
	}

	// Created by Abhishek Palankar on 14-Dec-2022
	// To update the extent report that the
	// required condition is satisfied with/without screenshot
	public static void loggerPass(String expectedMessage, String actualMessage, String screenshot) throws IOException {

		screenshot = screenshot.toLowerCase();

		switch (screenshot) {
		case "yes":
			screenshot = capture(driver);
			logger.pass(
					"<b> Expected Result: </b> " + expectedMessage + "</b><br /> <b> Actual Result: </b> "
							+ actualMessage,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

			Reporter.log(actualMessage, true);

			break;
		case "no":

			logger.pass("<b> Expected Result: </b> " + expectedMessage + "</b><br /> <b> Actual Result: </b> "
					+ actualMessage);

			Reporter.log(actualMessage, true);
			break;

		}

	}

	// Created by Abhishek Palankar on 22-Dec-2022
	// This method is used to update the extent report that the
	// required condition is satisfied with/without screenshot
	public static void loggerInfo(String message, String screenshot) throws IOException {
		screenshot = screenshot.toLowerCase();
		switch (screenshot) {
		case "yes":
			screenshot = capture(driver);
			logger.info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Reporter.log(message);
			break;
		case "no":
			logger.info(message);
			Reporter.log(message);
			break;
		}

	}

	// Created by Abhishek Palankar on 09-Dec-2022
	// Description: used to click on certain fields using Javascript
	// Parameter names that needs to be passed: WebElement of the Element
	public static void selectObjectFromDropdown(String objectName) throws IOException, InterruptedException {

		// used to click certain fields using javascript on span and div tag

		int attempts = 0;
		while (attempts < 10) {
			try {
				new WebDriverWait(driver, explicitWaitTimeOut).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + objectName + "']")));
				if (driver.findElement(By.xpath("//span[text()='" + objectName + "']")).isDisplayed()) {
					loggerPass(objectName + " object should be displayed.", objectName + " object is displayed.", "No");
					break;
				}
			} catch (Exception e) {
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(HomePage.NavigationBtn));
				logger.info("Select Home object from navigation menu");
				driver.findElement(HomePage.NavigationBtn).click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//section[@aria-label='Navigation Menu']//span[text()='" + objectName + "']"))
						.click();
				loggerPass(objectName + " object should be selected from navigation menu.",
						objectName + " object is selected from navigation menu.", "No");

			}
		}
		attempts++;
	}

	// Created by Abhishek Palankar on 16-May-2023
	// Description: This method is to send/fill text to any field:
	public static void fillFieldByLocator(By locator, String value) throws Exception {
		try {
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			driver.findElement(locator).click();
			Thread.sleep(2000);
			driver.findElement(locator).clear();
			Thread.sleep(2000);
			driver.findElement(locator).sendKeys(value);
			Thread.sleep(2000);

		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(e.getMessage());

		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void SelectAccountFromGlobalSearch(String accountNo, String accountName) throws Exception {
		selectObjectFromDropdown(Constants.home);
	//	selectAppOrObjFromAppLauncher("object", Constants.home);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(HomePage.globalSearchBtn));
		javaClickByLocator(HomePage.globalSearchBtn);
		javaClickByLocator(HomePage.searchBtn);
		fillFieldByLocator(HomePage.searchBtn, accountNo);
		logger.info("Account no is <b>" + accountNo);
		javaClickByLocator(HomePage.result);
		logger.info("Account name is <b>" + accountName);

//		"//a[text()='Accounts']/ancestor::div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small']//a[@title='"+ accountName + "']"
		driver.findElement(By.xpath(
				"//a[text()='Accounts']/ancestor::div[@class='slds-grid slds-grid_vertical']//a[@title=\""+ accountName + "\"]"))
				.click();
		
		
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.detailsTab));
		if (driver.findElements(Accounts.detailsTab).size() > 0) {
			loggerPass("Account details page should be displayed", "Account details page is displayed", "Yes");
		} else {
			loggerFail("Account details page should be displayed", "Account details page not displayed", true);
		}
	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static void ClickOnSaleOrderbutton() {
		try {
			if (driver.findElement(Accounts.createSalesOrderBtn).isDisplayed()) {
				javaClickByLocator(Accounts.createSalesOrderBtn);
			}
		} catch (Exception e) {
			javaClickByLocator(Accounts.carrot);
			javaClickByLocator(By.xpath("//span[text()='Create Sales Order']"));
		}

	}

	// Created by: Abhishek Palankar on 16-May-2023
	public static String GetText(By locator) throws InterruptedException {
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
		Thread.sleep(2000);
		String value = driver.findElement(locator).getText();
		System.out.println("Value is " + value);
		return value;

	}

	// Created by Abhishek Palankar on 14-Dec-2022
	// This method is used to get the default value from dropdown when select
	// tagname
	public String getDefaultDropdownValue(By Locator) throws IOException {
		String value = "";
		try {
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOf(driver.findElement(Locator)));

			Select object = new Select(driver.findElement(Locator));
			value = object.getFirstSelectedOption().getText();
		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(e.getMessage());

		}
		return value;
	}

	// Created by Abhishek Palankar on 17-May-2023
	// This method is used to scroll to particular element
	public static void scrollToElement(By locator) {
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
		js.executeScript("window.scrollBy(0,-200)", "");
		driver.manage().timeouts().implicitlyWait(explicitWaitTimeOut);
	}

	public static void handleReviewPopUp() throws InterruptedException {
		Thread.sleep(2000);
		if (driver.findElements(CreateSalesOrder.warningClose).size() > 0) {
			driver.findElement(CreateSalesOrder.warningClose).click();

		}
		Thread.sleep(2000);
		if (driver.findElements(CreateSalesOrder.reviewOpenSalesOrdersPopUp).size() > 0) {
			driver.findElement(CreateSalesOrder.dismissBtnForSalesOrder).click();

		}
		Thread.sleep(2000);
		if (driver.findElements(CreateSalesOrder.reviewOpenOutboundCases).size() > 0) {
			driver.findElement(CreateSalesOrder.dismissBtn).click();
		}
		
		Thread.sleep(2000);
		if (driver.findElements(CreateSalesOrder.closeCreateCaseTab).size() > 0) {
			CommonFunctions.javaClickByLocator(CreateSalesOrder.closeCreateCaseTab);
			Thread.sleep(2000);
			if (driver.findElements(CreateSalesOrder.saveChangesPopup).size() > 0) {
				CommonFunctions.javaClickByLocator(CreateSalesOrder.discardBtn);
			}
		}
	}

	/*
	 * // created by and Date: Abhishek Palankar on 19-May-2023 // Description:
	 * Selecting the Object from Appalication Launcher // Parameter names that needs
	 * to be passed: Object Name public static void
	 * selectObjectFromAppLaucher(String objectName) throws InterruptedException,
	 * IOException { String actualObject = ""; try { wait = new
	 * WebDriverWait(driver, 10);
	 * 
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.appLauncher
	 * )); WebElement appLauncher = driver .findElement(By.
	 * xpath("//div[@class='appLauncher slds-context-bar__icon-action']//button"));
	 * Thread.sleep(3000); javaClickByWebElement(appLauncher); new
	 * WebDriverWait(driver, 10).until(ExpectedConditions
	 * .visibilityOfElementLocated(By.
	 * xpath("//button[@type='button' and text()='View All']"))); WebElement
	 * ViewAllbutton =
	 * driver.findElement(By.xpath("//button[@type='button' and text()='View All']")
	 * ); Thread.sleep(3000); javaClickByWebElement(ViewAllbutton);
	 * Thread.sleep(3000);
	 * fillField("//input[@placeholder = 'Search apps or items...']", objectName);
	 * Thread.sleep(3000); WebElement obj =
	 * driver.findElement(By.xpath("//a[@data-label='" + objectName + "']"));
	 * javaClickByWebElement(obj); Thread.sleep(5000); if (driver
	 * .findElement(By.xpath(
	 * "//*[@class='appName slds-context-bar__label-action slds-context-bar__app-name']/span"
	 * )) .getText().contains("Contact Center")) { closeMultipleTabs(); if
	 * (driver.findElements(By.xpath("//h2[contains(text(),'Save changes')]")).size(
	 * ) > 0) { javaClickByLocator(By.xpath("//button[text()='Discard Changes']"));
	 * } driver.findElement(By.xpath("//a[@title='" + objectName + "']")).click();
	 * Thread.sleep(5000); }
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//span[text()='" + objectName + "']")));
	 * driver.findElement(By.xpath("//span[text()='" + objectName +
	 * "']")).isDisplayed();
	 * assertTrue(driver.findElement(By.xpath("//span[text()='" + objectName +
	 * "']")).isDisplayed(),
	 * "<b>Expected Result:</b> User should be able to open object <b>'" +
	 * objectName +
	 * "'</b> from the App Launcher. <br /> <b>Actual Result:</b> User has successfully opened the object <b>'"
	 * + actualObject + "'</b> from the App Launcher."); actualObject =
	 * driver.findElement(By.xpath("//span[text()='" + objectName +
	 * "']")).getText();
	 * 
	 * logger.pass("<b>Expected Result:</b> User should be able to open object <b>'"
	 * + objectName +
	 * "'</b> from the App Launcher. <br /> <b>Actual Result:</b> User has successfully opened the object <b>'"
	 * + actualObject + "'</b> from the App Launcher."); } catch (Exception e) {
	 * screenshot = capture(driver);
	 * logger.fail("<b>Expected Result:</b> User should be able to open object <b>'"
	 * + objectName +
	 * "'</b> from the App Launcher. <br /> <b>Actual Result:</b> User is failed to open the object <b>'"
	 * + actualObject + "'</b> from the App Launcher." + e.getMessage(),
	 * MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
	 * Assert.fail("<b>Expected Result:</b> User should be able to open object <b>'"
	 * + objectName +
	 * "'</b> from the App Launcher. <br /> <b>Actual Result:</b> User is failed to open the object <b>'"
	 * + actualObject + "'</b> from the App Launcher."); }
	 * 
	 * status = logger.getStatus();
	 * 
	 * }
	 */

	// Created by Abhishek Palankar on 22-May-2023:
	// Decription : used to generate the random numbers from range
	public static int getRandomInteger(int maximum, int minimum) {

		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	// Created by and Date: Abhishek Palankar on 23-May-2023

	public static void decisionTree(String inquiryType, String accountName, List<String> values)
			throws InterruptedException {

		org.openqa.selenium.support.ui.Select decisionTreeEnquiry = new org.openqa.selenium.support.ui.Select(
				driver.findElement(Accounts.decisionTreeEnquiry));
		decisionTreeEnquiry.selectByVisibleText(inquiryType);

		switch (inquiryType) {
		case "Close my account!":// Please add the Close my account functionality steps

			break;
		case "Contract / Pricing Related":// Please add the Contract / Pricing Related functionality steps
			String contractIssue = values.get(0);
			driver.findElement(By.xpath("//option[text()='Contract / Pricing Related']")).click();
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
			javaClickByLocator(Accounts.nextBtn);
			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//b[text()='Customer Support Ticket History']")).isDisplayed())
					;
				{
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
					javaClickByLocator(Accounts.nextBtn);
				}
			} catch (Exception e) {
			}
			// assertTrue(driver.findElement(By.xpath("//b[text()='Customer Support Ticket
			// History']")).isDisplayed());

			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.pricingQuestion));
			if (driver.findElement(Accounts.pricingQuestion).isDisplayed()) {
				logger.pass(
						"<b>Expected Result:</b> The Question <b>\"What pricing/contract issues or questions do you have?\"</b> should be displayed <br /> <b> Actual Result: </b>The Question <b>\"What pricing/contract issues or questions do you have?\"</b> is displayed ");
			} else {
				logger.fail(
						"<b>Expected Result:</b> The Question <b>\"What pricing/contract issues or questions do you have?\"</b> should be displayed <br /> <b> Actual Result: </b>The Question <b>\"What pricing/contract issues or questions do you have?\"</b> is not displayed ");
			}
			org.openqa.selenium.support.ui.Select issue = new org.openqa.selenium.support.ui.Select(
					driver.findElement(By.xpath("//select[@class='slds-select']")));
			issue.selectByVisibleText(contractIssue);
			Thread.sleep(2000);
			switch (contractIssue) {
			case "I am Requesting Free Products / Donations.":
				driver.findElement(By.xpath("//option[text()='I am Requesting Free Products / Donations.']")).click();
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				break;

			case "I have questions about available Promotions / Incentives.":
				driver.findElement(
						By.xpath("//option[text()='I have questions about available Promotions / Incentives.']"))
						.click();
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.next);
				Thread.sleep(2000);
				javaClickByLocator(By.xpath(
						"//div[@class='bBody']//select[@class='select uiInput uiInputSelect uiInput--default uiInput--select']"));
				driver.findElement(By.xpath("//option[@label='Sales Rep / Face-to-Face']")).click();
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				break;

			case "Questions about my existing Pricing, CMA or Contract.":
				javaClickByLocator(
						By.xpath("//option[text()='Questions about my existing Pricing, CMA or Contract.']"));
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(5000);
				try {
					if (driver.findElement(By.xpath("//button[text()='Next']")).isDisplayed())
						;
					{
						new WebDriverWait(driver, explicitWaitTimeOut)
								.until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
						salesforce_ScrollToElement("//button[text()='Next']");
						javaClickByLocator(Accounts.nextBtn);
					}
				} catch (Exception e) {
				}
				break;

			case "I have questions about Product Availability.":
				driver.findElement(By.xpath("//option[text()='I have questions about Product Availability.']")).click();
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				break;
			case "I want to order Tumblers / Contracted Tumblers":
				driver.findElement(By.xpath("//option[text()='I want to order Tumblers / Contracted Tumblers']"))
						.click();
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				break;
			case "I was overcharged on a previous order I placed.":
				driver.findElement(By.xpath("//option[text()='I was overcharged on a previous order I placed.']"))
						.click();
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				break;
			case "I have questions about my existing Pricing, CMA or Contract.":
				javaClickByLocator(
						By.xpath("//option[text()='I have questions about my existing Pricing, CMA or Contract.']"));
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(5000);
				try {
					if (driver.findElement(Accounts.nextBtn).isDisplayed()) {
						new WebDriverWait(driver, explicitWaitTimeOut)
								.until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
						salesforce_ScrollToElement("//button[text()='Next']");
						javaClickByLocator(Accounts.nextBtn);
					}
				} catch (Exception e) {
				}
				break;
			}

			break;
		case "Delivery / Order Related":// Please add the Delivery / Order Related functionality steps

			break;

		case "Equipment":

			String equipmentDescription = values.get(0);
			String equipmentTypeDetail = values.get(1);
			String typeOfEquipmentEnquiry = values.get(2);
			String equipmentProblem = values.get(3);
			Thread.sleep(2000);
			salesforce_ScrollToElement("//span[text()='Decision Tree']");
			Thread.sleep(2000);
			javaClickByLocator(Accounts.nextBtn);

			Thread.sleep(2000);

			if (driver.findElements(By.xpath(
					"//b[text()='Select the Equipment']/ancestor::div[@class='flowruntimeBody flowruntimeBody__lwc slds-card__body slds-p-horizontal_small']//table//tbody//tr"))
					.size() > 0) {
				logger.info("Account <b>" + accountName + "</b> has existing equipemt present");
			} else {
				logger.fail("Account <b>" + accountName + "</b> does not have any existing equipment present");
				Assert.fail();
			}
			System.out.println("Equipment Description is " + equipmentDescription);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//lightning-base-formatted-text[text()='" + equipmentDescription+ "']/ancestor::td/preceding-sibling::td//span[@class='slds-checkbox']")).click();
			javaClickByLocator(Accounts.nextBtn);
			try {
				if (driver.findElement(Accounts.openESTCSTHostory).isDisplayed()) {
					javaClickByLocator(Accounts.nextBtn);
				}
			} catch (Exception e) {
			}
			Thread.sleep(3000);

			if (driver.findElement(Accounts.equipmentTypeValue).getText().contains("Cooler")) {

				Thread.sleep(2000);
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(
						driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
				// CommonFunctions.javaClickByLocator(By.xpath("//label[text()='Equipment Type
				// Detail']/ancestor::lightning-picklist//button"));
				Thread.sleep(2000);

				select.selectByVisibleText(equipmentTypeDetail);
				// CommonFunctions.javaClickByLocator(By.xpath("
				// //lightning-base-combobox-item[@data-value='"+equipmentTypeDetail+"']"));
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(3000);

				select = new org.openqa.selenium.support.ui.Select(
						driver.findElement(By.xpath("//select[@class='slds-select']")));
				select.selectByVisibleText(typeOfEquipmentEnquiry);
				javaClickByLocator(Accounts.nextBtn);

				switch (typeOfEquipmentEnquiry) {
				case "Needs repair / Maintenance":
					Thread.sleep(3000);
					select = new org.openqa.selenium.support.ui.Select(
							driver.findElement(By.xpath("//select[@class='slds-select']")));
					select.selectByVisibleText(equipmentProblem);

					javaClickByLocator(Accounts.nextBtn);
					switch (equipmentProblem) {
					case "Electrical Issue":
						select = new org.openqa.selenium.support.ui.Select(
								driver.findElement(By.xpath("//select[@class='slds-select']")));
						select.selectByVisibleText("");
						switch ("") {
						case "No":
							select = new org.openqa.selenium.support.ui.Select(
									driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")));
							select.selectByVisibleText("");

							switch ("") {
							case "Yes":
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
								select.selectByVisibleText("Not Required");
								javaClickByLocator(Accounts.nextBtn);
								break;
							case "No":
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
								select.selectByVisibleText("");
								javaClickByLocator(Accounts.nextBtn);
								break;
							default:
								break;
							}
							break;
						case "Yes":
							select = new org.openqa.selenium.support.ui.Select(
									driver.findElement(By.xpath("//select[@class='slds-select']")));
							select.selectByVisibleText("");
							javaClickByLocator(Accounts.nextBtn);
							switch ("") {
							case "No":
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("//select[@class='slds-select']")));
								select.selectByVisibleText("");
								javaClickByLocator(Accounts.nextBtn);
								break;
							case "Yes":
								// done
								break;
							}
							break;
						default:
							break;
						}

						break;
					case "Need Parts":
						select = new org.openqa.selenium.support.ui.Select(
								driver.findElement(By.xpath("//select[@class='slds-select']")));
						select.selectByVisibleText("partNeed");
						javaClickByLocator(Accounts.nextBtn);

						break;

					case "Bad Odor":
						javaClickByLocator(Accounts.nextBtn);
						break;
					case "Noisy":
						select = new org.openqa.selenium.support.ui.Select(
								driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")));
						select.selectByVisibleText("");
						switch ("") {
						case "No":
							select = new org.openqa.selenium.support.ui.Select(
									driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
							select.selectByVisibleText("");
							javaClickByLocator(Accounts.nextBtn);
							break;
						}
						break;
					case "Temperature Issue":
						select = new org.openqa.selenium.support.ui.Select(
								driver.findElement(By.xpath("//select[@class='slds-select']")));
						select.selectByVisibleText("");
						javaClickByLocator(Accounts.nextBtn);
						break;
					case "Vandalized":

						break;
					case "Leaking":
						String isCustomerConatinedWater = values.get(4);
						String isWaterInTrafficArea = values.get(5);
						String customerAcceptedPhoneFix = values.get(6);
						String phoneFixRejectionReason = values.get(7);

						Thread.sleep(2000);
						// div[text()='What is
						// Leaking']/ancestor::div[@class='bBody']//div[@class='slds-form-element__control']
						// span[text()='What is Leaking']/ancestor::div[@class='flowruntime-input
						// slds-form-element']//div[@class='slds-form-element__control']
						if (driver.findElement(By.xpath(
								"//span[text()='What is Leaking']/ancestor::div[@class='flowruntime-input slds-form-element']//div[@class='slds-form-element__control']"))
								.getText().contains("Water")) {
							javaClickByLocator(Accounts.nextBtn);
						} else {
							select = new org.openqa.selenium.support.ui.Select(
									driver.findElement(By.xpath("//select[@class='slds-select']")));
							select.selectByVisibleText("Water");
							javaClickByLocator(Accounts.nextBtn);
						}
						Thread.sleep(2000);
						select = new org.openqa.selenium.support.ui.Select(
								driver.findElement(By.xpath("//select[@class='slds-select']")));
						select.selectByVisibleText(isCustomerConatinedWater);
						javaClickByLocator(Accounts.nextBtn);

						switch (isCustomerConatinedWater) {
						case "No":
							Thread.sleep(2000);
							select = new org.openqa.selenium.support.ui.Select(
									driver.findElement(By.xpath("//select[@class='slds-select']")));
							select.selectByVisibleText(isWaterInTrafficArea);
							javaClickByLocator(Accounts.nextBtn);

							switch (isWaterInTrafficArea) {
							case "No":
								Thread.sleep(2000);
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")));
								select.selectByVisibleText(customerAcceptedPhoneFix);

								switch (customerAcceptedPhoneFix) {
								case "Yes":
									Thread.sleep(2000);
									select = new org.openqa.selenium.support.ui.Select(
											driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
									select.selectByVisibleText(phoneFixRejectionReason);
									javaClickByLocator(Accounts.nextBtn);
									break;
								case "No":
									select = new org.openqa.selenium.support.ui.Select(
											driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
									select.selectByVisibleText(phoneFixRejectionReason);
									javaClickByLocator(Accounts.nextBtn);
									break;
								}
								break;
							case "Yes":
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")));
								select.selectByVisibleText(customerAcceptedPhoneFix);

								switch (customerAcceptedPhoneFix) {
								case "Yes":
									select = new org.openqa.selenium.support.ui.Select(
											driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
									select.selectByVisibleText(phoneFixRejectionReason);
									javaClickByLocator(Accounts.nextBtn);
									break;
								case "No":
									select = new org.openqa.selenium.support.ui.Select(
											driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
									select.selectByVisibleText(phoneFixRejectionReason);
									javaClickByLocator(Accounts.nextBtn);
									break;
								}
								break;
							}
							break;
						case "Yes":
							select = new org.openqa.selenium.support.ui.Select(
									driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")));
							select.selectByVisibleText(customerAcceptedPhoneFix);
							switch (customerAcceptedPhoneFix) {
							case "Yes":
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
								select.selectByVisibleText(phoneFixRejectionReason);
								javaClickByLocator(Accounts.nextBtn);
								break;
							case "No":
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
								select.selectByVisibleText(phoneFixRejectionReason);
								javaClickByLocator(Accounts.nextBtn);
								break;
							}
							break;
						}
						break;

					case "Maintenance":

						break;
					default:
						break;
					}

					break;
				case "New/Additional Equipment":

					break;
				case "Other Equipment Requests":

					break;
				case "Removal":

					break;
				case "Swap":

					break;
				case "Equipment Repair/Maintenance":
					Thread.sleep(2000);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Dropdown));
					Select problem = new Select(driver.findElement(Accounts.Dropdown));
					problem.selectByVisibleText("Bad Odor or Smell");
					Thread.sleep(3000);
					javaClickByLocator(Accounts.nextBtn);
					Thread.sleep(4000);
					break;
				case "Equipment needs repair/maintenance":
					Thread.sleep(2000);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Dropdown));
					Select problem1 = new Select(driver.findElement(Accounts.Dropdown));
					problem1.selectByVisibleText("Door/Glass Broken");
					Thread.sleep(3000);
					javaClickByLocator(Accounts.nextBtn);
					Thread.sleep(4000);
					break;
				case "My BOTTLER OWNED equipment needs repair/maintenance.":
					Thread.sleep(2000);
					select = new org.openqa.selenium.support.ui.Select(
							driver.findElement(By.xpath("//select[@class='slds-select']")));
					select.selectByVisibleText(equipmentProblem);
					javaClickByLocator(Accounts.nextBtn);
					Thread.sleep(3000);
					select = new org.openqa.selenium.support.ui.Select(
							driver.findElement(By.xpath("//select[@class='slds-select']")));
					select.selectByVisibleText("Water is pooling under the machine.");
					javaClickByLocator(Accounts.nextBtn);
					Thread.sleep(2000);
					break;
				case "My equipment needs repair/maintenance.":
					Thread.sleep(3000);

					select = new org.openqa.selenium.support.ui.Select(
							driver.findElement(By.xpath("//select[@class='slds-select']")));

					select.selectByVisibleText(equipmentProblem);
					javaClickByLocator(Accounts.nextBtn);
					switch (equipmentProblem) {
					case "I have a cosmetic request. (i.e. graphics, lights, labels, etc.)":

						break;
					case "I have an issue with my cooler door.":

						break;
					case "My equipment is making noises or is noisey overall.":

						break;
					case "Secure/Mount Equipment":

						break;
					case "The credit card reader is not reading cards / not working.":

						break;
					case "Bill Validator Related":

						break;
					case "Need Parts/Keys":

						break;
					case "We have a leak.":
						String whatIsLeaking = values.get(4);
						Thread.sleep(3000);

						select = new org.openqa.selenium.support.ui.Select(
								driver.findElement(By.xpath("//select[@class='slds-select']")));
						select.selectByVisibleText(whatIsLeaking);
						javaClickByLocator(Accounts.nextBtn);
						Thread.sleep(2000);
						try {
							if (Constants.bottler.equals("5000")) {
								select = new org.openqa.selenium.support.ui.Select(
										driver.findElement(By.xpath("//select[@class='slds-select']")));
								select.selectByVisibleText("Yes");
								javaClickByLocator(Accounts.nextBtn);
							}
						} catch (Exception x) {

						}
						break;
					case "We have a QUALITY issue!":

						break;
					case "We have a temperature Issue.":

						break;
					case "We have an electrical issue.":

						break;
					case "We have an issue when dispensing/pouring drinks.":

						break;

					}

					break;
				}

			} else if (driver.findElement(Accounts.equipmentTypeValue).getText().contains("Dispenser")) {
				Thread.sleep(2000);
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(
						driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")));
				select.selectByVisibleText(equipmentTypeDetail);
				Thread.sleep(2000);
				javaClickByLocator(Accounts.nextBtn);
				Thread.sleep(1000);

			}

			break;
		case "Feedback on Bottler Employee":// Please add the Feedback on Bottler Employee functionality steps

			break;
		case "Financial":// Please add the Financial functionality steps
			String financialIssue = values.get(0);
			driver.findElement(By.xpath("//option[text()='Financial']")).click();
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
			javaClickByLocator(Accounts.nextBtn);
			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//b[text()='Customer Support Ticket History']")).isDisplayed())
					;
				{
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
					javaClickByLocator(Accounts.nextBtn);
				}
			} catch (Exception e) {
			}
			// assertTrue(driver.findElement(By.xpath("//b[text()='Customer Support Ticket
			// History']")).isDisplayed());
			new WebDriverWait(driver,explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.pricingQuestion));
			if (driver.findElement(Accounts.pricingQuestion).isDisplayed()) {
				logger.pass(
						"<b>Expected Result:</b> The Question <b>\"What type of Financial inquiry do you have?\"</b> should be displayed <br /> <b> Actual Result: </b>The Question <b>\"What type of Financial inquiry do you have?\"</b> is displayed ");
			} else {
				logger.fail(
						"<b>Expected Result:</b> The Question <b>\"What type of Financial inquiry do you have?\"</b> should be displayed <br /> <b> Actual Result: </b>The Question <b>\"What type of Financial inquiry do you have?\"</b> is not displayed ");
			}
			org.openqa.selenium.support.ui.Select issue1 = new org.openqa.selenium.support.ui.Select(
					driver.findElement(By.xpath("//select[@class='slds-select']")));
			issue1.selectByVisibleText(financialIssue);
			Thread.sleep(2000);
			switch (financialIssue) {
			case "Account Balance":
				javaClickByLocator(By.xpath("//option[text()='Account Balance']"));
				Thread.sleep(5000);
				salesforce_ScrollToElement("//button[text()='Next']");
				new WebDriverWait(driver,explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				try {
					if (driver.findElement(By.xpath("//button[text()='Next']")).isDisplayed()) {
						new WebDriverWait(driver, explicitWaitTimeOut)
								.until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
						salesforce_ScrollToElement("//button[text()='Next']");
						Thread.sleep(2000);
						javaClickByLocator(Accounts.nextBtn);
					}
				} catch (Exception e) {

				}
				break;
			case "Commission Related":
				javaClickByLocator(By.xpath("//option[text()='Commission Related']"));
				Thread.sleep(5000);
				salesforce_ScrollToElement("//button[text()='Next']");
				new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
				javaClickByLocator(Accounts.nextBtn);
				try {
					if (driver.findElement(By.xpath("//button[text()='Next']")).isDisplayed()) {
						new WebDriverWait(driver, explicitWaitTimeOut)
								.until(ExpectedConditions.visibilityOfElementLocated(Accounts.nextBtn));
						salesforce_ScrollToElement("//button[text()='Next']");
						Thread.sleep(2000);
						javaClickByLocator(Accounts.nextBtn);
					}
				} catch (Exception e) {

				}
				break;
			case "Copy of Invoice/Statement":
				break;
			case "Credit Terms Request":
				break;
			case "Order/Credit Block on Account":
				break;
			case "Payment Method Question":
				break;

			}

			break;
		case "I have a Cosmetic Request":// Please add the I have a Cosmetic Request functionality steps

			break;
		case "Internal Use":// Please add the Internal Use functionality steps

			break;
		case "Make changes to my account":// Please add the Make changes to my account functionality steps

			break;
		case "Reimbursements":// Please add the Reimbursements functionality steps

			break;
		case "Sales Requests / Questions":// Please add the Sales Requests / Questions functionality steps

			break;
		default:
			break;
		}

	}

	// Created by Abhishek Palankar on 29-Dec-2022
	// This method is to Get the date as per the required pattern dd-MM-YYYY. d, dd,
	// MM etc
	// example calendarInput("d", 0), calendarInput("MMM d, yyyy", 1),
	// calendarInput("M/d/yyyy hh:mm a", 1), calendarInput("d", -7),
	// calendarInput("EEE d MMM", 0)
	public static String calendarInput(String pattern, long day) throws Exception {

		LocalDateTime currentDate = LocalDateTime.now();

		currentDate = currentDate.plusDays(day);
		DateTimeFormatter globalFormat = DateTimeFormatter.ofPattern(pattern);
		String expectedDateFormat = globalFormat.format(currentDate);
		// expectedDateFormat = expectedDateFormat.toUpperCase();
		return expectedDateFormat;

	}

	// Created by: Niranjan H on 15-June-2023
	public static void GlobalSearch(String Serach) throws Exception {
		selectObjectFromDropdown(Constants.home);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(HomePage.globalSearchBtn));
		javaClickByLocator(HomePage.globalSearchBtn);
		javaClickByLocator(HomePage.searchBtn);
		fillFieldByLocator(HomePage.searchBtn, Serach);
		logger.info("Searching Rsults For <b>" + Serach + "</b>");
		javaClickByLocator(HomePage.result);
		driver.findElement(By.xpath("//span/*[@title='" + Serach + "']")).click();

		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.detailsTab));

		if (driver.findElements(Accounts.detailsTab).size() > 0) {
			loggerPass(Serach + " Page should be displayed", Serach + " Page is displayed", "Yes");
		} else {
			loggerFail(Serach + " Page should be displayed", Serach + " Page not displayed", true);
		}
	}

	// Created by: Niranjan H on 21-June-2023
	public static void isDisplayedWithScreenshot(By locator, String ElementName, String ScreenShot) throws IOException {
		int attempts = 0;
		while (attempts < 4) {
			try {
				WebElement ele = null;
				ele = new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (ele.isDisplayed()) {
					assertTrue(ele.isDisplayed(),
							"<b>Expected Result:</b> The <b>'" + ElementName
									+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
									+ "'</b> is displayed. <br />");
			if ( ScreenShot.equalsIgnoreCase("YES")) {
						scrollToElement(locator);
						screenshot = capture(driver);
						logger.pass(
								"<b>Expected Result:</b> The <b>'" + ElementName
										+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'"
										+ ElementName + "'</b> is displayed. <br />",
								MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					} else {
						logger.pass("<b>Expected Result:</b> The <b>'" + ElementName
								+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
								+ "'</b> is displayed. <br />");
					}

					break;
				}
			} catch (Exception e) {

			}

			attempts++;
			if (attempts >= 4) {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b> The <b>'" + ElementName
								+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
								+ "'</b> is not displayed. <br />",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				Assert.fail("<b>Expected Result:</b> The <b>'" + ElementName
						+ "'</b> should be displayed. <br /> <b>Actual Result:</b> The <b>'" + ElementName
						+ "'</b> is not displayed. <br />");
				}
		}
	}
	
	// Created by H S Krishna Prasad on 04-JUNE-20223
	// This Method is to close all the sales order pop-ups
	public static void warningVisitHours() throws Throwable {
		if (driver.findElements(CreateCloneOrders.warningMsgVisitPlan).size() > 0) {
			driver.findElement(CreateCloneOrders.closeWarningMsgVisitPlan).click();
		}
		selectAppsFromAppLauncher("Contact Center");

		if (driver.findElements(CreateSalesOrder.reviewOpenSalesOrdersPopUp).size() > 0) {
			driver.findElement(CreateSalesOrder.dismissBtn).click();
		}
		if (driver.findElements(CreateCloneOrders.reviewOutboundCasePopup).size() > 0) {
			driver.findElement(CreateSalesOrder.dismissBtn).click();
		}
	}

	// Created by H S Krishna Prasad on 04-JUNE-20223
	// This Method is to close all the sales order pop-ups, if sales order page is open when logged in.
	public static void discardChanges() throws Throwable {
		if (driver.findElements(CreateCloneOrders.closeSalesOrder).size() > 0) {
			javaClickByLocator(CreateCloneOrders.closeSalesOrder);
			popupVerify("Save changes");
			fieldValidation("Save Changes popup buttons", CreateCloneOrders.errortab);
			javaClickByLocator(CreateCloneOrders.discard);
		}
	}
	
	// Created by Naveen S on 04-JUNE-20223
	public static boolean compareLists(List<String> lineList, List<String> cloneList) {
		if (cloneList.containsAll(lineList)) {
			return true;
		}
		else {
			return false;
		}
	}
	// Created By H S Krishna Prasad H S on 14 July 2023
	public static void scrollToWebElement(WebElement WebElement) {
		// used to select certain field by scrolling the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", WebElement);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	
	// Created By Niranjan H on 31 Aug 2023
	public static void CaptureFailure(Exception e) throws IOException {

		screenshot = capture(driver);
		logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		Assert.fail(e.getMessage());
		status = logger.getStatus();

	}
	
		// Created By Niranjan H on 13-Feb 2024
		public static void AddFilter(String Field, String Operator, String Value) throws IOException, InterruptedException {
			Thread.sleep(1000);
			scrollToElement(CST_Routing.addFilter);
			javaClickByLocator(CST_Routing.addFilter);
			javaClickByLocator(CST_Routing.field);
			Thread.sleep(2000);
			scrollToElement(By.xpath("//lightning-base-combobox-item/span/span[@title='"+Field+"']"));
			javaClickByLocator(By.xpath("//lightning-base-combobox-item/span/span[@title='"+Field+"']"));
			
			
			
			javaClickByLocator(CST_Routing.operator);
			Thread.sleep(2000);
			scrollToElement(By.xpath("//lightning-base-combobox-item/span/span[text()='"+Operator+"']"));
			javaClickByLocator(By.xpath("//lightning-base-combobox-item/span/span[@title='"+Operator+"']"));
			
			
			
			Thread.sleep(2000);
			if (driver.findElements((By.xpath("//div[text()='Value']/parent::div//div[@class='uiPopupTrigger']"))).size()>0) {
				driver.findElement((By.xpath("//div[text()='Value']/parent::div//div[@class='uiPopupTrigger']"))).click();
				scrollToElement(By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']/ul/li/a[text()='"+Value+"']"));
				driver.findElement(By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']/ul/li/a[text()='"+Value+"']")).click();
				Thread.sleep(1000);
			}else if (driver.findElements((By.xpath("//span[text()='Value']/ancestor::div[@class='forceRecordLayout']//input"))).size()>0) {
				Thread.sleep(1000);
				fillField("//span[text()='Value']/ancestor::div[@class='forceRecordLayout']//input", Value+org.sikuli.hotkey.Keys.TAB);
				
			}else if (driver.findElements(By.xpath("//legend[text()='Value']/ancestor::div[@class='forceRecordLayout']")).size()>0) {
				Thread.sleep(1000);
				javaClickByLocator(By.xpath("//legend[text()='Value']/ancestor::div[@class='forceRecordLayout']//span[text()='"+Value+"']/ancestor::span/input"));
			}
			Thread.sleep(1000);
			
			
			javaClickByLocator(CST_Routing.doneBtn);

			}
	
		    // Created By Niranjan H on 16-Feb 2024
		public static void VerifyCaseStatus_ForOCDM(String Disposition, String CallResult, String Exp_CaseStatus) throws Throwable
				 {
			selectClassPickList(Cases.Disposition_DropDown, "Visible Text", Disposition);
			javaClickByLocator(Accounts.nextBtn);
			selectClassPickList(Cases.CallResult_DropDown, "Visible Text", CallResult);
			javaClickByLocator(Accounts.nextBtn);
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.invisibilityOf(driver.findElement(By
					.xpath("//lightning-spinner[@class='slds-spinner_container']/ancestor::flowruntime-spinner-lwc"))));
			Thread.sleep(5000);
			driver.get(driver.getCurrentUrl());
			String Act_CaseStatus = GetText(Cases.Case_Status);
			if (Act_CaseStatus.equalsIgnoreCase(Exp_CaseStatus)) {
				loggerPass(
						"If Disposition is <b>" + Disposition + "</b> & Call Result is <b>" + CallResult
								+ "</b> Case status should be changed to <b>" + Exp_CaseStatus + "</b>",
						"Case status changed to <b>" + Act_CaseStatus + "</b>", "No");

			} else {
				loggerFail(
						"If disposition is <b>" + Disposition + "</b> & Call Result is <b>" + CallResult
								+ "</b> Case status should be changed to <b>" + Exp_CaseStatus + "</b>",
						"Case Status Not changed to <b>" + Exp_CaseStatus + "</b>", false);
			}
			
			Thread.sleep(2000);
			scrollToElement(Cases.DispositionValue);
			String DispositionValue = GetText(Cases.DispositionValue);
			if (DispositionValue.equalsIgnoreCase(Disposition)) {
				loggerPass("Disposition Field should be update as <b>" + Disposition + "</b>",
						"Disposition Field is update as <b>" + DispositionValue + "</b>", "No");

			} else {
				loggerFail("Disposition Field should be update as " + Disposition + "",
						"Disposition Field is Not updated as <b>" + Disposition + "</b>", false);
			}

			String CallResultValue = GetText(Cases.CallResultValue);
			if (CallResultValue.equalsIgnoreCase(CallResult)) {
				loggerPass("Disposition Field should be update as <b>" + CallResult + "</b>",
						"Disposition Field is update as <b>" + CallResultValue + "</b>", "No");

			} else {
				loggerFail("Disposition Field should be update as " + CallResult + "",
						"Disposition Field is Not updated as <b>" + CallResult + "</b>", false);
			}

		}
	
	
		public static void ChangeCaseStatus(String Status) throws Throwable {
		System.out.println("Changing Case status");
		scrollToElement(Cases.Case_Status);
		javaClickByLocator(Cases.EditCase_Status);
		Thread.sleep(2000);
		select_dropdownValue(Status, Cases.StatusButton);
		javaClickByLocator(Accounts.saveBtn);
		Thread.sleep(3000);
//		driver.get(driver.getCurrentUrl());
		}

		
		
		
		// Created By Niranjan H on 19-Mar-24
		public static String GetValue_SalesforceInspector(String APINameOrLableName) throws InterruptedException, IOException
		{
				for(int i =0;i<=10;i++) 
				{	driver.findElement(SalesForce_Inspector.Inspector).click();
					new WebDriverWait(driver,explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(SalesForce_Inspector.Options));
					driver.switchTo().frame(driver.findElement(SalesForce_Inspector.Options));
					if (driver.findElements(SalesForce_Inspector.ShowAllData).size() > 0) {System.out.println("Show All Data Displayed");break;}
					else {driver.switchTo().defaultContent();driver.findElement(SalesForce_Inspector.Inspector).click();Thread.sleep(1000);
					if (i == 10) {loggerInfo("No record to display", "Yes");System.exit(0);}}
				}
				((JavascriptExecutor) driver).executeScript("window.open('"+driver.findElement(SalesForce_Inspector.ShowAllData).getAttribute("href")+"')");
				String CurrentWin = driver.getWindowHandle();
				for (String winHandle : driver.getWindowHandles()) {driver.switchTo().window(winHandle);}
				new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(SalesForce_Inspector.SearchLable));
				driver.findElement(SalesForce_Inspector.SearchLable).sendKeys(APINameOrLableName);
				String Value = "";
				try {Value = driver.findElement(By.xpath("//td[contains(@class,'field')]/div[text()='" + APINameOrLableName+ "']/ancestor::tr//td[@class='field-column']/div")).getText();
				loggerPass("<b>"+APINameOrLableName+ "</b> Should be displayed in salesforce inspector", "<b>"+APINameOrLableName+ "</b> is displayed in salesforce inspector", "No");		
				loggerInfo("Value for <b>"+APINameOrLableName + "</b> is <b>" + Value+"</b>", "No");
				System.out.println("Value for "+APINameOrLableName + " is " + Value);
				}catch (org.openqa.selenium.NoSuchElementException e) {loggerFail("<b>"+APINameOrLableName+ "</b> Should be displayed in salesforce inspector", "<b>"+APINameOrLableName+ "</b> is not displayed in salesforce inspector", true);}
				driver.close();
				driver.switchTo().window(CurrentWin);
				javaClickByLocator(SalesForce_Inspector.Inspector);
				return Value;
		
		}
		
		// Created By :- Niranjan H
		// Created On :- 30-05-24
		public static void DecisionTree_Latest(List<String> List_Of_Questions,List<String> List_Of_Values,String ContactName) throws InterruptedException, IOException 
		{
			loggerInfo("Creating case from Decision Tree", "No");
			if (!(driver.findElements(By.xpath("//a[@aria-selected='true' and text()='Create Case']")).size()>0)) {
				if (driver.findElements(By.xpath("//button[@title='More Tabs']//parent::lightning-button-menu//span[text()='Create Case']")).size()>0) {
					javaClickByLocator(By.xpath("//button[@title='More Tabs']"));
					javaClickByLocator(By.xpath("//span[text()='Create Case']/parent::a"));
				}
				scrollToElement(By.xpath("//a[text()='Create Case']"));
				javaClickByLocator(By.xpath("//a[text()='Create Case']"));	
			}
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[text()='Decision Tree']/ancestor::article//select")));
			isDisplayed(By.xpath("//span[text()='Decision Tree']/ancestor::article//select"), "Decision Tree");
			
			String Question = null;
			String Value = null;
	
			for (int i = 0; i < List_Of_Questions.size(); i++) {
				Question = List_Of_Questions.get(i);
				Value = List_Of_Values.get(i);
				System.out.println(Question + " = " + Value);
				
				try {
					new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//flowruntime-list-container//*[contains(text(),'" + Question + "')]")));

					try {

						if (driver
								.findElements(By.xpath("//flowruntime-list-container//flowruntime-dependent-picklists"))
								.size() > 0) {
							selectClassPickList(By.xpath(
									"//*[contains(text(),'" + Question + "')]/ancestor::lightning-select//select"),
									"Visible Text", Value);

						} else if (driver.findElements(By.xpath(
								"//*[contains(text(),'" + Question + "')]/ancestor::flowruntime-lwc-field//select"))
								.size() > 0) {
							selectClassPickList(By.xpath(
									"//*[contains(text(),'" + Question + "')]/ancestor::flowruntime-lwc-field//select"),
									"Visible Text", Value);
						} else if (driver.findElements(By.xpath("//b[contains(text(),'" + Question + "')]"))
								.size() > 0) {

							driver.findElement(By.xpath("//lightning-base-formatted-text[text()='" + Value
									+ "']/ancestor::tr//span[@class='slds-checkbox']")).click();
						}
					} catch (Exception e) {
						loggerFail("The <b>" + Value + "'</b> should be displayed.<br />",
								"The <b>'" + Value + "'</b> is not displayed.<br />", true);
					}
				} catch (Exception e) {
					loggerFail("The <b>'" + Question + "'</b> should be displayed.<br />",
							"The <b>'" + Question + "'</b> is not displayed.<br /> ", true);
				}
				Thread.sleep(1000);
				javaClickByLocator(By.xpath("//button[text()='Next']"));
				Thread.sleep(3000);
				if (driver.findElements(By.xpath("//table//th//span[text()='Case Number']")).size() > 0) {
					scrollToElement(By.xpath("//button[text()='Next']"));
					javaClickByLocator(By.xpath("//button[text()='Next']"));
				}
			}
			if (!ContactName.isBlank()) {
				Thread.sleep(2000);
				try {
					javaClickByLocator(By.xpath("//lightning-base-formatted-text[text()='" + ContactName
							+ "']/ancestor::tr//td//span[@class='slds-radio']"));
					loggerInfo(ContactName+" Contact is Assign to the Case Record.", "No");
				} catch (Exception e) {
					loggerFail("Contact Name : " + ContactName + " Should be displayed",
							"Contact Name : " + ContactName + " is not displayed", true);
				}
			}
			scrollToElement(By.xpath("//button[text()='Next']"));
			javaClickByLocator(By.xpath("//button[text()='Next']"));
			Thread.sleep(3000);
			if(driver.findElements(By.xpath("//strong[text()='Case Summary Details']")).size()>0) {
				new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
				driver.findElement(By.xpath("//textarea[@required='']")).sendKeys("Detailed Description");
				Thread.sleep(2000);
				salesforce_ScrollToElement("//button[text()='Next']");
				javaClickByLocator(By.xpath("//button[text()='Next']"));
			}else {
				loggerFail("Case summery details should be displayed", "Case summery details is not displayed", true);
			}
			Thread.sleep(2000);
			try {
			isDisplayed(By.xpath("//h2[contains(text(),'Edit')]"), "Case Edit Pop-Up");
			javaClickByLocator(By.xpath("//button[text()='Save']"));
			Thread.sleep(2000);
			String CaseNumber = GetText(By.xpath("//records-record-layout-item[@field-label='Case Number']//lightning-formatted-text"));
			loggerInfo("Newly created case number is : "+CaseNumber, "yes");
				
			} catch (Exception e) {
				loggerFail("Case should be created", "case is not created", true);
			}
		}
}
