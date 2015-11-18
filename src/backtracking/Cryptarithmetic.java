package backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cryptarithmetic {
	public enum Operation {ADD, SUBSTRACT, MULTIPLY, DIVIDE};
	
	private String stra;
	private String strb;
	private Operation action;
	private String result;
	
	private String joined;
	private boolean[] marked;
	private Map<Character, Integer> map;
	
	public Cryptarithmetic(String a, String b, Operation op, String res) {
		this.stra = a;
		this.strb = b;
		this.action = op;
		this.result = res;
		
		joined = a + b + res;
		joined = nodup(joined);
	}
	
	public void solve() {
		
		marked = new boolean[10];
		
		map = new HashMap<Character, Integer>();
		if(search(0) == true) {
			
		} else {
			System.out.println("NOT DONE");
		}
	}	
	
	private String nodup(String input) {
		Set<Character> temp = new HashSet<Character>();
		for(Character c: input.toCharArray()) {
			temp.add(c);
		}
		
		StringBuilder sb = new StringBuilder();
		for (Iterator<Character> iterator = temp.iterator(); iterator.hasNext();) {
			Character character = (Character) iterator.next();
			sb.append(character);
		}
		return sb.toString();
	}
	
	private boolean search(int pos) {
		if(pos == joined.length()) {
			if(isSolved()) {
				System.out.println(getSolutionString());
				return true;
			} else {
				return false;
			}
		}
		
		for(int i = 0; i < marked.length; i++) {
			if(!marked[i]) {
				marked[i] = true;
				map.put(joined.charAt(pos), i);
				
				if(search(pos+1) == true) {
					return true;
				}
				
				marked[i] = false;
			}
		}
		return false;
	}
	
	private boolean isSolved() {
		if((map.get(stra.charAt(0)) == 0) || map.get(strb.charAt(0)) == 0) {
			return false;
		}
		
		StringBuilder sbA = new StringBuilder();
		for(int i = 0; i < stra.length(); i++) {
			sbA.append(map.get(stra.charAt(i)));
		}
		
		StringBuilder sbB = new StringBuilder();
		for(int i = 0; i < strb.length(); i++) {
			sbB.append(map.get(strb.charAt(i)));
		}
		
		StringBuilder sbR = new StringBuilder();
		for(int i = 0; i < result.length(); i++) {
			sbR.append(map.get(result.charAt(i)));
		}
		
		int res = -1;
		switch(action) {
		case ADD:
			res = Integer.parseInt(sbA.toString()) + Integer.parseInt(sbB.toString());
			break;
		case SUBSTRACT:
			res = Integer.parseInt(sbA.toString()) - Integer.parseInt(sbB.toString());
			break;
		case MULTIPLY:
			res = Integer.parseInt(sbA.toString()) * Integer.parseInt(sbB.toString());
			break;
		case DIVIDE:
			res = Integer.parseInt(sbA.toString()) / Integer.parseInt(sbB.toString());
			break;
		}
		
		if(res == Integer.parseInt(sbR.toString())) {
			return true;
		}
		return false;
	}
	
	private String getSolutionString() {
		StringBuilder sb = new StringBuilder();
		sb.append(stra);
		StringBuilder sbA = new StringBuilder();
		for(int i = 0; i < stra.length(); i++) {
			sbA.append(map.get(stra.charAt(i)));
		}
		sb.append(" ( " + sbA.toString() + " ) ");
		sb.append("\n");
		
		sb.append(strb);
		StringBuilder sbB = new StringBuilder();
		for(int i = 0; i < strb.length(); i++) {
			sbB.append(map.get(strb.charAt(i)));
		}
		sb.append(" ( " + sbB.toString() + " ) ");
		sb.append("\n");
		
		sb.append(getOperation(action) + "\n");
		
		for(int i = 0; i < 2 * result.length(); i++) {
			sb.append("-");
		}
		sb.append("\n");
		
		sb.append(result);
		StringBuilder sbR = new StringBuilder();
		for(int i = 0; i < result.length(); i++) {
			sbR.append(map.get(result.charAt(i)));
		}
		sb.append(" ( " + sbR.toString() + " ) ");
		
		sb.append("\n");
		for(int i = 0; i < 2 * result.length(); i++) {
			sb.append("-");
		}
		sb.append("\n");
		
		return sb.toString();
	}
	
	private String getOperation(Operation action) {
		String result = "";
		switch(action) {
		case ADD:
			result = "+";
			break;
		case SUBSTRACT:
			result = "-";
			break;
		case MULTIPLY:
			result = "*";
			break;
		case DIVIDE:
			result = "/";
			break;
		}
		return result;
	}

	public static void main(String[] args) {
		Cryptarithmetic puzzle1 = new Cryptarithmetic("SEND", "MORE", Operation.ADD, "MONEY");
		puzzle1.solve(); //9867 + 1085 = 10652;
		
		Cryptarithmetic puzzle2 = new Cryptarithmetic("COUNT", "COIN", Operation.SUBSTRACT, "SNUB");
		puzzle2.solve();
		
		Cryptarithmetic puzzle3 = new Cryptarithmetic("MAD", "BE", Operation.MULTIPLY, "AMID");
		puzzle3.solve();
	}
}