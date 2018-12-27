package pages;

import org.openqa.selenium.WebDriver;

import bankguru.RegisterPageUI;
import commons.AbstractPage;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Register Page : " + this.driver.toString());
	}

	public void inputToEmailTextbox(String inputValue) {
		waitForControlVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, inputValue);
	}
	
	public void clickToSubmitBtn() {
		waitForControlVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}
	
	public String getUserID() {
		waitForControlVisible(driver, RegisterPageUI.DYNAMIC_TEXT, "User ID :");
		return getTextElement(driver, RegisterPageUI.DYNAMIC_TEXT, "User ID :");
	}
	
	public String getPassword() {
		waitForControlVisible(driver, RegisterPageUI.DYNAMIC_TEXT, "Password :");
		return getTextElement(driver, RegisterPageUI.DYNAMIC_TEXT, "Password :");
	}

	public LoginPageObject openLoginPageByURL(String loginUrl) {
		openAnyURL(driver, loginUrl);
		return new LoginPageObject(driver);
	}


}
