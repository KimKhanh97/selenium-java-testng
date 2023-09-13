package workdrive;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part_II {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Click_and_Hold_Select_Multiples_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		
		//Click vào số 1 (source) => Vẫn giữ chuột/ chưa nhả ra
		action.clickAndHold(listNumber.get(0))
		
		      //di chuột tows số (target)
		        .moveToElement(listNumber.get(7))
	       	//Nhả chuột trái ra
		        .release()
		    //execute
		        .perform();
		sleepInSecond(2);
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 8);
		
		
	}
	
	@Test
	public void TC_02_Click_and_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Chạy được cho cả Mac/Windows
		Keys key = null;
		
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// Đang chứa 12 số/ item trong list
		
		// nhấn ctrl xuống
		action.keyDown(key).perform();
		//Click chọn số ramdom
		action.click(listNumber.get(0))
		.click(listNumber.get(2))
		.click(listNumber.get(4))
		.click(listNumber.get(6))
		.click(listNumber.get(7)).perform();
		// Nhả phím ctrl ra
		action.keyUp(key).perform();
		sleepInSecond(5);
		
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 5);
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