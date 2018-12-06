package pages;

import org.openqa.selenium.WebDriver;

import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Login Page : " + this.driver.toString());
	}
		
	public void inputToUserIDTextbox(String inputValue) {
		waitForControlVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, inputValue);
	}
	
	public void inputToPasswordTextbox(String inputValue) {
		waitForControlVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, inputValue);
	}
	
	public HomePageObject clickToLoginBtn() {
		waitForControlVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageFactoryManager.initHomePage(driver);
	}

	public boolean isLoginPageDisplayed() {
		waitForControlVisible(driver, LoginPageUI.LOGIN_PAGE_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_PAGE_FORM);
	}

	public String getCurrentLoginPageUrl() {
		return getCurrentPageUrl(driver);
	}
	
	public RegisterPageObject clickToHereLink() {
		waitForControlVisible(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageFactoryManager.initRegisterPage(driver);
	}
}
