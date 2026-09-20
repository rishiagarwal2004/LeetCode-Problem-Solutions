class Solution {
    public int reverseDegree(String s) {
        char [] arr = s.toCharArray();
        int k =1;
        int sum =0;

        for(int i =0;i<arr.length;i++){
            int n = -((int)arr[i] - 'z'-1);
            sum = sum + (n * k) ;
            k++;
        }
        return sum;
    }
}