package Pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;

public class RetailStoreGroups {

	public static final By RSGpage = By.xpath("//div[@class='slds-breadcrumb__item slds-line-height_reset slds-line-height--reset']/span[text()='Retail Store Groups']");
	public static final By newbtn = By.xpath("//div[@aria-label='Recently Viewed|Retail Store Groups|List View']//div[text()='New']");
	public static final By newrsg = By.xpath("//h2[text()='New Retail Store Group']");
	public static final By Alertgroup = By.xpath("//span[text()='Alert']");
	public static final By next = By.xpath("//span[text()='Next']");
	public static final By RSGname = By.xpath("//input[@name='Name']");
	public static final By uniquename = By.xpath("//input[@name='ReportAPIName__c']");
	public static final By save = By.xpath("//button[@name='SaveEdit']");
	public static final By Alert = By.xpath("//a/span[@title='Alerts']");
	public static final By newAlert= By.xpath("//button[text()='New']");
	public static final By Alertname= By.xpath("//input[@name='AlertName__c']");
	public static final By targetGroupSize= By.xpath("//span[text()='Target Group Size']/ancestor::records-record-layout-item[@field-label='Target Group Size']//lightning-formatted-number");
	public static final By targetGropMumber= By.xpath("//span[@title='Target Group Members']");
	public static final By options= By.xpath("//div[contains(@title,'Actions for New report')]/button");
	public static final By refresh= By.xpath("//li[@title='Refresh Tab']/a");
	public static final By descfield= By.xpath("//div[@class='slds-rich-text-editor__textarea slds-grid']");
	public static final By textField= By.xpath("//div[@class='slds-rich-text-editor__textarea slds-grid editor ql-container']//div//p");
	public static final By inputfordesc= By.xpath("//div[@class='slds-rich-text-editor__textarea slds-grid']/div");
	public static final By selectpriority= By.xpath("//lightning-picklist/descendant::button[@role='combobox']");
	public static final By setvalue= By.xpath("//lightning-base-combobox-item[@data-value='High']");
	public static final By startdate= By.xpath("//input[@name='StartDate__c']");
	public static final By enddate= By.xpath("//input[@name='EndDate__c']");
	public static final By closeRSG= By.xpath("//button[contains(@title,'Retail Store Group') and contains(@title,'Close')]");
	public static final By rsgDescription= By.xpath("//textarea[@class='slds-textarea']");
	public static final By RefreshGroupMembers= By.xpath("//button[@name='RetailLocationGroup.Create_Target_Group_Members']");
	public static final By TSGaccountname= By.xpath("(//dd[@class='slds-item_detail slds-truncate'])[1]");
	public static final By TSGaccountnumber= By.xpath("(//dd[@class='slds-item_detail slds-truncate'])[2]");
	public static final By pageload= By.xpath("//div[@class='slds-tabs_card']");
	
	
	//Call List
	public static final By CallList = By.xpath("//span[text()='Call List']");
	public static final By NewCallList = By.xpath("//h2[text()='New Retail Store Group: Call List']");
	public static final By CallListName = By.xpath("//input[@name='CallListName__c']");
	public static final By CallType = By.xpath("//label[text()='Call Type']/parent::div//button");
	public static final By CallListModel = By.xpath("//label[text()='Call List Model']/parent::div//button");
	public static final By CallListStartDate = By.xpath("//input[@name='CallListStartDate__c']");
	public static final By CallListEndDate = By.xpath("//input[@name='CallListEndDate__c']");
	public static final By PopulateCallList = By.xpath("//button[text()='Populate Call List']");
	public static final By CallListwidget = By.xpath("//a/span[@title='Call Lists']");
	public static final By geneateCallList = By.xpath("//button[text()='Generate CallList']");
	public final static By  Owner = By.xpath("//input[@placeholder='Search People...']");
	public final static By  firstresult = By.xpath("(//ul/li/lightning-base-combobox-item)[1]");
	public final static By  serachrsg = By.xpath("//input[@name='RetailLocationGroup-search-input']");
	public final static By  calllistitems = By.xpath("//span[text()='Call Lists']/ancestor::lst-common-list-internal//article[1]//a");
	public final static By  casesoncalllist = By.xpath("//span[@title='Cases']");
	public final static By  NewCase = By.xpath("//button[@name='NewCase']");
	public final static By  GenesysCloudCall = By.xpath("//input[@placeholder='Search Genesys Cloud Call List Config...']");
//	public final static By  PrimaryContact = By.xpath("//records-record-layout-section//span[text()='Business Contact']");
	public final static By  PrimaryContact = By.xpath("//records-record-layout-item[@field-label='Business Contact']//a");
	public final static By  SecondaryContact = By.xpath("//records-record-layout-item[@field-label='Secondary Contact']//a");
	public final static By  TertiaryContact = By.xpath("//records-record-layout-item[@field-label='Tertiary Contact']//a");
	public final static By  CalllistName = By.xpath("//records-record-layout-item[@field-label='Call List Number']//lightning-formatted-text");
	public final static By  CaseNumber = By.xpath("//th[@data-label='Case Number']//a//span");
	public final static By  DispositionPicklist = By.xpath("//span[text()='Disposition']/ancestor::div//select[@name='Picklsit_1']");
	public static List<String> OBDispositionDropDown = Collections.unmodifiableList(Arrays.asList("Account Review Only","Productive","Call Back","Order Reminder Sent","Unproductive"));
	public final static By  NextDisposition = By.xpath("//lightning-button[@class='slds-button flow-button__NEXT']//button[text()='Next']");
	public final static By  CallResultPicklist = By.xpath("//span[text()='Call Result']/ancestor::div//select[@name='Picklsit_1']");
	public static List<String> CallResults = Collections.unmodifiableList(Arrays.asList("No Order Needed","Need Replenishment Call","IVR Voicemail","Agent Declined","Send Back to Campaign"));
	public final static By  CaseStatus = By.xpath("//records-record-layout-item[@field-label='Status']/div[@data-target-selection-name='sfdc:RecordField.Case.Status']//lightning-formatted-text");
	public final static By  ActualDisposition = By.xpath("//records-record-layout-item[@field-label='Disposition']//lightning-formatted-text");
	public final static By  ActualCallResult = By.xpath("//records-record-layout-item[@field-label='Call Result']//lightning-formatted-text");
	public final static By  EditStatusButton = By.xpath("//records-record-layout-item[@field-label='Status']/div[@data-target-selection-name='sfdc:RecordField.Case.Status']//button");
	public final static By  callDate = By.xpath("//input[@name='callDate']");

	
	// US - 1374223
	public final static By callListDefinitionSection  = By.xpath("//span[text()='Call List Definition']");
	public final static By UCCXCampaignType  = By.xpath("//label[text()='UCCX Campaign Type']");
	public final static By UCCXCampaignTypeNone  = By.xpath("//label[text()='UCCX Campaign Type']/parent::div//button[@data-value='--None--']");
	public final static By UCCXPromptNone  = By.xpath("//label[text()='UCCX Prompt']/parent::div//button[@data-value='--None--']");

}