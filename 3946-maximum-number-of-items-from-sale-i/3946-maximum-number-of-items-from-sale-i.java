class Solution {
  
    public int maximumSaleItems(int[][] items, int budget) {
  
        int[] dp = new int[budget + 1];
  
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < items.length; i++) {
  
            int factor = items[i][0];
            int price = items[i][1];
  
            minPrice = Math.min(minPrice, price);

            int value = 0;

            for (int j = 0; j < items.length; j++) {

                if (items[j][0] % factor == 0) {

                    value++;
                }
            }

            for (int b = budget; b >= price; b--) {

                dp[b] = Math.max(dp[b], dp[b - price] + value);
            }
        }

        int maxItems = 0;

        for (int b = 0; b <= budget; b++) {

            int remainingBudget = budget - b;

            maxItems = Math.max(maxItems, dp[b] + (remainingBudget / minPrice));
        }
        return maxItems;
    }
}
