package dp;

public class ZeroOneKnapsack {
	private static int[][] zok;
	
	public static int solve_recursive(int[] value, int[] weight, int cap) {
		if(value.length != weight.length) {
			System.out.println("Invalid input, size of value and weight array is not same");
			return 0;
		}
		return solve_recursive_helper(value, weight, cap, weight.length-1);
	}
	
	private static int solve_recursive_helper(int[] value, int[] weight, int cap, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		if(weight[pos] > cap) {
			return solve_recursive_helper(value, weight, cap, pos-1);
		} else {
			return Math.max(value[pos] + solve_recursive_helper(value, weight, cap-weight[pos], pos-1),
					solve_recursive_helper(value, weight, cap, pos-1));
		}
	}
	
	public static int solve_memoized(int[] value, int[] weight, int cap) {
		if(value.length != weight.length) {
			System.out.println("Invalid input, size of value and weight array is not same");
			return 0;
		}
		
		zok = new int[cap+1][weight.length+1];
		for(int i = 0; i < zok.length; i++) {
			for(int j = 0; j < zok[0].length; j++) {
				zok[i][j] = -1;
			}
		}
		
		return solve_memoized_helper(value, weight, cap, weight.length-1);
	}
	
	private static int solve_memoized_helper(int[] value, int[] weight, int cap, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		if(zok[cap][pos] == -1) {
			if(weight[pos] > cap) {
				zok[cap][pos] = solve_memoized_helper(value, weight, cap, pos-1);
			} else {
				zok[cap][pos] = Math.max(value[pos] + solve_memoized_helper(value, weight, cap-weight[pos], pos-1),
						solve_memoized_helper(value, weight, cap, pos-1));
			}
		}
		return zok[cap][pos];
	}

	public static void main(String[] args) {
		assert ZeroOneKnapsack.solve_recursive(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50) == 220;
		assert ZeroOneKnapsack.solve_memoized(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50) == 220;
	}
}