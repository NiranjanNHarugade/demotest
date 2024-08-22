package Pages;

import org.openqa.selenium.By;

public class CreateSalesOrder {

	public final static By reviewOpenSalesOrdersPopUp = By.xpath("//h2[text()='Review Open Sales Orders']");
	public final static By warningClose = By.xpath("//button[@title='Close']/ancestor::div[@role='alertdialog']//button");
	public final static By reviewOpenOutboundCases = By.xpath("//h2[text()='Review Open Outbound Cases ']");
	public final static By dismissBtnForSalesOrder = By.xpath("//h2[text()='Review Open Sales Orders']//ancestor::header//following-sibling::footer//button");
	public final static By dismissBtn = By.xpath("//h2[text()='Review Open Outbound Cases ']//ancestor::header//following-sibling::footer//button[text()='Dismiss']");
	public final static By firstBusinessContact = By.xpath("(//th//lightning-primitive-cell-factory[@data-label='Business Contact']//span//span)[1]");
	public final static By orderHeaderTab = By.xpath("//a[text()='ORDER HEADER']");
	public final static By salesOrderType = By.xpath("//label[text()='Sales Document Type']");
	public final static By salesOrderTypeValue = By.xpath("//label[text()='Sales Document Type']/parent::lightning-combobox//span[text()='Standard Sales Order(ZOR)']");
	public final static By closeContact = By.xpath("//button[@title='Close Contacts']");
	public final static By discardBtn = By.xpath("//button[text()='Discard Changes']");
	public final static By saveChangesPopup = By.xpath("//h1[text()='Save changes in order before closing?']");
	public final static By orderContactName = By.xpath("//label[contains(text(),'Order Contact Name')]");
	public final static By orderContactNameSearchfield = By.xpath("//label[contains(text(),'Order Contact Name')]//parent::div//input[@placeholder='Search Contacts...']");
	public final static By shippingCartTab = By.xpath("//a[text()='SHOPPING CART']");
	public final static By cartSection = By.xpath("//a[text()='CART']");
	public final static By searchContactsdynamicdropDown = By.xpath("//div[@class='slds-tabs_default__content slds-show']//input[@placeholder='Search Contacts...']");
	public final static By LineItemNo = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Line Item no']");
	public final static By materialDescription = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Material Description']");
	public final static By materialId = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Material Id']");
	public final static By packageSize = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='packageSize']");
	public final static By tradeMark = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Trade Mark']");
	
	
	public final static By qty = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Qty']");
	public final static By avg = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='AVG']");
	public final static By caseCost = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Case Cost']");
	public final static By lineItemCost = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Line Item Cost']");
	public final static By lineItemSaving = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Line Item Saving']");
	public final static By unit = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Unit']");
	public final static By itemType = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Item Type']");
	public final static By usage = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Usage']");
	public final static By attemptResults = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Attempt Results']");
	public final static By notes = By.xpath("//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//div[@title='Notes']");
	public final static By productCatalog = By.xpath("(//a[text()='CART']//ancestor::lightning-tab-bar//following-sibling::div//button[text()='Product Catalog'])[1]");
	public final static By tradeMarkInProductCatalog = By.xpath("//select[@name='Trademark__c']");
	public final static By packType = By.xpath("//select[@name='PackType__c']");
	public final static By qtytextBox = By.xpath("(//h1[contains(text(),'Product Catalog')]/ancestor::div[@class='slds-modal__container']//table//tbody//input[@name='quantity'])[1]");
	public final static By firstProductName = By.xpath("(//h1[contains(text(),'Product Catalog')]/ancestor::div[@class='slds-modal__container']//table//tbody//input[@name='quantity'])[1]/ancestor::td//following-sibling::td[1]//div//lightning-formatted-rich-text");
	public final static By updateShoppingCart = By.xpath("//button[@title='Update Shopping Cart']");
	public final static By closeToastpopup = By.xpath("//div[@class='slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage']//button[@title='Close']");
	public final static By closeBtn = By.xpath("//button[text()='Close']");
	public final static By calculatePrice = By.xpath("(//div[@class='slds-tabs_default__content slds-show']//button[@title='Calculate'])[1]");
	public final static By submitBtn = By.xpath("//button[text()='Submit']");
	public final static By orderNumbervalue = By.xpath("//span[@title='Order Number']");
	public final static By orderTabNumber = By.xpath("//span[@title='Order']//following-sibling::span[@class='title slds-truncate']");
	public final static By orderstatus = By.xpath(
			"//label[text()='Order Status']/ancestor::div[@class='slds-form-element slds-form-element_horizontal']//button");
	public final static By orderStatusValue = By
			.xpath("//div[@title='Order Status']/ancestor::li//lightning-formatted-rich-text");
	public final static By ECCOrderNumberValue = By
			.xpath("//div[@title='ECC Order Number']/ancestor::li//lightning-formatted-rich-text");
	public final static By qtyTextField = By.xpath(
			"//div[@class='slds-tabs_default__content slds-show']//li[@title='CART']/ancestor::li//table//tbody//tr//td[@data-label='qty']//input");
	public final static By cartTableRows = By.xpath(
			"//div[@class='slds-tabs_default__content slds-show']//li[@title='CART']/ancestor::li//table//tbody//tr");
	public final static By closeCreateCaseTab = By.xpath("//span[text()='Close Create Sales Order']//ancestor::button");
	
	
	
	
	
}
