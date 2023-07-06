package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PII_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.id("under_18");
	By educationTextarea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	
	
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// textbox nếu có hiển thị thì nhập text vào và in ra console
		
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium Webdriver");
			System.out.println("Email textbox is displayed");
		}else 
			System.out.println("Email textbox is not displayed");
		
		//textarea nếu có hiển thị thì nhập text vào và in ra console
		if(driver.findElement(educationTextarea).isDisplayed()) {
			driver.findElement(educationTextarea).sendKeys("Selenium Webdriver123");
			System.out.println("Education textarea is displayed");
		}else 
			System.out.println("Education textarea is not displayed");
		
		//radio nếu có hiển thị thì nhập text vào và in ra console
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
		    driver.findElement(ageUnder18Radio).click();
		    System.out.println("Age Under 18 is displayed");
	   }else 
		    System.out.println("Age Under 18 is not displayed");
		
		// nameUser5Text
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		}else 
			System.out.println("Name User 5 is not displayed");
		
	}

	@Test
	public void TC_02_() {
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
		//driver.quit();
	}
}