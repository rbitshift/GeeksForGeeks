package dp;

import java.util.Arrays;

public class MaximumLengthChainOfPairs {
	private static int maxlenR;
	
	public static int solve_recursive(int[][] p) {
		if(p.length <= 0) {
			System.out.println("Invalid input");
			return 0;
		}

		Pair[] pairs = new Pair[p.length*2];
        for (int i = 0; i < p.length ; i++) {
            pairs[i*2] = new Pair(p[i][0], p[i][1]);
            pairs[i*2+1] = new Pair(p[i][1], p[i][0]);
        }
		
        Arrays.sort(pairs);
        
        maxlenR = 1;
        solve_recursive_helper(pairs, pairs.length-1);
        return maxlenR;
    }
	
	public static int solve_recursive_helper(Pair[] pairs, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		int maxlink = 1;
		for(int i = 0; i < pos; i++) {
			int q = solve_recursive_helper(pairs, i);
			if(isLinkPossible(pairs[i], pairs[pos])) {
				maxlink = Math.max(maxlink,  1 + q);
			}
		}
		
		maxlenR = Math.max(maxlink, maxlenR);
		return maxlink;
	}
	
	private static boolean isLinkPossible(Pair p1, Pair p2) {
		if(p1.right < p2.left) {
			return true;
		} else {
			return false;
		}
	}
	
	private static void printPairs(Pair[] pairs) {
		System.out.println("All possible combination of boxes after rotation");
        for (int i = 0; i <pairs.length ; i++) {
            System.out.println(pairs[i].left + " " + pairs[i].right);
        }
	}

	public static void main(String[] args) {
		assert MaximumLengthChainOfPairs.solve_recursive(new int[][]{{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90}}) == 5;
		assert MaximumLengthChainOfPairs.solve_recursive(new int[][]{{1, 5}, {2, 3}}) == 2;
	}
}

class Pair implements Comparable<Pair> {
	int left;
	int right;
	
	public Pair(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public int compareTo(Pair p) {
		return right - p.right;
	}
}