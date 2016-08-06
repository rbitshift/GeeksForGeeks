package dp;

public class SubsetSum {
	
	public static boolean solve_recursive(int[] num, int sum) {
		return solve_recursive_helper(num, num.length-1, sum);
	}
	
	private static boolean solve_recursive_helper(int[] num, int pos, int sum) {
		if(sum == 0) {
			return true;
		}
		
		if(pos < 0 || pos == 0 && sum != num[pos]) {
			return false;
		}
		
		
		return solve_recursive_helper(num, pos-1, sum) ||
				solve_recursive_helper(num, pos-1, sum - num[pos]);
	}

	public static void main(String[] args) {
		System.out.println(SubsetSum.solve_recursive(new int[]{3, 34, 4, 12, 2}, 9));

	}
}