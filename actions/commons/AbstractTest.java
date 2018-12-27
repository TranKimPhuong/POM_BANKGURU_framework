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
		  		System.setProperty("webdriver.gecko.driver", ".//resources//geckodriver.exe");
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
			  	
		  	case "ie":
				System.setProperty("webdriver.ie.driver",".//resources//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
		  		break;
		  }
		  driver.get(Constants.DEV_URL);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(AbstractPage.longTimeout, TimeUnit.SECONDS);
		  return driver;
	}
	
	protected void quitBrowser(WebDriver driver) {
		try {
			//IE-11
			driver.manage().deleteAllCookies();
			//quit browser
			driver.quit();
			//Quit process
			String osName = System.getProperty("os.name").toLowerCase();
			String cmd = "";
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.contains("mac")) {
					cmd = "pkill chromedriver";
				}else {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			
			if (driver.toString().toLowerCase().contains("firefox")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public int PickNumberRandom(int limit) {
		Random rd = new Random();
		return rd.nextInt(limit);
	}
}
