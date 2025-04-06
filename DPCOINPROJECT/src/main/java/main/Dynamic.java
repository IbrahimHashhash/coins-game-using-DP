package main;

public class Dynamic {
    private int[][] dpTable; // Dynamic programming table to store the results of subproblems
    private final int[] turns; // Array to track the sequence of coins chosen by both players

    public Dynamic(int[] arr) {
        this.dpTable = calculateDpTable(arr); // Calculate the DP table based on the given coins
        this.turns = calculateTurns(this.dpTable, arr); // Determine the sequence of turns based on the DP table
    }

    // Method to calculate the DP table for the coin game
    public int[][] calculateDpTable(int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][n];

        // Initialize base cases where only one coin is considered
        for (int i = 0; i < n; i++) {
            dp[i][i] = coins[i];
        }

        // Fill the DP table for all possible subarray lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // Calculate the maximum score difference by picking either the left or right coin
                dp[i][j] = Math.max(coins[i] - dp[i + 1][j], coins[j] - dp[i][j - 1]);
            }
        }
        return dp;
    }

    // Method to calculate the sequence of turns taken by both players
    public int[] calculateTurns(int[][] dp, int[] coins) {
        int n = coins.length;
        int[] moves = new int[n]; // Array to store the order of coin selections
        int i = 0, j = n - 1;
        int playerOneIndex = 0;
        int playerTwoIndex = n - 1;
        boolean playerOne = true; // Tracks whose turn it is

        // Simulate the game based on the DP table
        while (i <= j) {
            int leftOption = (i + 1 <= j) ? dp[i + 1][j] : 0; // Value if the left coin is picked
            int rightOption = (i <= j - 1) ? dp[i][j - 1] : 0; // Value if the right coin is picked

            if (playerOne) {
                if (coins[i] - leftOption >= coins[j] - rightOption) {
                    moves[playerOneIndex++] = coins[i];
                    i++;
                } else {
                    moves[playerOneIndex++] = coins[j];
                    j--;
                }
            } else {
                if (coins[i] - leftOption >= coins[j] - rightOption) {
                    moves[playerTwoIndex--] = coins[i];
                    i++;
                } else {
                    moves[playerTwoIndex--] = coins[j];
                    j--;
                }
            }
            playerOne = !playerOne; // Switch turns
        }

        return moves;
    }

    // Getter for the DP table
    public int[][] getDpTable() {
        return dpTable;
    }

    // Getter for the sequence of turns
    public int[] getTurns() {
        return turns;
    }
}
