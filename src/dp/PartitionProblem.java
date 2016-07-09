package dp;

public class PartitionProblem {
	private static int[][] p;
	
	public static boolean solve_recursive(int[] a) {
		int sum = 0;
		for(int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		
		if(sum %2 != 0) {
			return false;
		} else {
			return solve_recursive_helper(a, sum/2, a.length-1);
		}
	}
	
	private static boolean solve_recursive_helper(int[] a, int sum, int pos) {
		if(sum == 0) {
			return true;
		}
		
		if(pos < 0 || pos == 0 && a[pos] != sum) {
			return false;
		}
		
		if(a[pos] > sum) {
			return solve_recursive_helper(a, sum, pos-1);
		}
		
		return solve_recursive_helper(a, sum - a[pos], pos -1) ||
				solve_recursive_helper(a, sum, pos-1);
	}
	
	public static boolean solve_memoized(int[] a) {
		
		int sum = 0;
		for(int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		
		if(sum %2 != 0) {
			return false;
		} else {
			p = new int[sum][a.length];
			for(int i = 0; i < p.length; i++) {
				for(int j = 0; j < p[0].length; j++) {
					p[i][j] = -1;
				}
			}
			
			return solve_memoized(a, sum/2, a.length-1) == 1? true: false;
		}
	}
	
	private static int solve_memoized(int[] a, int sum, int pos) {
		if(sum == 0) {
			return 1;
		}
		
		if(pos < 0 || pos == 0 && a[pos] != sum ) {
			return 0;
		}
		
		if(p[sum][pos] == -1) {
			if(a[pos] > sum) {
				p[sum][pos] = solve_memoized(a, sum, pos-1);
			} else {
				p[sum][pos] = Math.max(solve_memoized(a, sum-a[pos], pos-1), 
						solve_memoized(a, sum, pos-1));
			}

		}
		return p[sum][pos];
	}

	public static void main(String[] args) {
		assert PartitionProblem.solve_recursive(new int[]{3, 1, 1, 2, 2, 1}) == true;
		assert PartitionProblem.solve_memoized(new int[]{3, 1, 1, 2, 2, 1}) == true;
		
		assert PartitionProblem.solve_recursive(new int[]{1, 5, 11, 5}) == true;
		assert PartitionProblem.solve_memoized(new int[]{1, 5, 11, 5}) == true;
		
		assert PartitionProblem.solve_recursive(new int[]{1, 5, 3}) == false;
		assert PartitionProblem.solve_memoized(new int[]{1, 5, 3}) == false;
		
		assert PartitionProblem.solve_recursive(new int[]{3, 1, 5, 9, 12}) == true;
		assert PartitionProblem.solve_memoized(new int[]{3, 1, 5, 9, 12}) == true;
	}
}