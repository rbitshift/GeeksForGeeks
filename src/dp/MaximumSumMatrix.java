package dp;

public class MaximumSumMatrix {
	
	//Brute Force: Solution in O(N^6)
	
	public static int solve_ON6(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int maxSum = Integer.MIN_VALUE;
		for(int rtop = 0; rtop < m; rtop++) {
			for(int rbottom = rtop; rbottom < m; rbottom++) {
				for(int cleft = 0; cleft < n; cleft++) {
					for(int cright = cleft; cright < n; cright++) {
						maxSum = Math.max(maxSum, matrixSum_ON6(matrix,  rtop, rbottom, cleft, cright));
					}
				}
			}
		}
		return maxSum;
	}
	
	private static int matrixSum_ON6(int[][] matrix, int rtop, int rbottom, int cleft, int cright) {
		int matrixSum = 0;
		for(int i = rtop; i <= rbottom; i++) {
			for(int j = cleft; j <= cright; j++) {
				matrixSum += matrix[i][j];
			}
		}
		
		return matrixSum;
	}
	
	//Reduce matrixSum in O(1): Solution in O(n^4)
	
	public static int solve_ON4(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int[][] sumMatrix = preprocessSumMatrix_ON4(matrix, m, n);
		int maxSum = Integer.MIN_VALUE;
		for(int rtop = 0; rtop < m; rtop++) {
			for(int rbottom = rtop; rbottom < m; rbottom++) {
				for(int cleft = 0; cleft < n; cleft++) {
					for(int cright = cleft; cright < n; cright++) {
						maxSum = Math.max(maxSum, matrixSum_ON4(sumMatrix,  rtop, rbottom, cleft, cright));
					}
				}
			}
		}
		return maxSum;
	}
	
	private static int[][] preprocessSumMatrix_ON4(int[][] matrix, int m, int n) {
		int[][] sumMatrix = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0 && j == 0) {
					sumMatrix[i][j] = matrix[i][j];
				} else if(i == 0) {
					sumMatrix[i][j] += sumMatrix[i][j-1] + matrix[i][j];
				} else if(j == 0) {
					sumMatrix[i][j] += sumMatrix[i-1][j] + matrix[i][j];
				} else {
					sumMatrix[i][j] += sumMatrix[i][j-1] + sumMatrix[i-1][j] - sumMatrix[i-1][j-1] + matrix[i][j];
				}	
			}
		}
		return sumMatrix;
	}
	
	private static int matrixSum_ON4(int[][] sumMatrix, int rtop, int rbottom, int cleft, int cright) {
		int matrixSum = 0;
		if(rtop == 0 && cleft == 0) {
			matrixSum = sumMatrix[rbottom][cright];
		} else if(rtop == 0) {
			matrixSum = sumMatrix[rbottom][cright] - sumMatrix[rbottom][cleft-1];
		} else if(cleft == 0) {
			matrixSum = sumMatrix[rbottom][cright] - sumMatrix[rtop-1][cright];
		} else {
			matrixSum = sumMatrix[rbottom][cright] - sumMatrix[rbottom][cleft-1] - sumMatrix[rtop-1][cright] + sumMatrix[rtop-1][cleft-1];
		}
		return matrixSum;
	}
	
	//Prefix and Cadane's Algorithm: O(N^3)
	//http://www.algorithmist.com/index.php/UVa_108
	//Kadane's algorithm is modified to work with all negative numbers
	
	public static int solve_ON3(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int maxSum = Integer.MIN_VALUE;
		for(int cleft = 0; cleft < n; cleft++) {
			int[] columnSum = new int[m];
			
			for(int cright = cleft; cright < n; cright++) {
				for(int row = 0; row < m; row++) {
					columnSum[row] += matrix[row][cright]; 
				}
				
				int max = maximumSubarraySum(columnSum);
				maxSum = Math.max(maxSum, max);
			}
		}
		return maxSum;
	}
	
	public static int maximumSubarraySum(int[] a) {
		int maxSum = Integer.MIN_VALUE;
		int localMax = 0;
		for(int i = 0; i < a.length; i++) {
			localMax = Math.max(a[i],  a[i] + localMax);
			maxSum = Math.max(maxSum, localMax);
		}
		return maxSum;
	}

	public static void main(String[] args) {
		 System.out.println(MaximumSumMatrix.solve_ON6(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}, {3, 8, 10, 1, 3}, {-4, -1, 1, 7, -6}}));
		 System.out.println(MaximumSumMatrix.solve_ON6(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}}));
		 System.out.println(MaximumSumMatrix.solve_ON6(new int[][]{{1, 2, -1, }, {-8, -3, 1}}));
		 
		 System.out.println(MaximumSumMatrix.solve_ON4(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}, {3, 8, 10, 1, 3}, {-4, -1, 1, 7, -6}}));
		 System.out.println(MaximumSumMatrix.solve_ON4(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}}));
		 System.out.println(MaximumSumMatrix.solve_ON4(new int[][]{{1, 2, -1, }, {-8, -3, 1}}));
		 
		 System.out.println(MaximumSumMatrix.solve_ON3(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}, {3, 8, 10, 1, 3}, {-4, -1, 1, 7, -6}}));
		 System.out.println(MaximumSumMatrix.solve_ON3(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}}));
		 System.out.println(MaximumSumMatrix.solve_ON3(new int[][]{{1, 2, -1, }, {-8, -3, 1}}));
	}
}