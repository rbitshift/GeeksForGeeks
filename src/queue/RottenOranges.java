package queue;

import java.util.Scanner;

public class RottenOranges {
	private int[][] grid;
	private int row;
	private int col;
	
	private Queue<Spot> queue;
	
	class Spot {
		int x; int y;
		public Spot(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Spot left() {
			return new Spot(x-1, y);
		}
		
		public Spot right() {
			return new Spot(x+1, y);
		}
		
		public Spot up() {
			return new Spot(x, y-1);
		}
		
		public Spot down() {
			return new Spot(x, y+1);
		}

		public boolean isValid() {
			if(0 <= x && x < row && 0 <= y && y < col) {
				return true;
			}
			return false;
		}
		
		public boolean containFreshOrange() {
			if(isValid() && grid[x][y] == 1) {
				return true;
			}
			return false;
		}
		
		public void turnRottenOrange() {
			grid[x][y] = 2;
		}
	}
	
	public RottenOranges(int[][] grid) {
		this.grid = grid;
		row = grid.length;
		col = grid[0].length;
		queue = new Queue<Spot>();
	}
	
	private void addToRottenQueue(Spot Spot) {
		if(Spot.containFreshOrange()) {
			Spot.turnRottenOrange();
			queue.enque(Spot);
		}
	}
	
	private int availableFreshOranges() {
		int count = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(grid[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}
	
	public void checkRottenTime() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(grid[i][j] == 2) {
					queue.enque(new Spot(i, j));
				}
			}
		}
		
		int size = queue.size();
		int count = 0;
		while(!queue.isEmpty()) {
			if(size == 0) {
				size = queue.size();
				count++;
			}
			Spot curSpot = queue.deque();
			addToRottenQueue(curSpot.left());
			addToRottenQueue(curSpot.right());
			addToRottenQueue(curSpot.up());
			addToRottenQueue(curSpot.down());
			
			size--;
		}
		
		int freshOranges = availableFreshOranges();
		if(freshOranges > 0) {
			System.out.println("Available fresh oranges: " + freshOranges);
		} else {
			System.out.println("Total time to rot all oranges: " + count);
		}
	}
	
	public static void main(String[] args) {
		String input1 = "3\n" + "5\n" +  "2 1 0 2 1\n" + "0 0 1 2 1\n" + "1 0 0 2 1\n";
		runTest(input1);
		
		String input2 = "3\n" + "5\n" + "2 1 0 2 1\n" + "1 0 1 2 1\n" + "1 0 0 2 1\n";
		runTest(input2);
		
		String input3 = "3\n" + "3\n" + "2 2 2\n" + "2 2 2\n" + "2 2 2\n";
		runTest(input3);
		
		String input4 = "3\n" + "3\n" + "1 1 1\n" + "1 1 1\n" + "1 1 1\n";
		runTest(input4);
	}
	
	public static void runTest(String input) {
		Scanner scan = new Scanner(input);
		int rows = Integer.valueOf(scan.nextLine());
		int columns = Integer.valueOf(scan.nextLine());
		
		int[][] grid = new int[rows][columns];
		for(int i = 0; i < rows; i++) {
			String data = scan.nextLine();
			String[] split = data.split(" ");
			if(columns != split.length) {
				throw new IllegalArgumentException("Provided data do not match with input");
			} else {
				for(int j = 0; j < columns; j++) {
					grid[i][j] = Integer.valueOf(split[j]);
				}
			}
		}
		
		RottenOranges puzzle = new RottenOranges(grid);
		puzzle.checkRottenTime();
	}
}