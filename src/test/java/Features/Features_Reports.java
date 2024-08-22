package Features;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.hotkey.Keys;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import com.aventstack.extentreports.MediaEntityBuilder;
import Pages.Accounts;
import Pages.Cases;
import Pages.Reports;
import Pages.RetailStoreGroups;
import Utilities.CommonFunctions;
import Utilities.Constants;




public class Features_Reports extends CommonFunctions {

	static String accountnumber = "";
	static String accountname ="";
	static String AlertName = "";
	
//	Created By - Niranjan H on 22-05-2023
//	Description - This test  Verify that user should see alert widget and all alerts that are currently on the account
	public static void Varify_AlertsWidget(HashMap<String, String> reports) throws Throwable {
		System.out.println("Verify alert widget");
		softAssert = new SoftAssert();
		CreateReport_AndAlert(reports);
		logOut();

		 // login as agent and validate case	
		login("UserName_Agent", "Password_Agent");
		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		selectAppOrObjFromAppLauncher("object", Constants.accountfromlauncher);
		SelectAccountFromGlobalSearch(accountnumber, accountname);

		  
		scrollToElement(By.xpath("//b[contains(text(),'" + Constants.AlertWidget + "')]"));

		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//b[contains(text(),'" + Constants.AlertWidget + "')]")));
		isDisplayed(By.xpath("//b[contains(text(),'" + Constants.AlertWidget + "')]"),
				Constants.AlertWidget + " widget");

		int accountspagealert = driver.findElements(Accounts.alerton_Accounts).size();
		
		javaClickByLocator(Accounts.casesWidget);
		Thread.sleep(3000);
		javaClickByLocator(Accounts.newcase);
		javaClickByLocator(Accounts.outboundRadioBtn);
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(Accounts.alertnext));
		javaClickByLocator(Accounts.alertnext);
		Thread.sleep(3000);
		new WebDriverWait(driver, explicitWaitTimeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(Accounts.outboundPopup));
		Thread.sleep(3000);

		if (driver.findElements(Accounts.outboundPopup).size() > 0) {
			loggerPass("<b>Outbound </b>page should be displayed", "<b>Outbound </b>page is displayed", "No");
			Thread.sleep(2000);
			if (driver.findElements((Accounts.AccountNameField)).size() > 0) 
			{
			driver.findElement(Accounts.AccountNameField).sendKeys(accountname + Keys.ENTER);
			javaClickByLocator(RetailStoreGroups.firstresult);
			}
			
		}
		Thread.sleep(2000);
		javaClickByLocator(Accounts.saveBtn);
		new WebDriverWait(driver, explicitWaitTimeOut)
		.until(ExpectedConditions.visibilityOfElementLocated(Accounts.caseNumberField));

		Thread.sleep(10000);
		String caseNumber = GetText(Accounts.caseNumberValue);
		Thread.sleep(5000);

		if (driver.findElement(Accounts.caseNumberValue).getText().equalsIgnoreCase(caseNumber)) {
			loggerPass("New Case should be created <b>" + caseNumber + "</b>",
					"New Case is created <b>" + caseNumber + "</b>", "Yes");
		}

		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//b[contains(text(),'" + Constants.AlertWidget + "')])[2]")));
		isDisplayed(By.xpath("(//b[contains(text(),'" + Constants.AlertWidget + "')])[2]"),
				Constants.AlertWidget + " widget");
		int casepagealert = driver.findElements(Accounts.alerton_case).size();
		Thread.sleep(2000);
		if (accountspagealert == casepagealert) {
			loggerPass("No Of Alerts on Accounts Page And Case Page Should Be Same", "No Of Alerts on Accounts Page And Case Page Are Same", "No");
		} else {
			loggerFail("No Of Alerts on Accounts Page And Case Page Should Be Same", "No Of Alerts on Accounts Page And Case Page Are Not Same", false);
		}

