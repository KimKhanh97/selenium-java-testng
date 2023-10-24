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

	@Test
	public void TC_01_() {
		//Parent page
		driver.get("https://automationfc.github.io/basic-form/");
		//Window/Tab nó sẽ có 2 hàm để lấy ra ID của Window/Tab đó
		// 1 - Nó sẽ ấy ra ID của Tab/ Window mà nó đang đứng (active)
		String parentPageWindowID = driver.getWindowHandle();
		System.out.println(parentPageWindowID);
		
		
		// 2 - Nó sẽ lấy ra tất cả ID ===> chỉ có duy nhất 1 tab thì ko dùng hàm này
		//Set<String> allWindowIDs = driver.getWindowHandles();
		
		
		// Click vào google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(5);
		
		// Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String ID : allWindowIDs) {
			if (!ID.equals(parentPageWindowID)) {
				driver.switchTo().window(ID);
			}
		}
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		// Nếu như cái ID nào mà khác với ID của parent thì switch
		
		
		
		// Case 1: chỉ có duy nhất 2 window hoặc 2 tab
		
		
		
		// Case 2: Nhiều hơn 2 window / 2 tab
	}

	@Test
	public void TC_02_() {
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