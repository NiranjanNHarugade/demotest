package Features;


import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import Pages.CreateCloneOrders;
import Utilities.CommonFunctions;


public class Features_CloneOrders extends CommonFunctions {

	// created by Krishna Prasad on 7 June 2023 	
	//This method is to verify that user are able to Clone Sales Orders in Dispatched, Prep For Shipment, Out For Delivery, Delivered status
	public static void cloneScript(HashMap<String, String> cloneDisptachedZMKTDetails, String salesRecordType,
		String statusType) throws Throwable {
		
		String order = cloneDisptachedZMKTDetails.get("Order");
		String actualStatusOrder = cloneDisptachedZMKTDetails.get("OrderStatus");
		String listViewSelection = cloneDisptachedZMKTDetails.get("ListViewSelection");
		String orderSort = cloneDisptachedZMKTDetails.get("OrderSorting");
		String shopCartFields = cloneDisptachedZMKTDetails.get("ShoppingCartFields");
		String parentClassName = cloneDisptachedZMKTDetails.get("ClassName");
		String shopCartButton = cloneDisptachedZMKTDetails.get("ShoppingCartButton");
		String deliveryInfoField = cloneDisptachedZMKTDetails.get("DeliveryInfoField");
		String DispatchInfoField = cloneDisptachedZMKTDetails.get("DispatchInfoField");
		String expectedOrderOrigin = cloneDisptachedZMKTDetails.get("OrderOrigin");
		String expectedAccName = "";
		String expectedContactDisplay = "";
		String actualorderigin = "";
		String contactdisplay = "";
		String actualaccname = "";
		String actualsalesdoc = "";
		String onetimedeliverynote = "";
		String onetimedispatchnote = "";
		String approvedby = "";
		String cloneordnum = "";
		String cloneorderigin = "";
		String cloneaccname = "";
		String clonesalesdoc = "";
		String clonecontactdisplay = "";
		String statusorder = "";
		String cloneonetimedeliverynote = "";
		String cloneonetimedispatchnote = "";
		String cloneapprovedby = "";
		closeMultipleTabs();
		try {
			System.out.println(
					"The " + salesRecordType + " Order, which is currently in a " + statusType + " statusType, has started");
			if(driver.findElements(CreateCloneOrders.errorMessage).size()>0) {
				javaClickByLocator(CreateCloneOrders.FinishButton);
			}
			Thread.sleep(2000);
			
			selectAppOrObjFromAppLauncher("Object", order);
			new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
			Thread.sleep(3000);
			javaClickByLocator(CreateCloneOrders.listViewOrders);
			Thread.sleep(2000);
			if (driver.findElements(CreateCloneOrders.listViewOrders).size() > 0) {
				logger.pass(
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are displayed in a list view.");
				assertTrue(driver.findElements(CreateCloneOrders.listViewOrders).size() > 0,
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are displayed in a list view.");

			} else {
				screenshot = capture(driver);
				logger.fail(
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are not displayed in a list view.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				softAssert.fail(
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are not displayed in a list view.");

			}
			driver.findElement(CreateCloneOrders.searchBar).click();
			Thread.sleep(2000);
			driver.findElement(CreateCloneOrders.searchBar).clear();
			Thread.sleep(2000);
			driver.findElement(CreateCloneOrders.searchBar).sendKeys(listViewSelection);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.selectList);
			new WebDriverWait(driver, Duration.ofSeconds(200))
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
			Thread.sleep(2000);
			driver.findElement(CreateCloneOrders.filterButton).click();
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.removeAllFilter);
			try {
				if (driver.findElement(CreateCloneOrders.saveButton).isDisplayed()) {
					javaClickByLocator(CreateCloneOrders.saveButton);
				}

			} catch (Exception e) {

			}
			new WebDriverWait(driver, Duration.ofSeconds(200))
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.addFilter));
					
			Thread.sleep(10000);
			javaClickByLocator(CreateCloneOrders.addFilter);
			javaClickByLocator(CreateCloneOrders.fieldButton);
			javaClickByLocator(CreateCloneOrders.salesDocumentType);
			javaClickByLocator(CreateCloneOrders.salesDocvalueButton);
			Thread.sleep(2000);
			javaClickByLocator(By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']//a[@title='"
					+ salesRecordType + "']"));

			javaClickByLocator(CreateCloneOrders.doneButton);
			javaClickByLocator(CreateCloneOrders.addFilter);
			javaClickByLocator(CreateCloneOrders.fieldButton);
			Thread.sleep(2000);
			scrollToWebElement(driver.findElement(CreateCloneOrders.statusFieldButton));
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.statusFieldButton);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.statusVlaueButton);
			javaClickByLocator(By.xpath("//a[text()='" + statusType + "']"));
			javaClickByLocator(CreateCloneOrders.statusVlaueButton);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.doneButton);
			javaClickByLocator(CreateCloneOrders.addFilter);
			javaClickByLocator(CreateCloneOrders.fieldButton);
			Thread.sleep(2000);
			scrollToWebElement(driver.findElement(CreateCloneOrders.startedDateFieldButton));
			javaClickByLocator(CreateCloneOrders.startedDateFieldButton);
			javaClickByLocator(CreateCloneOrders.operatorButton);
			Thread.sleep(1000);
			javaClickByLocator(CreateCloneOrders.greaterThanOperatorButton);
			javaClickByLocator(CreateCloneOrders.startedDateValueButton);
			Thread.sleep(2000);
			LocalDate startedDateValue = LocalDate.now().minusDays(7);         
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");        
			String formattedDate = startedDateValue.format(formatter);
			driver.findElement(CreateCloneOrders.startedDateValueButton).sendKeys(formattedDate);
			driver.findElement(CreateCloneOrders.startedDateValueButton).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.doneButton);
			javaClickByLocator(CreateCloneOrders.addFilter);
			javaClickByLocator(CreateCloneOrders.fieldButton);
			Thread.sleep(2000);
			scrollToWebElement(driver.findElement(CreateCloneOrders.orderOriginPicklist));
			javaClickByLocator(CreateCloneOrders.orderOriginPicklist);
			javaClickByLocator(CreateCloneOrders.operatorButton);
			Thread.sleep(1000);
			javaClickByLocator(CreateCloneOrders.notEqualtoOperator);
			javaClickByLocator(CreateCloneOrders.statusVlaueButton);
			Thread.sleep(1000);
			scrollToWebElement(driver.findElement(CreateCloneOrders.myCokeOrders));
			javaClickByLocator(CreateCloneOrders.myCokeOrders);
			javaClickByLocator(CreateCloneOrders.doneButton);
			Thread.sleep(2000);
			try {
				if (driver.findElement(CreateCloneOrders.saveButton).isDisplayed()) {
					javaClickByLocator(CreateCloneOrders.saveButton);
				}

			} catch (Exception e) {
			}
			Thread.sleep(10000);
			if (driver.findElements(CreateCloneOrders.selectOrder).size() > 0) {
				screenshot = capture(driver);
				logger.pass(
						"<b> Expected Result: </b>The list view of " + salesRecordType + " Orders with a " + statusType
								+ " statusType should be visible. <br /> <b> Actual Result: </b>The list view of "
								+ salesRecordType + " Orders with " + statusType + " statusType is displayed.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				assertTrue(driver.findElements(CreateCloneOrders.selectOrder).size() > 0,
						"<b> Expected Result: </b>The list view of " + salesRecordType + " Orders with a " + statusType
								+ " statusType should be visible. <br /> <b> Actual Result: </b>The list view of "
								+ salesRecordType + " Orders with " + statusType + " statusType is displayed.");
			} else {
				screenshot = capture(driver);
				logger.info("List View for Orders  for " + salesRecordType + " with " + statusType + " is not diplayed",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			}

			Thread.sleep(4000);

			if (driver.findElements(CreateCloneOrders.selectOrder).size() > 0) {
				new WebDriverWait(driver, Duration.ofSeconds(200))
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));

				javaClickByLocator(CreateCloneOrders.hideFilter);

				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
				Thread.sleep(2000);
				if ((driver.findElement(CreateCloneOrders.orderSorting).getText()).equals(orderSort)) {
					javaClickByLocator(CreateCloneOrders.orderStartDate);
					new WebDriverWait(driver, explicitWaitTimeOut)
							.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
				}
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));

				expectedAccName = driver.findElement(CreateCloneOrders.expectedAccName).getText();
				try {

					if (driver.findElements(CreateCloneOrders.expectedCont).size() > 0) {
						expectedContactDisplay = driver.findElement(CreateCloneOrders.expectedContName).getText();
					}
				} catch (Exception e) {
					screenshot = capture(driver);
					logger.info("Order contact is not available for this order",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				}
				javaClickByLocator(CreateCloneOrders.selectOrder);
				Thread.sleep(2000);
				actualorderigin = driver.findElement(CreateCloneOrders.orderOriginDisplay).getText();
				logger.info("The Order Origin of the selected Order is <b>"+actualorderigin+".</b>");

				contactdisplay = driver.findElement(CreateCloneOrders.orderContactDisplay).getText();
				if (contactdisplay.equals(expectedContactDisplay)) {
					logger.pass("<b>Expected Result :<b /> The selected Order Contact Name Should be "
							+ expectedContactDisplay
							+ "</br><b> Actual Result :<b /> The selected Order Contact Name is " + contactdisplay);
				}
				actualaccname = driver.findElement(CreateCloneOrders.accNamePage).getText();
				Assert(actualaccname, expectedAccName,  "The selected Order Account Name should be ",
						"The selected Order Account name is ", true);

				actualsalesdoc = driver.findElement(CreateCloneOrders.saleDocType).getText();
				Assert(actualsalesdoc, salesRecordType,  "The selected Order Sales Document Type should be ",
						"The selected Order Sales Document Type is ", true);
				Thread.sleep(4000);
				scrollToWebElement(driver.findElement(CreateCloneOrders.CartTab));
				Thread.sleep(2000);
				List<WebElement> lineItem = driver.findElements(CreateCloneOrders.material);
				List<WebElement> lineQty = driver.findElements(CreateCloneOrders.quantity);
				screenshot = capture(driver);
				logger.info("The products included in the selected order are visible shopping cart are shown/displayed.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				List<String> lineList = new ArrayList<>();
				// The Below For loop is used to add the Row "Material" to a list and later
				// comparing it with the cloned order
				for (WebElement ele : lineItem) {
					lineList.add(ele.getText());
				}

				List<String> lineQtyList = new ArrayList<>();
				// The Below For loop is used to add the Row "Quantity" to a list and later
				// comparing it with the cloned order
				for (WebElement ele1 : lineQty) {
					lineQtyList.add(ele1.getText());

				}

				List<String> modifiedQty = new ArrayList<>();
				if (statusType.equals("Delivered")) {

					for (String input : lineQtyList) {
						String modifiedQtyString = input.replace("/0", "");
						modifiedQty.add(modifiedQtyString);
					}
				}
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.delDisShipBtn);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.delInfoTab));
				scrollToWebElement(driver.findElement(CreateCloneOrders.oneTimeDeliveryNotes));
				onetimedeliverynote = driver.findElement(CreateCloneOrders.oneTimeDeliveryNotes).getText();
				onetimedispatchnote = driver.findElement(CreateCloneOrders.oneTimeDispatchNotes).getText();
				approvedby = driver.findElement(CreateCloneOrders.approvedBy).getText();
				Thread.sleep(2000);
				scrollToWebElement(driver.findElement(CreateCloneOrders.odrHdrBtn));
				javaClickByLocator(CreateCloneOrders.shopCartBtn);
				Thread.sleep(5000);
				scrollToWebElement(driver.findElement(CreateCloneOrders.shopCartBtn));

				Thread.sleep(2000);

				scrollToWebElement(driver.findElement(CreateCloneOrders.orderLayout));
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.cloneButton);
				Thread.sleep(2000);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated((CreateCloneOrders.clonePopUp)));
				// The below IF condition is used to check whether the Clone Pop up is
				// displayed
				if (driver.findElements(CreateCloneOrders.clonePopUp).size() > 0) {
					screenshot = capture(driver);
					assertTrue(driver.findElements(CreateCloneOrders.clonePopUp).size() > 0,
							"<b> Expected Result:</b> Clone Order popup should be displayed <br /> <b> Actual Result: </b> Clone Order popup is displayed");
					logger.pass(
							"<b> Expected Result:</b> Clone Order popup should be displayed <br /> <b> Actual Result: </b> Clone Order popup is displayed",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				} else {
					screenshot = capture(driver);
					logger.fail(
							"<b> Expected Result:</b> Clone Order popup should be displayed <br /> <b> Actual Result: </b> Clone Order popup is not displayed",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					Assert.fail(
							"<b> Expected Result:</b> Clone Order popup should be displayed <br /> <b> Actual Result: </b> Clone Order popup is not displayed");
				}
				javaClickByLocator(CreateCloneOrders.yesButton);
				if(driver.findElements(CreateCloneOrders.errorMessage).size()>0) {
					screenshot = capture(driver);
					logger.fail("<b> Expected Result:</b> Clone Order should be Created <br /> <b> Actual Result: </b> Clone Order is not Created",MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					Assert.fail("<b> Expected Result:</b> Clone Order should be Created <br /> <b> Actual Result: </b> Clone Order is not Created");
					
				}
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderNum));
				Thread.sleep(5000);
				cloneordnum = driver.findElement(CreateCloneOrders.orderNum).getText();
				Thread.sleep(2000);
				closeMultipleTabs();
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.globalSearchButton);
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.globalSearch);
				Thread.sleep(2000);
				driver.findElement(CreateCloneOrders.globalSearch).sendKeys(cloneordnum);
				Thread.sleep(2000);
				driver.findElement(CreateCloneOrders.globalSearch).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']//a[@title='"
								+ cloneordnum + "']"));
				Thread.sleep(2000);
				javaClickByLocator(
						By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']//a[@title='"
								+ cloneordnum + "']"));
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderNum));
				cloneorderigin = driver.findElement(By.xpath("//span[text()='" + cloneordnum
						+ "']/ancestor::div[contains(@class,'slds-page-header slds-page-header_record-home')]//div[text()='Order Origin']/parent::li/child::div[@class='slds-truncate']//span"))
						.getText();
				driver.findElement(CreateCloneOrders.accNamePage);
				cloneaccname = driver.findElement(CreateCloneOrders.accNamePage).getText();

				driver.findElement(CreateCloneOrders.accNamePage);
				cloneaccname = driver.findElement(CreateCloneOrders.accNamePage).getText();

				driver.findElement(CreateCloneOrders.saleDocType);
				clonesalesdoc = driver.findElement(CreateCloneOrders.saleDocType).getText();

				driver.findElement(CreateCloneOrders.orderContactDisplay);
				clonecontactdisplay = driver.findElement(CreateCloneOrders.orderContactDisplay).getText();

				statusorder = driver.findElement(CreateCloneOrders.statusOrder).getText();
				scrollToWebElement(driver.findElement(CreateCloneOrders.CartTab));
				Thread.sleep(5000);
				List<WebElement> cloneItem = driver.findElements(CreateCloneOrders.cloneMaterial);
				List<WebElement> cloneQty = driver.findElements(CreateCloneOrders.quantity);
				List<String> cloneList = new ArrayList<>();
				List<String> cloneQtyList = new ArrayList<>();
				screenshot = capture(driver);
				logger.info("The Cloned Order & it's corresponding products in the shopping cart are being displayed.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				// The Below For loop is used to add the Row "Material" to a list for comparing
				// it with the original order
				for (WebElement element : cloneItem) {
					cloneList.add(element.getText());
				}
				// The Below For loop is used to add the Row "Quantity" to a list and comparing
				// it with the original order

				for (WebElement ele2 : cloneQty) {
					cloneQtyList.add(ele2.getText());
				}
				Thread.sleep(2000);
				if (statusType.equals("Delivered")) {
					boolean allItemDeliverd = compareLists(lineList, cloneList);
					boolean allQtyDelivered = compareLists(modifiedQty, cloneQtyList);
					if (allItemDeliverd == true) {
						screenshot = capture(driver);
						assertTrue(allItemDeliverd == true,
								"<b> Expected Result: </b> The Products that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products that exist in the Original Order also exists in the Cloned Order.");
						logger.pass(
								"<b> Expected Result: </b> The Products that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products that exist in the Original Order also exists in the Cloned Order.");
					} else {
						logger.fail(
								"<b> Expected Result: </b> The Products that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products that exist in the Original Order doesn't exist in the Cloned Order.",
								MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					}
					if (allQtyDelivered == true) {
						screenshot = capture(driver);
						assertTrue(allQtyDelivered == true,
								"<b> Expected Result: </b> The Products Quantity that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products Quantity that exist in the Original Order also exists in the Cloned Order.");
						logger.pass(
								"<b> Expected Result: </b> The Products Quantity that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products Quantity that exist in the Original Order also exists in the Cloned Order.");
					} else {
						logger.fail(
								"<b> Expected Result: </b> The Products Quantity that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products Quantity that exist in the Original Order doesn't exist in the Cloned Order.",
								MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					}
				} else {
					boolean allItems = compareLists(lineList, cloneList);
					boolean allQty = compareLists(lineQtyList, cloneQtyList);
					Thread.sleep(2000);
					// The below If Condition is used to check whether all the materials present in
					// the Original order is displayed in the Cloned Order
					if (allItems == true) {
						screenshot = capture(driver);
						assertTrue(allItems == true,
								"<b> Expected Result: </b> The Products that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products that exist in the Original Order also exists in the Cloned Order.");
						logger.pass(
								"<b> Expected Result: </b> The Products that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products that exist in the Original Order also exists in the Cloned Order.");
					} else {
						logger.fail(
								"<b> Expected Result: </b> The Products that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products that exist in the Original Order doesn't exist in the Cloned Order.",
								MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					}
					// The below If Condition is used to check whether all the materials Quantity
					// present in the Original order is displayed in the Cloned Order
					if (allQty == true) {
						screenshot = capture(driver);
						assertTrue(allQty == true,
								"<b> Expected Result: </b> The Products Quantity that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products Quantity that exist in the Original Order also exists in the Cloned Order.");
						logger.pass(
								"<b> Expected Result: </b> The Products Quantity that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products Quantity that exist in the Original Order also exists in the Cloned Order.");
					} else {
						logger.fail(
								"<b> Expected Result: </b> The Products Quantity that exist in the Original Order must also exist in the Cloned Order. <br /> <b> Actual Result: </b> The Products Quantity that exist in the Original Order doesn't exist in the Cloned Order.",
								MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					}

				}

				if(expectedOrderOrigin.equals(cloneorderigin)) {
				logger.pass("The Order Origin of the Cloned Order is "+ cloneorderigin);
				}else {
					logger.fail("<b>Expected Result: </b>The Order Origin should be <b>"+expectedOrderOrigin+"</b>.</br><b>Actual Result: </b>The Order  Orgin is <b>"+cloneorderigin+"</b>.");
				}
				Thread.sleep(2000);

				Assert(actualaccname, cloneaccname, "The Cloned Order Account name should be ",
						"The Cloned Order Account name is ", true);
				Thread.sleep(2000);

				Assert(actualsalesdoc, clonesalesdoc, "The Cloned Order Sales Document Type should be ",
						"The Cloned Order actual Sales Document Type is ", true);
				Thread.sleep(2000);

				Thread.sleep(3000);
				Assert(contactdisplay, clonecontactdisplay, "The Cloned Order Contact Name should be ",
						"The Cloned Order actual Contact Name is ", true);

				Thread.sleep(2000);

				Assert(statusorder, actualStatusOrder, "The Cloned Order statusType should be ",
						"The Cloned Order statusType is ", true);

				Thread.sleep(2000);
				FieldsAndDataValidation(shopCartFields, parentClassName, CreateCloneOrders.shopingCartFields);
				FieldsAndDataValidation(shopCartButton, parentClassName, CreateCloneOrders.shoppingCartButtons);

				scrollToWebElement(driver.findElement(CreateCloneOrders.odrHdrBtn));
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.odrHdrBtn);
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.delDisShipBtn);
				Thread.sleep(2000);
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.delInfoTab));
				FieldsAndDataValidation(deliveryInfoField, parentClassName,
						CreateCloneOrders.deliveryInformationFields);
				FieldsAndDataValidation(DispatchInfoField, parentClassName,
						CreateCloneOrders.dispatchInformationFields);
				scrollToWebElement(driver.findElement(CreateCloneOrders.oneTimeDeliveryNotes));
				Thread.sleep(2000);
				cloneonetimedeliverynote = driver.findElement(CreateCloneOrders.oneTimeDeliveryNotes).getText();
				Assert(onetimedeliverynote, cloneonetimedeliverynote,
						"The Cloned Order  One time Driver Instructions should be ",
						"The Cloned Order One time Driver Instructions is ", true);

				cloneonetimedispatchnote = driver.findElement(CreateCloneOrders.oneTimeDispatchNotes).getText();
				Assert(onetimedispatchnote, cloneonetimedispatchnote,
						"The Cloned Order One time Dispatch Notes should be ",
						"The Cloned Order One time Dispatch Notes ", true);
				Thread.sleep(2000);
				cloneapprovedby = driver.findElement(CreateCloneOrders.approvedBy).getText();
				Assert(approvedby, cloneapprovedby, "The Cloned Order Approved by should be ",
						"The Cloned Order Approved by is ", true);
				scrollToWebElement(driver.findElement(CreateCloneOrders.odrHdrBtn));
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.shopCartBtn);
				Thread.sleep(2000);

				scrollToWebElement(driver.findElement(CreateCloneOrders.orderLayout));
				Thread.sleep(2000);

			} else {
				screenshot = capture(driver);
				logger.info("Currently There are No Sales Orders present for" + salesRecordType + "and" + statusType,
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			}
			Thread.sleep(2000);
			System.out.println(salesRecordType + " and " + statusType + " is done Successfully");

		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(e.getMessage());
		}
		softAssert.assertAll();
		status = logger.getStatus();
	}

	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// This method is to verify that users are able to clone ZMKT order that are in a dispatched status.
	public static void cloneZMKTOrderWithDispatchedStatus(HashMap<String, String> cloneDispatchedZMKTDetails) throws Throwable {
		System.out.println("Orders - ZMKT : Dispatched Status");
		String zmkt = cloneDispatchedZMKTDetails.get("SalesDocumentTypeZMKT");
		String statusType = cloneDispatchedZMKTDetails.get("StatusDispatched");
		softAssert = new SoftAssert();
		cloneScript(cloneDispatchedZMKTDetails, zmkt, statusType);
		System.out.println("The successful completion of cloning the ZMKT Order with a dispatched status has been accomplished.");
	}
	
	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// This method is to verify that users are able to clone ZMKT order that are in a Prep For Shipment status.
	public static void cloneZMKTOrderWithPrepForShipmentStatus(HashMap<String, String> clonePrepForShipmentZMKTDetails) throws Throwable {
		System.out.println("Orders - ZMKT : Prep For Shipment Status");
		String zmkt = clonePrepForShipmentZMKTDetails.get("SalesDocumentTypeZMKT");
		String statusType = clonePrepForShipmentZMKTDetails.get("StatusShipement");
		softAssert = new SoftAssert();
		cloneScript(clonePrepForShipmentZMKTDetails, zmkt, statusType);
		System.out.println("The successful completion of cloning the ZMKT Order with a Prep For Shipment status has been accomplished.");
	}
	
	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// This method is to verify that users are able to clone ZMKT order that are in a Out For Delivery status.
	public static void cloneZMKTOrderWithOutForDeliveryStatus(HashMap<String, String> cloneOutForDeliveryZMKTDetails) throws Throwable {
		System.out.println("Orders - ZMKT : Out For Delivery Status");
		String zmkt = cloneOutForDeliveryZMKTDetails.get("SalesDocumentTypeZMKT");
		String statusType = cloneOutForDeliveryZMKTDetails.get("StatusOutforDelivery");
		softAssert = new SoftAssert();
		cloneScript(cloneOutForDeliveryZMKTDetails, zmkt, statusType);
		System.out.println("The successful completion of cloning the ZMKT Order with a Out For Delivery status has been accomplished.");
	}
	
	// Created by - Vishwajeet Shabadi on 04-JULY-2023
	// This method is to verify that users are able to clone ZMKT order that are in a Delivered status.
	public static void cloneZMKTOrderWithDeliveredStatus(HashMap<String, String> cloneDeliveredZMKTDetails) throws Throwable {
		System.out.println("Orders - ZMKT : Delivered Status");
		String zmkt = cloneDeliveredZMKTDetails.get("SalesDocumentTypeZMKT");
		String statusType = cloneDeliveredZMKTDetails.get("StatusDelivered");
		softAssert = new SoftAssert();
		cloneScript(cloneDeliveredZMKTDetails, zmkt, statusType);
		System.out.println("The successful completion of cloning the ZMKT Order with a Delivered status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZRET order that are in a dispatched status.
	public static void cloneZRETOrderWithDispatchedStatus(HashMap<String, String> cloneDispatchedZRETDetails) throws Throwable {
		System.out.println("Orders - ZRET : Dispatched Status");
		String zret = cloneDispatchedZRETDetails.get("SalesDocumentTypeZRET");	
		String statusType = cloneDispatchedZRETDetails.get("StatusDispatched");
		softAssert = new SoftAssert();
		cloneScript(cloneDispatchedZRETDetails, zret, statusType);
		System.out.println("The successful completion of cloning the ZRET Order with a Dispatched status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZRET order that are in a Prep For Shipment status.
	public static void cloneZRETOrderWithPrepForShipmentStatus(HashMap<String, String> cloneShipmentZRETDetails) throws Throwable {
		System.out.println("Orders - ZRET : Prep For Shipment Status");
		String zret = cloneShipmentZRETDetails.get("SalesDocumentTypeZRET");
		String statusType = cloneShipmentZRETDetails.get("StatusShipement");
		softAssert = new SoftAssert();
		cloneScript(cloneShipmentZRETDetails, zret, statusType);
		System.out.println("The successful completion of cloning the ZRET Order with a Prep For Shipment status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZRET order that are in a Out For Delivery status.
	public static void cloneZRETOrderWithOutForDeliveryStatus(HashMap<String, String> cloneOutForDeliveryZRETDetails) throws Throwable {
		System.out.println("Orders -  ZRET : Out For Delivery Status");
		String zret = cloneOutForDeliveryZRETDetails.get("SalesDocumentTypeZRET");
		String statusType = cloneOutForDeliveryZRETDetails.get("StatusOutforDelivery");
		softAssert = new SoftAssert();
		cloneScript(cloneOutForDeliveryZRETDetails, zret, statusType);
		System.out.println("The successful completion of cloning the ZRET Order with a Out For Delivery status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZRET order that are in a Delivered status.
	public static void cloneZRETOrderWithDeliveredStatus(HashMap<String, String> cloneDeliveredZRETDetails) throws Throwable {
		System.out.println("Orders - ZRET : Delivered Status");
		String zret = cloneDeliveredZRETDetails.get("SalesDocumentTypeZRET");	
		String statusType = cloneDeliveredZRETDetails.get("StatusDelivered");
		softAssert = new SoftAssert();
		cloneScript(cloneDeliveredZRETDetails, zret, statusType);
		System.out.println("The successful completion of cloning the ZRET Order with a Delivered status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZOR order that are in a dispatched status.
	public static void cloneZOROrderWithDispatchedStatus(HashMap<String, String> cloneDispatchedZORDetails) throws Throwable {
		System.out.println("Orders - ZOR : Dispatched Status");
		String zor = cloneDispatchedZORDetails.get("SalesDocumentTypeZOR");
		String statusType = cloneDispatchedZORDetails.get("StatusDispatched");
		softAssert = new SoftAssert();
		cloneScript(cloneDispatchedZORDetails, zor, statusType);
		System.out.println("The successful completion of cloning the ZOR Order with a Dispatched status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZOR order that are in a Prep For Shipment status.
	public static void cloneZOROrderWithPrepForShipmentStatus(HashMap<String, String> cloneShipmentZORDetails) throws Throwable {
		System.out.println("Orders - ZOR : Prep For Shipment Status");
		String zor = cloneShipmentZORDetails.get("SalesDocumentTypeZOR");
		String statusType = cloneShipmentZORDetails.get("StatusShipement");
		softAssert = new SoftAssert();
		cloneScript(cloneShipmentZORDetails, zor, statusType);
		System.out.println("The successful completion of cloning the ZOR Order with a Prep For Shipment status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZOR order that are in a Out For Delivery status.
	public static void cloneZOROrderWithOutForDeliveryStatus(HashMap<String, String> cloneOutForDeliveryZORDetails) throws Throwable {
		System.out.println("Orders - ZOR : Out For Delivery Status");
		String zor = cloneOutForDeliveryZORDetails.get("SalesDocumentTypeZOR");
		String statusType = cloneOutForDeliveryZORDetails.get("StatusOutforDelivery");
		softAssert = new SoftAssert();
		cloneScript(cloneOutForDeliveryZORDetails, zor, statusType);
		System.out.println("The successful completion of cloning the ZOR Order with a Out For Delivery status has been accomplished.");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	// This method is to verify that users are able to clone ZOR order that are in a Delivered status.
	public static void cloneZOROrderWithDeliveredStatus(HashMap<String, String> cloneDeliveredZORDetails) throws Throwable {
		System.out.println("Orders - ZOR : Delivered Status");
		String zor = cloneDeliveredZORDetails.get("SalesDocumentTypeZOR");
		String statusType = cloneDeliveredZORDetails.get("StatusDelivered");
		softAssert = new SoftAssert();
		cloneScript(cloneDeliveredZORDetails, zor, statusType);
		System.out.println("The successful completion of cloning the ZOR Order with a Delivered status has been accomplished.");
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZFRE order that are in a dispatched status.
	public static void cloneZFREwithDispatchedStatus(HashMap<String, String> cloneZFRE)throws Throwable {
		System.out.println("Orders - ZFRE : Dispatched Status");
		String zfre = cloneZFRE.get("SalesDocumentTypeZFRE");
		String statusType = cloneZFRE.get("StatusDispatched");
		softAssert = new SoftAssert();
		cloneScript(cloneZFRE, zfre, statusType);
		System.out.println("The successful completion of cloning the ZFRE Order with a Dispatched status has been accomplished.");
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZFRE order that are in a Prep For Shipment status.
	public static void cloneZFREwithPrepForShipmentStatus(HashMap<String, String> cloneZFRE) throws Throwable{
		System.out.println("Orders - ZFRE : Prep For Shipment Status");
		String zfre = cloneZFRE.get("SalesDocumentTypeZFRE");
		String statusType = cloneZFRE.get("StatusShipement");
		softAssert = new SoftAssert();
		cloneScript(cloneZFRE, zfre, statusType);
		System.out.println("The successful completion of cloning the ZFRE Order with a Prep For Shipment status has been accomplished.");
		
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZFRE order that are in a Out For Delivery status.
	public static void cloneZFREwithOutForDeliveryStatus(HashMap<String, String> cloneZFRE) throws Throwable {
		System.out.println("Orders - ZFRE : Out For Delivery Status");
		String zfre = cloneZFRE.get("SalesDocumentTypeZFRE");
		String statusType = cloneZFRE.get("StatusOutforDelivery");
		softAssert = new SoftAssert();
		cloneScript(cloneZFRE, zfre, statusType);
		System.out.println("The successful completion of cloning the ZFRE Order with a Out For Delivery status has been accomplished.");
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZFRE order that are in a Delivered status.
	public static void cloneZFREwithDeliveredStatus(HashMap<String, String> cloneZFRE) throws Throwable {
		System.out.println("Orders - ZFRE : Delivered Status");
		String zfre = cloneZFRE.get("SalesDocumentTypeZFRE");
		String statusType = cloneZFRE.get("StatusDelivered");
		softAssert = new SoftAssert();
		cloneScript(cloneZFRE, zfre, statusType);
		System.out.println("The successful completion of cloning the ZFRE Order with a Delivered status has been accomplished.");		
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZOE order that are in a dispatched status.
	public static void cloneZOEwithDispatchedStatus(HashMap<String, String> cloneZOE) throws Throwable {
		System.out.println("Orders - ZOE : Dispatched Status");
		String zoe = cloneZOE.get("SalesDocumentTypeZOE");
		String statusType = cloneZOE.get("StatusDispatched");
		softAssert = new SoftAssert();
		cloneScript(cloneZOE, zoe, statusType);
		System.out.println("The successful completion of cloning the ZOE Order with a Dispatched status has been accomplished.");		
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZOE order that are in a Prep For Shipment status.
	public static void cloneZOEwithPrepForShipmentStatus(HashMap<String, String> cloneZOE) throws Throwable {
		System.out.println("Orders - ZOE : Prep For Shipment Status");
		String zoe = cloneZOE.get("SalesDocumentTypeZOE");
		String statusType = cloneZOE.get("StatusShipement");
		softAssert = new SoftAssert();
		cloneScript(cloneZOE, zoe, statusType);
		System.out.println("The successful completion of cloning the ZOE Order with a Prep For Shipment status has been accomplished.");		
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZOE order that are in a Out For Delivery status.
	public static void cloneZOEwithOutForDeliveryStatus(HashMap<String, String> cloneZOE) throws Throwable {
		System.out.println("Orders - ZOE : Out For Delivery Status");
		String zoe = cloneZOE.get("SalesDocumentTypeZOE");
		String statusType = cloneZOE.get("StatusOutforDelivery");
		softAssert = new SoftAssert();
		cloneScript(cloneZOE, zoe, statusType);
		System.out.println("The successful completion of cloning the ZOE Order with a Out For Delivery status has been accomplished.");	
	}
	
	// Created by - Naveen S on 05-July-2023
	// This method is to verify that users are able to clone ZOE order that are in a Delivered status.
	public static void cloneZOEwithDeliveredStatus(HashMap<String, String> cloneZOE) throws Throwable {
		System.out.println("Orders - ZOE : Delivered Status");
		String zoe = cloneZOE.get("SalesDocumentTypeZOE");
		String statusType = cloneZOE.get("StatusDelivered");
		softAssert = new SoftAssert();
		cloneScript(cloneZOE, zoe, statusType);
		System.out.println("The successful completion of cloning the ZOE Order with a Delivered status has been accomplished.");		
	}


	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with Draft Status
	public static void cloneOption(HashMap<String, String> cloneCheckDetails, String salesRecordType, String statusType)
			throws Throwable {
		String order = cloneCheckDetails.get("Order");
		String orderSort = cloneCheckDetails.get("OrderSorting");
		String listViewSelection = cloneCheckDetails.get("ListViewSelection");

		String expectedAccName = "";
		String expectedOrderOrigin = "";
		String actualorderigin = "";
		String actualaccname = "";
		String actualsalesdoc = "";

		closeMultipleTabs();
		try {
			System.out.println(salesRecordType + " and " + statusType + " has Started");
			Thread.sleep(2000);
			selectAppOrObjFromAppLauncher("Object",order);
			new WebDriverWait(driver, explicitWaitTimeOut)
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
			Thread.sleep(3000);
			javaClickByLocator(CreateCloneOrders.listViewOrders);
			Thread.sleep(2000);
			if (driver.findElements(CreateCloneOrders.listViewOrders).size() > 0) {
				screenshot = capture(driver);
				logger.pass(
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are displayed in a list view.");
				assertTrue(driver.findElements(CreateCloneOrders.listViewOrders).size() > 0,
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are displayed in a list view.");
			} else {
				screenshot = capture(driver);
				logger.fail(
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are not displayed in a list view.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				softAssert.fail(
						"<b> Expected Result: </b>The Orders should be displayed in a list view. <br /> <b> Actual Result: </b>The Orders are not displayed in a list view.");
			}
			logger.info("<b>The process of executing the "+salesRecordType+" Order with a "+statusType+" statusType commences at this point.</b>");
			driver.findElement(CreateCloneOrders.searchBar).click();
			Thread.sleep(2000);
			driver.findElement(CreateCloneOrders.searchBar).clear();
			Thread.sleep(2000);
			driver.findElement(CreateCloneOrders.searchBar).sendKeys(listViewSelection);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.selectList);
			new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.filterButton));
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.filterButton);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.removeAllFilter);
			try {
				if (driver.findElement(CreateCloneOrders.saveButton).isDisplayed()) {
					javaClickByLocator(CreateCloneOrders.saveButton);
				}

			} catch (Exception e) {

			}

			new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.addFilter));
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.addFilter);
			javaClickByLocator(CreateCloneOrders.fieldButton);

			javaClickByLocator(CreateCloneOrders.salesDocumentType);

			javaClickByLocator(CreateCloneOrders.salesDocvalueButton);
			Thread.sleep(2000);
			javaClickByLocator(By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']//a[@title='"
					+ salesRecordType + "']"));

			javaClickByLocator(CreateCloneOrders.doneButton);
			javaClickByLocator(CreateCloneOrders.addFilter);
			javaClickByLocator(CreateCloneOrders.fieldButton);
			Thread.sleep(2000);
			scrollToWebElement(driver.findElement(CreateCloneOrders.statusFieldButton));
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.statusFieldButton);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.statusVlaueButton);
			javaClickByLocator(By.xpath("//a[text()='" + statusType + "']"));
			javaClickByLocator(CreateCloneOrders.statusVlaueButton);
			Thread.sleep(2000);
			javaClickByLocator(CreateCloneOrders.doneButton);
			try {
				if (driver.findElement(CreateCloneOrders.saveButton).isDisplayed()) {
					javaClickByLocator(CreateCloneOrders.saveButton);
				}

			} catch (Exception e) {
			}
			new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.addFilter));
			Thread.sleep(6000);
			

			if (driver.findElements(CreateCloneOrders.selectOrderSpan).size() > 0) {
				screenshot = capture(driver);
				logger.pass(
						"<b> Expected Result: </b>The list view of " + salesRecordType + " Orders with a " + statusType
								+ " statusType should be visible. <br /> <b> Actual Result: </b>The list view of "
								+ salesRecordType + " Orders with " + statusType + " statusType is displayed.",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				assertTrue(driver.findElements(CreateCloneOrders.selectOrder).size() > 0,
						"<b> Expected Result: </b>The list view of " + salesRecordType + " Orders with a " + statusType
								+ " statusType should be visible. <br /> <b> Actual Result: </b>The list view of "
								+ salesRecordType + " Orders with " + statusType + " statusType is displayed.");
			} else {
				screenshot = capture(driver);
				logger.info("List View for Orders  for " + salesRecordType + " with " + statusType + " is not diplayed",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			}

			Thread.sleep(2000);

			if (driver.findElements(CreateCloneOrders.selectOrderSpan).size() > 0) {
				new WebDriverWait(driver, explicitWaitTimeOut)
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
				Thread.sleep(2000);
				javaClickByLocator(CreateCloneOrders.hideFilter);

				new WebDriverWait(driver, Duration.ofSeconds(10))
						.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
				Thread.sleep(2000);

				if ((driver.findElement(CreateCloneOrders.orderSorting).getText()).equals(orderSort)) {
					javaClickByLocator(CreateCloneOrders.orderStartDate);
					new WebDriverWait(driver, Duration.ofSeconds(30))
							.until(ExpectedConditions.visibilityOfElementLocated(CreateCloneOrders.orderTab));
				}
				Thread.sleep(2000);
				expectedAccName = driver.findElement(CreateCloneOrders.expectedAccName).getText();
				javaClickByLocator(CreateCloneOrders.selectOrder);
				Thread.sleep(2000);

				Thread.sleep(2000);
				actualorderigin = driver.findElement(CreateCloneOrders.orderOriginDisplay).getText();
				logger.info("The Order Origin of the selected Order is <b>"+actualorderigin+".</b>");
				actualaccname = driver.findElement(CreateCloneOrders.accNamePage).getText();
				Assert(actualaccname,expectedAccName,  "The selected Order Account Name should be ",
						"The selected Order Account name is ", true);

				actualsalesdoc = driver.findElement(CreateCloneOrders.saleDocType).getText();
				Assert(actualsalesdoc,salesRecordType,  "The selected Order Sales Document Type should be ",
						"The selected Order Sales Document Type is ", true);

				Thread.sleep(2000);

				scrollToWebElement(driver.findElement(CreateCloneOrders.odrHdrBtn));
				Thread.sleep(2000);

				scrollToWebElement(driver.findElement(CreateCloneOrders.orderLayout));
				Thread.sleep(2000);
				try {
					if ((driver.findElements(CreateCloneOrders.cloneButton).size() > 0)) {
						screenshot = capture(driver);
						logger.fail(
								"<b>Expected Result :</b> Clone Button Should not be Displayed. <br /> <b>Actual Result :</b> Clone Button is Displayed",MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
						Assert.fail(
								"<b>Expected Result :</b> Clone Button Should not be Displayed. <br /> <b>Actual Result :</b> Clone Button is Displayed");
					} else {
						screenshot = capture(driver);
						logger.pass(
								"<b>Expected Result :</b> Clone Button Should not be Displayed. <br /> <b>Actual Result :</b> Clone Button is not Displayed",MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
						assertTrue(!(driver.findElement(CreateCloneOrders.cloneButton).isDisplayed()),
								"<b>Expected Result :</b> Clone Button Should not be Displayed. <br /> <b>Actual Result :</b> Clone Button is not Displayed");

					}
				} catch (Exception e) {

				}

				logger.info("The process of executing the <b>"+salesRecordType+"</b> Order with a <b>"+statusType+"</b> statusType terminates at this point.");
			} else {
				screenshot = capture(driver);
				logger.info("Currently There are No Sales Orders present for" + salesRecordType + "and" + statusType,MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			}
			System.out.println(salesRecordType + " and " + statusType + " is done Successfully");

		} catch (Exception e) {
			screenshot = capture(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
			Assert.fail(e.getMessage());
		}
		softAssert.assertAll();
		status = logger.getStatus();

	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with Draft Status
	public static void checkCloneOrderWithDraftStatus(HashMap<String, String> CheckCloneDraftDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with Draft Status");
		String zmkt = CheckCloneDraftDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckCloneDraftDetails.get("SalesDocumentTypeZRET");
		String zor = CheckCloneDraftDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckCloneDraftDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckCloneDraftDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckCloneDraftDetails.get("StatusDraft");
		
		softAssert = new SoftAssert();
		cloneOption(CheckCloneDraftDetails, zmkt, statusType);
		cloneOption(CheckCloneDraftDetails, zret, statusType);
		cloneOption(CheckCloneDraftDetails, zor, statusType);
		cloneOption(CheckCloneDraftDetails, zfre, statusType);
		cloneOption(CheckCloneDraftDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with Draft Status completed successfully");

	}

	// Created by - H S Krishna Prasad on 04-JULY-2023	
	//This method is to verify the Visibility of Clone option in orders with Pending Status
	public static void checkCloneOrderWithPendingStatus(HashMap<String, String> CheckClonePendingDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with Pending Status");
		String zmkt = CheckClonePendingDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckClonePendingDetails.get("SalesDocumentTypeZRET");
		String zor = CheckClonePendingDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckClonePendingDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckClonePendingDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckClonePendingDetails.get("StatusPending");
		
		softAssert = new SoftAssert();
		cloneOption(CheckClonePendingDetails, zmkt, statusType);
		cloneOption(CheckClonePendingDetails, zret, statusType);
		cloneOption(CheckClonePendingDetails, zor, statusType);
		cloneOption(CheckClonePendingDetails, zfre, statusType);
		cloneOption(CheckClonePendingDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with Pending Status completed successfully");

	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with In-Error Status
	public static void checkCloneOrderWithInErrorStatus(HashMap<String, String> CheckCloneInErrorDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with In-Error Status");
		String zmkt = CheckCloneInErrorDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckCloneInErrorDetails.get("SalesDocumentTypeZRET");
		String zor = CheckCloneInErrorDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckCloneInErrorDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckCloneInErrorDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckCloneInErrorDetails.get("StatusInError");
		
		softAssert = new SoftAssert();
		cloneOption(CheckCloneInErrorDetails, zmkt, statusType);
		cloneOption(CheckCloneInErrorDetails, zret, statusType);
		cloneOption(CheckCloneInErrorDetails, zor, statusType);
		cloneOption(CheckCloneInErrorDetails, zfre, statusType);
		cloneOption(CheckCloneInErrorDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with In-Error Status completed successfully");

	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with Confirmed Status
	public static void checkCloneOrderWithConfirmedStatus(HashMap<String, String> CheckCloneConfirmedDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with Confirmed Status");
		String zmkt = CheckCloneConfirmedDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckCloneConfirmedDetails.get("SalesDocumentTypeZRET");
		String zor = CheckCloneConfirmedDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckCloneConfirmedDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckCloneConfirmedDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckCloneConfirmedDetails.get("StatusConfirmed");
		
		softAssert = new SoftAssert();
		cloneOption(CheckCloneConfirmedDetails, zmkt, statusType);
		cloneOption(CheckCloneConfirmedDetails, zret, statusType);
		cloneOption(CheckCloneConfirmedDetails, zor, statusType);
		cloneOption(CheckCloneConfirmedDetails, zfre, statusType);
		cloneOption(CheckCloneConfirmedDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with Confirmed Status completed successfully");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with In-Settlement Status
	public static void checkCloneOrderWithInSettlementStatus(HashMap<String, String> CheckCloneInSettlementDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with In-Settlement Status");
		String zmkt = CheckCloneInSettlementDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckCloneInSettlementDetails.get("SalesDocumentTypeZRET");
		String zor = CheckCloneInSettlementDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckCloneInSettlementDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckCloneInSettlementDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckCloneInSettlementDetails.get("StatusSettlement");
		
		softAssert = new SoftAssert();
		cloneOption(CheckCloneInSettlementDetails, zmkt, statusType);
		cloneOption(CheckCloneInSettlementDetails, zret, statusType);
		cloneOption(CheckCloneInSettlementDetails, zor, statusType);
		cloneOption(CheckCloneInSettlementDetails, zfre, statusType);
		cloneOption(CheckCloneInSettlementDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with In-Settlement Status completed successfully");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with Not Delivered Status
	public static void checkCloneOrderWithNotDeliveredStatus(HashMap<String, String> CheckCloneNotDeliveredDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with Not Delivered Status");
		String zmkt = CheckCloneNotDeliveredDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckCloneNotDeliveredDetails.get("SalesDocumentTypeZRET");
		String zor = CheckCloneNotDeliveredDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckCloneNotDeliveredDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckCloneNotDeliveredDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckCloneNotDeliveredDetails.get("StatusNotDelivered");
		
		softAssert = new SoftAssert();
		cloneOption(CheckCloneNotDeliveredDetails, zmkt, statusType);
		cloneOption(CheckCloneNotDeliveredDetails, zret, statusType);
		cloneOption(CheckCloneNotDeliveredDetails, zor, statusType);
		cloneOption(CheckCloneNotDeliveredDetails, zfre, statusType);
		cloneOption(CheckCloneNotDeliveredDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with Not Delivered Status completed successfully");
	}

	// Created by - H S Krishna Prasad on 04-JULY-2023
	//This method is to verify the Visibility of Clone option in orders with Cancelled Status
	public static void checkCloneOrderWithCancelledStatus(HashMap<String, String> CheckCloneCancelledDetails) throws Throwable {
		System.out.println("To Check the Visibility of Clone option in orders with Cancelled Status");
		String zmkt = CheckCloneCancelledDetails.get("SalesDocumentTypeZMKT");
		String zret = CheckCloneCancelledDetails.get("SalesDocumentTypeZRET");
		String zor = CheckCloneCancelledDetails.get("SalesDocumentTypeZOR");
		String zfre = CheckCloneCancelledDetails.get("SalesDocumentTypeZFRE");
		String zoe = CheckCloneCancelledDetails.get("SalesDocumentTypeZOE");
		
		String statusType = CheckCloneCancelledDetails.get("StatusCancelled");
		
		softAssert = new SoftAssert();
		cloneOption(CheckCloneCancelledDetails, zmkt, statusType);
		cloneOption(CheckCloneCancelledDetails, zret, statusType);
		cloneOption(CheckCloneCancelledDetails, zor, statusType);
		cloneOption(CheckCloneCancelledDetails, zfre, statusType);
		cloneOption(CheckCloneCancelledDetails, zoe, statusType);
		System.out.println("To Check the Visibility of Clone option in orders with Cancelled Status completed successfully");
	}

}