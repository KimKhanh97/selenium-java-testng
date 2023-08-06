package workdrive;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Jquery_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();
        //2 - Chò cho tất cả các item được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		
		//Đưa hết tất cả item trong dropdown vào trong 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		//3 - Tìm item xem đúng cái đang cần hay không(dùng vòng lặp duyệt qua)
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
		//4 - Kiểm tra cái text của item đúng với cái mình mong muốn
			if (itemText.equals("Faster")) {
		//5 -  Click vào item đó
			System.out.println("Click vào item");
				tempItem.click();
				// Thoát ra khỏi vòng lặp không xét cho các case 
				break;
			} else {
				System.out.println("Không click vào item");
			}
		}
       
 
	}
	 //3 - Tìm item xem đúng cái đang cần hay không

	
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