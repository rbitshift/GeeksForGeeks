package dp;

public class CoinChangeWays {
	private static int[][] cc;

	public static int solve_recursive(int[] coin, int sum) {
		return solve_recursive_helper(coin, sum, coin.length-1);
	}
	
	private static int solve_recursive_helper(int[] coin, int sum, int pos) {
		if(sum < 0 || pos < 0) {
			return 0;
		} else if (sum == 0) {
			return 1;
		}
				
		return solve_recursive_helper(coin, sum-coin[pos], pos) + 
				solve_recursive_helper(coin, sum, pos-1);
	}
	
	public static int solve_memoized(int[] coin, int sum) {
		cc = new int[sum+1][coin.length];
		
		for(int i = 0; i < cc.length; i++) {
			for(int j = 0; j < cc[0].length; j++) {
				cc[i][j] = -1;
			}
		}
		
		return solve_memoized_helper(coin, sum, coin.length-1);
	}
	
	private static int solve_memoized_helper(int[] coin, int sum, int pos) {
		if(sum < 0 || pos < 0) {
			return 0;
		} else if(cc[sum][pos] == -1) {
			if(sum == 0) {
				cc[sum][pos] = 1;
			} else {
				cc[sum][pos] = solve_recursive_helper(coin, sum-coin[pos], pos) +
						solve_recursive_helper(coin, sum, pos-1);
			}
		}
		return cc[sum][pos];
	}
	
	public static void main(String[] args) {
		assert CoinChangeWays.solve_recursive(new int[]{1, 2, 3}, 4) == 4;
		assert CoinChangeWays.solve_memoized(new int[]{1, 2, 3}, 4) == 4;
		
		assert CoinChangeWays.solve_recursive(new int[]{1, 5, 3}, 6) == 4;
		assert CoinChangeWays.solve_memoized(new int[]{1, 5, 3}, 6) == 4;
	}
}