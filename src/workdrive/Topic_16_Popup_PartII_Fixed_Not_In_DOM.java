package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
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

	//@Test
	public void TC_01_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
	
		//By: nó chưa có đi tìm element
		By loginPopup = By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 jyAQAr']/parent::div");
		
		//Verify nó chưa hiển thị khi chưa click vào login button
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		//Click cho bật goin popup lên
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		//Close Popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		//Verify popup ko hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	@Test
	public void TC_02_Fixed_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		
		By CreateAccPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		Assert.assertEquals(driver.findElements(CreateAccPopup).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElements(CreateAccPopup).size(), 1);
		
		
		driver.findElement(By.name("lastname")).sendKeys("Khanh");
		driver.findElement(By.name("firstname")).sendKeys("Tran");
		driver.findElement(By.name("reg_email__")).sendKeys("0766625039");
		driver.findElement(By.name("reg_passwd__")).sendKeys("0123456789@K");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("9");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Sep");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1997");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(CreateAccPopup).size(), 0);
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