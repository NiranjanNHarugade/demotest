package Pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;

import Utilities.CommonFunctions;

public class Cases {

	 public final static By record = By.xpath("(//span[text()='Case Record Type']/ancestor::records-record-layout-item//span)[3]");
	 public final static By colSize = By.xpath("//div[contains(@class,'slds-col slds-size_1-of-1 col')]");
	
	// Task widget
	 public final static By createTask = By.xpath("//span[text()='Create a task...']");
	 public final static By priority = By.xpath("//span[text()='New Task']//ancestor::div[@role='tablist']//following-sibling::section//span[text()='Priority']");
	 public final static By subject = By.xpath("//label[text()='Subject']//following-sibling::div//input");
	 public final static By saveBtn = By.xpath("//a[@data-tab-name='NewTask']//ancestor::ul/parent::div//following-sibling::section/parent::div//button[@data-aura-class='uiButton']//span[text()='Save']");
	 public final static By openTaskForParentAccountWidget = By.xpath("//span[contains(text(),'Open Tasks for Parent Account')]");
	
 //Alert
	 public final static By alertname = By.xpath("//td[@data-label='Alert Name']");
	 public final static By viewall = By.xpath("(//footer[@class='slds-card__footer']/a[text()='View All'])[2]");
	 public final static By status = By.xpath("//span[@class='slds-truncate' and @title='Status']");
	 public final static By CSTbtn = By.xpath("//span[text() = 'Customer Service Ticket']");
	 
	 
	 //CSTcase
	public final static By CSTPopup = By.xpath("//h2[text()='New Case: Customer Service Ticket']");
	public final static By callType = By.xpath("//label[contains(text(),'Call Type')]//following-sibling::div//button");
	public final static By CSTProblemReported = By.xpath("//label[contains(text(),'CST Problem Reported')]//following-sibling::div//button");
	public final static By assignedToField = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//span[text()='Assigned To']");
	public final static By assignedToFieldValue = By.xpath("//records-record-layout-item[@field-label='Assigned To']//lightning-formatted-text");
	public final static By assignedToEmailFieldValue = By.xpath("//records-record-layout-item[@field-label='Assigned To Email']//div//emailui-formatted-email-wrapper");
	public final static By caseNo = By.xpath("//span[text()='Case Number']/ancestor::records-record-layout-item[@field-label='Case Number']//lightning-formatted-text");
	public final static By ContactInformationSection = By.xpath("//span[text()='Contact Information']");
	public final static By CSTProblemReportedFieldValue = By.xpath("//records-record-layout-item[@field-label='CST Problem Reported']//div//lightning-formatted-text");
	public final static By cst_tip = By.xpath("//lightning-formatted-rich-text//p/strong");
	public final static By CSTProblemReported_Picklist = By.xpath("//label[text()='CST Problem Reported']/parent::lightning-combobox/descendant::lightning-base-combobox-item");
	public static final By Casenext = By.xpath("//span[text()='Next']/parent::button");
	public static final By CancelCase = By.xpath("//button[@name='CancelEdit']");

//Accounts Attribute
	public final static By RelatedTab = By.xpath("//records-entity-label[text()='Case']/ancestor::one-record-home-flexipage2//a[text()='Related']");
	public final static By quickactionlayout = By.xpath("//div[@data-aura-class= 'forceQuickActionLayout']");
	public final static By AccountAttribute = By.xpath("//a[text()='Account Attributes']");
	public final static By customerinstruction = By.xpath("//records-entity-label[text()='Case']/ancestor::one-record-home-flexipage2//span[contains(text(),'Customer Instructions')]");
	public final static By newCase = By.xpath("//button[@name='NewCase']");
	public final static By DetailsTab = By.xpath("//records-entity-label[text()='Case']//ancestor::flexipage-component2//following-sibling::flexipage-component2[@data-component-id='flexipage_tabset']//a[text()='Details']");



