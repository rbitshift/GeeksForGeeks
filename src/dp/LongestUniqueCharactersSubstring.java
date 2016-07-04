package dp;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueCharactersSubstring {
	private static int lucslenR;
	private static Set<Character> lucsSetR;
	
	public static int solve_recursive(String str) {
		if(str == null || str.length() < 0) {
			throw new IllegalArgumentException("Input string is null or empty");
		}
		lucslenR = 1;
		lucsSetR = new HashSet<Character>();
		solve_recursive_helper(str, str.length()-1);
		return lucslenR;
	}
	
	private static int solve_recursive_helper(String str, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		int maxlen = solve_recursive_helper(str, pos-1);
		if(lucsSetR.contains(str.charAt(pos))) {
			maxlen = 1;
			lucsSetR.clear();
		}
		
		lucsSetR.add(str.charAt(pos));
		maxlen = Math.max(maxlen, lucsSetR.size());
		lucslenR = Math.max(lucslenR, maxlen);
		
		return maxlen;
	}

	public static void main(String[] args) {
		assert LongestUniqueCharactersSubstring.solve_recursive("ABDEFGABEF") == 6;
	}
}