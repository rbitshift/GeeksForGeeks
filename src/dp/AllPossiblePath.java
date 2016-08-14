package dp;

public class AllPossiblePath {
	
	public static int solve_recursive(int m, int n) {
		if(m == 1 || n == 1) {
			return 1;
		}
		
		return solve_recursive(m, n-1) + solve_recursive(m-1, n);
	}

	public static void main(String[] args) {
		System.out.println(AllPossiblePath.solve_recursive(3, 3));
	}
}