package ProjectDevOps;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Features.Features_Case;
import Features.Features_Reports;
import Utilities.BaseTest;
import Utilities.CommonFunctions;
import Utilities.Constants;
import Utilities.DataProvider;

public class Cases extends BaseTest implements Constants {
	String caseNumber = "";
	static boolean flag;

	@BeforeClass
	public void LaunchBrowser() throws IOException, InterruptedException {
		extent.attachReporter(spark);
		deleteScreenShots();
		initialization();
		flag = CommonFunctions.readMetaData("Cases", "ContactCenter", "DevOps CC Part A L11");

	}

	// Created by - Abhishek Palankar on 19-May-2023
	// US - 975748: TC - 241013
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 1, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that For the OB case, User should see an order widget matching the one that is on the account page with the all statuses.")
	public void ValidateOrderWidget(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.1 - Account&Case - Validate Order widget on account & OB case page")
					.assignCategory("Regression");
			try { 
				  login("UserName_Agent", "Password_Agent");
				  Features_Case.verifyOrderWidgetsInAccountPage(accounts);
			    
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}

		}
	}


	// Created by - Abhishek Palankar on 22-May-2023
	// US - 975748: TC - 241014
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should see a task widget (from the account) and the status of the task should be open. It should be separate from the current tasks that are on the case.")
	public void ValidateTaskWidget(HashMap<String, String> accounts) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.2 - Account&Case - Validate task widget in account & OB case page")
					.assignCategory("Regression");
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_Case.verifyTaskWidgetsInAccountPage();
				
		    }catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
			
		}
	}

	
	// Created by - Abhishek Palankar on 23-May-2023
	// US - 975748: TC - 241016
	@Parameters({ "CreateEST.json" })
	@Test(priority = 3, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that following testcases should apply only  to OB case record type.")
	public void ValidateOrderTaskAlertWidgetOnlyInCSTNotInEST(HashMap<String, String> mapValue) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.3 - Account&Case - Validate order,task and alert widgets only in OB")
					.assignCategory("Regression");
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_Case.verifyOrderTaskAlertsWidgetsInOB(mapValue);
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}

	
	// Created by - Niranjan H on 22-May-2023
	// US - 975748: TC - 241015
	@Parameters({ "Verify_AlertWidget.json" })
	@Test(priority = 4, dataProviderClass = DataProvider.class, dataProvider = "readData",description="Verify that user should see a lert widget and all alerts that are currently on the account")
	public void ValidateAlertWidget(HashMap<String, String> Reports) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.4 - Account&Case - Verify alert widget in acoount & OB case page")
					.assignCategory("Regression");
			try {
				
				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_Reports.Varify_AlertsWidget(Reports);
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}

	
	// Created by - Abhishek Palankar on 14-June-2023
	// US - 943556: TC - 248517 and 248519
	@Parameters({ "CreateEST.json" })
	@Test(priority = 5, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that for all personas Contact Center should need to use Chatter .")
	public void ValidateChatterForCST(HashMap<String, String> mapValue) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.5 - Case - Validate Chatter for CST")
					.assignCategory("Regression");
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_Case.validateChatterForCST();
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();} 
		}
	}
	
	
	// Created by - Abhishek Palankar on 14-June-2023
	// US - 943556: TC - 248521
	@Parameters({ "CreateEST.json" })
	@Test(priority = 6, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that Chatter is enabled on outbound case record type")
	public void ValidateChatterForOB(HashMap<String, String> mapValue) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.6 - Case - Validate Chatter for OB")
					.assignCategory("Regression");	
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_Case.validateChatterForOB();
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}	 
		}
	}

	
	// Created by - Niranjan H on 13-June-2023
	// US-943555 : TC-248830
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 7, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "View Marketing Attributes and other Customer attributes on the Outbound Case Layout From Account Details Page.")
	public void Validate_AccountsAttributesOnCasePage(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.7 - Account & Case - Verify Marketing Attributes and Customer attributes on the Outbound Case page Layout")
					.assignCategory("Regression");
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_Case.Verify_AccountaAttributesOn_OBCase(Cases);
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
			
		}
	}


	// Created by - Niranjan H on 12-Jul-2023
	// US-943557 : TC-248543
	@Parameters({ "AccountRegression.json" })
	@Test(priority = 8, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that the “Other Contact Type” picklist is updated to include Bottler, Consumer and NAOU")
	public void Validate_OtherContactPicklist(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
					logger = extent.createTest("2.8 - Case -Verify that the “Other Contact Type” picklist is updated to include Bottler, Consumer and NAOU")
					.assignCategory("Regression");
			try {
					login("UserName_OffshoreBottler", "Password_OffshoreBottler");
					Features_Case FC = new Features_Case();
					FC.Verify_OtherContactPicklist();
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
			
		}
	}
	

	    // Created by - Niranjan H on 30-11-23
		// US-1374249 : TC-299059
		@Parameters({"Case.json" })
		@Test(priority = 9, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Call List Name field in the header of the case next to Case Origin")
		public void Validate_CallList_Name (HashMap<String, String> Cases) throws Throwable {
			if (flag) {
						logger = extent.createTest("2.9 - Case - Call list name field in the header of the Case")
						.assignCategory("Regression");
				try {
						login("UserName_OffshoreBottler", "Password_OffshoreBottler");
						Features_Case.Verify_CallList_Name(Cases);
					
				}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
				 finally {logOut();}
				
			}
		}

		//Created by - Niranjan H on 06-12-23
		//US-1374231 : TC-309023 , 309024 
		@Parameters({"Case.json" })
		@Test(priority = 10, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "visibility of the Message Field on the case related list for account records")
		public void Validate_MessageField(HashMap<String, String> Cases) throws Throwable {
			if (flag) {
						logger = extent.createTest("2.10 - Case - Visibility of the Message Field on the case related list for account records")
						.assignCategory("Regression");
				try {
						login("UserName_Agent", "Password_Agent");
						Features_Case.Verify_MessageField(Cases);
					
				}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
				 finally {logOut();}
				
			}
		}
		
		//Created by - Abhishek Palankar on 02-01-24
		//US-1374223 : TC - 299057, 299058
		@Parameters({ "Case.json" })
		@Test(priority = 11, dataProviderClass = DataProvider.class, dataProvider ="readData", description = "This test is to verify that we should have a field to capture the Campaign Type, and Promt options")
		public void Validate_UCCXPromptCampaignTypeAndUCCXPromptOptions(HashMap<String, String> Cases) throws Throwable {
			
			if (flag && Constants.bottler.equals("4600")) {
				logger = extent.createTest("2.11 - Case - Validate UCCX Campaign type and Prompt options in RSG")
						.assignCategory("Regression");
				try {
					login("UserName_OffshoreBottler", "Password_OffshoreBottler");
					 Features_Case.ValidateCampaignTypeOptions();

				} catch (Exception e) {
					CommonFunctions.CaptureFailure(e);
				} finally {
					logOut();
				}

			}
			
		}
		

		// Created by - Niranjan Harugade on 13-02-2024
		// US-1374229 : TC - 309004,309012,309013,309014,309015,309016,309017,309018,309019,309020,309021,309022
		@Parameters({ "Case.json" })
		@Test(priority = 12, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that we should have a Outbound case Disposition on outbound case page layout")
		public void Validate_OutboundCallDisposition_Matrix(HashMap<String, String> Cases) throws Throwable {
		
			if (flag && Constants.bottler.equals("4600")) {
				logger = extent.createTest("2.12 - Case - Validate Outbound Disposition Matrix")
						.assignCategory("Regression");
				try {
					login("UserName_OffshoreBottler", "Password_OffshoreBottler");
					Features_Case.Varify_OutboundCallDisposition_Matrix();

				} catch (Exception e) {
					CommonFunctions.CaptureFailure(e);
				} finally {
					logOut();
				}

			}
		}

		
		// Created by - Niranjan Harugade on 25-07-2024
		// US- 2040243  : TC - 342002 ,342005,342006
		@Parameters({ "Case.json" })
		@Test(priority = 13, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test verify OB Case Layout Changes")
		public void Validate_OutboundPageLayout(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
			logger = extent.createTest("2.13 - Case - OB Case Layout Changes")
					.assignCategory("Regression");
		try {
			login("UserName_Agent", "Password_Agent");
			Features_Case.verifyOutboundPageLayout(Cases);	

			} catch (Exception e) {
			 CommonFunctions.CaptureFailure(e);
			} finally {logOut();}

					}
				}
		
		
		
		
		
		
		
		
		
		
		
		// Created by - Niranjan H on 02-08-2024
		// US - 2040227: TC - 342014, 342015, 342016, 342017, 342018
		@Parameters({ "Case.json" })
		//@Test(priority = 14, dataProviderClass = DataProvider.class, dataProvider = "readData", description = " Planning Calendar On OB Case Header")
		public void VerifyPlanningCalendarButtonInOBPage(HashMap<String, String> mapValue) throws Throwable {
			if (flag) {
						logger = extent.createTest("2.14 - Case - Planning Calendar On OB Case Header")
						.assignCategory("Regression");
				try {
					
					login("UserName_Agent", "Password_Agent");
					Features_Case.verifyPlanningCalendarButtonInOBPage(mapValue);
					
				}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
				 finally {logOut();}
			}
		}
		
		
		
//================================== Don't execute ================================================================

//		//Created by - Niranjan H on 22-12-23
//		//US-1374211 : TC - 315586, 315587
//		@Parameters({"Case.json" })
//		@Test(priority = 11, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Ability to see information about declined call in IVR")
//		public void Validate_IVROptionsCaseStatus(HashMap<String, String> Cases) throws Throwable {
//			if (flag) {
//				logger = extent.createTest("2.11 - Case - Ability to see information about declined call in IVR")
//						.assignCategory("Regression");
//				try {
//					login("UserName_OffshoreBottler", "Password_OffshoreBottler");
//					Features_Case FC = new Features_Case();
//					FC.Verify_IVROptionsCaseStatus(Cases);
//				} catch (Exception e) {
//					CommonFunctions.CaptureFailure(e);
//				} finally {
//					logOut();
//				}
//			}
//				
//		}

       
}
