class Solution {
    int n;
    int[][][] dp;
   public int solveForAlice(int [] piles,int person,int i,int M){
    if(i>=n)return 0;
    if (dp[i][M][person] != -1) {
        return dp[i][M][person];
    }
    
    int result;
    if(person==1){
        result= -1;
    }else{
        result=Integer.MAX_VALUE;
    }
    int stones=0;
    for(int x=1;x<=Math.min(2*M,n-i);x++){
        stones += piles[i+x-1];
        if(person==1){
            //Alice
            result=Math.max(result,stones+solveForAlice(piles,0,i+x,Math.max(M,x)));
        }else{
        result=Math.min( result, solveForAlice(piles,1,i+x,Math.max(M,x)));            
        }
    }
    return dp[i][M][person] = result;

   }
    public int stoneGameII(int[] piles) {
        n=piles.length;
        dp = new int[n][n + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }        
        return solveForAlice(piles,1,0,1);
    }
}