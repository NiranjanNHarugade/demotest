package Features;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.hotkey.Keys;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import Pages.Accounts;
import Pages.CST_Routing;
import Pages.Cases;
import Pages.Contacts;
import Pages.HomePage;
import Pages.Reports;
import Pages.RetailStoreGroups;
import Utilities.CommonFunctions;
import Utilities.Constants;
import net.bytebuddy.asm.Advice.FieldSetterHandle;

public class Features_Account extends CommonFunctions {

	private static final String WebDriver = null;
	public static boolean validationFlag=true;
	
	
	
	// Created By - Niranjan H on 18-05-2023
	// Description :- This method is used to verify Instructions like:-
	// Call,Order,Dispatcher,Drivers are visible on Accounts Details Page.
	public void Customer_Instruction(HashMap<String, String> createCSTData) throws Throwable {
		System.out.println("Varify Customers Instructions");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";

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
			accountName = "10 BARRELS BREWING LLC";
			accountNo = "0500807779";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "601230384";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "601731108";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "601451828";
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
		isDisplayed(Accounts.CIwidget, "Customer Instructions");
		Thread.sleep(2000);

		Validate_Instruction("Call Handling");
	
		Validate_Instruction("Dispatch Instructions");
	
		Validate_Instruction("Driver Instructions");
	
		Validate_Instruction("Order Instructions");

		softAssert.assertAll();

	}

//  Created By - Niranjan H on 18-05-2023
//	 Varify Instructions like:- Call,Order,Dispatcher,Drivers are visible on Accounts Details Page.
	public static void Validate_Instruction(String InstructionName) throws Throwable {
		String Expected_note = null;
		Thread.sleep(1000);
		if (!(driver.findElements(By.xpath("//a[text()='" + InstructionName + "']")).size() > 0)) {
			javaClickByLocator(Accounts.CI_Object);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.presenceOfElementLocated(Accounts.instructionsnewBtn));
			javaClickByLocator(Accounts.instructionsnewBtn);
			select_dropdownValue(InstructionName, Accounts.dropdown);
			String actualNote = "Test";
			fillFieldByLocator(Accounts.note, actualNote);
			driver.findElement(Accounts.dropdown).click();
			Thread.sleep(1000);
			javaClickByLocator(Accounts.saveBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.presenceOfElementLocated(Accounts.closeinstruction));
			javaClickByLocator(Accounts.closeinstruction);
			if (driver.findElements(Accounts.Close_CI).size()>0) {
				javaClickByLocator(Accounts.Close_CI);
			}
			Thread.sleep(3000);
			driver.navigate().to(driver.getCurrentUrl());

		}

		String xpath = "//a[text()='" + InstructionName + "']";
		isDisplayed(By.xpath(xpath), InstructionName);
		String Actual_note = driver.findElement(By.xpath(
				"//a[text()='" + InstructionName + "']/ancestor::li[@class='slds-item']//lightning-formatted-text"))
				.getText();
		By link = By.xpath("//a[text()='" + InstructionName + "']");
		Thread.sleep(2000);
		javaClickByLocator(link);

//		driver.findElement((link)).click();
		Thread.sleep(2000);
		Expected_note = driver.findElement(Accounts.actualnote).getText();
		if (Expected_note.equalsIgnoreCase(Actual_note)) {
			loggerPass(Expected_note, Actual_note, "yes");
		} else {
			loggerFail(Expected_note, Actual_note, false);
		}

		isEditable("//li[@data-target-selection-name='sfdc:StandardButton.CustomerInstruction__c.Edit']",
				InstructionName);
		Thread.sleep(2000);
		while (true) {
			if (driver.findElements(Accounts.closeinstruction).size() > 0) {
				for (WebElement X : driver.findElements(Accounts.closeinstruction)) {
					javaClickByWebElement(X);
				}
			} else {
				break;
			}
		}

	}

	// Created By - Niranjan H on 07-12-23
	// Description :- Account page layout in the ICEP to display the 'Active Related Contacts' list
	public void Verify_ActiveRelatedContacts(HashMap<String, String> createCSTData) throws Throwable {
		System.out.println("Active Related Contacts list");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";

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

		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		if (driver.findElement(Accounts.ActiveRelatedContactsWidget).isDisplayed()) {
			loggerPass("<b>Active Related Contacts</b> should be displayed on Accounts details page",
					"<b>Active Related Contacts</b> displayed on Accounts details page", "yes");
		} else {
			loggerFail("<b>Active Related Contacts</b> should be displayed on Accounts details page",
					"<b>Active Related Contacts</b>Not displayed on Accounts details page", true);

		}
		Thread.sleep(2000);
		if (driver.findElements(Accounts.listofcontacts).size() > 0) {
			loggerPass("Contacts list should be displayed", "Contacts list is displayed", "No");
		} else {
			loggerFail("Contacts list should be displayed", "Contacts list Not displayed", true);
		}

		List<WebElement> ContactNames = driver.findElements(Accounts.ContactNames);
		for (WebElement name : ContactNames) {
			if (name.getText().equalsIgnoreCase("")) {
				loggerFail("Name field " + name.getText() + "Should be displayd",
						"Name field " + name.getText() + " Not displayd", true);
			}
			;

		}
		loggerPass("Name field Should be displayd", "Name field displayd", "No");

		if (driver.findElement(Accounts.PrimaryPhone).isDisplayed()) {
			loggerPass("<b>Primary Phone</b> field should be displayed",
					"<b>Primary Phone</b> field Phone is displayed", "No");

		} else {
			loggerFail("<b>Primary Phone</b> should be displayed", "<b>Primary Phone</b> Not displayed", true);
		}

		if (driver.findElement(Accounts.Email).isDisplayed()) {
			loggerPass("<b>Email</b> field should be displayed", "<b>Email</b> field is displayed", "No");
		} else {
			loggerFail("<b>Email</b> field should be displayed", "<b>Email</b> field is not displayed", true);
		}

		if (driver.findElement(Accounts.MyCokeRoles).isDisplayed()) {
			loggerPass("<b>MyCoke Roles</b> field should be displayed", "<b>MyCoke Roles</b> field is displayed", "No");
		} else {
			loggerFail("<b>MyCoke Roles</b> field should be displayed", "<b>MyCoke Roles</b> field is not displayed",
					true);
		}

		javaClickByLocator(Accounts.ViewAllActiveRelatedContacts);
		Thread.sleep(2000);
		fieldValidation("Columns", Constants.AccountrelatedContactsColumns);
		javaClickByLocator(Accounts.CloseContact);

		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Accounts.AllContacts));
		List<WebElement> AllContacts = driver.findElements(Accounts.AllContacts);
		WebElement FirstContact = AllContacts.get(0);

		loggerInfo("Selected Contact Name " + FirstContact.getText() + "", "No");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", FirstContact);
		isDisplayed(Accounts.ContactDetailsPage, " Contact Detail Page for " + FirstContact.getText() + "");

	}

	// Created By - Niranjan H on 12-12-23
	// Description :- Contact Priority should be created in the
	// AccountContactRelation layout
	public void Verify_ContactPriority(HashMap<String, String> createCSTData) throws Throwable {
		System.out.println("Active Related Contacts list");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "#1 QUALITY REALTY";
			accountNo = "0500092859";
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

		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		if (driver.findElement(Accounts.ActiveRelatedContactsWidget).isDisplayed()) {
			loggerPass("<b>Active Related Contacts</b> should be displayed on Accounts details page",
					"<b>Active Related Contacts</b> displayed on Accounts details page", "yes");
		} else {
			loggerFail("<b>Active Related Contacts</b> should be displayed on Accounts details page",
					"<b>Active Related Contacts</b>Not displayed on Accounts details page", true);

		}
		Thread.sleep(2000);
		if (driver.findElements(Accounts.listofcontacts).size() > 0) {
			loggerPass("Contacts list should be displayed", "Contacts list is displayed", "No");
		} else {
			loggerFail("Contacts list should be displayed", "Contacts list Not displayed", true);
		}
		javaClickByLocator(Accounts.ViewAllActiveRelatedContacts);
		Thread.sleep(2000);
		List<WebElement> AllContacts = driver.findElements(Accounts.AllContactsARC);
		WebElement FirstContact = AllContacts.get(0);
		String ContactName = FirstContact.getText();
		loggerInfo("Selected Contact Name " + ContactName + "", "No");
		javaClickByWebElement(FirstContact);
		isDisplayed(Accounts.ContactDetailsPage, " Contact Detail Page for " + ContactName + "");
		javaClickByLocator(Accounts.Contacts_Relatedtab);
		Thread.sleep(2000);
		isDisplayed(Accounts.RelatedAccounts, "Related Accounts");
		Thread.sleep(2000);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.AddRelationship));
		javaClickByLocator(Accounts.AddRelationship);
		isDisplayed(Accounts.ContactPriorityPickllist, "Contact Priority Picklist");

		try {
			driver.findElement(Accounts.ContactPriorityPickllist).click();
			loggerPass("Contact Priority Should be Editable", "Contact Priority is Editable", "No");
		} catch (Exception e) {
			loggerFail("Contact Priority Should be Editable", "Contact Priority is not Editable", true);
		}
		if (driver.findElements(Accounts.ContactPriorityPickllist).size() > 0) {
			javaClickByLocator(Accounts.ContactPriorityPickllist);
			Thread.sleep(1000);
			loggerInfo("Contact Priority Picklist Validation","Yes");
			List<String> present = new ArrayList<String>();
			List<String> notpresent = new ArrayList<String>();
			for (String fieldName : ContactPriorityDropDown) {
				if (driver
						.findElements(By
								.xpath("//lightning-base-combobox-item//span[text()='" + fieldName + "']"))
						.size() != 0) {

					present.add(fieldName);
				} else {
					notpresent.add(fieldName);
					validationFlag = false;
				}
			}
			fieldValidation("Contact Priority options validation", present, notpresent);
			present.clear();
			notpresent.clear();

		}else {
			loggerFail("Contact Priority field should be display", "Contact Priority is not displayed", false);

		}
		
		javaClickByLocator(Accounts.closeRealationship);
		javaClickByLocator(By.xpath("//button[@title='Close " + ContactName + " | Contact']"));

	}

