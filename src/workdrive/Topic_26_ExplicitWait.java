package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	String handFileName = "hand.jpg";
	String loveFileName = "Love.jpg";
	String mountainFileName = "mountain.jpg";
	
	String handFilePath = projectPath + "\\uploadFile\\" + handFileName;
	String loveFilePath = projectPath + "\\uploadFile\\" + loveFileName;
	String mountainFilePath = projectPath + "\\uploadFile\\" + mountainFileName;
	
	String autoITFireFoxOneTimePath = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String autoITChromeOneTimePath = projectPath + "\\AutoIT\\chromeUploadOneTime.exe";
	String autoITFireFoxMultipleTimePath = projectPath + "\\AutoIT\\firefoxUploadMultiple.exe";
	String autoITChromeMultipleTimePath = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
	
		explicitWait = new WebDriverWait(driver, 5);
				
		
		//Click vào start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	//@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Apply 5s cho các điều kiện/ trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 5);
		
		//Click vào start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Đủ thời gian để cho 1 element tiếp theo hoạt động
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 20);
		//Wait cho date picker hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.RadCalendar")));
		
		// Verify cho Selected Dates là không có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(), "No Selected Dates to display.");
		//Wait cho ngày 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='20']")));
		//Click ngày 20
		driver.findElement(By.xpath("//a[text()='20']")).click();
		// Waiting cho Ajax icon loading biến mất (Invislble)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		// Wait cho ngày vừa được chọn là được phép click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='20']")));
		//Verify cho Selected Date là "Wednesday, December 20, 2023"
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(), "Wednesday, December 20, 2023");	
	}
	
	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/welcome");
		
		
		//Wait cho Add files button được visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
	driver.findElement(By.cssSelector("input[type='file']")).sendKeys(handFilePath + "\n" + loveFilePath + "\n" + mountainFilePath);
	
	//Wait cho từng loadi icon của từng file biến mất
	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("explicitWait.until(ExpectedConditions.")));
	
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