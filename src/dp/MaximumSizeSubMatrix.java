package dp;

public class MaximumSizeSubMatrix {
	private static int maxSizeR;
	private static int maxSizeM;
	private static int[][] mss;
	
	public static int solve_recursive(int[][] matrix) {
		if(matrix.length < 0) {
			System.out.println("Input matrix is empty");
			return 0;
		}
		
		maxSizeR = 0;
		solve_recursive_helper(matrix, matrix.length-1, matrix[0].length-1);
		return maxSizeR;
		
	}
	
	private static int solve_recursive_helper(int[][] matrix, int row, int col) {
		if(row < 0 || col < 0) {
			return 0;
		} else {
			int localSize = min(solve_recursive_helper(matrix, row-1, col-1),
					solve_recursive_helper(matrix, row, col-1),
					solve_recursive_helper(matrix, row-1, col));
	
			if(matrix[row][col] == 1) {
				localSize = 1 + localSize;
			} else {
				localSize = 0;
			}
			maxSizeR = Math.max(maxSizeR, localSize);
			return localSize;
		}
	}
	
	public static int solve_memoized(int[][] matrix) {
		if(matrix.length < 0) {
			System.out.println("Input matrix is empty");
			return 0;
		}
		
		mss = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < mss.length; i++) {
			for(int j = 0; j < mss[0].length; j++) {
				mss[i][j] = -1;
			}
		}
		
		solve_memoized_helper(matrix, matrix.length-1, matrix[0].length-1);
		return maxSizeM;
	}
	
	private static int solve_memoized_helper(int[][] matrix, int row, int col) {
		if(row < 0 || col < 0) {
			return 0;
		}
		
		if(mss[row][col] == -1) {
			int localSize = min(solve_memoized_helper(matrix, row-1, col-1),
					solve_memoized_helper(matrix, row, col-1),
					solve_memoized_helper(matrix, row-1, col));
			if(matrix[row][col] == 1) {
				mss[row][col] = 1 + localSize;
			} else {
				mss[row][col] = 0;
			}
		}
		
		maxSizeM = Math.max(maxSizeM,  mss[row][col]);
		return mss[row][col];
	}
	
	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a,  b), c);
	}

	public static void main(String[] args) {
		int[][] matrix = {{0, 1, 1, 0, 1}, 
		                 {1, 1, 0, 1, 0}, 
		                 {0, 1, 1, 1, 0},
		                 {1, 1, 1, 1, 0},
		                 {1, 1, 1, 1, 1},
		                 {0, 0, 0, 0, 0}};
		
		assert MaximumSizeSubMatrix.solve_recursive(matrix) == 3;
		assert MaximumSizeSubMatrix.solve_memoized(matrix) == 3;
	}
}