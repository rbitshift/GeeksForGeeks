package dp;

/*
 * Optimal Substructure:
 * Rn = Max(Pi + Rn-i) for 1 <= i <= n
 */

public class RodCuttingRecursive {
	private int[] cost;
	
	public RodCuttingRecursive(int[] input) {
		this.cost = input;
	}
	
	public void solve(int num) {
		System.out.println(cutHelper(num));
	}
	
	private int cutHelper(int num) {
		if(num == 0) {
			return 0;
		}
		
		Integer q = Integer.MIN_VALUE;
		for(int i = 1; i <= num; i++) {
			q = Math.max(q, cost[i-1] + cutHelper(num-i));
		}
		return q;
	}
	
	public static void main(String[] args) {
		int[] a = {1, 5, 8, 9, 10, 17, 17, 20};
		int num = 8;
		RodCuttingRecursive puzzle = new RodCuttingRecursive(a);
		puzzle.solve(num);
	}
}