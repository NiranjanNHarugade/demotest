package Features;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.contentstream.operator.graphics.CloseFillEvenOddAndStrokePath;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.antlr.ast.If;
import org.sikuli.hotkey.Keys;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.ibm.icu.util.Calendar;

import Pages.Accounts;
import Pages.CST_Routing;
import Pages.Cases;
import Pages.CreateCloneOrders;
import Pages.CreateSalesOrder;
import Pages.RetailStoreGroups;
import Utilities.CommonFunctions;
import Utilities.Constants;
import jnr.ffi.Struct.off_t;

public class Features_Case extends CommonFunctions {
	public static boolean validationFlag = true;

	public static void createOutbound(HashMap<String, String> createoutBoundData) throws Throwable {

		String caseNumber = "";
		String record = "";
		System.out.println("Create OutBound case");
		softAssert = new SoftAssert();
		String accountName = createoutBoundData.get("AccountName");
		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		selectAppOrObjFromAppLauncher("object", Constants.caseobjectfromlauncher);
		closeMultipleTabs();

		List<String> values = Collections.unmodifiableList(Arrays.asList(accountName));
		caseNumber = createCaseNumber("Outbound", values);
		loggerInfo("The newly created case details page of the case <b>" + caseNumber + "</b> is displayed", "No");
		record = driver.findElement(Cases.record).getText();
		Assert(record, "Outbound", "The case record type should be", "The case record type is", true);
		int size = driver.findElements(Cases.colSize).size();
		if (size == 3) {
			loggerPass("The number of sections/columns on outboundcase details page should be <b>" + size + "</b>",
					"The number of sections/columns on outboundcase details page is <b>" + size + "</b>", "Yes");
		} else {

			loggerFail("The number of sections/columns on outboundcase details page should be <b>" + size + "</b>",
					"The number of sections/columns on outboundcase details page is not <b>" + size + "</b>", true);

		}
		fieldValidation("Left Side section/column on Outbound case page", Constants.leftside);
		fieldValidation("Right Side section/column on Outbound case page", Constants.rightside);
		System.out.println("Create OutBound case completed successfully");
		softAssert.assertAll();
	}