//	This test is not executed - Target Group Members takes time to autopopulate.
//	Created By - Niranjan H on 12-12-23
//	Description :- Primary, Secondary and Tertiary should populated on outbound case created from Call list
	public void Verify_ContactPriorityOnOutbound(HashMap<String, String> createCSTData) throws Throwable {

		Verify_ContactPriority(createCSTData);
		String PrimaryContact = "";
		String SecondaryContact = "";
		String TertiaryContact = "";
		String RSGname = "Contact Priority Test " + generateRandomInteger();
		String reportUniqueName = "";
		String startdate = dateToday();
		String enddate = increaseOrDecreaseDate(startdate, "day", 2);
		String accountname = "";
		String accountnumber = "";
		switch (Constants.bottler) {
		case "4100":
			reportUniqueName = "X4100_CallList";
			break;
		case "4200":
			reportUniqueName = "X4200_ContactPriority";
			break;
		case "4400":
			reportUniqueName = "X4400_CallList";
			break;
		case "4500":
			reportUniqueName = "X4500_CallList";
			break;
		case "4600":
			reportUniqueName = "New_Accounts_Report_27nov";
			break;
		case "4700":
			reportUniqueName = "X4700_CallList";
			break;
		case "4800":
			reportUniqueName = "X4800_CallList";
			break;
		case "4900":
			reportUniqueName = "X4900_CallList";
			break;
		case "5000":
			reportUniqueName = "X5000_ContactPriority";
			break;
		case "5200":
			reportUniqueName = "X5200_ContactPriority";
			break;
		case "5300":
			reportUniqueName = "X5300_CallList";
			break;
		}
		Thread.sleep(2000);
		List<WebElement> AllContacts = driver.findElements(Accounts.AllContactsARC);
		List<WebElement> Allcontactpriority = driver.findElements(Accounts.ContactPrioritycol);

		for (String ReqPriority : Constants.ContactPriorityDropDown) {

			for (int i = 0; i <= AllContacts.size(); i++) {
				if (i == AllContacts.size()) {
					loggerFail(ReqPriority + " Contact should be present for Account",
							ReqPriority + " Contact is not present for Account", false);
					break;
				}

				String ContactName = AllContacts.get(i).getText();
				String priority = Allcontactpriority.get(i).getAttribute("title");

				if (priority.equals(ReqPriority)) {
					loggerInfo(ReqPriority + " Contact Maintained for Account", "No");
					if (priority.equals("Primary")) {
						PrimaryContact = ContactName;
					}
					if (priority.equals("Secondary")) {
						SecondaryContact = ContactName;
					}
					if (priority.equals("Tertiary")) {
						TertiaryContact = ContactName;
					}
					break;
				}

			}

		}
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		javaClickByLocator(RetailStoreGroups.newbtn);
		isDisplayed(RetailStoreGroups.newrsg, "New Retail store group page");
		javaClickByLocator(RetailStoreGroups.CallList);
		javaClickByLocator(RetailStoreGroups.next);
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.RSGname));
		isDisplayed(RetailStoreGroups.NewCallList, "New Retail store group page:Call List");
		fillFieldByLocator(RetailStoreGroups.RSGname, RSGname);
		fillFieldByLocator(RetailStoreGroups.uniquename, reportUniqueName);
		Thread.sleep(2000);
		driver.findElement(RetailStoreGroups.rsgDescription).sendKeys("Test");

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
		new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.CallListwidget));
		javaClickByLocator(RetailStoreGroups.PopulateCallList);
		new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.geneateCallList));
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
			accountname = GetText(RetailStoreGroups.TSGaccountname);
			accountnumber = GetText(RetailStoreGroups.TSGaccountnumber);
		} catch (Exception e) {
			driver.navigate().to(driver.getCurrentUrl());
			accountname = GetText(RetailStoreGroups.TSGaccountname);
			accountnumber = GetText(RetailStoreGroups.TSGaccountnumber);
		}
		isDisplayed(RetailStoreGroups.targetGroupSize, "Target Group Size");
		driver.navigate().to(driver.getCurrentUrl());
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", Constants.RSGFromLauncger);
		fillFieldByLocator(RetailStoreGroups.serachrsg, RSGname);
		driver.findElement(RetailStoreGroups.serachrsg).sendKeys(RSGname + Keys.ENTER);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + RSGname + "']")))
				.click();
		if (driver.findElements(RetailStoreGroups.calllistitems).size() > 0) {
			loggerPass("Call list should populate automatically", "Call list is populated automatically", "No");
		} else {
			loggerFail("Call list should populate automatically", "Call list not populated automatically", true);
		}
		String calllistnumber = GetText(RetailStoreGroups.calllistitems);
		javaClickByLocator(RetailStoreGroups.calllistitems);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(RetailStoreGroups.casesoncalllist));
		scrollToElement(RetailStoreGroups.casesoncalllist);
		isDisplayed(RetailStoreGroups.casesoncalllist, "Cases On call List");
		javaClickByLocator(RetailStoreGroups.NewCase);
		if (driver.findElements(Cases.newCase).size() > 0) {
			loggerPass("Different case record types list should be display",
					"Different case record types list is displayed", "Yes");
			Thread.sleep(3000);
			javaClickByLocator(Accounts.outboundRadioBtn);
			Thread.sleep(3000);
			javaClickByLocator(Accounts.next);
			Thread.sleep(3000);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
			Thread.sleep(3000);

			if (driver.findElements(Accounts.outboundPopup).size() > 0) {
				loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
				Thread.sleep(2000);
				driver.findElement(Accounts.AccountNameField).sendKeys(accountname + Keys.ENTER);
				javaClickByLocator(RetailStoreGroups.firstresult);
				Thread.sleep(2000);
				scrollToElement(RetailStoreGroups.GenesysCloudCall);
				driver.findElement(RetailStoreGroups.GenesysCloudCall).sendKeys("US-" + Keys.ENTER);
				javaClickByLocator(RetailStoreGroups.firstresult);
				javaClickByLocator(Accounts.saveBtn);
			}

			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));

			Thread.sleep(10000);
			String caseNumber = GetText(Accounts.caseNumberValue);
			Thread.sleep(5000);

			if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
				loggerPass("New Case should be created <b>" + caseNumber + "</b>",
						"New Case is created <b>" + caseNumber + "</b>", "Yes");
			}
		}
		salesforce_ScrollDown(2);

		if (driver.findElements(RetailStoreGroups.PrimaryContact).size() > 0) {
			if (GetText(RetailStoreGroups.PrimaryContact).equalsIgnoreCase(PrimaryContact)) {
				loggerPass("Primary contact should be same as present on Active related contacts",
						"Primary contact is same as present on Active related contacts", "No");
			} else {
				loggerFail("Primary contact should be same as present on Active related contacts",
						"Primary contact is Not same as present on Active related contacts", false);
			}
		} else {
			loggerFail("Business Contact Should be displaed", "Business Contact is Not displaed", true);
		}

		if (driver.findElements(RetailStoreGroups.SecondaryContact).size() > 0) {
			if (GetText(RetailStoreGroups.SecondaryContact).equalsIgnoreCase(SecondaryContact)) {
				loggerPass("Secondary Contact should be same as present on Active related contacts",
						"Secondary Contact is same as present on Active related contacts", "No");
			} else {
				loggerFail("Secondary Contact should be same as present on Active related contacts",
						"Secondary Contact is Not same as present on Active related contacts", false);
			}
		} else {
			loggerFail("Secondary Contact Should be displaed", "Secondary Contact is Not displaed", true);
		}

		if (driver.findElements(RetailStoreGroups.TertiaryContact).size() > 0) {
			if (GetText(RetailStoreGroups.TertiaryContact).equalsIgnoreCase(TertiaryContact)) {
				loggerPass("Tertiary Contact should be same as present on Active related contacts",
						"Tertiary Contact is same as present on Active related contacts", "No");
			} else {
				loggerFail("Tertiary Contact should be same as present on Active related contacts",
						"Tertiary Contact is Not same as present on Active related contacts", false);
			}
		} else {
			loggerFail("Tertiary Contact Should be displaed", "Secondary Contact is Not displaed", true);
		}
	}

	// Created By - Abhishek Palankar on 18-12-2023
	// Description :- This test is to verify that Case Creation From Contact from
	// decision tree- Default the Account, if the Contact has only one associated
	// account.
	public void contactAssociatedAccounts(HashMap<String, String> createCSTData) throws Throwable {
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";

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
			accountName = "10 BARRELS BREWING LLC";
			accountNo = "0500807779";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "600608462";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "601230384";
			break;
		case "5000":
			accountName = "DOMINOS #3710";
			accountNo = "0601778750";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "601451828";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			break;
		}
		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		SelectAccountFromGlobalSearch(accountNo, accountName);
		scrollToElement(Accounts.relatedContact);
		javaClickByLocator(Accounts.relatedContact);

		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.businessContact));
		javaClickByLocator(Accounts.firstContact);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.ContactDetailsTab));

		if (driver.findElements(Accounts.ContactDetailsTab).size() > 0) {
			loggerPass("Details tab should be displayed in Contact page", "Details tab is displayed in Contact page",
					"Yes");

			if (driver.findElements(Accounts.ContactRelatedTab).size() > 0) {
				loggerPass("Related tab should be displayed in Contact page",
						"Related tab is displayed in Contact page", "Yes");

				if (driver.findElements(Accounts.ContactCreateCaseTab).size() > 0) {
					loggerPass("Create Case tab should be displayed in Contact page",
							"Create Case tab is displayed in Contact page", "Yes");
					javaClickByLocator(Accounts.ContactCreateCaseTab);

					Thread.sleep(2000);

					if (driver.findElements(Accounts.multipleAccountError).size() == 0) {

						loggerPass("Contact should be associated to only one account",
								"Contact is associated to only one account", "Yes");
					} else {
						loggerFail("Contact should be associated to only one account",
								"Contact is associated to multiple accounts", true);
					}

				} else {
					loggerFail("Create Case tab should be displayed in Contact page",
							"Create Case tab is not displayed in Contact page", true);
				}

			} else {
				loggerFail("Related tab should be displayed in Contact page",
						"Related tab is not displayed in Contact page", true);
			}

		} else {
			loggerFail("Details tab should be displayed in Contact page",
					"Details tab is not displayed in Contact page", true);
		}

		softAssert.assertAll();

	}

	// Created By - Niranjan H on 20-12-23
	// Description :- Contact Priority should be created in the
	// AccountContactRelation layout
	public void Verify_ContactPriority_Sync(HashMap<String, String> createCSTData) throws Throwable {
		System.out.println("Contact Priority Synchronization");
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		String ContactDetailsPriority = "";
		String UpdatedPriority = "";
		switch (Constants.bottler) {
		case "4100":
			accountName = "FIVE STAR FOOD SERVICE INC";
			accountNo = "0500004781";
			break;
		case "4200":
			accountName = "MICHAEL BRADY INC";
			accountNo = "0500018594";
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
			accountName = "DOMINOS PIZZA #10273";
			accountNo = "0602129363";
			break;
		}

		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		SelectAccountFromGlobalSearch(accountNo, accountName);
		if (driver.findElement(Accounts.ActiveRelatedContactsWidget).isDisplayed()) {
			loggerPass("<b>Active Related Contacts</b> should be displayed on Accounts details page",
					"<b>Active Related Contacts</b> displayed on Accounts details page", "yes");
		} else {
			loggerFail("<b>Active Related Contacts</b> should be displayed on Accounts details page",
					"<b>Active Related Contacts</b>Not displayed on Accounts details page", true);

		}
		Thread.sleep(2000);
		if (driver.findElements(Accounts.listofcontacts).size() > 0) {
			loggerPass("Contacts list should be displayed", "Contacts list is displayed", "No");
		} else {
			loggerFail("Contacts list should be displayed", "Contacts list Not displayed", true);
		}

		javaClickByLocator(Accounts.ViewAllActiveRelatedContacts);
		Thread.sleep(2000);
		List<WebElement> AllContacts = driver.findElements(Accounts.AllContactsARC);
		WebElement FirstContact = AllContacts.get(0);
		String ContactName = FirstContact.getText();
		loggerInfo("Selected Contact Name " + ContactName + "", "No");
		javaClickByWebElement(FirstContact);
		isDisplayed(Accounts.ContactDetailsPage, "Contact Detail Page for " + ContactName + "");
		javaClickByLocator(Accounts.Contacts_Relatedtab);
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//article[@aria-label='Related Accounts']//a//slot[text()='" + accountName + "']")));
		driver.findElement(By.xpath("//article[@aria-label='"+accountName+"']//span[text()='Show Actions']/ancestor::button")).click();
		javaClickByLocator(Accounts.ViewRelationship);
		isDisplayed(
				By.xpath("//records-entity-label[text()='Account Contact Relationship']/ancestor::records-highlights2//slot[text()='"+accountName+"']"),
				"Account contact Relationship for " + ContactName);
		
		String PriviousValue = "";
		if (driver.findElement(Accounts.ContactpriorityOnrelationship).getText().isEmpty() == false) {
			PriviousValue = driver.findElement(Accounts.ContactpriorityOnrelationship).getText();
			loggerInfo("Contact Priority value is " + PriviousValue + "", "No");
		} else {
			loggerInfo("Contact Priority value is Null", "No");
		}

		scrollToElement(Accounts.Relationship_EditCP);
		Thread.sleep(5000);
		javaClickByLocator(Accounts.Relationship_EditCP);
		Thread.sleep(2000);
		scrollToElement(Accounts.ContactPriorityPickllist);
		driver.findElement(Accounts.ContactPriorityPickllist).click();
		if (PriviousValue.equalsIgnoreCase("")) {
			
			javaClickByLocator(Accounts.Primary);
			loggerInfo("Contact Priority is changed to Primary from Account Contact Relationship", "No");
		} else if (PriviousValue.equalsIgnoreCase("Primary")) {
			javaClickByLocator(Accounts.Secondary);
			loggerInfo("Contact Priority is changed to Secondary from Account Contact Relationship", "No");
		} else if (PriviousValue.equalsIgnoreCase("Secondary")) {
			javaClickByLocator(Accounts.Tertiary);
			loggerInfo("Contact Priority is changed to Tertiary from Account Contact Relationship", "No");
		} else if (PriviousValue.equalsIgnoreCase("Tertiary")) {
			javaClickByLocator(Accounts.Primary);
			loggerInfo("Contact Priority is changed to Primary from Account Contact Relationship", "No");

		}
		Thread.sleep(2000);
		javaClickByLocator(Accounts.saveBtn);
		Thread.sleep(2000);
		 new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.ContactpriorityOnrelationship));
		 UpdatedPriority =GetText(Accounts.ContactpriorityOnrelationship);
		javaClickByLocator(By.xpath("//a[@title='" + ContactName + " | Contact']"));
		Thread.sleep(2000);
		driver.navigate().to(driver.getCurrentUrl());
		ContactDetailsPriority = new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.ContactpriorityOnContacts)).getText();
		if (UpdatedPriority.equals(ContactDetailsPriority)) {
			loggerPass("Contact Priority should gets updated on the <b>Contact detail</b> page",
					"Contact Priority gets updated on the <b>Contact detail</b> page", "yes");
		} else {
			loggerFail("Contact Priority should gets updated on the <b>Contact detail</b> page",
					"Contact Priority Not updated on the <b>Contact detail</b> page", true);
		}

		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Contacts_EditCP));
		javaClickByLocator(Accounts.Contacts_EditCP);
		Thread.sleep(2000);
		if (PriviousValue == "") {
			select_dropdownValue("--None--", Accounts.CPPickllist);
			Thread.sleep(2000);
		} else {
			select_dropdownValue(PriviousValue, Accounts.CPPickllist);
			loggerInfo("Contact Priority is changed to " + PriviousValue + " from Contact Details Page", "yes");
		}

		Thread.sleep(2000);
		javaClickByLocator(Accounts.saveBtn);
	
		Thread.sleep(5000);
		if (driver.findElement(Accounts.ContactpriorityOnContacts).getText().isEmpty() == true) {
			ContactDetailsPriority = "";
			
		} else {
			ContactDetailsPriority = new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.ContactpriorityOnContacts)).getText();
			
		}
		Thread.sleep(2000);
		javaClickByLocator(Accounts.Tab_AccountContactRelationship);
		if (driver.findElement(Accounts.ContactpriorityOnrelationship).getText().isEmpty() == true) {
			UpdatedPriority = "";
			
			
		} else {
			UpdatedPriority = new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Accounts.ContactpriorityOnrelationship)).getText();
			
			
		}
		if (UpdatedPriority.equals(ContactDetailsPriority)) {
			loggerPass("Contact Priority should gets updated on the <b>Accounts Contacts Relationship</b> page",
					"Contact Priority gets updated on the <b>Accounts Contacts Relationship</b> page", "yes");
		} else {
			loggerFail("Contact Priority should gets updated on the <b>Accounts Contacts Relationship</b> page",
					"Contact Priority Not updated on the <b>Accounts Contacts Relationship</b> page", true);
		}
	}

	
	// Created By - Niranjan H on 21-02-2024
	// Description :- This test is to verify that Case Creation From Contact from decision tree-if the Contact has Multiple associated account.
	public void contactAssociated_MultipleAccounts(HashMap<String, String> createCSTData) throws Throwable {
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";

		switch (Constants.bottler) {
		case "4100":
			accountName = "AMERICAN LEGION POST #95";
			accountNo = "0600131790";
			break;
		case "4200":
			accountName = "DOMINOS PIZZA #5678";
			accountNo = "0501141465";
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
			accountName = "DOMINOS PIZZA #6302";
			accountNo = "0501354348";
			break;
		case "4800":
			accountName = "DOMINOS #6504";
			accountNo = "0501099714";
			break;
		case "4900":
			accountName = "CUPKAS #2";
			accountNo = "0601206132";
			break;
		case "5000":
			accountName = "1 FOOD MART";
			accountNo = "0601737203";
			break;
		case "5200":
			accountName = "BOSTON MARKET #1728";
			accountNo = "0601471691";
			break;
		case "5300":
			accountName = "DOMINOS PIZZA #10273";
			accountNo = "0602129363";
			break;
		}
		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		SelectAccountFromGlobalSearch(accountNo, accountName);
		salesforce_ScrollDown(3);
		javaClickByLocator(Accounts.relatedContact);

		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.businessContact));
		javaClickByLocator(Accounts.firstContact);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.ContactDetailsTab));

		if (driver.findElements(Accounts.ContactDetailsTab).size() > 0) {
			loggerPass("Details tab should be displayed in Contact page", "Details tab is displayed in Contact page",
					"Yes");

			if (driver.findElements(Accounts.ContactRelatedTab).size() > 0) {
				loggerPass("Related tab should be displayed in Contact page",
						"Related tab is displayed in Contact page", "Yes");

				if (driver.findElements(Accounts.ContactCreateCaseTab).size() > 0) {
					loggerPass("Create Case tab should be displayed in Contact page",
							"Create Case tab is displayed in Contact page", "Yes");
					javaClickByLocator(Accounts.ContactCreateCaseTab);

					Thread.sleep(2000);

					if (driver.findElements(Accounts.multipleAccountError).size() == 0) {

						loggerFail("Contact should be associated to multiple accounts",
								"Contact is associated to only one accounts", true);
					} else {
						loggerPass("Contact should be associated to multiple accounts",
								"Contact is associated to multiple account", "Yes");
					}

				} else {
					loggerFail("Create Case tab should be displayed in Contact page",
							"Create Case tab is not displayed in Contact page", true);
				}

			} else {
				loggerFail("Related tab should be displayed in Contact page",
						"Related tab is not displayed in Contact page", true);
			}

		} else {
			loggerFail("Details tab should be displayed in Contact page",
					"Details tab is not displayed in Contact page", true);
		}
		List<String> present = new ArrayList<String>();
		List<String> notpresent = new ArrayList<String>();

		for (String fieldName : Constants.RelatedAccount_columns) {
			if (driver.findElements(By.xpath("//th[@role='columnheader']//span[text()='" + fieldName + "']")).size() != 0) {
				present.add(fieldName);
			} else {
				notpresent.add(fieldName);
			}
		}
		fieldValidation("columns", present, notpresent);
		present.clear();
		notpresent.clear();

		softAssert.assertAll();

	}

	
	
