import java.util.*;

class Solution {
   
    public int maximumSaleItems(int[][] items, int budget) {
   
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;
        
        for (int[] item : items) {

            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }
        
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {

            freq[item[0]]++;
        }
        
        int[] multipleCount = new int[maxFactor + 1];

        for (int d = 1; d <= maxFactor; d++) {

            if (freq[d] > 0) {

                for (int m = d; m <= maxFactor; m += d) {

                    multipleCount[d] += freq[m];
                }
            }
        }
        
        TreeMap<Long, Long> deals = new TreeMap<>();

        for (int[] item : items) {

            int factor = item[0];
            long price = item[1];
            
            long validFreeItems = multipleCount[factor] - 1;
            
            if (validFreeItems > 0 && price < 2 * minPrice) {

                deals.put(price, deals.getOrDefault(price, 0L) + validFreeItems);
            }
        }
        
        long totalItems = 0;
        long remBudget = budget;
        
        for (Map.Entry<Long, Long> entry : deals.entrySet()) {
          
            long price = entry.getKey();
            long availableDeals = entry.getValue();
            
            if (remBudget < price) break;
            
            long canBuy = Math.min(availableDeals, remBudget / price);
          
            totalItems += canBuy * 2;
            remBudget -= canBuy * price;
        }
        
        totalItems += remBudget / minPrice;
        
        return (int) totalItems;
    }
}
