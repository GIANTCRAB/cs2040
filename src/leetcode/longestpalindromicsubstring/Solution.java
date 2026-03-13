package leetcode.longestpalindromicsubstring;

class Solution {
    public String longestPalindrome(String s) {
        String longestPalindrome = s.substring(0, 1);
        for(int rootIndex = 0; rootIndex < s.length(); rootIndex++) {
            // We do two type of checks, the odd palindrome and the even palindrome
            int leftIndex = rootIndex;
            int rightIndex = rootIndex + 1;

            // Even palindrome check
            while(leftIndex >= 0 && rightIndex < s.length()) {
                if(s.charAt(leftIndex) == s.charAt(rightIndex)) {
                    String currentString = s.substring(leftIndex, rightIndex + 1);
                    if(currentString.length() > longestPalindrome.length()) {
                        longestPalindrome = currentString;
                    }
                    leftIndex--;
                    rightIndex++;
                } else {
                    break;
                }
            }

            leftIndex = rootIndex - 1;
            rightIndex = rootIndex + 1;

            // Odd palindrome check
            while(leftIndex >= 0 && rightIndex < s.length()) {
                if(s.charAt(leftIndex) == s.charAt(rightIndex)) {
                    String currentString = s.substring(leftIndex, rightIndex + 1);
                    if(currentString.length() > longestPalindrome.length()) {
                        longestPalindrome = currentString;
                    }
                    leftIndex--;
                    rightIndex++;
                } else {
                    break;
                }
            }

            // optimisation: if the (remaining string length * 2 + 1) is smaller than or equal the longest substring length, we can end the loop
            int remainingLength = s.length() - rootIndex;
            int cutOff = (remainingLength * 2) + 1;
            if(cutOff <= longestPalindrome.length()) {
                break;
            }
        }

        return longestPalindrome;
    }
}