	//Chatter box for CST
	public final static By chatterTab = By.xpath("//records-entity-label[text()='Case']//ancestor::flexipage-component2//following-sibling::flexipage-component2[@data-component-id='flexipage_tabset']//a[text()='Chatter']");
	public final static By shareAnUpdate = By.xpath("//lightning-tabset//span[text()='Share an update...']/parent::button");
	public final static By shareAnUpdateTextBox = By.xpath("//div[@data-placeholder='Share an update...']//p");
	public final static By shareBtn = By.xpath("//button[text()='Share']");
	public final static By searchThisFeed = By.xpath("//input[@placeholder='Search this feed...']");

	//Chatter box for OB
	public final static By DetailsTabOB = By.xpath("//records-entity-label[text()='Case']/ancestor::one-record-home-flexipage2//a[text()='Details']");
	public final static By chatterTabOB = By.xpath("//records-entity-label[text()='Case']/ancestor::one-record-home-flexipage2//a[text()='Chatter']");
	public final static By postBtn = By.xpath("//flexipage-component2[@data-component-id='forceChatter_recordFeedContainer']//span[text()='Post']/ancestor::a");
	public final static By refreshTab = By.xpath("//span[text()='Refresh Tab']");
	public final static By moreTab = By.xpath("//div[@class='cuf-compactRecordFeedContainer supportCompactRecordFeedContainerDesktop']//li[@class='tabs__item uiTabOverflowMenuItem']//a[text()='More']");
	public final static By postBtnMoreTab = By.xpath("//div[@class='cuf-compactRecordFeedContainer supportCompactRecordFeedContainerDesktop']//li[@class='tabs__item uiTabOverflowMenuItem']//a[text()='Post']");


	public final static By OtherContactTypefield = By.xpath("//records-record-layout-item[@field-label='Other Contact Type']//button");
	public final static By OtherContactType = By.xpath("//label[text()='Other Contact Type']/ancestor::lightning-combobox//button[@role='combobox']");
	public final static List<String> ExpectedValues = Collections.unmodifiableList(Arrays.asList( "Bottler", "Consumer", "NAOU"));

	public final static By CVM_ListView = By.xpath("//h2/span[text()='Code Value Mappings']/following-sibling::span");
	public final static By All_ListView = By.xpath("//span[text()='All']/ancestor::a");
	public final static By ServiceData = By.xpath("//button/span[text()='Service Data']");
	public final static By ProgramID = By.xpath("//span[text()='Program ID']");
	public final static By ProgramIDValue = By.xpath("//span[text()='Program ID']/parent::div/following-sibling::div//lightning-formatted-text");
	public final static By listview = By.xpath("//span[text()='Code Value Mappings']/following-sibling::span[text()='Recently Viewed']");
	public final static By Cvmsearch = By.xpath("//input[@name='CodeValueMapping__c-search-input']");
	public final static By EquipmentType = By.xpath("//span[text()='Equipment Type']/parent::div/following-sibling::div//lightning-formatted-text");
	public final static By EquipmentDetails = By.xpath("//span[text()='Equipment Type Detail']/parent::div/following-sibling::div//lightning-formatted-text");
	public final static By ESTProblemReported = By.xpath("//span[text()='EST Problem Reported']/parent::div/following-sibling::div//lightning-formatted-text");
	public final static By BillableCheckBox = By.xpath("//records-record-layout-item[@field-label='Billable']//lightning-input[@checked]");
	public final static By ContactInfoSection = By.xpath("//span[text()='Contact Information']");
	public final static By RelaventForFeeCharge  = By.xpath("//span[text()='Relevant For Fee Charge']");
	public final static By RelaventForFeeChargeValue  = By.xpath("//span[text()='Relevant For Fee Charge']/parent::div/following-sibling::div//lightning-formatted-text");

