package com.amazon.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.commons.Pagebase;

public class Login extends Pagebase
{

	public Login(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public Home login(String username,String password) throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@class='a-input-text a-span12 auth-autofocus auth-required-field']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='a-input-text a-span12 auth-autofocus auth-required-field']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		return new Home(driver); 
	}

	@Override
	public void navigate() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
