package Pages;

import org.openqa.selenium.By;

public class SalesForce_Inspector {

	
	public static final By Inspector = By.xpath("//div[contains(@title,'Show Salesforce details')]");
	public static final By Options = By.xpath("//iframe[@class='insext-popup']");
	public static final By ShowAllData = By.xpath("//div[@id='root']//a[contains(@href,'inspect.html')]");
	public static final By SearchLable = By.xpath("//input[@placeholder='Filter']");
	
}
