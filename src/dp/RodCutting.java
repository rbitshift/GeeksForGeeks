package dp;

public class RodCutting {
	private int[] cost;
	private int[] store;
	private int[] sol;
	
	public RodCutting(int[] input) {
		this.cost = input;
		this.store = new int[cost.length+1];
		this.sol = new int[cost.length+1];
	}
	
	public void solve(int num) {
		if(num > cost.length) {
			System.out.println("No cost value found for length " + num);
		} else {
			System.out.println("Maximum revenue: " + cutHelper(num));
			
			StringBuilder sb = new StringBuilder();
			for(int i = num; i > 0; i = (i - sol[i])) {
				sb.append(sol[i] + " ");
			}
			System.out.println("Cut pieces: " + sb.toString());
		}
	}
	
	private int cutHelper(int num) {
		if(num == 0) {
			return 0;
		}
		
		int q = Integer.MIN_VALUE;
		for(int i = 1; i <= num; i++) {
			int curmax = q;
			q = Math.max(curmax,  cost[i-1] + cutHelper(num-i));
			if(curmax < q) {
				sol[num] = i;
			}
		}
		store[num] = q;
		return store[num];
	}
	
	public static void main(String[] args) {
		int[] a = {1, 5, 8, 9, 10, 17, 17, 20};
		int num = 8;
		
		RodCutting puzzle = new RodCutting(a);
		puzzle.solve(num);
		
	}
}