	// Created by - Abhishek Palankar on 19-May-2023
	// This method is used to verify task widget in account page and in outbound
	// page
	public static void verifyOrderWidgetsInAccountPage(HashMap<String, String> accounts) throws Throwable {
		System.out.println("Verify order widget");
		String caseNumber = "";
		String firstOrderID = "";
		String OrderNumber = "";
		String Order_value = "";
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		boolean value = false;

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "AUTOZONE #4887";
			accountNo = "0601984036";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "0602184829";
			break;

		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		scrollToElement(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']"));
		isDisplayed(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']"), Constants.orderWidget + " widget");
		javaClickByLocator(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']"));
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.orderPage));
		Thread.sleep(3000);

		Order_value = GetText(Accounts.FirstOrder_Value);

		javaClickByLocator(Accounts.CloseOrdersBtn);
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']")));
		Thread.sleep(2000);
		javaClickByLocator(Accounts.casesWidget);
		javaClickByLocator(Accounts.newcase);
		javaClickByLocator(Accounts.outboundRadioBtn);
		javaClickByLocator(Accounts.next);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
		if (driver.findElements(Accounts.outboundPopup).size() > 0) {
			loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
			javaClickByLocator(Accounts.saveBtn);

			Thread.sleep(2000);
			if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
				javaClickByLocator(Accounts.closeSnag);
				scrollToElement(Cases.CSTPopup);
				fillFieldByLocator(Accounts.AccountNameField, accountName);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']"))
						.click();
				Thread.sleep(1000);
				javaClickByLocator(Accounts.saveBtn);
			}
			Thread.sleep(2000);
			
			if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
				javaClickByLocator(Accounts.closeSnag);
				javaClickByLocator(Accounts.saveBtn);
				Thread.sleep(2000);
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
				{	javaClickByLocator(Accounts.closeSnag);
				javaClickByLocator(Accounts.saveBtn);}
			}
			
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
			caseNumber = GetText(Accounts.caseNumberValue);
			Thread.sleep(2000);

			if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
				loggerPass("New Case should be created <b>" + caseNumber + "</b>",
						"New Case is created <b>" + caseNumber + "</b>", "Yes");

				scrollToElement(Accounts.orderForParentAccountWidget);
				isDisplayed(Accounts.orderForParentAccountWidget, "Order for Parent Account widget");
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.firstOrderId));
				firstOrderID = GetText(Accounts.firstOrderId);
				System.out.println("first Order number " + firstOrderID);
				javaClickByLocator(Accounts.orderWidgetViewBtn);
				Thread.sleep(2000);
				int size = driver.findElements(Accounts.orderForParentAccountWidgetSize).size();
				System.out.println("#####" + size);
				javaClickByLocator(Accounts.firstOrderId);

				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.OrderID));

				OrderNumber = GetText(Accounts.OrderID);
				System.out.println("Order number " + OrderNumber);
				if (OrderNumber.equalsIgnoreCase(firstOrderID)) {
					loggerPass("Orders should be opened by clicking on order ID",
							"Orders are opened by clicking on order ID", "Yes");
				} else {
					loggerFail("Orders should be opened by clicking on order ID",
							"Orders are not opened by clicking on order ID", true);
				}

				javaClickByLocator(By.xpath("//button[@title='Close " + firstOrderID + "']"));
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.firstOrderId));
				for (int i = 1; i <= size; i++) {
					String order_Number = driver.findElement(By.xpath(
							"(//div[@class='windowViewMode-maximized active lafPageHost']//div[text()='Order Number']//ancestor::thead//following-sibling::tbody//tr//a)["
									+ i + "]"))
							.getText();

					System.out.println("########" + order_Number);
					if (Order_value.equals(order_Number)) {
						value = true;
						break;
					}
				}
				if (value == true) {
					loggerPass("No. of orders on Account page and outbound page orders should be same",
							"No. of orders on Account page and outbound page orders are same", "No");
				} else {
					loggerFail("No. of orders on Account page and outbound page orders should be same",
							"No. of orders on Account page and outbound page orders are not same", true);
				}
			} else {
				loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
			}
		} else {
			loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page not displayed", true);
		}

		softAssert.assertAll();

	}

	// Created by - Abhishek Palankar on 22-May-2023
	// This method is used to verify task widget in account page and in outbound
	// page
	public static void verifyTaskWidgetsInAccountPage() throws Throwable {
		System.out.println("Verify task widget");
		String num = "";
		String caseNumber = "";
		String taskStatus = "";
		String taskName = "";
		String newTaskName = "";
		String accountName = "";
		String accountNo = "";
		String toast = "";
		softAssert = new SoftAssert();

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "DOMINOS PIZZA #5678";
			accountNo = "0501141465";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "BOSS BURGER";
			accountNo = "0500460905";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		scrollToElement(By.xpath("//span[text()='" + Constants.taskWidget + "']"));
		isDisplayed(By.xpath("//span[text()='" + Constants.taskWidget + "']"), Constants.taskWidget + " widget");
		javaClickByLocator(Cases.createTask);
		num = Integer.toString(getRandomInteger(100, 500));
		taskName = "Testing" + num;
		newTaskName = "Phone CallTesting" + num;

		fillFieldByLocator(Cases.subject, taskName);
		scrollToElement(Cases.priority);
		javaClickByLocator(Cases.saveBtn);
		toast = "Task " + "\"" + newTaskName + "\"" + " was created.";
		verifyToastMessage(Constants.foastMessageClassName, toast);
		javaClickByLocator(Accounts.casesWidget);
		javaClickByLocator(Accounts.newcase);
		javaClickByLocator(Accounts.outboundRadioBtn);
		javaClickByLocator(Accounts.next);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
		if (driver.findElements(Accounts.outboundPopup).size() > 0) {
			loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
			Thread.sleep(4000);
			javaClickByLocator(Accounts.saveBtn);

			Thread.sleep(2000);
			if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
				javaClickByLocator(Accounts.closeSnag);
				scrollToElement(Cases.CSTPopup);
				fillFieldByLocator(Accounts.AccountNameField, accountName);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']"))
						.click();
				Thread.sleep(1000);
				javaClickByLocator(Accounts.saveBtn);
			}
			if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
				javaClickByLocator(Accounts.closeSnag);
				javaClickByLocator(Accounts.saveBtn);
				Thread.sleep(2000);
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
				{	javaClickByLocator(Accounts.closeSnag);
				javaClickByLocator(Accounts.saveBtn);}
			}
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
			caseNumber = GetText(Accounts.caseNumberValue);
			System.out.println("Case number is " + caseNumber);
			Thread.sleep(2000);

			if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
				loggerPass("New Case should be created <b>" + caseNumber + "</b>",
						"New Case is created <b>" + caseNumber + "</b>", "Yes");
				scrollToElement(Cases.openTaskForParentAccountWidget);
				isDisplayed(Cases.openTaskForParentAccountWidget, "Open Tasks for Parent Account widget");
				javaClickByLocator(Cases.openTaskForParentAccountWidget);
				Thread.sleep(2000);
				if (driver.findElements(By.xpath(
						"//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='" + newTaskName + "']"))
						.size() > 0) {
					loggerPass(
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> should be displayed in outbound case",
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> is displayed in outbound case",
							"Yes");
					Thread.sleep(2000);
					new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='"
									+ newTaskName + "']//ancestor::td//following-sibling::td[3]")));
					taskStatus = GetText(
							By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='"
									+ newTaskName + "']//ancestor::td//following-sibling::td[3]"));

					if (taskStatus.equalsIgnoreCase("Open")) {
						loggerPass("Status should be <b>open</b> for newly created task in outbound case",
								"Status is <b>" + taskStatus + "</b> for newly created task in outbound case", "No");
					} else {
						loggerFail("Status should be <b>open</b> for newly created task in outbound case",
								"Status is <b>" + taskStatus + "</b> for newly created task in outbound case", true);
					}
				} else {
					loggerFail(
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> should be displayed in outbound case",
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> not displayed in outbound case",
							true);
				}
			} else {
				loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
			}
		} else {
			loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page not displayed", true);
		}

		softAssert.assertAll();

	}

	// Created by - Abhishek Palankar on 23-May-2023
	// This method is used to verify order, Task and alerts widgets in account page
	// and should apply only to OB case record type.
	public static void verifyOrderTaskAlertsWidgetsInOB(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify order, task and alert widgets");
		String caseNumber = "";
		String firstOrderID = "";
		String OrderNumber = "";
		String Order_value = "";
		String num = "";
		String taskStatus = "";
		String taskName = "";
		String newTaskName = "";
		String toast = "";
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		boolean value = false;

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "AUTOZONE #4887";
			accountNo = "0601984036";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		SelectAccountFromGlobalSearch(accountNo, accountName);
		scrollToElement(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']"));
		isDisplayed(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']"), Constants.orderWidget + " widget");
		javaClickByLocator(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']"));
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.orderPage));
		Thread.sleep(3000);
		Order_value = GetText(Accounts.FirstOrder_Value);

		javaClickByLocator(Accounts.CloseOrdersBtn);
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h2//span[text()='" + Constants.orderWidget + "']")));
		Thread.sleep(2000);
		scrollToElement(By.xpath("//span[text()='" + Constants.taskWidget + "']"));
		isDisplayed(By.xpath("//span[text()='" + Constants.taskWidget + "']"), Constants.taskWidget + " widget");
		javaClickByLocator(Cases.createTask);
		num = Integer.toString(getRandomInteger(100, 500));
		taskName = "Testing" + num;
		newTaskName = "Phone CallTesting" + num;

		fillFieldByLocator(Cases.subject, taskName);
		scrollToElement(Cases.priority);
		javaClickByLocator(Cases.saveBtn);
		toast = "Task " + "\"" + newTaskName + "\"" + " was created.";
		verifyToastMessage(Constants.foastMessageClassName, toast);
		scrollToElement(Accounts.casesWidget);
		javaClickByLocator(Accounts.casesWidget);
		javaClickByLocator(Accounts.newcase);
		javaClickByLocator(Accounts.outboundRadioBtn);
		javaClickByLocator(Accounts.next);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
		if (driver.findElements(Accounts.outboundPopup).size() > 0) {
			loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
			Thread.sleep(4000);
			javaClickByLocator(Accounts.saveBtn);

			Thread.sleep(2000);
			if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
				javaClickByLocator(Accounts.closeSnag);
				scrollToElement(Cases.CSTPopup);
				fillFieldByLocator(Accounts.AccountNameField, accountName);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']"))
						.click();
				Thread.sleep(1000);
				javaClickByLocator(Accounts.saveBtn);
			}
			if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
				javaClickByLocator(Accounts.closeSnag);
				javaClickByLocator(Accounts.saveBtn);
				Thread.sleep(2000);
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
				{	javaClickByLocator(Accounts.closeSnag);
				javaClickByLocator(Accounts.saveBtn);}
			}
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
			caseNumber = GetText(Accounts.caseNumberValue);
			System.out.println("Case number is " + caseNumber);
			Thread.sleep(2000);

			if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
				loggerPass("New Case should be created <b>" + caseNumber + "</b>",
						"New Case is created <b>" + caseNumber + "</b>", "Yes");
				Thread.sleep(1000);

				scrollToElement(Accounts.orderForParentAccountWidget);
				isDisplayed(Accounts.orderForParentAccountWidget, "Order for Parent Account widget");
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.firstOrderId));
				firstOrderID = GetText(Accounts.firstOrderId);
				System.out.println("first Order number " + firstOrderID);
				javaClickByLocator(Accounts.orderWidgetViewBtn);

				Thread.sleep(2000);

				int size = driver.findElements(Accounts.orderForParentAccountWidgetSize).size();
				System.out.println("#####" + size);
				javaClickByLocator(Accounts.firstOrderId);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.OrderID));

				OrderNumber = GetText(Accounts.OrderID);
				System.out.println("Order number " + OrderNumber);

				if (OrderNumber.equalsIgnoreCase(firstOrderID)) {
					loggerPass("Orders should be opened by clicking on order ID",
							"Orders are opened by clicking on order ID", "Yes");
				} else {
					loggerFail("Orders should be opened by clicking on order ID",
							"Orders are not opened by clicking on order ID", true);
				}

				javaClickByLocator(By.xpath("//button[@title='Close " + firstOrderID + "']"));
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.firstOrderId));
				for (int i = 1; i <= size; i++) {
					String order_Number = driver.findElement(By.xpath(
							"(//div[@class='windowViewMode-maximized active lafPageHost']//div[text()='Order Number']//ancestor::thead//following-sibling::tbody//tr//a)["
									+ i + "]"))
							.getText();

					System.out.println("########" + order_Number);
					if (Order_value.equals(order_Number)) {
						value = true;
						break;
					}
				}
				if (value == true) {
					loggerPass("No. of orders on Account page and outbound page orders should be same",
							"No. of orders on Account page and outbound page orders are same", "No");
				} else {
					loggerFail("No. of orders on Account page and outbound page orders should be same",
							"No. of orders on Account page and outbound page orders are not same", true);
				}

				javaClickByLocator(Accounts.closeOrdersForParent);

				scrollToElement(Cases.openTaskForParentAccountWidget);
				isDisplayed(Cases.openTaskForParentAccountWidget, "Open Tasks for Parent Account widget");
				javaClickByLocator(Cases.openTaskForParentAccountWidget);
				if (driver.findElements(By.xpath(
						"//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='" + newTaskName + "']"))
						.size() > 0) {
					loggerPass(
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> should be displayed in outbound case",
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> is displayed in outbound case",
							"Yes");
					new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='"
									+ newTaskName + "']//ancestor::td//following-sibling::td[3]")));
					Thread.sleep(2000);
					taskStatus = GetText(
							By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='"
									+ newTaskName + "']//ancestor::td//following-sibling::td[3]"));
					if (taskStatus.equalsIgnoreCase("Open")) {
						loggerPass("Status should be <b>open</b> for newly created task in outbound case",
								"Status is <b>" + taskStatus + "</b> for newly created task in outbound case", "No");
					} else {
						loggerFail("Status should be <b>open</b> for newly created task in outbound case",
								"Status is <b>" + taskStatus + "</b> for newly created task in outbound case", true);
					}
				} else {
					loggerFail(
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> should be displayed in outbound case",
							"Newly created task (From account) <b>" + newTaskName
									+ "</b> not displayed in outbound case",
							true);
				}
			} else {
				loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
			}
		} else {
			loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page not displayed", true);
		}

		softAssert.assertAll();

	}

	// Created by - Niranjan H on 13-jun-2023
	// Verify that user should see Marketing Attributes and other Customer
	// attributes on the Outbound Case page Layout
	public static void Verify_AccountaAttributesOn_OBCase(HashMap<String, String> cases) throws Throwable {
		System.out.println("Verify Accounts Attributes on Case Page");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "DOMINOS #4452";
			accountNo = "0602426529";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "AVON PARK HIGH SCHOOL";
			accountNo = "0500121742";
			break;
		case "4600":
			accountName = "DOMINOS #8585";
			accountNo = "0601609054";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		Thread.sleep(4000);
		fieldValidation("Marketing Attributes & Other Attributes", Constants.OBcase_AccountAttribute);
		List<String> Field_Values = new ArrayList();
		for (int i = 0; i < Constants.OBcase_AccountAttribute.size(); i++) {
			String Field_Name = Constants.OBcase_AccountAttribute.get(i);
			String value = driver
					.findElement(By.xpath("//h3/following-sibling::div//records-record-layout-item[@field-label='"
							+ Field_Name + "']//lightning-formatted-text"))
					.getText();
			Field_Values.add(value);
		}

		javaClickByLocator(Accounts.casesWidget);
		Thread.sleep(3000);
		javaClickByLocator(Accounts.newcase);
		javaClickByLocator(Accounts.outboundRadioBtn);
		Thread.sleep(1000);
		javaClickByLocator(Accounts.next);
		Thread.sleep(1000);
		isDisplayed(Accounts.outboundPopup, "Outbound case");
		javaClickByLocator(Accounts.saveBtn);
		verifyToastMessage("Case was created");
		Thread.sleep(2000);
		logger.info("Outbound Case Number : <b>" + GetText(Accounts.caseNumberValue) + "</b>");

		javaClickByLocator(Cases.RelatedTab);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Cases.customerinstruction));
		// TC-248513
		isDisplayed(Cases.AccountAttribute, "Account Attributes");
		// TC-248515, 248516, 248830
		fieldValidation("Account Attributes", Constants.OBcase_AccountAttribute);
		System.out.println("All Required feilds available in Account Attributes");

		// TC-248514
		List<String> present = new ArrayList<String>();
		List<String> notPresent = new ArrayList<String>();
		for (String fieldName : Constants.OBcase_AccountAttribute) {

			if (driver
					.findElements(By.xpath("//article//span[text()='" + fieldName
							+ "']/ancestor::records-record-layout-item//span[contains(@class,'is-read-only')]"))
					.size() != 0) {
				present.add(fieldName);

			} else {
				notPresent.add(fieldName);
			}
		}
		noneditFieldValidation(present, notPresent, "Account Attribute");
		present.clear();
		notPresent.clear();
		System.out.println("Editable feilds validation complete");

		// Fields Data Validation
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//runtime_platform_actions-lwc-quick-action-layout//span[text()='"
						+ Constants.OBcase_AccountAttribute.get(0) + "']")));
		for (int i = 0; i < Constants.OBcase_AccountAttribute.size(); i++) {
			WebElement OB_feildvalue = driver.findElement(By.xpath("//article//span[text()='"
					+ Constants.OBcase_AccountAttribute.get(i)
					+ "']/ancestor::records-record-layout-item//span[contains(@class,'is-read-only')]//lightning-formatted-text"));

			if (OB_feildvalue.getText().contains(Field_Values.get(i))) {
				present.add(Constants.OBcase_AccountAttribute.get(i) + ": " + Field_Values.get(i));
			} else {
				notPresent.add(Constants.OBcase_AccountAttribute.get(i) + ": Detail Not Found");
			}
		}

		String prevalue = "";
		String nonPrevalue = "";

		if (!present.isEmpty()) {
			for (String value : present) {
				prevalue += value + ", ";
			}
			loggerPass(
					"Field Values On Outbound Case Page <b>'" + prevalue.substring(0, prevalue.length() - 2)
							+ "</b> should be displayed ",
					"Field Values On Outbound Case Page <b>" + prevalue.substring(0, prevalue.length() - 2)
							+ "</b> are displayed successfully",
					"No");
		}

		if (!notPresent.isEmpty()) {
			for (String value : notPresent) {
				nonPrevalue += value + ", ";
			}
			loggerFail(
					"Field Values On Outbound Case Page <b>" + nonPrevalue.substring(0, nonPrevalue.length() - 2)
							+ "</b> should be displayed",
					"Field Values On Outbound Case Page <b>" + nonPrevalue.substring(0, nonPrevalue.length() - 2)
							+ " are not displayed",
					true);
		}

		softAssert.assertAll();

	}

	// Created by - Abhishek Palankar on 14-June-2023
	// This method is used to verify Chatter for CST
	public static void validateChatterForCST() throws Exception {
		System.out.println("Validate Chatter");
		softAssert = new SoftAssert();
		List<String> values = new ArrayList<String>();
		String accountName = "";
		String accountNo = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "DOMINOS PIZZA #5678";
			accountNo = "0501141465";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		switch (Constants.bottler) {
		case "4100":
			values = Constants.values_4100;
			break;
		case "4200":
			values = Constants.values_4200;
			break;
		case "4400":
			values = Constants.values_4400;
			break;
		case "4500":
			values = Constants.values_4500;
			break;
		case "4600":
			values = Constants.values_4600;
			break;
		case "4700":
			values = Constants.values_4700;
			break;
		case "4800":
			values = Constants.values_4800;
			break;
		case "4900":
			values = Constants.values_4900;
			break;
		case "5000":
			values = Constants.values_5000;
			break;
		case "5200":
			values = Constants.values_5200;
			break;
		case "5300":
			values = Constants.values_5300;
			break;

		}
		String callType = values.get(0);
		String CSTProblemReported = values.get(3);
		String detailDescription = values.get(2);
		CSTCaseCreation(callType, CSTProblemReported, detailDescription, accountName);

		softAssert.assertAll();

	}

	// Created by - Abhishek Palankar on 14-June-2023
	// This method is used to create CST,verify Chatter and search functionality
	public static void CSTCaseCreation(String callType, String CSTProblemReported, String detailDescription,
			String accountName) throws Exception {
		String caseNumber = "";
		String num = "";
		String text = "";
		javaClickByLocator(Accounts.casesWidget);
		Thread.sleep(4000);
		javaClickByLocator(Accounts.newcase);
		if (driver.findElements(Cases.newCase).size() > 0) {
			loggerPass("Different case record types list should be display",
					"Different case record types list is displayed", "Yes");
			javaClickByLocator(Cases.CSTbtn);
			javaClickByLocator(Accounts.next);
			Thread.sleep(3000);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Cases.CSTPopup));
			if (driver.findElements(Cases.CSTPopup).size() > 0) {
				loggerPass("New Case: Customer Service Ticket page layout should be display",
						"New Case: Customer Service Ticket page layout displayed", "No");
				javaClickByLocator(Cases.callType);

				javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));

				driver.findElement(Accounts.detailedDescriptionTextBox).click();
				driver.findElement(Accounts.detailedDescriptionTextBox).sendKeys(detailDescription);

				javaClickByLocator(Cases.CSTProblemReported);

				javaClickByLocator(
						By.xpath("//lightning-base-combobox-item[@data-value='" + CSTProblemReported + "']"));

				javaClickByLocator(Accounts.saveBtn);
				Thread.sleep(2000);
				if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					scrollToElement(Cases.CSTPopup);
					fillFieldByLocator(Accounts.AccountNameField, accountName);
					Thread.sleep(1000);
					driver.findElement(
							By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")).click();
					Thread.sleep(1000);
					javaClickByLocator(Accounts.saveBtn);
				}
				Thread.sleep(3000);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Cases.assignedToField));
				caseNumber = GetText(Cases.caseNo);
				if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
					loggerPass("New Case should be created <b>" + caseNumber + "</b>",
							"New Case is created <b>" + caseNumber + "</b>", "Yes");
					if (driver.findElements(Cases.DetailsTab).size() > 0) {
						loggerPass("Details tab should be display in case page",
								"Details tab is displayed in case page", "No");
						// TC - 248519
						if (driver.findElements(Cases.chatterTab).size() > 0) {
							loggerPass("Chatter tab should be display in case page",
									"Chatter tab is displayed in case page", "No");
							javaClickByLocator(Cases.chatterTab);
							Thread.sleep(1000);
							javaClickByLocator(Cases.postBtn);
//							try {
//								if (driver.findElement(Cases.moreTab).isDisplayed()) {
//									javaClickByLocator(Cases.moreTab);
//									Thread.sleep(2000);
//									javaClickByLocator(Cases.postBtnMoreTab);
//								}
//							} catch (Exception e) {
//								javaClickByLocator(Cases.postBtn);
//							}
							Thread.sleep(1000);

							javaClickByLocator(Cases.shareAnUpdateTextBox);
							num = Integer.toString(getRandomInteger(100, 500));
							text = "Testing" + num;
							driver.findElement(Cases.shareAnUpdateTextBox).sendKeys(text);
							Thread.sleep(2000);

							javaClickByLocator(Cases.shareBtn);
							Thread.sleep(15000);
							driver.findElement(By.xpath("//button[@title='Actions for " + caseNumber + " | Case']"))
									.click();
							Thread.sleep(2000);
							driver.findElement(Cases.refreshTab).click();

//							Thread.sleep(3000);
//							driver.findElement(By.xpath("//button[@title='Actions for " + caseNumber + " | Case']")).click();
//							Thread.sleep(2000);
//							driver.findElement(Cases.refreshTab).click();

//							Thread.sleep(5000);
//							scrollToElement(By.xpath("//span[text()='" + text + "']"));
//							driver.findElement(Cases.searchThisFeed).click();
//							Thread.sleep(2000);
//							driver.findElement(Cases.searchThisFeed).sendKeys(text);
//							Thread.sleep(2000);
//							new Actions(driver).sendKeys(driver.findElement(Cases.searchThisFeed), Keys.ENTER).build()
//									.perform();
							Thread.sleep(2000);
							// driver.findElement(Cases.searchThisFeed).sendKeys(Keys.ENTER);
							// Thread.sleep(2000);
							new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//span[text()='" + text + "']")));
							scrollToElement(By.xpath("//span[text()='" + text + "']"));
							if (driver.findElements(By.xpath("//span[text()='" + text + "']")).size() > 0) {
								loggerPass("User should be able to search for most recently viewed activity",
										"User is able to search for most recently viewed activity", "Yes");
							} else {
								loggerFail("User should be able to search for most recently viewed activity",
										"User not able to search for most recently viewed activity", true);
							}
						} else {
							loggerFail("Chatter tab should be display in case page",
									"Chatter tab not displayed in case page", true);
						}

					} else {
						loggerFail("Details tab should be display in case page",
								"Details tab not displayed in case page", true);
					}
				} else {
					loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
				}

			} else {
				loggerFail("New Case: Customer Service Ticket page layout should be display",
						"New Case: Customer Service Ticket page layout not displayed", true);
			}

		} else {
			loggerFail("Different case record types list should be display",
					"Different case record types list is displayed", true);
		}

	}

	// Created by - Abhishek Palankar on 14-June-2023
	// This method is used to verify Chatter for OB
	public static void validateChatterForOB() throws Exception {
		System.out.println("Validate Chatter for OB");
		softAssert = new SoftAssert();
		String caseNumber = "";
		String num = "";
		String text = "";
		String accountName = "";
		String accountNo = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "STANLEY INDUSTRIAL S";
			accountNo = "0602006081";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "BOSS BURGER";
			accountNo = "0500460905";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		javaClickByLocator(Accounts.casesWidget);
		javaClickByLocator(Accounts.newcase);

		if (driver.findElements(Cases.newCase).size() > 0) {
			loggerPass("Different case record types list should be display",
					"Different case record types list is displayed", "Yes");
			Thread.sleep(5000);
			
			javaClickByLocator(Accounts.outboundRadioBtn);
			Thread.sleep(3000);
			javaClickByLocator(Accounts.next);
			Thread.sleep(3000);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
			Thread.sleep(3000);

			if (driver.findElements(Accounts.outboundPopup).size() > 0) {
				loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");

				Thread.sleep(3000);
				if (driver.findElements(Accounts.AccountNameField).size() > 0) {
					fillFieldByLocator(Accounts.AccountNameField, accountName + Keys.ENTER);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")))
							.click();
				}

				javaClickByLocator(Accounts.saveBtn);
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);
					Thread.sleep(2000);
					if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
					{	javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);}
				}
				Thread.sleep(10000);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
				caseNumber = GetText(Accounts.caseNumberValue);
				Thread.sleep(5000);

				if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
					loggerPass("New Case should be created <b>" + caseNumber + "</b>",
							"New Case is created <b>" + caseNumber + "</b>", "Yes");

					if (driver.findElements(Cases.DetailsTabOB).size() > 0) {
						loggerPass("Details tab should be display in case page",
								"Details tab is displayed in case page", "No");
						if (driver.findElements(Cases.chatterTabOB).size() > 0) {
							loggerPass("Chatter tab should be display in case page",
									"Chatter tab is displayed in case page", "No");
							javaClickByLocator(Cases.chatterTabOB);
							Thread.sleep(1000);

							if (driver.findElements(Cases.moreTab).size() > 0) {

								javaClickByLocator(Cases.moreTab);
								Thread.sleep(2000);
								javaClickByLocator(Cases.postBtnMoreTab);

							} else {

								javaClickByLocator(Cases.postBtn);

							}

							Thread.sleep(1000);
							// javaClickByLocator(Cases.shareAnUpdate);
							num = Integer.toString(getRandomInteger(100, 500));
							text = "Testing" + num;
							driver.findElement(Cases.shareAnUpdateTextBox).sendKeys(text);

							javaClickByLocator(Cases.shareBtn);
							Thread.sleep(15000);
							driver.findElement(By.xpath("//button[@title='Actions for " + caseNumber + " | Case']"))
									.click();
							Thread.sleep(2000);
							driver.findElement(Cases.refreshTab).click();

//								Thread.sleep(5000);
//								driver.findElement(By.xpath("//button[@title='Actions for " + caseNumber + " | Case']"))
//										.click();
//								Thread.sleep(2000);
//								driver.findElement(Cases.refreshTab).click();
//
//								Thread.sleep(5000);
//								driver.findElement(Cases.searchThisFeed).click();
//								driver.findElement(Cases.searchThisFeed).sendKeys(text);
//								Thread.sleep(5000);
//								new Actions(driver).sendKeys(driver.findElement(Cases.searchThisFeed), Keys.ENTER)
//										.build().perform();
//								Thread.sleep(3000);
//								salesforce_ScrollDown(2);
							Thread.sleep(2000);
							new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//span[text()='" + text + "']")));
							scrollToElement(By.xpath("//span[text()='" + text + "']"));
							if (driver.findElements(By.xpath("//span[text()='" + text + "']")).size() > 0) {
								loggerPass("User should be able to search for most recently viewed activity",
										"User is able to search for most recently viewed activity", "Yes");
							} else {
								loggerFail("User should be able to search for most recently viewed activity",
										"User not able to search for most recently viewed activity", true);
							}
						} else {
							loggerFail("Chatter tab should be display in case page",
									"Chatter tab not displayed in case page", true);
						}
					} else {
						loggerFail("Details tab should be display in case page",
								"Details tab not displayed in case page", true);
					}
				} else {
					loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
				}
			} else {
				loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page not displayed", true);
			}
		} else {
			loggerFail("Different case record types list should be display",
					"Different case record types list is displayed", true);
		}

		softAssert.assertAll();

	}

