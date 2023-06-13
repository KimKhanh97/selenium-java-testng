package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_selenium_locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register");
	}
	// Code HTML của FirstName textbox
	// <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
	// Tên thẻ của element (TagName HTML): input
	// Tên thuộc tính (Attribute name): type data-val data-val-required id name
	// Giá trị thuộc tính (Attribute value): text true First name is required. FirstName FirstName

	@Test
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/basic-form/");
		//Java: System.out.println -> sysout ctrl+space
		//C#: Console.WriteLine
		//JS: Console.log
		
		System.out.println("Text của thẻ H5: " + driver.findElement(By.xpath("//h5[id='nested']")).getText());
		
	}

	@Test
	public void TC_02_Class() {
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}