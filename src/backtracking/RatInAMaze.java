package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 Input:
 4
 0 -1 -1 -1
 0  0 -1  0
-1  0 -1 -1
 0  0  0  0

Output:
 1 -1 -1 -1
 1  1 -1  0
-1  1 -1 -1
 0  1  1  1
 * 
 */

public class RatInAMaze {
	private final int mazesize;
	private int[][] maze;
	
	public RatInAMaze(int size) {
		this.mazesize = size;
		maze = new int[mazesize][mazesize];
	}
	
	public void setBlocks(int row, int col, int pos) {
		if((0 <= row && row < mazesize) && (0 <= col && col < mazesize)) {
			maze[row][col] = pos;
		} else {
			throw new RuntimeException("Invalid input");
		}
	}
	
	public void solve() {
		maze[0][0] = 1;
		if(move(0, 0) == false) {
			System.out.println("Solution do not exists");
		}
	}
	
	private boolean move(int row, int col) {
		if(row == mazesize-1 && col == mazesize-1) {
			printRatTrace();
			return true;
		}
		
		List<Point> allMoves = getAllMoves(row, col);
		for(Point p: allMoves) {
			maze[p.x][p.y] = 1;
			if(move(p.x, p.y) == true) {
				return true;
			}
			maze[p.x][p.y] = 0;
		}
		return false;
	}
	
	private class Point {
		int x;
		int y;
		
		public Point(int row, int col) {
			this.x = row;
			this.y = col;
		}
	}
	
	private List<Point> getAllMoves(int row, int col) {
		List<Point> list = new ArrayList<Point>();
		
		if(isValid(row, col+1)) { //right;
			list.add(new Point(row, col+1));
		}
		if(isValid(row+1, col)) { //down;
			list.add(new Point(row+1, col));
		}
		
		return list;
	}
	
	private boolean isValid(int row, int col) {
		if(row < 0 || row >= mazesize || col < 0 || col >= mazesize) {
			return false;
		}
		
		if(maze[row][col] == 0) {
			return true;
		}
		
		return false;
	}
	
	private void printRatTrace() {
		for(int i = 0; i < mazesize; i++) {
			for(int j = 0; j < mazesize; j++) {
				System.out.print(String.format(" %2d ", maze[i][j]));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		int mazesize = Integer.parseInt(scanner.nextLine());
		
		RatInAMaze maze = new RatInAMaze(mazesize);
		for(int i = 0; i < mazesize; i++) {
			String line = scanner.nextLine();
			String[] ce = line.split(" ");
			for(int j = 0; j < ce.length; j++) {
				maze.setBlocks(i, j, Integer.parseInt(ce[j]));
			}
		}
		
		maze.solve();
		scanner.close();
	}
}