// Created by - Niranjan H on 12-Jul-2023
	// Verify that the “Other Contact Type” picklist is updated to include Bottler,
	// Consumer and NAOU")
	public void Verify_OtherContactPicklist() throws Exception {
		System.out.println("Verify that the “Other Contact Type” picklist is updated");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		List<String> createcst;

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "DOMINOS #5550";
			accountNo = "0602423591";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "DOMINOS #8134";
			accountNo = "0601651113";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #3969";
			accountNo = "0601401743";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "BOSS BURGER";
			accountNo = "0500460905";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		selectAppOrObjFromAppLauncher("Object", Constants.caseobjectfromlauncher);
		closeMultipleTabs();
		createcst = Collections
				.unmodifiableList(Arrays.asList(accountName, create_cst.get(0), create_cst.get(1), create_cst.get(2)));
		createCaseNumber("Customer Service Ticket", createcst);
		Thread.sleep(1000);
		scrollToElement(Cases.OtherContactTypefield);
		javaClickByLocator(Cases.OtherContactTypefield);
		Thread.sleep(1000);
		javaClickByLocator(Cases.OtherContactType);
		Thread.sleep(500);
		isDisplayedWithScreenshot(Cases.OtherContactType, "Other Contact Picklist", "yes");
		for (String field : Cases.ExpectedValues) {
			if (driver.findElements(By.xpath(
					"//label[text()='Other Contact Type']/ancestor::lightning-combobox//lightning-base-combobox-item//span[text()='"
							+ field + "']"))
					.size() != 0) {
				loggerPass("PickList value <b>" + field + "</b> Should be displayed",
						"PickList value <b>" + field + "</b> is displayed", "No");
			} else {
				loggerFail("PickList value <b>" + field + "</b> Should be displayed",
						"PickList value <b>" + field + "</b> Not displayed", true);
			}
		}

		softAssert.assertAll();

	}

	// Created by - Niranjan H on 27-Jul-2023
	// Verify that the CST Problem Reported codes should have new values qualified,
	// new customer and follow up needed
	public static void VerifyCSTProblemReported(HashMap<String, String> CStProblem) throws Exception {
		System.out.println("Validate CST Code for values \"qualified, new customer\" and \"follow up needed\"");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		List<String> PicklistValues;
		List<String> CVMPicklistValues;
		String callType = CStProblem.get("Call Type");
		String Follow_Up = CStProblem.get("Follow_Up");
		String Qualified_New_Customer = CStProblem.get("Qualified New Customer");
		if (Constants.bottler != "4500") {
			logger.info("This Method Only works for 4500 Bottler");
			System.out.println("This Method Only works for 4500 Bottler");
			System.exit(0);
		} else {

			switch (Constants.bottler) {
			case "4500":
				accountName = "DOMINOS PIZZA #5189";
				accountNo = "0600782158";
				break;
			}

			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
			closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			javaClickByLocator(Accounts.casesWidget);
			Thread.sleep(1000);
			javaClickByLocator(Accounts.newcase);
			Thread.sleep(3000);
			javaClickByLocator(Cases.CSTbtn);
			Thread.sleep(5000);
			javaClickByLocator(Cases.Casenext);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Cases.callType));
			scrollToElement(Cases.callType);
			javaClickByLocator(Cases.callType);
			isDisplayed(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"), "Prospecting");
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));
			Thread.sleep(1000);
			javaClickByLocator(Cases.CSTProblemReported);
			Thread.sleep(1000);
			List<WebElement> ProblemReported_Picklist = driver.findElements(Cases.CSTProblemReported_Picklist);
			PicklistValues = new ArrayList<String>();
			for (WebElement Picklist_value : ProblemReported_Picklist) {
				PicklistValues.add(Picklist_value.getText());
			}

			if (PicklistValues.contains(Follow_Up)) {
				loggerPass("CST Problem Reported Code Should Contain <b>" + Follow_Up + "</b>",
						"CST Problem Reported Code Contains <b>" + Follow_Up + "</b>", "No");
			} else {
				loggerFail("CST Problem Reported Code Should Contain <b>" + Follow_Up + "</b>",
						"CST Problem Reported Code Not Contains <b>" + Follow_Up + "</b>", false);
			}

			if (PicklistValues.contains(Qualified_New_Customer)) {
				loggerPass("CST Problem Reported Code Should Contain <b>" + Qualified_New_Customer + "</b>",
						"CST Problem Reported Code Contains <b>" + Qualified_New_Customer + "</b>", "yes");
			} else {
				loggerFail("CST Problem Reported Code Should Contain <b>" + Qualified_New_Customer + "</b>",
						"CST Problem Reported Code Not Contains <b>" + Qualified_New_Customer + "</b>", true);
			}

			javaClickByLocator(Cases.CancelCase);
			if (driver.findElements(CST_Routing.discardbtn).size() > 0) {
				driver.findElement(CST_Routing.discardbtn).click();
			}
			logOut();

			// CVM
			login("UserName_OffshoreBottler", "Password_OffshoreBottler");
			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CVMobjectfromlauncher);
			javaClickByLocator(CST_Routing.New);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.Newcodevaluemapping, "CVM records");
			javaClickByLocator(CST_Routing.CSTMetaData);
			javaClickByLocator(Accounts.next);
			Thread.sleep(1000);
			isDisplayed(CST_Routing.CSTMetaDataPage, "New Code Value Mapping: CST Metadata");
			javaClickByLocator(Cases.callType);
			isDisplayed(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"), "Prospecting");
			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));
			Thread.sleep(1000);
			javaClickByLocator(Cases.CSTProblemReported);
			Thread.sleep(1000);
			List<WebElement> CVMProblemReported_Picklist = driver.findElements(Cases.CSTProblemReported_Picklist);
			CVMPicklistValues = new ArrayList<String>();
			for (int i = 0; i <= CVMProblemReported_Picklist.size(); i++) {
				if (i == CVMProblemReported_Picklist.size()) {
					loggerFail("On CST Metadata page CST Problem Reported Code Should Contain <b>" + Follow_Up + "</b>",
							"On CST Metadata page CST Problem Reported Code Not Contains <b>" + Follow_Up + "</b>",
							true);
				} else if (CVMProblemReported_Picklist.get(i).getText().equalsIgnoreCase(Follow_Up)) {
					javaClickByWebElement(CVMProblemReported_Picklist.get(i));
					loggerPass("On CST Metadata page CST Problem Reported Code Should Contain <b>" + Follow_Up + "</b>",
							"On CST Metadata page CST Problem Reported Code Contains <b>" + Follow_Up + "</b>", "yes");
					break;
				}
			}
			javaClickByLocator(Cases.CSTProblemReported);
			Thread.sleep(1000);

			for (int i = 0; i <= CVMProblemReported_Picklist.size(); i++) {
				if (i == CVMProblemReported_Picklist.size()) {
					loggerFail(
							"On CST Metadata page CST Problem Reported Code Should Contain <b>" + Qualified_New_Customer
									+ "</b>",
							"On CST Metadata page CST Problem Reported Code Not Contains <b>" + Qualified_New_Customer
									+ "</b>",
							true);
				} else if (CVMProblemReported_Picklist.get(i).getText().equalsIgnoreCase(Qualified_New_Customer)) {
					javaClickByWebElement(CVMProblemReported_Picklist.get(i));
					loggerPass(
							"On CST Metadata page CST Problem Reported Code Should Contain <b>" + Qualified_New_Customer
									+ "</b>",
							"On CST Metadata page CST Problem Reported Code Contains <b>" + Qualified_New_Customer
									+ "</b>",
							"Yes");
					break;
				}
			}

		}
		softAssert.assertAll();

	}

