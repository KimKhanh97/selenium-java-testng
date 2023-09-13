package JavaTester;

public class topc_06_String {

	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String userName = "admin";
		String passWord = "admin";
		
		System.out.println(url);
		
		String[] arrayUrl = url.split("//");
          // 1 - http: -> Index = 0
		  // 2 - the-internet.herokuapp.com/basic_auth -> Index = 1
		
		url = arrayUrl[0] + "//" + userName + ":" + passWord + "@" + arrayUrl[1];
		
		System.out.println(url);
		
		String firstName = "Kim";
		String lastName = "Khanh";
		System.out.println(firstName + " " + lastName);
		System.out.println(firstName.concat(" ").concat(lastName));
	}

}
