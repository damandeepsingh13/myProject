package com.amazon.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.commons.Pagebase;

public class Prac extends Pagebase{

	public Prac(WebDriver driver)  {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void navigate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Login clickLogin()
	{			
		driver.findElement(By.xpath("//*[@class='nav-line-1'][text()='Hello. Sign in']")).click();

		return new Login(driver); 
	}
}
