package Utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Pages.CreateSalesOrder;

public class Temp extends BaseTest {

	public static void login() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get("https://test.salesforce.com/");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("offshore.bottleradmin4200@cona.com.lfln008");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("P@sswordNew@12345");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
	}

	public static void OpenFromApplauncher(String application) throws InterruptedException {
		WebElement applicationname = driver.findElement(
				By.xpath("//*[@class='appName slds-context-bar__label-action slds-context-bar__app-name']"));
		if (applicationname.getText().contains(application) == false) {
			driver.findElement(By.xpath("//div[@aria-label='App']//button")).click();
			Thread.sleep(600);
			driver.findElement(By.xpath("//input[contains(@id,'input-')]")).sendKeys(application);
			driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
			System.out.println(application+" Opened");
		} else {
			System.out.println("Currently on "+application);
		}
	}

	public static void Select_Tab(WebDriver driver, String object) throws InterruptedException {
		WebElement currenttab = driver
				.findElement(By.xpath("//a[@class='slds-context-bar__label-action slds-p-left--xx-small']"));
		WebElement navigationbutton = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"));
		if (currenttab.getText().contains(object) == false) {
			navigationbutton.click();
			Thread.sleep(200);
			WebElement TabName = driver.findElement(By.xpath("//a[contains(@data-label,'" + object + "')]"));
			TabName.click();
			Thread.sleep(2000);
			System.out.println(object + " tab Open");
		} else {
			System.out.println("Currently on " + object + " Tab");
		}
	}

	public static void SearchGlobal(String Account_Number) throws InterruptedException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Thread.sleep(300);
		WebElement globalsearchButton = driver.findElement(
				By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']"));
		globalsearchButton.click();
		WebElement globalsearch = driver.findElement(
				By.xpath("//div[@class='forceSearchAssistantDialog']//descendant::input[@class='slds-input']"));
		globalsearch.sendKeys(Account_Number + Keys.ENTER);
		Thread.sleep(500);
		WebElement accountstable = driver
				.findElement(By.xpath("(//div[@class='slds-grid listDisplays safari-workaround-anchor'])[2]"));
		js.executeScript("arguments[0].scrollIntoVeiw", accountstable);
		try {
			WebElement accountslink = driver.findElement(By.xpath(
					"//div[@data-aura-class='forceSearchSearchResultsGridHeader forceSearchResultsGridLVM']/following-sibling::div[contains(@aria-label,'Accounts')]/descendant::a[@data-aura-class]"));
			accountslink.click();
			Thread.sleep(2000);
			WebElement accountnumber = driver
					.findElement(By.xpath("(//lightning-formatted-text[@data-output-element-id='output-field'])[1]"));
			if (accountnumber.getText().equals(Account_Number)) {
				System.out.println("Showing correct result");
			} else {
				System.out.println("Account not found");
			}

		} catch (Exception e) {
			System.out.println("Search not found");
		}
	}

	public static void closeMultipleTabs() throws InterruptedException {
		try {	
			CommonFunctions.handleReviewPopUp();
			List<WebElement> multipleclosebuttons = driver.findElements(By.xpath("//button[@class='slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container']"));
		
			driver.findElement(By.xpath("//div[@class='tabBar slds-grid']"));
		

			for (int i = 0; i <= multipleclosebuttons.size(); i++) {
				multipleclosebuttons.get(i).click();
			}
			if (driver.findElements(CreateSalesOrder.saveChangesPopup).size() > 0) {
				CommonFunctions.javaClickByLocator(CreateSalesOrder.discardBtn);
			}
			System.out.println("All other tabs are closed");
		} catch (Exception e) {
			System.out.println("No other Tabs Are Open");
		}
	}

}
