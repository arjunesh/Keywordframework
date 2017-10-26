package config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
import org.openqa.selenium.support.ui.Select;

import com.beust.jcommander.Parameter;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import executionEngine.DriverScriptTest;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.remote.MobileCapabilityType;
//import utility.Log;
import utility.Log;

import static executionEngine.DriverScriptTest.OR;


public class ActionKeywords {
			
		public static WebDriver driver = null;
		public static WebDriver mdriver = null;
		public static ExtentReports reports;
		public static ExtentTest Logger;
		
		//Constructor to initialize the object of class ExtentReports whenever we initialize object
		public ActionKeywords(){
			//Create an object of ExtentReports class
			reports=new ExtentReports(Constants.Extent_Reports,true);
			reports.loadConfig(new File("./extend-config.xml"));
		}
		//This block of code will decide which browser type to start
		//All the methods in this class now accept 'Object' name as an argument
		public static void openBrowser(String object, String data){
			try{
				//Log.info("Opening Browser");
				//If value of the parameter is Chrome, this will execute
				if (data.equals("Chrome")){
					System.setProperty("webdriver.chrome.driver","C:\\Users\\ADC_2\\Downloads\\chromedriver.exe");
					driver=new ChromeDriver();
					driver.manage().window().maximize();
					Logger.log(LogStatus.PASS, "opened Browser -"+ data);
					Log.info("Chrome browser started");
					}
				else if (data.equals("IE")){
					//You may need to change the code here to start IE Driver
					System.setProperty("webdriver.ie.driver","C:\\JARs\\MicrosoftWebDriver.exe");
					driver=new InternetExplorerDriver();
					driver.manage().window().maximize();
					Logger.log(LogStatus.PASS, "opened Browser -"+ data);
					Log.info("IE browser started");
					}
				else if(data.equals("Mozilla")){
					System.setProperty("webdriver.gecko.driver","C:\\JARs\\geckodriver-v0.19.0-win64\\geckodriver.exe");
					driver=new FirefoxDriver();
					Logger.log(LogStatus.PASS, "opened Browser -"+ data);
					Log.info("Mozilla browser started");}
				//This block will execute only in case of an exception
			
			}
			
			
				catch(Exception e){
				//This is to print the Logs - Method Name & Error description/stack
				Log.info("Not able to open Browser --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to opened Browser -"+ data);
				//Set the value of result variable to false
				DriverScriptTest.bResult = false;
			}
			
		}
		
		public static void navigate(String object, String data){
			try{
				Log.info("Navigating to URL "+ "'" + Constants.URL+"'");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//Constant Variable is used in place of URL
				//As it was declared as 'static', it can be used by referring the class name
				//Type the class name 'Constants' and press '.' dot, it will display all the memebers of the class Constants
				driver.get(data);
				Log.info( "Navigated to URL - "+ data);
			}catch(Exception e){
				Log.info("Not able to navigate --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to Navigate to URL - "+ data);
				DriverScriptTest.bResult = false;
			}
				
		}
		
		public static void input(String object, String data){
			try{
				Log.info("Entering the text in "+ object);
				//Constant Variable is used in place of UserName
				//This is fetching the xpath of the element from the Object Repository property file
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
				Logger.log(LogStatus.PASS, "Entered the text in "+ object);
			}catch(Exception e){
				Log.error("Not able to Enter UserName --- " + e.getMessage());
				DriverScriptTest.bResult = false;
				Logger.log(LogStatus.FAIL, "Not able to enter text in "+ object);
			}
			 
		}
		
//		public static void input_Password(String object){
//			try{
//				Log.info("Entering the text in "+ object);
//				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
//			}catch(Exception e){
//				Log.error("Not able to Enter Password --- " + e.getMessage());
//				DriverScriptTest.bResult = false;
//			}
//			
//		}
		
		public static void select_listbox(String object, String data){
			try{
				Log.info("Clicking on Webelement "+ object);
				System.out.println("object-->>>>"+object+"data-->>>"+data);
				Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				System.out.println("identified select box");
				dropdown.selectByVisibleText(data);
				System.out.println("selected data from list box");
				Logger.log(LogStatus.PASS, "Succefully selected value from Lis "+ object);
			}catch(Exception e){
				Log.error("Not able to click --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to select value from List "+ object);
	 			DriverScriptTest.bResult = false;
			}
			
		}
		
