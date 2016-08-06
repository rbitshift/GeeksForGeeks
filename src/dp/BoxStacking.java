package dp;

import java.util.Arrays;

public class BoxStacking {
	private static int maxHeightR;
	
	public static int solve_recursive(int[][] b) {
		if(b.length <= 0) {
			System.out.println("Invalid input length");
			return 0;
		}
		
		Box[] boxes = new Box[b.length*3];
		for(int i = 0; i < b.length; i++) {
			boxes[i*3] = new Box(b[i][0], b[i][1], b[i][2]);
			boxes[i*3+1] = new Box(b[i][1], b[i][2], b[i][0]);
			boxes[i*3+2] = new Box(b[i][2], b[i][0], b[i][1]);
		}
		
		maxHeightR = 0;
		Arrays.sort(boxes);
		solve_recursive_helper(boxes, boxes.length-1);
		return maxHeightR;
	}

	private static int solve_recursive_helper(Box[] boxes, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		int maxH = boxes[pos].height;
		for(int i = 0; i < pos; i++) {
			int q = solve_recursive_helper(boxes, i);
			if(canPlaceOnTop(boxes[i], boxes[pos])) {
				maxH = Math.max(maxH,  q + boxes[pos].height);
			}
			
		}
		maxHeightR = Math.max(maxH,  maxHeightR);
		return maxH;
	}
	
	private static boolean canPlaceOnTop(Box b1, Box b2) {
		return b1.length < b2.length && b1.width < b2.width;
	}

    public static void main(String[] args) {
    	assert BoxStacking.solve_recursive(new int[][]{ {5, 5, 1}, {4, 5, 2} }) == 7;
        assert BoxStacking.solve_recursive(new int[][]{ {4, 6, 7}, {1, 2, 3}, {4, 5, 6}, {10, 12, 32} }) == 60;
        assert BoxStacking.solve_recursive(new int[][]{ {4, 7, 9}, {5, 8, 9}, {11, 20, 40}, {1, 2, 3} }) == 78;
	}
}

class Box implements Comparable<Box>{
    int length;
    int width;
    int height;
    
    public Box(int length, int width, int height){
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    public int area() {
    	return length * width;
    }

    public int compareTo(Box o) {
        return area() - o.area();
    }
}