package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class AbstractTest {
	WebDriver driver;

	public WebDriver openMultiBrowsers( String browser) {
		 switch (browser)
		  {
		  	case "firefox":
			  	driver = new FirefoxDriver();
			  	break;
			  	
		  	case "chrome":
				System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver.exe");
				driver = new ChromeDriver();
			  	break;
			  	
		  	case "headless":
		  	    System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver.exe");

 	    	    ChromeOptions chromeOptions = new ChromeOptions();
 	    	    chromeOptions.addArguments("headless");
 	    	    chromeOptions.addArguments("window-size=1212x911");

 	    	    driver = new ChromeDriver(chromeOptions);
			  	break;
			  	
		  	case "IE":
				System.setProperty("webdriver.ie.driver",".//Browsers//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
		  		break;
		  }
		  driver.get(Constants.DEV_URL);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  return driver;
	}
	
	public int PickNumberRandom(int limit) {
		Random rd = new Random();
		return rd.nextInt(limit);
	}
}
