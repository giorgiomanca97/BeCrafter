package test.selenium;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAddProductToCart {
	
	// Davide Bianchi 0228110
	public static void main(String[] args) {
		String expected1 = "10";
		String expected2 = "5";
		String actual1 = "";
		String actual2 = "";
		WebDriver webDriver = null;
		WebElement webElement;
		
		try {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.get("http://localhost:8080/BeCrafter/index.jsp");
			
			webDriver.findElement(By.xpath("//*[@id=\"products\"]/table/tbody/tr[2]/td[9]/form/input[1]")).click();
			Thread.sleep(1000);
			webElement = webDriver.findElement(By.xpath("/html/body/div[1]/form/input[1]"));
			webElement.clear();
			webElement.sendKeys(expected1);
			webDriver.findElement(By.xpath("/html/body/div[1]/form/input[2]")).click();
			Thread.sleep(1000);
			
			webDriver.findElement(By.xpath("//*[@id=\"products\"]/table/tbody/tr[10]/td[9]/form/input[1]")).click();
			Thread.sleep(1000);
			webElement = webDriver.findElement(By.xpath("/html/body/div[1]/form/input[1]"));
			webElement.clear();
			webElement.sendKeys(expected2);
			webDriver.findElement(By.xpath("/html/body/div[1]/form/input[2]")).click();
			Thread.sleep(1000);
			
			webDriver.findElement(By.xpath("//*[@id=\"action\"]/form[3]/input")).click();
			Thread.sleep(1000);
			actual1 = webDriver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[9]")).getText();
			actual2 = webDriver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[3]/td[9]")).getText();
			
			Thread.sleep(1000);
			
			webDriver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[12]/form/input[1]")).click();
			webDriver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[12]/form/input[1]")).click();
		} catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Test Exception");
		} finally {
			if(webDriver != null) {
				webDriver.close();
			}
		}
		
		if( actual1.equals(expected1) && actual2.equals(expected2)) {
			Logger.getGlobal().log(Level.INFO, "Test Succesful");
		} else {
			Logger.getGlobal().log(Level.WARNING, "Test Failed");
		}
		
	}
}
