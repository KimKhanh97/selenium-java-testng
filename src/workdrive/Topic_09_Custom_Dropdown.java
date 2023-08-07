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
		
		//Muốn chọn item cho Speed dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
 
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
	
	// TRánh lặp lại code nhiều lần thì chỉ cần gọi hàm ra để dùng
	// Đi kèm với tham số
	// Nếu truyền cứng 1 giá trị vào trong hàm = vô nghĩa
	// Nên define để dùng đi dùng lại nhiều lần
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {
        //1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector(parentCss)).click();
        //2 - Chò cho tất cả các item được load ra thành công
		// Locator phải lấy để đại diện cho tất cả các item
		// Lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		//Đưa hết tất cả item trong dropdown vào trong 1 list
		List <WebElement>speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		
		//3 - Tìm item xem đúng cái đang cần hay không(dùng vòng lặp duyệt qua)
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
		//4 - Kiểm tra cái text của item đúng với cái mình mong muốn
			if (itemText.equals(expectedTextItem)) {
		//5 -  Click vào item đó
				tempItem.click();
				// Thoát ra khỏi vòng lặp không xét cho các case 
				break;
			}
		}
 
	}
 }