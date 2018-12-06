package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewAccountPageObject extends AbstractPage {
	WebDriver driver;
		
	public NewAccountPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("NewAccount Page : " + this.driver.toString());
	}
}
