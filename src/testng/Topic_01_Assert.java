package testng;

import org.testng.Assert;

public class Topic_01_Assert {
	public static void main(String[] args) {
	String fullname = "Automation Testing";
	
	// Mong đợi 1 điều kiện trả về là đúng (true)
	Assert.assertTrue(fullname.contains("Automation"));
	
	// Mong đợi 1 điều kiện trả về là sai (false)
	Assert.assertFalse(fullname.contains("Manual"));
	
	// Mong đợi 2 điều kiện bằng nhau
	  //Expected result = Actual result
	Assert.assertEquals(fullname, "Automation Testing");

	}
}
