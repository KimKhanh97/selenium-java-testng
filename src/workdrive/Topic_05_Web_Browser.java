package workdrive;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
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
		
		//Tương tác với browder thì thông qua biến WebDriver driver
		//Tương tác với Element thì thông qua biến WebElement element
		
	}

	@Test
	public void TC_01_() {
		// Java document (cách sử dụng hàm này như thế nào)
		
		
		// >=2: nó sẽ đóng tab/window mà nó đang đứng
		// =1: đóng browser
		driver.close(); //*
		
		// Không quan tâm bao nhiều tab/window -> đóng browser
		driver.quit(); //**
		
		
		//FIND ELEMENT
		   //Có thể lưu vào 1 biến để sử dụng cho các step sau
		     // clean code
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']")); //**
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		    // Bad code
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		
		 //Có thể sử dụng luôn (không cần tạo biến) - dùng 1 lần ko cần tạo biến
		driver.findElement(By.xpath("//buton[@id='login']")).click();	
		
		//FIND ELEMENT số nhiều
		List<WebElement> Checkboxes = driver.findElements(By.xpath("")); //*
		
		//Mở ra 1 URL bất kì
		driver.get("https://www.facebook.com/"); //**
		
		
		
		 // Trả về URL của page hiện tại
		    // Có thể lưu vào 1 biến để sử dụng cho các step sau
		String vietnamesePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(vietnamesePageUrl,"https://vi-vn.facebook.com/");
				
		
	   	   // Có thể sử dụng luôn (không cần tạo biến) - suggestion use
		Assert.assertEquals(driver.getCurrentUrl(),"https://vi-vn.facebook.com/");
		
		// Trả về source code HTML của page hiện tại - HTML/CSS/JS
		  // Verify tương đối
		driver.getPageSource();
		
		Assert.assertTrue(driver.getPageSource().contains("Trần Thị Kim Khánh"));
		Assert.assertTrue(driver.getPageSource().contains("Kim Khánh"));
		Assert.assertTrue(driver.getPageSource().contains("Trần Thị"));
		
		// Trả về title của page hiện tại
		driver.getTitle();
		
		Assert.assertEquals(driver.getTitle(),"Facebook");
		
		
		// WebDriver API - Window/ Tabs
	   	  // Lấy ra ID của window/tab mà driver đang đứng(active)
		String loginWindowID = driver.getWindowHandle(); //**
		
		  // Lấy ra ID của tất cả window/tab
		Set<String> AllIDs = driver.getWindowHandles(); //*
		
		// Cookie/ Cache
		Options opt = driver.manage();
		
		// Login thành công - lưu lại
		opt.getCookies(); //*
		
		// Test case khác --> set cookie vào lại --> không cần login nữa
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		// Khoảng thời gian chờ cho element xuất hiện trong vòng x giây
		   //5s = 5000ms = 5000000 micro second
		time.implicitlyWait(5, TimeUnit.SECONDS); //**
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		// Khoảng thời gian chờ page load xong trong vòng x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		
		// Khoảng thời gian chờ script được thực thi trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		
		Window win = (Window) opt.window();
		win.maximize(); //**
		win.fullscreen();
		
		// Test GUI: Font/Size/Color/Position/Locatio...
		   // set position set trình duyệt nằm ở tọa độ nào so với độ phân giải của máy
		win.getPosition();
		
		   // setSize là set kích thước bên trong
		win.getSize();
		
		Navigation nav = driver.navigate();
		nav.back();  // back icon của browser
		nav.refresh();  // load page icon của browser
		nav.forward();  // đi tiếp icon của browser
		nav.to("https://facebook.com/"); // giống => Mở ra 1 URL bất kì driver.get("https://www.facebook.com/"); nhưng nav.to support history tốt hơn cho nav.
		
		TargetLocator tar = driver.switchTo();
		
		tar.alert(); //*
		
		tar.frame(""); //*
		
		tar.window("");//*
	}

	@Test
	public void TC_02_Invalid_Email() {
	}

	@Test
	public void TC_03_Incorrect_Email() {
	}
	
	@Test
	public void TC_04_Invalid_Password() {
	}
	
	@Test
	public void TC_05_Incorrect_Password() {
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}