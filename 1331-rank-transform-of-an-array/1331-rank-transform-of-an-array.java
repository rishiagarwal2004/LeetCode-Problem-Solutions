class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] copied = arr.clone();
        Arrays.sort(copied);
        HashMap<Integer,Integer> map = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < copied.length; i++) {
            if (!map.containsKey(copied[i])) {
                map.put(copied[i], rank);
                rank++;
            }
        }
        int []newArray = new int[arr.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = map.get(arr[i]);
        }
        return newArray;
        
    }
}