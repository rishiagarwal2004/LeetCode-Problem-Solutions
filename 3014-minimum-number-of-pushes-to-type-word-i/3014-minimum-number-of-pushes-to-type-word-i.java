class Solution {
    public int minimumPushes(String word) {
        int n=word.length();
        if(n<=8)
            return n;

        // int ans=  0;
        // for(int i=0;i<n;i++){
        //     ans=ans +(i/8)+1;
        // }  
        // return ans;  
        else if(n>8 && n<=16){
            return n+(n-8);
        }    
        else if(n>16 && n<=24){
            return n+(n-8)+(n-16);
        }  
        else{
            return n+(n-8)+(n-16)+(n-24);
        } 

    }
}