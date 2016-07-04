package dp;

public class MinCostPath {
	private static int[][] minPath;
	
	public static int solve_recursive(int[][] cost) {
		if(cost.length < 0) {
			System.out.println("Input matrix is empty");
			return 0;
		}
		
		return solve_recursive_helper(cost, cost.length-1, cost[0].length-1);
	}
	
	private static int solve_recursive_helper(int[][] cost, int row, int col) {
		if(row < 0 || col < 0) {
			return Integer.MAX_VALUE;
		} else if(row == 0 && col == 0) {
			return cost[0][0];
		} else {
			return cost[row][col] + min(solve_recursive_helper(cost, row-1, col-1),
					solve_recursive_helper(cost, row, col-1),
					solve_recursive_helper(cost, row-1, col));
		} 
	}
		
	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a,  b), c);
	}
	
	public static int solve_memoized(int[][] cost) {
		if(cost.length < 0) {
			System.out.println("Input matrix is emtpy");
			return 0;
		}
		minPath = new int[cost.length+1][cost[0].length+1];
		for(int i = 0; i < minPath.length; i++) {
			for(int j = 0; j < minPath[0].length; j++) {
				if(i == 0 || j == 0) {
					minPath[i][j] = Integer.MAX_VALUE;
				} else {
					minPath[i][j] = -1;
				}
			}
		}
		return solve_memoized_helper(cost, cost.length, cost[0].length);
	}
	
	private static int solve_memoized_helper(int[][] cost, int row, int col) {
		if(row-1 == 0 && col-1 == 0) {
			minPath[row][col] = cost[row-1][col-1];
		} else if(minPath[row][col] == -1) {
			minPath[row][col] = cost[row-1][col-1] + min(solve_memoized_helper(cost, row-1, col-1),
					solve_memoized_helper(cost, row, col-1),
					solve_memoized_helper(cost, row-1, col));
		}
		
		return minPath[row][col];
	}

	public static void main(String[] args) {
		int[][] cost = {{1, 2, 3},
		                {4, 8, 2},
		                {1, 5, 3}};
		
		assert MinCostPath.solve_recursive(cost) == 8;
		assert MinCostPath.solve_memoized(cost) == 8;
	}
}