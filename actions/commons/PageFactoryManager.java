package commons;

import org.openqa.selenium.WebDriver;

import pages.DepositPageObject;
import pages.EditAccountPageObject;
import pages.EditCustomerPageObject;
import pages.HomePageObject;
import pages.LoginPageObject;
import pages.NewAccountPageObject;
import pages.NewCustomerPageObject;
import pages.RegisterPageObject;

public class PageFactoryManager {
	public static LoginPageObject initLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static RegisterPageObject initRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static HomePageObject initHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static NewCustomerPageObject initNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	
	public static EditCustomerPageObject initEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
	
	public static NewAccountPageObject initNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
		
	public static EditAccountPageObject initEditAccountPage(WebDriver driver) {
		return new EditAccountPageObject(driver);
	}
	
	public static DepositPageObject initDepositPage(WebDriver driver) {
		return new DepositPageObject(driver);
	}
}
