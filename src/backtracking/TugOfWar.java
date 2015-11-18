package backtracking;

import java.util.Arrays;

public class TugOfWar {
	private int[] input;
	private boolean[] marked;
	private boolean[] solution;
	private int count;
	private int minDiff;
	
	public TugOfWar(int[] a) {
		this.input = a;
		marked = new boolean[input.length];
		minDiff = Integer.MAX_VALUE;
	}
	
	public void solve() {
		search(0);
		printSolution();
	}
	
	private void printSolution() {
		StringBuilder sbOne = new StringBuilder();
		StringBuilder sbTwo = new StringBuilder();
		for(int i = 0; i < input.length; i++) {
			if(solution[i]) {
				sbOne.append(input[i] + " ");
			} else {
				sbTwo.append(input[i] + " ");
			}
		}
		System.out.println("Minimum Difference: " + minDiff);
		System.out.println("Set 1: " + sbOne.toString());
		System.out.println("Set 2: " + sbTwo.toString());
	}
	
	private boolean search(int pos) {
		if(count == (input.length/2)) {
			int diff = getDiff();
			if(diff < minDiff) {
				minDiff = diff;
				solution = Arrays.copyOf(marked,  marked.length);
			}
		}
		
		for(int i = 0; i < input.length; i++) {
			if(!marked[i] && count <= (input.length/2)) {
				marked[i] = true; count++;
				if(search(i) == true) {
					return true;
				}
				marked[i] = false; count--;
			}
		}
		return false;
	}
	
	private int getDiff() {
		int diff = 0;
		for(int i = 0; i < input.length; i++) {
			if(marked[i]) {
				diff += input[i];
			} else {
				diff -= input[i];
			}
		}
		return Math.abs(diff);
	}
	
	public static void main(String[] args) {
		int[] input = {1,2,4,8,16,31};
		TugOfWar puzzle = new TugOfWar(input);
		puzzle.solve();
	}
}
