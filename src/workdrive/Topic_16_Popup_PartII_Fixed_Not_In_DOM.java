package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_PartII_Fixed_Not_In_DOM {
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
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		
		//implicitlyWait: nó sẽ ảnh hưởng tới việc đi tìm element
		//findElement/findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		
		//By: nó chưa có đi tìm element
		By loginPopup = By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 jyAQAr']/parent::div");
		
		//Verify nó chưa hiển thị khi chưa click vào login button
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		//Click cho bật goin popup lên
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInSecond(3);
	}

	//@Test
	public void TC_01_Fixed_Not_In_DOM_Facebook() {
		
	}

	//1000ms = 1s
			public void sleepInSecond(long timeInsecond) {
					try {
						Thread.sleep(timeInsecond * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}