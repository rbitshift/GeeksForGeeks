package dp;

public class MinCoinRecursive {
	
	private int[] coins;
	private int amount;
	
	
	public MinCoinRecursive(int[] coins, int amount) {
		this.coins = coins;
		this.amount = amount;
	}
	
	public int getMinCoin() {
		return minCoinHelper(amount);
	}
	
	private int minCoinHelper(int amount) {
		if(amount == 0) {
			return 0;
		}
		
		int q = Integer.MAX_VALUE;
		for(int i = 0; i < coins.length; i++) {
			if(amount > coins[i]) {
				q = Math.min(q,  minCoinHelper(amount-coins[i]));
			} else {
				if(amount == coins[i]) {
					return 1;
				}
			}
		}
		return 1 + q;
	}

	public static void main(String[] args) {
		int[] coins = {1, 2, 5, 10};
		int amount = 13;
		
		MinCoinRecursive puzzle = new MinCoinRecursive(coins, amount);
		System.out.println("Minimum coins required: " + puzzle.getMinCoin());
	}
}