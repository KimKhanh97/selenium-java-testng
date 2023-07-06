package workdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
	By jobRole01SingleDropdown = By.cssSelector("#job1");
	By jobRole02MultipleDropdown = By.cssSelector("#job2");
	By interestsDevelopmentCheckbox = By.cssSelector("#development");
	By Slider01 = By.cssSelector("#slider-1");
	By PasswordTextbox = By.cssSelector("#disable_password");
	By ageRadioDisable = By.cssSelector("#radio-disabled");
	By BiographyTextarea = By.cssSelector("#bio");
	By jobRole03DisableDropdown = By.cssSelector("#job3");
	By interestsCheckboxisDisable = By.cssSelector("#check-disbaled");
	By Slider02 = By.cssSelector("#slider-2");
	By JavaCheckbox = By.cssSelector("#java");
	
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

	//@Test
	public void TC_01_Display() {
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

	//@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// ENABLED	
		// Email
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}
		// Age (Under 18)
		if (driver.findElement(ageUnder18Radio).isEnabled()) {
			System.out.println("Age Under 18 is enabled");
		} else {
			System.out.println("Age Under 18 is disabled");
		}
		// Education
		if (driver.findElement(educationTextarea).isEnabled()) {
			System.out.println("Education Textarea is enabled");
		} else {
			System.out.println("Education Textarea is disabled");
		}
		//Job role 01
		if (driver.findElement(jobRole01SingleDropdown).isEnabled()) {
			System.out.println("Job Role 01 Single Dropdown is enabled");
		} else {
			System.out.println("Job Role 01 Single Dropdown is disabled");
		}
		// Job role 02
		if (driver.findElement(jobRole02MultipleDropdown).isEnabled()) {
			System.out.println("Job Role 02 Multiple Dropdown is enabled");
		} else {
			System.out.println("Job Role 02 Multiple Dropdown is disabled");
		}
		// Interests (Development) checkbox
		if (driver.findElement(interestsDevelopmentCheckbox).isEnabled()) {
			System.out.println("Interests Development Checkbox is enabled");
		} else {
			System.out.println("Interests Development Checkbox is disabled");
		}
		// Slider 01
		if (driver.findElement(Slider01).isEnabled()) {
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is disabled");
		}
		// DISABLE
		//Password
		if (driver.findElement(PasswordTextbox).isEnabled()) {
			System.out.println("Password Textbox is enabled");
		}else 
			System.out.println("Password Textbox is disabled");
		//Age (Radiobutton is disable)
		if (driver.findElement(ageRadioDisable).isEnabled()) {
			System.out.println("Age Radio Disable is enabled");
		}else 
			System.out.println("Age Radio Disable is disabled");
		// Biography
		if (driver.findElement(BiographyTextarea).isEnabled()) {
			System.out.println("Biography Textarea is enabled");
		}else 
			System.out.println("Biography Textarea is disabled");
		// Job Role 03
		if (driver.findElement(jobRole03DisableDropdown).isEnabled()) {
			System.out.println("Job Role 03 Disable Dropdown is enabled");
		}else 
			System.out.println("Job Role 03 Disable Dropdown is disabled");
		// Interests (Checkbox is disabled)
		if (driver.findElement(interestsCheckboxisDisable).isEnabled()) {
			System.out.println("Interests Checkbox is Disable is enabled");
		}else 
			System.out.println("Interests Checkbox is Disable is disabled");
		// slider 02 (disabled)
		if (driver.findElement(Slider02).isEnabled()) {
			System.out.println("Slider 02 is enabled");
		}else 
			System.out.println("Slider 02 is disabled");
	}
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Verify checkbox/radiobutton are deselected
		  // Java Checkbox
		Assert.assertFalse(driver.findElement(JavaCheckbox).isSelected());
		  // Age Under 18 Radio button
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		
		// Click to checkbox/radio
		driver.findElement(JavaCheckbox).click();
		driver.findElement(ageUnder18Radio).click();
		sleepInSecond(3);

		// Verify checkbox/radiobutton are selected
		  // Java Checkbox
		Assert.assertTrue(driver.findElement(JavaCheckbox).isSelected());
		  // Age Under 18 Radio button
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		
	
		
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