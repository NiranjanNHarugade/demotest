package Pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;

public class Accounts {

	public final static By decisionTreeEnquiry = By.xpath("//select[@class='slds-select']");
	public final static By CIwidget = By.xpath("//span[contains(text(),'Customer Instructions')]/ancestor::article");
	public final static By openESTCSTHistory = By.xpath("//b[text()='Open EST/CST History']");
	public final static By equipmentTypeValue = By.xpath("(//select[@class='slds-select'])[1]");
	public final static By detailsTab = By.xpath("//a[text()='Details']");
	public final static By carrot = By.xpath("//button[@class='slds-button slds-button_icon-border-filled']");
	public final static By createSalesOrderBtn = By.xpath("//button[text()='Create Sales Order']");
	public final static By CI_Object = By.xpath("//span[contains(text(),'Customer Instructions')]//parent::a");
	public final static By Close_CI = By.xpath("//button[@title='Close Customer Instructions']");
	public final static By contact = By.xpath("//span[@title='Contacts']");
	public final static By instructionsnewBtn = By.xpath("//div[contains(text(),'Customer Instructions')]/ancestor::header//button[text()='New']");
	public final static By dropdown = By.xpath("//label[text()='Type']/following::button[@role='combobox']");
	public final static By note = By.xpath("//textarea[contains(@id,'input')]");
	public final static By actualnote = By.xpath("//records-record-layout-item[@field-label='Notes']//lightning-formatted-text");
	public final static By closeinstruction = By.xpath("//button[contains(@title,'Close CI') ]");
	
	//Outbound
	public final static By FirstOrder_Value = By.xpath("(//div[@class='windowViewMode-maximized active lafPageHost']//span[text()='Order Number']//ancestor::thead//following-sibling::tbody//tr//a//span[@class='slds-truncate']/slot)[1]");
	public final static By orderPage = By.xpath("//h1[text()='Orders']");
	public final static By CloseOrdersBtn = By.xpath("//button[@title='Close Orders']");
	public final static By casesWidget = By.xpath("//h2//span[text()='Cases']");
	public final static By outboundRadioBtn = By.xpath("//h2[text()='New Case']/ancestor::div[@class='inlinePanel oneRecordActionWrapper']//span[text() = 'Outbound']/preceding-sibling::input");
	public final static By next = By.xpath("//button/span[text()='Next']"); 
	public final static By outboundPopup = By.xpath("//records-record-layout-item[@field-label='Case Record Type']//span[text()='Outbound']");
	public final static By caseNumberField = By.xpath("//span[text()='Case Number']/ancestor::records-record-layout-item[@field-label='Case Number']");
	public final static By caseNumberValue = By.xpath("//span[text()='Case Number']/ancestor::records-record-layout-item[@field-label='Case Number']//lightning-formatted-text");
	public final static By orderForParentAccountWidget = By.xpath("//span[contains(text(),'Orders for Parent Account ')]");
	public final static By orderWidgetViewBtn = By.xpath("//span[contains(text(),'Orders for Parent Account ')]//ancestor::article//a[contains(text(),'View')]");
	public final static By orderForParentAccountWidgetSize = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//div[text()='Order Number']//ancestor::thead//following-sibling::tbody//tr//a");
	public final static By firstOrderId = By.xpath("(//div[@class='windowViewMode-maximized active lafPageHost']//div[text()='Order Number']//ancestor::thead//following-sibling::tbody//tr//a)[1]");
	public final static By OrderID = By.xpath("//span[text()='Order']//following-sibling::span[@title='Order Number']");
	public final static By outboundCallDispositionSection = By.xpath("//span[text()='Outbound Call Disposition']//ancestor::button");
	public final static By closeOrdersForParent = By.xpath("//button[@title='Close Orders for Parent Account']");
	
	
	public final static By saveBtn = By.xpath("//button[@name='SaveEdit']");
	public final static By nextBtn = By.xpath("//button[text()='Next']");    
	public final static By newBtn = By.xpath("//button[text()='New']");
	public final static By newcase = By.xpath("//button[text()='New' and @name='NewCase']");
	//EST
	
	public final static By createCase = By.xpath("//a[@data-label='Create Case']");
	public final static By moreTab = By.xpath("//slot[@name='main']//div[@class='slds-tabs_card']//button[@title='More Tabs']");
	public final static By createCaseMoreTab = By.xpath("//span[text()='Create Case']");
	public final static By decisiontrue = By.xpath("//span[text()='Decision Tree']");
	public final static By caseEditpopUpTitle = By.xpath("//h2[contains(text(),'Edit')]");

