package JavaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// I - Kiểu dữ liệu nguyên thủy (primitive): 8 kiêu (Kích thước/độ rộng để lưu trữ dữ liệu từ nhỏ đến lớn)
		    // Số nguyên: byte short int long (không có phần thập phân)
		      //byte(form -128 to 127);
		        byte bNumber = 127;
		      //Short(from -32768 to 32767);
		        short sNumber = 32000;
		      //int(from -2147483648 to 2147483647); 
		        int iNumber = 499233299;
		      //Long(from -9223372036854775808 to 9223372036854775807)
		        long lNumber = 83294;
		          
		    // Số thực: float double (có phần thập phân)
		        float studentPoint = 9.5f;
		        
		        double employeeSalary = 35.6d;
		        
		    // Logic: boolean (được dùng cho dữ liệu nào đó có giá trị true/false, đúng/sai, nam/nữ, false/pass)
		        boolean status = true; //Nam
		        status = false; //Nữ
		        
		    // Kí tự: char (đại diện cho 1 kí tự)
		        char a = 'A';
		        
		  
		// II - Kiểu dữ liệu tham chiếu ( reference)
		        // Class
		        FirefoxDriver driver = new FirefoxDriver();
		        // Interface
		        WebElement fisrtNameTextbox;
		        // String
		        String firstName = "Automation Testing";
		        // Object
		        Object people;
		        // Array
		        String[] studentName = {"Nguyễn Văn An", "Lê Văn Hùng", "Nguyễn Thị Loan"};
		        // Collection: List/Set/Queue
		        List<WebElement> checkboxes = (List<WebElement>) driver.findElement(By.cssSelector(""));
		        // Map
		        Map<String, Integer> student = new HashMap<String, Integer>();
		        
		 // Sự khác nhau giữa nguyên thủy và tham chiếu: 
		        // Không thay đổi giá trị khi gán bằng là nguyên thủy
		        // B tham chiếu tới A, khi value của A thay đổi thì value của B cũng thay đổi là tham chiếu
		        

	}

}
