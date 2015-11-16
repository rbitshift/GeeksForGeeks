package dc;

public class MedianOfTwoSortedArray {

	public static int solve(int[] a, int[] b) {
		int i = 0, j = 0, k = 0;
		int[] c = new int[a.length + b.length];
		

		while(i < a.length && j < b.length) {
			if(a[i] < b[j]) {
				c[k++] = a[i++];
			} else {
				c[k++] = b[j++];
			}
		}
			
		while(i < a.length) {
			c[k++] = a[i++];
		}
		
		while(j < b.length) {
			c[k++] = b[j++];
		}
		
		if(c.length % 2 == 0) {
			return (c[(c.length-1)/2] + c[c.length/2])/2;
		} else {
			return c[(c.length-1)/2];
		}
	}
	
	public static int solve2(int[] a, int[] b) {
		//TODO:XXX
		return 0;
	}
	
	public static void main(String[] args) {
		int[] a = {1, 12, 15, 26, 28};
		int[] b = {2, 13, 17, 30, 45};
		System.out.println(MedianOfTwoSortedArray.solve(a, b));
	}
}