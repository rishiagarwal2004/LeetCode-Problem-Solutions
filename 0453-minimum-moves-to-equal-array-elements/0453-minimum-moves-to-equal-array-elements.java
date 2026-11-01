class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int min = Arrays.stream(nums).min().getAsInt();
        return sum - (n * min);
    }
}