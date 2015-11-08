package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KnightTour {
	private final int boardsize;
	private int x;
	private int y;
	private int[][] board;
	
	public KnightTour(int n) {
		this.boardsize = n;
		board = new int[boardsize][boardsize];
		
		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				board[i][j] = -1;
			}
		}
	}
	
	public void setInitialPos(int row, int col) {
		this.x = row;
		this.y = col;
		
		board[x][y] = 1;
	}
	
	public void solve() {
		if(move(x, y, 1) == false) {
			System.out.println("Solution do not exist");
		}
	}
	
	private boolean move(int row, int col, int count) {
		if(count == (boardsize * boardsize)) {
			board[row][col] = count;
			printboard();
			
			return true;
		}
		
//		List<Point> allMoves = getAllMoves(row, col);
		List<Point> allMoves = getAllMovesOptimized(row, col); //Faster solution implemented with heuristics
		for(Point p: allMoves) {
			board[row][col] = count;
			if(move(p.x, p.y, count+1) == true) {
				return true;
			}
			board[row][col] = -1; 
		}
		return false;
	}
	
	public List<Point> getAllMovesOptimized(int row, int col) {
		List<Point> list = getAllMoves(row, col);
		Collections.sort(list, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				if(getAllMoves(p1.x, p1.y).size() > getAllMoves(p2.x, p2.y).size()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return list;
	}
	
	public List<Point> getAllMoves(int row, int col) {
		List<Point> list = new ArrayList<Point>();
		
		if(isValid(row-2, col-1)) {
			list.add(new Point(row-2, col-1));
		}
		
		if(isValid(row-2, col+1)) {
			list.add(new Point(row-2, col+1));
		}
		
		if(isValid(row-1, col-2)) {
			list.add(new Point(row-1, col-2));
		}
		
		if(isValid(row-1, col+2)) {
			list.add(new Point(row-1, col+2));
		}
		
		if(isValid(row+1, col-2)) {
			list.add(new Point(row+1, col-2));
		}
		
		if(isValid(row+1, col+2)) {
			list.add(new Point(row+1, col+2));
		}
		
		if(isValid(row+2, col-1)) {
			list.add(new Point(row+2, col-1));
		}
		
		if(isValid(row+2, col+1)) {
			list.add(new Point(row+2, col+1));
		}
		
		return list;
	}
	
	private boolean isValid(int row, int col) {
		if(row < 0 || col < 0 || row >= boardsize || col >= boardsize) {
			return false;
		}
		
		if(board[row][col] == -1) {
			return true;
		}
		
		return false;
	}
	
	private class Point {
		int x;
		int y;
		
		Point(int row, int col) {
			this.x = row;
			this.y = col;
		}
	};
	
	private void printboard() {
		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				System.out.print(String.format(" %2d ", board[i][j]));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int boardsize = 8;
		
		KnightTour knight = new KnightTour(boardsize);
		knight.setInitialPos(0,  0);
		knight.solve();
	}
}