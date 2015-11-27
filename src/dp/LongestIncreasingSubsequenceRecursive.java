package dp;

public class LongestIncreasingSubsequenceRecursive {
	private int[] a;
	private int globalmax;
	
	public LongestIncreasingSubsequenceRecursive(int[] in) {
		this.a = in;
		this.globalmax = 0;
	}
	
	public void solve() {
		getlis(a.length-1);
		System.out.println(globalmax);
	}
	
	private int getlis(int pos) {
		if(pos == -1) {
			return 0;
		}
		
		int q = 0, localmax = 1;
		for(int i = 0; i < pos; i++) {
			q = Math.max(q,  getlis(i));
			if(a[i] < a[pos] && localmax < 1 + q) {
				localmax = 1 + q;
			}
		}
		
		if(globalmax < localmax) {
			globalmax = localmax;
		}
		
		return localmax;
	}

	public static void main(String[] args) {
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60, 1};
		LongestIncreasingSubsequenceRecursive puzzle = new LongestIncreasingSubsequenceRecursive(a);
		puzzle.solve();
	}
}