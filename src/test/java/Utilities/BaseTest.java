package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.aventstack.extentreports.service.ExtentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pages.Login;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements Constants {
	public static WebDriver driver;
	public static Properties prop;
	public static String screenshot = null;
	public static ExtentTest logger;
	public static Status status;
	public static ExtentReporter htmlReporter;
	// ExtentReports extent;
	public static ExtentTest test;
	public static ExtentService extentservice;
	public static String screenshotpath;
	public static ExtentReports extent = new ExtentReports();
	static String nameOS = "os.name";
	public JavascriptExecutor jse;
	public static WebDriverWait wait;
	public static final Duration explicitWaitTimeOut = Duration.ofSeconds(15);
	public static SoftAssert softAssert ;

	public static ExtentSparkReporter spark = new ExtentSparkReporter("./target/surefire-reports/extent-report.html")
			.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.DEVICE,
					ViewName.AUTHOR, ViewName.EXCEPTION, ViewName.LOG, })
			.apply();

	// Created by: Abhishek palankar
	public BaseTest() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "./src/test/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Created by: Abhishek palankar
	public void initialization() throws InterruptedException {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
//			System.setProperty("webdriver.chrome.driver","./Browser_Drivers/chrome/chromedriver.exe");
//		WebDriverManager.chromedriver().browserVersion("121.0.6167.185").setup();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "./Browser_Drivers/");
			driver = new FirefoxDriver();
		}
	
		// Setting new download directory path
		Map<String, Object> prefs = new HashMap<String, Object>();

		String currentUserDir = System.getProperty("user.dir");
		String downloadDir = currentUserDir + File.separator + "Downloads";

		// Use File.separator as it will work on any OS
		prefs.put("download.default_directory", downloadDir);
		
		// Adding capabilities to ChromeOptions
		ChromeOptions options = new ChromeOptions();
		
		//options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		options.setExperimentalOption("prefs", prefs);

		// Printing set download directory
		options.addArguments("--disable-notifications");
		//options.addArguments("--headless");
		
		options.addExtensions(new File (".\\CrxFiles\\Salesforce inspector 1.14.0.0.crx") );
		driver = new ChromeDriver(options);
		((JavascriptExecutor) driver).executeScript("window.focus();");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		/*driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().deleteAllCookies();*/
		
		/*
		 * Capabilities cap = ((RemoteWebDriver) driver).getCapabilities(); String
		 * browserName1 = cap.getBrowserName(); String browserVersion = (String)
		 * cap.getCapability("browserVersion"); String OS =
		 * System.getProperty("os.name").toLowerCase(); String javaVersion =
		 * System.getProperty("java.version"); String userName =
		 * System.getProperty("user.name");
		 * spark.config().setReportName("CONA DevOps Automation Report");
		 * spark.config().setDocumentTitle("CONA DevOps");
		 * extent.setSystemInfo("Environment Name", Constants.envName);
		 * extent.setSystemInfo("Bottler ID", Constants.bottler + "-" +
		 * Constants.bottlerName); extent.setSystemInfo("Browser Name", browserName1);
		 * extent.setSystemInfo("Browser Version", browserVersion);
		 * extent.setSystemInfo("OS Name", OS); extent.setSystemInfo("User Name",
		 * userName); extent.setSystemInfo("Java-Version", javaVersion);
		 */
		
		
	}

	// Created by: Abhishek palankar
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		// conver json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

		
	
	// Created by: Abhishek palankar
	public static String capture(WebDriver driver) throws IOException {

		String scrBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		File file = OutputType.FILE.convertFromBase64Png(scrBase64);
		FileUtils.copyFile(file, new File("./screenshots/scrBase64" + System.currentTimeMillis()), true);

		return scrBase64;
	}

	// Created by: Abhishek palankar
		// This method is to delete all screenshots of the previous execution
		public static void deleteScreenShots() {
			File directory = new File("./screenshots");
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (!file.delete()) {
						System.out.println("Failed to delete " + file);
					}
				}
			} else {
				System.out.println("Screenshots folder is empty, adding latest screenshots");
			}
		}

		// Created by: Abhishek palankar
	public static void logOut() {
		try {
			new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(Login.userLogo));
			CommonFunctions.javaClickByLocator(Login.userLogo);
			new WebDriverWait(
					driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(Login.userNameValidation));
			String UserName = driver.findElement(Login.userNameValidation).getText();
			new WebDriverWait(driver, explicitWaitTimeOut)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log Out']")));
			CommonFunctions.javaClickByLocator(By.xpath("//a[text()='Log Out']"));
			if(driver.findElements(By.xpath("//h2[contains(text(),'Save changes')]")).size()>0) {
				CommonFunctions.javaClickByLocator(By.xpath("//button[text()='Discard Changes']"));
			}
			logger.info("User logging off as: " + UserName);
			Reporter.log("==================================================================================", true);
		}
		catch(Exception e) {
			
		}
		status = logger.getStatus();
	}
	
	// Created by: Abhishek palankar
	public static void DirectLogin(String username, String password) throws IOException, InterruptedException, ParseException{
		 String defaultUserName = "";
		 String defaultPassWord = "";
		 String UserName = "";
		 int k = 0;
		JSONParser jsonParser = new JSONParser();
		try  {
			FileReader reader = new FileReader(System.getProperty("user.dir") + "//Test_data//"+bottler+".json");
			//Read JSON file
		
			Object obj = jsonParser.parse(reader);
	        JSONArray usersList = (JSONArray) obj;
	       
	       
	        if(username.contains("Agent"))
	        {	
	        	k=0;
	        }
	        else if (username.contains("Manager")) 
	        {
	        	k=1;
			}
	        else if (username.contains("Supervisor")) 
	        {
	        	k=2;
			}
	        else if (username.contains("Admin")) 
	        {
	        	k=3;
			}
	        else if (username.contains("UnRes")) 
	        {
	        	k=4;
			}
	        else if (username.contains("OffshoreBottler")) 
	        {
	        	k=5;
			}
	        
	        JSONObject users = (JSONObject) usersList.get(k);	       
	        JSONObject user = (JSONObject) users.get("users");	       
	        defaultUserName = (String) user.get(username);
	        defaultPassWord = (String) user.get(password);
	        } 
	        catch (FileNotFoundException e) {
	    		e.printStackTrace();
			
		}
			
		
			wait = new WebDriverWait(driver, explicitWaitTimeOut);
			

				int i = 1;
				while (i <= 2) {
					adminLogin(defaultUserName, defaultPassWord);
					
					try {
					//Thread.sleep(10000);
					new WebDriverWait(driver, explicitWaitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='setupGear']")));
					if (driver.findElement(By.xpath("//div[@class='setupGear']")).isDisplayed()) {
						logger.info("SFDC Home Page loaded successfully!");
						break;
					}
				
				
				}
					catch (Exception e) {
						 if (i>=2) {
							screenshot = capture(driver);
							logger.fail(
									"SFDC Home Page is failed to load.",
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

							Assert.fail("SFDC Home Page is failed to load.");

						}
						 else {
							i++;
						}
					}
				}
				
				
				Thread.sleep(6000);
				CommonFunctions.closeMultipleTabs();
				Thread.sleep(1000);
				try {
					new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(Login.userLogo));
					CommonFunctions.javaClickByLocator(Login.userLogo);
					new WebDriverWait(driver, Duration.ofSeconds(25))
							.until(ExpectedConditions.visibilityOfElementLocated(Login.userNameValidation));
					// CommonFunctions.elementIsDisplayed(driver.findElement(Login.userNameValidation));
					if (driver.findElement(Login.userNameValidation).isDisplayed()) {
						driver.findElement(Login.userNameValidation).isDisplayed();

						 UserName = driver.findElement(Login.userNameValidation).getText();

						
						screenshot = capture(driver);
						logger.pass(
								"<b>Expected Result:</b> User Email <b>'" + defaultUserName
										+ "'</b>. <br /> <b>Actual Result:</b> User Name <b>'" + UserName + "'</b>.",
								MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					}
				}

				catch (Exception e) {
					screenshot = capture(driver);
					logger.fail(
							"<b>Expected Result:</b> User Email <b>'" + defaultUserName
									+ "</b>'. <br /> <b>Actual Result:</b> User Name <b>'" + UserName + "'</b>.",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

					Assert.fail("<b>Expected Result:</b> User Email <b>'" + defaultUserName
							+ "</b>'. <br /> <b>Actual Result:</b> User Name <b>'" + UserName + "'</b>.");

				}
				Thread.sleep(1000);
				CommonFunctions.javaClickByLocator(Login.userLogo);

			status = logger.getStatus();

	}
	
	// Created by: Abhishek palankar
	public static void login(String username, String password) throws IOException, InterruptedException, ParseException{
		 String defaultUserName = "";
		 String defaultPassWord = "";
		 
		 int k = 0;
		JSONParser jsonParser = new JSONParser();
		try  {
			FileReader reader = new FileReader(System.getProperty("user.dir") + "//Test_data//"+bottler+".json");
			//Read JSON file
		
			Object obj = jsonParser.parse(reader);
	        JSONArray usersList = (JSONArray) obj;
	       
	       
	        if(username.contains("Agent"))
	        {	
	        	k=0;
	        }
	        else if (username.contains("OffshoreBottler")) 
	        {
	        	k=1;
			}
	        
	        JSONObject users = (JSONObject) usersList.get(k);	       
	        JSONObject user = (JSONObject) users.get("users");	       
	        defaultUserName = (String) user.get(username);
	        defaultPassWord = (String) user.get(password);
	        } 
	        catch (FileNotFoundException e) {
	    		e.printStackTrace();
			
		}
			
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		adminLogin(defaultUserName, defaultPassWord);
		
		new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(Login.userLogo));
		CommonFunctions.closeMultipleTabs();
		Thread.sleep(1000);
		verifyuser(defaultUserName);

		status = logger.getStatus();

	}
	
	// Created by  :- Abhishek palankar
	// Modified By :- Niranjan H     06-10-23 
	public static void verifyuser(String defaultUserName) throws IOException, InterruptedException {
		String UserName = "";
		int i ;
		
		// Checks Visibility of Username for 3 times.
		for( i=1;i<=4;i++)
		{
			try {
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(Login.userLogo));
				Thread.sleep(1000);
				CommonFunctions.javaClickByLocator(Login.userLogo);
				Thread.sleep(5000);
				new WebDriverWait(driver, Duration.ofSeconds(30))
						.until(ExpectedConditions.visibilityOfElementLocated(Login.userNameValidation));
				break;
			}catch(Exception E)
			{  
				if(i>3){CommonFunctions.CaptureFailure(E);}
				driver.navigate().refresh();
				
			}
		}
		// CommonFunctions.elementIsDisplayed(driver.findElement(Login.userNameValidation));
		if (driver.findElements(Login.userNameValidation).size()>0) {
			driver.findElement(Login.userNameValidation).isDisplayed();

			 UserName = driver.findElement(Login.userNameValidation).getText();
			
			 CommonFunctions.loggerPass("User Email <b>'" + defaultUserName+"'</b>.", "User Name <b>'" + UserName + "'</b>.", "Yes");
		}else {
			CommonFunctions.loggerFail("User Email <b>'" + defaultUserName+"'</b>.", "User Name <b>'" + UserName + "'</b>.", true);
		}
		
		
		Thread.sleep(1000);
		CommonFunctions.javaClickByLocator(Login.userLogo);
	}

	// Created by: Abhishek palankar
	// Description: Method to login to the SFDC
	public static void adminLogin(String defaultUserName, String defaultPassWord)
			throws IOException, InterruptedException {
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().deleteAllCookies();
		driver.switchTo().window(driver.getWindowHandle());
	//	Thread.sleep(3000);
//	wait = new WebDriverWait(driver, 20);

	wait.until(ExpectedConditions.visibilityOfElementLocated(Login.logo));
	if (driver.findElements(Login.logo).size()>0) {

		logger.info("SFDC Login page Displayed successfully!");
		driver.findElement(Login.userName).sendKeys(defaultUserName);
		driver.findElement(Login.password).click();
		Thread.sleep(1000);
		driver.findElement(Login.password).sendKeys(defaultPassWord);
		logger.info("The user logged in as <b>" + defaultUserName + "</b> ");
		driver.findElement(Login.loginButton).click();
	} else {
		CommonFunctions.loggerFail("SFDC Login page should be Displayed.", "SFDC Login page is not Displayed.", true);
	}
		
		status = logger.getStatus();
	}
	

	// Created by: Abhishek palankar
	@AfterSuite
	public void tearDown() {
		
		extent.flush();
		
	}
	
//	 Created by: Niranjan H
	@AfterClass
	public void quit() {
		
		if(userName.contains(Username_Leonardo)) 
		{driver.quit();}
	}
	 

}