//// Created by - Niranjan H on 25-Sep-23
//	//Verify that when SPID is null OR ECVM = FALSE then on the EST Case the Relevant Fee for charge should display Out of Scope
//	 public static void Verify_RelaventFeeCharge_outofscope(HashMap<String, String> CStProblem) throws Throwable {
//			System.out.println("Verify That SPID is null OR ECVM = FALSE Then Relevant Fee For Charge On EST Case Is  \"Out of Scope\"");
//			softAssert = new SoftAssert();
//			String Expected_FeeCharge =CStProblem.get("ExpectedValue") ;
//			String Acual_FeeCharge="";
//			String accountName = "";
//			String accountNo = "";
//			String Cvm = "";
//			String Equipment_Type="";
//			String Equipment_TypeDetail="";
//			String EST_ProblemReported="";
//			List <String> CreateEST;
//			boolean SPID = true  ;
//			boolean ECVM = true ;
//			
//			
//			switch (Constants.bottler) {
//			case "4100":
//				accountName = "FIVE STAR FOOD SERVICE INC";
//				accountNo = "0500004781";
//				Cvm="CVM-0005772";
//				break;
//			case "4200":
//				accountName = "DOMINOS #5550";
//				accountNo = "0602423591";
//				Cvm="CVM-0004069";
//				break;
//			case "4400":
//				accountName = "1984 CLASSIC ARCADE";
//				accountNo = "0500287109";
//				Cvm="CVM-0009448";
//				break;
//			case "4500":
//				accountName = "DOMINOS PIZZA #5189";
//				accountNo = "0600782158";
//				Cvm="CVM-0005632";
//				break;
//			case "4600":
//				accountName = "DOMINOS #8134";
//				accountNo = "0601651113";
//				Cvm="CVM-0006044";
//				break;
//			case "4700":
//				accountName = "DOMINOS PIZZA #1632";
//				accountNo = "0600940258";
//				Cvm="CVM-0000404";
//				break;
//			case "4800":
//				accountName = "DOLLAR GENERAL #11614";
//				accountNo = "0600608462";
//				Cvm="CVM-0003188";
//				break;
//			case "4900":
//				accountName = "DOMINOS #3969";
//				accountNo = "0601401743";
//				Cvm="CVM-0000958";
//				break;
//			case "5000":
//				accountName = "BIG Y #0108";
//				accountNo = "0601731108";
//				Cvm="CVM-0008747";
//				break;
//			case "5200":
//				accountName = "BOSS BURGER";
//				accountNo = "0500460905";
//				Cvm="CVM-0002635";
//				break;
//			case "5300":
//				accountName ="ABBY CONVENIENCE STORE";
//				accountNo ="602184829";
//				Cvm="CVM-0003346";
//				break;
//			}
//			
//			 selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
//			 closeMultipleTabs();
//			 selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
//			 SelectAccountFromGlobalSearch(accountNo, accountName);
//			 scrollToElement(Cases.ServiceData);
//			 Thread.sleep(1000);
//			 new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Cases.ProgramID));
//
//			 isDisplayed(Cases.ProgramID, "Service Program ID (SPID)");
//		    if(driver.findElement(Cases.ProgramIDValue).getText().isBlank()==true) 
//			{
//				 SPID = false;
//			     loggerInfo("Service Program ID (SPID) is Null", "Yes");
//				
//			}else {
//				 SPID = true;
//				 loggerInfo("Service Program ID (SPID) is Not Null", "Yes");
//				 
//			}
//			 	
//		    
//		    logOut();
//		    Thread.sleep(2000);
//		    login("UserName_OffshoreBottler", "Password_OffshoreBottler");
//		    selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
//			closeMultipleTabs();
//			selectAppOrObjFromAppLauncher("Object", Constants.CVMobjectfromlauncher);
//			javaClickByLocator(Cases.CVM_ListView);
//			javaClickByLocator(Cases.All_ListView);
//			Thread.sleep(2000);
//			driver.findElement(Cases.Cvmsearch).sendKeys(Cvm+Keys.ENTER);
//			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='"+Cvm+"']")));
//			javaClickByLocator(By.xpath("//a[@title='"+Cvm+"']"));
//			
//			Thread.sleep(2000);
//			Equipment_Type=GetText(Cases.EquipmentType);
//			Equipment_TypeDetail=GetText(Cases.EquipmentDetails);
//			EST_ProblemReported=GetText(Cases.ESTProblemReported);
//			
//			CreateEST=new ArrayList<String>();
//			CreateEST.add(accountName);
//			CreateEST.add(Equipment_Type);
//			CreateEST.add(EST_ProblemReported);
//			CreateEST.add("Test-"+generateRandomInteger());
//			CreateEST.add(Equipment_TypeDetail);
//			
//			
//			
//			try {
//				if (driver.findElements(Cases.BillableCheckBox).size() > 0) {
//					loggerInfo("On CVM Billable Checkbox Is <b>Checked</b>", "yes");
//					ECVM =true;
//				}else {
//					loggerInfo("On CVM Billable Checkbox Is <b>Unchecked</b>", "yes");
//					ECVM = false;
//				} 
//			} catch (Exception e) {
//				loggerInfo("On CVM Billable Checkbox Is <b>Unchecked</b>", "yes");
//				ECVM = false;
//			}
//			
//			closeMultipleTabs();
//			selectAppOrObjFromAppLauncher("Object", Constants.caseobjectfromlauncher);
//			createCaseNumber("Equipment Service Ticket", CreateEST);			
//			
//			Thread.sleep(2000);
//
//			isDisplayed(Cases.RelaventForFeeCharge, "Relavent For Fee Charge");
//			Acual_FeeCharge = GetText(Cases.RelaventForFeeChargeValue);
//			salesforce_ScrollUp(1);
//			if(SPID==false || ECVM == false) 
//			{
//				String result = "Null";
//				if(SPID==true) {result = "Not Null";}
//				if(Acual_FeeCharge.equalsIgnoreCase(Expected_FeeCharge)) 
//				{loggerPass("IF <b>Service Program ID (SPID)</b> is <b>Null</b> And <b>Billable Checkbox On ECVM</b> is <b>False</b> Then Relavent For Fee Charge should display with <b>Out of Scope</b> value" ,
//						"<b>Service Program ID (SPID)</b> is <b>"+result+"</b> And <b>Billable Checkbox On ECVM</b> is <b>"+ECVM+"</b> Relavent For Fee Charge field displayed with <b>Out of Scope</b> value", "yes");}
//				else {
//					loggerFail("IF <b>Service Program ID (SPID)</b> is <b>Null</b> And <b>Billable Checkbox On ECVM</b> is <b>False</b> Then Relavent For Fee Charge should display with <b>Out of Scope</b> value", 
//							"Relavent For Fee Charge Is Not displayed with <b>Out of Scope</b> value", true);
//				}
//			}
//			softAssert.assertAll();
//		}

	// Created by - Niranjan H on 30-11-23
	// Call List Name field in the header of the case next to Case Origin
	public static void Verify_CallList_Name(HashMap<String, String> reports) throws Throwable {
		System.out.println("Verify Call List Name");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		String caseNumber = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "DOLLAR GENERAL #14315";
			accountNo = "0500019285";
			break;
		case "4200":
			accountName = "AUTOZONE #4887";
			accountNo = "0601984036";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "BOSS BURGER";
			accountNo = "0500460905";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}
		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		javaClickByLocator(Accounts.casesWidget);
		javaClickByLocator(Accounts.newcase);
		if (driver.findElements(Cases.newCase).size() > 0) {
			loggerPass("Different case record types list should be display",
					"Different case record types list is displayed", "Yes");
			Thread.sleep(5000);
			javaClickByLocator(Accounts.outboundRadioBtn);
			Thread.sleep(5000);
			javaClickByLocator(Accounts.next);
			Thread.sleep(3000);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));

			if (driver.findElements(Accounts.outboundPopup).size() > 0) {
				loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
				Thread.sleep(3000);
				if (driver.findElements(Accounts.AccountNameField).size() > 0) {
					fillFieldByLocator(Accounts.AccountNameField, accountName + Keys.ENTER);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")))
							.click();
				}
				Thread.sleep(1000);
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);
					Thread.sleep(2000);
					if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
					{	javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);}
				}
				javaClickByLocator(Accounts.saveBtn);
			} else {
				loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is Not displayed", true);

			}
		}

		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
		caseNumber = GetText(Accounts.caseNumberValue);
		System.out.println("Case number is " + caseNumber);

		Thread.sleep(10000);
		caseNumber = GetText(Accounts.caseNumberValue);
		Thread.sleep(5000);

		if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
			loggerPass("New Case should be created <b>" + caseNumber + "</b>",
					"New Case is created <b>" + caseNumber + "</b>", "Yes");

			if (driver.findElements(Cases.DetailsTabOB).size() > 0) {
				loggerPass("Details tab should be display in case page", "Details tab is displayed in case page", "No");
			} else {
				loggerFail("Details tab should be display in case page", "Details tab not displayed in case page",
						true);
			}

			if (driver.findElements(Cases.CallListName).size() > 0) {
				loggerPass("Call List Name should be display on Outbound case page",
						"Call List Name is displayed in Outbound case page", "yes");
			} else {
				loggerFail("Call List Name should be display on Outbound case page",
						"Call List Name is not displayed in Outbound case page", true);
			}

		} else {
			loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
		}

	}

	// Created by - Niranjan H on 06-12-23
	// visibility of the Message Field on the case
	public static void Verify_MessageField(HashMap<String, String> reports) throws Throwable {
		System.out.println("Visibility of the Message Field on the case");

		String accountName = "";
		String accountNo = "";
		String caseNumber = "";
		String TestMessage = "";
		String Actual_Message = "";
		String MessageOnRelatedList = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "AUTOZONE #4887";
			accountNo = "0601984036";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			break;
		case "4600":
			accountName = "AUTOZONE #4534";
			accountNo = "0500162746";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			break;
		case "5200":
			accountName = "BOSS BURGER";
			accountNo = "0500460905";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}

		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		javaClickByLocator(Accounts.casesWidget);
		isDisplayed(Cases.RelatedList_Message, "Message field on the case related list");
		javaClickByLocator(Accounts.newcase);
		if (driver.findElements(Cases.newCase).size() > 0) {
			loggerPass("Different case record types list should be display",
					"Different case record types list is displayed", "Yes");
			Thread.sleep(5000);
			javaClickByLocator(Accounts.outboundRadioBtn);
			Thread.sleep(5000);
			javaClickByLocator(Accounts.next);
			Thread.sleep(3000);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));

			if (driver.findElements(Accounts.outboundPopup).size() > 0) {
				loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
				Thread.sleep(3000);
				if (driver.findElements(Accounts.AccountNameField).size() > 0) {
					fillFieldByLocator(Accounts.AccountNameField, accountName + Keys.ENTER);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")))
							.click();
					
				}
				
				javaClickByLocator(Accounts.saveBtn);
				Thread.sleep(2000);
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);
					Thread.sleep(2000);
					if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
					{	javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);}
				}
			} else {
				loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is Not displayed", true);

			}
		}
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
		caseNumber = GetText(Accounts.caseNumberValue);
		System.out.println("Case number is " + caseNumber);
		Thread.sleep(10000);
		caseNumber = GetText(Accounts.caseNumberValue);
		Thread.sleep(5000);
		if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
			loggerPass("New Case should be created <b>" + caseNumber + "</b>",
					"New Case is created <b>" + caseNumber + "</b>", "Yes");

			if (driver.findElements(Cases.DetailsTabOB).size() > 0) {
				loggerPass("Details tab should be display in case page", "Details tab is displayed in case page", "No");
			} else {
				loggerFail("Details tab should be display in case page", "Details tab not displayed in case page",
						true);
			}

			if (driver.findElements(Cases.CaseRecord_Outbound).size() > 0) {
				loggerPass("Case record type should be Outbound", "Case record type is Outbound", "No");
			} else {
				loggerFail("Case record type should be Outbound", "Case record type is not Outbound", true);
			}

			if (driver.findElements(Cases.MessageField_CaseDetails).size() > 0) {
				salesforce_ScrollDown(1);
				loggerPass("Message Field should be display in case details page",
						"Message Field is displayed in case details page", "yes");
			} else {
				loggerFail("Message Field should be display in case details page",
						"Message Field is Not displayed in case details page", true);
			}

			if (driver.findElements(Cases.Edit_Message).size() > 0) {
				loggerPass("Message Field should be Editable", "Message Field is Editable", "No");
			} else {
				loggerFail("Message Field should be Editable", "Message Field is Not Editable", true);
			}

			javaClickByLocator(Cases.Edit_Message);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Cases.MessageTextBox));
			TestMessage = "Testing Message Field " + generateRandomInteger();
			Thread.sleep(2000);
			driver.findElement(Cases.MessageTextBox).sendKeys(TestMessage + Keys.TAB);
			Thread.sleep(5000);
			javaClickByLocator(RetailStoreGroups.save);

			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Cases.MessageField_CaseDetails));

			Actual_Message = GetText(Cases.MessageFieldValue);

			if (TestMessage.equals(Actual_Message)) {
				loggerPass("Message field should display <b>" + TestMessage + "</b> value",
						"Message field showing <b>" + Actual_Message + "</b> value", "yes");
			} else {
				loggerFail("Message field should display <b>" + TestMessage + "</b> value",
						"Message field showing <b>" + Actual_Message + "</b> value", false);
			}
			Thread.sleep(10000);
			javaClickByLocator(By.xpath("//span[text()='Close " + caseNumber + " | Case']/ancestor::button"));

			closeMultipleTabs();
			driver.navigate().to(driver.getCurrentUrl());
			Thread.sleep(2000);
			SelectAccountFromGlobalSearch(accountNo, accountName);
			javaClickByLocator(Accounts.casesWidget);
			Thread.sleep(5000);
			MessageOnRelatedList = GetText(By.xpath("//table//slot[text()='" + caseNumber
					+ "']//ancestor::th/following-sibling::td[@data-label='Message']//lst-formatted-text"));
			if (MessageOnRelatedList.equals(Actual_Message)) {
				loggerPass("Message field should display <b>" + TestMessage + "</b> In <b>Related list</b>",
						"Message field showing <b>" + MessageOnRelatedList + "</b> In <b>Related list</b>", "yes");
			} else {
				loggerFail("Message field should display <b>" + TestMessage + "</b> In <b>Related list</b>",
						"Message field showing <b>" + Actual_Message + "</b> In <b>Related list</b>", false);
			}

		} else {
			loggerFail("New Case should be created <b>" + caseNumber + "</b>", "New Case is not created", true);
		}

	}

	// Created by - Niranjan H on 22-12-23
	// Ability to see information about declined call in IVR
	public void Verify_IVROptionsCaseStatus(HashMap<String, String> reports) throws Throwable {
		System.out.println("Ability to see information about declined call in IVR");

		String accountName = "";
		String accountNo = "";
		String RSGname = "Call List RSG V1_" + generateRandomInteger();
		String NewRSGname = "Call List RSG V2_ " + generateRandomInteger();
		String startdate = dateToday();
		String enddate = increaseOrDecreaseDate(startdate, "day", 7);
		String reportUniqueName = "";
		String RequireStatus = "";
		String Disposition = "Productive";
		String Callresult = "No Order Needed";
		String calllistnumber = "";
		String GenerateCallList = "";
		softAssert = new SoftAssert();
		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			reportUniqueName = "X4100_CallList";
			break;
		case "4200":
			accountName = "DOMINOS #5550";
			accountNo = "0602423591";
			reportUniqueName = "X4200_ContactPriority";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			reportUniqueName = "X4400_CallList";
			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";
			reportUniqueName = "X4500_CallList";
			break;
		case "4600":
			accountName = "DOMINOS #8134";
			accountNo = "0601603370";
			reportUniqueName = "New_Accounts_Report_27nov";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			reportUniqueName = "X4700_CallList";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			reportUniqueName = "X4800_CallList";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			reportUniqueName = "X4900_CallList";
			break;
		case "5000":
			accountName = "DOMINOS #3710";
			accountNo = "0601778750";
			reportUniqueName = "X5000_ContactPriority";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			reportUniqueName = "X5200_ContactPriority";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			reportUniqueName = "X5300_CallList";
			break;
		}
		selectAppOrObjFromAppLauncher("object", Constants.accountfromlauncher);
		SelectAccountFromGlobalSearch(accountNo, accountName);
		scrollToElement(Accounts.CallingHours);
		javaClickByLocator(Accounts.CallingHoursvalue);
		salesforce_ScrollDown(10);
		isDisplayed(Accounts.DayofWeek, "Next Day for Calling");
		SimpleDateFormat sp = new SimpleDateFormat("EEEE");
		Date dt = new Date();
		String TodaysDate = sp.format(dt);
		Calendar cal = Calendar.getInstance();
		String reqdate = GetText(Accounts.DayofWeek);
		for (int j = 0; j < 7; j++) {
			cal.setTime(sp.parse(TodaysDate));
			cal.add(cal.DAY_OF_MONTH, j);
			String newDate = sp.format(cal.getTime());
			if (reqdate.equalsIgnoreCase(newDate) == true) {
				GenerateCallList = CommonFunctions.calendarInput("MMM dd, YYYY", j);
			}
		}

		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		javaClickByLocator(RetailStoreGroups.newbtn);
		isDisplayed(RetailStoreGroups.newrsg, "New Retail store group page");
		javaClickByLocator(RetailStoreGroups.CallList);
		javaClickByLocator(RetailStoreGroups.next);
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.RSGname));
		isDisplayed(RetailStoreGroups.NewCallList, "New Retail store group page:Call List");
		fillFieldByLocator(RetailStoreGroups.RSGname, RSGname);
		fillFieldByLocator(RetailStoreGroups.uniquename, reportUniqueName);
		Thread.sleep(2000);
		driver.findElement(RetailStoreGroups.rsgDescription).sendKeys("Test");
		closeMultipleTabs();
		scrollToElement(RetailStoreGroups.CallListName);
		fillFieldByLocator(RetailStoreGroups.CallListName, "Call List_" + generateRandomInteger());
		select_dropdownValue("Order Reminder", RetailStoreGroups.CallType);
		select_dropdownValue("Adhoc Call List", RetailStoreGroups.CallListModel);
		driver.findElement(RetailStoreGroups.Owner)
				.sendKeys("autooffshore.bottleradmin " + Constants.bottler + Keys.ENTER);
		javaClickByLocator(RetailStoreGroups.firstresult);
		fillFieldByLocator(RetailStoreGroups.CallListStartDate, startdate);
		fillFieldByLocator(RetailStoreGroups.CallListEndDate, enddate);
		driver.findElement(RetailStoreGroups.CallListEndDate).sendKeys(Keys.TAB);
		javaClickByLocator(RetailStoreGroups.save);
		CommonFunctions.verifyToastMessage("Retail Store Group " + RSGname + " was created");
		Thread.sleep(2000);
		try {
			String title = "" + RSGname + " | Retail Store Groups";
			List<WebElement> close = driver.findElements(RetailStoreGroups.closeRSG);
			for (WebElement closetab : close) {
				if (!closetab.getText().contains(title))
					javaClickByWebElement(closetab);
			}
		} catch (Exception e) {
		}
		driver.navigate().to(driver.getCurrentUrl());
		new WebDriverWait(driver, Duration.ofSeconds(25))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.CallListwidget));
		javaClickByLocator(RetailStoreGroups.PopulateCallList);
		new WebDriverWait(driver, Duration.ofSeconds(25))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.geneateCallList));
		javaClickByLocator(RetailStoreGroups.geneateCallList);
		int i = 1;
		while (i <= 15) {
			Thread.sleep(10000);
			driver.navigate().refresh();
			if (!driver.findElement(RetailStoreGroups.targetGroupSize).getText().isEmpty()) {
				if (driver.findElement(RetailStoreGroups.targetGroupSize).getText() == "0") {
					screenshot = capture(driver);
					loggerFail("Target Group Size value should not be Zero.", "Target Group Size value is Zero", true);
				}
				break;
			} else if (i >= 15) {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b>Target Group Size value should not be empty. <br /> <b>Actual Result:</b> Target Group Size value is empty",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				Assert.fail(
						"<b>Expected Result:</b>Target Group Size value should not be empty. <br /> <b>Actual Result:</b> Target Group Size value is empty");
			}
			i++;
		}
		try {
			accountName = GetText(RetailStoreGroups.TSGaccountname);
			accountNo = GetText(RetailStoreGroups.TSGaccountnumber);
		} catch (Exception e) {
			driver.navigate().to(driver.getCurrentUrl());
			accountName = GetText(RetailStoreGroups.TSGaccountname);
			accountNo = GetText(RetailStoreGroups.TSGaccountnumber);
		}
		isDisplayed(RetailStoreGroups.targetGroupSize, "Target Group Size");
		driver.navigate().to(driver.getCurrentUrl());
		closeMultipleTabs();

		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		driver.findElement(RetailStoreGroups.serachrsg).sendKeys(RSGname + Keys.ENTER);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + RSGname + "']")))
				.click();
		if (driver.findElements(RetailStoreGroups.calllistitems).size() > 0) {
			loggerPass("Call list should populate automatically", "Call list is populated automatically", "No");
		} else {
			loggerFail("Call list should populate automatically", "Call list not populated automatically", true);
		}

		javaClickByLocator(RetailStoreGroups.calllistitems);
		calllistnumber = GetText(RetailStoreGroups.CalllistName);
		loggerInfo("Call list " + calllistnumber + " Is created", "No");
		Thread.sleep(10000);
		salesforce_ScrollDown(2);
		isDisplayed(RetailStoreGroups.casesoncalllist, "Outbound Case On call List");

		String casenumber = GetText(Cases.CaseNumber);
		loggerInfo("Outbound Case " + casenumber + " is created", "No");
		javaClickByLocator(Cases.Case_OnCallList);
		isDisplayed(By.xpath("//flexipage-tab2[@aria-labelledby='detailTab__item']//lightning-formatted-text[text()='"
				+ casenumber + "']"), "Case Details Page");

		isDisplayed(RetailStoreGroups.DispositionPicklist, "Outbound Case Disposition Picklist");
		logger.info("Disposition Picklist Validation:");
		Select sc = new Select(driver.findElement(RetailStoreGroups.DispositionPicklist));
		List<String> AllOptionsValues = new ArrayList<String>();

		List<WebElement> AllOptions = sc.getOptions();
		for (WebElement Value1 : AllOptions) {
			AllOptionsValues.add(Value1.getText());
		}
		for (String ExDisp_Value : RetailStoreGroups.OBDispositionDropDown) {
			if (AllOptionsValues.contains(ExDisp_Value) == true) {
				loggerPass("<b>" + ExDisp_Value + "</b> Should be present", "<b>" + ExDisp_Value + "</b> is present",
						"No");
			} else {
				loggerFail("<b>" + ExDisp_Value + "</b> Should be present",
						"<b>" + ExDisp_Value + "</b> is Not present", false);
			}
		}
		sc.selectByVisibleText(Disposition);
		javaClickByLocator(RetailStoreGroups.NextDisposition);

		isDisplayed(RetailStoreGroups.CallResultPicklist, "Call Result Picklist");
		logger.info("Call Result Picklist Validation:");
		Select sc1 = new Select(driver.findElement(RetailStoreGroups.CallResultPicklist));
		List<WebElement> actualcallresult = sc1.getOptions();
		AllOptionsValues.clear();
		for (WebElement Value : actualcallresult) {
			AllOptionsValues.add(Value.getText());
		}
		for (String Value_CallResult : RetailStoreGroups.CallResults) {
			if (AllOptionsValues.contains(Value_CallResult)) {
				loggerPass("<b>" + Value_CallResult + "</b> Should be present",
						"<b>" + Value_CallResult + "</b> is present", "No");
			} else {
				loggerFail("<b>" + Value_CallResult + "</b> Should be present",
						"<b>" + Value_CallResult + "</b> is Not   present", false);
			}
		}
		sc1.selectByVisibleText(Callresult);
		javaClickByLocator(RetailStoreGroups.NextDisposition);
		Thread.sleep(5000);
		String ActualStatus = GetText(RetailStoreGroups.CaseStatus);
		if (Disposition == "Productive" && Callresult == "No Order Needed") {
			RequireStatus = "Closed";
		}

		if (RequireStatus.equalsIgnoreCase(ActualStatus)) {
			loggerPass("Case status should be Changed To <b>" + ActualStatus + "</b>",
					"<b>Case status Changed To <b>" + ActualStatus + "</b>", "Yes");
		} else {
			loggerFail("Case status should be Changed To <b>" + RequireStatus + "</b>",
					"Case status Not Changed To <b>" + RequireStatus + "</b>", true);
		}

		scrollToElement(RetailStoreGroups.ActualDisposition);
		if (Disposition.equalsIgnoreCase(GetText(RetailStoreGroups.ActualDisposition))) {
			loggerPass("Disposition field should get's updated with value given <b>" + Disposition + "</b>",
					"Disposition field get's updated with value given <b>" + Disposition + "</b>", "No");
		} else {
			loggerFail("Disposition field should get's updated with value given <b>" + Disposition + "</b>",
					"Disposition field Not get's updated with value given <b>" + Disposition + "</b>", true);
		}

		if (Callresult.equalsIgnoreCase(GetText(RetailStoreGroups.ActualCallResult))) {
			loggerPass("Call result field should get's updated with value given <b>" + Callresult + "</b>",
					"Call result field get's updated with value given <b>" + Callresult + "</b>", "No");
		} else {
			loggerFail("Call result field should get's updated with value given <b>" + Callresult + "</b>",
					"Call result field Not get's updated with value given <b>" + Callresult + "</b>", true);
		}

		// TC - 315587
		loggerInfo("Creating new RSG with Call List", "No");
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		javaClickByLocator(RetailStoreGroups.newbtn);
		isDisplayed(RetailStoreGroups.newrsg, "New Retail store group page");
		javaClickByLocator(RetailStoreGroups.CallList);
		javaClickByLocator(RetailStoreGroups.next);
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.RSGname));
		isDisplayed(RetailStoreGroups.NewCallList, "New Retail store group page:Call List");
		fillFieldByLocator(RetailStoreGroups.RSGname, NewRSGname);
		fillFieldByLocator(RetailStoreGroups.uniquename, reportUniqueName);
		Thread.sleep(2000);
		driver.findElement(RetailStoreGroups.rsgDescription).sendKeys("Test 2");
		scrollToElement(RetailStoreGroups.CallListName);
		fillFieldByLocator(RetailStoreGroups.CallListName, "Call List_" + generateRandomInteger());
		select_dropdownValue("Inside Sales", RetailStoreGroups.CallType);
		select_dropdownValue("Order replenishment - ZT Partner", RetailStoreGroups.CallListModel);
		driver.findElement(RetailStoreGroups.Owner)
				.sendKeys("autooffshore.bottleradmin " + Constants.bottler + Keys.ENTER);
		javaClickByLocator(RetailStoreGroups.firstresult);
		fillFieldByLocator(RetailStoreGroups.CallListStartDate, startdate);
		fillFieldByLocator(RetailStoreGroups.CallListEndDate, enddate);
		driver.findElement(RetailStoreGroups.CallListEndDate).sendKeys(Keys.TAB);
		javaClickByLocator(RetailStoreGroups.save);
		CommonFunctions.verifyToastMessage("Retail Store Group " + NewRSGname + " was created");
		Thread.sleep(2000);
		try {
			String title = "" + NewRSGname + " | Retail Store Groups";
			List<WebElement> close = driver.findElements(RetailStoreGroups.closeRSG);
			for (WebElement closetab : close) {
				if (!closetab.getText().contains(title))
					javaClickByWebElement(closetab);
			}
		} catch (Exception e) {
		}
		driver.navigate().to(driver.getCurrentUrl());
		new WebDriverWait(driver, Duration.ofSeconds(25))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.CallListwidget));
		javaClickByLocator(RetailStoreGroups.PopulateCallList);
		new WebDriverWait(driver, Duration.ofSeconds(25))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.geneateCallList));
		fillFieldByLocator(RetailStoreGroups.callDate, GenerateCallList);
		Thread.sleep(5000);
		driver.findElement(RetailStoreGroups.callDate).click();
		driver.findElement(RetailStoreGroups.callDate).sendKeys(Keys.ESC);
		javaClickByLocator(RetailStoreGroups.geneateCallList);
		int j = 1;
		while (j <= 15) {
			Thread.sleep(10000);
			driver.navigate().refresh();
			if (!driver.findElement(RetailStoreGroups.targetGroupSize).getText().isEmpty()) {
				if (driver.findElement(RetailStoreGroups.targetGroupSize).getText() == "0") {
					screenshot = capture(driver);
					loggerFail("Target Group Size value should not be Zero.", "Target Group Size value is Zero", true);
				}
				break;
			} else if (j >= 15) {
				screenshot = capture(driver);
				logger.fail(
						"<b>Expected Result:</b>Target Group Size value should not be empty. <br /> <b>Actual Result:</b> Target Group Size value is empty",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				Assert.fail(
						"<b>Expected Result:</b>Target Group Size value should not be empty. <br /> <b>Actual Result:</b> Target Group Size value is empty");
			}
			j++;
		}
		try {
			accountName = GetText(RetailStoreGroups.TSGaccountname);
			accountNo = GetText(RetailStoreGroups.TSGaccountnumber);
		} catch (Exception e) {
			driver.navigate().to(driver.getCurrentUrl());
			accountName = GetText(RetailStoreGroups.TSGaccountname);
			accountNo = GetText(RetailStoreGroups.TSGaccountnumber);
		}
		isDisplayed(RetailStoreGroups.targetGroupSize, "Target Group Size");
		driver.navigate().to(driver.getCurrentUrl());
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		driver.findElement(RetailStoreGroups.serachrsg).sendKeys(NewRSGname + Keys.ENTER);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + NewRSGname + "']")))
				.click();
		if (driver.findElements(RetailStoreGroups.calllistitems).size() > 0) {
			loggerPass("Call list should populate automatically", "Call list is populated automatically", "No");
		} else {
			loggerFail("Call list should populate automatically", "Call list not populated automatically", true);
		}
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		driver.findElement(RetailStoreGroups.serachrsg).sendKeys(NewRSGname + Keys.ENTER);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + NewRSGname + "']")))
				.click();
		if (driver.findElements(RetailStoreGroups.calllistitems).size() > 0) {
			loggerPass("Call list should populate automatically", "Call list is populated automatically", "No");
		} else {
			loggerFail("Call list should populate automatically", "Call list not populated automatically", true);
		}

		javaClickByLocator(RetailStoreGroups.calllistitems);
		calllistnumber = GetText(RetailStoreGroups.CalllistName);
		loggerInfo("Call list " + calllistnumber + " Is created", "No");
		Thread.sleep(10000);
		salesforce_ScrollDown(2);

		if (driver.findElements(RetailStoreGroups.CaseNumber).size() > 0) {
			loggerFail("Outbound Case should not be created on New RSG", "Outbound Case created on New RSG", false);
		} else {
			loggerPass("Outbound Case should not be created on New RSG", "Outbound Case is not created on New RSG",
					"Yes");

		}
	}

	// Created by - Abhishek Palankar on 02-Jan-2024
	// This method is used to validate options in Campaign Type and Prompt.
	public static void ValidateCampaignTypeOptions() throws Throwable {
		System.out.println("Verify order widget");
		softAssert = new SoftAssert();

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		javaClickByLocator(RetailStoreGroups.newbtn);
		isDisplayed(RetailStoreGroups.newrsg, "New Retail store group page");
		javaClickByLocator(RetailStoreGroups.CallList);
		javaClickByLocator(RetailStoreGroups.next);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.RSGname));

		// TC - 299057
		if (driver.findElements(RetailStoreGroups.NewCallList).size() > 0) {
			loggerPass("New Retail Store Group: Call List page creation layout should be display",
					"New Retail Store Group: Call List page creation layout is displayed", "No");
			scrollToElement(RetailStoreGroups.callListDefinitionSection);
			if (driver.findElements(RetailStoreGroups.UCCXCampaignType).size() > 0) {
				loggerPass("UCCX Compaign Type field should be display", "UCCX Compaign Type field is displayed",
						"Yes");
				Thread.sleep(1000);
				javaClickByLocator(RetailStoreGroups.UCCXCampaignTypeNone);
				Thread.sleep(1000);
				loggerInfo("UCCX Compaign options validation", "Yes");
				List<String> present = new ArrayList<String>();
				List<String> notpresent = new ArrayList<String>();
				for (String fieldName : UCCXCompaignType) {
					if (driver
							.findElements(By.xpath("//lightning-base-combobox-item//span[text()='" + fieldName + "']"))
							.size() != 0) {

						present.add(fieldName);
					} else {
						notpresent.add(fieldName);
						validationFlag = false;
					}
				}
				fieldValidation("UCCX Prompt options validation", present, notpresent);
				present.clear();
				notpresent.clear();

			} else {
				loggerFail("UCCX Compaign Type field should be display", "UCCX Compaign Type field is not displayed",
						false);

			}

			// TC - 299058
			if (driver.findElements(RetailStoreGroups.UCCXCampaignType).size() > 0) {
				loggerPass("UCCX Prompt field should be display", "UCCX Prompt field is displayed", "No");
				Thread.sleep(1000);
				javaClickByLocator(RetailStoreGroups.UCCXPromptNone);
				Thread.sleep(1000);
				loggerInfo("UCCX Prompt options validation", "Yes");
				List<String> present = new ArrayList<String>();
				List<String> notpresent = new ArrayList<String>();
				for (String fieldName : UCCXPromptOptions) {
					if (driver
							.findElements(By.xpath("//lightning-base-combobox-item//span[text()='" + fieldName + "']"))
							.size() != 0) {

						present.add(fieldName);
					} else {
						notpresent.add(fieldName);
						validationFlag = false;
					}
				}
				fieldValidation("UCCX Prompt options validation", present, notpresent);
				present.clear();
				notpresent.clear();

			} else {
				loggerFail("UCCX Prompt field should be display", "UCCX Prompt field is not displayed", false);

			}

		} else {

			loggerFail("New Retail Store Group: Call List page creation layout should be display",
					"New Retail Store Group: Call List page creation layout is not displayed", false);
		}
		softAssert.assertAll();

	}

	// This test is to verify that we should have a Outbound case Disposition on
	// outbound case page layout
	public static void Varify_OutboundCallDisposition_Matrix() throws Throwable {
		System.out.println("verify that we should have a Outbound case Disposition on outbound case page layout");
		softAssert = new SoftAssert();
		String ListName = "OCDM Test";
	
		
		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		selectAppOrObjFromAppLauncher("object", Constants.caseobjectfromlauncher);
		closeMultipleTabs();
	
		javaClickByLocator(By.xpath("//button[contains(@title,'Select a List View:')]"));
		fillField("//input[@placeholder='Search lists...']", ListName);
		Thread.sleep(3000);
		if (driver.findElements(By.xpath("(//span[text()='" + ListName + "']/ancestor::a)[1]")).size() > 0) {
			javaClickByLocator(By.xpath("(//span[text()='" + ListName + "']/ancestor::a)[1]"));
			Thread.sleep(3000);
			tableLoadingWait();
			
			} else if (driver.findElements(By.xpath("(//mark[text()='" + ListName + "']/ancestor::a)[1]")).size() > 0) {
			javaClickByLocator(By.xpath("(//mark[text()='" + ListName + "']/ancestor::a)[1]"));
			
		} else {

			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Cases.NewListView));
			javaClickByLocator(Cases.NewListView);
			fillFieldByLocator(Cases.ListviewName, ListName);
			javaClickByLocator(Cases.NewListView_save);
			Thread.sleep(5000);
			javaClickByLocator(CST_Routing.filterbyowner);
			javaClickByLocator(Cases.allCases);
			javaClickByLocator(CST_Routing.done);
			AddFilter("Status", "equals", "Assigned");
			Thread.sleep(1000);
			AddFilter("Case Record Type", "equals", "Outbound");
			AddFilter("Outbound Disposition Type", "equals", "Inside Sales");
			javaClickByLocator(CST_Routing.saveFilterBtn);
			tableLoadingWait();
			javaClickByLocator(CST_Routing.CloseFilter);

		}

			
			if (driver.findElements(Cases.first_case).size() > 0) {
				String casenumber = GetText(Cases.first_case);
				javaClickByLocator(Cases.first_case);
				loggerPass("Outbound cases should be present", "Outbound cases are present", "No");
				isDisplayed(By
						.xpath("//flexipage-tab2[@aria-labelledby='detailTab__item']//lightning-formatted-text[text()='"
								+ casenumber + "']"),
						"Case Details Page");
				isDisplayed(Cases.Disposition_DropDown, "Disposition");
			} else {
				loggerFail("Outbound cases should be present", "Outbound cases are Not present", true);
			}
			// TC-309012
			VerifyCaseStatus_ForOCDM("Productive", "No Order Needed", "Closed");
			ChangeCaseStatus("New");
			// TC-309013
			VerifyCaseStatus_ForOCDM("Productive", "Need Replenishment Call", "Closed");
			ChangeCaseStatus("New");
			// TC-309014
			VerifyCaseStatus_ForOCDM("Productive", "IVR Voicemail", "Closed");
			ChangeCaseStatus("New");
			// TC-309015
			VerifyCaseStatus_ForOCDM("Productive", "Agent Declined", "Closed");
			ChangeCaseStatus("New");
			// TC-309016
			VerifyCaseStatus_ForOCDM("Productive", "Send Back to Campaign", "In Progress");
			ChangeCaseStatus("New");
			// TC-309017
			VerifyCaseStatus_ForOCDM("Unproductive", "No Answer", "In Progress");
			ChangeCaseStatus("New");
			// TC-309018
			VerifyCaseStatus_ForOCDM("Unproductive", "No Answer – Several Attempts", "Closed");
			ChangeCaseStatus("New");
			// TC-309019
			VerifyCaseStatus_ForOCDM("Unproductive", "Voicemail Full", "In Progress");
			ChangeCaseStatus("New");
			// TC-309020
			VerifyCaseStatus_ForOCDM("Call Back", "Customer Will Call Back", "In Progress");
			ChangeCaseStatus("New");
			// TC-309021
			VerifyCaseStatus_ForOCDM("Call Back", "Contact Not Ready", "In Progress");
			ChangeCaseStatus("New");
			// TC-309022
			VerifyCaseStatus_ForOCDM("Call Back", "Request Call Back", "In Progress");
		

	}

	public static void decisionTree() throws Throwable {
		String accountName = "";
		String accountNo = "";
		softAssert = new SoftAssert();

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";

			break;
		case "4200":
			accountName = "DOLLAR GENERAL #02523";
			accountNo = "0500008854";

			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";

			break;
		case "4500":
			accountName = "DOMINOS PIZZA #5189";
			accountNo = "0600782158";

			break;
		case "4600":
			accountName = "DOMINOS #8134";
			accountNo = "0601603370";

			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";

			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";

			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";

			break;
		case "5000":
			accountName = "DOMINOS #3710";
			accountNo = "0601778750";

			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";

			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";

			break;
		}
			selectAppOrObjFromAppLauncher("object", Constants.accountfromlauncher);
			closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			scrollToElement(By.xpath("//a[text()='Create Case']"));
			javaClickByLocator(By.xpath("//a[text()='Create Case']"));
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Decision Tree']/ancestor::article//select")));
			isDisplayed(By.xpath("//span[text()='Decision Tree']/ancestor::article//select"), "Decision Tree");
			DecisionTree_Latest(CSTQuestions_4200, CSTvalues_4200,"");
	}

	
	// Created by - Abhishek Palankar on 11-07-2024
		// This test is to verify that A link/button to the planning calendar should be added to the OB Case header
		public static void verifyPlanningCalendarButtonInOBPage (HashMap<String, String> mapValue) throws Throwable {
			System.out.println("Planning Calendar On OB Case Header");
			softAssert = new SoftAssert();
			String accountName = "";
			String accountNo = "";
			String	ListName="CST Cases";
			String	ListName2="EST Cases";

			switch (Constants.bottler) {
			case "4100":
				accountName = "FIVE STAR FOOD SERVICE INC";
				accountNo = "0500004781";
				break;
			case "4200":
				accountName = "DOMINOS #5550";
				accountNo = "0602423591";
				break;
			case "4400":
				accountName = "DOMINO'S PIZZA #7661";
				accountNo = "0501456013";
				break;
			case "4500":
				accountName = "DOMINOS PIZZA #5189";
				accountNo = "0600782158";
				break;
			case "4600":
				accountName = "DOMINOS #8585";
				accountNo = "0601609054";
				break;
			case "4700":
				accountName = "AUTOZONE #4440";
				accountNo = "0600927373";
				break;
			case "4800":
				accountName = "DOLLAR GENERAL #11614";
				accountNo = "0600608462";
				break;
			case "4900":
				accountName = "DOMINOS #4080";
				accountNo = "0601230384";
				break;
			case "5000":
				accountName = "BIG Y #0108";
				accountNo = "0601731108";
				break;
			case "5200":
				accountName = "DOMINOS #3286";
				accountNo = "0601451828";
				break;
			case "5300":
				accountName = "ABBY CONVENIENCE STORE";
				accountNo = "602184829";
				break;
			}

			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			javaClickByLocator(Cases.Acccounts_PlanningCalendarBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(Cases.PlanningCalenarPage));
			Thread.sleep(4000);
			String AccPlanningCalenderHeading=GetText(Cases.PlanningCalenarPage);
			String FirstMonth="August   2024";//GetText(Cases.FisrtCalendarMonth);
			String SecondMonth=GetText(Cases.SecondCalendarMonth);
			String Month01 = FirstMonth.replaceAll("\\d", "").trim();
//			String Month02 = SecondMonth.replaceAll("\\d", "").trim();
			Thread.sleep(6000);
			List<WebElement> calling = 	driver.findElements(By.xpath("//b[contains(text(),'"+Month01+"')]/ancestor::div[@data-aura-class='cCONA_CalendarComponent']//td//img[@title='Calling Day' and not (@class='slds-hide')]//ancestor::span[@class='slds-day']"));
			List<String> callingDates = new ArrayList<>();
			for (WebElement element : calling) { callingDates.add(element.getText()); }
			System.out.println("Calling Dates on Accounts Page : "+callingDates);
			
			
			List<WebElement> Delivery = 	driver.findElements(By.xpath("//b[contains(text(),'"+Month01+"')]/ancestor::div[@data-aura-class='cCONA_CalendarComponent']//td//img[@title='Delivery Day' and not (@class='slds-hide')]//ancestor::span[@class='slds-day']"));
			List<String> DeliveryDates = new ArrayList<>();
			for (WebElement element : Delivery) { DeliveryDates.add(element.getText()); }
			System.out.println("Delivery Dates on Accounts Page : "+DeliveryDates);
			
			
			List<WebElement> F2FVisit = 	driver.findElements(By.xpath("//b[contains(text(),'"+Month01+"')]/ancestor::div[@data-aura-class='cCONA_CalendarComponent']//td//img[@title='Face to Face Visit' and not (@class='slds-hide')]//ancestor::span[@class='slds-day']"));
			List<String> F2FVisitdates = new ArrayList<>();
			for (WebElement element : F2FVisit) { F2FVisitdates.add(element.getText()); }
			System.out.println("F2F Visit Dates on Accounts Page : "+F2FVisitdates);
			
			List<WebElement> Reroute = 	driver.findElements(By.xpath("//b[contains(text(),'"+Month01+"')]/ancestor::div[@data-aura-class='cCONA_CalendarComponent']//td//img[@title='Reroute Details' and not (@class='slds-hide')]//ancestor::span[@class='slds-day']"));
			List<String> RerouteDates = new ArrayList<>();
			for (WebElement element : Reroute) { RerouteDates.add(element.getText()); }
			System.out.println("Reroute Dates on Accounts Page : "+RerouteDates);

			List<WebElement> Holiday = 	driver.findElements(By.xpath("//b[contains(text(),'"+Month01+"')]/ancestor::div[@data-aura-class='cCONA_CalendarComponent']//td//img[contains(@title,'Holiday') and not (@class='slds-hide')]//ancestor::span[@class='slds-day']"));
			List<String> HolidayDates = new ArrayList<>();
			for (WebElement element : Holiday) { HolidayDates.add(element.getText()); }
			System.out.println("Holiday Dates on Accounts Page : "+HolidayDates);

			
			javaClickByLocator(Cases.Close);
			Thread.sleep(4000);
			if (driver.findElements(By.xpath("//div[@data-aura-class='forceToastMessage']//button")).size()>0) {
				javaClickByLocator(By.xpath("//div[@data-aura-class='forceToastMessage']//button"));
		
			}
			Thread.sleep(4000);
			javaClickByLocator(Cases.ClosePlanningCalender);			
			Thread.sleep(3000);
			driver.navigate().refresh();
			salesforce_ScrollDown(2);
			scrollToElement(Accounts.casesWidget);
			javaClickByLocator(Accounts.casesWidget);
			Thread.sleep(2000);
			javaClickByLocator(Accounts.newcase);
			javaClickByLocator(Accounts.outboundRadioBtn);
			javaClickByLocator(Accounts.next);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
			if (driver.findElements(Accounts.outboundPopup).size() > 0) {
				loggerPass("<b>Outbound Case</b>page should be displayed", "<b>Outbound Case</b>page is displayed", "No");
				Thread.sleep(4000);		
				if (driver.findElements(Accounts.AccountNameField).size() > 0) {
					driver.findElement(Accounts.AccountNameField).sendKeys(accountName + Keys.ENTER);
					javaClickByLocator(RetailStoreGroups.firstresult);
					Thread.sleep(2000);
				}
				javaClickByLocator(Accounts.saveBtn);

				Thread.sleep(2000);
				if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					scrollToElement(Cases.CSTPopup);
					fillFieldByLocator(Accounts.AccountNameField, accountName);
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//lightning-base-combobox-formatted-text[@title='" + accountName + "'])[1]"))
							.click();
					Thread.sleep(1000);
					javaClickByLocator(Accounts.saveBtn);
				}
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);
					Thread.sleep(2000);
					if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) 
					{	javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);}
				}
			
			
			
			
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberValue));			
			loggerInfo("Selected outbound case number is : "+GetText(Accounts.caseNumberValue), "No");
			if (driver.findElements(Cases.DetailsTabOB).size() > 0) {
					loggerPass("Details tab should be display in case page", "Details tab is displayed in case page",
							"No");
				} else {
					loggerFail("Details tab should be display in case page", "Details tab not displayed in case page",
							true);
				}
				isDisplayed(Cases.FollowButton, "Follow Button");
				isDisplayedWithScreenshot(Cases.PlanningCalendarbtn, "Planning Calendar button on OB Case page header", "Yes");
				isDisplayed(Cases.EditButton, "Edit Button");
				isDisplayed(Cases.CreateSalesOrderButton, "Create Sales Order Button");
				isDisplayed(Cases.SetReminderButton, "Set Reminder Button");
				if (bottler.equals("4800")) {isDisplayed(Cases.e_VisitButton, "E-Visit Button");}
				if (GetText(Cases.FirstButtonAfterFollow).equalsIgnoreCase("Planning Calendar")) {
				
					loggerPass("Planning Calendar button should be placed after the Follow button and at the second position in OB Case Page header", "Planning Calendar button is placed after the follow button and at the second position in OB Case Page header", "No");
				}else {
					loggerFail("Planning Calendar button should be placed after the Follow button and at the second position in OB Case Page header", "Planning Calendar button is not placed after the follow button and at the second position in OB Case Page header", true);
					
				}	
			javaClickByLocator(Cases.Cases_PlanningCalendarBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(Cases.PlanningCalenarPage));
			isDisplayed(Cases.PlanningCalenarPage, "Planning Calendar Page");
			String CasePlanningCalendarheading=GetText(Cases.PlanningCalenarPage);
			String CaseFirstMonth=GetText(Cases.FisrtCalendarMonth);
			String CaseSecondMonth=GetText(Cases.SecondCalendarMonth);
			if (CasePlanningCalendarheading.equals(AccPlanningCalenderHeading) && CaseFirstMonth.equals(FirstMonth) && CaseSecondMonth.equals(SecondMonth)) {
				loggerPass("Planning Calendar should be displayed the same way when we invoke it from the Account detail page.", "Planning Calendar is displayed the same way when we invoke it from the Account detail page.", "yes");	
			}else {
				loggerFail("Planning Calendar should be displayed the same way when we invoke it from the Account detail page.", "Planning Calendar is Not displayed the same way when we invoke it from the Account detail page.", validationFlag);
			}
				
			isDisplayed(Cases.NextPageBtn, "Next Page Button");
			isDisplayed(Cases.PreviousPageBtn, "Previous Button");

			} else {
				loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page not displayed", true);
			}
			
			
			try {

				for (String Dates : callingDates) {
					if (!driver.findElement(By.xpath("//Div[text()='" + Dates
							+ "']//img[@title='Calling Day' and not (@class='slds-hide')]//ancestor::div[@data-aura-class='cCONA_CalendarComponent']//b[contains(text(),'"
							+ Month01 + "')]")).isDisplayed()) {
						loggerFail("Calling Day should be displayed", "Calling Day not displayed", true);
					}

				}

				for (String Dates : DeliveryDates) {
					if (!driver.findElement(By.xpath("//Div[text()='" + Dates
							+ "']//img[@title='Delivery Day' and not (@class='slds-hide')]//ancestor::div[@data-aura-class='cCONA_CalendarComponent']//b[contains(text(),'"
							+ Month01 + "')]")).isDisplayed()) {
						loggerFail("Delivery Day should be displayed", "Delivery Day not displayed", true);
					} 
				}

				for (String Dates : F2FVisitdates) {
					if (!driver.findElement(By.xpath("//Div[text()='" + Dates
							+ "']//img[@title='Face to Face Visit' and not (@class='slds-hide')]//ancestor::div[@data-aura-class='cCONA_CalendarComponent']//b[contains(text(),'"
							+ Month01 + "')]")).isDisplayed()) {
						loggerFail("Face to Face Visit should be displayed", "Face to Face Visit not displayed", true);
					} 
				}

				for (String Dates : RerouteDates) {
					if (!driver.findElement(By.xpath("//Div[text()='" + Dates
							+ "']//img[@title='Reroute Details' and not (@class='slds-hide')]//ancestor::div[@data-aura-class='cCONA_CalendarComponent']//b[contains(text(),'"
							+ Month01 + "')]")).isDisplayed()) {
						loggerFail("Reroute Details should be displayed", "Reroute Details not displayed", true);
					} 
				}
				
				for (String Dates : HolidayDates) {
					if (!driver.findElement(By.xpath("//Div[text()='" + Dates
							+ "']//img[contains(@title,'Holiday') and not (@class='slds-hide')]//ancestor::div[@data-aura-class='cCONA_CalendarComponent']//b[contains(text(),'"
							+ Month01 + "')]")).isDisplayed()) {
						loggerFail("Holiday Details should be displayed", "Holiday Details not displayed", true);
					} 
				}
				loggerPass("Calling, Delivery, Holiday, Face to Face Visit, Reroute Details should be displayed",
						"Calling, Delivery, Holiday, Face to Face Visit, Reroute Details are displayed", "No");
			} catch (Exception e) 
			{
				loggerFail("Calling, Delivery, Holiday, Face to Face Visit, Reroute Details should be displayed",
						"Calling, Delivery, Holiday, Face to Face Visit, Reroute Details are not displayed", true);
			}
			String TodaysDate=new SimpleDateFormat("dd").format(new Date()).toString();
			System.out.println("Todays date : "+TodaysDate);
			javaClickByLocator(By.xpath("//b[contains(text(),'"+Month01+"')]/ancestor::div[@data-aura-class='cCONA_CalendarComponent']//Div[text()='"+TodaysDate+"']"));
			Thread.sleep(2000);
			new WebDriverWait(driver, Duration.ofSeconds(40))
			.until(ExpectedConditions.visibilityOfElementLocated(Cases.CreatSalesOrderTab));
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
			if(driver.findElement(Cases.ORDERHEADER).isDisplayed())
			{
				loggerPass("Sales order creation page should be displayed", "Sales order creation page is displayed", "yes");
			}else {
				loggerFail("Sales order creation page should be displayed", "Sales order creation page is not displayed", true);
			}
			
			SimpleDateFormat OrderDateFormat = new SimpleDateFormat("MMM dd, yyyy");
			SimpleDateFormat CalenderDateFormat = new SimpleDateFormat("dd MMM yyyy");
			scrollToElement(Cases.orderStart);
			String OrderStart = driver.findElement(Cases.orderStart).getAttribute("value");
	        Date Order_date =OrderDateFormat.parse(OrderStart);
	        System.out.println("Order Date : "+Order_date);


	        String SelectedDate_Calendar=TodaysDate+" "+FirstMonth.replaceAll("\\s+", " ");
	        Date Selected_Date =CalenderDateFormat.parse(SelectedDate_Calendar);
	        System.out.println("Required date : "+Selected_Date);
		
	        
	        
	        
	        if (Order_date.equals(Selected_Date)) {
				loggerPass("Selecting a date on the Planning Calendar should trigger the creation of a Sales Order for the same date", "selecting a date on the Planning Calendar triggers the creation of a Sales Order for the same date", "yes");
			}
			else {
				loggerFail("Selecting a date on the Planning Calendar should trigger the creation of a Sales Order for the same date", "Selecting a date on the Planning Calendar Not triggers the creation of a Sales Order for the same date", true);
			}
			
	        if (driver.findElements(CreateCloneOrders.closeSalesOrder).size() > 0) {
				javaClickByLocator(CreateCloneOrders.closeSalesOrder);
				javaClickByLocator(CreateCloneOrders.discard);
			}
			
	        
	        
	        
	        if (!(driver.findElements(Cases.SuccessPopUp).size()>0)) {
				loggerPass("User should be able to close out of the Planning Calendar without Creating a new Sales Order", "User is able to close out of the Planning Calendar without Creating a new Sales Order", "yes");
			}else {loggerFail("User should be able to close out of the Planning Calendar without Creating a new Sales Order", "User unable to close out of the Planning Calendar without Creating a new Sales Order", true);}
			
	        closeMultipleTabs();
	        selectObjectFromDropdown(Constants.caseobjectfromlauncher);
	    	javaClickByLocator(By.xpath("//button[contains(@title,'Select a List View:')]"));
			fillField("//input[@placeholder='Search lists...']", ListName);
			Thread.sleep(3000);
			if (driver.findElements(By.xpath("(//span[text()='" + ListName + "']/ancestor::a)[1]")).size() > 0) {
				javaClickByLocator(By.xpath("(//span[text()='" + ListName + "']/ancestor::a)[1]"));
				Thread.sleep(3000);
				tableLoadingWait();
				
				} else if (driver.findElements(By.xpath("(//mark[text()='" + ListName + "']/ancestor::a)[1]")).size() > 0) {
				javaClickByLocator(By.xpath("(//mark[text()='" + ListName + "']/ancestor::a)[1]"));
				
			} else {

				javaClickByLocator(CST_Routing.ListViewSettingBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Cases.NewListView));
				javaClickByLocator(Cases.NewListView);
				fillFieldByLocator(Cases.ListviewName, ListName);
				javaClickByLocator(Cases.NewListView_save);
				Thread.sleep(5000);
				javaClickByLocator(CST_Routing.filterbyowner);
				javaClickByLocator(Cases.allCases);
				javaClickByLocator(CST_Routing.done);
				AddFilter("Status", "equals", "New");
				Thread.sleep(1000);
				AddFilter("Case Record Type", "equals", "Customer Service Ticket");
				AddFilter("Date/Time Opened", "greater than", "01/01/2024");
				javaClickByLocator(CST_Routing.saveFilterBtn);
				tableLoadingWait();
				javaClickByLocator(CST_Routing.CloseFilter);

			}

				
			if (driver.findElements(Cases.first_case).size() > 0) {
				String casenumber = GetText(Cases.first_case);
				loggerInfo("Selected case no is : " + casenumber, "No");
				javaClickByLocator(Cases.first_case);
				isDisplayed(By
						.xpath("//flexipage-tab2[@aria-labelledby='detailTab__item']//lightning-formatted-text[text()='"
								+ casenumber + "']"),
						"Case Details Page");
				if (GetText(Cases.record).equals("Customer Service Ticket")) {
					loggerPass("Case Record Type should be <b>Customer Service Ticket</b>", "Case Record Type is <b>Customer Service Ticket</b>",
							"yes");

					if (driver.findElements(Cases.PlanningCalenderButton).size() > 0) {
						loggerFail("<b>Planning calendar button should not be displayed</b> on Customer Service Ticket",
								"<b>Planning calendar button is displayed</b> on Customer Service Ticket", true);
					} else {
						loggerPass("<b>Planning calendar button should not be displayed</b> on Customer Service Ticket",
								"<b>Planning calendar button is not displayed</b> on Customer Service Ticket", "No");
					}
				} else {
					loggerFail("Case Record Type should be <b>Customer Service Ticket</b>",
							"Case Record Type <b>is Not Customer Service Ticket</b>", true);
				}

			} else {
					loggerFail("cases should be present", "cases are Not present", true);
				}
			
			
			
			
				closeMultipleTabs();
		        selectObjectFromDropdown(Constants.caseobjectfromlauncher);
		    	javaClickByLocator(By.xpath("//button[contains(@title,'Select a List View:')]"));
				fillField("//input[@placeholder='Search lists...']", ListName2);
				Thread.sleep(3000);
				if (driver.findElements(By.xpath("(//span[text()='" + ListName2 + "']/ancestor::a)[1]")).size() > 0) {
					javaClickByLocator(By.xpath("(//span[text()='" + ListName2 + "']/ancestor::a)[1]"));
					Thread.sleep(3000);
					tableLoadingWait();
					
					} else if (driver.findElements(By.xpath("(//mark[text()='" + ListName2 + "']/ancestor::a)[1]")).size() > 0) {
					javaClickByLocator(By.xpath("(//mark[text()='" + ListName2 + "']/ancestor::a)[1]"));
					
				} else {

					javaClickByLocator(CST_Routing.ListViewSettingBtn);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Cases.NewListView));
					javaClickByLocator(Cases.NewListView);
					fillFieldByLocator(Cases.ListviewName, ListName2);
					javaClickByLocator(Cases.NewListView_save);
					Thread.sleep(5000);
					javaClickByLocator(CST_Routing.filterbyowner);
					javaClickByLocator(Cases.allCases);
					javaClickByLocator(CST_Routing.done);
					AddFilter("Status", "equals", "New");
					Thread.sleep(1000);
					AddFilter("Case Record Type", "equals", "Equipment Service Ticket");
					AddFilter("Date/Time Opened", "greater than", "01/01/2024");
					javaClickByLocator(CST_Routing.saveFilterBtn);
					tableLoadingWait();
					javaClickByLocator(CST_Routing.CloseFilter);

				}

					
				if (driver.findElements(Cases.first_case).size() > 0) {
					String casenumber = GetText(Cases.first_case);
					loggerInfo("Selected case no is : " + casenumber, "No");
					javaClickByLocator(Cases.first_case);
					isDisplayed(By
							.xpath("//flexipage-tab2[@aria-labelledby='detailTab__item']//lightning-formatted-text[text()='"
									+ casenumber + "']"),
							"Case Details Page");
					if (GetText(Cases.record).equals("Equipment Service Ticket")) {
						loggerPass("Case Record Type should be <b>Equipment Service Ticket</b>", "Case Record Type is <b>Equipment Service Ticket</b>",
								"yes");

						if (driver.findElements(Cases.PlanningCalenderButton).size() > 0) {
							loggerFail("<b>Planning calendar button should not be displayed</b> on Equipment Service Tickit",
									"<b>Planning calendar button is displayed</b> on Customer Service Ticket", true);
						} else {
							loggerPass("<b>Planning calendar button should not be displayed</b> on Equipment Service Tickit",
									"<b>Planning calendar button is not displayed</b> on Customer Service Ticket", "No");
						}
					} else {
						loggerFail("Case Record Type should be <b>Equipment Service Ticket</b>",
								"Case Record Type is <b>Equipment Service Ticket</b>", true);
					}

				} else {
						loggerFail("cases should be present", "cases are Not present", true);
					}
			
			softAssert.assertAll();

		}
		
		
		
		
		
		
		
		
		
		
		
		// Created by - Niranjan H on 25-07-2024
		// This test verify OB Case Layout Changes
		public static void verifyOutboundPageLayout (HashMap<String, String> OBcase) throws Throwable {
			System.out.println("OB Case Layout Changes");
			softAssert = new SoftAssert();
			String accountName = "";
			String accountNo = "";
			String EquipmentTypeDetail="Cooler";
			ArrayList<String> Contacts;

			switch (Constants.bottler) {
			case "4100":
				accountName = "FIVE STAR FOOD SERVICE INC";
				accountNo = "0500004781";

				break;
			case "4200":
				accountName = "DOLLAR GENERAL #02523";
				accountNo = "0500008854";

				break;
			case "4400":
				accountName = "1984 CLASSIC ARCADE";
				accountNo = "0500287109";

				break;
			case "4500":
				accountName = "DOMINOS PIZZA #5189";
				accountNo = "0600782158";

				break;
			case "4600":
				accountName = "DOMINOS #8134";
				accountNo = "0601603370";

				break;
			case "4700":
				accountName = "AUTOZONE #4440";
				accountNo = "0600927373";

				break;
			case "4800":
				accountName = "DOLLAR GENERAL #11614";
				accountNo = "0600608462";

				break;
			case "4900":
				accountName = "DOMINOS #4080";
				accountNo = "0601230384";

				break;
			case "5000":
				accountName = "DOMINOS #3710";
				accountNo = "0601778750";

				break;
			case "5200":
				accountName = "DOMINOS #3286";
				accountNo = "0601451828";

				break;
			case "5300":
				accountName = "ABBY CONVENIENCE STORE";
				accountNo = "602184829";

				break;
			}

			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			selectAppOrObjFromAppLauncher("object", Constants.accountfromlauncher);
			closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			scrollToElement(Accounts.casesWidget);
			javaClickByLocator(Accounts.casesWidget);
			if (driver.findElements(Cases.CasesInTable).size() > 0) {
				loggerPass("Account related cases should get displayed", "Account related cases get displayed", "No");
			} else {
				loggerFail("Account related cases should get displayed", "Account related cases are not displayed",
						true);
			}

			javaClickByLocator(Accounts.newcase);
			Thread.sleep(4000);
			javaClickByLocator(Accounts.outboundRadioBtn);
			Thread.sleep(3000);
			javaClickByLocator(Accounts.next);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
			if (driver.findElements(Accounts.outboundPopup).size() > 0) {
				loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
				Thread.sleep(4000);
				if (driver.findElements(Accounts.AccountNameField).size() > 0) {
					fillFieldByLocator(Accounts.AccountNameField, accountName+ Keys.ENTER);
					Thread.sleep(1000);
					javaClickByLocator(By.xpath("//lightning-base-combobox-formatted-text[@title='"+accountName+"']"));
					Thread.sleep(1000);
				}
				javaClickByLocator(Accounts.saveBtn);

				Thread.sleep(2000);
				if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					scrollToElement(Cases.CSTPopup);
					fillFieldByLocator(Accounts.AccountNameField, accountName);
					Thread.sleep(1000);
					driver.findElement(
							By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']")).click();
					Thread.sleep(1000);
					javaClickByLocator(Accounts.saveBtn);
				}
				if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
					javaClickByLocator(Accounts.closeSnag);
					javaClickByLocator(Accounts.saveBtn);
					Thread.sleep(2000);
					if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
						javaClickByLocator(Accounts.closeSnag);
						javaClickByLocator(Accounts.saveBtn);
					}
				}
				
			} else {
				loggerFail("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page not displayed", true);
			}
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
			if (driver.findElements(Cases.DetailsTabOB).size() > 0) {
				loggerPass("Details tab should be display in case page", "Details tab is displayed in case page",
						"No");
			} else {
				loggerFail("Details tab should be display in case page", "Details tab not displayed in case page",
						true);
			}
			driver.get(driver.getCurrentUrl());
			
			Thread.sleep(3000);
			salesforce_ScrollDown(3);
			Thread.sleep(2000);
			isDisplayedWithScreenshot(Cases.ARConCase, "Active Related Contacts section On Outbound case page layout", "yes");
			isDisplayedWithScreenshot(Cases.Tasksforparentaccount, "Open Tasks for Parent Account On Outbound case page layout", "yes");
			isDisplayedWithScreenshot(Accounts.orderForParentAccountWidget, "Order for Parent Account On Outbound case page layout", "yes");
			scrollToElement(Accounts.orderForParentAccountWidget);
			javaClickByLocator(Accounts.orderForParentAccountWidget);
			Thread.sleep(3000);
			javaClickByLocator(Cases.OrderNumbercol);
			if (driver.findElements(Cases.Sortbuuton).size() > 0) {
				loggerPass("User should be able to sort Orders for Parent Account component manually",
						"User is able to sort Orders for Parent Account component manually", "No");
			} else {
				loggerFail("User should be able to sort Orders for Parent Account component manually",
						"User is not able to sort Orders for Parent Account component manually", true);
			}
			javaClickByLocator(Cases.CloseOrders);
			Thread.sleep(2000);
			javaClickByLocator(By.xpath("//button[@title='Close " + GetText(Accounts.caseNumberValue) + " | Case']"));
			Thread.sleep(2000);
