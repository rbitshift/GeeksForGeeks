package dp;

public class LongestIncreasingSubsequence {
	
	public static void solve(int[] a) {
		int[] c = new int[a.length];
		
		int max = 0, maxindex = -1;
		for(int i = 0; i < a.length; i++) {
			c[i] = 1;
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i] && c[i] < 1 + c[j]) {
					c[i] = 1 + c[j];
				}
			}
			
			if(max < c[i]) {
				max = c[i];
				maxindex = i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.insert(0,  a[maxindex] + " ");
		for(int i = maxindex; i >= 0; i--) {
			if(a[i] < a[maxindex] && c[i] == c[maxindex] - 1) {
				sb.insert(0, a[i] + " ");
				maxindex = i;
			}
		}
		System.out.println(max);
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60, 1};
		LongestIncreasingSubsequence.solve(a);
	}
}