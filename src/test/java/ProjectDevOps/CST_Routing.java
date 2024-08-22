package ProjectDevOps;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Features.Features_CSTRouting;
import Features.Features_Case;
import Features.Features_EST;
import Utilities.BaseTest;
import Utilities.CommonFunctions;
import Utilities.Constants;
import Utilities.DataProvider;

public class CST_Routing extends BaseTest implements Constants {
	static boolean flag;
	
	
	@BeforeClass
	public void LaunchBrowser() throws IOException, InterruptedException {
		extent.attachReporter(spark);
		deleteScreenShots();
		initialization();
		flag = CommonFunctions.readMetaData("CST_Routing", "ContactCenter", "DevOps CC Part A L11");

	}

	// Created by - Abhishek Palankar on 31-May-2023
	// US - 981662: TC - 242007, 242008, 242009 and 243562
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 1, dataProviderClass = DataProvider.class, dataProvider = "readData", 
	description="This test is to verify that if there is a matching Spring related Account Partner, it will assign the case to the one that matches")
	public void validateCSTRoutingForSpring(HashMap<String, String> MapValue) throws Throwable {
		if (flag) {
			logger = extent.createTest("4.1 - Case - Verify CST Routing for Spring and validate Assign To and Email")
					.assignCategory("Regression");

			 try { 
				 
				  login("UserName_Agent", "Password_Agent");
				  Features_CSTRouting.cstRoutingForSpring(MapValue);
				  
			 }catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			  finally {logOut();}
		}
	}	

	
	
	// Created by - Niranjan H on 22-June-2023
	// US-935032 : TC-248578
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that When “Assign to Manager” is checked, using Partner Function Routing type CSTs will be assigned to the Manager")
	public void ValidatePartnerFunction_AssignedToManager(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
			logger = extent.createTest("4.2 - Case - Verify that When “Assign to Manager” is checked, using Partner Function Routing type CSTs will be assigned to the Manager")
					.assignCategory("Regression");
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateAssignToManagerON_Case(Cases);

			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}

	
	// Created by - Niranjan H on 30-June-2023
	// US-935032 : TC-248580
	@Parameters({ "CSTRouting.json" })
	@Test (priority = 3, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that if partner function manager is blank keep assign to blank,Trigger the CST routing error The Partner Function's Manager is not maintained.Please reassign or notify Contact Center Leadership")
	public void VirifyAssignToManagerON_Case_PartnerFunctionsManagerIsnotPresent(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
					logger = extent.createTest("4.3 - Case - Verify that if partner function manager is blank keep assign to blank,Trigger the CST routing error")
					.assignCategory("Regression");
					
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateAssignToManagerON_Case_PartnerFunctionsManagerIsnotPresent(Cases);
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
	    }
	}
	
	
	// Created by - Niranjan H on 30-June-2023
	// US-935032 : TC-248583
	@Parameters({ "CSTRouting.json" })
	@Test (priority = 4, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that  When “Assign to Manager” is left unchecked, the CST will route to the selected Partner Function")
	public void VerifyAssignedToManagerisUncheked (HashMap<String, String> Cases) throws Throwable {
		if (flag) {
					logger = extent.createTest("4.4 - Case - Verify that when “Assign to Manager” is left unchecked, the CST will route to the selected Partner Function")
					.assignCategory("Regression");
			try {
				
				login("UserName_Agent", "Password_Agent");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateAssignedToManagerisUncheked(Cases);
				
			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
			 finally {logOut();}
		}
	}
	
	
//================================== Don't execute ================================================================

//	// Created by - Abhishek Palankar on 05-June-2023
//	// US - 981662: TC - 242010
//	@Parameters({ "CSTRouting.json" })
//	@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData", 
//	description="This test is to verify that if there is a matching PACE AND Spring responsible on the CST Routing Record and if the Account Partner are the same , it will assign the Account Partner to Pace")
//	public void validateCSTRoutingForSpringAndPace(HashMap<String, String> MapValue) throws Throwable {
//		if (flag) {
//			logger = extent.createTest("5.2 - Case - Verify CST Routing for matching PACE & Spring responsible then it will assign the Account Partner to Pace")
//					.assignCategory("Regression");
//			
//			try {
//				
//				login("UserName_Agent", "Password_Agent");
//				Features_CSTRouting.cstRoutingForPACEAndSpring(MapValue);
//				
//			}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
//			finally {logOut();}
//		}
//	}	
	
	
	
// Created by - Niranjan H  on 31-May-2023
// US - 981662: TC - 242006
//@Parameters({ "CSTRouting.json" })
//@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData",description="This test is to verify that If there is a matching PACE related Account Partner, it will assign the case to the one that matches")
//public void CSTRoutingTo_PACE_Spring(HashMap<String, String> MapValue) throws Throwable {
//	if (flag) {
//				logger = extent.createTest("5.2 - Case - verify that if there is a matching PACE related Account Partner assign the case that one matches")
//						.assignCategory("Regression");
//		
//		try { 
//			
//			login("UserName_Agent","Password_Agent");
//			Features_CSTRouting FC= new Features_CSTRouting();
//			FC.PaceRouting_validation(MapValue);
//			
//		}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
//		 finally {logOut();}
//	}
//}	
	
	
}
