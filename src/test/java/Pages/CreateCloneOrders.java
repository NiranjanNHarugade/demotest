package Pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;

public class CreateCloneOrders {
	//Created by KP
		public final static By listViewOrders = By.xpath("//button[@title='Select a List View: Orders']");
		public final static By searchBar = By.xpath("//div[@class='slds-form-element slds-lookup forceVirtualAutocompleteMenu']//input");
		public final static By recentListView = By.xpath("//div[@class='list uiAbstractList forceVirtualAutocompleteMenuList']");
		public final static By filterButton = By.xpath("//button[@title='Show filters']");
		public final static By hideFilter	= By.xpath("//div[contains(@class,'slds-page-header--object-home slds-page-header')]//button[@title='Hide filters']");
		public final static By selectList  =  By.xpath("//mark[text()='Copy of Automation_List_All_Orders']/ancestor::li[contains(@class,'slds-dropdown__item has-icon--left   ')]//a");
		public final static By removeAllFilter = By.xpath("//a[text()='Remove All']");
		public final static By addFilter = By.xpath("//a[text()='Add Filter']");
		public final static By clickOnFilter=By.xpath("//div[text()='New Filter']/ancestor::a");
		public final static By fieldButton = By.xpath("//div[@class='forceFilterPanelCallout']//button//span[text()='Account Name']");
		public final static By salesDocumentType = By.xpath("//span[text()='Sales Document Type']/ancestor::lightning-base-combobox-item[@data-value='SalesDocumentType__c']");
		public final static By salesDocvalueButton = By.xpath("//div[@class='forceFilterPanelCallout']//a");
		public final static By doneButton = By.xpath("//div[@class='forceFilterPanelCallout']//span[text()='Done']//parent::button");
		public final static By statusFieldButton = By.xpath("//span[text()='Status']/ancestor::lightning-base-combobox-item");
		public final static By startedDateFieldButton = By.xpath("//span[text()='Order Start Date']/ancestor::lightning-base-combobox-item");
		public final static By startedDateValueButton = By.xpath("//span[text()='Value']/parent::label/following-sibling::input");
		public final static By operatorButton = By.xpath("//label[text()='Operator']/parent::div//button");
		public final static By greaterThanOperatorButton = By.xpath("//span[text()='greater than']/parent::span/parent::lightning-base-combobox-item");
		public final static By CreatedByFieldButton = By.xpath("//span[text()='Created By Alias']/ancestor::lightning-base-combobox-item");
		public final static By CreatedByValue = By.xpath("//input[contains(@class,'filterTextInput valueInput')]");
		public final static By statusVlaueButton = By.xpath("//div[@class='forceFilterPanelCallout']//a");
		public final static By saveButton = By.xpath("//div[@class='slds-panel__header panelHeader']//button[text()='Save']");
		public final static By cloneButton = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//div[contains(@class,'slds-page-header slds-page')]//button[@title='Clone Order Flow']");
		public final static By yesButton = By.xpath("//footer[@class='slds-scope footerContainer slds-grid slds-grid_align-spread']//button[text()='Yes']");
		public final static By closeFilter = By.xpath("//div[@class='slds-panel__header panelHeader']//button[@title='Close Filters']");
		public final static By orderTab = By.xpath("//div[@class='slds-grid listViewContainer safari-workaround']//span[@title='Order Number']");
		public final static By selectOrderSpan = By.xpath("(//div[@class='windowViewMode-maximized active lafPageHost']//div[@class='listViewContent slds-table--header-fixed_container']//tbody//th//span)[1]");
		public final static By selectOrder = By.xpath("(//div[@class='windowViewMode-maximized active lafPageHost']//div[@class='listViewContent slds-table--header-fixed_container']//tbody//th//a)[1]");
		public final static By orderStartDate = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//div[@class='slds-grid listDisplays safari-workaround-anchor']//span[@title='Order Start Date']/parent::a");
		public final static By orderSorting = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//th[@aria-label='Order Start Date']//div/child::span[@class='slds-assistive-text']");
		public final static By clonePopUp = By.xpath("//div[@class='slds-modal__container']//h2[contains(text(),'Clone Order')]");
		public final static By orderNum = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//div[@class='slds-page-header__row']//span[@title='Order Number']");
		public final static By saleDocType = By.xpath("(//div[contains(@class,'windowViewMode-maximized')]//lightning-formatted-rich-text[@class='slds-rich-text-editor__output']//span)[2]");
		public final static By accNamePage = By.xpath("(//div[contains(@class,'windowViewMode-maximized')]//lightning-formatted-rich-text[@class='slds-rich-text-editor__output']//span)[1]");
		public final static By orderOriginDisplay = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[text()='Order Origin']/parent::li/child::div[@class='slds-truncate']//span");
		public final static By orderContactDisplay = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[text()='Order Contact Name']/parent::li/child::div[@class='slds-truncate']//span");
		public final static By delDisShipBtn= By.xpath("//div[contains(@class,'windowViewMode-maximized')]//a[text()='DELIVERY, DISPATCH & SHIPMENT']");
		public final static By delInfoTab= By.xpath("//div[contains(@class,'windowViewMode-maximized')]//span[text()='Delivery Information']");
		public final static By oneTimeDeliveryNotes = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[@class='slds-tabs_default__content slds-show']//textarea[@name='OneTimeDeliveryNotes__c']");
		public final static By oneTimeDispatchNotes = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[@class='slds-tabs_default__content slds-show']//textarea[@name='OneTimeDispatchNotes__c']");
		public final static By approvedBy = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[@class='slds-tabs_default__content slds-show']//input[@name='OrderedApprovedBy__c']");
		public final static By odrHdrBtn = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//a[text()='ORDER HEADER']");
		public final static By tableRows = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[@class='slds-grid slds-gutters slds-scrollable']//table[contains(@class,'a slds-table  slds-table')]//tr");
		public final static By shopCartBtn = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//a[contains(text(),'SHOPPING CART')]");
		public final static By orderLayout = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[@class='slds-page-header__row']");
		public final static By statusOrder = By.xpath("//div[contains(@class,'windowViewMode-maximized')]//div[text()='Order Status']/parent::li/child::div[@class='slds-truncate']//span");
		public final static By globalSearchButton = By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']");
		public final static By globalSearch = By.xpath("//lightning-input[@class='saInput slds-grow slds-form-element']//div[contains(@class,'slds-form-element__control slds-grow')]//input");
		public final static By expectedAccName = By.xpath("((//div[@class='windowViewMode-maximized active lafPageHost']//table[contains(@class,'slds-table forceRecord')]//tbody//td)[2])//a");
		public final static By expectedCont = By.xpath("((//div[contains(@class,'windowViewMode')]//table[contains(@class,'slds-table ')]//tbody//td)[9])//span[contains(@class,'slds-truncate uiOutputText ')]");
		public final static By expectedContSpan = By.xpath("((//div[@class='windowViewMode-maximized active lafPageHost']//table[contains(@class,'slds-table forceRecord')]//tbody//td)[9])//span");
		public final static By expectedContName = By.xpath("((//div[@class='windowViewMode-maximized active lafPageHost']//table[contains(@class,'slds-table forceRecord')]//tbody//td)[9])//a");
		public final static By expectedOrdOrig = By.xpath("(((//div[@class='windowViewMode-maximized active lafPageHost']//table[contains(@class,'slds-table forceRecord')]//tbody//td)[8])//span)[2]");
		public final static By feeWaiver = By.xpath("//span[@title='Fee Waivers']");
	    public final static By cloneSearch = By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']");
	    public final static By material = By.xpath("(((//div[@title='Material Description'])[1]/ancestor::thead)/following-sibling::tbody//th[2])/div");
	    public final static By cloneMaterial = By.xpath("(((//div[@title='Material Description'])[1]/ancestor::thead)/following-sibling::tbody//th[2])/div");
	    public final static By quantity = By.xpath("(((//div[@title='Qty'])[1]/ancestor::thead)/following-sibling::tbody//td[4])");
	    public final static By createdBy = By.xpath("//label[text()='Created By']/parent::div//input");
	    public final static By lineItemNo = By.xpath("//div[@class='slds-grid slds-gutters slds-scrollable']//div[@title='Line Item no']");
	    public final static By shopCartTable = By.xpath("//a[text()='SHOPPING CART']/ancestor::div[@class='slds-m-around_small cCONA_SalesOrderPageOrderDetailsForm']//div[@class='tableFixHead']");
	    public final static By promoAndSuggest = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//span[text()='Promotions And Suggestions']/parent::button");
		public final static By CartTab = By.xpath("//a[text()='CART']");
		public final static List<String> shopingCartFields = Arrays.asList("PO Required Flag", "PO Number", "PO Type","PO Date"); //changed and erased
		public final static List<String> shoppingCartButtons = Arrays.asList("Calculate Price", "Product Catalog");//changed and erased
		public final static List<String> deliveryInformationFields = Arrays.asList("Delivery Date", "Planning Calendar",
				"Shipping Condition", "Order Dispatch Cutoff", "Master Route", "Perm. Delivery Notes",
				"Ordered/Approved By", "Requested Delivery Date", "Special Delivery Request", "Off Week Policy",
				"Off Day Policy", "CO2 Rack Policy", "One Time Driver Instructions", "Delivery Type");
		public final static List<String> dispatchInformationFields = Arrays.asList("Perm. Dispatch Notes", "One-Time Dispatch Notes");//changed and removed fields
		public final static By warningMsgVisitPlan = By.xpath("//div[@role='alertdialog']//div[text()='warning!']");
		public final static By closeWarningMsgVisitPlan = By.xpath("//button[@title='Close']/ancestor::div[@role='alertdialog']//button");
		public final static List<String> errortab = Collections.unmodifiableList(Arrays.asList("Continue Editing","Discard Changes"));
		public final static By discard = By.xpath("//button[text()='Discard Changes']");
		public final static By closeSalesOrder = By.xpath("//button[@title='Close Create Sales Order']");
		public final static By reviewOpenSalesOrdersPopUp = By.xpath("//h2[text()='Review Open Sales Orders']");
		public final static By reviewOutboundCasePopup = By.xpath("//h2[text()='Review Open Outbound Cases ']");
		public final static By dismissBtn = By.xpath("//button[@class='slds-button slds-button_brand']/ancestor::footer/button");
		public final static By errorMessage = By.xpath("//span[text()='Error !']");
		public final static By FinishButton = By.xpath("//button[text()='Finish']");
		public final static By orderOriginPicklist = By.xpath("//span[text()='Order Origin']/ancestor::lightning-base-combobox-item");
		public final static By notEqualtoOperator = By.xpath("//span[text()='not equal to']/parent::span/parent::lightning-base-combobox-item");
		public final static By myCokeOrders = By.xpath("//a[text()='myCoke360']");
	
	}