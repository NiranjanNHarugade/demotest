package ProjectDevOps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Features.Features_Account;
import Features.Features_Case;
import Utilities.BaseTest;
import Utilities.CommonFunctions;
import Utilities.Constants;
import Utilities.DataProvider;

public class Accounts extends BaseTest implements Constants {
	static boolean flag;
	
	
	
	@BeforeSuite(alwaysRun = true)
	public void ICEPLaunchBrowser() throws InterruptedException, IOException {
		extent.attachReporter(spark);
		deleteScreenShots();
		initialization();
	}
	
	@BeforeClass
	public void LaunchBrowser() throws IOException, InterruptedException {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName1 = cap.getBrowserName();
		String browserVersion = (String) cap.getCapability("browserVersion");
		String OS = System.getProperty("os.name").toLowerCase();
		String javaVersion = System.getProperty("java.version");
		String userName = System.getProperty("user.name");
		spark.config().setReportName("CONA DevOps Automation Report");
		spark.config().setDocumentTitle("CONA DevOps");
		extent.setSystemInfo("Suite Name", "Contact Center DevOps Part A");
		extent.setSystemInfo("Environment Name", Constants.envName);
		extent.setSystemInfo("Bottler ID", Constants.bottler + "-" + Constants.bottlerName);
		extent.setSystemInfo("Browser Name", browserName1);
		extent.setSystemInfo("Browser Version", browserVersion);
		extent.setSystemInfo("OS Name", OS);
		extent.setSystemInfo("User Name", userName);
		extent.setSystemInfo("Java-Version", javaVersion);
		flag = CommonFunctions.readMetaData("Accounts", "ContactCenter","DevOps CC Part A L11");
		
	}

