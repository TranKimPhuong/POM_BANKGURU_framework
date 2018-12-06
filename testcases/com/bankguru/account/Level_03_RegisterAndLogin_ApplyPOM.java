package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pages.HomePageObject;
import pages.LoginPageObject;
import pages.RegisterPageObject;

public class Level_03_RegisterAndLogin_ApplyAbstractPage_ApplyPOM extends AbstractTest{
	private WebDriver driver;
	private String loginPageURL;

	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = openMultiBrowsers(browser);
		
		loginPage = new LoginPageObject(driver);
		email = "automationtest" + PickNumberRandom(999) + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSytem() {
		loginPageURL = loginPage.getCurrentLoginPageUrl();
		loginPage.clickToHereLink();

		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextbox(email);
		registerPage.clikcToSubmitBtn();
		userID = registerPage.getUserID();
		password = registerPage.getPassword();
	}

	@Test
	public void TC_02_LoginToSytem() {
		registerPage.openLoginPageFromAnotherPage(loginPageURL);		
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clikcToLoginBtn();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}

	@Test
	public void TC_03_openMultiPages() {
		
	}
	
	@Test
	public void TC_04_Logout() {
		homePage.clickToLogoutLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private String email, userID, password;

}
