package dp;

public class OptimalStrategy {
	public static int solve_recursive(int[] coins) {
		return solve_recursive(coins, 0, coins.length-1);
	}
	
	private static int solve_recursive(int[] coins, int i, int j) {
		if(i >= coins.length || j < 0) {
			return 0;
		}
		
		if(j - i == 1) {
			return Math.max(coins[i],  coins[j]);
		}
		
		return Math.max(coins[i] + Math.min(solve_recursive(coins, i+1, j-1), solve_recursive(coins, i+2, j)),
				coins[j] + Math.min(solve_recursive(coins, i+1, j-1), solve_recursive(coins, i, j-2)));
	}
	
	
	public static void main(String[] args) {
		System.out.println(OptimalStrategy.solve_recursive(new int[]{5, 3, 7, 10}));
		System.out.println(OptimalStrategy.solve_recursive(new int[]{5, 15, 5, 1}));
		System.out.println(OptimalStrategy.solve_recursive(new int[]{8, 15, 3, 7}));
	}
}