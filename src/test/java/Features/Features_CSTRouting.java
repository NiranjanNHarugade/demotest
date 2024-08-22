package Features;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.MediaEntityBuilder;
import Pages.Accounts;
import Pages.CST_Routing;
import Pages.Cases;
import Utilities.CommonFunctions;
import Utilities.Constants;

public class Features_CSTRouting extends CommonFunctions {

	// Created by - Abhishek Palankar on 31-May-2023
	// This test is to verify that if there is a matching Spring related Account
	// Partner, it will assign the case to the one that matches
	public static void cstRoutingForSpring(HashMap<String, String> MapValue) throws Exception {
		softAssert = new SoftAssert();
		String spring = MapValue.get("Spring");
		String CSTRouting = "";
		String partnerRole = "";
		String name = "";
		String managerName = "";
		String email = "";
		int i;

		boolean value = false;
		System.out.println("CST routing for spring");
		String accountName = "";
		String accountNo = "";
		switch (Constants.bottler) {
		case "4100":
			accountName = "DOLLAR GENERAL #13545";
			accountNo = "0600378877";	
			CSTRouting = "CSTR-1387759";
			break;
		case "4200":
			accountName = "DOLLAR GENERAL #02523";
			accountNo = "0500008854";
			CSTRouting = "CSTR-2114999";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			CSTRouting = "CSTR-1387773";
			break;
		case "4500":
			accountName = "AVON PARK HIGH SCHOOL";
			accountNo = "0500121742";
			CSTRouting = "CSTR-1387775";
			break;
		case "4600":
			accountName = "DOMINOS #8585";
			accountNo = "0601609054";
			CSTRouting = "CSTR-1387777";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			CSTRouting = "CSTR-1387779";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			CSTRouting = "CSTR-1387781";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			CSTRouting = "CSTR-1387788";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			CSTRouting = "CSTR-1387784";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			CSTRouting = "CSTR-1387786";
			break;
		}
		
			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			selectAppOrObjFromAppLauncher("object", Constants.CSTRoutingobjectfromlauncher);
			javaClickByLocator(CST_Routing.listViewBtn);
			javaClickByLocator(CST_Routing.allActive);
			Thread.sleep(3000);
			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.clone));
			javaClickByLocator(CST_Routing.clone);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.saveBtn));
			javaClickByLocator(CST_Routing.saveBtn);
			Thread.sleep(5000);
			javaClickByLocator(CST_Routing.addFilter);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
			javaClickByLocator(CST_Routing.field);
			Thread.sleep(1000);
			scrollToElement(CST_Routing.CSTRoutingNumberOption);
			javaClickByLocator(CST_Routing.CSTRoutingNumberOption);
			driver.findElement(CST_Routing.valueField).click();
			driver.findElement(CST_Routing.valueField).sendKeys(CSTRouting);
			driver.findElement(CST_Routing.valueField).sendKeys(Keys.TAB);
			
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.doneBtn);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.saveFilterBtn);
			
			Thread.sleep(2000);
			try {
				if (driver.findElements(By.xpath("//a[@title='" + CSTRouting + "']")).size() > 0) {
					loggerPass("CST Routing should be display <b>" + CSTRouting,
							"CST Routing is displayed <b>" + CSTRouting, "Yes");
				} else {
					loggerFail("CST Routing should be display <b>" + CSTRouting,
							"CST Routing is not displayed <b>" + CSTRouting, true);
				}
				Thread.sleep(1000);
			}finally {
				javaClickByLocator(CST_Routing.ListViewSettingBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteListView));
				javaClickByLocator(CST_Routing.deleteListView);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteBtn));
				javaClickByLocator(CST_Routing.deleteBtn);
				Thread.sleep(2000);
			}
			

			CommonFunctions.closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			Thread.sleep(2000);
			scrollToElement(CST_Routing.relatedListQuickLinks);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.showAllLinkText));

			Thread.sleep(2000);
			driver.findElement(Accounts.showAllLinkText).click();
			javaClickByLocator(Accounts.acountPartner);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.acountPartnerPage));
			int size = driver.findElements(Accounts.size).size();
			// Below loop is used to check Spring partner role
			for (i = 1; i <= size; i++) {
				partnerRole = driver.findElement(By.xpath(
						"//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i + "]//th//lst-formatted-text"))
						.getText();

				if (partnerRole.equalsIgnoreCase(spring)) {
					value = true;
					break;
				}

			}
			if (value == true) {
				loggerPass(spring + " Partner Role should be display", partnerRole + " Partner Role is displayed",
						"Yes");
				name = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i
						+ "]/td[4]//lightning-formatted-rich-text//span")).getText();
				managerName = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr["
						+ i + "]/td[5]//lightning-formatted-rich-text//span")).getText();
				email = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i
						+ "]/td[6]//lightning-formatted-rich-text//span//a")).getText();
				System.out.println("Name " + name);
				System.out.println("Manager Name " + managerName);
				System.out.println("Email " + email);

			} else {
				loggerFail(spring + " Partner Role should be display",
						spring + " Partner Role is displayed in Account Partners page", true);
			}

			javaClickByLocator(Accounts.closeAccountPartners);
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

			}
			String callType = values.get(0);
			String CSTProblemReported = values.get(1);
			String detailDescription = values.get(2);
			CSTCaseCreate(callType, CSTProblemReported, detailDescription, name, email);

		
		softAssert.assertAll();
		

	}

	// Created by - Abhishek Palankar on 31-May-2023
	// This method is used to create CST case and validate assigned to and assigned
	// to email
	public static void CSTCaseCreate(String callType, String CSTProblemReported, String detailDescription, String name,
			String email) throws InterruptedException, IOException {
		String caseNumber = "";
		String GetAssignedTo = "";
		String GetAssignedToEmail = "";
		String GetCSTProblemReported = "";

		javaClickByLocator(Accounts.casesWidget);
		Thread.sleep(2000);
		javaClickByLocator(Accounts.newcase);
		javaClickByLocator(Cases.CSTbtn);

		javaClickByLocator(Accounts.next);
		Thread.sleep(4000);

		if (driver.findElements(Cases.CSTPopup).size() > 0) {
			loggerPass("New Case: Customer Service Ticket page layout should be dispaly",
					"New Case: Customer Service Ticket page layout displayed", "No");

			javaClickByLocator(Cases.callType);

			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));

			driver.findElement(Accounts.detailedDescriptionTextBox).click();
			driver.findElement(Accounts.detailedDescriptionTextBox).sendKeys(detailDescription);

			javaClickByLocator(Cases.CSTProblemReported);

			javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + CSTProblemReported + "']"));

			javaClickByLocator(Accounts.saveBtn);
			Thread.sleep(3000);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Cases.assignedToField));
			caseNumber = GetText(Cases.caseNo);

			if (driver.findElements(Cases.assignedToField).size() > 0) {
				loggerPass("The Newly created Case <b>" + caseNumber + "</b> should be displayed.",
						"The Newly created Case <b>" + caseNumber + "</b> is displayed.", "No");
				// TC - 242008
				GetAssignedTo = GetText(Cases.assignedToFieldValue);
				GetAssignedToEmail = GetText(Cases.assignedToEmailFieldValue);

				if (GetAssignedTo.equalsIgnoreCase(name)) {
					loggerPass(
							"Assigned To value should be auto populated with value that matched the account partner record <b>"
									+ name,
							"Assigned To value is auto populated with value that matched the account partner record <b>"
									+ GetAssignedTo,
							"Yes");
				} else {
					loggerFail(
							"Assigned To value should be auto populated with value that matched the account partner record <b>"
									+ name,
							"Assigned To value is not auto populated with value that matched the account partner record <b>"
									+ GetAssignedTo,
							true);
				}
				if (GetAssignedToEmail.equalsIgnoreCase(email)) {
					loggerPass(
							"Assigned To Email value should be auto populated with value that matched the account partner record <b>"
									+ email,
							"Assigned To Email value is auto populated with value that matched the account partner record <b>"
									+ GetAssignedToEmail,
							"No");
				} else {
					loggerFail(
							"Assigned To Email value should be auto populated with value that matched the account partner record <b>"
									+ email,
							"Assigned To Email value is not auto populated with value that matched the account partner record <b>"
									+ GetAssignedToEmail,
							true);
				}
				// TC - 243562
				scrollToElement(Cases.ContactInformationSection);
				Thread.sleep(1000);
				GetCSTProblemReported = GetText(Cases.CSTProblemReportedFieldValue);
				if (GetCSTProblemReported.equalsIgnoreCase(CSTProblemReported)) {
					loggerPass("CST Problem Reported Field should be display as <b>" + CSTProblemReported,
							"CST Problem Reported Field is displayed as <b>" + GetCSTProblemReported, "Yes");
				} else {
					loggerFail("CST Problem Reported Field should be display as <b>" + CSTProblemReported,
							"CST Problem Reported Field is displayed as <b>" + GetCSTProblemReported, true);
				}

				// TC - 242009
				loggerInfo("<b>Validate CST Routing type with ECC team and Salesforce inspector</b>", "No");
			} else {
				loggerFail("The Newly created Case <b>" + caseNumber + "</b> should be displayed.",
						"The Newly created Case <b>" + caseNumber + "</b> is not displayed.", true);
			}

		} else {
			loggerFail("New Case: Customer Service Ticket page layout should be dispaly",
					"New Case: Customer Service Ticket page layout not displayed", true);
		}

	}

