package dp;

public class MaximumEffortTask {
	
	private static int[] task;
	
	public static int solve_recursive(int[] low, int[] high) {
		if(low.length != high.length) {
			System.out.println("Low and High effort tasks are not provided for every day");
			return 0;
		}
		
		return solve_recursive_helper(low, high, low.length-1);
	}
	
	private static int solve_recursive_helper(int[] low, int[] high, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		return Math.max(low[pos] + solve_recursive_helper(low, high, pos-1), 
				high[pos] + solve_recursive_helper(low, high, pos-2));
	}
	
	public static int solve_memoized(int[] low, int[] high) {
		if(low.length != high.length) {
			System.out.println("Low and High effort task are not provided for every day");
			return 0;
		}
		
		task = new int[low.length+1];
		for(int i = 0; i <= low.length; i++) {
			if(i != 0) {
				task[i] = -1;
			}
		}
		return solve_memoized_helper(low, high, low.length);
	}
	
	private static int solve_memoized_helper(int[] low, int[] high, int pos) {
		if(pos <= 0) {
			return 0;
		}
		
		if(task[pos] == -1) {
			task[pos] = Math.max(low[pos-1] + solve_memoized_helper(low, high, pos-1), 
					high[pos-1] + solve_memoized_helper(low, high, pos-2));
		}
		
		return task[pos];
	}

	public static void main(String[] args) {
		int[] low = {1, 5, 4, 5, 3};
		int[] high = {3, 6, 8, 7, 6};
	    
		System.out.println(MaximumEffortTask.solve_recursive(low, high));
		System.out.println(MaximumEffortTask.solve_memoized(low, high));

	}

}