// Created By - Niranjan H on 18-04-2024
// Description :- This test is to verify that when trying to  manually create or edit any of the following records on the Account belonging to the other Bottler a validation should be displayed to restrict the user from making any changes on account.
public void OtherBottlersAccount(HashMap<String, String> Account) throws Throwable {softAssert = new SoftAssert();
String ListName = "Other Bottler Account";
String ValidationMessage= "This Account is outside of your Bottler’s Territory. Updates of any kind are not permitted";
String AccountError= "You cannot create or update an Activity related to another Bottler. Please check the Account you are creating this data on.";
String CaseError= "You cannot create or update a Case related to another Bottler. Please check the Account you are creating this data on";
String ContactError="You cannot create or update a Contact related to another Bottler. Please check the Account you are creating this data on.";


String AccountName ="";
List<String> values = new ArrayList<String>();
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
String callType =values.get(0); 
String CSTProblemReported=values.get(3); 
String detailDescription=values.get(2); 

selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
selectAppOrObjFromAppLauncher("object", Constants.accountfromlauncher);
closeMultipleTabs();

	javaClickByLocator(By.xpath("//button[contains(@title,'Select a List View:')]"));
		fillField("//input[@placeholder='Search lists...']", ListName);
	Thread.sleep(3000);
	if (driver.findElements(By.xpath("(//mark[text()='" + ListName + "']/ancestor::a)[1]")).size() > 0)
	{
		javaClickByLocator(By.xpath("(//mark[text()='" + ListName + "']/ancestor::a)[1]"));
	}else if (driver.findElements(By.xpath("(//span[text()='" + ListName + "']/ancestor::a)[1]")).size() > 0) {
			javaClickByLocator(By.xpath("(//span[text()='" + ListName + "']/ancestor::a)[1]"));
		} 
	else
	{
		javaClickByLocator(CST_Routing.ListViewSettingBtn);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Cases.NewListView));
		javaClickByLocator(Cases.NewListView);
		fillFieldByLocator(Cases.ListviewName, ListName);
		javaClickByLocator(Cases.NewListView_save);
		Thread.sleep(5000);
		javaClickByLocator(CST_Routing.filterbyowner);
		javaClickByLocator(Accounts.allaccounts);
		javaClickByLocator(CST_Routing.done);
		AddFilter("Created Date", "greater than", "06/01/2022");
		AddFilter("Sales Organization", "not equal to","");
		AddFilter("Sales Organization", "not equal to",Constants.bottler);
		AddFilter("Account Record Type", "equals", "Customer");
		javaClickByLocator(CST_Routing.saveFilterBtn);
		tableLoadingWait();
		Thread.sleep(10000);
		javaClickByLocator(CST_Routing.CloseFilter);	
	}
		tableLoadingWait();
		try {
		List<WebElement> AccountsList = driver.findElements(Accounts.ListOfAccounts);
		if (AccountsList.size()>0) {
			loggerPass("List of Accounts Should be displayed", "List of Accounts are displayed", "yes");
		}else {
			loggerFail("List of Accounts Should be displayed", "List of Accounts not displayed", true);
		}
		WebElement FirstRow = AccountsList.get(0);
		AccountName =FirstRow.getText();
		javaClickByWebElement(FirstRow);
		loggerInfo("Account name is : <b>"+AccountName+"</b>", "No");
		} catch (Exception e) {
			loggerFail("List Of Accounts Should be displayed", "List Of Accounts not displayed", true);
		}
		Thread.sleep(5000);
		
		try {
		List<WebElement> text = driver.findElements(By.xpath("//flexipage-component2[@data-component-id='flexipage_richText']//p/span"));
		String BannerText="";
		for(int i=0;i<text.size();i++){BannerText=BannerText+text.get(i).getText();}
		if (BannerText.contains(ValidationMessage)) {
			loggerPass("Validation Message <b>"+ValidationMessage+"</b> Should Be displayed", "Validation Message <b>"+BannerText+"</b> is displayed", "yes");
		}else {loggerFail("Validation Message <b>"+ValidationMessage+"</b> Should Be displayed","Validation Message <b>"+ValidationMessage+"</b> Not displayed", true);}
		}catch (Exception e) {
			loggerFail("Banner should be displayed", "Banner not displayed", true);
		}

		loggerInfo("Create Task for other bottlers account", "No");
		closeMultipleTabs();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(HomePage.GlobalActions)).click();
		javaClickByLocator(HomePage.NewTask);
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(HomePage.PhoneCall));
		isDisplayed(HomePage.PhoneCall, "New Task Pop-up");
		fillFieldByLocator(HomePage.Subject_Tasks, "Phone call_"+generateRandomInteger());
		Thread.sleep(1000);
		fillFieldByLocator(Accounts.AccountNameField, AccountName+ Keys.ENTER);
		Thread.sleep(1000);
		javaClickByLocator(By.xpath("//div[@role='listbox']//div[@title='"+AccountName+"']/ancestor::a"));
		Thread.sleep(1000);
		fillFieldByLocator(HomePage.comments_Tasks, "Test");
		javaClickByLocator(HomePage.Save_Tasks);
		if (driver.findElement(HomePage.Error).isDisplayed()) {
			if (GetText(HomePage.Error).contains(AccountError)) {
				loggerPass("Error Message <b>"+AccountError+"</b> Should Be displayed", "Error Message <b>"+GetText(HomePage.Error)+"</b> is displayed", "yes");
			}else {
				loggerFail("Error Message <b>"+AccountError+"</b> Should Be displayed", "Error Message <b>"+AccountError+"</b> Not displayed", true);
			}	
		}else {
			loggerFail("Page Error should be displayed", "Page Error is not displayed", true);
		}
		javaClickByLocator(HomePage.CloseTask);

		
		
		
		
		
		loggerInfo("Case for other bottlers account", "No");
		selectAppOrObjFromAppLauncher("object", Constants.caseobjectfromlauncher);
		closeMultipleTabs();
		javaClickByLocator(Cases.NewCase);
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(Cases.NewCasepopup));
		if (driver.findElements(Cases.CaseRecordTypes).size()>0) {
			loggerPass("Diffrent case record types should be displayed", "Diffrent case record type are displayed", "Yes");
		}else {
			loggerFail("Diffrent case record types should be displayed", "Diffrent case record types are not displayed", true);
		}
		
		javaClickByLocator(Cases.CST_Case);
		Thread.sleep(3000);

		
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(Accounts.alertnext));
		javaClickByLocator(Accounts.alertnext);
		Thread.sleep(3000);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Cases.CSTPopup));
		Thread.sleep(3000);
		if (driver.findElements(Cases.CSTPopup).size() > 0) {
			loggerPass("New Case: Customer Service Ticket page layout should be display",
					"New Case: Customer Service Ticket page layout displayed", "No");
			
			fillFieldByLocator(Accounts.AccountNameField,AccountName);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//lightning-base-combobox-formatted-text[@title='"+AccountName+"']")).click();
			Thread.sleep(1000);
			javaClickByLocator(Cases.callType);

			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));

			driver.findElement(Accounts.detailedDescriptionTextBox).click();
			driver.findElement(Accounts.detailedDescriptionTextBox).sendKeys(detailDescription);

			javaClickByLocator(Cases.CSTProblemReported);

			javaClickByLocator(
					By.xpath("//lightning-base-combobox-item[@data-value='" + CSTProblemReported + "']"));

			javaClickByLocator(Accounts.saveBtn);
		
		Thread.sleep(3000);
		if (driver.findElement(Cases.PageError).isDisplayed()) {
			if (GetText(Cases.PageError).contains(CaseError)) {
				loggerPass("Error Message <b>"+CaseError+"</b> Should Be displayed while creating a case", "Error Message <b>"+GetText(Cases.PageError)+"</b> is displayed while creating a case", "yes");
			}else {
				loggerFail("Error Message <b>"+CaseError+"</b> Should Be displayed while creating a case", "Error Message <b>"+CaseError+"</b> Not displayed while creating a case", true);
			}	
		}else {loggerFail("Page Error should be displayed", "Page Error is not displayed", true);}
		
		}else {
			loggerFail("New Case: Customer Service Ticket page layout should be display",
					"New Case: Customer Service Ticket page layout not displayed", true);
		}
		
		javaClickByLocator(Cases.CloseCaseCreation);
		Thread.sleep(2000);
		javaClickByLocator(CST_Routing.discardbtn);
		
		
		loggerInfo("Contacts for other bottlers account", "No");
		selectAppOrObjFromAppLauncher("object", Constants.Contactsobjectfromlauncher);
		closeMultipleTabs();
		javaClickByLocator(Contacts.NewContact);
		new WebDriverWait(driver, explicitWaitTimeOut)
		.until(ExpectedConditions.visibilityOfElementLocated(Contacts.BusinessContact));			
		if (driver.findElement(Contacts.BusinessContact).isDisplayed()) {
			loggerPass("New Contact: Business page should be display",
					"New Contact: Business page displayed", "No");
		}else {
			loggerFail("New Contact: Business page should be display", "New Contact: Business page is not displayed", true);
		}
		select_dropdownValue("Mr.", Contacts.salutation);
		fillFieldByLocator(Contacts.FirstName, "Test"+Keys.TAB);
		Thread.sleep(1000);
		scrollToElement(Contacts.LastName);
		fillFieldByLocator(Contacts.LastName, "ABC");
		javaClickByLocator(Accounts.AccountNameField);
		Thread.sleep(1000);
		driver.findElement(Accounts.AccountNameField).sendKeys(AccountName);
		Thread.sleep(1000);
		javaClickByLocator(Cases.showAllResults);
		Thread.sleep(1000);
		javaClickByLocator(Cases.selectFirstAccount);			
		
		new WebDriverWait(driver, explicitWaitTimeOut)
		.until(ExpectedConditions.visibilityOfElementLocated(Contacts.ContactPriorityPickllist));						
		scrollToElement(Contacts.ContactPriorityPickllist);
		javaClickByLocator(Contacts.ContactPriorityPickllist);
		Thread.sleep(1000);
		select_dropdownValue("Correspondence- Customer", Contacts.Function);
		scrollToElement(Accounts.PrimaryPhoneTextbox);
		Thread.sleep(2000);
		select_dropdownValue("Mobile", Contacts.PrimaryPhoneType);
		fillFieldByLocator(Accounts.PrimaryPhoneTextbox, "0123456789" + Keys.TAB);
		javaClickByLocator(Reports.save);
		Thread.sleep(3000);
		
		if (driver.findElement(Cases.PageError).isDisplayed()) {
			if (GetText(Cases.PageError).contains(ContactError)) {
				loggerPass("Error Message <b>"+ContactError+"</b> Should Be displayed while creating a case", "Error Message <b>"+GetText(Cases.PageError)+"</b> is displayed while creating a case", "yes");
			}else {
				loggerFail("Error Message <b>"+ContactError+"</b> Should Be displayed while creating a case", "Error Message <b>"+ContactError+"</b> Not displayed while creating a case", true);
			}	
		}else {loggerFail("Page Error should be displayed", "Page Error is not displayed", true);}
		
		
		javaClickByLocator(Cases.CloseContactCreation);
		Thread.sleep(2000);
		javaClickByLocator(CST_Routing.discardbtn);}



