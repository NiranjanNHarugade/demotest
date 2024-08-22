package Features;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.MediaEntityBuilder;
import Pages.Accounts;
import Pages.CST_Routing;
import Utilities.CommonFunctions;
import Utilities.Constants;

public class Features_EST extends CommonFunctions {
	static String CloneCaseNumber = "";
	static String UpdateCaseNumber = "";
	// Created by and Date: Abhishek palankar on 23-May-2023
	// Description: Method to Create EST case
	public static void CreateNewESTForValidate(HashMap<String, String> CreateESTDetails) throws Throwable {
		softAssert = new SoftAssert();
		String accountName="";
		String accountNo="";
		 switch (Constants.bottler) {
			case "4100":
				accountName = "FIVE STAR FOOD SERVICE INC";
				accountNo = "0500004781";
				break;
			case "4200":
				accountName ="DOMINOS #5550";
				accountNo ="0602423591";
				break;
			case "4400":
				accountName ="DOMINOS PIZZA #7598";
				accountNo ="0501245003";
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
				accountName ="DOMINOS #5550";
				accountNo ="0602423591";
				break;
			case "4800":
				accountName ="DOMINOS #5550";
				accountNo ="0602423591";
				break;
			case "4900":
				accountName ="DOMINOS #5550";
				accountNo ="0602423591";
				break;
			case "5000":
				accountName ="DOMINOS #5550";
				accountNo ="0602423591";
				break;
			case "5200":
				accountName ="DOMINOS #5550";
				accountNo ="0602423591";
				break;

			}
		String caseNumber = "";
		/*
		 * String equipmentDescription = CreateESTDetails.get("EquipmentDescription");
		 * String equipmentTypeDetail = CreateESTDetails.get("EquipmentTypeDetail");
		 * String typeOfEquipmentEnquiry =
		 * CreateESTDetails.get("TypeOfEquipmentEnquiry"); String equipmentProblem =
		 * CreateESTDetails.get("EquipmentProblem"); String whatIseaking =
		 * CreateESTDetails.get("WhatIsLeaking"); String customerAcceptedPhoneFix =
		 * CreateESTDetails.get("CustomerAcceptedPhoneFix"); String
		 * phoneFixRejectionReason = CreateESTDetails.get("PhoneFixRejectionReason");
		 * String isCustomerConatinedWater =
		 * CreateESTDetails.get("CustomerConatinedWater"); String isWaterInTrafficArea =
		 * CreateESTDetails.get("WaterInTrafficArea");
		 */
		String inquiryType = CreateESTDetails.get("InquiryType");
		String detailDescription = CreateESTDetails.get("DetailDescription");
		Thread.sleep(2000);
			SelectAccountFromGlobalSearch(accountNo,accountName);
			
			try {
				if (driver.findElement(Accounts.createCase).isDisplayed()) {
					javaClickByLocator(Accounts.createCase);
				}
			} catch (Exception e) {
				javaClickByLocator(Accounts.moreTab);
				javaClickByLocator(Accounts.createCaseMoreTab);
			}
			scrollToElement(Accounts.decisiontrue);
			
			List<String> values = new ArrayList<String>();
			switch (Constants.bottler) {
			case "4100":
				values = value_4100;
				break;
			case "4200":
				values = value_4200;
				break;
			case "4400":
				values = value_4400;
				break;
			case "4500":
				values = value_4500;
				break;
			case "4600":
				values = value_4600;
				break;
			case "4700":
				values = value_4700;
				break;
			case "4800":
				values = value_4800;
				break;
			case "4900":
				values = value_4900;
				break;
			case "5000":
				values = value_5000;
				break;
			case "5200":
				values = value_5200;
				break;

			}
			 
			decisionTree(inquiryType, accountName, values);
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseEditpopUpTitle));
				if (driver.findElements(Accounts.caseRecordType).size()>0) {
					loggerPass("Newly created Case Record Type should be <b>'Equipment Service Ticket'</b>.","Newly created Case Record Type is <b>'Equipment Service Ticket'</b>.","No");
				}else {
					
					loggerFail("Newly created Case Record Type should be <b>'Equipment Service Ticket'</b>.","Newly created Case Record Type is not <b>'Equipment Service Ticket'</b>.",true);

				}
			
			caseNumber = driver.findElement(Accounts.caseEditpopUpTitle).getText();
			Thread.sleep(1000);
			caseNumber = caseNumber.replace("Edit ", "");
			
			scrollToElement(Accounts.detailedDescription);
			driver.findElement(Accounts.detailedDescriptionTextBox).click();
			driver.findElement(Accounts.detailedDescriptionTextBox).sendKeys(detailDescription);
			driver.findElement(Accounts.detailedDescriptionTextBox).sendKeys(Keys.TAB);
			javaClickByLocator(Accounts.saveBtn);
			Thread.sleep(3000);
			if (driver.findElements(By.xpath("//span[@class='test-id__field-value slds-form-element__static slds-grow word-break-ie11']//span[text()='"+ accountName + "']")).size() > 0
					&& driver.findElements(By.xpath("//lightning-formatted-text[text()='" + caseNumber + "']"))
							.size() > 0) {

			
				loggerPass("The Newly created Case " + caseNumber + " should be displayed.","The Newly created Case " + caseNumber + " is displayed.","No");

			} else {
				loggerFail("The Newly created Case " + caseNumber + " should be displayed.","The Newly created Case " + caseNumber + " is not displayed.",true);
			}

	}


	// Created by - Niranjan H on 15-June-2023
	// Verify that On the Code Value Mapping Layout for EST user should see a new Field “Send External Communication”.
	public void Verify_SendExternalCommunicationFieldForEST(HashMap<String, String> mapValue) throws Throwable {
		System.out.println("Verify Code Value Mapping Layout for Send External Communication Field For EST");
		softAssert = new SoftAssert();

		
			selectAppOrObjFromAppLauncher("App", Constants.contactCenterfromlauncher);
			selectObjectFromDropdown(Constants.home);
			closeMultipleTabs();
			selectAppOrObjFromAppLauncher("Object", Constants.CVMobjectfromlauncher);
			javaClickByLocator(CST_Routing.New);
			Thread.sleep(2000);
			isDisplayed(CST_Routing.Newcodevaluemapping, "CVM records");
			javaClickByLocator(CST_Routing.ESTMetaData);
			javaClickByLocator(Accounts.next);
			Thread.sleep(1000);
			isDisplayed(CST_Routing.ESTMetaDataPage, "New Code Value Mapping: EST Metadata");
			scrollToElement(CST_Routing.SendExternalCommunication);
			isDisplayed(CST_Routing.SendExternalCommunication, "Send External Communication");
			Thread.sleep(1000);

			String TypeOf_Field = driver.findElement(CST_Routing.SendExternalCommunicationfield).getAttribute("type");
			if (TypeOf_Field.equals("checkbox")) {
				loggerPass("Send External Communication Should be <b>Checkbox Field</b>",
						"Send External Communication is <b>Checkbox Field</b>", "No");

			} else {
				loggerFail("Send External Communication Should be <b>Checkbox Field</b>",
						"Send External Communication is <b>Not a Checkbox Field</b>", false);
			}

			if (driver.findElements(CST_Routing.checkbox).size() > 0) {
				loggerPass("Send External Communication Checkbox should be <b>Checked</b> by default",
						"Send External Communication Checkbox is <b>Checked</b> by default", "Yes");
			} else {
				loggerFail("Send External Communication Checkbox should be <b>Checked</b> by default",
						"Send External Communication Checkbox is <b>Unchecked</b> by default", true);
			}

		
		softAssert.assertAll();
		
	}

}
