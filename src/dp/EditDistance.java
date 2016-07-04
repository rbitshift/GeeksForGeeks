package dp;

public class EditDistance {
	private static int[][] edit;
	
	
	public static int solve_recursive(String a, String b) {
		return solve_recursive_helper(a, b, a.length()-1, b.length()-1);
	}
	
	private static int solve_recursive_helper(String a, String b, int aPos, int bPos) {
		if(aPos < 0 ) {
			return 1 + bPos;
		}
		
		if(bPos < 0) {
			return 1 + aPos;
		}
		
		if(a.charAt(aPos) == b.charAt(bPos)) {
			return solve_recursive_helper(a, b, aPos-1, bPos-1);
		} else {
			return 1 + min(solve_recursive_helper(a, b, aPos-1, bPos-1),
					solve_recursive_helper(a, b, aPos, bPos-1),
					solve_recursive_helper(a, b, aPos-1, bPos));
		}
	}
	
	public static int solve_memoized(String a, String b) {
		edit = new int[a.length()+1][b.length()+1];
		for(int i = 0; i < edit.length; i++) {
			for(int j = 0; j < edit[0].length; j++) {
				if(i == 0 || j == 0) {
					edit[i][j] = 0;
				} else {
					edit[i][j] = -1;
				}
			}
		}
		
		return solve_memoized_helper(a, b, a.length(), b.length());
	}
	
	private static int solve_memoized_helper(String a, String b, int aPos, int bPos) {
		if(aPos == 0) {
			edit[aPos][bPos] = bPos;
		} else if(bPos == 0) {
			edit[aPos][bPos] = aPos;
		} else if(edit[aPos][bPos] == -1) {
			if(a.charAt(aPos-1) == b.charAt(bPos-1)) {
				edit[aPos][bPos] = solve_memoized_helper(a, b, aPos-1, bPos-1);
			} else {
				edit[aPos][bPos] = 1 + min(solve_memoized_helper(a, b, aPos-1, bPos-1),
						solve_memoized_helper(a, b, aPos, bPos-1),
						solve_memoized_helper(a, b, aPos-1, bPos));
			} 
		}
		
		return edit[aPos][bPos];
	}
	
	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a,  b), c); 
	}

	public static void main(String[] args) {
		assert EditDistance.solve_recursive("cat", "cut") == 1;
		assert EditDistance.solve_recursive("kitten", "sitting") == 3;
		assert EditDistance.solve_recursive("saturday", "sunday") == 3;
		
		assert EditDistance.solve_memoized("cat", "cut") == 1;
		assert EditDistance.solve_memoized("kitten", "sitting") == 3;
		assert EditDistance.solve_memoized("sunday", "saturday") == 3;
	}
}