// Created By - Niranjan H on 08-07-24
// Description :- New Business Contact button on account details page
	public void VerifyNewBusinessContact(HashMap<String, String> CreateContsct) throws Throwable {
		softAssert = new SoftAssert();
		String accountName = "";
		String accountNo = "";
		String BusnessContactNumber = "";
		List<String> present = new ArrayList<String>();
		List<String> notpresent = new ArrayList<String>();
		String FirstName = CreateContsct.get("FirstName");
		String LastName = CreateContsct.get("LastName");
		String LastName2 = CreateContsct.get("LastName2");
		String Function = CreateContsct.get("Function");
		String PhoneType = CreateContsct.get("PhoneType");
		String PrimeryPhone = CreateContsct.get("PrimeryPhone");

		switch (Constants.bottler) {
		case "4100":
			accountName = "AMERICAN LEGION POST #95";
			accountNo = "0600131790";
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
			accountName = "DOMINOS #8585";
			accountNo = "0601609054";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #6302";
			accountNo = "0501354348";
			break;
		case "4800":
			accountName = "DOMINOS #6504";
			accountNo = "0501099714";
			break;
		case "4900":
			accountName = "CUPKAS #2";
			accountNo = "0601206132";
			break;
		case "5000":
			accountName = "1 FOOD MART";
			accountNo = "0601737203";
			break;
		case "5200":
			accountName = "BOSTON MARKET #1728";
			accountNo = "0601471691";
			break;
		case "5300":
			accountName = "DOMINOS PIZZA #10273";
			accountNo = "0602129363";
			break;
		}

		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		selectObjectFromDropdown(Constants.Contactsobjectfromlauncher);
		closeMultipleTabs();
		fillFieldByLocator(Accounts.searchcontact, FirstName + " " + LastName + Keys.TAB);
		Thread.sleep(5000);
		if (driver.findElements(By.xpath("//a[text()='" + FirstName + " " + LastName
				+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).size() > 0) {
			driver.findElement(By.xpath("//a[text()='" + FirstName + " " + LastName
					+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).click();
			javaClickByLocator(Contacts.Delete);
			javaClickByLocator(CST_Routing.deleteBtn);
			tableLoadingWait();
		}

		fillFieldByLocator(Accounts.searchcontact, FirstName + " " + LastName2 + Keys.TAB);
		Thread.sleep(5000);
		if (driver.findElements(By.xpath("//a[text()='" + FirstName + " " + LastName2
				+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).size() > 0) {
			driver.findElement(By.xpath("//a[text()='" + FirstName + " " + LastName2
					+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).click();
			javaClickByLocator(Contacts.Delete);
			javaClickByLocator(CST_Routing.deleteBtn);
		}

		
		
	// Creating new contact from contacts object
		Thread.sleep(5000);
		javaClickByLocator(Accounts.newcontact);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Contacts.BusinessContact));
		fillFieldByLocator(Contacts.FirstName, FirstName);
		Thread.sleep(1000);
		scrollToElement(Contacts.LastName);
		fillFieldByLocator(Contacts.LastName, LastName);
		javaClickByLocator(Accounts.AccountNameField);
		Thread.sleep(1000);
		driver.findElement(Accounts.AccountNameField).sendKeys(accountName);
		Thread.sleep(1000);
		javaClickByLocator(Cases.showAllResults);
		Thread.sleep(1000);
		javaClickByLocator(Cases.selectFirstAccount);
		Thread.sleep(1000);
		select_dropdownValue(Function, Contacts.Function);
		scrollToElement(Accounts.PrimaryPhoneTextbox);
		Thread.sleep(2000);
		select_dropdownValue(PhoneType, Contacts.PrimaryPhoneType);
		fillFieldByLocator(Accounts.PrimaryPhoneTextbox, PrimeryPhone + Keys.TAB);
		javaClickByLocator(Reports.save);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Accounts.ContactDetailsPage));

		List<WebElement> FieldElements = driver.findElements(Accounts.FieldsOnContactsDetailsPage);
		List<String> FieldNames = new ArrayList<String>();
		for (WebElement feild : FieldElements) {
			FieldNames.add(feild.getText());
		}

		System.out.println(FieldNames);
		closeMultipleTabs();

		SelectAccountFromGlobalSearch(accountNo, accountName);
		try {
			
			new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Newbusisnesscontact));
			isDisplayed(Accounts.Newbusisnesscontact, "New Business Contact");
		} catch (Exception e) {
			loggerFail("New Business Contact button should be displyed", "New Business Contact button is not displyed",
					true);
		}
		Thread.sleep(2000);
		BusnessContactNumber = GetText(Accounts.BusnessContact);

		
		
		
	// creating contact from New business contact button
		javaClickByLocator(Accounts.Newbusisnesscontact);
		isDisplayed(Accounts.NewbusisnesscontactPage, "New Business Contact Creation Page");
		fieldValidation("New Business Contact Fields validation", NewbusinesscontactCrteation);
		for (String fieldName : PhoneSectionfeilds) {
			if (driver.findElements(By.xpath("//*[text()='" + fieldName + "']")).size() != 0) {
				present.add(fieldName);
			} else {
				notpresent.add(fieldName);
			}
		}
		if (driver.findElements(Accounts.PrimaryPhoneField).size() > 0) {
			present.add("Primary Phone Type");
		} else {
			notpresent.add("Primary Phone Type");
		}
		if (driver.findElements(Accounts.SecondaryPhoneField).size() > 0) {
			present.add("Secondary Phone Type");
		} else {
			notpresent.add("Secondary Phone Type");
		}
		fieldValidation("Phone Section All Fields", present, notpresent);
		System.out.println("Present: " + present);
		System.out.println("Not present: " + notpresent);
		present.clear();
		notpresent.clear();
		fieldValidation("Email Section All Fields", EmailSectionfeilds);

	// Validate values before contact creation
		List<String> NewBusnessContactFeilds = Collections.unmodifiableList(Arrays.asList("Account Name", "Function",
				"Language", "Department", "Primary Phone", "Marketing Opt In/Out", "Marketing Opt-In/Out (Other)",
				"Operational Updates Opt In/Out", "Operational Updates Opt-In/Out (Other)",
				"Order Reminders Opt In/Out", "Order Reminders Opt-In/Out (Other)", "Order Confirmation Opt In/Out",
				"Order Confirmation Opt-In/Out (Other)", "Mobile Invoice Opt In/Out",
				"Mobile Invoice Opt-In/Out (Other)"));
		List<String> NewBusnessContactvalues = Collections
				.unmodifiableList(Arrays.asList(accountName, "Correspondence- Customer", "English", "Sales",
						BusnessContactNumber, "Not Offered", "Not Offered", "Not Offered", "Not Offered", "Not Offered",
						"Not Offered", "Not Offered", "Not Offered", "Not Offered", "Not Offered"));
		for (int i = 0; i < NewBusnessContactFeilds.size(); i++) {
		
			if (driver.findElements(By.xpath("//span[text()='" + NewBusnessContactFeilds.get(i)
					+ "']/ancestor::div[@data-target-selection-name]//a[text()=\"" + NewBusnessContactvalues.get(i)
					+ "\"]")).size() > 0) {
				present.add(NewBusnessContactFeilds.get(i) + " = <b>" + NewBusnessContactvalues.get(i) + "</b>");
			} else if (driver.findElements(By.xpath("//span[text()='" + NewBusnessContactFeilds.get(i)
					+ "']/ancestor::div[@data-target-selection-name]//span[contains(text(),\""
					+ NewBusnessContactvalues.get(i) + "\")]")).size() > 0) {
				present.add(NewBusnessContactFeilds.get(i) + " = <b>" + NewBusnessContactvalues.get(i) + "</b>");
			}  else if (driver
					.findElement(By.xpath("//span[text()='" + NewBusnessContactFeilds.get(i)
							+ "']/ancestor::div[@data-target-selection-name]//input"))
					.getAttribute("value").equals(NewBusnessContactvalues.get(i))) {
				present.add(NewBusnessContactFeilds.get(i) + " = <b>" + NewBusnessContactvalues.get(i) + "</b>");
			} else {
				notpresent.add(NewBusnessContactFeilds.get(i) + "= <b>" + NewBusnessContactvalues.get(i) + "</b>");
			}
		}
		fieldValidation("Default values for fields", present, notpresent);
		System.out.println("Present: " + present);
		System.out.println("Not present: " + notpresent);
		present.clear();
		notpresent.clear();
		fillFieldByLocator(Contacts.First_Name, FirstName + Keys.TAB);
		fillFieldByLocator(Contacts.Last_Name, LastName2 + Keys.TAB);
		javaClickByLocator(Contacts.Save_contact);
		Thread.sleep(1000);
		
		
		try {
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.ActiveRelatedContactsWidget));
			javaClickByLocator(Accounts.ViewAllActiveRelatedContacts);
		WebElement table = driver.findElement(By.xpath("//table[@aria-label='Active Related Contacts']/ancestor::div[@class='slds-scrollable_y']"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", table);
		
		
		if (driver.findElements(
					By.xpath("//th[@data-label='Business Contact']//a//span[@class='slds-truncate']/slot[text()='"
							+ FirstName + " " + LastName2 + "']"))
					.size() > 0) {
				loggerPass("Newly created contact should be displayed in Active Related Contact list",
						"Newly created contact displayed in Active Related Contact list", "yes");
				javaClickByLocator(
						By.xpath("//th[@data-label='Business Contact']//a//span[@class='slds-truncate']/slot[text()='"
								+ FirstName + " " + LastName2 + "']"));
			} else {
				loggerFail("Newly created contact should be displayed in Active Related Contact list",
						"Newly created contact not displayed in Active Related Contact list", true);
			}
			isDisplayedWithScreenshot(Accounts.ContactDetailsPage, "Contact Detailes page", "yes");

			
			
		// validate values after contact creation
			List<String> FeildsAfterCreation = Collections.unmodifiableList(
					Arrays.asList("Name", "Contact Record Type", "Account Name", "Function", "Language", "Department",
							"Bottler Ids", "Primary Phone", "Marketing Opt In/Out", "Marketing Opt-In/Out (Other)",
							"Operational Updates Opt In/Out", "Operational Updates Opt-In/Out (Other)",
							"Order Reminders Opt In/Out", "Order Reminders Opt-In/Out (Other)",
							"Order Confirmation Opt In/Out", "Order Confirmation Opt-In/Out (Other)",
							"Mobile Invoice Opt In/Out", "Mobile Invoice Opt-In/Out (Other)"));
			List<String> ValuesAfterCreation = Collections.unmodifiableList(Arrays.asList(FirstName + " " + LastName2,
					"Business", accountName, "Correspondence- Customer", "English", "Sales", Constants.bottler,
					BusnessContactNumber, "Not Offered", "Not Offered", "Not Offered", "Not Offered", "Not Offered",
					"Not Offered", "Not Offered", "Not Offered", "Not Offered", "Not Offered"));

			for (int i = 0; i < FeildsAfterCreation.size(); i++) {
				if (driver.findElements(By.xpath("//span[text()='" + FeildsAfterCreation.get(i)
						+ "']//ancestor::div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dd//slot[@name='outputField']//lightning-formatted-text[text()=\""
						+ ValuesAfterCreation.get(i) + "\"]")).size() > 0) {
					present.add(FeildsAfterCreation.get(i) + " = <b>" + ValuesAfterCreation.get(i) + "</b>");

				} else if (driver.findElements(By.xpath("//span[text()='" + FeildsAfterCreation.get(i)
						+ "']//ancestor::div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dd//slot[@name='outputField']//lightning-formatted-name[text()=\""
						+ ValuesAfterCreation.get(i) + "\"]")).size() > 0) {
					present.add(FeildsAfterCreation.get(i) + " = <b>" + ValuesAfterCreation.get(i) + "</b>");
				} else if (driver.findElements(By.xpath("//span[text()='" + FeildsAfterCreation.get(i)
						+ "']//ancestor::div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dd//slot[@name='outputField']//span[text()=\""
						+ ValuesAfterCreation.get(i) + "\"]")).size() > 0) {
					present.add(FeildsAfterCreation.get(i) + " = <b>" + ValuesAfterCreation.get(i) + "</b>");
				} else if (driver.findElements(By.xpath("//span[text()='" + FeildsAfterCreation.get(i)
						+ "']//ancestor::div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dd//slot[@name='outputField']//a[text()=\""
						+ ValuesAfterCreation.get(i) + "\"]")).size() > 0) {
					present.add(FeildsAfterCreation.get(i) + " = <b>" + ValuesAfterCreation.get(i) + "</b>");

				} else if (driver.findElements(By.xpath("//span[text()='" + FeildsAfterCreation.get(i)
						+ "']//ancestor::div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dd//slot[@name='outputField']//slot[text()=\""
						+ ValuesAfterCreation.get(i) + "\"]")).size() > 0) {
					present.add(FeildsAfterCreation.get(i) + " = <b>" + ValuesAfterCreation.get(i) + "</b>");

				} else {
					notpresent.add(FeildsAfterCreation.get(i) + "= <b>" + ValuesAfterCreation.get(i) + "</b>");
				}
			}
			fieldValidation("Fields and values after contact creation", present, notpresent);
			System.out.println("Present: " + present);
			System.out.println("Not present: " + notpresent);
			present.clear();
			notpresent.clear();
			List<String> Newbusinesscontactfields = new ArrayList<String>();
			for (WebElement Elements : driver.findElements(
					By.xpath("//div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dt"))) {
				Newbusinesscontactfields.add(Elements.getText());
			}
			System.out.println(Newbusinesscontactfields);
			if (Newbusinesscontactfields.containsAll(FieldNames)) {
				loggerPass(
						"Contact created using <b>New busness contact button on accounts details page</b> and <b>contact created using contacts object</b> should be same",
						"Contact created using New busness contact button on accounts details page and Contact created using contacts object Are same",
						"No");
			} else {
				ArrayList<String> differences = new ArrayList<>();
				for (String item : FieldNames) {
					if (!(Newbusinesscontactfields.contains(item))) {
						differences.add(item);
					}
					loggerFail(
							"Contact created using New busness contact button on accounts details page and contact created using contacts object should be same",
							"Contacts created using the New Business Contact button do not include the following fields: "
									+ differences,
							true);
				}
			}
		} finally {
			closeMultipleTabs();
			int attempts = 0;
			while (attempts < 10) {
				try {
					new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//span[text()='" + Constants.Contactsobjectfromlauncher + "']")));
					if (driver.findElement(By.xpath("//span[text()='" + Constants.Contactsobjectfromlauncher + "']"))
							.isDisplayed()) {
						break;
					}
				} catch (Exception e) {
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(HomePage.NavigationBtn));
					driver.findElement(HomePage.NavigationBtn).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//section[@aria-label='Navigation Menu']//span[text()='"
							+ Constants.Contactsobjectfromlauncher + "']")).click();
				}
			}
			attempts++;
			fillFieldByLocator(Accounts.searchcontact, FirstName + " " + LastName + Keys.TAB);
			Thread.sleep(5000);
			if (driver.findElements(By.xpath("//a[text()='" + FirstName + " " + LastName
					+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).size() > 0) {
				driver.findElement(By.xpath("//a[text()='" + FirstName + " " + LastName
						+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).click();
				javaClickByLocator(Contacts.Delete);
				javaClickByLocator(CST_Routing.deleteBtn);
				tableLoadingWait();
			}

			
			fillFieldByLocator(Accounts.searchcontact, FirstName + " " + LastName2 + Keys.TAB);
			Thread.sleep(5000);
			if (driver.findElements(By.xpath("//a[text()='" + FirstName + " " + LastName2
					+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).size() > 0) {
				driver.findElement(By.xpath("//a[text()='" + FirstName + " " + LastName2
						+ "']/ancestor::tr//span[text()='Show Actions']/ancestor::a")).click();
				javaClickByLocator(Contacts.Delete);
				javaClickByLocator(CST_Routing.deleteBtn);
			}

		}
	}

}

