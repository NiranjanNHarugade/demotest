package ProjectDevOps;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Features.Features_CloneOrders;
import Utilities.BaseTest;
import Utilities.CommonFunctions;
import Utilities.Constants;
import Utilities.DataProvider;

public class CloneOrders extends BaseTest implements Constants {
	static boolean flag;

	@BeforeClass
	public void LaunchBrowser() throws IOException, InterruptedException {
		extent.attachReporter(spark);
//		startTime = LocalDateTime.now();
		deleteScreenShots();
		initialization();
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName1 = cap.getBrowserName();
		String browserVersion = (String) cap.getCapability("browserVersion");
		String OS = System.getProperty("os.name").toLowerCase();
		String javaVersion = System.getProperty("java.version");
		String userName = System.getProperty("user.name");
		spark.config().setReportName("CONA DevOps Automation Report");
		spark.config().setDocumentTitle("CONA DevOps");
		extent.setSystemInfo("Suite Name", "Contact Center DevOps Part B");
		extent.setSystemInfo("Environment Name", Constants.envName);
		extent.setSystemInfo("Bottler ID", Constants.bottler + "-" + Constants.bottlerName);
		extent.setSystemInfo("Browser Name", browserName1);
		extent.setSystemInfo("Browser Version", browserVersion);
		extent.setSystemInfo("OS Name", OS);
		extent.setSystemInfo("User Name", userName);
		extent.setSystemInfo("Java-Version", javaVersion);
		flag = CommonFunctions.readMetaData("CloneOrders", "ContactCenter_1", "DevOps CC Part B L11");

	}

	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// US - 969954 TC - 241005 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 1, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZMKT order that are in a dispatched status.")
	public void cloneDispatchedZMKTOrder(HashMap<String, String> cloneDisptachedZMKTDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.1 - Clone Order - ZMKT : Dispatched Status").assignCategory("Regression");
		try {
			login("UserName_Agent", "Password_Agent");
			Features_CloneOrders.cloneZMKTOrderWithDispatchedStatus(cloneDisptachedZMKTDetails);
		}finally {
			System.out.println("Clone Order for Dispatched, Prep For Shipment, Out For Delivery and Delivered status has started");
		}
		}
	}

	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// US - 969954 TC - 241006 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 2, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZMKT order that are in a Prep For Shipment status.")
	public void cloneZMKTOrderWithPrepForShipmentStatus(HashMap<String, String> clonePrepForShipmentZMKTDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.2 - Clone Order - ZMKT : Prep For Shipment Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZMKTOrderWithPrepForShipmentStatus(clonePrepForShipmentZMKTDetails);
		}
	}

	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// US - 969954 TC - 241007 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 3, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZMKT order that are in a Prep For Shipment status.")
	public void cloneZMKTOrderWithOutForDeliveryStatus(HashMap<String, String> cloneOutForDeliveryZMKTDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.3 - Clone Order - ZMKT : Out For Deliver Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZMKTOrderWithOutForDeliveryStatus(cloneOutForDeliveryZMKTDetails);
			
		}
	}

	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// US - 969954 TC - 241008 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 4, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZMKT order that are in a Prep For Shipment status.")
	public void cloneZMKTOrderWithDeliveredStatus(HashMap<String, String> cloneDeliveredZMKTDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.4 - Clone Order - ZMKT : Delivered Status").assignCategory("Regression");
			Features_CloneOrders.cloneZMKTOrderWithDeliveredStatus(cloneDeliveredZMKTDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241005 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 5, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZRET order that are in a Dispatched status.")
	public void cloneZRETOrderWithDispatchedStatus(HashMap<String, String> cloneDispatchedZRETDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.5 - Clone Order - ZRET : Dispatched Status").assignCategory("Regression");
			Features_CloneOrders.cloneZRETOrderWithDispatchedStatus(cloneDispatchedZRETDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241006 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 6, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZRET order that are in a Prep For Shipment status.")
	public void cloneZRETOrderWithPrepForShipmentStatus(HashMap<String, String> cloneShipmentZRETDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.6 - Clone Order - ZRET : Prep For Shipment Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZRETOrderWithPrepForShipmentStatus(cloneShipmentZRETDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241007 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 7, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZRET order that are in a Out For Delivery status.")
	public void cloneZRETOrderWithOutForDeliveryStatus(HashMap<String, String> cloneOutForDeliveryZRETDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.7 - Clone Order - ZRET : Out For Delivery Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZRETOrderWithOutForDeliveryStatus(cloneOutForDeliveryZRETDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241008 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 8, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZRET order that are in a Delivered status.")
	public void cloneZRETOrderWithDeliveredStatus(HashMap<String, String> cloneDeliveredZRETDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.8 - Clone Order - ZRET : Delivered Status").assignCategory("Regression");
			
				Features_CloneOrders.cloneZRETOrderWithDeliveredStatus(cloneDeliveredZRETDetails);
			
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241005 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 9, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOR order that are in a dispatched status.")
	public void cloneZOROrderWithDispatchedStatus(HashMap<String, String> cloneDispatchedZORDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.9 - Clone Order - ZOR : Dispatched Status").assignCategory("Regression");
			Features_CloneOrders.cloneZOROrderWithDispatchedStatus(cloneDispatchedZORDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241006 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 10, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOR order that are in a Prep For Shipment status.")
	public void cloneZOROrderWithPrepForShipmentStatus(HashMap<String, String> cloneShipmentZORDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.10 - Clone Order - ZOR : Prep For Shipment Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZOROrderWithPrepForShipmentStatus(cloneShipmentZORDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241007 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 11, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOR order that are in a Out For Delivery status.")
	public void cloneZOROrderWithOutForDeliveryStatus(HashMap<String, String> cloneOutForDeliveryZORDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest("3.11 - Clone Order - ZOR : Out For Delivery Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZOROrderWithOutForDeliveryStatus(cloneOutForDeliveryZORDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 241008 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 12, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOR order that are in a Delivered status.")
	public void cloneZOROrderWithDeliveredStatus(HashMap<String, String> cloneDeliveredZORDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.12 - Clone Order - ZOR : Delivered Status").assignCategory("Regression");
			Features_CloneOrders.cloneZOROrderWithDeliveredStatus(cloneDeliveredZORDetails);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241005 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 13, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZFRE orders that are in a dispatched status.")
	public void cloneZFREwithDispatched(HashMap<String, String> cloneZFRE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.13 - Clone Order - ZFRE : Dispatched Status").assignCategory("Regression");
			Features_CloneOrders.cloneZFREwithDispatchedStatus(cloneZFRE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241006 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 14, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZFRE orders that are in a Prep For Shipment Status.")
	public void cloneZFREwithPrepForShipment(HashMap<String, String> cloneZFRE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.14 - Clone Order - ZFRE : Prep For Shipment Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZFREwithPrepForShipmentStatus(cloneZFRE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241007 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 15, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZFRE orders that are in a Out For Delivery status.")
	public void cloneZFREwithOutForDelivery(HashMap<String, String> cloneZFRE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.15 - Clone Order - ZFRE : Out For Delivery Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZFREwithOutForDeliveryStatus(cloneZFRE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241008 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 16, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZFRE orders that are in a Delivered status.")
	public void cloneZFREwithDelivered(HashMap<String, String> cloneZFRE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.16 - Clone Order - ZFRE : Delivered Status").assignCategory("Regression");
			Features_CloneOrders.cloneZFREwithDeliveredStatus(cloneZFRE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241005 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 17, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOE orders that are in a dispatched status.")
	public void cloneZOEwithDispatched(HashMap<String, String> cloneZOE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.17 - Clone Order - ZOE : Dispatched Status").assignCategory("Regression");
			Features_CloneOrders.cloneZOEwithDispatchedStatus(cloneZOE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241006 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 18, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOE orders that are in a Prep For Shipment status.")
	public void cloneZOEwithPrepForShipment(HashMap<String, String> cloneZOE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.18 - Clone Order - ZOE : Prep For Shipment Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZOEwithPrepForShipmentStatus(cloneZOE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241007 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 19, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOE orders that are in a Out For Delivery status.")
	public void cloneZOEwithOutForDelivery(HashMap<String, String> cloneZOE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.19 - Clone Order - ZOE : Out For Delivery Status")
					.assignCategory("Regression");
			Features_CloneOrders.cloneZOEwithOutForDeliveryStatus(cloneZOE);
		}
	}

	// Created by - Naveen S on 05-July-2023
	// US - 969954 TC - 241008 & 247006
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 20, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify that users are able to clone ZOE orders that are in a Delivered status.")
	public void cloneZOEwithDelivered(HashMap<String, String> cloneZOE) throws Throwable {
		if (flag) {
			logger = extent.createTest("3.20 - Clone Order - ZOE : Delivered Status").assignCategory("Regression");
			Features_CloneOrders.cloneZOEwithDeliveredStatus(cloneZOE);
				}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 21, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with Draft Status")
	public void checkCloneOrderWithDraftStatus(HashMap<String, String> CheckCloneDraftDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.21 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>Draft status</b>")
					.assignCategory("Regression");
			Features_CloneOrders.checkCloneOrderWithDraftStatus(CheckCloneDraftDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 22, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with Pending Status")
	public void checkCloneOrderWithPendingStatus(HashMap<String, String> CheckClonePendingDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.22 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>Pending Status</b>")
					.assignCategory("Regression");
			Features_CloneOrders.checkCloneOrderWithPendingStatus(CheckClonePendingDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 23, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with In-Error Status")
	public void checkCloneOrderWithInErrorStatus(HashMap<String, String> CheckCloneInErrorDetails) throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.23 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>In-Error Status</b>")
					.assignCategory("Regression");
			Features_CloneOrders.checkCloneOrderWithInErrorStatus(CheckCloneInErrorDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 24, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with Confirmed Status")
	public void checkCloneOrderWithConfirmedStatus(HashMap<String, String> CheckCloneConfirmedDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.24 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>Confirmed Status</b>")
					.assignCategory("Regression");
			Features_CloneOrders.checkCloneOrderWithConfirmedStatus(CheckCloneConfirmedDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 25, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with In-Settlement Status")
	public void checkCloneOrderWithInSettlementStatus(HashMap<String, String> CheckCloneInSettlementDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.25 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>In-Settlement Status</b>")
					.assignCategory("Regression");
			Features_CloneOrders.checkCloneOrderWithInSettlementStatus(CheckCloneInSettlementDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 26, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with Not Delivered Status")
	public void checkCloneOrderWithNotDeliveredStatus(HashMap<String, String> CheckCloneNotDeliveredDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.26 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>Not Delivered Status</b>")
					.assignCategory("Regression");
			Features_CloneOrders.checkCloneOrderWithNotDeliveredStatus(CheckCloneNotDeliveredDetails);
		}
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// US - 969954 TC - 247083
	@Parameters({ "CloneOrder.json" })
	@Test(priority = 27, dataProviderClass = DataProvider.class, dataProvider = "readData", description = "This method is to verify the Visibility of Clone option in orders with Cancelled Status")
	public void checkCloneOrderWithCancelledStatus(HashMap<String, String> CheckCloneCancelledDetails)
			throws Throwable {
		if (flag) {
			logger = extent.createTest(
					"3.27 - Clone Order - Verifying that no clone option for all type of Orders which are in <b>Cancelled Status</b>")
					.assignCategory("Regression");
			try {
				Features_CloneOrders.checkCloneOrderWithCancelledStatus(CheckCloneCancelledDetails);
			} finally {
				System.out.println("All Orders : Cancelled Status with no clone option is Successful");
				logOut();
			}
		}
	}
}
