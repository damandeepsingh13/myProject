package com.amazon.PageObjects;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.commons.DriverInstance;
import com.amazon.commons.Pagebase;
import com.amazon.commons.WebElements;

public class Home extends Pagebase
{
	private static final Pagebase Expectedconditions = null;
	public Home(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public Login clickLogin()
	{			
		driver.findElement(By.xpath("//*[@class='nav-line-1'][text()='Hello. Sign in']")).click();

		return new Login(driver); 
	}

	public List<String> getAllMaincategories() 
	{
		org.openqa.selenium.support.ui.Select allListitem = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//select[@class='nav-search-dropdown searchSelect']")));
		int linkCount = allListitem.getOptions().size();
		System.out.println(linkCount);	
		List<String> getAllLinks = new ArrayList<String>();

		for(int i = 0; i <linkCount ; i++)
		{

			getAllLinks.add(allListitem.getOptions().get(i).getText());
		}
		return getAllLinks; 

	}


	/*public void getAllLinkTitle() throws InterruptedException
	{
		WebElement eleSel = driver.findElement(By.xpath("//select[@class='nav-search-dropdown searchSelect']"));
		org.openqa.selenium.support.ui.Select allListitem = new org.openqa.selenium.support.ui.Select(eleSel);
		int linkCount = allListitem.getOptions().size();
		System.out.println(linkCount);	


		for(int i = 0; i <linkCount ; i++)
		{
			Thread.sleep(1000);
			//System.out.println(allListitem.getOptions().get(i).getText());
			WebDriverWait wait = new WebDriverWait(driver,19);
			WebElement ellll = wait.until(ExpectedConditions.elementToBeSelected(allListitem));

			WebElement eleSearch = driver.findElement(By.xpath("//*[@type='submit'][@tabindex='7']"));

			eleSearch.isEnabled();
			Thread.sleep(3000);
			eleSearch.click();
			String title = driver.getTitle();
			System.out.println(title);
		}
	}
*/

	public void hoverFunction()
	{
		WebElement SignInelement = driver.findElement(By.xpath("//a[@class='nav-a nav-a-2'][@tabindex='61']"));
		Actions action = new Actions(driver);

		action.moveToElement(SignInelement).build().perform();

		WebElement signinList = driver.findElement(By.xpath("//div[@id='nav-flyout-accountList']"));
		signinList.getText();

	}




	public void getHoverLinks()
	{
		Actions act = new Actions(driver);
		WebElement eleHov = driver.findElement(By.xpath("//*[@class='nav-line-2'][text()='Departments']"));
		act.moveToElement(eleHov).build().perform();

		List<WebElement> getDepLinks = driver.findElements
				(By.xpath("//*[contains(@class, 'nav-hasPanel')]"));
		for(WebElement getDepLink : getDepLinks)
		{
			System.out.println(getDepLink.getText());
		}

	}

	public void search(String searchText)
	{
		WebElement eletext = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")); 
		eletext.sendKeys(searchText);
		WebElement eleButton = driver.findElement(By.xpath("//input[@class='nav-input'][@value='Go']")); 
		eleButton.click();

	}


	public void fontWeight(String xPath)
	{
		WebElement ele = driver.findElement(By.xpath(xPath));
		System.out.println(ele.getCssValue("font-weight"));		
	}



	public void moveToTop()
	{
		WebElement element = driver.findElement(By.xpath("//*[text()='Back to top']"));
		element.click();		
	}

	public void scrollBottom()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void openLinkRClick(String linkName) throws InterruptedException, AWTException
	{
		Actions act = new Actions(driver);
		Robot robot = new Robot();

		WebElement eleHov = driver.findElement(By.xpath("//*[@class='nav-line-2'][text()='Category']"));
		act.moveToElement(eleHov).build().perform();
		WebElement eleLink = driver.findElement(By.xpath("//*[@class='nav-text'][text()='"+linkName+"']"));
		Thread.sleep(2000);
		act.contextClick(eleLink).build().perform();		
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);	

	}

	public void alerts() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");

		WebElement eleAlert1 = driver.findElement(By.xpath("//button[@id='alert']"));
		eleAlert1.click();

