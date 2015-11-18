package backtracking;

import java.util.Scanner;

public class FillPuzzle {
	private final int size;
	private int[] sol;
	
	public FillPuzzle(int num) {
		this.size = num;
		sol = new int[2 * num];
	}
	
	public void testSol(int[] sol) {
		this.sol = sol;
	}
	
	public void solve() {
		if(search(0) == true) {
			
		} else {
			System.out.println("No Solution Exists");
		}
	}
	
	private boolean search(int pos) {
		if(pos == sol.length && isSolved()) {
			System.out.println(getSolutionString());
			return true;
		}
		
		for(int num = 1; num <= size; num++) {
			if(isvalid(pos, num)) {
				sol[pos] = num;
				if(search(pos+1) == true) {
					return true;
				}
				sol[pos] = 0;
			}
		}
		return false;
	}
	
	
	private boolean isvalid(int pos, int num) {
		int count = 0;
		for(int i = 0; i < pos; i++) {
			if(sol[i] == num) {
				count++;
			}
		}
		
		if(count != 2) {
			return true;
		} else {
			return false;
		}	
	}
	
	private boolean isSolved() {
		for(int i = 1; i <= size; i++) {
			int instCount = 0, firstiPos = -1;
			boolean select = true;
			for(int j = 0; j < sol.length; j++) {
				if(i == sol[j]) {
					instCount++;
				}
				if(select && instCount == 1) {
					firstiPos = j;
					select = false;
				}			
			}
			
			if(instCount != 2 || ((firstiPos + i + 1) > (sol.length - 1)) || 
					sol[firstiPos] != sol[firstiPos + i + 1]) {
				return false;
			}
		}
		
		return true;
	}
	
	private String getSolutionString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sol.length; i++) {
			sb.append(sol[i] + " ");
		}
		sb.append("\n");
		return sb.toString();
	}
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		FillPuzzle puzzle = new FillPuzzle(num);
		puzzle.solve(); //for n = 7: 1 4 1 5 6 7 4 2 3 5 2 6 3 7
		scan.close();
	}
}