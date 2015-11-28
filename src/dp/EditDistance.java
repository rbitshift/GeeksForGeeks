package dp;

public class EditDistance {
	
	public static void solve(String a, String b) {
		int[][] ed = new int[a.length()+1][b.length()+1];
		
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				if(i == 0 || j == 0) {
					ed[i][j] = 0;
				}
				
				if(a.charAt(i-1) == b.charAt(j-1)) {
					ed[i][j] = ed[i-1][j-1];
				} else {
					ed[i][j] = 1 + min(ed[i][j-1], ed[i-1][j], ed[i-1][j-1]);
				}
			}
		}
		
		System.out.println(ed[a.length()][b.length()]);
	}
	
	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a,  b), c); 
	}

	public static void main(String[] args) {
		String a = "kitten";
		String b = "sitting";
		EditDistance.solve(a, b);
	}
}