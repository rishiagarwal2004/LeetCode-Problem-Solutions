class Solution {
    private final int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startAlice = convertToDayOfYear(arriveAlice);
        int endAlice = convertToDayOfYear(leaveAlice);
        int startBob = convertToDayOfYear(arriveBob);
        int endBob = convertToDayOfYear(leaveBob);
        int commonStart = Math.max(startAlice, startBob);
        int commonEnd = Math.min(endAlice, endBob);
        if (commonStart <= commonEnd) {
            return commonEnd - commonStart + 1; 
        }
        
        return 0; 
    }
    private int convertToDayOfYear(String dateStr) {
        int month = Integer.parseInt(dateStr.substring(0, 2));
        int day = Integer.parseInt(dateStr.substring(3, 5));

        int dayCount = 0;
        for (int i = 0; i < month - 1; i++) {
            dayCount += daysInMonths[i];
        }
        return dayCount + day;
    }
}