//	Created By - Niranjan H on 30-05-2023
//	Description Verify that If there is a matching PACE related Account Partner assign the case that one matches
	public void PaceRouting_validation(HashMap<String, String> CST) throws Throwable {
		System.out.println("PACE related Account Partner validation");
		softAssert = new SoftAssert();
		String CST_Pace = "";
		String Pace_Responsible_Role = "";
		String accountName = "";
		String accountNo = "";
		String ListName =CST.get("List Name");
		
		switch (Constants.bottler) {
		case "4100":
			accountName = "DOLLAR GENERAL #14315";
			accountNo = "500019285";
			CST_Pace = "CSTR-1387761";
			break;
		case "4200":
			accountName = "DOLLAR GENERAL #02523";
			accountNo = "0500008854";
			CST_Pace = "CSTR-2115006";
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			CST_Pace = "CSTR-1387762";
			break;
		case "4500":
			accountName = "AVON PARK HIGH SCHOOL";
			accountNo = "0500121742";
			CST_Pace = "CSTR-1387763";
			break;
		case "4600":
			accountName = "DOMINOS #8585";
			accountNo = "0601609054";
			CST_Pace = "CSTR-1387764";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			CST_Pace = "CSTR-2115008";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			CST_Pace = "CSTR-1387766";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			CST_Pace = "CSTR-1387767";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			CST_Pace = "CSTR-1387768";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			CST_Pace = "CSTR-1387769";
			break;
		case "5300":
			accountName = "ABBY CONVENIENCE STORE";
			accountNo = "602184829";
			CST_Pace = "CSTR-1387770";
			break;

		}
		
			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			selectAppOrObjFromAppLauncher("object", Constants.CSTRoutingobjectfromlauncher);

			driver.navigate().refresh();
			Thread.sleep(2000);
			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.newlistview));
			javaClickByLocator(CST_Routing.newlistview);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListName));
			fillFieldByLocator(CST_Routing.ListName,ListName);
			Thread.sleep(5000);
			javaClickByLocator(CST_Routing.SaveListView);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.filterbyowner));
			javaClickByLocator(CST_Routing.filterbyowner);
			Thread.sleep(2000);
			javaClickByLocator(CST_Routing.allcst);
			javaClickByLocator(CST_Routing.done);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.addFilter);
			Thread.sleep(1000);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
			javaClickByLocator(CST_Routing.field);
			Thread.sleep(2000);
			scrollToElement(CST_Routing.recordtype);
			javaClickByLocator(CST_Routing.recordtype);
			
			driver.findElement(CST_Routing.valuebox).click();
		
			scrollToElement(CST_Routing.spring_pace);
			driver.findElement(CST_Routing.spring_pace).click();
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.doneBtn);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.saveFilterBtn);
			Thread.sleep(2000);
			javaClickByLocator(CST_Routing.CloseFilter);
			fillFieldByLocator(CST_Routing.searchforlist, CST_Pace);
			driver.findElement(CST_Routing.searchforlist).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			
			try {
				if (driver.findElements(By.xpath("//a[@title='" + CST_Pace + "']")).size() > 0) {
					loggerPass("CST Routing should be display <b>" + CST_Pace + "</b>",
							"CST Routing is displayed <b>" + CST_Pace, "Yes");
				} else {
					loggerFail("CST Routing should be display <b>" + CST_Pace + "</b>",
							"CST Routing is not displayed <b>" + CST_Pace, true);
				}
				
				
				driver.findElement(By.xpath("//a[@title='" + CST_Pace + "']")).click();
				Thread.sleep(1000);
				Pace_Responsible_Role = GetText(CST_Routing.Pace_Responsible);
				Thread.sleep(2000);
			
				try {
					if (driver.findElements(CST_Routing.ActiveCheckbox).size() > 0) {
						loggerPass("CST Routing Record should be <b>Activ</b>",
								"CST Routing Record is <b>Activ</b>", "Yes");

					} else {
						loggerFail("CST Routing Record should be <b>Activ</b>",
								"CST Routing Record is <b>InActiv</b>", true);
					}
				} catch (Exception e) {
					loggerFail("CST Routing Record should be <b>Activ</b>",
							"CST Routing Record is <b>InActiv</b>", true);
				}
				
				
			} finally {
				javaClickByLocator((By.xpath("//a[@title='"+ListName+" | CST Routing' and @class='tabHeader slds-context-bar__label-action ']")));
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListViewSettingBtn));
				javaClickByLocator(CST_Routing.ListViewSettingBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteListView));
				javaClickByLocator(CST_Routing.deleteListView);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteBtn));
				javaClickByLocator(CST_Routing.deleteBtn);
				
			}
			
			Thread.sleep(1000);
			selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
			closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			Thread.sleep(3000);
			scrollToElement(CST_Routing.relatedListQuickLinks);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(Accounts.showAllLinkText));
			driver.findElement(Accounts.showAllLinkText).click();
			javaClickByLocator(Accounts.Account_PartnersLink);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Account_Partners));
			isDisplayed(Accounts.Account_Partners, "Accounts partners");
			List<WebElement> Roles = driver.findElements(Accounts.PartnerRoles);
			int count = 0;
			for (WebElement RoleName : Roles) {
				if (RoleName.getText().equalsIgnoreCase(Pace_Responsible_Role)) {
					Assert.assertTrue(true);
					loggerPass("Partner Role " + Pace_Responsible_Role + " Should present",
							"Partner Role " + Pace_Responsible_Role + " Is present", "Yes");
					Pace_Responsible_Role = RoleName.getText();
					break;
				} else {
					count++;
					if (count >= Roles.size()) {
						loggerFail("Partner Role " + Pace_Responsible_Role + " Should present",
								"Partner Role " + Pace_Responsible_Role + " Is Not present", true);
					}
				}
			}
			Thread.sleep(1000);
			String AccountsPartner_Name = driver
					.findElement(By.xpath("//span[text()='" + Pace_Responsible_Role + "']"
							+ "/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Name']"))
					.getText();
			loggerInfo("Account Partners Name " + "<b>" + AccountsPartner_Name + "</b>", "No");

			String AccountsPartner_Email = driver
					.findElement(By.xpath("//span[text()='"+ Pace_Responsible_Role + "']"
							+ "/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Email']"))
					.getText();
			loggerInfo("Account Partners Email " + "<b>" + AccountsPartner_Email + "</b>", "No");

			javaClickByLocator(Accounts.closeAccountPartners);
			String callType = Constants.create_cst.get(0);
			String detailDescription = Constants.create_cst.get(2);
			String CSTProblemReported = Constants.create_cst.get(1);
			CSTCaseCreate(callType, CSTProblemReported, detailDescription, AccountsPartner_Name, AccountsPartner_Email);

		
		softAssert.assertAll();
		

	}

	// Created by - Abhishek Palankar on 15-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on on the Email ID routing
	public void VarifyFieldsOnEmailIdRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify  CC Business Segment,OU Region, and MU/Division fields on the Email ID routing");
		softAssert = new SoftAssert();

			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.EmailIDRoutingRadioBtn);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.EmailIDRoutingPage, "New CST Routing : Email ID");
			fieldValidation("New CST Routing: Email ID Page", Constants.CSTRoting_Fields);

		
		softAssert.assertAll();
		
	}

	// Created by - Abhishek Palankar on 05-June-2023
	// This test is to verify that if there is a matching PACE and Spring related
	// Account Partner, it will assign the Account Partner to Pace
	public static void cstRoutingForPACEAndSpring(HashMap<String, String> MapValue) throws Exception {
		String spring = MapValue.get("Spring");
		String Pace = MapValue.get("Pace");
		String CSTRouting = "";
		String partnerRole = "";
		String name1 = "";
		String managerName1 = "";
		String email1 = "";
		String name2 = "";
		String managerName2 = "";
		String email2 = "";
		int i;

		boolean value = false;
		System.out.println("CST routing for PACE & spring");
		softAssert = new SoftAssert();

		String accountName = "";
		String accountNo = "";
		switch (Constants.bottler) {
		case "4100":
			accountName = "DOLLAR GENERAL #14315";
			accountNo = "500019285";	
			CSTRouting = "CSTR-1387760";
			
			break;
		case "4200":
			accountName = "DOLLAR GENERAL #02523";
			accountNo = "0500008854";
			CSTRouting = "CSTR-2115000";
			
			break;
		case "4400":
			accountName = "1984 CLASSIC ARCADE";
			accountNo = "0500287109";
			CSTRouting = "CSTR-1387774";
			break;
		case "4500":
			accountName = "AVON PARK HIGH SCHOOL";
			accountNo = "0500121742";
			CSTRouting = "CSTR-1387776";
			break;
		case "4600":
			accountName = "DOMINOS #8585";
			accountNo = "0601609054";
			CSTRouting = "CSTR-1387778";
			break;
		case "4700":
			accountName = "DOMINOS PIZZA #1632";
			accountNo = "0600940258";
			CSTRouting = "CSTR-1387780";
			break;
		case "4800":
			accountName = "DOLLAR GENERAL #11614";
			accountNo = "0600608462";
			CSTRouting = "CSTR-1387782";
			break;
		case "4900":
			accountName = "DOMINOS #4080";
			accountNo = "0601230384";
			CSTRouting = "CSTR-1387783";
			break;
		case "5000":
			accountName = "BIG Y #0108";
			accountNo = "0601731108";
			CSTRouting = "CSTR-1387785";
			break;
		case "5200":
			accountName = "DOMINOS #3286";
			accountNo = "0601451828";
			CSTRouting = "CSTR-1387786";
			break;
		}
		
			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			selectAppOrObjFromAppLauncher("object", Constants.CSTRoutingobjectfromlauncher);
			javaClickByLocator(CST_Routing.listViewBtn);
			javaClickByLocator(CST_Routing.allActive);
			Thread.sleep(3000);
			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.clone));
			javaClickByLocator(CST_Routing.clone);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.saveBtn));
			javaClickByLocator(CST_Routing.saveBtn);
			Thread.sleep(5000);
			javaClickByLocator(CST_Routing.addFilter);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
			javaClickByLocator(CST_Routing.field);
			Thread.sleep(1000);
			scrollToElement(CST_Routing.CSTRoutingNumberOption);
			javaClickByLocator(CST_Routing.CSTRoutingNumberOption);
			driver.findElement(CST_Routing.valueField).click();
			driver.findElement(CST_Routing.valueField).sendKeys(CSTRouting);
			driver.findElement(CST_Routing.valueField).sendKeys(Keys.TAB);
			
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.doneBtn);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.saveFilterBtn);
			
			Thread.sleep(2000);
			try {
				if (driver.findElements(By.xpath("//a[@title='" + CSTRouting + "']")).size() > 0) {
					loggerPass("CST Routing should be display <b>" + CSTRouting,
							"CST Routing is displayed <b>" + CSTRouting, "Yes");
				} else {
					loggerFail("CST Routing should be display <b>" + CSTRouting,
							"CST Routing is not displayed <b>" + CSTRouting, true);
				}
				Thread.sleep(1000);
			}finally {
				javaClickByLocator(CST_Routing.ListViewSettingBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteListView));
				javaClickByLocator(CST_Routing.deleteListView);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteBtn));
				javaClickByLocator(CST_Routing.deleteBtn);
				Thread.sleep(2000);
			}
			
			CommonFunctions.closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			Thread.sleep(2000);
			scrollToElement(CST_Routing.relatedListQuickLinks);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.showAllLinkText));

			Thread.sleep(2000);
			driver.findElement(Accounts.showAllLinkText).click();
			javaClickByLocator(Accounts.acountPartner);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.acountPartnerPage));
			int size = driver.findElements(Accounts.size).size();
			// Below loop is used to check Pace partner role
			for (i = 1; i <= size; i++) {
				partnerRole = driver.findElement(By.xpath(
						"//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i + "]//th//lst-formatted-text"))
						.getText();

				if (partnerRole.equalsIgnoreCase(Pace)) {
					value = true;
					break;
				}

			}
			if (value == true) {
				loggerPass(Pace + " Partner Role should be display", partnerRole + " Partner Role is displayed", "Yes");
				name2 = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i
						+ "]/td[4]//lightning-formatted-rich-text//span")).getText();
				managerName2 = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr["
						+ i + "]/td[5]//lightning-formatted-rich-text//span")).getText();
				email2 = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i
						+ "]/td[6]//lightning-formatted-rich-text//span//a")).getText();
				System.out.println("Name " + name2);
				System.out.println("Manager Name " + managerName2);
				System.out.println("Email " + email2);

			} else {
				loggerFail(Pace + " Partner Role should be display",
						Pace + " Partner Role is displayed in Account Partners page", true);
			}
			// Below loop is used to check Spring partner role
			for (i = 1; i <= size; i++) {
				partnerRole = driver.findElement(By.xpath(
						"//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i + "]//th//lst-formatted-text"))
						.getText();

				if (partnerRole.equalsIgnoreCase(spring)) {
					value = true;
					break;
				}

			}
			if (value == true) {
				loggerPass(spring + " Partner Role should be display", partnerRole + " Partner Role is displayed",
						"Yes");
				name1 = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i
						+ "]/td[4]//lightning-formatted-rich-text//span")).getText();
				managerName1 = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr["
						+ i + "]/td[5]//lightning-formatted-rich-text//span")).getText();
				email1 = driver.findElement(By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr[" + i
						+ "]/td[6]//lightning-formatted-rich-text//span//a")).getText();
				System.out.println("Name " + name1);
				System.out.println("Manager Name " + managerName1);
				System.out.println("Email " + email1);

			} else {
				loggerFail(spring + " Partner Role should be display",
						spring + " Partner Role is displayed in Account Partners page", true);
			}

			javaClickByLocator(Accounts.closeAccountPartners);
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

			}
			String callType = values.get(0);
			String CSTProblemReported = values.get(3);
			String detailDescription = values.get(2);
			CSTCaseCreate(callType, CSTProblemReported, detailDescription, name2, email2);

		
		softAssert.assertAll();
		

	}

	// Created by - Niranjan H on 15-June-2023
	// Verify that On the Code Value Mapping Layout for CST user should see a new
	// Field “Send External Communication”.
	public void Verify_SendExternalCommunicationFieldForCST(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify Code Value Mapping Layout for Send External Communication Field For CST");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CVMobjectfromlauncher);
			javaClickByLocator(CST_Routing.New);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.Newcodevaluemapping, "CVM records");
			javaClickByLocator(CST_Routing.CSTMetaData);
			javaClickByLocator(Accounts.next);
			Thread.sleep(1000);
			isDisplayed(CST_Routing.CSTMetaDataPage, "New Code Value Mapping: CST Metadata");
			isDisplayed(CST_Routing.SendExternalCommunication, "Send External Communication");
			Thread.sleep(1000);

			// TC - 248531
			String TypeOf_Field = driver.findElement(CST_Routing.SendExternalCommunicationfield).getAttribute("type");
			if (TypeOf_Field.equals("checkbox")) {
				loggerPass("Send External Communication Should be <b>Checkbox Field</b>",
						"Send External Communication is <b>Checkbox Field</b>", "No");

			} else {
				loggerFail("Send External Communication Should be <b>Checkbox Field</b>",
						"Send External Communication is <b>Not a Checkbox Field</b>", false);
			}

			// TC - 248532
			if (driver.findElements(CST_Routing.checkbox).size() > 0) {
				loggerPass("Send External Communication Checkbox should be <b>Checked</b> by default",
						"Send External Communication Checkbox is <b>Checked</b> by default", "Yes");
			} else {
				loggerFail("Send External Communication Checkbox should be <b>Checked</b> by default",
						"Send External Communication Checkbox is <b>Unchecked</b> by default", true);
			}

		
		softAssert.assertAll();
		
	}

	// Created by - Abhishek Palankar on 21-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the Collector name routing
	public void VarifyFieldsOnCollectorNameRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out
				.println("Verify  CC Business Segment,OU Region, and MU/Division fields on the Collector name routing");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver,Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.CollectorName_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.CollectorNameRoutingPage, "New CST Routing : Collector Name");
			fieldValidation("New CST Routing : Collector Name", Constants.CSTRoting_Fields);

		
		softAssert.assertAll();
		

	}

	// Created by - Abhishek Palankar on 21-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the Employee routing
	public void VarifyFieldsOnEmployeeRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify CC Business Segment,OU Region, and MU/Division fields on the Employee routing");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.Employee_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.EmployeeRoutingPage, "New CST Routing : Employee");
			fieldValidation("New CST Routing : Employee", Constants.CSTRoting_Fields);
		
		softAssert.assertAll();
	}

	// Created by - Abhishek Palankar on 22-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the PACE routing
	public void VarifyFieldsOnPACERouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify CC Business Segment,OU Region, and MU/Division fields on the PACE routing");
		softAssert = new SoftAssert();

			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.Employee_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.EmployeeRoutingPage, "New CST Routing : Pace");
			fieldValidation("New CST Routing : Pace", Constants.CSTRoting_Fields);

		
		softAssert.assertAll();
		
	}

	// Created by - Abhishek Palankar on 22-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the Pace/Spring routing
	public void VarifyFieldsOnPACEAndSpringRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify CC Business Segment,OU Region, and MU/Division fields on the Pace/Spring routing");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.PaceAndSpring_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.PaceAndSpringPage, "New CST Routing : Pace/Spring");
			fieldValidation("New CST Routing : Pace/Spring", Constants.CSTRoting_Fields);

		
		softAssert.assertAll();
		

	}

	// Created by - Abhishek Palankar on 22-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the Spring routing
	public void VarifyFieldsOnSpringRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify CC Business Segment,OU Region, and MU/Division fields on the Spring routing");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.Spring_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.SpringPage, "New CST Routing : Spring");
			fieldValidation("New CST Routing : Spring", Constants.CSTRoting_Fields);

		
		softAssert.assertAll();
		

	}

	// Created by - Niranjan H on 21-Jun-2023
	// This test is to verify that On the Partner Function CST Routing Type,checkbox
	// should be present below “Partner Function” and label it “Assign to Manager”.
	public void Validate_AssigneToMangerCheckBox(HashMap<String, String> mapValue) throws Throwable {
		System.out.println(
				"Verify that On the Partner Function CST Routing Type CheckBox Should Be Present As Assign to Manager");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver,Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.PartnerFunctionRoutingRadioBtn);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.PartnerFunctionRoutingPage, "New CST Routing : Partner Function");
			isDisplayedWithScreenshot(CST_Routing.AssignToManger, "Assign To Manager", "yes");
			Thread.sleep(1000);
			String TypeOf_Field = driver.findElement(CST_Routing.AssignToMangerfield).getAttribute("type");
			if (TypeOf_Field.equals("checkbox")) {
				loggerPass("Assign To Manager Should be <b>Checkbox Field</b>",
						"Assign To Manager is <b>Checkbox Field</b>", "No");

			} else {
				loggerFail("Assign To Manager Should be <b>Checkbox Field</b>",
						"Assign To Manager is <b>Not a Checkbox Field</b>", false);
			}

		softAssert.assertAll();

	}

	// Created by - Abhishek Palankar on 23-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the Sales Office Contact routing
	public void VarifyFieldsOnSalesOfficeContactRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println(
				"Verify CC Business Segment,OU Region, and MU/Division fields on the Sales Office Contact routing");
		softAssert = new SoftAssert();

		try {
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver,Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.SalesOfficeContact_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.SalesOfficeContactPage, "New CST Routing : Sales Office Contact");
			fieldValidation("New CST Routing : Sales Office Contact", Constants.CSTRoting_Fields);

		} catch (Exception e) {

			screenshot = capture(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(e.getMessage());
		}
		softAssert.assertAll();
		status = logger.getStatus();

	}

	// Created by - Abhishek Palankar on 23-June-2023
	// Verify that user should be able to see the CC Business Segment,OU Region, and
	// MU/Division fields on the Partner Function routing
	public void VarifyFieldsOnPartnerFunctionRouting(HashMap<String, String> mapValue) throws Throwable {
		System.out.println(
				"Verify CC Business Segment,OU Region, and MU/Division fields on the Partner Function routing");
		softAssert = new SoftAssert();

		try {
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
			try {
				String title = "Recently Viewed | CST Routing";
				List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
				for (WebElement closetab : close) {
					if (!closetab.getText().contains(title))
						javaClickByWebElement(closetab);
				}
			} catch (Exception e) {
			}
			javaClickByLocator(CST_Routing.New);
			new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
			isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
			javaClickByLocator(CST_Routing.PartnerFunction_Routing);
			javaClickByLocator(Accounts.next);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.PartnerFunctionPage, "New CST Routing : Partner Function");
			fieldValidation("New CST Routing : Partner Function", Constants.CSTRoting_Fields);

		} catch (Exception e) {

			screenshot = capture(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(e.getMessage());
		}
		softAssert.assertAll();
		status = logger.getStatus();
	}
	
	
	
	
	
	// Created by -Niranjan H on 22-June-2023
		// Verify that When “Assign to Manager” is checked, using Partner Function Routing type CSTs will be assigned to the Manager
		public void ValidateAssignToManagerON_Case(HashMap<String, String> mapValue) throws Throwable {
			System.out.println(
					"Verify that When “Assign to Manager” is checked, using Partner Function Routing type CSTs will be assigned to the Manager");
			softAssert = new SoftAssert();

			String accountName = "";
			String accountNo = "";
			String CST_PartnerFunction = "";
			String PartnerFunction_Role = "";
			String ListName =mapValue.get("List Name");
			
			switch (Constants.bottler) {
			case "4100":
				accountName = "DOLLAR GENERAL #14315";
				accountNo = "500019285";
				CST_PartnerFunction = "CSTR-1387791";
				break;
			case "4200":
				accountName = "DOLLAR GENERAL #02523";
				accountNo = "0500008854";
				CST_PartnerFunction = "CSTR-2115002";
				break;
			case "4400":
				accountName = "1984 CLASSIC ARCADE";
				accountNo = "0500287109";
				CST_PartnerFunction = "CSTR-1387792";
				break;
			case "4500":
				accountName = "AVON PARK HIGH SCHOOL";
				accountNo = "0500121742";
				CST_PartnerFunction = "CSTR-1387793";
				break;
			case "4600":
				accountName = "DOMINOS #8585";
				accountNo = "0601609054";
				CST_PartnerFunction = "CSTR-1387794";
				break;
			case "4700":
				accountName = "DOMINOS PIZZA #1632";
				accountNo = "600940258";
				CST_PartnerFunction = "CSTR-2115009";
				break;
			case "4800":
				accountName = "DOLLAR GENERAL #11614";
				accountNo = "0600608462";
				CST_PartnerFunction = "CSTR-1387796";
				break;
			case "4900":
				accountName = "DOMINOS #4080";
				accountNo = "0601230384";
				CST_PartnerFunction = "CSTR-1387797";
				break;
			case "5000":
				accountName = "BIG Y #0108";
				accountNo = "0601731108";
				CST_PartnerFunction = "CSTR-1387798";
				break;
			case "5200":
				accountName = "DOMINOS #3286";
				accountNo = "0601451828";
				CST_PartnerFunction = "CSTR-1387799";
				break;
			case "5300":
				accountName = "ABBY CONVENIENCE STORE";
				accountNo = "602184829";
				CST_PartnerFunction = "CSTR-1387800";
				break;
			}

			
				selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
				selectAppOrObjFromAppLauncher("object", Constants.CSTRoutingobjectfromlauncher);				

//				driver.navigate().refresh();
				Thread.sleep(2000);
				javaClickByLocator(CST_Routing.ListViewSettingBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.newlistview));
				javaClickByLocator(CST_Routing.newlistview);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListName));
				fillFieldByLocator(CST_Routing.ListName,ListName);
				Thread.sleep(5000);
				javaClickByLocator(CST_Routing.SaveListView);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.filterbyowner));
				javaClickByLocator(CST_Routing.filterbyowner);
				Thread.sleep(2000);
				javaClickByLocator(CST_Routing.allcst);
				javaClickByLocator(CST_Routing.done);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.addFilter);
				Thread.sleep(1000);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
				javaClickByLocator(CST_Routing.field);
				Thread.sleep(2000);
				scrollToElement(CST_Routing.recordtype);
				javaClickByLocator(CST_Routing.recordtype);
				driver.findElement(CST_Routing.valuebox).click();
				scrollToElement(CST_Routing.RecordTypePartnerFunction);
				driver.findElement(CST_Routing.RecordTypePartnerFunction).click();
				javaClickByLocator(CST_Routing.done);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.addFilter);
				Thread.sleep(1000);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
				javaClickByLocator(CST_Routing.field);
				scrollToElement(CST_Routing.CSTRoutingNumberOption);
				javaClickByLocator(CST_Routing.CSTRoutingNumberOption);
				driver.findElement(CST_Routing.valueField).click();
				driver.findElement(CST_Routing.valueField).sendKeys(CST_PartnerFunction);
				driver.findElement(CST_Routing.valueField).sendKeys(Keys.TAB);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.doneBtn);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.saveFilterBtn);
				Thread.sleep(2000);
				javaClickByLocator(CST_Routing.CloseFilter);
				tableLoadingWait();
				try {
					
				
				if (driver.findElements(By.xpath("//a[@title='" + CST_PartnerFunction + "']")).size() > 0) {
					loggerPass("CST Routing should be display <b>" + CST_PartnerFunction + "</b>",
							"CST Routing is displayed <b>" + CST_PartnerFunction + "</b>", "Yes");
				} else {
					loggerFail("CST Routing should be display <b>" + CST_PartnerFunction + "</b>",
							"CST Routing is not displayed <b>" + CST_PartnerFunction, true);
				}
				driver.findElement(By.xpath("//a[@title='" + CST_PartnerFunction + "']")).click();
				Thread.sleep(1000);
				PartnerFunction_Role = GetText(CST_Routing.PartnerFunction);
				Thread.sleep(1000);
				try {
					if (driver.findElements(CST_Routing.ActiveCheckbox).size() > 0) {
						loggerPass("CST Routing Record should be <b>Activ</b>",
								"CST Routing Record is <b>Activ</b>", "Yes");

					} else {
						loggerFail("CST Routing Record should be <b>Activ</b>",
								"CST Routing Record is <b>InActiv</b>", true);
					}
				} catch (Exception e) {
					loggerFail("CST Routing Record should be <b>Activ</b>",
							"CST Routing Record is <b>InActiv</b>", true);
				}
				String TypeOf_Field = driver.findElement(CST_Routing.AssignToMangerfield).getAttribute("type");
				if (TypeOf_Field.equals("checkbox")) {
					loggerPass("Assign To Manager Should be <b>Checkbox Field</b>",
							"Assign To Manager is <b>Checkbox Field</b>", "No");

				} else {
					loggerFail("Assign To Manager Should be <b>Checkbox Field</b>",
							"Assign To Manager is <b>Not a Checkbox Field</b>", false);
				}

				try {
					if (driver.findElements(CST_Routing.AssignToManagerCheckbox).size() > 0) {
						loggerPass("Assign To Manager Checkbox should be <b>Checked</b>",
								"Assign To Manager Checkbox is <b>Checked</b> ", "Yes");

					} else {
						loggerFail("Assign To Manager Checkbox should be <b>Checked</b>",
								"Assign To Manager Checkbox Is <b>Unchecked</b>", true);
					}
				} catch (Exception e) {
					loggerFail("Assign To Manager Checkbox should be <b>Checked</b>",
							"Assign To Manager Checkbox Is <b>Unchecked</b>", true);
				}
				} finally {
			javaClickByLocator((By.xpath("//a[@title='"+ListName+" | CST Routing' and @class='tabHeader slds-context-bar__label-action ']")));
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListViewSettingBtn));
			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteListView));
			javaClickByLocator(CST_Routing.deleteListView);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteBtn));
			javaClickByLocator(CST_Routing.deleteBtn);
			
		}				
				Thread.sleep(1000);
				selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
				closeMultipleTabs();
				SelectAccountFromGlobalSearch(accountNo, accountName);
				Thread.sleep(3000);
				scrollToElement(CST_Routing.relatedListQuickLinks);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.showAllLinkText));
				driver.findElement(Accounts.showAllLinkText).click();
				javaClickByLocator(Accounts.Account_PartnersLink);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Account_Partners));
				isDisplayed(Accounts.Account_Partners, "Accounts partners");
				List<WebElement> Roles = driver.findElements(Accounts.PartnerRoles);
				int count = 0;
				for (WebElement RoleName : Roles) {

					if (RoleName.getText().equalsIgnoreCase(PartnerFunction_Role.substring(5))) {

						Assert.assertTrue(true);
						loggerPass("Partner Role " + PartnerFunction_Role + " Should present",
								"Partner Role " + PartnerFunction_Role + " Is present", "Yes");
						PartnerFunction_Role = RoleName.getText();
						break;
					} else {
						count++;
						if (count >= Roles.size()) {
							loggerFail("Partner Role " + PartnerFunction_Role + " Should present",
									"Partner Role " + PartnerFunction_Role + " Is Not present", true);
						}
					}
				}
				Thread.sleep(1000);
				String Manager_Name = driver.findElement(By.xpath("//span[text()='"+ PartnerFunction_Role
						+ "']"
						+ "/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Manager Name']"))
						.getText();
				loggerInfo("Account Partners Manager Name " + "<b>" + Manager_Name + "</b>", "No");

				String Managers_Email = driver
						.findElement(By.xpath("//span[text()='" + PartnerFunction_Role + "']"
								+ "/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Email']"))
						.getText();
				loggerInfo("Account Partners Manager Email " + "<b>" + Managers_Email + "</b>", "No");
				javaClickByLocator(Accounts.closeAccountPartners);
				String callType = Constants.createCST_ForPartnerFunctions.get(0);
				String detailDescription = Constants.createCST_ForPartnerFunctions.get(2);
				String CSTProblemReported = Constants.createCST_ForPartnerFunctions.get(1);
				Features_CSTRouting.CSTCaseCreate(callType, CSTProblemReported, detailDescription, Manager_Name,
						Managers_Email);

			
			softAssert.assertAll();

		}

		// Created by -Niranjan H on 22-June-2023
		// Verify that if partner function manager is blank keep assign to blank,Trigger the CST routing error The Partner Function's Manager is not maintained.Please reassign or notify Contact Center Leadership
		public void ValidateAssignToManagerON_Case_PartnerFunctionsManagerIsnotPresent(HashMap<String, String> mapValue)
				throws Throwable {
			System.out.println("Verify that if partner function manager is blank keep assign to assign to blank and Trigger the CST routing error");
			softAssert = new SoftAssert();

			String accountName = "";
			String accountNo = "";
			String CST_PartnerFunction = "";
			String PartnerFunction_Role = "";
			String caseNumber = "";
			String Manager_Name = "";
			String TIP = null;
			String ListName =mapValue.get("List Name");
			switch (Constants.bottler) {
			case "4100":
				accountName = "WALMART #1346 SUPERCENTER-M3";
				accountNo = "500479713";
				CST_PartnerFunction = "CSTR-1387791";
				break;
			case "4200":
				accountName = "AUTOZONE #4887";
				accountNo = "0601984036";
				CST_PartnerFunction = "CSTR-2115102";
				break;
			case "4400":
				accountName = "1984 CLASSIC ARCADE";
				accountNo = "0500287109";
				CST_PartnerFunction = "CSTR-1387792";
				break;
			case "4500":
				accountName = "FEDEX OFFICE & PRNT SRV INC";
				accountNo = "600396006";
				CST_PartnerFunction = "CSTR-1387793";
				break;
			case "4600":
				accountName = "#1 EAST CAMPUS";
				accountNo = "501050383";
				CST_PartnerFunction = "CSTR-1387794";
				break;
			case "4700":
				accountName = "18 NORTH CENTRAL";
				accountNo = "600943635";
				CST_PartnerFunction = "CSTR-2115010";
				break;
			case "4800":
				accountName = "DOMINOS #6504";
				accountNo = "501099714";
				CST_PartnerFunction = "CSTR-1387796";
				break;
			case "4900":
				accountName = "10 X FITNESS";
				accountNo = "501014738";
				CST_PartnerFunction = "CSTR-1387797";
				break;
			case "5000":
				accountName = "DOMINOS #3710 GREENFIELD";
				accountNo = "601778750";
				CST_PartnerFunction = "CSTR-1387798";
				break;
			case "5200":
				accountName = "20TH CENTURY TOWING";
				accountNo = "601299879";
				CST_PartnerFunction = "CSTR-1387799";
				break;
			case "5300":
				accountName = "DOMINOS PIZZA #10273";
				accountNo = "602129363";
				CST_PartnerFunction = "CSTR-1387800";
				break;
			}

	
				selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
				selectAppOrObjFromAppLauncher("object", Constants.CSTRoutingobjectfromlauncher);
				
				driver.navigate().refresh();
				Thread.sleep(2000);
				javaClickByLocator(CST_Routing.ListViewSettingBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.newlistview));
				javaClickByLocator(CST_Routing.newlistview);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListName));
				fillFieldByLocator(CST_Routing.ListName,ListName);
				Thread.sleep(5000);
				javaClickByLocator(CST_Routing.SaveListView);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.filterbyowner));
				javaClickByLocator(CST_Routing.filterbyowner);
				Thread.sleep(2000);
				javaClickByLocator(CST_Routing.allcst);
				javaClickByLocator(CST_Routing.done);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.addFilter);
				Thread.sleep(1000);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
				javaClickByLocator(CST_Routing.field);
				Thread.sleep(2000);
				scrollToElement(CST_Routing.recordtype);
				javaClickByLocator(CST_Routing.recordtype);
				driver.findElement(CST_Routing.valuebox).click();
				scrollToElement(CST_Routing.RecordTypePartnerFunction);
				driver.findElement(CST_Routing.RecordTypePartnerFunction).click();
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.doneBtn);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.addFilter);
				Thread.sleep(1000);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
				javaClickByLocator(CST_Routing.field);
				scrollToElement(CST_Routing.CSTRoutingNumberOption);
				javaClickByLocator(CST_Routing.CSTRoutingNumberOption);
				driver.findElement(CST_Routing.valueField).click();
				driver.findElement(CST_Routing.valueField).sendKeys(CST_PartnerFunction);
				driver.findElement(CST_Routing.valueField).sendKeys(Keys.TAB);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.doneBtn);
				Thread.sleep(1000);
				javaClickByLocator(CST_Routing.saveFilterBtn);
				Thread.sleep(2000);
				javaClickByLocator(CST_Routing.CloseFilter);				
				tableLoadingWait();
				try {
				if (driver.findElements(By.xpath("//a[@title='" + CST_PartnerFunction + "']")).size() > 0) {
					loggerPass("CST Routing should be display <b>" + CST_PartnerFunction + "</b>",
							"CST Routing is displayed <b>" + CST_PartnerFunction + "</b>", "Yes");
				} else {
					loggerFail("CST Routing should be display <b>" + CST_PartnerFunction + "</b>",
							"CST Routing is not displayed <b>" + CST_PartnerFunction, true);
				}
				driver.findElement(By.xpath("//a[@title='" + CST_PartnerFunction + "']")).click();
				Thread.sleep(1000);
				PartnerFunction_Role = GetText(CST_Routing.PartnerFunction);
				Thread.sleep(1000);

				String TypeOf_Field = driver.findElement(CST_Routing.AssignToMangerfield).getAttribute("type");
				if (TypeOf_Field.equals("checkbox")) {
					loggerPass("Assign To Manager Should be <b>Checkbox Field</b>",
							"Assign To Manager is <b>Checkbox Field</b>", "No");

				} else {
					loggerFail("Assign To Manager Should be <b>Checkbox Field</b>",
							"Assign To Manager is <b>Not a Checkbox Field</b>", false);
				}

				try {
					if (driver.findElements(CST_Routing.AssignToManagerCheckbox).size() > 0) {
						loggerPass("Assign To Manager Checkbox should be <b>Checked</b>",
								"Assign To Manager Checkbox is <b>Checked</b> ", "Yes");

					} else {
						loggerFail("Assign To Manager Checkbox should be <b>Checked</b>",
								"Assign To Manager Checkbox Is <b>Unchecked</b>", true);
					}
				} catch (Exception e) {
					loggerFail("Assign To Manager Checkbox should be <b>Checked</b>",
							"Assign To Manager Checkbox Is <b>Unchecked</b>", true);
				}
				} finally {
					javaClickByLocator((By.xpath("//a[@title='"+ListName+" | CST Routing' and @class='tabHeader slds-context-bar__label-action ']")));
					new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListViewSettingBtn));
					javaClickByLocator(CST_Routing.ListViewSettingBtn);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteListView));
					javaClickByLocator(CST_Routing.deleteListView);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteBtn));
					javaClickByLocator(CST_Routing.deleteBtn);
					
				}				
				
				Thread.sleep(1000);
				selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
				closeMultipleTabs();
				SelectAccountFromGlobalSearch(accountNo, accountName);
				Thread.sleep(3000);
				scrollToElement(CST_Routing.relatedListQuickLinks);
				new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.showAllLinkText));
				driver.findElement(Accounts.showAllLinkText).click();
				javaClickByLocator(Accounts.Account_PartnersLink);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Account_Partners));
				isDisplayed(Accounts.Account_Partners, "Accounts partners");
				List<WebElement> Roles = driver.findElements(Accounts.PartnerRoles);
				int count = 0;
				for (WebElement RoleName : Roles) {

					if (RoleName.getText().equalsIgnoreCase(PartnerFunction_Role.substring(5))) {

						Assert.assertTrue(true);
						loggerPass("Partner Role " + PartnerFunction_Role + " Should present",
								"Partner Role " + PartnerFunction_Role + " Is present", "Yes");
						PartnerFunction_Role = RoleName.getText();
						break;
					} else {
						count++;
						if (count >= Roles.size()) {
							loggerFail("Partner Role " + PartnerFunction_Role + " Should present",
									"Partner Role " + PartnerFunction_Role + " Is Not present", true);
						}
					}
				}
				Thread.sleep(1000);
				Manager_Name = driver.findElement(By.xpath("//span[text()='" + PartnerFunction_Role + "']"
						+ "/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Manager Name']"))
						.getText();
				if (Manager_Name == "") {
					loggerInfo("Account Partners Manager Name is Not maintained", "No");
				} else {
					loggerFail("Account Partners Manager name Should not prsent",
							"Account Partners Manager Name is prsent <b>" + Manager_Name + "</b>", true);
				}

				javaClickByLocator(Accounts.closeAccountPartners);
				String callType = Constants.create_cst.get(0);
				String detailDescription = Constants.create_cst.get(2);
				String CSTProblemReported = Constants.create_cst.get(1);

				javaClickByLocator(Accounts.casesWidget);
				Thread.sleep(2000);
				javaClickByLocator(Accounts.newcase);
				javaClickByLocator(Cases.CSTbtn);

				javaClickByLocator(Accounts.next);
				Thread.sleep(4000);

				if (driver.findElements(Cases.CSTPopup).size() > 0) {
					loggerPass("New Case: Customer Service Ticket page layout should be dispaly",
							"New Case: Customer Service Ticket page layout displayed", "No");

					javaClickByLocator(Cases.callType);

					javaClickByLocator(By.xpath("//lightning-base-combobox-item[@data-value='" + callType + "']"));

					driver.findElement(Accounts.detailedDescriptionTextBox).click();
					driver.findElement(Accounts.detailedDescriptionTextBox).sendKeys(detailDescription);

					javaClickByLocator(Cases.CSTProblemReported);

					javaClickByLocator(
							By.xpath("//lightning-base-combobox-item[@data-value='" + CSTProblemReported + "']"));

					javaClickByLocator(Accounts.saveBtn);
					Thread.sleep(3000);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(Cases.assignedToField));
					caseNumber = GetText(Cases.caseNo);

					if (driver.findElements(Cases.assignedToField).size() > 0) {
						loggerPass("The Newly created Case <b>" + caseNumber + "</b> should be displayed.",
								"The Newly created Case <b>" + caseNumber + "</b> is displayed.", "No");

						if (driver.findElements(Cases.assignedToFieldValue).size() > 0) {
							if (driver.findElement(Cases.assignedToFieldValue).getText().equals("")) {
								loggerPass("Assigne To Value Should Be Blank", "Assigne To Value is Blank", "No");
							} else {
								loggerFail("Assigne To Value Should Be Blank", "Case is Assigne To <b>"
										+ driver.findElement(Cases.assignedToFieldValue).getText() + "</b>", false);
							}
						} else {
							loggerFail("Feild Should be present", "Feild is Not present", false);
						}

						if (driver.findElements(Cases.assignedToEmailFieldValue).size() > 0) {
							if (driver.findElement(Cases.assignedToEmailFieldValue).getText().equals("")) {
								loggerPass("Assigne To Email Value Should Be Blank", "Assigne To Email Value is Blank",
										"No");
							} else {
								loggerFail("Assigne To Email Value Should Be Blank",
										"Case is Assigne To Email <b>"
												+ driver.findElement(Cases.assignedToEmailFieldValue).getText() + "</b>",
										false);
							}
						} else {
							loggerFail("Feild Should be present", "Feild is Not present", false);
						}
					}

				}   
				TIP = "The Partner Function's manager is not maintained. Please reassign as necessary or notify Contact Center Leadership";
				if (GetText(Cases.cst_tip).equalsIgnoreCase(TIP)) {
					loggerPass("Tip With Note <b>" + TIP + "</b> Should be Display",
							"Tip With Note <b>" + GetText(Cases.cst_tip) + "</b> is Displayed", "No");
				} else {
					loggerPass("Tip With Note <b>" + TIP + "</b> Should be Display",
							"Tip With Note <b>" + TIP + "</b> is Not Displayed", "No");
				}

			
			softAssert.assertAll();
			
		}
		
		// Created by -Niranjan H on 22-June-2023
		//Verify that  When “Assign to Manager” is left unchecked, the CST will route to the selected Partner Function
		public void ValidateAssignedToManagerisUncheked(HashMap<String, String> mapValue)throws Throwable {
			System.out.println("Verify when Assigned to Manger Checkbox is Unchcked");
			softAssert = new SoftAssert();

			String accountName = "";
			String accountNo = "";
			String CST_PartnerFunction = "";
			String PartnerFunction_Role = "";
			String Manager_Name = "";
			String partner_Name ="";
			String partner_Email ="";
			String ListName =mapValue.get("List Name");
			switch (Constants.bottler) {
			case "4100":
				accountName = "DOLLAR GENERAL #14315";
				accountNo = "500019285";
				CST_PartnerFunction = "CSTR-1387791";
				break;
			case "4200":
				accountName = "DOLLAR GENERAL #02523";
				accountNo = "0500008854";
				CST_PartnerFunction = "CSTR-2115005"; // CSTR-1387790
				break;
			case "4400":
				accountName = "1984 CLASSIC ARCADE";
				accountNo = "0500287109";
				CST_PartnerFunction = "CSTR-1387792";
				break;
			case "4500":
				accountName = "AVON PARK HIGH SCHOOL";
				accountNo = "0500121742";
				CST_PartnerFunction = "CSTR-1387793";
				break;
			case "4600":
				accountName = "DOMINOS #8585";
				accountNo = "0601609054";
				CST_PartnerFunction = "CSTR-1387794";
				break;
			case "4700":
				accountName = "18 NORTH CENTRAL";
				accountNo = "0600943635";
				CST_PartnerFunction = "CSTR-2115013";
				break;
			case "4800":
				accountName = "DOLLAR GENERAL #11614";
				accountNo = "0600608462";
				CST_PartnerFunction = "CSTR-1387796";
				break;
			case "4900":
				accountName = "DOMINOS #4080";
				accountNo = "0601230384";
				CST_PartnerFunction = "CSTR-1387797";
				break;
			case "5000":
				accountName = "BIG Y #0108";
				accountNo = "0601731108";
				CST_PartnerFunction = "CSTR-1387798";
				break;
			case "5200":
				accountName = "DOMINOS #3286";
				accountNo = "0601451828";
				CST_PartnerFunction = "CSTR-1387799";
				break;
			case "5300":
				accountName = "ABBY CONVENIENCE STORE";
				accountNo = "602184829";
				CST_PartnerFunction = "CSTR-1387800";
				break;
			}

			
			selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
			selectAppOrObjFromAppLauncher("object", Constants.CSTRoutingobjectfromlauncher);
			
			driver.navigate().refresh();
			Thread.sleep(2000);
			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.newlistview));
			javaClickByLocator(CST_Routing.newlistview);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListName));
			fillFieldByLocator(CST_Routing.ListName,ListName);
			Thread.sleep(5000);
			javaClickByLocator(CST_Routing.SaveListView);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.filterbyowner));
			javaClickByLocator(CST_Routing.filterbyowner);
			Thread.sleep(2000);
			javaClickByLocator(CST_Routing.allcst);
			javaClickByLocator(CST_Routing.done);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.addFilter);
			Thread.sleep(1000);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
			javaClickByLocator(CST_Routing.field);
			Thread.sleep(2000);
			scrollToElement(CST_Routing.recordtype);
			javaClickByLocator(CST_Routing.recordtype);
			driver.findElement(CST_Routing.valuebox).click();
			scrollToElement(CST_Routing.RecordTypePartnerFunction);
			driver.findElement(CST_Routing.RecordTypePartnerFunction).click();
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.doneBtn);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.addFilter);
			Thread.sleep(1000);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.field));
			javaClickByLocator(CST_Routing.field);
			scrollToElement(CST_Routing.CSTRoutingNumberOption);
			javaClickByLocator(CST_Routing.CSTRoutingNumberOption);
			driver.findElement(CST_Routing.valueField).click();
			driver.findElement(CST_Routing.valueField).sendKeys(CST_PartnerFunction);
			driver.findElement(CST_Routing.valueField).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.doneBtn);
			Thread.sleep(1000);
			javaClickByLocator(CST_Routing.saveFilterBtn);
			Thread.sleep(2000);
			javaClickByLocator(CST_Routing.CloseFilter);
			tableLoadingWait();
			try {
			if (driver.findElements(By.xpath("//a[@title='" + CST_PartnerFunction + "']")).size() > 0) {
				loggerPass("CST Routing should be display <b>" + CST_PartnerFunction + "</b>",
						"CST Routing is displayed <b>" + CST_PartnerFunction + "</b>", "Yes");
			} else {
				loggerFail("CST Routing should be display <b>" + CST_PartnerFunction + "</b>",
						"CST Routing is not displayed <b>" + CST_PartnerFunction, true);
			}
			driver.findElement(By.xpath("//a[@title='" + CST_PartnerFunction + "']")).click();
			Thread.sleep(1000);
			try {
				if (driver.findElements(CST_Routing.ActiveCheckbox).size() > 0) {
					loggerPass("CST Routing Record should be <b>Activ</b>",
							"CST Routing Record is <b>Activ</b>", "Yes");

				} else {
					loggerFail("CST Routing Record should be <b>Activ</b>",
							"CST Routing Record is <b>InActiv</b>", true);
				}
			} catch (Exception e) {
				loggerFail("CST Routing Record should be <b>Activ</b>",
						"CST Routing Record is <b>InActiv</b>", true);
			}
			PartnerFunction_Role = GetText(CST_Routing.PartnerFunction);
			Thread.sleep(1000);
			String TypeOf_Field = driver.findElement(CST_Routing.AssignToMangerfield).getAttribute("type");
			if (TypeOf_Field.equals("checkbox")) {
				loggerPass("Assign To Manager Should be <b>Checkbox Field</b>",
						"Assign To Manager is <b>Checkbox Field</b>", "No");

			} else {
				loggerFail("Assign To Manager Should be <b>Checkbox Field</b>",
						"Assign To Manager is <b>Not a Checkbox Field</b>", false);
			}

			if (!(driver.findElements(CST_Routing.AssignToManagerCheckbox).size() > 0)) {
				loggerPass("Assign To Manager Checkbox should be <b>Unchecked</b>",
						"Assign To Manager Checkbox is <b>Unchecked</b>", "Yes");

			} else {
				loggerFail("Assign To Manager Checkbox should be <b>Unchecked</b>",
						"Assign To Manager Checkbox Is <b>Checked</b>", true);
			}
		} finally {
			javaClickByLocator((By.xpath("//a[@title='"+ListName+" | CST Routing' and @class='tabHeader slds-context-bar__label-action ']")));
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.ListViewSettingBtn));
			javaClickByLocator(CST_Routing.ListViewSettingBtn);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteListView));
			javaClickByLocator(CST_Routing.deleteListView);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.deleteBtn));
			javaClickByLocator(CST_Routing.deleteBtn);
			
		}				
		
			
			Thread.sleep(1000);
			selectAppOrObjFromAppLauncher("Object", Constants.accountfromlauncher);
			closeMultipleTabs();
			SelectAccountFromGlobalSearch(accountNo, accountName);
			Thread.sleep(3000);
			scrollToElement(CST_Routing.relatedListQuickLinks);
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(Accounts.showAllLinkText));
			driver.findElement(Accounts.showAllLinkText).click();
			javaClickByLocator(Accounts.Account_PartnersLink);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(Accounts.Account_Partners));
			isDisplayed(Accounts.Account_Partners, "Accounts partners");
			List<WebElement> Roles = driver.findElements(Accounts.PartnerRoles);
			int count = 0;
			for (WebElement RoleName : Roles) {

				if (RoleName.getText().equalsIgnoreCase(PartnerFunction_Role.substring(5))) {

					Assert.assertTrue(true);
					loggerPass("Partner Role " + PartnerFunction_Role + " Should present",
							"Partner Role " + PartnerFunction_Role + " Is present", "Yes");
					PartnerFunction_Role = RoleName.getText();
					break;
				} else {
					count++;
					if (count >= Roles.size()) {
						loggerFail("Partner Role " + PartnerFunction_Role + " Should present",
								"Partner Role " + PartnerFunction_Role + " Is Not present", true);
					}
				}
			}
			Thread.sleep(1000);
			Manager_Name = driver.findElement(By.xpath("//span[text()='" + PartnerFunction_Role + "']"
					+ "/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Manager Name']"))
					.getText();

			partner_Name = driver
					.findElement(By.xpath("//span[text()='" + PartnerFunction_Role
							+ "']/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Name']"))
					.getText();
			partner_Email = driver
					.findElement(By.xpath("//span[text()='" + PartnerFunction_Role
							+ "']/ancestor::th[@data-label='Partner Role']/following-sibling::td[@data-label='Email']"))
					.getText();

			javaClickByLocator(Accounts.closeAccountPartners);
			String callType = Constants.createCST_ForAssigneManager.get(0);
			String detailDescription = Constants.createCST_ForAssigneManager.get(2);
			String CSTProblemReported = Constants.createCST_ForAssigneManager.get(1);

			Features_CSTRouting.CSTCaseCreate(callType, CSTProblemReported, detailDescription, partner_Name, partner_Email);

			
			softAssert.assertAll();
			
		}




