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
		// Thao tác lên element thì đầu tiên phải tìm được element đó: findElement
		// Find theo cái gì: id/class/name/css/xpath/...
		// Find tìm thấy element rồi thì action lên element đó: click/sendkey/...
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_Class() {
		//Mở màn hình search
		driver.get("https://demo.nopcommerce.com/search");
		// Nhập text vào search textbox
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		//Click vào advances Search checkbox
		driver.findElement(By.name("advs")).click();
	}
	@Test
	public void TC_04_TagName() {
		// tìm bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	@Test
	public void TC_05_LinkText() {
		// CLick vào đường link Addresses(truyền vào text tuyệt đối)
		driver.findElement(By.linkText("Addresses")).click();
	}
	@Test
	public void TC_06_PartialLinkText() {
		// CLick vào đường link Apply for vendor account (truyền vào text tương đối)
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	@Test
	public void TC_07_CSS() {
		// Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		//3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("automation@gmail.com");
		
	}
	@Test
	public void TC_08_Xpath() {
		// Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		//2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
		//3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("automation@gmail.com");
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}