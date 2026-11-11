class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long total = 0;
        if(cost1 + cost2 <= costBoth){
            return (long) cost1*need1 + (long) cost2*need2;
        }
        else{
            int both = Math.min(need1 , need2);
            total += (long)both*costBoth;
            need1 = need1 - both;
            need2 = need2 - both;
            if(need1 > 0){
               int minCost1 = Math.min(cost1 , costBoth);
               total +=  (long)need1*minCost1;
            }
            else{
                int minCost2 = Math.min(cost2 , costBoth);
                total += (long)need2*minCost2;
            }
        }
        return total;
        
    }
}