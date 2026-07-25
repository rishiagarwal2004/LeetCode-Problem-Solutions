class Solution {
    public int maxProduct(int n) {
       ArrayList<Integer> arr = new ArrayList<>(); 
        while(n>0){
            int temp=n%10;
            arr.add(temp);
            n=n/10;
        }
        int product=0;
        for(int i=0;i<arr.size();i++){
            for(int j=0;j<arr.size();j++){
                if(i!=j){
                    int mul=arr.get(i)*arr.get(j);
                    if(mul>product){
                        product=mul;
                    }
                }
            }
        }
        return product;

    }
}