package Pages;

import org.openqa.selenium.By;



public class HomePage {
	public final static By appLauncher = By.xpath("//div[@class='appLauncher slds-context-bar__icon-action']");
	public final static By viewAllBtn = By.xpath("//button[@type='button' and text()='View All']");
	public final static By searchTxtBox = By.xpath("//label [text() = 'Search apps and items...']/parent::div/div/input");
//	public final static By searchTxtBox = By.xpath("//label[text() = 'Search apps and items...']/parent::lightning-input/div/input");
	public final static By appLauncherSearchTxtBox = By.xpath("//input[@placeholder = 'Search apps or items...']");
	public final static By appName = By.xpath("//*[@class='appName slds-context-bar__label-action slds-context-bar__app-name']//span");
	public final static By object = By.xpath("//*[@class='appName slds-context-bar__label-action slds-context-bar__app-name']//span");
	public final static By NavigationBtn = By.xpath("//button[@title='Show Navigation Menu']");
	
	
	public final static By globalSearchBtn = By.xpath("//button[@aria-label='Search']");
	public final static By searchBtn = By.xpath("//input[@placeholder='Search...']");
	public final static By result = By.xpath("//span[contains(text(),'Show more results for')]");
	public final static By today = By.xpath("//android.widget.TextView[contains(@text,'Today')]");
	public final static By userLogo=By.xpath("//button/div/span/div/span");
    public final static By userNameValidation=By.xpath("//h1[@class='profile-card-name']//a");
	
//   Create RSG Group and Alert	
  public final static By apps = By.xpath("//div[@aria-label='App']//button");
    public final static By appsname = By.xpath(" //h3[text()='Apps']");
    public final static By searchapps = By.xpath(" //input[contains(@placeholder,'Search apps')]");
    public final static By selectApp = By.xpath(" //p[@class='slds-truncate']");

    public final static By GlobalActions = By.xpath("//span[@role='tooltip' and text()='Global Actions']/ancestor::a");
    public final static By NewTask = By.xpath("//a[@title='New Task']");
    public final static By PhoneCall = By.xpath("//h2[@title='New Task']");
    public final static By comments_Tasks = By.xpath(" //div[@data-target-selection-name='sfdc:RecordField.Task.Description']//textarea");
    public final static By Save_Tasks = By.xpath("//span[text()='Save']/parent::button[@data-aura-class='uiButton']");
    public final static By Subject_Tasks = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Subject']//input");
    public final static By Error = By.xpath("//div[@role='alert']//ul[@class='errorsList slds-text-color_error']//li");
    public final static By CloseTask = By.xpath("//div[@class='headerLink']//button[@title='Close']");



}
