package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		//Executable file
		//Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Edge
		System.setProperty("webdriver.Edge.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Empty_Data() {
	}

	@Test
	public void TC_02_Invalid_Email() {
	}

	@Test
	public void TC_03_Incorrect_Email() {
	}
	
	@Test
	public void TC_04_Invalid_Password() {
	}
	
	@Test
	public void TC_05_Incorrect_Password() {
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}