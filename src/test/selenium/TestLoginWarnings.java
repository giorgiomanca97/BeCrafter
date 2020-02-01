package test.selenium;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLoginWarnings {

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
		
		try {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.get("http://localhost:8080/BeCrafter/login.jsp");
			
			emailField = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
			passwordField = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
			loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/form/input[1]"));
			
			emailField.clear();
			loginButton.click();
			
			Thread.sleep(1000);
			
			emailField = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
			passwordField = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
			loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/form/input[1]"));
			errorField = webDriver.findElement(By.xpath("/html/body/div[1]/form/p"));
			
			emailField.clear();
			actualFirst = errorField.getText();
			emailField.sendKeys("test@provider.org");
			passwordField.sendKeys("WrongPassword");
			loginButton.click();
			
			Thread.sleep(1000);
			
			emailField = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
			passwordField = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
			loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/form/input[1]"));
			errorField = webDriver.findElement(By.xpath("/html/body/div[1]/form/p"));
			
			emailField.clear();
			actualSecond = errorField.getText();
			emailField.sendKeys("test@provider.org");
			passwordField.sendKeys("TestPass1");
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
		
		if( actualFirst.equals(expectedEmpty) && actualSecond.equals(expectedWrong) && actualThird.equals("test@provider.org") ) {
			Logger.getGlobal().log(Level.INFO, "Test Succesful");
		} else {
			Logger.getGlobal().log(Level.WARNING, "Test Failed");
		}
	}

}
