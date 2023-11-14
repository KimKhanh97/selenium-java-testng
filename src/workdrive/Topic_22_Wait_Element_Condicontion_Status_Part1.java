package workdrive;

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

public class Topic_22_Wait_Element_Condicontion_Status_Part1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		// Có trên UI (bắt buộc)
		// Có trong HTML (bắt buộc)
		
		// Chờ cho email address textbox hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	}

	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		// Không có trên UI (bắt buộc)
		// Có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Chờ cho re-enter email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
}
	
	//@Test
	public void TC_03_Invisible_Undisplayed_Invisibility_II() {
		// Không có trên UI (bắt buộc)
		// Không có trong HTML
		driver.get("https://www.facebook.com/");
		// Chờ cho re-enter email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	//@Test
	public void TC_04_Presence_I() {
		//Có ở trên UI
		//Có ở trong cây HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		// Chờ cho re-enter email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		}
		
	//@Test
	public void TC_05_Presence_II() {
		//Không có trên UI
		//Có ở trong cây HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Chờ cho re-enter email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	//@Test
	public void TC_06_Staleness() {
		// Không có trên UI (bắt buộc)
		// Không có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Phase1: Element có trong cây HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		// Thao tác với element ..làm cho element re-enter ko còn trong DOM nữa
		
		// Close Popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		// Chờ cho re-enter email textbox không còn trong vòng 10s
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
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
}