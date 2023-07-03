package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}
        // Driver đã được khởi tạo rồi => không bị lỗi can not instantiate class
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_WebElement() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		WebElement element = driver.findElement(By.className(""));
		// Dùng cho các textbox/textarea/dropdown (editable)
		// Xóa dữ liệu đi trước khi nhập text
		element.clear();
		// Dùng cho các textbox/textarea/dropdown (editable)
		// Nhập liệu
		element.sendKeys("");
		//Click vào các button/link/checkbox/radio/image...
		element.click();
		
		   // search store
		String searchAttribute = element.getAttribute("placeholder");
		
		// get bất kì attribute nào của css
		element.getCssValue("background-color");
		
		
		
		
		// Khi mà element này được dùng lại nhiều lần -> khai báo biến
		WebElement emailTextbox = driver.findElement(By.id("Email"));
		emailTextbox.isDisplayed();
		emailTextbox.click();
		emailTextbox.sendKeys("");
		// Khi dùng 1 lần -> không cần
		driver.findElement(By.id("Email")).sendKeys("");
		
		
		
		// Tương tác được với Element thì cần phải tìm được element đó
		// Thông qua các locator của nó
		// By: id/class/name/xpath/css/tagname/linkText/partialLinkText
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}