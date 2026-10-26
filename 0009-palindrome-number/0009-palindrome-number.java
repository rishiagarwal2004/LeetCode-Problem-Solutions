class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        ArrayList<Integer>arr = new ArrayList<>();
        while(x > 0){
            int d = x % 10;
            arr.add(d);
            x = x/10;
        }
        int left = 0;
        int right = arr.size()-1;
        while(left < right){
            if(!arr.get(left).equals(arr.get(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
        
    }
}