	public final static By CallListName  = By.xpath("//div/p[@title='Call List Name']");
	public final static By RelatedList_Message  = By.xpath("//div[@data-aura-class='forceRelatedListDesktop']//span[@title='Message']");
	public final static By CaseRecord_Outbound  = By.xpath("//records-record-layout-item[@field-label='Case Record Type']//span[text()='Outbound']");
	public final static By MessageField_CaseDetails  = By.xpath("//records-record-layout-item[@field-label='Message']//span[text()='Message']");
	public final static By Edit_Message  = By.xpath("//button[@title='Edit Message']");
	public final static By MessageTextBox  = By.xpath("//input[@name='Message__c']");
	public final static By MessageFieldValue  = By.xpath("//records-record-layout-item[@field-label='Message']//lightning-formatted-text");
	public final static By AccountNumberField  = By.xpath("//records-record-layout-item[@field-label='Account Number']//lightning-formatted-text");
	public final static By Case_OnCallList  = By.xpath("//th[@data-label='Case Number']//a");
	public final static By CaseNumber  = By.xpath("//th[@data-label='Case Number']//a//span");
	
	//OCDM
	public final static By  NewListView = By.xpath("//span[text()='New']/parent::a");
	public final static By  ListviewName = By.xpath("//div[@part='input-container']/input[@name='title']");
	public final static By  allCases = By.xpath("//span[text()='All cases']/preceding-sibling::span");
	public final static By  StatusField = By.xpath("//lightning-base-combobox-item/span/span[@title='Status']");
	public final static By  Status_New = By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']/ul/li/a[text()='New']");
	public final static By  NewListView_save = By.xpath("//h1[text()='New List View']//parent::div//following-sibling::div[@class='modal-footer slds-modal__footer']//span[text()='Save']//parent::button");
	public final static By  first_case = By.xpath("(//tbody/tr/th)[1]//a");
	public final static By  Disposition_DropDown = By.xpath("//span[text()='Disposition']/ancestor::div//lightning-select//select");
	public final static By  CallResult_DropDown = By.xpath("//span[text()='Call Result']/ancestor::div//lightning-select//select");
	public final static By  Case_Status = By.xpath("//records-record-layout-item[@field-label='Status']//lightning-formatted-text");
	public final static By  Case_Object = By.xpath("//a[@title='Cases']");
	public final static By  DispositionValue = By.xpath("//records-record-layout-item[@field-label='Disposition']//lightning-formatted-text");
	public final static By  CallResultValue = By.xpath("//records-record-layout-item[@field-label='Call Result']//lightning-formatted-text");
	public final static By  StatusButton = By.xpath("//records-record-layout-item[@field-label='Status']//button");
	public final static By  EditCase_Status = By.xpath("//records-record-layout-item[@field-label='Status']//button[@title='Edit Status']");

	public final static By showAllResults = By.xpath("//span[contains(@title,'Show more results')]");
	public final static By selectFirstAccount = By.xpath("//table/tbody/tr/td/a");
	public final static By NewCase = By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Case.NewCase']//a[@title='New']");
	public final static By NewCasepopup = By.xpath("//h2[text()='New Case']");
	public final static By CaseRecordTypes = By.xpath("//div[@class='changeRecordTypeCenter']//input[@type='radio']");
	public final static By CST_Case = By.xpath("//span[text()='Customer Service Ticket']/preceding-sibling::input");
	public final static By PageError = By.xpath("//div[@class='pageLevelErrors']//ul/li");
	public final static By CloseCaseCreation = By.xpath("//button[contains(@title,'Close New Case')]");
	public final static By CloseContactCreation = By.xpath("//button[contains(@title,'Close New Contact')]");
	public final static By CasesInTable = By.xpath("//table[@aria-label='Cases']//tr//th[@data-label='Case']//a");
	public final static By Filter =By.xpath("//h1[text()='Cases']/ancestor::div//button[@title='Show quick filters']");
	public final static By ARConCase =By.xpath("//records-entity-label[text()='Case']/ancestor::div[@data-aura-class='navexWorkspace']//article//a/span[text()='Active Related Contacts']");
	public final static By Tasksforparentaccount =By.xpath("//records-entity-label[text()='Case']/ancestor::div[@data-aura-class='navexWorkspace']//article//a/span[contains(text(),'Open Tasks for Parent Account')]");
	public final static By Eventsforparentaccount =By.xpath("//records-entity-label[text()='Case']/ancestor::div[@data-aura-class='navexWorkspace']//article//a/span[contains(text(),'Open Events for Parent Account')]");
	
