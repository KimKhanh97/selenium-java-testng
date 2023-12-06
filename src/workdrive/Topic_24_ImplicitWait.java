package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ImplicitWait {
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
		
		//1-Ảnh hưởng trực tiếp đến hàm: findElement/findElements
		//2- Ngoại lệ
		// ImplicitWait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gám lại thì sẽ dùng cái giá trị mới/không dùng giá trị cũ
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào start button
		driver.findElement(By.cssSelector("div#start")).click();
		
		
		//Loading mất 5s mới biến mất
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào start button
		driver.findElement(By.cssSelector("div#start")).click();
		
		
		//Loading mất 5s mới biến mất
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào start button
		driver.findElement(By.cssSelector("div#start")).click();
		
		
		//Loading mất 5s mới biến mất
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
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