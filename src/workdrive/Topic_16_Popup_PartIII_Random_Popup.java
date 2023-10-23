

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

	//@Test
	public void TC_01_Random_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(90);
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		// vì nó luôn có trong DOM nên cỏ thể dùng hàm isDisplayed() để kiểm tra được
		if(driver.findElement(lePopup).isDisplayed()) {
			
		
		// nhập email vào
		 driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
		 driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
		 sleepInSecond(5);
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");
		Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
		
		
	    // đóng popup đi -> qua step tiếp theo
		// Sau khoảng 5s sẽ tự đóng popup
		sleepInSecond(15);
		// Qua step này
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		}
	}

	//@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(40);
		By popup = By.cssSelector("div#tve-p-scroller");
		
		//Hàm isDissplayed() chỉ check co element có trong DOM
		//Ko có trong DOM thì ko có chekc được -> fail ngay đoạn findElement rồi
		if (driver.findElement(popup).isDisplayed()) {
			//Close popup hoặc là clcik vào link để join group zalo
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(3);
			driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
			sleepInSecond(3);
			Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		}
	}
	
	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		By popup = By.cssSelector("div.popup-content");
		
		//findElement -> sẽ bị fail khi tìm ko thấy element -> ném ra 1 ngoại lệ: NoSuchElementException
		//finElements -> ko bị fail khi ko tìm thấy element -> trả về 1 list rỗng (empty)
		
		//isDisplayed
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Trần Thị Kim Khánh");
			driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
			driver.findElement(By.id("popup-phone")).sendKeys("0766625039");
			sleepInSecond(3);
			driver.findElement(By.id("close-popup")).click();
			sleepInSecond(3);
		}
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.id("search-courses")).sendKeys(courseName);
		driver.findElement(By.id("search-course-button")).click();
		sleepInSecond(3);
		
		
		//Duy nhất 1 course hiển thị
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content>h4")).getText(), courseName);
		
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
		//driver.quit();
	}
}