	public final static By OrderNumbercol =By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[@data-name='OrderNumber']");
	public final static By Sortbuuton =By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//a[@data-name='OrderNumber']//lightning-icon[contains(@icon-name,'utility:arrow')]");
	public final static By CloseOrders =By.xpath("//button[@title='Close Orders for Parent Account']");
	public final static By DetailedDescription =By.xpath("//label[text()='Detailed Description']/ancestor::lightning-textarea//textarea");
	public final static By CI_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='oneWorkspaceTabWrapper']//*[contains(text(),'Customer Instructions')]");
	public final static By SalesAid_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='oneWorkspaceTabWrapper']//*[contains(text(),'Sales Aids')]");
	public final static By CaseHistory_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='oneWorkspaceTabWrapper']//*[contains(text(),'Case History')]");
	public final static By Order_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='oneWorkspaceTabWrapper']//*[contains(text(),'Orders for Parent Account')]");
	public final static By OutboundDispositionECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='record-page-decorator']//h2/span[text()='Outbound Call Disposition']");
	public final static By ARCON_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='record-page-decorator']//h2//span[contains(text(),'Active Related Contacts')]");
	public final static By Tasks_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='record-page-decorator']//span[contains(text(),'Open Tasks for Parent Account')]");
	public final static By Events_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='record-page-decorator']//span[contains(text(),'Open Events for Parent Account')]");
	public final static By UpcomingAndOverdues_ECST_Page =By.xpath("//span[text()='Equipment Confirmation Service Ticket']/ancestor::div[@class='record-page-decorator']//button[@title='Upcoming & Overdue']");
	
	//Planning calendar
	public final static By PlanningCalendarbtn =By.xpath("//slot[@name='header']//button[@name='Case.Planning_Calendar']");
	public final static By FirstButtonAfterFollow =By.xpath("(//button//span[text()='Follow']/ancestor::div[@class='slds-grid primaryFieldRow']//ul/li)[1]//button");
	public final static By PlanningCalender =By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Case.Planning_Calendar' and @class='visible']//button");
	public final static By FollowButton =By.xpath("//div[@data-target-selection-name='sfdc:StandardButton.Case.Follow']//button/span[text()='Follow']");
	public final static By EditButton =By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Case.Edit' and @class='visible']//button");
	public final static By CreateSalesOrderButton =By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Case.Create_Sales_Order' and @class='visible']//button");
	public final static By SetReminderButton =By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Case.SetReminder' and @class='visible']//button");
	public final static By e_VisitButton =By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.Case.e_Visit' and @class='visible']//button");
	public final static By Acccounts_PlanningCalendarBtn =By.xpath("//button[@name='Account.Planning_Calendar']");
	public final static By FisrtCalendarMonth =By.xpath("(//h2[@id='month'])[1]");
	public final static By SecondCalendarMonth =By.xpath("(//h2[@id='month'])[2]");
	public final static By CallingDay =By.xpath("//td//img[@title='Calling Day' and not (@class='slds-hide')]");
	public final static By Cases_PlanningCalendarBtn =By.xpath("//button[@name='Case.Planning_Calendar']");
	public final static By PlanningCalenarPage =By.xpath("//h2[contains(text(),'Planning Calendar for ')]");
	public final static By NextPageBtn =By.xpath("//button[@title='Next Page']");
	public final static By PreviousPageBtn =By.xpath("//button[@title='Previous']");
	public final static By Close =By.xpath("//button[text()='Close']");
	public final static By ClosePlanningCalender =By.xpath("//button[@title='Close Planning Calendar']");
	public final static By CreatSalesOrderTab =By.xpath("//a[@title='Create Sales Order']");
	public final static By ORDERHEADER =By.xpath("//a[text()='ORDER HEADER']");
	public final static By orderStart =By.xpath("(//input[@name='EffectiveDate'])[1]");
	public final static By SuccessPopUp =By.xpath("//div[@data-key='success' and @role='alert']");
	public final static By PlanningCalenderButton =By.xpath("//records-entity-label[text()='Case']/ancestor::div[contains(@class,'slds-page-header_record-home')]//*[text()='Planning Calendar']");






}