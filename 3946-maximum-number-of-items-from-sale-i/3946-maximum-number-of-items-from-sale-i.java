class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            minPrice = Math.min(minPrice, price);
            int totalGain = 0;
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    totalGain++;
                }
            }
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + totalGain);
            }
        }
        int maxItems = 0;
        for (int spent = 0; spent <= budget; spent++) {
            maxItems = Math.max(maxItems, dp[spent] + (budget - spent) / minPrice);
        }

        return maxItems;
    }
}
