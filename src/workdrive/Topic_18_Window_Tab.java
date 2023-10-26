package workdrive;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window_Tab {
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
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ID() {
		//Parent page
		driver.get("https://automationfc.github.io/basic-form/");
		//Window/Tab nó sẽ có 2 hàm để lấy ra ID của Window/Tab đó
		// 1 - Nó sẽ ấy ra ID của Tab/ Window mà nó đang đứng (active)
		
		String basicFormID = driver.getWindowHandle();
		System.out.println(basicFormID);
		
		
		// 2 - Nó sẽ lấy ra tất cả ID ===> chỉ có duy nhất 1 tab thì ko dùng hàm này
		//Set<String> allWindowIDs = driver.getWindowHandles();
		
		
		// Click vào google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(5);
		// Lấy hết tất cả các ID ra
		// Nếu như cái ID nào mà khác với ID của parent thì switch
		switchToWindowByID(basicFormID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		//Lấy ID của Window/Tab google - Vì sao dùng hàm này lại -> lấy ra được ID của tab goolge?? => vì driver đang đừng ớ Window/Tab active
		String googleWindowID = driver.getWindowHandle();
		System.out.println(googleWindowID);
		switchToWindowByID(googleWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
	}
	@Test
	public void TC_02_Title() {
		//Parent page
		driver.get("https://automationfc.github.io/basic-form/");
		//Window/Tab nó sẽ có 2 hàm để lấy ra ID của Window/Tab đó
		
		// Click vào google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(5);
		switchToWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
		
		// Click vào Facebook link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		switchToWindowByPageTitle("Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("bupbekotinhyeu20885@yahoo.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
		
		// Click vào Tiki link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("MacBook");
		
	}
	
	@Test
	public void TC_03_Live_Guru() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		//Click vào Xperia - Compare
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
	}
	
	// Case 1: chỉ có duy nhất 2 ID (Window/Tab)
	public void switchToWindowByID(String otherID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String ID : allWindowIDs) {
			if (!ID.equals(otherID)) {
				driver.switchTo().window(ID);
				sleepInSecond(2);
			}
		}
	}

	
	// Case 2: Dùng được cho từ 2 ID trở lên (Window/Tab)
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			// Switch từng ID trước
			driver.switchTo().window(ID);
			//Lấy ra title của page này
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				sleepInSecond(2);
				break;
			}
		}
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