package backtracking;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * Input:
3 0 6 5 0 8 4 0 0
5 2 0 0 0 0 0 0 0
0 8 7 0 0 0 0 3 1
0 0 3 0 1 0 0 8 0
9 0 0 8 6 3 0 0 5
0 5 0 0 9 0 6 0 0
1 3 0 0 0 0 2 5 0
0 0 0 0 0 0 0 7 4
0 0 5 2 0 6 3 0 0
 *
 */

public class Sudoku {
	public static int SIZE = 9;
	private int[][] grid;
	
	private class Point {
		int x;
		int y;
		
		public Point(int row, int col) {
			this.x = row;
			this.y = col;
		}
		
		public String toString() {
			return String.format("%d:%d",  x, y);
		}
	}
	
	public Sudoku() {
		grid = new int[SIZE][SIZE];
	}
	
	public void fill(int row, int col, int val) {
		grid[row][col] = val;
	}
	
	public void solve() {
		if(solutionHelper() == true) {
			System.out.println(this);
		} else {
			System.out.println("No solution exists");
		}
		
	}
	
	public boolean solutionHelper() {
		Point emptyPos = findEmptyPos();
		if(emptyPos == null) {
			return true;
		}
		
		Set<Integer> list = getPossibleNumbers(emptyPos.x, emptyPos.y);
		for(Integer m: list) {
			grid[emptyPos.x][emptyPos.y] = m;
			if(solutionHelper() == true) {
				return true;
			}
			grid[emptyPos.x][emptyPos.y] = 0;
		}
		return false;
	}
	
	private boolean isvalid() {
		for(int i = 0; i < SIZE; i++) {
			Set<Integer> result = new HashSet<Integer>(getInitializedList());
			for(int j = 0; j < SIZE; j++) {
				result.remove(grid[i][j]);
			}
			
			if(!result.isEmpty()) {
				return false;
			}
		}
		
		for(int j = 0; j < SIZE; j++) {
			Set<Integer> result = new HashSet<Integer>(getInitializedList());
			for(int i = 0; i < SIZE; i++) {
				result.remove(grid[i][j]);
			}
			
			if(!result.isEmpty()) {
				return false;
			}
		}
		
		for(int i = 0; i < SIZE/3; i++) {
			for(int j = 0; j < SIZE/3; j++) {
				Set<Integer> result = new HashSet<Integer>(getInitializedList());
				
				int cellRStart = i * 3;
				int cellREnd = cellRStart + 3;
				
				int cellCStart = j * 3;
				int cellCEnd = cellCStart + 3;
				
				for(int m = cellRStart; m < cellREnd; m++) {
					for(int n = cellCStart; n < cellCEnd; n++) {
						result.remove(grid[m][n]);
					}
				}
				
				if(!result.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Set<Integer> getPossibleNumbers(int row, int col) {
		Set<Integer> result = new HashSet<Integer>(getInitializedList());
		for(int i = (3 * (row/3)); i < ((3 * (row/3)) + 3); i++) {
			for(int j = (3 * (col/3)); j < ((3 * (col/3)) + 3); j++) {
				result.remove(grid[i][j]);
			}
		}
		
		for(int i = 0; i < SIZE; i++) {
			if(i != row) {
				result.remove(grid[i][col]);
			}
			
			if(i != col) {
				result.remove(grid[row][i]);
			}
		}
		
		return result;
	}
	
	private Point findEmptyPos() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(grid[i][j] == 0) {
					Point ep = new Point(i, j);
					return ep;
				}
			}
		}
		return null;
	}
	
	private List<Integer> getInitializedList() {
		return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < SIZE; i++) {
			boolean split = true;
			for(int j = 0; j < SIZE; j++) {
				if(j % 3 == 0 && j != 0) {
					sb.append("|");
				}
				
				if(i != 0 && i %3 == 0 && split == true) {
					for(int k = 0; k < SIZE; k++) {
						if(k!= 0 && k %3 == 0) {
							sb.append(" ");
						}
						sb.append(String.format(" - "));
					}
					sb.append("\n");
					split = false;
				}
					
				sb.append(String.format(" %d ", grid[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Sudoku sudoku = new Sudoku();
		for(int i = 0; i < Sudoku.SIZE; i++) {
			String line = scanner.nextLine();
			String[] split = line.split(" ");
			for(int j = 0; j < Sudoku.SIZE; j++) {
				sudoku.fill(i, j, Integer.parseInt(split[j]));
			}
		}
		scanner.close();
		sudoku.solve();
	}
}