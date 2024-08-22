package Pages;

import org.openqa.selenium.By;

public class Reports {
	
	public final static By newReports = By.xpath("//a[@title='New Report']");
	public final static By reportpage = By.xpath("//span[@class='label'and text()='Report' ]");
	public final static By closecolmn = By.xpath("//span[@class='slds-icon_container slds-pill__remove sectionable-table-row-actions' ]");
	public static final By reportTypeAsAccounts = By.xpath("//p[text()='Accounts']/parent::a");
	public static final By continueBtn = By.xpath("//button[text()='Start Report']");
	public static final By numberofcolmn = By.xpath("//div[@class='sectionable-table-group-content']/ul/li[@role='presentation']");
	public static final By addColumnTxtBox = By.xpath("//input[@placeholder='Add column...']");
	public static final By filtersTab = By.xpath("//h2[text()='Filters']");
	public static final By showMeFilterBtn = By.xpath("//span[text()='Show Me']/ancestor::button");
	public static final By FilterDropDown = By.xpath("//div[@class='slds-popover__body']//button[@class='slds-button slds-button_neutral slds-picklist__label']");
	public static final By allAccountsOption = By.xpath("//span[text()='All accounts']");
	public static final By applyBtn = By.xpath("//button[text()='Apply']");
	public static final By refreshBtn = By.xpath("//button[text()='Refresh']");
	public static final By outlineTab = By.xpath("//h2[text()='Outline']");
	public static final By accountIDColumnOption = By.xpath("//span[text()='Account ID']");
	public static final By suggetion = By.xpath("//span[text()='Account General']");
	public static final By addFilterSearchTxtBox = By.xpath("//input[@placeholder='Add filter...']");
	public static final By preferredOrderingMethodFilter = By.xpath("//span[@class='slds-media__body']/span[@title = 'Preferred Ordering Method']");
	public static final By value = By.xpath("//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_left-right']");
	public static final By valuefeild = By.xpath("//input[@id='undefined-search']");
	public static final By callcenteroption = By.xpath("//div[text()='Call Center']");
	public static final By createddatefilter =By.xpath("//span[text()='Created Date']/parent::button");
	public static final By datefilter =By.xpath("//label[text()='Range']/parent::div/div/button");
	public static final By datealltimes =By.xpath("//span[text()='All Time']");
	public static final By reportname =By.xpath("//input[@id='reportName']");
	public static final By uniqueReportNameTxtField = By.xpath("//input[@id ='reportUniqueName']");
	public static final By reportDescriptionTxtField = By.xpath("//textarea[@id ='reportDescription']");
	public static final By reportNameTxtField = By.xpath("//input[@id = 'reportName']");
	public static final By save = By.xpath("//button[text()='Save']");
	public static final By selectFolderBtn = By.xpath("//button[@title= 'Select Folder']");
	public static final By searchfolder = By.xpath("//input[@placeholder='Search folders...']");
	public static final By selectfolder = By.xpath("//div[@class='search-result-item']/li/a");
	public static final By confirm = By.xpath(" //div[@class='modal-footer slds-modal__footer']/button[text()='Select Folder' ]");
	public static final By save1 = By.xpath("//footer/button[text()='Save']");
	public static final By alerttoast = By.xpath("//div[@role='alertdialog']");
	public static final By Morebtn = By.xpath("//button[contains(@class,'more-actions-button')]");
	public static final By properties = By.xpath("//span[text()='Properties']");
	public static final By alertWidget = By.xpath("//div[text()='Case']//ancestor::div[@class='slds-col slds-size_1-of-1 row region-header']//following-sibling::div//b[contains(text(),'Customer Alerts')]");
	public final static By SearchReport = By.xpath("//input[@placeholder='Search Report Types...']");
	public static final By reportTypeAsCases = By.xpath("//p[text()='Cases']/parent::a");
	public final static By AllReports = By.xpath("//a[text()='All Reports']");
	public final static By SearchReports = By.xpath("//input[@placeholder='Search all reports...']");
	public final static By EditReports = By.xpath("//div[@role='group']//button[text()='Edit']");
}
