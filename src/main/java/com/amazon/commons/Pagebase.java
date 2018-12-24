package com.amazon.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;






public abstract class Pagebase {
	
	private static final String XPATH = "xpath";
	private static final String CSS = "css";
	private static final String ID = "id";
	

	static Logger log = Logger.getLogger(Pagebase.class);
	
	
	protected WebDriver driver;
    
    /**
     * @param driver
     */
    public Pagebase(WebDriver driver)
    {
        this.driver = driver;
    }

    
    /**
     * Webelement to be used within the class
     */ 
   
	private WebElement item;
    
    /**
     * Right clicks the item
     * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
     * @throws Exception 
     */
	protected void rightClick(String locator) throws Exception
    {
		this.item = Find(locator, 1);
        item.click();
        sleepInSeconds(2);
        sleepInSeconds(2);
        Actions action = new Actions(driver);

        action.contextClick(item).perform();
    }
    
	protected void rightClickOnSourceName(String locator,String srcName) throws Exception
    {
		List<WebElement>  elements = FindElements(locator, 1);		
        sleepInSeconds(2);
        Actions action = new Actions(driver);
        for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).getText().equals(srcName))
			{
				action.contextClick(elements.get(i)).perform();	 			
 				break; 
			}
		}	
        
       
    }
	
	/**
	 * Click element using JavaScript 
	 * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
	 * @throws Exception 
	 */
	protected void javaScriptClick(String locator) throws Exception {

		this.item = Find(locator, 1);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].fireEvent('onclick');", this.item);
	}

	/**
	 * Click element using JavaScript 
	 * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
	 * @throws Exception 
	 */
	protected void javaScriptRightClick(String locator) throws Exception {

		this.item = Find(locator, 1);
		javaScriptClick(locator);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].fireEvent('oncontextmenu');", this.item);
	}


    /**
     * Click after moving the mouse to the element (Using Action class)
     *	@param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
     * @throws Exception
     */
	protected void moveAndClick(String locator) throws Exception
    {
		this.item = Find(locator, 1);
        if (this.item != null)
        {
            Actions action = new Actions(driver);
            action.moveToElement(this.item).click().build().perform();
        }
        else
        {
            throw new Exception("Could not click locator");
        }
    }

	protected void moveAndClick(int x, int y, String locator) throws Exception
    {
		this.item = Find(locator, 1);
        if (this.item != null)
        {
            Actions action = new Actions(driver);
            action.moveToElement(this.item,x,y).click().build().perform();
        }
        else
        {
            throw new Exception("Could not click locator");
        }
    }
	
	protected void moveAndClickInMiddle(String locator) throws Exception
    {
		this.item = Find(locator, 1);
		int x = this.item.getSize().getHeight();
		int y = this.item.getSize().getWidth();
		System.out.println(x+"-----------x and y------------"+y); 
        if (this.item != null)
        {
            Actions action = new Actions(driver);
            action.moveToElement(this.item,x/2,y/2).click().build().perform();
        }
        else
        {
            throw new Exception("Could not click locator");
        }
    }

	
	private void longPress(WebElement onElement) throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.clickAndHold(onElement).perform();	
		Thread.sleep(1000);
		action.release().perform();
		Thread.sleep(1000);
	}	
	
	public void longPress(String locator, String item) throws Exception
	{
		List<WebElement> elements = FindElements(locator,1); 
		log.info("number of items : "+elements.size());
		for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).getText().equals(item))
			{
				longPress(elements.get(i));				
 				break; 
			}
		}				
	}	
	
    /**
     * Navigates to the URL
     * @param url url
     */
    protected void navigateToUrl(String url) 
    {
        log.info("Navigating to URL = " + url);
        driver.navigate().to(url);
    }
    
    /**
     * Navigates to the web page that this class represents.
        Implemented by derived classes.
     * @throws Exception 
     */
    abstract public void navigate() throws Exception;
    
  
    /**
     * Locates the element by specified locator and then sets its value
     *	@param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
     * @param elementValue The value of the element
     * @throws Exception 
     */
    protected void setField(String locator, String elementValue) throws Exception
    {
    	this.item = Find(locator, 1);
        if (this.item != null)
        {
        	this.item.clear();
        	this.item.sendKeys(elementValue);
        }
    }
    
    protected void clearAndSetValue(String locator, String elementValue) throws Exception
    {        
        this.item  = Find(locator, 1);
        this.item.clear();		
        this.item.sendKeys(Keys.CONTROL+"a");		
        this.item.sendKeys(elementValue);		
    }
    
    protected void setFieldAction(String locator, String elementValue) throws Exception 
    {
    	Actions actions = new Actions(driver);
    	actions.moveToElement(Find(locator, 1));
    	actions.click();
    	if(elementValue.equals(""))
    	{
    		actions.sendKeys(Keys.CONTROL + "a");
    		actions.sendKeys(Keys.DELETE);
    	}
    	
    	actions.sendKeys(elementValue);
    	actions.build().perform();

    }
    
    /** Selects the dropdown with given option
     *	@param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
     * @param option Option to select
     * @throws Exception 
     */
    protected void select(String locator, String option) throws Exception
    {
    	this.item = Find(locator, 1);
    	Select select = new Select(this.item);
    	select.selectByVisibleText(option);
    }
    
    protected void select(String locator, int index) throws Exception
    {
    	this.item = Find(locator, 1);
    	Select select = new Select(this.item);
    	select.selectByIndex(index); 
    }
    
    /** Returns the selected option from the dropdown
     *	@param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
     * @return Selected option
     * @throws Exception 
     */
    protected String getSelect(String locator) throws Exception
    {
    	this.item = Find(locator, 1);
    	Select select = new Select(this.item);
    	return select.getFirstSelectedOption().getText();
    }
    
    /**
     * Clicks the element
     *	@param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only these three are supported. It can be added on demand.
     * @throws Exception
     */
    protected void click(String locator) throws Exception
    {
    	this.item = Find(locator, 1);
        if (this.item != null)
        {
        	this.item.click();
        }
        else
        {
            throw new Exception("Could not click locator " + locator);
        }
    }
    
    
    /**
     * Verifies whether specified element is present or not.
     * @param by Provides a mechanism by which to find elements within a document.
     * @return Presence of element
     */
    private boolean isElementPresent(By by)
    {
        try
        {
            driver.findElement(by);
            return true;
        }
        catch (Exception NoSuchElementException)
        {
            return false;
        }
    }
    
    protected boolean isElementPresent(String locator) throws Exception 
    {
    	try
        {
            Find(locator,1);
            return true;
        }
        catch (Exception NoSuchElementException)
        {
            return false;
        } 
        		
    }
    /**
     * Sleeps for specified seconds
     * @param seconds Seconds
     */
    protected void sleepInSeconds(int seconds)
    {
        sleep(seconds * 1000);
    }

    /** Sleeps for specified minutes
     * @param minutes Minutes
     */
    protected void sleepInMinutes(int minutes)
    {
        sleep(minutes * 60 * 1000);
    }

    /** Sleeps for specified milliseconds
     * @param miliSeconds miliSeconds
     */
    private void sleep(int miliSeconds)
    {
        try {
			Thread.sleep(miliSeconds);
			//log.debug("Sleeping...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    /** 
     * Returns whether given element is enabled or not
     * @param element Element
     * @return True if given element is enable else returns false
     */
    protected boolean isElementEnabled(WebElement element)
    {
        return element.isEnabled();
        
    }
    
    public boolean isElementEnabled(String locator) throws Exception
    {
    	boolean isEnabled = false;
    	try {
			
    		isEnabled = isElementEnabled(Find(locator,1));
		} catch (Exception e) {
			isEnabled = false;			
		}
		return isEnabled;  
		
    	         
    }
    
    /** 
     * Returns whether given element is displayed or not
     * @param element Element
     * @return True if given element is displayed else returns false
     */
    protected boolean isElementDisplayed(WebElement element)
    {
        return element.isDisplayed();
    }
    
    public boolean isElementDisplayed(String locator)
    {
    	boolean isThere = false;
    	try {
			
			isThere = isElementDisplayed(Find(locator,1));
		} catch (Exception e) {
			isThere = false;			
		}
		return isThere;     	
        
    }
    
    /** 
     * Returns whether given element is selected or not
     * @param element Element
     * @return True if given element is selected else returns false
     */
    protected boolean isElementSelected(WebElement element)
    {
        return element.isSelected();
    }
    
    protected boolean isElementSelected(String locator) throws Exception
    {
    	boolean isSelected = false;
    	try {       
    		isSelected =  isElementSelected(Find(locator, 1));
    	}
    	catch (Exception e) {
    		isSelected = false;			
		}
    	
    	return isSelected;
    }
    
    public Alert getAlert() {
    	WebDriverWait wait = new WebDriverWait(driver, 6);
    	Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    	return alert;    	
	}
    
    private By getBy(String locator) throws Exception
    {
    	return By.xpath(locator);		    		
    	
    }
  
    /**
     * Returns the WebElement instance of the locator for the given xpath
     * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only this three are supported. It can be added on demand.
     * @param times Times to wait for element to be present
     * @return WebElement instance of the locator
     * @throws Exception
     */
    private WebElement Find(String locator, int times) throws Exception
    {
    	
    	WebElement element = null;
    	boolean elementFound = false;
    	for (int i = 0; i < times; i++)
    	{
    		try
            {
                element = driver.findElement(getBy(locator));
                elementFound = true;
                break;
            }
            catch (Exception NoSuchElementException)
            {
            	elementFound = false;
            }
		}
    	
    	if(! elementFound || (element == null))
    	{
    		throw new Exception("Element not found with locator:  in " + times + " attmpts" + " Exception");
    		
    	}
    	return element;
    }
    
    public WebElement Find(String locator) throws Exception
    {

    	WebElement element = null;
    	boolean elementFound = false;

    	try
    	{
    		element = driver.findElement(getBy(locator));
    		elementFound = true;
    		
    	}
    	catch (Exception NoSuchElementException)
    	{
    		elementFound = false;
    	}

    	if(! elementFound || (element == null))
    	{
    		throw new Exception("Element not found Exception");
    		
    	}
    	return element;
    }
    
    /**
     * Returns the WebElement instance of the locator for the given xpath
     * @param xpath xpath of the locator
     * @param times Times to wait for element to be present
     * @return WebElement instance of the locator
     * @throws Exception
     */
    private WebElement Find(WebElement parentElement, String xpath, int times) throws Exception
    {
    	WebElement element = null;
    	boolean elementFound = false;
    	for (int i = 0; i < times; i++) {
    		try
            {
                element = parentElement.findElement(By.xpath(xpath));
                elementFound = true;
                break;
            }
            catch (Exception NoSuchElementException)
            {
            	elementFound = false;
            }
		}
    	
    	if(! elementFound || (element == null))
    	{
    		throw new Exception("Element not found with xpath: " + xpath);
    	}
    	return element;
    }
    
    /**
     * Returns the list of WebElement instance of the locator for the given xpath
     * @param xpath xpath of the locator
     * @param times Times to wait for element to be present
     * @return List of WebElement instance of the locator
     * @throws Exception
     */
    private List<WebElement> FindElements(String xpath, int times) throws Exception
    {
    	List<WebElement>  elements = null;
    	boolean elementsFound = false;
    	for (int i = 0; i < times; i++) {
    		try
            {
                elements = driver.findElements(By.xpath(xpath));
                elementsFound = true;
                break;
            }
            catch (Exception NoSuchElementException)
            {
            	elementsFound = false;
            }
		}
    	
    	if(! elementsFound || (elements == null))
    	{
    		throw new Exception("Element not found with xpath: " + xpath);
    	}
    	return elements;
    }
    
    /** 
     * Returns the inner text of given element
     * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only this three are supported. It can be added on demand.
     * @return inner text of given element
     * @throws Exception 
     */
    protected String getText(String locator) throws Exception
    {
    	this.item = Find(locator, 1);
    	return this.item.getText();
    }   
    
    public String getTileNumberOfSource(String locator, String sourceName) throws Exception
	{
    	String getTilePos = "";	  
    	String tileNumber = "";	
    	String[] tileNumberArr = new String[3];
    	List<WebElement> elements = FindElements(locator,1); 
    	for(int i=0;i<elements.size();i++)
		{
    		if(elements.get(i).getText().equals(sourceName))
    		{  	 			
    			
    			getTilePos = elements.get(i).getAttribute("style"); 
    			tileNumberArr = getTilePos.split("z-index:");
    			tileNumber = tileNumberArr[1].replace(";", "").trim();
    			break;
    		}			
		}		
		return tileNumber;
	}
    
    protected List<String> getListOfItems(String locator) throws Exception
    {
    	List<String> itemLists = new ArrayList<String>();
    	Thread.sleep(2000); 
		List<WebElement> elements = FindElements(locator,1); 
		log.info("number of items : "+elements.size());
		for(WebElement element : elements)
		{
			itemLists.add(element.getText());
			
		}
    	
		return itemLists;    	    
    }    
    
    
    protected List<String> getTextOfItems(String locator) throws Exception
    {
    	List<String> itemLists = new ArrayList<String>();			
		List<WebElement> elements = FindElements(locator,1); 
		log.info("number of items : "+elements.size());
		for(WebElement element : elements)
		{
			itemLists.add(element.getText());
			
		}
    	
		return itemLists;    	    
    }
    
  //*[@id='card-title']
    
  //*[@id='layout-list-card-container']//*[@id='card-title']
    
    protected void clickOnItemInList(String listLocator, String itemName) throws Exception
    {
    	List<WebElement> elements = FindElements(listLocator,1); 
		log.info("number of items : "+elements.size());
		for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).getText().equals(itemName))
			{
				elements.get(i).click();
				break;
			}
		}		 	    
    }
    
    protected void clickOnTilePosition(String index) throws Exception
    {
 //   	WebElement element = driver.findElement(By.xpath("//*[contains(@id,'grid-tile-"+index+"')]//*[@class='tile-data']"));
    	//*[contains(@id,'grid-tile-5')]//*[@class='tile-data']
    //	WebElement element = driver.findElement(By.xpath("//*[contains(@style,'z-index: "+index+"')]/.."));
    	WebElement element = driver.findElement(By.xpath("//*[contains(@id,'grid-tile-"+index+"')]//*[@class='tile']"));
    	                                                                   
    	element.click();
    	
    }
    public void clickButtonPosition(String listLocator,int position) throws Exception 
    {
    	List<WebElement> elements = FindElements(listLocator,1); 
    	elements.get(position-1).click();			
    }

	/** 
     * Returns the tag name of given element
     * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only this three are supported. It can be added on demand.
     * @return tag name of given element
     * @throws Exception 
     */
    protected String getTagname(String locator) throws Exception
    {
    	this.item = Find(locator, 1);
    	return this.item.getTagName();
    }
    

    /**
     * Returns the attribute value of given element and attribute
      * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only this three are supported. It can be added on demand.
     * @param attribute attribute
     * @return attribute value of given element and attribute
     * @throws Exception 
     */
    public String getAttribute(String locator, String attribute) throws Exception
    {
    	this.item = Find(locator, 1);
    	return this.item.getAttribute(attribute);
    }
    
    /** Wait till provided seconds for element not present
      * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only this three are supported. It can be added on demand.
     * @param waitSeconds
     * @throws Exception
     */
    protected void waitTillElementNotPresent(String locator, int waitSeconds) throws Exception
    {
    	WebDriverWait wait = null;
    	wait = new WebDriverWait(this.driver, waitSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy(locator)));
