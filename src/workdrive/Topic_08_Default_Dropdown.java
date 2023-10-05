package workdrive;

import java.util.Random;
import java.util.concurrent.TimeUnit;	
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year, countryName, provinceName,
			cityName, postalCode, phoneNumber, addressName;

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
		firstName = "Khanh";
		lastName = "Kim";
		emailAddress = "trankimkhanh" + getRandomNumber() + "@gmail.com";
		companyName = "TechAvenueSolution";
		password = "Kimkhanh@090997";
		day = "18";
		month = "December";
		year = "1994";
		countryName = "United States";
		provinceName = "California";
		cityName = "Los Angeles";
		postalCode = "90003";
		phoneNumber = "+1 646 980 4741";
		addressName = "17 Da's Son";
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Dùng để chọn: selectByIndex; selectByValue; selectByVisibleText
		// Dùng để bỏ chọn: deselectByIndex; deselectByValue; deselectByVisibleText
		// Dùng trong trường hơp dropdown có thể chọn nhiều value (mutiple dropdown):
		// deselectAll
		// Get toàn bộ những option được chọn trong dropdown: getAllSelectedOptions
		// Get option đầu tiên được chọn: getFirstSelectedOption
		// Dùng cho trường hợp get list option trong dropdown: getOptions
		// Kiểm tra xem dropdown là single/F hay multiple/T: isMultiple

		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		sleepInSecond(5);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		sleepInSecond(5);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		sleepInSecond(5);
		
	   // Kiểm tra loại của dropdown: ==> Assert.assertFalse(new Select(driver.findElement(By.name("DateOfBirthDay"))).isMultiple());
	   // Nếu như dropdown là Single thì hàm này trả về False
	   // Nếu như dropdown là multiple thì hàm này trả về True

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		sleepInSecond(3);
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		sleepInSecond(3);
		// Verify
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);

		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
	}

	//@Test
	public void TC_02_Add_Address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//button[@class='button-1 add-address-button']")).click();
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//button[@class='button-1 save-address-button']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), addressName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(provinceName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), countryName);

	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	// 1000ms = 1s
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