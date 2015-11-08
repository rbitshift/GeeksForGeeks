package backtracking;

import java.util.Scanner;

public class NQueen {
	private final int size;
	private int[] qpos;
	private int count;
	
	public NQueen(int boardsize) {
		this.size = boardsize;
		qpos = new int[boardsize];
		for(int i = 0; i < size; i++) {
			qpos[i] = -1;
		}
	}
	
	public void solve() {
		if(size <= 0 || size == 2 || size == 3) {
			System.out.println(String.format("Solution do not exits for boardsize: %d", size));
			return;
		}
		
		search(0);
	}
	
	private boolean search(int row) {
		if(row == size) {
			printboard();
		}
		
		for(int j = 0; j < size; j++) {
			if(isvalid(row, j) == true) {
				qpos[row] = j;
				if(search(row+1) == true) {
					return true;
				}
				qpos[row] = -1;
			}
		}
		return false;
	}
	
	private boolean isvalid(int row, int col) {
		if(row < 0 || row >= size || col < 0 || col >= size) {
			return false;
		}
		
		for(int i = 0; i < row; i++) {
			if((qpos[i] == col) || (Math.abs(row-i) == Math.abs(qpos[i]-col))) {
				return false;
			}
		}
		
		return true;
	}
	
	private void printboard() {
		System.out.println(String.format("Solution %d",  ++count));
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(qpos[i] == j) {
					System.out.print(" Q ");
				} else {
					System.out.print(" * ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int boardsize = scanner.nextInt();
		scanner.close();
		
		NQueen puzzle = new NQueen(boardsize);
		puzzle.solve();
	}
}