	//	Created By - Niranjan H on 18-05-2023
	// 	User Story - 969979  , TC-(241009,241010,241011,241012)
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 1, dataProviderClass = DataProvider.class, dataProvider = "readData", 
	description="This test is to verify that user can see instructions texts of the note without having to click into it")
	public void CustomerInstruction(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
			logger = extent.createTest("1.1 - Account - Customer instructions").assignCategory("Regression");
			 try { 
				login("UserName_Agent","Password_Agent");
				Features_Account account=new Features_Account();
				account.Customer_Instruction(accounts);
			} 
			 catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}

	
	//	Created By - Niranjan H on 18-05-2023
	// 	User Story - 1374257  , TC - 312121 , 312123 , 312125 , 312126 ,312128 Enhancement - 3158482
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData", 
	description="Account page layout in the ICEP to display the 'Active Related Contacts' list")
	public void Validate_ActiveRelatedContacts(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
			logger = extent.createTest("1.2 - Account - Active Related Contacts' list").assignCategory("Regression");
			 try { 
				login("UserName_Agent","Password_Agent");
				Features_Account account=new Features_Account();
				account.Verify_ActiveRelatedContacts(accounts);
			} 
			 catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}
	
	
	//	Created By - Niranjan H on 18-05-2023
	// 	User Story - 1374258  , TC - 312274 ,312275
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 3, dataProviderClass = DataProvider.class, dataProvider = "readData", 
	description="Contact Priority should be created in the AccountContactRelation layout")
	public void Validate_ContactPriority(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
			logger = extent.createTest("1.3 - Account - Contact Priority").assignCategory("Regression");
			 try { 
				login("UserName_Agent","Password_Agent");
				Features_Account account=new Features_Account();
				account.Verify_ContactPriority(accounts);
			} 
			 catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}
	
	
	//	Created By - Niranjan H on 20-05-2023
	// 	User Story - 1374258  , TC - 315439
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 4, dataProviderClass = DataProvider.class, dataProvider = "readData", 
	description="Two-way synchronization between the fields Contact.ContactPriority and AccountContactRelation.ContactPriority ")
	public void Validate_ContactPriority_Sync(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
			logger = extent.createTest("1.4 - Account - Contact Priority Synchronization").assignCategory("Regression");
			 try { 
				login("UserName_Agent","Password_Agent");
				Features_Account account=new Features_Account();
				account.Verify_ContactPriority_Sync(accounts);
			} 
			 catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}
	
    // Created By Abhishek Palankar on 19-12-2023
	// US - 1374277 and TC - 312277
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 5, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that Case Creation From Contact from decision tree-  Default the Account, if the Contact has only one associated account.")
	public void contactOnlyOneAssociatedAccounts(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
			logger = extent.createTest("1.5 - Account - Contact should be associated to only one account").assignCategory("Regression");
			try {
				login("UserName_Agent", "Password_Agent");
				Features_Account account = new Features_Account();
				account.contactAssociatedAccounts(accounts);
			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}
	
	    // Created By Niranjan H on 19-12-2023
		// US - 1374277 and TC - 312278
		@Parameters({ "AccountRegression.json" })
		@Test(priority = 6, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that Case Creation From Contact from decision tree-if the Contact has Multiple associated account.")
		public void contactOnlyMultipleAssociatedAccounts(HashMap<String, String> accounts) throws Throwable {
			if (flag) {
				logger = extent.createTest("1.6 - Account - Contact should be associated to Multiple account").assignCategory("Regression");
				try {
					login("UserName_Agent", "Password_Agent");
					Features_Account account = new Features_Account();
					account.contactAssociated_MultipleAccounts(accounts);
				} catch (Exception e) {
					CommonFunctions.CaptureFailure(e);
				} finally {
					logOut();
				}
			}
		}

		
	    // Created By Niranjan H on 19-12-2023
		// US - 2924903 and TC - 333712,333713
		@Parameters({ "AccountRegression.json" })
		@Test(priority = 7, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "the Account belonging to the other Bottler a validation should be displayed to restrict the user from making any changes on account.")
		public void ValdatationForOtherBottlerAccounts(HashMap<String, String> accounts) throws Throwable {
			if (flag) {
				logger = extent.createTest("1.7 - Account - Validation for Other bottlers account").assignCategory("Regression");
				try {
					login("UserName_Agent", "Password_Agent");
					Features_Account account = new Features_Account();
					account.OtherBottlersAccount(accounts);
				} catch (Exception e) {
					CommonFunctions.CaptureFailure(e);
				} finally {
					logOut();
				}
			}
		}
	
		
	    // Created By Niranjan H on 08-07-24
		// US - 1716187 and TC - 343125, 343128,343130
		@Parameters({ "AccountRegression.json" })
		@Test(priority = 8, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "New Business Contact Button on Accounts Details Page")
		public void ValidateNewBusinessContact(HashMap<String, String> accounts) throws Throwable {
			if (flag) {
				logger = extent.createTest("1.8 - Account - New Business Contact Button on Accounts Details Page").assignCategory("Regression");
				try {	
					login("UserName_Agent", "Password_Agent");
					Features_Account account = new Features_Account();
					account.VerifyNewBusinessContact(accounts);
				} catch (Exception e) {
					CommonFunctions.CaptureFailure(e);
				} finally {
					logOut();
				}  
			}
		}		
		
		
		
		
		
		
		
		
		
		
		
		
//		// Created by - Niranjan Harugade
//		@Parameters({ "Case.json" })
//		@Test(priority = 12, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that we should have a Outbound case Disposition on outbound case page layout")
//		public void DecisionTree(HashMap<String, String> Cases) throws Throwable {
//		
//			if (flag ) {
//				logger = extent.createTest("Decision tree")
//						.assignCategory("Regression");
//				try {
//					login("UserName_Agent", "Password_Agent");
//					Features_Case.decisionTree();
//				
//				
//				
//					} catch (Exception e) {
//						CommonFunctions.CaptureFailure(e);
//					} finally {
//						logOut();
//					}
//
//			}
//		}

//================================== Don't execute ================================================================

//	// Created By - Niranjan H on 18-05-2023
//	// User Story - 1374258  , TC - 315438
//	@Parameters({ "AccountRegression.json" })
//	@Test(priority = 4, dataProviderClass = DataProvider.class, dataProvider = "readData", 
//	description="Primary, Secondary and Tertiary should populated on outbound case created from Call list")
//	public void Validate_ContactPriorityOnOutboundcase(HashMap<String, String> accounts) throws Throwable {
//		if (flag) {
//			logger = extent.createTest("1.4 - Account - Contact Priority").assignCategory("Regression");
//			 try { 
//				login("UserName_OffshoreBottler","Password_OffshoreBottler");
//				Features_Account account=new Features_Account();
//				account.Verify_ContactPriorityOnOutbound(accounts);
//			} 
//			 catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
//			 finally {logOut();}
//		}
//	}
	
	

}
