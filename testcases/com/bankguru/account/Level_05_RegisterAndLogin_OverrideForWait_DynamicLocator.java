package com.bankguru.account;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManager;
import pages.DepositPageObject;
import pages.EditAccountPageObject;
import pages.EditCustomerPageObject;
import pages.HomePageObject;
import pages.LoginPageObject;
import pages.NewAccountPageObject;
import pages.NewCustomerPageObject;
import pages.RegisterPageObject;

public class Level_05_RegisterAndLogin_OverrideForWait_DynamicLocator extends AbstractTest{
		
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = openMultiBrowsers(browser);
		
		loginPage = PageFactoryManager.initLoginPage(driver);
		email = "automationtest" + PickNumberRandom(999) + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSytem() {
		loginPageURL = loginPage.getCurrentLoginPageUrl();
		registerPage = loginPage.clickToHereLink();	
		registerPage.inputToEmailTextbox(email);
		registerPage.clickToSubmitBtn();
		userID = registerPage.getUserID();
		password = registerPage.getPassword();
		System.out.println(userID);
		System.out.println(password);
	}

	@Test
	public void TC_02_LoginToSytem() {
		loginPage = registerPage.openLoginPageByURL(loginPageURL);		
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginBtn();
		
		// kiem tra time khi wait 1 element invisible v� enable
		// undisplayed, invisible
		Assert.assertTrue(loginPage.isLoginPageNotDisplayed());
		
		//displayed , visible
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}

	@Test
	public void TC_03_openMultiPages() {
		newCustomerPage = homePage.openNewCustomerPage(driver);
		editCutomerPage = newCustomerPage.openEditCustomerPage(driver);
		newAccountPage = editCutomerPage.openNewAccountPage(driver);
		editAccountPage = newAccountPage.openEditAccountPage(driver);
		depositPage = editAccountPage.openDepositPage(driver);
		homePage = depositPage.openHomePage(driver);
	}
	
	@Test
	public void TC_04_Logout() {		
		loginPage = homePage.clickToLogoutLink(driver);
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		quitBrowser(driver);
	}

	private String email, userID, password;
	private WebDriver driver;
	private String loginPageURL;

	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCutomerPage;
	private NewAccountPageObject newAccountPage;
	private EditAccountPageObject editAccountPage;
	private DepositPageObject depositPage;

}
