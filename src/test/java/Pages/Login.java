package Pages;

import org.openqa.selenium.By;

public class Login {

	public final static By logo=By.xpath("//img[@id='logo']");
	public final static By userName=By.xpath("//input[@id='username']");
	public final static By password=By.xpath("//input[@id='password']");
	public final static By loginButton=By.xpath("//input[@id='Login']");
	
	
	//login profile logo
	public final static By userLogo=By.xpath("//button/div/span/div/span");
	public final static By userNameValidation=By.xpath("//h1[@class='profile-card-name']//a");
	
	//Below are the Locators to Alias Login.
	public final static By setUpGearLogo =By.xpath("//div[@class='setupGear']");
	public final static By setUpOption=By.xpath("//li[@id='related_setup_app_home']/a/div/div/span/span[2]");
	public final static By globalSearchTxtBox=By.xpath("//div[2]/div/input");
	public final static By globalSearchdValue=By.xpath("//div[2]/span/div");
	public final static By aliasLoginButton=By.xpath("//input[@name='login']");

	 public final static By logoValidation=By.xpath("//div[@class='panel-content scrollable']//div[@class='profile-card-indent']//h1//a[@class='profile-link-label']");
}