//			javaClickByLocator(By.xpath("//a[@title='Cases']"));
					javaClickByLocator(Accounts.newcase);
					javaClickByLocator(Accounts.EquipmentConfirmationTickit);
					javaClickByLocator(Accounts.next);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Accounts.EquipmentConfirmationTickitpage));
					if (driver.findElements(Accounts.EquipmentConfirmationTickitpage).size() > 0) {
						loggerPass("<b>Equipment Confirmation Service Ticket</b>page should be displayed", "<b>Equipment Confirmation Service Ticket </b>page is displayed", "No");
					}else {
						loggerFail("<b>Equipment Confirmation Service Ticket </b>page should be displayed", "<b>Equipment Confirmation Service Ticket </b>page not displayed", true);
					}
					select_dropdownValue("Cooler", By.xpath("//button[@aria-label='Equipment Type']"));
					Thread.sleep(2000);
					
					
					javaClickByLocator(By.xpath("//button[@aria-label='Equipment Type Detail']"));
					new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//div[@aria-label='Equipment Type Detail']//span[@title= '" + EquipmentTypeDetail + "']"))));
					javaClickByLocator(By.xpath("//div[@aria-label='Equipment Type Detail']//span[@title= '" + EquipmentTypeDetail + "']"));
				
					Thread.sleep(2000);		
					select_dropdownValue("Bad Odor", By.xpath("//button[@aria-label='EST Problem Reported']"));
					driver.findElement(Cases.DetailedDescription).sendKeys("Test");
					javaClickByLocator(Accounts.saveBtn);

					Thread.sleep(2000);
					if (driver.findElements(Accounts.AccountNameSnag).size() > 0) {
						javaClickByLocator(Accounts.closeSnag);
						scrollToElement(Cases.CSTPopup);
						fillFieldByLocator(Accounts.AccountNameField, accountName);
						Thread.sleep(1000);
						driver.findElement(
								By.xpath("//lightning-base-combobox-formatted-text[@title='" + accountName + "']"))
								.click();
						Thread.sleep(1000);
						javaClickByLocator(Accounts.saveBtn);
					}
					if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
						javaClickByLocator(Accounts.closeSnag);
						javaClickByLocator(Accounts.saveBtn);
						Thread.sleep(2000);
						if (driver.findElements(Accounts.ExclusiveAccessSnag).size() > 0) {
							javaClickByLocator(Accounts.closeSnag);
							javaClickByLocator(Accounts.saveBtn);
						}
					}
		
					
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));
					salesforce_ScrollDown(1);
					Thread.sleep(3000);
					
					if (!(driver.findElements(Cases.CI_ECST_Page).size() > 0)) {
						if (!(driver.findElements(Cases.SalesAid_ECST_Page).size() > 0)) {
							if (!(driver.findElements(Cases.CaseHistory_ECST_Page).size() > 0)) {
								if (!(driver.findElements(Cases.Order_ECST_Page).size() > 0)) {
									loggerPass(
											"<b>Customer Instructions,Sales Aid,Case History,Orders for parent Account</b> Shold not be displayed on Equipment Confirmation Service Tickit",
											"<b>Customer Instructions,Sales Aid,Case History,Orders for parent Account</b> is not be displayed on Equipment Confirmation Service Tickit",
											"Yes");
								} else {
									loggerFail(
											"Orders for parent Account Shold not be displayed on Equipment Confirmation Service Tickit",
											"Orders for parent Account is displayed on Equipment Confirmation Service Tickit",
											true);

								}
							} else {
								loggerFail(
										"Case History Shold not be displayed on Equipment Confirmation Service Tickit",
										"Case History is displayed on Equipment Confirmation Service Tickit", true);

							}
						} else {
							loggerFail("Sales Aid Shold not be displayed on Equipment Confirmation Service Tickit",
									"Sales Aid is displayed on Equipment Confirmation Service Tickit", true);

						}
					} else {
						loggerFail(
								"Customer Instructions Shold not be displayed on Equipment Confirmation Service Tickit",
								"Customer Instructions is displayed on Equipment Confirmation Service Tickit", true);
					}
					
					driver.get(driver.getCurrentUrl());
					Thread.sleep(2000);
					salesforce_ScrollDown(2);
					
					if (driver.findElements(Cases.OutboundDispositionECST_Page).size() > 0) {
						loggerPass("Outbound Disposition section Shold be displayed on Equipment Confirmation Service Tickit Page layout", "Outbound Disposition section displayed on Equipment Confirmation Service Tickit Page layout", "No");
						if (driver.findElements(Cases.ARCON_ECST_Page).size() > 0) {
							loggerPass("Active Related Contact section Shold be displayed on Equipment Confirmation Service Tickit Page layout", "Active Related Contact section displayed on Equipment Confirmation Service Tickit Page layout", "No");
							if (driver.findElements(Cases.Tasks_ECST_Page).size() > 0) {
								loggerPass("Open Tasks for Parent Account Shold be displayed on Equipment Confirmation Service Tickit Page layout", "Open Tasks for Parent Account displayed on Equipment Confirmation Service Tickit Page layout", "No");
									if (driver.findElements(Cases.UpcomingAndOverdues_ECST_Page).size() > 0) {
										loggerPass(
												"Upcoming And Overdue Shold be displayed on Equipment Confirmation Service Tickit Page layout",
												"Upcoming And Overdue is displayed on Equipment Confirmation Service Tickit Page layout",
												"No");
									} else {
										loggerFail(
												"Upcoming And Overdue Shold be displayed on Equipment Confirmation Service Tickit Page layout",
												"Upcoming And Overdue is not displayed on Equipment Confirmation Service Tickit",
												true);

									}
								
							} else {
								loggerFail(
										"Open Tasks for Parent Account Shold be displayed on Equipment Confirmation Service Tickit Page layout",
										"Open Tasks for Parent Account is not displayed on Equipment Confirmation Service Tickit",
										true);

							}

						} else {
							loggerFail(
									"Active Related Contact section Shold be displayed on Equipment Confirmation Service Tickit Page layout",
									"Active Related Contact section is not displayed on Equipment Confirmation Service Tickit",
									true);

						}

					} else {
						loggerFail(
								"Outbound Disposition section Shold be displayed on Equipment Confirmation Service Tickit Page layout",
								"Outbound Disposition section is not displayed on Equipment Confirmation Service Tickit",
								true);

					}
					
		}
			
			
			
			
			
}
