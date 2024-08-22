package Pages;

import org.openqa.selenium.By;

public class Contacts {

	
	
	public static final By NewContact = By.xpath("//span[text()='Contacts']/ancestor::div//a[@title='New']");
	public static final By BusinessContact = By.xpath("//h2[text()='New Contact: Business']");
	public static final By salutation = By.xpath("//lightning-picklist[@data-field='salutation']//button");
	public static final By FirstName = By.xpath("//lightning-input[@data-field='firstName']//input");
	public static final By LastName = By.xpath("//lightning-input[@data-field='lastName']//input");
	public static final By SearchAccounts = By.xpath("//input[@placeholder='Search Accounts...']");
	public final static By  ContactPriorityPickllist = By.xpath("//label[text()='Contact Priority']/ancestor::span//lightning-base-combobox//button");
	public final static By  Primary = By.xpath("//span[@title='Primary']");
	public final static By  Function = By.xpath("//label[text()='Function']/ancestor::span//lightning-base-combobox//button");
	public final static By  PrimaryPhoneType = By.xpath("//label[text()='Phone Type']/ancestor::span//lightning-base-combobox//button");
	public final static By  SecondaryPhoneType = By.xpath("//div[contains(@data-target-selection-name,'SecondaryPhoneType__c')]//lightning-base-combobox//button");
	public final static By Delete = By.xpath("//a[@title='Delete']");
	public final static By RecordTypeBusiness = By.xpath("//records-record-layout-item[@field-label='Contact Record Type']//span[text()='Business']");
	public static final By ContactName =By.xpath("//records-record-layout-item[@field-label='Name']//lightning-formatted-name");
	public static final By AccountName =By.xpath("//records-record-layout-item[@field-label='Account Name']//a//span");
	public static final By FunctiOnoncontact =By.xpath("//records-record-layout-item[@field-label='Function']//lightning-formatted-text");
	public static final By ContactPriority =By.xpath("//records-record-layout-item[@field-label='Contact Priority']//lightning-formatted-text");
	public static final By PrimaryPhone =By.xpath("//records-record-layout-item[@field-label='Primary Phone']//lightning-formatted-phone");
	public static final By SecondaryPhone =By.xpath("//records-record-layout-item[@field-label='Secondary Phone']//lightning-formatted-phone");
	public static final By PhoneTypeOnContact =By.xpath("//records-record-layout-item[@field-label='Phone Type']//lightning-formatted-text");
	public static final By SecondaryPhoneTextbox =By.xpath("//records-record-layout-item[@field-label='Secondary Phone']//input[@name='HomePhone']");
	public static final By SecondaryPhoneTypeonContact =By.xpath("//div[contains(@data-target-selection-name,'SecondaryPhoneType__c')]//lightning-formatted-text");
	
	// New business contact
	public static final By First_Name =By.xpath("//input[@placeholder='First Name']");
	public static final By Last_Name =By.xpath("//input[@placeholder='Last Name']");
	public static final By Save_contact =By.xpath("//footer//span[text()='Save']/ancestor::button");


}
