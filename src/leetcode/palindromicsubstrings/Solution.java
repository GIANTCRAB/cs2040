package leetcode.palindromicsubstrings;

class Solution {
    public int countSubstrings(String s) {
        int palindromeCount = 0;
        final int stringLength = s.length();

        for (int mainIndex = 0; mainIndex < stringLength; mainIndex++) {
            final char mainChar = s.charAt(mainIndex);
            palindromeCount++;
            // odd check
            palindromeCount += doCount(s, mainIndex, mainIndex);

            // even check
            final int forwardIndex = mainIndex + 1;
            if (forwardIndex != stringLength) {
                final char forwardChar = s.charAt(forwardIndex);
                if (mainChar == forwardChar) {
                    palindromeCount++;
                    palindromeCount += doCount(s, mainIndex, forwardIndex);
                }
            }
        }

        return palindromeCount;
    }

    private int doCount(String s, int startingLeftIndex, int startingRightIndex) {
        return doCountIter(s, startingLeftIndex - 1, startingRightIndex + 1, 0);
    }

    private int doCountIter(String s, int leftIndex, int rightIndex, int totalCount) {
        final int stringLength = s.length();
        if (leftIndex != -1 && rightIndex != stringLength) {
            if (s.charAt(leftIndex) == s.charAt(rightIndex)) {
                return doCountIter(s, leftIndex - 1, rightIndex + 1, totalCount + 1);
            }
        }

        return totalCount;
    }
}