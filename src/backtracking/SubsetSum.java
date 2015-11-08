package backtracking;

import java.util.LinkedList;
import java.util.List;

public class SubsetSum {
	private final int[] array;
	private final int ksum;
	private List<Integer> list;
	
	public SubsetSum(int[] input, int sum) {
		this.array = input;
		this.ksum = sum;
		list = new LinkedList<Integer>();
	}
	
	public void solve() {
		for(int i = 0; i < array.length; i++) {
			list.clear();
			subset(i);
		}
	}
	
	private void subset(int pos) {
		list.add(array[pos]);
		if(sum(list) == ksum) {
			System.out.println(list);
		}
		
		for(int j = pos+1; j < array.length; j++) {
			subset(j);
		}
		list.remove(list.size()-1);		
	}
	
	private int sum(List<Integer> list) {
		int setsum = 0;
		for (Integer k: list) {
			setsum += k;
		}
		return setsum;
	}
	
	public static void main(String[] args) {
		int[] input = {10, 7, 5, 18, 12, 20, 15};
	    int ksum = 35;
	    
	    SubsetSum puzzle = new SubsetSum(input, ksum);
	    puzzle.solve();
	}
}