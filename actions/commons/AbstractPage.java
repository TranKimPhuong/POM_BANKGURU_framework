package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


// con userinteraction va uplpad download
public class AbstractPage {
	public void openAnyURL(WebDriver driver, String url) {
		driver.get(url);
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
	
	
	
	
	private long shortTimeout = 10;
	private long longTimeout = 30;
}

