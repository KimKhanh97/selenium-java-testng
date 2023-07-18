package workdrive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PIII_Exercise2 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, password, confirmation, fullName;   
	
	
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
        rand = new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAddress = "Automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "Khanh";
		fullName = firstName + " " + lastName;
		lastName = "Tran";
		password = "1234567890";
		
	}

	//@Test
	public void TC_01_Login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	
	}
	
	//@Test
	public void TC_02_Login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	//@Test
	public void TC_03_Login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	//@Test
	public void TC_04_Login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		String contacInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contacInformationText);
		
		Assert.assertTrue(contacInformationText.contains(fullName));
		Assert.assertTrue(contacInformationText.contains(emailAddress));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}

	@Test
	public void TC_06_Login() {
	   driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	   sleepInSecond(2);
	   driver.findElement(By.id("email")).sendKeys(emailAddress);
	   driver.findElement(By.id("pass")).sendKeys(password);
	   driver.findElement(By.id("send2")).click();
	   sleepInSecond(2);
	   String contacInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	   System.out.println(contacInformationText);
	   Assert.assertTrue(contacInformationText.contains(fullName));
	   Assert.assertTrue(contacInformationText.contains(emailAddress));
	   
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