package leetcode.palindromicsubstrings;

// I first identified the pattern that we can initialise count as the string length itself
// then, I identify that we have 2 types of palindromes - odd and even
// we can center our checks around a rootIndex
// everytime we begin our checks, leftIndex will decrement, rightIndex will increase
// I check that the character on left == character on right
// if that fails, then it is not a palindrome and we can break its sub-loop
class Solution {
    public int countSubstrings(String s) {
        int count = s.length();

        if(count == 1) {
            return count;
        }

        for(int rootIndex = 0; rootIndex < s.length(); rootIndex++) {
            int leftIndex = rootIndex;
            int rightIndex = rootIndex + 1;

            // check even
            while(leftIndex >= 0 && rightIndex < s.length()) {
                if(s.charAt(leftIndex) == s.charAt(rightIndex)) {
                    leftIndex--;
                    rightIndex++;
                    count++;
                } else {
                    break;
                }
            }

            leftIndex = rootIndex - 1;
            rightIndex = rootIndex + 1;
            // check odd
            while(leftIndex >= 0 && rightIndex < s.length()) {
                if(s.charAt(leftIndex) == s.charAt(rightIndex)) {
                    leftIndex--;
                    rightIndex++;
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }
}