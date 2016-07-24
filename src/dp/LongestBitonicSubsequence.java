package dp;

public class LongestBitonicSubsequence {
	private static int[] lisendR;
	private static int[] ldsbegR;
	private static int bitonicMaxR;

	public static int solve_recursive(int[] a) {
		if(a.length <= 0) {
			System.out.println("Invalid input array");
			return 0;
		}
		
		bitonicMaxR = Integer.MIN_VALUE;
		lisendR = new int[a.length];
		ldsbegR = new int[a.length];
		
		build_lisend_array(a, a.length-1);
		build_lisbeg_array(a, 0);
		
		for(int i = 0; i < a.length; i++) {
			bitonicMaxR = Math.max(bitonicMaxR, lisendR[i] + ldsbegR[i] - 1);
		}
		return bitonicMaxR;
	}
	
	private static int build_lisend_array(int[] a, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		int endlis = 1;
		for(int i = 0; i < pos; i++) {
			int q = build_lisend_array(a, i);
			if(a[i] < a[pos]) {
				endlis = Math.max(endlis, 1 + q); 
			}
		}
		lisendR[pos] = endlis;
		return endlis;
	}
	
	private static int build_lisbeg_array(int[] a, int pos) {
		if(pos > a.length-1) {
			return 0;
		}
		
		int ldsbeg = 1;
		for(int i = a.length-1; i > pos; i--) {
			int q = build_lisbeg_array(a, i);
			
			if(a[i] < a[pos]) {
				ldsbeg = Math.max(ldsbeg, 1 + q);
			}
		}
		ldsbegR[pos] = ldsbeg;
		return ldsbeg;
	}
	
	public static void main(String[] args) {
		System.out.println(LongestBitonicSubsequence.solve_recursive(new int[]{1, 11, 2, 10, 4, 5, 2, 1}));
		System.out.println(LongestBitonicSubsequence.solve_recursive(new int[]{12, 11, 40, 5, 3, 1}));
		System.out.println(LongestBitonicSubsequence.solve_recursive(new int[]{80, 60, 30, 40, 20, 10}));
		System.out.println(LongestBitonicSubsequence.solve_recursive(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
	}
}