		javaClickByLocator(Cases.viewall);
		Thread.sleep(2000);
		List<WebElement> alerts_prsecent = driver.findElements(Cases.alertname);
		Thread.sleep(1000);
		int count = 0;
		for(WebElement Name:alerts_prsecent)
		{if(Name.getText().equals(AlertName)) 
		 	{
			loggerPass(AlertName+" Is present On Outbound case page", Name.getText()+" present On Outbound case page", "No");
			break;
		 	} 
			count++;
			if(count >= alerts_prsecent.size())
			{ loggerFail(AlertName+" Is present On Outbound case page", AlertName+" Not present On Outbound case page", false);}
		}	
		softAssert.assertAll();
		
	}
	public static void CreateReport_AndAlert(HashMap<String, String> reports) throws Exception {

		String reportName = reports.get("Reportname");
		String ordermethod = reports.get("Filter");
		String value = reports.get("value");
		String reportUniqueName = "New_report24";
		String RSGname = reports.get("RSGName")+"_"+generateRandomInteger();
	    AlertName = reports.get("AlertName")+"_"+generateRandomInteger();
		String searchapp = reports.get("searchapp");
		String Target_Group_Member = reports.get("TargetGroupMember");
		String Target_Group_Size = reports.get("TargetGroupSize");
		String startdate = dateToday();
		String enddate = increaseOrDecreaseDate(startdate, "day", 2);
		
		selectAppOrObjFromAppLauncher("app", Constants.contactCenterfromlauncher);
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", Constants.objectNameReports);
		javaClickByLocator(Reports.AllReports);
		Thread.sleep(2000);
		fillFieldByLocator(Reports.SearchReports, reportName+Keys.ENTER);
		
		
		if (!(driver.findElements(By.xpath("//a[@title='"+reportName+"']")).size()>0)) 
		{
		loggerInfo(reportName +" Is not present creating a new report", "No");
		javaClickByLocator(Reports.newReports);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Report Builder']")));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath("//a[text()='All']")));
		javaClickByLocator(By.xpath("//a[text()='All']"));
		javaClickByLocator(Reports.reportTypeAsAccounts);
		javaClickByLocator(Reports.continueBtn);
		isDisplayed(Reports.reportpage, "Reports page layout");
		
		while (true) {
			if (driver.findElements(Reports.closecolmn).size() > 0) {
				List<WebElement> closebtn = driver.findElements(Reports.closecolmn);
				for (WebElement remove : closebtn) {
					javaClickByWebElement(remove);
				}
				Thread.sleep(1000);
			} else {break;}
		}
		
		driver.findElement(Reports.outlineTab).click();
		Thread.sleep(2000);
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(Reports.addColumnTxtBox));
		
		for (String col : Constants.colunms) {
			fillFieldByLocator(Reports.addColumnTxtBox, col);
			javaClickByLocator(By.xpath("//span[@title='" + col + "']"));
		}

		driver.findElement(Reports.filtersTab).click();
		javaClickByLocator(Reports.showMeFilterBtn);
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(Reports.FilterDropDown));
		javaClickByLocator(Reports.FilterDropDown);
		javaClickByLocator(Reports.allAccountsOption);
		javaClickByLocator(Reports.applyBtn);

		javaClickByLocator(Reports.createddatefilter);
		driver.findElement(Reports.datefilter).click();
		javaClickByLocator(Reports.datealltimes);
		javaClickByLocator(Reports.applyBtn);

		driver.findElement(Reports.addFilterSearchTxtBox).click();
		fillFieldByLocator(Reports.addFilterSearchTxtBox, ordermethod);
		Thread.sleep(2000);
		driver.findElement(Reports.preferredOrderingMethodFilter).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(Reports.FilterDropDown));
		driver.findElement(Reports.value).click();
		driver.findElement(Reports.valuefeild).sendKeys(value);
		Thread.sleep(1000);
		javaClickByLocator(Reports.callcenteroption);
		javaClickByLocator(Reports.applyBtn);
		driver.findElement(Reports.save).click();

		driver.findElement(Reports.reportNameTxtField).sendKeys(Keys.BACKSPACE);
		driver.findElement(Reports.reportNameTxtField).sendKeys(reportName);
		driver.findElement(Reports.reportNameTxtField).sendKeys(Keys.TAB + Keys.TAB);
		driver.findElement(Reports.uniqueReportNameTxtField).sendKeys(Keys.BACKSPACE);
		Thread.sleep(1000);
		driver.findElement(Reports.reportDescriptionTxtField).sendKeys("This report is used for alert widget. Do not edit.");
		reportUniqueName = driver.findElement(Reports.uniqueReportNameTxtField).getAttribute("value");

		isDisplayed(Reports.uniqueReportNameTxtField, "Unique Name");
		driver.findElement(Reports.selectFolderBtn).click();
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		new WebDriverWait(driver, Duration.ofSeconds(
				5)).until(ExpectedConditions.visibilityOfElementLocated(Reports.searchfolder));
		fillFieldByLocator(Reports.searchfolder, Utilities.Constants.bottler + " Standard TG Reports");
		javaClickByLocator(Reports.selectfolder);
		javaClickByLocator(Reports.confirm);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Report Builder']")));
		javaClickByLocator(Reports.save1);
		}else {
			loggerInfo("<b>"+reportName+"</b> Report is selected", "yes");
			javaClickByLocator(By.xpath("//a[@title='"+reportName+"']"));
			Thread.sleep(3000);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Report Viewer']")));
			javaClickByLocator(Reports.EditReports);
		}
		
			
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Report Builder']")));
		javaClickByLocator(Reports.Morebtn);
		javaClickByLocator(Reports.properties);
		reportUniqueName = driver.findElement(Reports.uniqueReportNameTxtField).getAttribute("value");
		System.out.println(reportUniqueName);
		javaClickByLocator(Reports.save);
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		
		// Create RSG Group and Alert
		closeMultipleTabs();
		selectAppOrObjFromAppLauncher("object", searchapp);
		new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(RetailStoreGroups.RSGpage));
		isDisplayed(RetailStoreGroups.RSGpage, "Retail store group");
		Thread.sleep(1000);
		try {
			String title = "Close Recently Viewed | Retail Store Groups";
			List<WebElement> close = driver.findElements(RetailStoreGroups.closeRSG);
			for (WebElement closetab : close) {
				if (!closetab.getText().contains(title))
					javaClickByWebElement(closetab);
			}
		} catch (Exception e) {
		}
		javaClickByLocator(RetailStoreGroups.newbtn);
		isDisplayed(RetailStoreGroups.newrsg, "New Retail store group page");
		javaClickByLocator(RetailStoreGroups.Alertgroup);
		javaClickByLocator(RetailStoreGroups.next);
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.RSGname));
		fillFieldByLocator(RetailStoreGroups.RSGname, RSGname);
		fillFieldByLocator(RetailStoreGroups.uniquename, reportUniqueName);
		Thread.sleep(2000);
		driver.findElement(RetailStoreGroups.rsgDescription).sendKeys("Test");
		javaClickByLocator(RetailStoreGroups.save);
		CommonFunctions.verifyToastMessage("Retail Store Group " + RSGname + " was created");
		Thread.sleep(2000);
		try {
			String title = ""+RSGname+" | Retail Store Groups";
			List<WebElement> close = driver.findElements(RetailStoreGroups.closeRSG);
			for (WebElement closetab : close) {
				if (!closetab.getText().contains(title))
					javaClickByWebElement(closetab);
			}
		} catch (Exception e) {
		}
		driver.navigate().to(driver.getCurrentUrl());
		new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.elementToBeClickable(RetailStoreGroups.Alert));
		javaClickByLocator(RetailStoreGroups.RefreshGroupMembers);
		int i = 1;
		while (i <= 15) {
			Thread.sleep(10000);
			driver.navigate().refresh();
			if (!driver.findElement(RetailStoreGroups.targetGroupSize)
					.getText().isEmpty())
			{
					if(driver.findElement(RetailStoreGroups.targetGroupSize).getText()=="0")
					{
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
//		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(RetailStoreGroups.targetGropMumber));
		try {
			accountname = GetText(RetailStoreGroups.TSGaccountname);
			accountnumber = GetText(RetailStoreGroups.TSGaccountnumber);
		} catch (Exception e) {
			driver.navigate().to(driver.getCurrentUrl());
			accountname = GetText(RetailStoreGroups.TSGaccountname);
			accountnumber = GetText(RetailStoreGroups.TSGaccountnumber);
		}
		isDisplayed(RetailStoreGroups.targetGropMumber, Target_Group_Member);
		isDisplayed(RetailStoreGroups.targetGroupSize, Target_Group_Size);

		
		
		
		// Create Alert
		Thread.sleep(2000);
		javaClickByLocator(RetailStoreGroups.Alert);
		Thread.sleep(1000);
		javaClickByLocator(RetailStoreGroups.newAlert);
		Thread.sleep(1000);
		fillFieldByLocator(RetailStoreGroups.Alertname, AlertName);
		Thread.sleep(1000);
		scrollToElement(RetailStoreGroups.descfield);

		Thread.sleep(1000);
		driver.findElement(RetailStoreGroups.descfield).click();
		Thread.sleep(2000);
		driver.findElement(RetailStoreGroups.textField).sendKeys("Testing");

		Thread.sleep(1000);
		scrollToElement(RetailStoreGroups.enddate);
		javaClickByLocator(RetailStoreGroups.selectpriority);
		javaClickByLocator(RetailStoreGroups.setvalue);
		driver.findElement(RetailStoreGroups.startdate).sendKeys(startdate);
		driver.findElement(RetailStoreGroups.enddate).sendKeys(enddate);
		driver.findElement(RetailStoreGroups.selectpriority).click();
		driver.findElement(RetailStoreGroups.selectpriority).click();
		try{javaClickByLocator(RetailStoreGroups.save);
		if(driver.findElement(Constants.foastMessageClassName).isDisplayed()) 
		{assertTrue(true);
		verifyToastMessage("Alert was saved");}
		}
		catch(Exception e){javaClickByLocator(RetailStoreGroups.save);
		verifyToastMessage("Alert was saved");}}



}
