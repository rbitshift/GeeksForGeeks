package dp;

public class EditDistanceRecursive {
	private String a;
	private String b;
	
	public EditDistanceRecursive(String x, String y) {
		this.a = x;
		this.b = y;
	}
	
	public void solve() {
		System.out.println(editDistanceHelper(a.length()-1, b.length()-1));
	}
	
	private int editDistanceHelper(int n, int m) {
		if(n < 0) {
			return 1 + m;
		}
		
		if(m < 0) {
			return 1 + n;
		}
		
		if(a.charAt(n) == b.charAt(m)) {
			return editDistanceHelper(n-1, m-1);
		} else {
			return 1 + min(editDistanceHelper(n-1, m), editDistanceHelper(n, m-1), editDistanceHelper(n-1, m-1));
		}
	}
	
	private int min(int x, int y, int z) {
		return Math.min(Math.min(x,  y), z);
	}

	public static void main(String[] args) {
		String a = "kitten";
		String b = "sitting";
		EditDistanceRecursive puzzle = new EditDistanceRecursive(a, b);
		puzzle.solve();
	}
}
