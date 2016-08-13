package dp;

public class InterleavingString {
	
	public static boolean solve(String a, String b, String c) {
		if(a.length() + b.length() != c.length()) {
			return false;
		}
		
		int i = 0, j = 0;
		for(int k = 0; k < c.length(); k++) {
			if(i < a.length() && c.charAt(k) == a.charAt(i)) {
				i++;
			} else if(j < b.length() && c.charAt(k) == b.charAt(j)) {
				j++;
			}
		}
		
		if(i != a.length() || j != b.length()) {
			return false;
		}
		
		return true;
	}

	public static void main(String[] args) {
		System.out.println(InterleavingString.solve("XXY", "XXZ", "XXZXXXY"));
		System.out.println(InterleavingString.solve("XY" ,"WZ" ,"WZXY"));
		System.out.println(InterleavingString.solve("XY", "X", "XXY"));
		System.out.println(InterleavingString.solve("YX", "X", "XXY"));
		System.out.println(InterleavingString.solve("XXY", "XXZ", "XXXXZY"));
	}
}