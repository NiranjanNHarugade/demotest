package Pages;

import org.openqa.selenium.By;

public class CST_Routing {

	public final static By  CST_View = By.xpath("//h1/span[@data-aura-class='uiOutputText']");
	public final static By  AllActiveCST = By.xpath("//a[@role='option']/span[text()='All Active']");
	public final static By  SearchCST = By.xpath("//input[@name='CSTRouting__c-search-input']");
	public final static By  loadingscreen = By.xpath("//div[@class='slds-spinner_container slds-grid']");
	public final static By  CSRTName = By.xpath("//lightning-formatted-text[@slot='primaryField']");
	public final static By  Pace_Responsible = By.xpath("//span[text()='Pace Responsible']/parent::div/following-sibling::div/span/slot/lightning-formatted-text");
	public final static By  CSTProblemReported = By.xpath("//span[text()='CST Problem Reported']/parent::div/following-sibling::div/span/slot/lightning-formatted-text");
	public final static By  CloseCSTR = By.xpath("//button[contains(@title,'Close CSTR')]");
	public final static By  listViewBtn = By.xpath("//button[@title='Select a List View: CST Routing']");
	public final static By  allActive = By.xpath("//span[text()='Automation testing - All Active']");
	public final static By  searchList = By.xpath("//span[text()='CST Routing']//ancestor::div[@class='slds-grid']/following-sibling::div//input[@placeholder='Search this list...']");
	public final static By  searchforlist = By.xpath("//input[@name='CSTRouting__c-search-input']");

	public final static By  ListViewSettingBtn = By.xpath("//div[@title='List View Controls']/button");
	public final static By  newlistview = By.xpath("//span[text()='New']/parent::a");
	public final static By  ListName = By.xpath("//label[text()='List Name']/following-sibling::div/input");
	public final static By  SaveListView = By.xpath("//div[@class='modal-footer slds-modal__footer']//span[text()='Save']/parent::button");
	public final static By  filterbyowner = By.xpath("//div[text()='Filter by Owner']/ancestor::a");
	public final static By  allcst = By.xpath("//span[text()='All cst routing']/preceding-sibling::span");
	public final static By  recordtype = By.xpath("//lightning-base-combobox-item/span/span[@title='Record Type']");
	public final static By  valuebox = By.xpath("//div[text()='Value']/parent::div//div[@class='uiPopupTrigger']");
	public final static By  frame = By.xpath("iframe[style='display: none;']");
	public final static By  spring_pace = By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']/ul/li/a[text()='Pace/Spring']");
	public final static By  RecordTypePartnerFunction = By.xpath("//div[@class='forceFilterPanelMultiPicklistCriterionEditor']/ul/li/a[text()='Partner Function']");

	public final static By  done = By.xpath("//span[text()='Done']/parent::button");
	public final static By  CloseFilter = By.xpath("//h2[text()='Filters']/parent::div//button");
	public final static By  ActiveCheckbox = By.xpath("//records-record-layout-item[@field-label='Active']//lightning-input[@checked]");
	
	public final static By  clone = By.xpath("//span[text()='Clone']");
	public final static By  saveBtn = By.xpath("//h2[text()='Clone List View']//parent::div//following-sibling::div[@class='modal-footer slds-modal__footer']//span[text()='Save']//parent::button");
	public final static By  addFilter = By.xpath("//a[text()='Add Filter']");
	public final static By  field = By.xpath("//label[text()='Field']//ancestor::lightning-combobox//button");	
	public final static By  operator = By.xpath("//label[text()='Operator']//ancestor::lightning-combobox//button");	
	
	public final static By  CSTRoutingNumberOption = By.xpath("//lightning-base-combobox-item[@data-value='Name']");
	public final static By  valueField = By.xpath("//span[text()='Value']//parent::label//parent::div//input");
	public final static By  doneBtn = By.xpath("//span[text()='Done']/parent::button");
	public final static By  saveFilterBtn = By.xpath("//button[text()='Save']");
	public final static By  deleteListView = By.xpath("//span[text()='Delete']");
	public final static By  deleteBtn = By.xpath("//span[text()='Delete']/parent::button");
	public final static By  relatedListQuickLinks = By.xpath("//div[@class='windowViewMode-maximized active lafPageHost']//h2[text()='Related List Quick Links']");
	
	//CVM
	public final static By  New = By.xpath("//a[@title='New']");
	public final static By  Newcodevaluemapping = By.xpath("//h2[text()='New Code Value Mapping']");
	public final static By  CSTMetaData = By.xpath("//span[text()='CST Metadata']/preceding-sibling::span");
	public final static By  CSTMetaDataPage = By.xpath("//h2[text()='New Code Value Mapping: CST Metadata']");
	//EST
	public final static By  ESTMetaData = By.xpath("//span[text()='EST Metadata']/preceding-sibling::span");
	public final static By  ESTMetaDataPage = By.xpath("//h2[text()='New Code Value Mapping: EST Metadata']");
	
