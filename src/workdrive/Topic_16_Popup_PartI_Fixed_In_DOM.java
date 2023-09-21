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

public class Topic_16_Popup_PartI_Fixed_In_DOM {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Fixed_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(10);
		By loginPopup = By.xpath("//div[@class='modal fade in']//div[@class='modal-content']");
		//Verify popup is unDisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		//Click vào button Login
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		//Verify popup isDisplayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='password-input']']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");

	}

	@Test
	public void TC_01_Fixed_In_DOM_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		//Verify popup is unDisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		//Click vào button Login
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		//Verify popup isDisplayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(2);
		//Verify popup is unDisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

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
		driver.quit();
	}
}