import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }
        int[] multiplesCount = new int[maxFactor + 1];
        for (int i = 1; i <= maxFactor; i++) {
            if (freq[i] > 0) {
                for (int j = i; j <= maxFactor; j += i) {
                    multiplesCount[i] += freq[j];
                }
            }
        }
        TreeMap<Integer, Long> dealsByPrice = new TreeMap<>();
        long limit = 2L * minPrice;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price < limit) {
                long freeCopiesAvailable = multiplesCount[factor] - 1;
                if (freeCopiesAvailable > 0) {
                    dealsByPrice.merge(price, freeCopiesAvailable, Long::sum);
                }
            }
        }
        long totalItems = 0;
        long remBudget = budget;

        for (Map.Entry<Integer, Long> entry : dealsByPrice.entrySet()) {
            long price = entry.getKey();
            long availableDeals = entry.getValue();

            if (remBudget < price) {
                break;
            }

            long bought = Math.min(availableDeals, remBudget / price);
            remBudget -= bought * price;
            totalItems += bought * 2;
        }
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}
