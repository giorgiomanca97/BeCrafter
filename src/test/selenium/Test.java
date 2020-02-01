package test.selenium;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("http://localhost:8080/BeCrafter/index.jsp");
		
		webDriver.findElement(By.xpath(""));
	}
}
