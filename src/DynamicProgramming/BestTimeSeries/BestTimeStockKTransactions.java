/* 123.Best Time to Buy and Sell Stock III
input : prices, ith element is the prices of stock on day i
output: max profit, at most two transactions (sell before buy)
 * Thought Process:
The problem is to get the maximum profit within two transactions from day 1 to day price.length.
If we define STATE dp[i][k] as the maximum profit within k transactions from day 1 to day i, then the FINAL (AIM) STATE is dp[2][prices.length].
STATE TRANSFER as below :
Assume that we are standing at day i, we can either do nothing/buy or sell.

If we do nothing or buy on day i,
dp[k][i] = dp[k][i-1]
If we sell on day i, the stock to sell can be bought on day j for 1 <= j < i
dp[k][i] = max(dp[k-1][j-1] + prices[i] - prices[j]); 
We always choose the bigger one of them.
MENTOR : MOST STATES OF DPS CAN BE GENERATED BY THE PROBLEM DEFINITION INTUITIVELY, 
         'KADANE' CAN MAKE THINGS INTUITIVE
 * 
 */
package DynamicProgramming.BestTimeSeries;

/**
 *
 * @author xinrong
 */
public class BestTimeStockKTransactions {

    public static void main(String[] args) {
        BestTimeStockKTransactions inst = new BestTimeStockKTransactions();
        int[] prices = {2, 1, 4};
        inst.maxProfit(prices);
    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[][] dp = new int[2 + 1][prices.length + 1];
        for (int k = 1; k <= 2; k++) {
            int maxVal = -prices[0];
            for (int i = 1; i <= prices.length; i++) {
                for (int j = 1; j < i; j++) {
                    maxVal = Math.max(maxVal, dp[k - 1][j - 1] - prices[j - 1]);
                }
                dp[k][i] = Math.max(dp[k][i - 1], prices[i - 1] + maxVal);
            }
        }
        return dp[2][prices.length];
    }

    private void printDP(int[][] dp) {
        System.out.println("---------------------");
        for (int[] r : dp) {
            for (int c : r) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