		Thread.sleep(1000);
		driver.switchTo().alert().accept();


	}

	public void windows()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");

		String parentWindow = driver.getWindowHandle();
		System.out.println("parentwindow: "+parentWindow);

		WebElement eleButt1 = driver.findElement(By.xpath("//button[@id='button1']"));
		WebElement eleButt2 = driver.findElement(By.xpath("//button[@onclick='newMsgWin()']"));
		WebElement eleButt3 = driver.findElement(By.xpath("//button[@onclick='newBrwTab()']"));

		eleButt1.click();
		eleButt2.click();
		eleButt3.click();	

		Set<String> childWinHandles = driver.getWindowHandles();
		System.out.println(childWinHandles.size());
		for(String childWin : childWinHandles)
		{


			System.out.println("window title:"+driver.switchTo().window(childWin).getTitle());
			System.out.println("URL : "+driver.getCurrentUrl());
			if(!driver.switchTo().window(childWin).getWindowHandle().equals(parentWindow))
			{				
				driver.close();
			}
		}

		Iterator<String> childWin = childWinHandles.iterator();
		int i=0;
		while(childWin.hasNext())
		{
			System.out.println(childWin.next());	
			i++;
			if(i==1)
			{
				driver.switchTo().window(childWin.next());
				System.out.println("URL:"+driver.getCurrentUrl());
				driver.switchTo().window(parentWindow);
				System.out.println("URL:"+driver.getCurrentUrl());
				WebElement elealt = driver.findElement(By.xpath("//button[@id='alert']"));
				elealt.click();
				break;
			}
		}	



		WebElement elealt = driver.findElement(By.xpath("//button[@id='alert']"));
		elealt.click();
	}

	public void frames() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://toolsqa.com/iframe-practice-page/");

		List<WebElement> eleFrames = driver.findElements(By.tagName("iframe"));
		System.out.println(eleFrames.size());
		driver.switchTo().frame("iframe2");

		WebElement eletab = driver.findElement(By.xpath("//a[@id='ui-id-1']"));
		eletab.click();
		System.out.println("text : "+eletab.getText());

		WebElement eleTitle = driver.findElement(By.xpath("//div[@aria-labelledby='ui-id-1']"));
		System.out.println("title : "+eleTitle.getText());		

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		List<WebElement> eleHome = driver.findElements(By.xpath("//span[@class='text-wrap']"));
		System.out.println(eleHome.size());
		eleHome.get(0).click();
		//		WebElement eleHome = driver.findElement(By.xpath("//span[@class='text-wrap']"));
		//		eleHome.click();
	}

	public void clickBottom()
	{
		//	scrollBottom();
		//WebElement eleLink = driver.findElement(By.xpath("//a[@class='sign-in-tooltip-link']"));
		List<WebElement> eleLink = driver.findElements(By.xpath("//a[@class='a-link-normal']"));

		eleLink.get(8).click();
	}

	/*public void explicitWait()
	{
		driver.get("http://www.firstcry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.switchTo().frame("iframe_Login");

		WebElement ele = driver.findElement(By.xpath("//input[@id='Email']"));
		ele.sendKeys("Daman");
		WebElement ele = null;
		WebDriverWait wait = null;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");

		ele = driver.findElement(By.xpath("//button[@id='timingAlert']"));
		ele.click();

		wait =  new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();


		//2nd ex
		driver.get("https://www.msn.com");
		ele = driver.findElement(By.xpath("//span[@class='title'][text()='Are aliens out there? These 16 signs may surprise you']"));


		//wait =  new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.elementToBeClickable(ele)); 
		ele.click();
	}*/

	
	
	
	
	public void shoesize(int size)
	{
		search("mens shoes");
		
		List<WebElement> eleShoeSizes = driver.findElements(By.xpath("//div[@class='buttonsprite s-ref-text-link']//span[@class='a-size-small a-color-base']"));
		String[] ssize = new String[eleShoeSizes.size()];
		float[] result = new float[eleShoeSizes.size()];
		for(int i=0;i<eleShoeSizes.size();i++)
		{
			ssize[i] = eleShoeSizes.get(i).getText();
			result[i] = Float.parseFloat(ssize[i]);		
		}
		
	    int index = 	Arrays.binarySearch(result,size);
	    eleShoeSizes.get(index).click();
		
	}
	
	public void openBrowser()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.ifsccodebank.com/");
	}
	
	public void setIFSCCode(String xDropDown,String OptionName) throws InterruptedException
    {
           Select select = null;
           WebDriverWait wait = new WebDriverWait(driver,20);
           WebElement lstDrp = driver.findElement(By.xpath("//select[@name='ctl00$BC$ddl"+xDropDown+"']"));
           select = new Select(lstDrp);
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='ctl00$BC$ddl"+xDropDown+"']")));
           select.selectByVisibleText(OptionName);                

           driver.findElement(By.xpath("//input[@name='ctl00$BC$txtLocation']")).sendKeys("");
           driver.findElement(By.xpath("//input[@name='ctl00$BC$btnSeach']")).click();
           
    }
	
	public String getIfSCCode() throws InterruptedException
	{
	
		   
           Thread.sleep(3000);
   		   WebElement eleCode = driver.findElement(By.xpath("//div[@class='IFSCCode floatLEFT']//a[contains(@href,'https://www.ifsccodebank.com/ifsc-code-')]"));

   		   return eleCode.getText();

    } 


	public String getIFSCCode(String[] xDropDown,String[] OptionName,String Address) throws InterruptedException
	{          

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.ifsccodebank.com/");

		WebElement lstDrp =null;
		Select select = null;
		WebDriverWait wait = new WebDriverWait(driver,20);
		for(int i=0;i<xDropDown.length;i++)
		{

			lstDrp = driver.findElement(By.xpath("//select[@name='ctl00$BC$ddl"+xDropDown[i]+"']"));
			select = new Select(lstDrp);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='ctl00$BC$ddl"+xDropDown[i]+"']")));
			select.selectByVisibleText(OptionName[i]);
		}         

		driver.findElement(By.xpath("//input[@name='ctl00$BC$txtLocation']")).sendKeys(Address);
		driver.findElement(By.xpath("//input[@name='ctl00$BC$btnSeach']")).click();


		//use sleep or explicit wait as code takes time get generated
		Thread.sleep(3000);
		WebElement eleCode = driver.findElement(By.xpath("//div[@class='IFSCCode floatLEFT']//a[contains(@href,'https://www.ifsccodebank.com/ifsc-code-')]"));

		return eleCode.getText();
	}


	

	public String getIFSCCode(String[][] bankDetails) throws InterruptedException
    { 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.ifsccodebank.com/");
		
           WebElement lstDrp =null;
           Select select = null;
           WebDriverWait wait = new WebDriverWait(driver,20);
           for(int i=0;i<bankDetails.length;i++)
           {
                  lstDrp = driver.findElement(By.xpath("//select[@name='ctl00$BC$ddl"+bankDetails[i][0]+"']"));
                  select = new Select(lstDrp);
                  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='ctl00$BC$ddl"+bankDetails[i][0]+"']")));
                  select.selectByVisibleText(bankDetails[i][1]);
           }
           
           driver.findElement(By.xpath("//input[@name='ctl00$BC$txtLocation']")).sendKeys("");
           driver.findElement(By.xpath("//input[@name='ctl00$BC$btnSeach']")).click();
           //use sleep or explicit wait as code takes time get generated
           Thread.sleep(3000);
   		WebElement eleCode = driver.findElement(By.xpath("//div[@class='IFSCCode floatLEFT']//a[contains(@href,'https://www.ifsccodebank.com/ifsc-code-')]"));

   		return eleCode.getText();

    }
	 


	public String getIFSCCode(Hashtable<String, String> hash) throws InterruptedException
	{          
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.ifsccodebank.com/");
		
		WebElement lstDrp =null;
		Select select = null;
		WebDriverWait wait = new WebDriverWait(driver,20);        

		Iterator<String> keySetIterator = hash.keySet().iterator();
		while(keySetIterator.hasNext())
		{
			String key = keySetIterator.next();
			System.out.println("key: " + key + " value: " + hash.get(key));
			lstDrp = driver.findElement(By.xpath("//select[@name='ctl00$BC$ddl"+key+"']"));
			select = new Select(lstDrp);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='ctl00$BC$ddl"+key+"']")));
			select.selectByVisibleText(hash.get(key));
		}
		driver.findElement(By.xpath("//input[@name='ctl00$BC$txtLocation']")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='ctl00$BC$btnSeach']")).click();
		//use sleep or explicit wait as code takes time get generated

		Thread.sleep(3000);
		WebElement eleCode = driver.findElement(By.xpath("//div[@class='IFSCCode floatLEFT']//a[contains(@href,'https://www.ifsccodebank.com/ifsc-code-')]"));

		return eleCode.getText();
	}


	
	/* public static void main(String[] str)

    {

           Utility util = new Utility();

           util.getIFSCCode("Bank", "Axis Bank");

           util.getIFSCCode("State", "Assam");

           util.getIFSCCode("", "");

           util.getIFSCCode("", "");



           //or



           String[] dd = {"Bank","State",""};

           String[] option ={"Axis Bank","Assam",""};

           util.getIFSCCode(dd, option," ");



           //or



           Hashtable<String, String> values = new Hashtable<String, String>();

           values.put("Bank", "Axis Bank");

           values.put("State", "Assam");

           util.getIFSCCode(values);



           //or



           String bankDetails[][] = new String[4][2];

           bankDetails[0][0] = "Bank";

           bankDetails[0][1] = "Axis bank";

           bankDetails[1][0] = "State";

           bankDetails[1][1] = "Assam";

           bankDetails[2][0] = "District";

           util.getIFSCCode(bankDetails);          

    }*/
	
	@Override
	public void navigate() throws Exception {
		// TODO Auto-generated method stub

	}


}
