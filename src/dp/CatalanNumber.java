package dp;

public class CatalanNumber {
	
	public static int solve_recursive(int n) {
		if(n <= 1) {
			return 1;
		}
		
		int cn = 0;
		for(int i = 0; i < n; i++) {
			 cn += (solve_recursive(i) * solve_recursive(n-i-1));
		}
		return cn;
	}
	

	public static void main(String[] args) {
		for(int i = 0; i <= 10; i++) {
			System.out.println(String.format("Catalan Number (%d) : %d", i, CatalanNumber.solve_recursive(i)));
		}
	}

}
