class Solution {
    public int maxProduct(int n) {
       ArrayList<Integer> arr = new ArrayList<>(); 
        while(n>0){
            int temp=n%10;
            arr.add(temp);
            n=n/10;
        }
        Collections.sort(arr);
        int size=arr.size();
        return arr.get(size-1)*arr.get(size-2);

    }
}