// Created by -Niranjan H on 10-Jul-2023
		// Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Email ID Routing page.
		public void ValidateESTProblemOn_EmailIDRouting(HashMap<String, String> mapValue) throws Throwable {
			System.out.println("Verify that user should able to see EST Problem Reported On Email ID Routing page");
			softAssert = new SoftAssert();

				selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
				selectObjectFromDropdown(Constants.home);
				selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
				try {
					String title = "Recently Viewed | CST Routing";
					List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
					for (WebElement closetab : close) {
						if (!closetab.getText().contains(title)) 
						{	javaClickByWebElement(closetab);
							Thread.sleep(1000);
							if(driver.findElements(CST_Routing.discardbtn).size()>0)
							{driver.findElement(CST_Routing.discardbtn).click();}	
						}
					}
				} catch (Exception e) {
				}
				javaClickByLocator(CST_Routing.New);
				new WebDriverWait(driver, Duration.ofSeconds(20))
						.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
				isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
				javaClickByLocator(CST_Routing.EmailIDRoutingRadioBtn);
				javaClickByLocator(Accounts.next);
				Thread.sleep(2000);
				isDisplayed(CST_Routing.EmailIDRoutingPage, "New CST Routing : Email ID");
				isDisplayed(CST_Routing.CSTProblemDropdown, "CST Problem Reported Field On Email ID Routing Page");
				isDisplayedWithScreenshot(CST_Routing.ESTProblemDropdown, "EST Problem Reported Field On Email ID Routing Page", "yes");

			
			softAssert.assertAll();
			
		}
		
		
		
		// Created by -Niranjan H on 10-Jul-2023
		// Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Employee Routing page.
		public void ValidateESTProblemOn_EmployeeRouting(HashMap<String, String> mapValue)throws Throwable {
		System.out.println("Verify that user should able to see EST Problem Reported On Employee Routing page");
		softAssert = new SoftAssert();
					
		
		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		selectObjectFromDropdown(Constants.home);
		selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
		try {
			String title = "Recently Viewed | CST Routing";
			List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
			for (WebElement closetab : close) {
				if (!closetab.getText().contains(title)) 
					{	javaClickByWebElement(closetab);
						Thread.sleep(1000);
						if(driver.findElements(CST_Routing.discardbtn).size()>0)
						{driver.findElement(CST_Routing.discardbtn).click();}	
					}
			}
		} catch (Exception e) {
		}
		javaClickByLocator(CST_Routing.New);
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
		isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
		javaClickByLocator(CST_Routing.Employee_Routing);
		javaClickByLocator(Accounts.next);
		Thread.sleep(2000);
		isDisplayed(CST_Routing.EmployeeRoutingPage, "New CST Routing : Employee");
		isDisplayed(CST_Routing.CSTProblemDropdown, "CST Problem Reported Field On Employee Routing Page");
		isDisplayedWithScreenshot(CST_Routing.ESTProblemDropdown,"EST Problem Reported Field On Employee Routing Page","yes");
		
		
			softAssert.assertAll();
			
		}
		
		
		// Created by -Niranjan H on 10-Jul-2023
		// Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Partner Function Routing page.
		public void ValidateESTProblemOn_PartnerFunctionRouting(HashMap<String, String> mapValue)throws Throwable {
		System.out.println("Verify that user should able to see EST Problem Reported On Partner Function Routing page");
		softAssert = new SoftAssert();
					
		
		selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
		selectObjectFromDropdown(Constants.home);
		selectAppOrObjFromAppLauncher("Object", Constants.CSTRoutingobjectfromlauncher);
		try {
			String title = "Recently Viewed | CST Routing";
			List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
			for (WebElement closetab : close) {
				if (!closetab.getText().contains(title)) 
				{	javaClickByWebElement(closetab);
					Thread.sleep(1000);
					if(driver.findElements(CST_Routing.discardbtn).size()>0)
					{driver.findElement(CST_Routing.discardbtn).click();}	
				}
			}
		} catch (Exception e) {
		}
		javaClickByLocator(CST_Routing.New);
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(CST_Routing.NewCST_Routing));
		isDisplayed(CST_Routing.NewCST_Routing, "CST routing record");
		javaClickByLocator(CST_Routing.PartnerFunction_Routing);
		javaClickByLocator(Accounts.next);
		Thread.sleep(2000);
		isDisplayed(CST_Routing.PartnerFunctionPage, "New CST Routing : Employee");
		isDisplayed(CST_Routing.CSTProblemDropdown, "CST Problem Reported Field On Partner Function Routing Page");
		isDisplayedWithScreenshot(CST_Routing.ESTProblemDropdown,"EST Problem Reported Field On Partner Function Routing Page","yes");
		
		
			softAssert.assertAll();
			
		}
		
		
		// Created by -Niranjan H on 12-Jul-2023
		// Verify That if User tries to add both CST Problem Reported Code And an EST Problem Reported Code, Validation rule with an Error should be displayed
		public void ValidateErrorMSGFor_CSTAndESTProblemReported(HashMap<String, String> mapValue)throws Throwable {
		System.out.println("User tries to add both CST Problem Reported Code And an EST Problem Reported Code, Validation rule with an Error should be displayed");
		softAssert = new SoftAssert();
				String CST_ProblemReported = "Ad Activity";
				String EST_ProblemReported = "Animal In Equipment";
				String PartnerFunction = "ZL - Area Sales Mgr";
				String Message="";
		
			System.out.println("== Partner Function Routing ==");
			ValidateESTProblemOn_PartnerFunctionRouting(mapValue);
			select_dropdownValue(CST_ProblemReported, CST_Routing.CSTProblemDropdown);
			Thread.sleep(1000);
			select_dropdownValue(EST_ProblemReported, CST_Routing.ESTProblemDropdown);
			select_dropdownValue(Constants.bottler, CST_Routing.SalesOrganizationDropdown);
			select_dropdownValue(PartnerFunction, CST_Routing.PartnerFunctionDropdown);
			javaClickByLocator(Accounts.saveBtn);
			Thread.sleep(1000);
			isDisplayed(CST_Routing.Erroemsg, "Error Message");
			Thread.sleep(1000);
			Message=GetText(CST_Routing.Erroemsg);
			if(Message.equalsIgnoreCase("You cannot have a value populated for both "
					+ "CST Problem Reported Code and EST Problem Reported Code. Choose only one."))
			{
				loggerPass("You cannot have a value populated for both CST Problem Reported Code and "
						+ "EST Problem Reported Code. Choose only one. Should be displayed",Message+" Is Displayed", "yes");
			}else {
				loggerFail("You cannot have a value populated for both CST Problem Reported Code and "
						+ "EST Problem Reported Code. Choose only one. Should be displayed",Message+" Is Displayed", true);
			}
			List<WebElement> close = driver.findElements(CST_Routing.CloseCSTRouting);
			for (WebElement closetab : close)
			{   javaClickByWebElement(closetab);Thread.sleep(1000);
				if(driver.findElements(CST_Routing.discardbtn).size()>0)
				{driver.findElement(CST_Routing.discardbtn).click();}	
			}
			
			//Email Id Routing
			System.out.println("== Email Id Routing ==");
			ValidateESTProblemOn_EmailIDRouting(mapValue);
			select_dropdownValue(CST_ProblemReported, CST_Routing.CSTProblemDropdown);
			Thread.sleep(1000);
			select_dropdownValue(EST_ProblemReported, CST_Routing.ESTProblemDropdown);
			select_dropdownValue(Constants.bottler, CST_Routing.SalesOrganizationDropdown);
			javaClickByLocator(Accounts.saveBtn);
			Thread.sleep(1000);
			isDisplayed(CST_Routing.Erroemsg, "Error Message");
			Thread.sleep(1000);
			Message=GetText(CST_Routing.Erroemsg);
			if(Message.equalsIgnoreCase("You cannot have a value populated for both "
					+ "CST Problem Reported Code and EST Problem Reported Code. "
					+ "Choose only one."))
			{
				loggerPass("You cannot have a value populated for both CST Problem Reported Code and "
						+ "EST Problem Reported Code. Choose only one. Should be displayed",Message+" Is Displayed", "yes");
			}else {
				loggerFail("You cannot have a value populated for both CST Problem Reported Code and "
						+ "EST Problem Reported Code. Choose only one. Should be displayed",Message+" Is Displayed", true);
			}
			List<WebElement> close1 = driver.findElements(CST_Routing.CloseCSTRouting);
			for (WebElement closetab : close1)
			{   javaClickByWebElement(closetab);Thread.sleep(1000);
				if(driver.findElements(CST_Routing.discardbtn).size()>0)
				{driver.findElement(CST_Routing.discardbtn).click();}	
			}
			
			
			//Employee Routing
			System.out.println("== Employee Id Routing ==");
			ValidateESTProblemOn_EmployeeRouting(mapValue);
			scrollToElement(CST_Routing.EmployeeSearchBox);
			driver.findElement(CST_Routing.EmployeeSearchBox).sendKeys("Autooffshore.bottleradmin "+Constants.bottler+Keys.ENTER);
			Thread.sleep(500);
			driver.findElement(CST_Routing.Firstresult).click();
			select_dropdownValue(CST_ProblemReported, CST_Routing.CSTProblemDropdown);
			Thread.sleep(1000);
			select_dropdownValue(EST_ProblemReported, CST_Routing.ESTProblemDropdown);
			select_dropdownValue(Constants.bottler, CST_Routing.SalesOrganizationDropdown);
			javaClickByLocator(Accounts.saveBtn);
			Thread.sleep(1000);
			isDisplayed(CST_Routing.Erroemsg, "Error Message");
			Thread.sleep(1000);
			Message=GetText(CST_Routing.Erroemsg);
			if(Message.equalsIgnoreCase("You cannot have a value populated for both "
					+ "CST Problem Reported Code and EST Problem Reported Code. "
					+ "Choose only one."))
			{
				loggerPass("You cannot have a value populated for both CST Problem Reported Code and "
						+ "EST Problem Reported Code. Choose only one. Should be displayed",Message+" Is Displayed","yes");
			}else {
				loggerFail("You cannot have a value populated for both CST Problem Reported Code and "
						+ "EST Problem Reported Code. Choose only one. Should be displayed",Message+" Is Displayed", true);
			}
			List<WebElement> close2 = driver.findElements(CST_Routing.CloseCSTRouting);
			for (WebElement closetab : close2)
			{   javaClickByWebElement(closetab);Thread.sleep(500);
				if(driver.findElements(CST_Routing.discardbtn).size()>0)
				{driver.findElement(CST_Routing.discardbtn).click();}	
			}
					
				
			softAssert.assertAll();
			
		}




}
