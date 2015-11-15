package dc;

/*
 * Valid only for positive powers
 */

public class Pow {

	public static int calculate(int x, int n) {
		if(n == 0) {
			return 1;
		}
		int temp = calculate(x, n/2);
		if(n % 2 == 0) {
			return temp * temp;
		} else {
			if(n > 0) {
				return x * temp * temp;
			} else {
				return (temp * temp)/x;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Pow.calculate(2, 3));
	}
}