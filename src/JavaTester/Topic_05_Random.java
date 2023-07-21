package JavaTester;

import java.util.Random;

public class Topic_05_Random {
	public static void main(String[] args) {
		// utilities = tiện ích
		// Data Type: class/ interface/ collection/ String/ Float/...
		Random rand = new Random();
		System.out.println(rand.nextFloat());
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextInt());
		System.out.println("Trankimkhanh" + rand.nextInt(9999) + "@gmail.com");
		System.out.println(rand.nextLong());
		
	}
}
