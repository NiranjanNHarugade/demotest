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
import Features.Features_EST;
import Utilities.BaseTest;
import Utilities.CommonFunctions;
import Utilities.Constants;
import Utilities.DataProvider;

public class CSTRoutingValidation extends BaseTest implements Constants {
	static boolean flag;

	@BeforeClass
	public void LaunchBrowser() throws IOException, InterruptedException {
		extent.attachReporter(spark);
		deleteScreenShots();
		initialization();
		flag = CommonFunctions.readMetaData("CSTRoutingValidation", "ContactCenter", "DevOps CC Part A L11");

	}

	// Created by - Abhishek Palankar on 15-Jun-2023
	// US-935032 : TC-248568
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 1, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that user should be able to see the CC Business Segment,OU Region, and MU/Division fields on on the  Email ID routing")
	public void Validate_FieldsONEmailIDRouting(HashMap<String, String> CST) throws Throwable {
		if (flag) {
			logger = extent.createTest("5.1 - Case -Verify fields on the Email ID routing")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.VarifyFieldsOnEmailIdRouting(CST);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Niranjan H on 15-Jun-2023
// US-943557 : TC-248528 , 248531 , 248532
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that On the Code Value Mapping Layout for CST user should see a new Field “Send External Communication”.")
	public void ValidatepresenceOF_SendExternalCommunicationOnCST(HashMap<String, String> CVM) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.2 - CST & EST - Verify that On Code Value Mapping Layout for CST user should see a new Field “Send External Communication”.")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.Verify_SendExternalCommunicationFieldForCST(CVM);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}

		}

	}

// Created by - Niranjan H on 15-Jun-2023
// US-943557 : TC-248530
	@Parameters({ "CreateEST.json" })
	@Test(priority = 3, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that On the Code Value Mapping Layout for EST user should see a new Field “Send External Communication”.")
	public void ValidatepresenceOF_SendExternalCommunicationOnEST(HashMap<String, String> CVM) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.3 - CST & EST - Verify that On Code Value Mapping Layout for EST user should see a new Field “Send External Communication”.")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_EST FE = new Features_EST();
				FE.Verify_SendExternalCommunicationFieldForEST(CVM);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Abhishek Palankar on 21-Jun-2023
// US-935032 : TC-248570
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 4, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the Collector Name routing")
	public void Validate_FieldsONCollectorNameRouting(HashMap<String, String> CST) throws Throwable {
		if (flag) {
			logger = extent.createTest("5.4 - Case -Verify fields on the Collector Name routing")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.VarifyFieldsOnCollectorNameRouting(CST);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Abhishek Palankar on 21-Jun-2023
// US-935032 : TC-248571
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 5, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the Employee routing")
	public void Validate_FieldsONEmployeeRouting(HashMap<String, String> CST) throws Throwable {
		if (flag) {
			logger = extent.createTest("5.5 - Case -Verify fields on the Employee routing")
					.assignCategory("Regression");
			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.VarifyFieldsOnEmployeeRouting(CST);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}



	
	

// Created by - Abhishek Palankar on 22-Jun-2023
// US-935032 : TC-248574
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 6, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the Spring routing")
	public void Validate_FieldsONSpringRouting(HashMap<String, String> CST) throws Throwable {
		if (flag) {
			logger = extent.createTest("5.6 - Case -Verify fields on the Spring routing").assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.VarifyFieldsOnSpringRouting(CST);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Abhishek Palankar on 23-Jun-2023
// US-935032 : TC-248575
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 7, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the Sales Office Contact routing")
	public void VarifyFieldsOnSalesOfficeContactRouting(HashMap<String, String> CST) throws Throwable {
		if (flag) {
			logger = extent.createTest("5.7 - Case -Verify fields on the Sales Office Contact routing")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.VarifyFieldsOnSalesOfficeContactRouting(CST);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Abhishek Palankar on 23-Jun-2023
// US-935032 : TC-248576
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 8, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the Partner Function routing")
	public void Validate_FieldsONPartnerFunctionRouting(HashMap<String, String> CST) throws Throwable {
		if (flag) {
			logger = extent.createTest("5.8 - Case -Verify fields on the Partner Function routing")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.VarifyFieldsOnPartnerFunctionRouting(CST);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Niranjan H  on 21-June-2023
// US - 935032 : TC - 248577
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 9, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that On the Partner Function CST Routing Type ChekBox Should Be Present As Assign to Manager")
	public void ValidatepresenceOF_AssignToManagerCheckbox(HashMap<String, String> MapValue) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.9 - CST & EST - Verify that On the Partner Function CST Routing Type ChekBox Should Be Present As Assign to Manager")
					.assignCategory("Regression");

			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.Validate_AssigneToMangerCheckBox(MapValue);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Niranjan H on 10-Jul-2023
// US-943559 : TC-248858
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 10, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Email ID Routing page")
	public void VerifyESTProblem_OnEmailIdRouting(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.10 - CST & EST - Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Email ID Routing page")
					.assignCategory("Regression");
			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateESTProblemOn_EmailIDRouting(Cases);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Niranjan H on 10-Jul-2023
// US-943559 : TC-248860
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 11, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Employee Routing page")
	public void VerifyESTProblem_OnEmployeeRouting(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.11 - CST & EST - Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Employee Routing page")
					.assignCategory("Regression");
			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateESTProblemOn_EmployeeRouting(Cases);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

// Created by - Niranjan H on 10-Jul-2023
// US-943559 : TC-248861
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 12, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Partner Function Routing page")
	public void VerifyESTProblem_OnPartnerFunctionRouting(HashMap<String, String> Cases) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.12 - CST & EST - Verify that user should able to see EST Problem Reported under the CST Problem Reported Code field on Partner Function Routing page")
					.assignCategory("Regression");
			try {

				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateESTProblemOn_PartnerFunctionRouting(Cases);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

	// Created by - Niranjan H on 12-Jul-2023
	// US-943559 : TC-248862
	@Parameters({ "CSTRouting.json" })
	@Test(priority = 13, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "Verify That if User tries to add both CST Problem Reported Code And an EST Problem Reported Code, Validation rule with an Error should be displayed")
	public void ErrorMSGFor_CSTAndESTProblemReported(HashMap<String, String> Cst) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"5.13 - CST & EST - Verify that if User Tries To Add Both CST Problem Reported Code And an EST Problem Reported Code, Validation Rule with an Error should be displayed")
					.assignCategory("Regression");
			try {
				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
				Features_CSTRouting FC = new Features_CSTRouting();
				FC.ValidateErrorMSGFor_CSTAndESTProblemReported(Cst);

			} catch (Exception e) {
				CommonFunctions.CaptureFailure(e);
			} finally {
				logOut();
			}
		}
	}

//==================================================== Don't execute =====================================
	
	// Changes reverted for Pace/Spring routing
////Created by - Abhishek Palankar on 22-Jun-2023
////US-935032 : TC-248573
//	@Parameters({ "CSTRouting.json" })
//	@Test(priority = 6, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the Pace/Spring routing")
//	public void Validate_FieldsONPACEAndSpringRouting(HashMap<String, String> CST) throws Throwable {
//		if (flag) {
//			logger = extent.createTest("5.6 - Case -Verify fields on the Pace/Spring routing")
//					.assignCategory("Regression");
//
//			try {
//
//				login("UserName_OffshoreBottler", "Password_OffshoreBottler");
//				Features_CSTRouting FC = new Features_CSTRouting();
//				FC.VarifyFieldsOnPACEAndSpringRouting(CST);
//
//			} catch (Exception e) {
//				CommonFunctions.CaptureFailure(e);
//			} finally {
//				logOut();
//			}
//		}
//	}
	
	
	
////Created by - Abhishek Palankar on 21-Jun-2023
////US-935032 : TC-248572
//@Parameters({ "CSTRouting.json" })
//@Test(priority = 6, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This test is to verify that user should able to see CC Business Segment, OU/Region, MU/Division fields on the PACE routing")
//public void Validate_FieldsONPACERouting(HashMap<String, String> CST) throws Throwable {
//	if (flag) {
//		logger = extent.createTest("4.6 - Case -Verify fields on the PACE routing")
//				.assignCategory("Regression");
//		
//		try {
//			
//			login("UserName_OffshoreBottler", "Password_OffshoreBottler");
//			Features_CSTRouting FC = new Features_CSTRouting();
//			FC.VarifyFieldsOnPACERouting(CST);
//		
//		}catch (Exception e) {CommonFunctions.CaptureFailure(e);} 
//		 finally {logOut();}
//	}
//}
	
	
}
