package dp;

public class MaximumLengthChainOfPairs {
	private static int maxlenR;
	
	public static int solve_recursive(int[][] pairs) {
		if(pairs.length <= 0) {
			System.out.println("Invalid input");
			return 0;
		}
		solve_recursive_helper(pairs, pairs.length-1);
		return maxlenR;
	}
	
	public static int solve_recursive_helper(int[][] pairs, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		int maxlink = 1;
		for(int i = 0; i < pos; i++) {
			int q = solve_recursive_helper(pairs, i);
			if(isLinkPossible(pairs[i], pairs[pos])) {
				maxlink = Math.max(maxlink,  1 + q);
			}
		}
		
		maxlenR = Math.max(maxlink, maxlenR);
		return maxlink;
	}
	
	private static boolean isLinkPossible(int[] p1, int[] p2) {
		if(p1[0] < p1[1] && p2[0] < p2[1] && p2[0] < p1[1]) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(MaximumLengthChainOfPairs.solve_recursive(new int[][]{{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90}}));
	}
}