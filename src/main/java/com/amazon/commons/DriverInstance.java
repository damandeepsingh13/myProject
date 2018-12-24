package com.amazon.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverInstance 
{
	WebDriver driver;
	public WebDriver getDriverInstance()
	{
		System.setProperty("webdriver.chrome.driver", "Utils\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.amazon.in");
		return driver;	 
	}
	
	
}
