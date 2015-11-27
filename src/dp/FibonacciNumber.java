package dp;

import java.util.Scanner;

public class FibonacciNumber {
	private static int[] lookup;
	
	public static int get(int num) {
		if(num < 0) {
			throw new IllegalArgumentException();
		}
		
		lookup = new int[num];
		for(int i = 0; i < num; i++) {
			lookup[i] = -1;
		}
		return getHelper(num-1);
	}
	
	private static int getHelper(int num) {
		if(lookup[num] == -1) {
			if(num <= 1) {
				lookup[num] = num;
			} else {
				lookup[num] = getHelper(num-1) + getHelper(num-2);
			}
		}
		return lookup[num];
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		System.out.println(FibonacciNumber.get(num));
		scan.close();
	}
}