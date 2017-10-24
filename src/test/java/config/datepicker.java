package config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class datepicker {
	
	@Test
	public static void sample() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADC_2\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://seleniumpractise.blogspot.in/2016/08/bootstrap-dropdown-example-for-selenium.html");
		driver.findElement(By.xpath("//button[@id='menu1']")).click();
		List<WebElement> text = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li/a"));
	
		for (WebElement e: text) {
			System.out.println(e.getText());
			/*if(e.getText().equals("JavaScript")){
				e.click();
				break;				
			}*/
		}
		
		//driver.findElement(By.xpath("//*[@id='menu-mukesh1']")).click();
		
				
		////List<WebElement> list = driver.findElements(By.xpath("//*[@id='menu-mukesh1']/li/a"));  
		   
		//for (WebElement e: list) {
		//	System.out.println(e.getText());
		//}
		
		//driver.findElement(By.xpath("//a[@title='MySQL Database testing using selenium webdriver']")).click();
		  driver.close();
	}

}
