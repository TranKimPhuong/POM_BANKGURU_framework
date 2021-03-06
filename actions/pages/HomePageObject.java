package pages;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;

public class HomePageObject extends AbstractPage {
	WebDriver driver;
		
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Home Page : " + this.driver.toString());
	}

	public boolean isHomePageDisplayed() {
		waitForControlVisible(driver, HomePageUI.HOME_PAGE_HEADING);
		return isControlDisplayed(driver, HomePageUI.HOME_PAGE_HEADING);
	}
}
