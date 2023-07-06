package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
		element.clear(); //*
		// Dùng cho các textbox/textarea/dropdown (editable)
		// Nhập liệu
		element.sendKeys(""); //**
		//Click vào các button/link/checkbox/radio/image...
		element.click(); //**
		
		   // search store
		String searchAttribute = element.getAttribute("placeholder");  //**
		
		// get bất kì attribute nào của css
		element.getCssValue("background-color"); //*
		
		// Vị trí của element so với web (bên ngoài)
		Point point = element.getLocation();
		point.x = 324;
		point.y = 324;
		
		// Kích thước của element (bên trong)
		Dimension dimension = element.getSize();
		dimension.getHeight();
		dimension.getWidth();
		
		System.out.println(dimension.height);
		System.out.println(dimension.width);
		
		//Location + Size
		Rectangle Re = element.getRect();
		
		// Chụp hình khi TCS fail
		element.getScreenshotAs(OutputType.FILE);  //*
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		
		// Lấy ra tên thẻ HTML của element đó -> truyền vào cho 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		String emailTextboxTagname = driver.findElement(By.cssSelector("Email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));
		
		element.getTagName();
		
		// Lấy text từ Error message/success message/label/ header/...
		element.getText(); //**
		
		// Khi nào dùng getText - geAttribute
		// Khi cái value mình cần lấy nó nằm bên ngoài -> getText
		// Khi cái value mình cần lấy nó nằm bên trong -> getAttribute
		
		
		// Verify xem 1 element hiển thị hoặc không
		   // Phạm vi: Tất cả các element
		Assert.assertTrue(element.isDisplayed()); //**
		Assert.assertFalse(element.isDisplayed());
		
		
		// Dùng để verify xem 1 element có thao tác được hay ko
		  // Phạm vi: Tất cả các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Dùng để verify xem 1 element có được chọn hay chưa
		  // Phạm vi: checkbox/radio
		Assert.assertTrue(element.isSelected()); //*
		Assert.assertFalse(element.isSelected());
		
		// Các element nằm trong thẻ form
		// Tương ứng với end user (enter)
		element.submit();
		
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