package workdrive;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		driver.close();
		
		// Không quan tâm bao nhiều tab/window -> đóng browser
		driver.quit();
		
		
		//FIND ELEMENT
		   //Có thể lưu vào 1 biến để sử dụng cho các step sau
		     // clean code
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		    // Bad code
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		
		 //Có thể sử dụng luôn (không cần tạo biến) - dùng 1 lần ko cần tạo biến
		driver.findElement(By.xpath("//buton[@id='login']")).click();	
		
		//FIND ELEMENT số nhiều
		List<WebElement> Checkboxes = driver.findElements(By.xpath(""));
		
		
		
		
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