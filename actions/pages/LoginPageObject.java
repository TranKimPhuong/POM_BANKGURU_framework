package pages;

import org.openqa.selenium.WebDriver;

import bankguru.LoginPageUI;
import commons.AbstractPage;

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
	
	public void clikcToLoginBtn() {
		waitForControlVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	
	public void clickToHereLink() {
		waitForControlVisible(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
	}
	
	public boolean isLoginPageDisplayed() {
		waitForControlVisible(driver, LoginPageUI.LOGIN_PAGE_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_PAGE_FORM);
	}

	public String getCurrentLoginPageUrl() {
		return getCurrentPageUrl(driver);
	}
	
	public void openLoginPage(String loginUrl) {
		openAnyURL(driver, loginUrl);
	}
}
