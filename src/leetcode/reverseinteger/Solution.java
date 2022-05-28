package leetcode.reverseinteger;

class Solution {
    public int reverse(int x) {
        final boolean isNegative = x < 0;
        final String minValue = "2147483648";
        final String maxValue = "2147483647";
        final int lengthCheck = maxValue.length();

        // this part can be removed and replaced with StringBuilder method .reverse()
        final StringBuilder givenString = new StringBuilder(String.valueOf(Math.abs(x)));
        int leftIndex = 0;
        int rightIndex = givenString.length() - 1;
        while (leftIndex < rightIndex) {
            final String leftChar = String.valueOf(givenString.charAt(leftIndex));
            final String rightChar = String.valueOf(givenString.charAt(rightIndex));
            givenString.replace(leftIndex, leftIndex + 1, rightChar);
            givenString.replace(rightIndex, rightIndex + 1, leftChar);
            leftIndex++;
            rightIndex--;
        }

        // remove zeros
        while (!givenString.isEmpty()) {
            final char firstChar = givenString.charAt(0);
            if (firstChar == '0') {
                givenString.deleteCharAt(0);
            } else {
                break;
            }
        }

        // check for length
        if (givenString.isEmpty()) {
            return 0;
        }

        final int reversedLength = givenString.length();
        if (reversedLength == lengthCheck) {
            if (isNegative) {
                if (this.stringComparison(minValue, givenString.toString())) {
                    return -Integer.parseInt(givenString.toString());
                }
            } else {
                if (this.stringComparison(maxValue, givenString.toString())) {
                    return Integer.parseInt(givenString.toString());
                }
            }
        } else {
            if (reversedLength < lengthCheck) {
                final int result = Integer.parseInt(givenString.toString());
                return isNegative ? -result : result;
            }
        }

        return 0;
    }

    private boolean stringComparison(String checkAgainst, String givenString) {
        final int checkAgainstLength = checkAgainst.length();
        for (int mainIndex = 0; mainIndex < checkAgainstLength; mainIndex++) {
            final int mainInteger = Integer.parseInt(String.valueOf(givenString.charAt(mainIndex)));
            final int checkAgainstInteger = Integer.parseInt(String.valueOf(checkAgainst.charAt(mainIndex)));
            if (mainInteger < checkAgainstInteger) {
                return true;
            } else {
                if (mainInteger > checkAgainstInteger) {
                    return false;
                }
            }
        }

        return true;
    }
}