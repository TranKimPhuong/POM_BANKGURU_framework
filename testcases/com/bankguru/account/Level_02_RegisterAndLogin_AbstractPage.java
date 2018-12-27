package com.bankguru.account;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.Constants;

public class Level_02_RegisterAndLogin_AbstractPage {
	private WebDriver driver;
	private String loginPageURL;
	private AbstractPage abstractPage; 
	
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  abstractPage = new AbstractPage();
	  abstractPage.openAnyURL(driver, Constants.DEV_URL);
	  email = "automationtest" + PickNumberRandom(999) +"@gmail.com";	  
  }
  
  @Test
  public void TC_01_RegisterToSytem() {
	  loginPageURL = abstractPage.getCurrentPageUrl(driver);
	  abstractPage.clickToElement(driver, "//a[text() = 'here']");
	  abstractPage.sendkeyToElement(driver, "//input[@name = 'emailid']", email);
	  abstractPage.clickToElement(driver, "//input[@name = 'btnLogin']");
	  userID = abstractPage.getTextElement(driver, "//td[text() = 'User ID :']/following-sibling::td");
	  password = abstractPage.getTextElement(driver, "//td[text() = 'Password :']/following-sibling::td");
  }

  @Test
  public void TC_02_LoginToSytem() {
	  abstractPage.openAnyURL(driver, loginPageURL);
	  abstractPage.sendkeyToElement(driver, "//input[@name = 'uid']", userID);
	  abstractPage.sendkeyToElement(driver, "//input[@name = 'password']", password);
	  abstractPage.clickToElement(driver, "//input[@name = 'btnLogin']");  
	  Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//marquee[text() = \"Welcome To Manager's Page of Guru99 Bank\"]"));
  }
  
  @Test
  public void TC_03_Logout() {
	  abstractPage.clickToElement(driver, "//a[text() = 'Log out']");
	  abstractPage.acceptAlert(driver);	  
	  Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//form[@name = 'frmLogin']"));
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  private String email, userID, password;
	public int PickNumberRandom(int limit) {
		Random rd = new Random();
		return rd.nextInt(limit);
	}

}
