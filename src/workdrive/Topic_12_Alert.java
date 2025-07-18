package workdrive;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenFirefox = projectPath + "\\AutoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\AutoIT\\authen_chrome.exe";
	String username = "admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		// 1 - có thể switch qua và tương tác luôn
		  //alert = driver.switchTo().alert();
		// 2 - cần wait trước khi nào nó xuất hiện thì mới switch qua và tương tác - suggest dùng - wait và switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		   // Verify alert title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// Accept cái Alert này
		alert.accept();
		sleepInSecond(3);
		//Verify message hiển thị tại result
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		// 1 - có thể switch qua và tương tác luôn
		  //alert = driver.switchTo().alert();
		// 2 - cần wait trước khi nào nó xuất hiện thì mới switch qua và tương tác - suggest dùng - wait và switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		   // Verify alert title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		// Accept cái Alert này
		alert.dismiss();
		sleepInSecond(3);
		//Verify message hiển thị tại result
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
		

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		// 1 - có thể switch qua và tương tác luôn
		  //alert = driver.switchTo().alert();
		// 2 - cần wait trước khi nào nó xuất hiện thì mới switch qua và tương tác - suggest dùng - wait và switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		   // Verify alert title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String courseName = "Fullstack Selenium Java";
		
		// nhập text vào alert
		alert.sendKeys(courseName);
		sleepInSecond(1);
		// Accept cái Alert này
		alert.accept();
		sleepInSecond(3);
		//Verify message hiển thị tại result
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
	}
	
	//@Test
	public void TC_04_Authentication_Alert_I() {
		driver.get("https://the-internet.herokuapp.com/");
		String authenUrl = driver.findElement(By.xpath("//a[text()=\"Basic Auth\"]")).getAttribute("href");
		
	// Truyền trực tiếp UserName / Password vào trong chính url này -> tự động sign in
		// http:// + Username : Password @ the-internet.herokuapp.com/basic_aut
		driver.get(passUserAndPassToUrl(authenUrl, "admin", "admin"));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), \"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
		
	}
	
	@Test
	//work cho những site không chấp nhận cách 1
	public void TC_04_Authentication_Alert_II() throws IOException {
		if (driver.toString().contains("firefox")) {
			//Runtime.getRuntime().exec: thực thi 1 file exe trong code Java
			Runtime.getRuntime().exec(new String[] { authenFirefox, username, password });	
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { authenChrome, username, password });
		}
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), \"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
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