//        int timeInSeconds = 0;
//        sleepInSeconds(2);
//        while ((!isElementPresent(By.xpath(locator))) && timeInSeconds != waitSeconds)
//        {
//            sleepInSeconds(1);
//            log.debug("Waiting ... " + timeInSeconds);
//            timeInSeconds++;
//        }
//        if (isElementPresent(By.xpath(locator)))
//        {
//            throw new Exception("Element " + locator + " Still present after " + waitSeconds + " seconds");
//        }
    }
    
    /** Wait till provided visible
      * @param locator of the element. It should contains @@ to separate locator strategy and locator. 
     * Supported BY's are ID, XPATH, CSS. At this moment only this three are supported. It can be added on demand.
     * @param waitSeconds
     * @throws Exception
     */
    protected void waitTillElementVisible(String locator, int waitSeconds) throws Exception
    {
    	WebDriverWait wait = null;
    	wait = new WebDriverWait(this.driver, waitSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(locator)));
//        int timeInSeconds = 0;
//        sleepInSeconds(2);
//        while (isElementDisplayed(element) && timeInSeconds != waitSeconds)
//        {
//            sleepInSeconds(1);
//            log.debug("Waiting ... " + timeInSeconds);
//            timeInSeconds++;
//        }
//        if (!isElementDisplayed(element))
//        {
//            throw new Exception("Element " +  " Still not visible after " + waitSeconds + " seconds");
//        }
    }
    
    /** Wait till provided visible
     * @param locator
     * @param waitSeconds
     * @throws Exception
     */
    protected void waitTillElementNotVisible(WebElement element, int waitSeconds) throws Exception
    {
        int timeInSeconds = 0;
        sleepInSeconds(2);
        while ((!isElementDisplayed(element)) && timeInSeconds != waitSeconds)
        {
            sleepInSeconds(1);
            log.debug("Waiting ... " + timeInSeconds);
            timeInSeconds++;
        }
        if (isElementDisplayed(element))
        {
            throw new Exception("Element " +  " Still not visible after " + waitSeconds + " seconds");
        }
    }
    
    /**
     *  Switches to new tab
     */
    protected void switchToTab()
    {
//    	ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
    	
    	
    	String parentWindow = driver.getWindowHandle();
    	Set<String> handles =  driver.getWindowHandles();
    	   for(String windowHandle  : handles)
    	       {
    	       if(!windowHandle.equals(parentWindow))
    	          {
    	    	   driver.switchTo().window(windowHandle);
    	          }
    	       }
    	

    	
    }
    
    /** Switches to new frame
     * @param name Name of frame
     */
    protected void switchToFrame(String name)
    {
    	driver.switchTo().frame(name);
    }
    
    /**
     * Switches back to main window from selected frame
     */
    protected void switchBackToDefaultContent()
    {
    	driver.switchTo().defaultContent();
    }    
   
   
    protected void moveWindow(String locator,int x,int y) throws Exception
    {
    	this.item = Find(locator, 1);
        if (this.item != null)
        {
            Actions action = new Actions(driver);
        //    action.dragAndDropBy(this.item,101 ,0).build().perform();
            action.clickAndHold(this.item).moveByOffset(x, y).perform();
            action.release(this.item).build();
            
        }
        else
        {
            throw new Exception("Could not find locator");
        }
    }    
    
    protected String getCurrentURL() throws Exception
    {
    	return driver.getCurrentUrl();   
    }
    
}
