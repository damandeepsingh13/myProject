package com.amazon.PageObjectTests;

import org.testng.annotations.Test;


import com.amazon.PageObjects.Cart;
import com.amazon.PageObjects.Home;
import com.amazon.PageObjects.Login;
import com.amazon.PageObjects.Prac;
import com.amazon.commons.DriverInstance;
import com.google.common.base.Verify;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class HomeTest {
 
  WebDriver getdriver = null;
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }


  
  @BeforeClass
  public void beforeClass()
  {
	  DriverInstance driverInstance = new DriverInstance();
	  getdriver = driverInstance.getDriverInstance();
  }

  @AfterClass
  public void afterClass() 
  {
	 // driver.quit();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }
  
  @Test
  public void loginTest() throws InterruptedException, AWTException
  {
	 Prac p = new Prac(getdriver);
	 p.clickLogin().login("abcde", "12345");
	 
	 //array
	/* String[] dd = {"Bank","State","District","City","Branch"};
     String[] option ={"Axis Bank","Assam","Guwahati","Guwahati","Service Branch Guwahati (UTIB0001190)"};

    String code = home.getIFSCCode(dd, option," ");*/
	 
	 
	
	 
	 //2d Array
	 
	 
	/* String bankDetails[][] = new String[5][2];
     bankDetails[0][0] = "Bank";
     bankDetails[0][1] = "Axis Bank";
     bankDetails[1][0] = "State";
     bankDetails[1][1] = "Assam";
     bankDetails[2][0] = "District";
     bankDetails[2][1] = "Guwahati";
     bankDetails[3][0] = "City";
     bankDetails[3][1] = "Guwahati";
     bankDetails[4][0] = "Branch";
     bankDetails[4][1] = "Service Branch Guwahati (UTIB0001190)";
     
     
     
    String code = home.getIFSCCode(bankDetails);*/
	 
	 //hashtable
	 
		/* Hashtable<String, String> values = new Hashtable<String, String>();

	     values.put("Bank", "Axis Bank");
	     values.put("State", "Assam");
	     values.put("District", "Guwahati");
	     values.put("City", "Guwahati");
	     values.put("Branch", "Service Branch Guwahati (UTIB0001190)");

	    String code = home.getIFSCCode(values);*/
	 
    //System.out.println("code::"+code);
	/* home.openBrowser();
	 home.setIFSCCode("Bank","Axis Bank");
	 home.setIFSCCode("State", "Assam");
	 home.setIFSCCode("District", "Guwahati");
	 home.setIFSCCode("City", "Guwahati");
	 home.setIFSCCode("Branch", "Service Branch Guwahati (UTIB0001190)");
	 String code = home.getIfSCCode();
	 System.out.println(code);*/
	// home.openLinkRClick("Full Store Directory");
	// home.clickLogin().login("daman.singh84@gmail.com", "gurunanak@13").selectTest();
	 
	// driver.navigate().to("http://toolsqa.com/automation-practice-form/");
	 /*driver.navigate().to("http://only-testing-blog.blogspot.in/2013/09/testing.html");
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 Select sel = new Select(driver.findElement(By.xpath("//select[@name='FromLB']")));
	 sel.selectByVisibleText("Russia"); 
	 sel.selectByVisibleText("India"); 
	 sel.selectByVisibleText("Germany"); 
	 Thread.sleep(10000);
	 sel.deselectAll();*/
	/*Assert.assertEquals(true, true);
	Assert.assertEquals(false,true);
	System.out.println("1111111111111");
	SoftAssert softAssert = new SoftAssert();
	softAssert.fail("Failing first assertion");
	   System.out.println("Failing 1");
	   softAssert.fail("Failing first assertion");
	   System.out.println("Failing 1");
	   softAssert.assertEquals(true, false);
	   System.out.println("22222222222");
	   softAssert.assertAll();*/
	  
  }

}
