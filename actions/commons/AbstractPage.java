package commons;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


public class AbstractPage {
	public void openAnyURL(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void backToBrowser(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshToBrowser(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancleAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String inputValue) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(inputValue);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String selectedValue) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select selector = new Select(element);
		selector.selectByVisibleText(selectedValue);
	}

	public void SelectManyItemsInDropDown(WebDriver driver, String parentLocator, String childLocator, List<String> expectedValue){		
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		WebElement element = driver.findElement(By.xpath(parentLocator));	
		//clickElementByJs(driver, element);	
		scrollToViewToClickElementByJs(driver, element);

		List <WebElement> lstAllItems = driver.findElements(By.xpath(childLocator));	
		wait.until(ExpectedConditions.visibilityOfAllElements(lstAllItems));
		for(WebElement e: lstAllItems) 
		{			
			int index = expectedValue.indexOf(e.getText().trim());	
			if (index>-1)
				scrollToViewToClickElementByJs(driver, element);
		}
	}
	
	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select selector = new Select(element);
		return selector.getFirstSelectedOption().getText();
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	
	public int countElementNumber(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}
	
	public void checkToCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected())
			element.click();
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected())
			element.click();
	}
	
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	
	public boolean isControlSelectedy(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	
	public boolean isControlEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	
	public void switchByID(WebDriver driver, String parentID){	
		Set<String> allWindows = driver.getWindowHandles();
		for(String w: allWindows) {		
			if (w.equals(parentID)) {
				driver.switchTo().window(w);
				break;
			}
		}
	}
	
	public void switchByTitle(WebDriver driver, String newTitle){	
		Set<String> allWindows = driver.getWindowHandles();
		for(String w: allWindows) {		
			driver.switchTo().window(w);
			if (driver.getTitle().trim().equals(newTitle.trim()))
				break;
		}
	}
	
	public boolean CloseAllWindowWithouParent(WebDriver driver, String parentWindow) {	
		Set<String> allWindows = driver.getWindowHandles();
		for(String w: allWindows) {
			if(!w.equalsIgnoreCase(parentWindow))
			{
				driver.switchTo().window(w);
				driver.close();
			}
		}	
		if (allWindows.size() == 1) {
			driver.switchTo().window(parentWindow);
			return true;
		}
		else
			return false;
	}
	
	public Object clickElementByJs(WebDriver driver,WebElement element) {
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	return js.executeScript("arguments[0].click();", element);
	    }catch(Exception e){
	    	e.getMessage();
	    	return null;
	    }
	}
	
	public void scrollToViewToClickElementByJs(WebDriver driver,WebElement element) {
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].scrollIntoView(true);", element);
	    	element.click();
	    }catch(Exception e){
	    	e.getMessage();
	    }
	}
	
	public Object sendKeyByJs(WebDriver driver,WebElement element, String value) {
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	    }catch(Exception e){
	    	e.getMessage();
	    	return null;
	    }
	}
	
	public Object removeAttributeInDOMByJs(WebDriver driver,WebElement element, String attribute) {
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	return js.executeScript("arguments[0].removeAttribute('" + attribute +"');", element);
	    }catch(Exception e){
	    	e.getMessage();
	    	return null;
	    }
	}
	
	public Object navigateToUrlByJs(WebDriver driver, String url) {
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	return js.executeScript("window.location='" + url + "'");
	    }catch(Exception e){
	    	e.getMessage();
	    	return null;
	    }
	}
	
	public Object executeJSForBrowser(WebDriver driver, String javaSript) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	
	public Object scrollToElement(WebDriver driver, String locator) {
        try {
        	WebElement element = driver.findElement(By.xpath(locator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	
	public Object scrollToBottomPage(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	
	public void waitForControlVisible(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForControlInvisible(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void doubleClicktoElement(WebDriver driver, String locator){
		WebElement element = driver.findElement(By.xpath(locator));	
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();		
	}
	
	public void clickAndholdtManyElements(WebDriver driver, String locator, int elementNumber){
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform(); 
		for(int i = 0; i< elementNumber; i++) {
			elements.get(i).click();
		}
		action.keyUp(Keys.CONTROL).build().perform();	
	}
	
	public void hoverMouse(WebDriver driver, String locator){
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(locator));
		action.moveToElement(element).perform();
	}

	public void rightClickElement(WebDriver driver, String locator){
		WebElement element = driver.findElement(By.xpath(locator));	
		Actions action = new Actions(driver);
		action.contextClick(element).perform();	
	}
	
	public void drapAnddropElement(WebDriver driver, String sourceLocator, String targetLocator){
		WebElement dragElementFrom = driver.findElement(By.xpath(sourceLocator));
		WebElement dropElementTo = driver.findElement(By.xpath(targetLocator));
		Actions action = new Actions(driver);
		action.clickAndHold(dragElementFrom).perform();
		action.release(dropElementTo).perform();
	}
	
	public int downLoadByWGet(WebDriver driver, String locator, String exePath, String downloadFolder){
		WebElement downloadBtn = driver.findElement(By.xpath(locator));
		String sourceLocation = downloadBtn.getAttribute("href");
        String wget_cmd = "cmd /c " + exePath + " -P " + downloadFolder +" --no-check-certificate " + sourceLocation;
		
        try {
			Process exec = Runtime.getRuntime().exec(wget_cmd);
			return exec.waitFor();
		}catch (InterruptedException | IOException ex){
			return -1;
		}		
	}
	
	public void downloadByAutoIT(){

	}
	
	public void uploadBySendKeys(WebDriver driver, String locator, String inputPath, String browser){		
		WebElement btnAddFiles = driver.findElement(By.xpath(locator));		
		if (browser == "Chrome" | browser == "IE")
			btnAddFiles.sendKeys(getStringFileNames(inputPath, "\n", "", true)); 
		else
		{		
			List<String> filePaths = getFileNamesList(inputPath, true);	
			for(String s:filePaths){
				btnAddFiles = driver.findElement(By.xpath(locator));
				btnAddFiles.sendKeys(s);
			}
		}
		
	}
	
	public String getStringFileNames(String location, String delimiter, String quote, boolean absolutePath ) {
		File folder = new File(location);
	    File[] files = folder.listFiles();
	    String sFilenames = "";
	    String path = "";
	    	    	
	    for(int i = 0; i < files.length; i++){
		    if (absolutePath == true)
		    	path = files[i].getAbsolutePath();
		    else
		    	path = files[i].getName();
		    
	    	if (files[i].isFile())
	    		sFilenames += (i != 0? quote + delimiter + quote: quote) + path;
	    }
	    sFilenames += quote;  
	    
	    return  sFilenames;
	}
	
	public List<String> getFileNamesList(String location, boolean absolutePath) {
		File folder = new File(location);
		File[] files = folder.listFiles(); 
	    List<String> filenamesList = new ArrayList<String>();
	    String path = "";
	    
	    for(int i = 0; i < files.length; i++){
	    	 if (absolutePath == true)
			    	path = files[i].getAbsolutePath();
			    else
			    	path = files[i].getName();
	    	if (files[i].isFile())
	    		filenamesList.add(path);
	    }	    
	    return  filenamesList;
	}
	/**
	 * uploadByAutoIT
		 * 		$CmdLine[1]: window name
		 * 		$CmdLine[2]: Path
		 * 		$CmdLine[3]: File names, delimiter is "|" instead of " " because AutoIT  will split by space
	 * @throws Exception: can test lai
	 */
	public void uploadByAutoIT(WebDriver driver, String locator, String browser, String inputPath, String exePath){
		String windowTitle = "Open";
		String manyFiles = getStringFileNames(inputPath, "|", "", false);
		
		if (browser != "Chrome")
			windowTitle = "File-Upload";
	
		driver.findElement(By.xpath(locator)).click();		
		try {
			Runtime.getRuntime().exec(exePath + " " + windowTitle + " " + inputPath  + " " + manyFiles);
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void uploadByRobot(WebDriver driver, String locator, String inputPath){
		driver.findElement(By.xpath(locator)).click();
		 try {
	            Robot robot = new Robot();
	        	setClipboardDataForPath(inputPath);
	            
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);  
	            robot.delay(1000); 
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            
	            setClipboardDatForFileName(inputPath);
	                        
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V); 
	            robot.delay(1000);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	        } catch (Exception exp) {
	        	exp.printStackTrace();
	        }

	}
	
	/**
     * This method will set any parameter string to the system's clip board.
     */
	public void setClipboardDataForPath(String path) {
	    StringSelection stringSelection = new StringSelection(path);	    	    
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public void setClipboardDatForFileName(String path) {
        StringSelection stringSelection = new StringSelection(getStringFileNames(path, " ", "\"", false));	    	    
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	private long shortTimeout = 10;
	private long longTimeout = 30;
}

