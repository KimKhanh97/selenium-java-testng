package workdrive;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

public class Topic_21_Upload_AutoIT {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_One_File_Per_Time_AutoIT() throws IOException { 
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// Click bật Open file dialog lên
		driver.findElement(By.cssSelector("span.btn-success")).click();
		
		
		//Load file lên
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[]{autoITFireFoxOneTimePath, handFilePath});
		} else {
			Runtime.getRuntime().exec(new String[]{autoITChromeOneTimePath, handFilePath});
		}
		
		// Verify file được load lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + handFileName + "']")).isDisplayed());
		//Click Upload
		List <WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadButton) {
			button.click();
			sleepInSecond(5);
		}
		
		//Verify Upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + handFileName + "']")).isDisplayed());
		//Verify Upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + handFileName + "')]"));
	}
	
	//@Test
	public void TC_02_ultiple_File_Per_Time_AutoIT() throws IOException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // Click bật Open file dialog lên
        driver.findElement(By.cssSelector("span.btn-success")).click();
       //Load file lên
    	if (driver.toString().contains("firefox")) {
    		Runtime.getRuntime().exec(new String[]{autoITFireFoxMultipleTimePath, handFilePath, loveFilePath, mountainFilePath});
    	} else {
    		Runtime.getRuntime().exec(new String[]{autoITChromeMultipleTimePath, handFilePath, loveFilePath, mountainFilePath});
    	}
		
		// Verify file được load lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + handFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + loveFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountainFileName + "']")).isDisplayed());
		//Click Upload
		List <WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadButton) {
			button.click();
			sleepInSecond(5);
		}
		
		//Verify Upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + handFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + loveFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + mountainFileName + "']")).isDisplayed());
		//Verify Upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + handFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + loveFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + mountainFileName + "')]"));
			
	}

	//@Test
	public void TC_03_One_File_Per_Time_Robot() throws IOException, AWTException { 
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	// Click bật Open file dialog lên
		driver.findElement(By.cssSelector("span.btn-success")).click();
	
	//Load file lên
		loadFileByRobot(handFilePath);			
	// Verify file được load lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + handFileName + "']")).isDisplayed());	
	//Click Upload
		List <WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadButton) {
			button.click();
			sleepInSecond(5);
	}		
	//Verify Upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + handFileName + "']")).isDisplayed());
	//Verify Upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + handFileName + "')]"));
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
	public void loadFileByRobot(String filePath) throws AWTException {
		// Copy file path
		StringSelection select = new StringSelection(filePath);
		Toolkit .getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// Load file
		Robot robot = new Robot();
		sleepInSecond(1);
		
		
		// Nhấn xuống Ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		// Nhả Ctrl + V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);
		
		//Nhấn Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_V);
		sleepInSecond(1);
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