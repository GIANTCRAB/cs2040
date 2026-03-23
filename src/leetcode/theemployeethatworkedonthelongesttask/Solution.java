package leetcode.theemployeethatworkedonthelongesttask;

class Solution {
    // seems like simple logic and state storage
    // single for loop is sufficient
    public int hardestWorker(int n, int[][] logs) {
        int longestTime = 0;
        int longestTimeEmployeeId = -1;

        int previousTime = 0;

        for (int i = 0; i < logs.length; i++) {
            int[] log = logs[i];
            int timeTaken = log[1] - previousTime;
            previousTime = log[1];

            if(timeTaken >= longestTime) {
                if(timeTaken > longestTime) {
                    longestTime = timeTaken;
                    longestTimeEmployeeId = log[0];
                } else {
                    longestTimeEmployeeId = Math.min(longestTimeEmployeeId, log[0]);
                }
            }
        }

        return longestTimeEmployeeId;
    }
}