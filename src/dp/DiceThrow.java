package dp;

public class DiceThrow {
	public static int solve(int m, int n, int x) {
		if(n <= 0 && x <= 0) {
			return 0;
		}
		
		if(n == 1 && 0 < x && x <= m) {
			return 1;
		}
		
		int sum = 0;
		for(int i = 1; i <= m; i++) {
			sum += solve(m, n-1, x-i);
		}
		
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(DiceThrow.solve(4, 2, 1));
		System.out.println(DiceThrow.solve(2, 2, 3));
		System.out.println(DiceThrow.solve(6, 3, 8));
		System.out.println(DiceThrow.solve(4, 2, 5));
		System.out.println(DiceThrow.solve(4, 3, 5));
	}
}