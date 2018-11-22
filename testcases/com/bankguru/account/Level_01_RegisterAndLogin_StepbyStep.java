package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.Constants;

// tối đa 5 -10 -15 testcases/class
public class Level_01_RegisterAndLogin_StepbyStep {
	WebDriver driver;
	String loginPageURL;
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.get(Constants.DEV_URL);
	  email = "automationtest" + PickNumberRandom(999) +"@gmail.com";	  
  }
  
  @Test
  public void TC_01_RegisterToSytem() {
	  loginPageURL = driver.getCurrentUrl();
	  driver.findElement(By.xpath("//a[text() = 'here']")).click();
	  
	  driver.findElement(By.xpath("//input[@name = 'emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();
	  
	  userID = driver.findElement(By.xpath("//td[text() = 'User ID :']/following-sibling::td")).getText();
	  password = driver.findElement(By.xpath("//td[text() = 'Password :']/following-sibling::td")).getText();
  }

  @Test
  public void TC_02_LoginToSytem() {
	  driver.get(loginPageURL);
	  
	  driver.findElement(By.xpath("//input[@name = 'uid']")).sendKeys(userID);
	  driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text() = \"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
  }
  
  @Test
  public void TC_03_Logout() {
	  
	  driver.findElement(By.xpath("//a[text() = 'Log out']")).click();
	  driver.switchTo().alert().accept();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@name = 'frmLogin']")).isDisplayed());
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
