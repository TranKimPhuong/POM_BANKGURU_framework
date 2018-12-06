package pages;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	WebDriver driver;
		
	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Home Page : " + this.driver.toString());
	}

	public boolean isHomePageDisplayed() {
		waitForControlVisible(driver, HomePageUI.HOME_PAGE_HEADING);
		return isControlDisplayed(driver, HomePageUI.HOME_PAGE_HEADING);
	}

	public void clickToLogoutLink() {
		waitForControlVisible(driver, HomePageUI.LOGOUT_LINK);
		clickToElement(driver, HomePageUI.LOGOUT_LINK);
		acceptAlert(driver);
	}

}