	public final static By  SendExternalCommunication = By.xpath("//span[text()='Send External Communication']"); 
	public final static By  SendExternalCommunicationfield= By.xpath("//input[@name='Send_External_Communication__c']");
	public final static By  checkbox = By.xpath("//records-record-layout-item[@field-label='Send External Communication']//lightning-input[@checked]");

	// Email ID routing
    public final static By  NewCST_Routing = By.xpath("//h2[text()='New CST Routing']");
	public final static By  EmailIDRoutingRadioBtn = By.xpath("//span[text()='Email ID']/preceding-sibling::span");
	public final static By  EmailIDRoutingPage = By.xpath("//h2[text()='New CST Routing: Email ID']");
	public final static By  CloseCSTRouting = By.xpath("//button[contains(@title,'Close') and contains(@title,'CST Routing')]");
	public final static By  PartnerFunctionRoutingRadioBtn = By.xpath("//span[text()='Partner Function']/preceding-sibling::span");
	public final static By  PartnerFunctionRoutingPage = By.xpath("//h2[text()='New CST Routing: Partner Function']");
	public final static By  AssignToManger= By.xpath("//span[text()='Assigned to Manager']");
	public final static By  AssignToMangerfield= By.xpath("//input[@name='Assigned_to_Manager__c']");
	public final static By  CSTProblemDropdown= By.xpath("//label[text()='CST Problem Reported']/ancestor::lightning-combobox//button[@role='combobox']");
	public final static By  ESTProblemDropdown= By.xpath("//label[text()='EST Problem Reported']/ancestor::lightning-combobox//button[@role='combobox']");
	public final static By  SalesOrganizationDropdown = By.xpath( "//label[text()='Sales Organization']/ancestor::lightning-combobox//button[@role='combobox']");
	public final static By  PartnerFunctionDropdown = By.xpath( "//label[text()='Partner Function']/ancestor::lightning-combobox//button[@role='combobox']");
	public final static By  EmployeeSearchBox = By.xpath( "//label[text()='Employee']/following-sibling::div/descendant::input");
	public final static By  Firstresult = By.xpath( "//div[@role='listbox']/ul[@role='group']/li[1]");
	public final static By  Erroemsg = By.xpath( "//div[@class='container']/div[@class='panel-content scrollable']//ul/li");
	public final static By discardbtn = By.xpath("//button[text()='Discard Changes']");
	
	//Collector Name routing
	public final static By  CollectorName_Routing = By.xpath("//span[text()='Collector Name']/preceding-sibling::span");
	public final static By  CollectorNameRoutingPage = By.xpath("//h2[text()='New CST Routing: Collector Name']");
	
	// Employee Name routing
	public final static By Employee_Routing = By.xpath("//span[text()='Employee']/preceding-sibling::span");
	public final static By EmployeeRoutingPage = By.xpath("//h2[text()='New CST Routing: Employee']");
	
	// PACE Name routing
	public final static By Pace_Routing = By.xpath("//span[text()='Pace']/preceding-sibling::span");
	public final static By PaceRoutingPage = By.xpath("//h2[text()='New CST Routing: Pace']");
	
	// PACE/Spring Name routing
	public final static By PaceAndSpring_Routing = By.xpath("//span[text()='Pace/Spring']/preceding-sibling::span");
	public final static By PaceAndSpringPage = By.xpath("//h2[text()='New CST Routing: Pace/Spring']");
	
	// Spring Name routing
	public final static By Spring_Routing = By.xpath("//span[text()='Spring']/preceding-sibling::span");
	public final static By SpringPage = By.xpath("//h2[text()='New CST Routing: Spring']");

// Partner Functions Routing
	public final static By  PartnerFunction = By.xpath("//records-record-layout-item[@field-label='Partner Function']//lightning-formatted-text");
	public final static By  AssignToManagerCheckbox = By.xpath("//records-record-layout-item[@field-label='Assigned to Manager']//lightning-input[@checked]");
	public final static By PartnerFunction_Routing = By.xpath("//span[text()='Partner Function']/preceding-sibling::span");
	public final static By PartnerFunctionPage = By.xpath("//h2[text()='New CST Routing: Partner Function']");
	
	// Sales Office Contact routing
		public final static By SalesOfficeContact_Routing = By.xpath("//span[text()='Sales Office Contact']/preceding-sibling::span");
		public final static By SalesOfficeContactPage = By.xpath("//h2[text()='New CST Routing: Sales Office Contact']");

}