		public static void verify_add(String object, String data){
			try{
				Log.info("Clicking on Webelement "+ object);
				List<WebElement> values = driver.findElements(By.xpath(OR.getProperty(object)));
				
				String stringcon = "";
				for(WebElement value: values) {
					stringcon = stringcon+value.getText()+"|";
					//System.out.println("Added value--->"+value.getText());
				}
				//System.out.println(stringcon);
				if(stringcon.equals(data)) {
					Logger.log(LogStatus.PASS, "Add was succesfull "+ object);
				}
				else {
					Logger.log(LogStatus.FAIL, "Add was not succesfull "+ object);
				}
				
			}catch(Exception e){
				Log.error("Not able to click --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to Click on object "+ object);
	 			DriverScriptTest.bResult = false;
			}
			
		}
		
		
		
		public static void click(String object, String data){
			try{
				Log.info("Clicking on Webelement "+ object);
				driver.findElement(By.xpath(OR.getProperty(object))).click();
				Logger.log(LogStatus.PASS, "Succefully Clicked on object "+ object);
			}catch(Exception e){
				Log.error("Not able to click --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to Click on object "+ object);
	 			DriverScriptTest.bResult = false;
			}
			
		}
		
		
		public static void waitFor(String object, String data) throws Exception{
			try{
				Log.info("Wait for 5 seconds");
				Thread.sleep(2000);
			}catch(Exception e){
				Log.error("Not able to Wait --- " + e.getMessage());
				DriverScriptTest.bResult = false;
			}
			
		}
		
		public static void closeBrowser(String object, String data){
			try{
				Log.info("Closing the Browser");
				driver.quit();
				Logger.log(LogStatus.PASS, "Succefully closed the browser -"+ object);
			}catch(Exception e){
				Log.error("Not able to Close the Browser --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to close the browser -"+ object);
				DriverScriptTest.bResult = false;
			}
			
		}
		
		public static String GetText(String object, String data){
			try{
				Log.info("Getting the text of '"+object+"'");
				String Text = driver.findElement(By.xpath(OR.getProperty(object))).getText();
				return Text;
			}catch (Exception e){
				Log.error("Not able read the text --- " + e.getMessage());
				DriverScriptTest.bResult = false;
				return null;
			}
			
		}
		
		public static void compareGetText(String object, String data){
			try{
				Log.info("Comparing the text '" +data+ "' with '"+object+"'" );
				String acutalText = driver.findElement(By.xpath(object)).getAttribute("innerText");
				if(acutalText.equals(data)) {
					Logger.log(LogStatus.PASS, "Expected text '"+data+ "'is same as actual text'"+ object);
			}
			}catch(Exception e){
				Log.error("Not able to compare the text --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to compare the text "+ object);
				DriverScriptTest.bResult = false;
			}
			
		}
		
		public static void verifyElement(String object, String data){
			try{
				Log.info("Verifying the element '"+ object);
				driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed();
				elementHighlight(driver.findElement(By.xpath(OR.getProperty(object))));
				Thread.sleep(600);
				Logger.log(LogStatus.PASS, "Webelement '"+ object+"'displayed on page");
			}catch(Exception e){
				Log.error("Unable to find Webelement --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to find Webelement "+ object);
	 			DriverScriptTest.bResult = false;
			}
		}
		
		
		public static void mverifyElement(String object, String data){
			try{
				Log.info("Verifying the element '"+ object);
				mdriver.findElement(By.xpath(OR.getProperty(object))).isDisplayed();
				//elementHighlight(mdriver.findElement(By.xpath(OR.getProperty(object))));
				Thread.sleep(600);
				Logger.log(LogStatus.PASS, "Webelement '"+ object+"'displayed on page");
			}catch(Exception e){
				Log.error("Unable to find Webelement --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Unable to find Webelement "+ object);
	 			DriverScriptTest.bResult = false;
			}
		}
		
		
		public static void elementHighlight(WebElement element) {
			for (int i = 0; i < 2; i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(
						"arguments[0].setAttribute('style', arguments[1]);",
						element, "color: blue; border: 3px solid blue;");
				js.executeScript(
						"arguments[0].setAttribute('style', arguments[1]);",
						element, "");
			}
		}
		
		public static void compareLinkText(String object, String data){
			try{
				Log.info("Comparing link text '"+ object);
				String lnktxt=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("innerText");
				elementHighlight(driver.findElement(By.xpath(OR.getProperty(object))));
				Thread.sleep(600);
				if (lnktxt.equals(data)) {
				Logger.log(LogStatus.PASS, "Link text is matching expcted value-"+data);
				}
				
			}catch(Exception e){
				Log.error("Link text is not matching with expected value --- " + e.getMessage());
				Logger.log(LogStatus.FAIL, "Link text is not matched with expected value "+ data);
	 			DriverScriptTest.bResult = false;
			}
			
		}
		
}
