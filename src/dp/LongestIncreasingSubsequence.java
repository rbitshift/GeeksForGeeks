package dp;

public class LongestIncreasingSubsequence {
	
	public static void solve(int[] a) {
		int[] c = new int[a.length];
		
		int max = 0;
		for(int i = 0; i < a.length; i++) {
			c[i] = 1;
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i] && c[i] < 1 + c[j]) {
					c[i] = 1 + c[j];
				}
			}
			
			if(max < c[i]) {
				max = c[i];
			}
		}
		System.out.println(max);
	}
	
	public static void main(String[] args) {
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60, 1};
		LongestIncreasingSubsequence.solve(a);
	}
}