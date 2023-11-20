package workdrive;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_findElement_findElements {
	WebDriver driver;
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
		driver.get("https://live.techpanda.org/index.php/customer/account/login/");
		// Đang apply 15s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElement() {
		// Thao tác 1 lần
	     	//driver.findElement(By.cssSelector("")).click();
		
		// Thao tác nhiều lần
	    	//WebElement emailTextbox = driver.findElement(By.cssSelector(""));
		
		
		// - Tìm thấy duy nhất 1 element/node
		  // Tìm thấy và thao tác trực tiếp lên node đó
		  // Vì nó tìm thấy nên ko cần chờ hết timeout 15s
		driver.findElement(By.cssSelector("input#email"));
		
		// - Tìm thấy nhiều hơn 1 element/node
		  // Nó sẽ thao tác với node đầu tiên
		  // Không quan tâm các node còn lại
		  // trong trường hợp bắt locator sai thì nó tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("trankimkhanh0909@gmail.com");
		
		// - Không tìm thấy element/node nào
		  //Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		  // Nếu trong thời gian đang tìm lại mà thấy element thì thỏa điều kiện - Pass
		  // Nếu hết thời gian (15s) mà vẫn ko thấy element thì 
		     // + Đánh fail TCS này tại step này + không chạy steps tiếp theo
		     // + Throw ra 1 ngoại lệ: NoSuchElementException
		

	}

	@Test
	public void TC_02_FindElements() {
		// - Tìm thấy duy nhất 1 element/node
		  // Tìm thấy và lưu nó vào list = 1 element
		  // Vì nó tìm thấy nên ko cần chờ hết timeout 15s
		List <WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());
		
		// - Tìm thấy nhiều hơn 1 element/node
		  // Tìm thấy và lưu nó vào list = element tương ứng
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());
		// - Không tìm thấy element/node nào
		  //Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		  // Nếu trong thời gian đang tìm lại mà thấy element thì thỏa điều kiện - Pass
		  // Nếu hết thời gian (15s) mà vẫn ko thấy element thì
		      //+ Không đánh fail TCS + Vẫn chạy step tiếp theo
		      //+ Trả về 1 list rỗng (empty) = 0
		
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