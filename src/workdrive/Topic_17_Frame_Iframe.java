package workdrive;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Frame_Iframe {
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
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		// Veri Facebook iframe hiển thị
		// Facebook iframe vẫn là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		// Cần phải switch vào đúng cái thẻ iframe chưa các element đó
	     //driver.switchTo().frame(0); ==> frame có thể bị thay đổi vị trí
	     //driver.switchTo().frame("Name or id"); ==> frame ko có name ko có id
	     driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
	     
	     String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
	     System.out.println(facebookLike);
	     
	     Assert.assertEquals(facebookLike, "167K followers");
	     
	     // Cần switch về main page
	     driver.switchTo().defaultContent();
	     // Từ main page switch qua iframe chat
	     driver.switchTo().frame("cs_chat_iframe");
	     //Click chat để show lên chat support
	     driver.findElement(By.cssSelector("div.button_bar")).click();
	     sleepInSecond(40);
	     
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Kim Khánh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0766625039");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tư vấn khóa học Excel");
		sleepInSecond(3);
		
		 //Từ Chat iframe Switch về main page
		 driver.switchTo().defaultContent();
		 // Search với từ khóa excel
		 driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		 driver.findElement(By.cssSelector("button.search-button")).click();
		 
		 List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		 
		 for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		 
	}

	//@Test
	public void TC_02_HDFC_Bank_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("KimKhanh");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
		driver.findElement(By.cssSelector("")).sendKeys("");
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