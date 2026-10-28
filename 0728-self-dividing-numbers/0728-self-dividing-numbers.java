class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = left; i <= right; i++){
            boolean num = true;
            int j = i; 
            while(j > 0){
                int d = j % 10;
                if((d == 0)||(i % d != 0) ){
                   num = false;
                   break;
                }
                j = j/10;
            }
            if(num == true){
                arr.add(i);
            }
        }
        return arr;
        
    }
}