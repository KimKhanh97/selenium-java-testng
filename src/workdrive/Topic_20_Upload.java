package workdrive;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Upload {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	String handFileName = "hand.jpg";
	String loveFieName = "Love.jpg";
	String mountainFileName = "mountain.jpg";
	
	String handFilePath = projectPath + "\\uploadFile\\" + handFileName;
	String loveFilePath = projectPath + "\\uploadFile\\" + loveFieName;
	String mountainFilePath = projectPath + "\\uploadFile\\" + mountainFileName;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file lên
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(mountainFilePath);
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(loveFilePath);
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(handFilePath);
		sleepInSecond(2);
		
		// Verify file được load lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + handFilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + loveFilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountainFilePath + "']")).isDisplayed());
		//Click Upload
		List <WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadButton) {
			button.click();
			sleepInSecond(1);
		}
		
		//Verify Upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + handFilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + loveFilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + mountainFilePath + "']")).isDisplayed());
		//Verify Upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + handFilePath + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + loveFilePath + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + loveFilePath + "')]"));
	}
	
	@Test
	public void TC_02_One_File_Per_Time() {
			
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
		//driver.quit();
	}
	
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}