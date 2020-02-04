package test.selenium;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLoginWarnings {

	// Giorgio Manca 0239067
	public static void main(String[] args) {
		String expectedWrong = "Email and Password do not match";
		String expectedEmpty = "Please fill all the fields";
		String actualFirst = "";
		String actualSecond = "";
		String actualThird = "";
		WebDriver webDriver = null;
		WebElement emailField;
		WebElement passwordField;
		WebElement errorField;
		WebElement loginButton;
		
		String testEmail = "test@provider.org";
		String testPassword = "TestPass1";
		
		String xpathEmail = "//*[@id=\"email\"]";
		String xpathPassword = "//*[@id=\"password\"]";
		String xpathLoginButton = "/html/body/div[1]/form/input[1]";
		String xpathErrorField = "/html/body/div[1]/form/p";
		
		try {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.get("http://localhost:8080/BeCrafter/login.jsp");
			
			emailField = webDriver.findElement(By.xpath(xpathEmail));
			passwordField = webDriver.findElement(By.xpath(xpathPassword));
			loginButton = webDriver.findElement(By.xpath(xpathLoginButton));
			
			emailField.clear();
			loginButton.click();
			
			Thread.sleep(1000);
			
			emailField = webDriver.findElement(By.xpath(xpathEmail));
			passwordField = webDriver.findElement(By.xpath(xpathPassword));
			loginButton = webDriver.findElement(By.xpath(xpathLoginButton));
			errorField = webDriver.findElement(By.xpath(xpathErrorField));
			
			emailField.clear();
			actualFirst = errorField.getText();
			emailField.sendKeys("test@provider.org");
			passwordField.sendKeys("WrongPassword");
			loginButton.click();
			
			Thread.sleep(1000);
			
			emailField = webDriver.findElement(By.xpath(xpathEmail));
			passwordField = webDriver.findElement(By.xpath(xpathPassword));
			loginButton = webDriver.findElement(By.xpath(xpathLoginButton));
			errorField = webDriver.findElement(By.xpath(xpathErrorField));
			
			emailField.clear();
			actualSecond = errorField.getText();
			emailField.sendKeys(testEmail);
			passwordField.sendKeys(testPassword);
			loginButton.click();
			
			Thread.sleep(1000);
			
			actualThird = webDriver.findElement(By.xpath("//*[@id=\"action\"]/b")).getText();
			webDriver.findElement(By.xpath("//*[@id=\"action\"]/form[1]/input[1]")).click();
			
		} catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Test Exception");
		} finally {
			if(webDriver != null) {
				webDriver.close();
			}
		}
		
		if( actualFirst.equals(expectedEmpty) && actualSecond.equals(expectedWrong) && actualThird.equals(testEmail) ) {
			Logger.getGlobal().log(Level.INFO, "Test Succesful");
		} else {
			Logger.getGlobal().log(Level.WARNING, "Test Failed");
		}
	}

}
