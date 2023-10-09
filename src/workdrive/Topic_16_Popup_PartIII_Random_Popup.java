

package workdrive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_PartIII_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testDemo" + getRandomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		
		//implicitlyWait: nó sẽ ảnh hưởng tới việc đi tìm element
		//findElement/findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	//Yêu cầu:
	// Random popup nên có thể hiển thị 1 cách ngẫu nhiên hoặc ko hiển thị
	// Nếu như nó hiển thị thì mình cần thao tác lên popup -> đóng nó điđể qua step tiếp theo
	// Khi nà đóng nó lại thì nó có thể refresh trang để nó hiện lên lại/ hoặc là ko
	// Nếu như nó ko hiển thị thì qua step tiếp theo luôn

	@Test
	public void TC_01_Random_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		// vì nó luôn có trong DOM nên cỏ thể dùng hàm isDisplayed() để kiểm tra được
		if(driver.findElement(lePopup).isDisplayed()) {
			
		
		// nhập email vào
		 driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
		 
		 
		 
		// đóng popup đi -> qua step tiếp theo
		
		
		
		
		
	
	
	}

	//@Test
	public void TC_02_Random_In_DOM() {
		
	}
	
	//@Test
	public void TC_03_Random_Not_In_DOM() {
		
	}

	//1000ms = 1s
			public void sleepInSecond(long timeInsecond) {
					try {
						Thread.sleep(timeInsecond * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
			public int getRandomNumber() {
				return new Random().nextInt(99999);
			}
			
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}