package dp;

public class AssemblyLineScheduling {
	
	public static int solve_recursive(int[][] processingTime, int[][] shiftTime, int[] entryTime, int[] exitTime) {
		int station = processingTime[0].length-1;
		return solve_recursive_helper(processingTime, shiftTime, entryTime, exitTime, station); 
	}
	
	private static int solve_recursive_helper(int[][] processingTime, int[][] shiftTime, int[] entryTime, int[] exitTime, int station) {
		return Math.min(exitTime[0] + minStageTime(processingTime, shiftTime, entryTime, 0, station), 
				exitTime[1] + minStageTime(processingTime, shiftTime, entryTime, 1, station));
	}
	
	private static int minStageTime(int[][] processingTime, int[][] shiftTime, int[]entryTime, int line, int station) {
		if(station == 0) {
			return entryTime[line] + processingTime[line][0];
		}
		
		return processingTime[line][station] + Math.min(minStageTime(processingTime, shiftTime, entryTime, line, station-1), 
				shiftTime[(line+1)%2][station] + minStageTime(processingTime, shiftTime, entryTime, (line+1)%2, station-1)); 
	}

	public static void main(String[] args) {
		int[][] processingTime = {{4, 5, 3, 2}, {2, 10, 1, 4}};
		int[][] shiftTime = {{0, 7, 4, 5}, {0, 9, 2, 8}};
		int[] entryTime = {10, 12};
		int[] exitTime = {18, 7};
		System.out.println(AssemblyLineScheduling.solve_recursive(processingTime, shiftTime, entryTime, exitTime));
	}
}