	public final static By detailedDescriptionTxtBox = By.xpath("//span[text()='Detailed Description']/ancestor::div[@class='slds-form-element slds-hint-parent']//textarea");
	public final static By saveBt = By.xpath("//button[@name='CancelEdit']");
	public final static By showAllLinkText = By.xpath("//a[contains(text(),'Show All')]");
	public final static By equipmentLinkText = By.xpath("//h2[text()='Related List Quick Links']/ancestor::article[@class='slds-card slds-card_boundary']//span[contains(text(),'Equipment')]/ancestor::a");
	public final static By closeEquipmentTab = By.xpath("//button[@title='Close Equipment']");
	
	
	public final static By openESTCSTHostory = By.xpath("//b[text()='Open EST/CST History']");
	public final static By equipmentTypeDetail = By.xpath("(//select[@class='slds-select'])[2]");
	public final static By pricingQuestion = By.xpath("//div[@class='flowruntime-input-label']");
	public final static By Dropdown = By.xpath("//div[@class='slds-select_container']//select");
	
	public final static By detailedDescription = By.xpath("//label[text()='Detailed Description']");
	public final static By detailedDescriptionTextBox = By.xpath("//label[text()='Detailed Description']//following-sibling::div//textarea");
	public final static By caseRecordType = By.xpath("//h2[contains(text(),'Edit')]/ancestor::records-lwc-detail-panel//span[text()='Equipment Service Ticket']");

// Alert widget
	public final static By alerton_Accounts = By.xpath("(//article[@data-aura-class='cCONA_AccountCustomAlerts'])[1]//tbody/tr");
	public final static By alerton_case = By.xpath("//records-entity-label[text()='Case']//ancestor::div[@class='slds-col slds-size_1-of-1 row region-header']//following-sibling::div//article[@data-aura-class='cCONA_AccountCustomAlerts']//div//lightning-datatable//table//tbody//tr//th[@data-label='Alert Id']");
	public final static By alertnext = By.xpath("//span[text()='Next']");
	
	
	//Account partners
	public final static By acountPartner = By.xpath("//a//span[contains(text(),'Account Partners')]");
	public final static By acountPartnerPage = By.xpath("//h1[text()='Account Partners']");
	public final static By size = By.xpath("//span[text()='Partner Role']//ancestor::table//tbody//tr");
	public final static By closeAccountPartners = By.xpath("//button[@title='Close Account Partners']");
	public final static By relatedContacts = By.xpath("//a//span[contains(text(),'Related Contacts')]");

//	Pace validation
	public final static By Account_PartnersLink = By.xpath("//a/slot/span[contains(text(),'Account Partners')]");
	public final static By  Account_Partners = By.xpath("//h1[@title='Account Partners']");
	public final static By  PartnerRoles = By.xpath("//th[@data-label='Partner Role']");
	public final static By  UpdateAccount = By.xpath("//input[@placeholder='Search Accounts...']");
	public final static By  AccountNameSnag = By.xpath("//a[text()='Account Name']");
	public final static By  ExclusiveAccessSnag = By.xpath("//div[@class='pageLevelErrors']//li[contains(text(),'unable to obtain exclusive access to this record or 1 records')]");
	public final static By  closeSnag = By.xpath("//button[@title='Close error dialog']");
	public final static By  AccountNameField = By.xpath("//input[@placeholder='Search Accounts...']");
	public final static By  Selectbutton = By.xpath("//button[text()='Select']");

// Active Related contacts
	public final static By  ActiveRelatedContactsWidget = By.xpath("//span[contains(text(),'Active Related Contacts')]/ancestor::article");
	public final static By  ViewAllActiveRelatedContacts = By.xpath("//article[@aria-label='Active Related Contacts']//span[text()='View All']/parent::a");
	public final static By  AllContacts = By.xpath("//article[@aria-label='Active Related Contacts']//article//a//span[@class='slds-truncate']/slot");//
	public final static By  AllContactsARC = By.xpath("//th[@data-label='Business Contact']//a//span[@class='slds-truncate']/slot");
	public final static By  listofcontacts = By.xpath("//article[@aria-label='Active Related Contacts']//records-hoverable-link//a");
	public final static By  ContactDetailsPage = By.xpath("//h1//records-entity-label[text()='Contact']");
	public final static By  PrimaryPhone = By.xpath("(//article[@aria-label='Active Related Contacts']//article)[1]//dt[text()='my-Coke Roles:']");
	public final static By  Email = By.xpath("(//article[@aria-label='Active Related Contacts']//article)[1]//dt[text()='Email:']");
	public final static By  MyCokeRoles = By.xpath("(//article[@aria-label='Active Related Contacts']//article)[1]//dt[text()='my-Coke Roles:']");
	public final static By  Contacts_Relatedtab = By.xpath("//records-entity-label[text()='Contact']/ancestor::flexipage-record-home-template-desktop2//a[text()='Related']");
	public final static By  AddRelationship = By.xpath("//div[@class='actionsWrapper']//a[@title='Add Relationship']");
	public final static By  ContactPriorityPickllist = By.xpath("//label[text()='Contact Priority']/ancestor::span//lightning-base-combobox");
	public final static By  CloseContact = By.xpath("//button[@title='Close Account Contact Relationships']");
	public final static By  RelatedAccounts = By.xpath("//article[@aria-label='Related Accounts']//span[@title='Related Accounts']");
	public final static By  ContactNames = By.xpath("//span[contains(text(),'Active Related Contacts')]/ancestor::article//ul/li//a[@class='slds-truncate textUnderline']");
	public final static By  Primary = By.xpath("//div[@aria-label='Contact Priority']//span[@title='Primary']");
	public final static By  SearchAccounts = By.xpath("//input[@title='Search Accounts']");
	public final static By  AccountResults = By.xpath("//h2[text()='Account Results']");
	public final static By  save = By.xpath("//button[@title='Save']");
	public final static By  closeRealationship = By.xpath("//button[@title='Close New Account Contact Relationship']");
	public final static By  ContactPrioritycol = By.xpath("//td[@data-label='Contact Priority']//lightning-formatted-text");
	

//TC- 312277
	public final static By  relatedContact = By.xpath("//div[@class='grouping']//h2[text()='Related List Quick Links']//ancestor::article//a//slot[contains(text(),'Related Contacts')]");
	public final static By  businessContact = By.xpath("//span[text()='Business Contact']");
	public final static By  firstContact = By.xpath("(//table[@aria-label='Related Contacts']//tbody//th[1]//a)[1]");
	public final static By  ContactDetailsTab = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='Details']");
	public final static By  ContactRelatedTab = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='Related']");
	public final static By  ContactCreateCaseTab = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[text()='Create Case']");
	public final static By  multipleAccountError = By.xpath("//strong[text()='This Contact is related to multiple Accounts.']");


	public final static By  ViewRelationship = By.xpath("//a[@title='View Relationship']");
	public final static By  AccountContactRelationship = By.xpath("//div[text()='Account Contact Relationship']");
	public final static By  ContactpriorityOnContacts = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Contact.ContactPriority__c']//lightning-formatted-text");
	public final static By  ContactpriorityOnrelationship = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.AccountContactRelation.Contact_Priority__c']//lightning-formatted-text");
	public final static By  Relationship_EditCP = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.AccountContactRelation.Contact_Priority__c']//button[@title='Edit Contact Priority']");
	public final static By  Secondary = By.xpath("//lightning-base-combobox-item//span[@title='Secondary']");
	public final static By  Tertiary = By.xpath("//lightning-base-combobox-item//span[@title='Tertiary']");
	public final static By  Contacts_EditCP= By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Contact.ContactPriority__c']//button[@title='Edit Contact Priority']");
	public final static By  CPPickllist = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Contact.ContactPriority__c']//button");
	public final static By  Tab_AccountContactRelationship = By.xpath("//a[@title='Account Contact Relationship | Account Contact Relationship' and @role='tab']");
	public final static By  CallingHours = By.xpath("//span[text()='Contact Center Calling Hours']");
	public final static By  CallingHoursvalue = By.xpath("//span[text()='Contact Center Calling Hours']/ancestor::records-record-layout-item//a");
	public final static By  DayofWeek = By.xpath("//th[@data-label='Day of Week']");
	public final static By  allaccounts = By.xpath("//span[text()='All accounts']/preceding-sibling::span");
	public final static By  ListOfAccounts = By.xpath("//tbody/tr//th//a[@data-refid='recordId']");
	public static final By PrimaryPhoneTextbox =By.xpath("//records-record-layout-item[@field-label='Primary Phone']//input[@name='Phone']");
	
	
	//New busisness contact
	public final static By Newbusisnesscontact = By.xpath("//button[@name='Account.NewBusinessContact']");
	public final static By NewbusisnesscontactPage = By.xpath("//div[@id='wrapper-body']//h2[text()='New Business Contact']");
	public final static By searchcontact = By.xpath("//input[@name='Contact-search-input']");
	public final static By newcontact = By.xpath("//li[contains(@data-target-selection-name,'Contact.NewContact')]//a");
	public final static By BusnessContact =By.xpath("//p[@title='Business Phone']/parent::div//lightning-formatted-phone/a");
	public final static By FieldsOnContactsDetailsPage =By.xpath("//div[contains(@data-target-selection-name,'sfdc:RecordField.Contact')]//dt");
	public final static By ToastMessage = By.xpath("//span[@data-aura-class='forceActionsText']");
	public final static By PrimaryPhoneField = By.xpath("//div[contains(@data-target-selection-name,'PrimaryPhoneType')]");
	public final static By SecondaryPhoneField = By.xpath("//div[contains(@data-target-selection-name,'SecondaryPhoneType')]");

	
	
	public final static By PrimaryContact =By.xpath("//span[text()='Primary']/ancestor::tr//th[@data-label='Business Contact']//a");
	public final static By SecondaryContact =By.xpath("//span[text()='Secondary']/ancestor::tr//th[@data-label='Business Contact']//a");
	public final static By TertiaryContact =By.xpath("//span[text()='Tertiary']/ancestor::tr//th[@data-label='Business Contact']//a");
	public final static By EquipmentConfirmationTickit = By.xpath("//h2[text()='New Case']/ancestor::div[@class='inlinePanel oneRecordActionWrapper']//span[text() = 'Equipment Confirmation Service Ticket']/preceding-sibling::input");
	public final static By EquipmentConfirmationTickitpage = By.xpath("//records-record-layout-item[@field-label='Case Record Type']//span[text()='Equipment Confirmation Service Ticket']");
	






}
