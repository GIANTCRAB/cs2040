package leetcode.validpalindrome;

import java.lang.*;

// I am thinking of solving it via 2 pointers method
// We should ignore checking when we encounter non-alphanumeric
// We also need to make sure to lowercase them.
// Start from the most left and the most right and proceed towards center
// Whenever both characters are valid, apply the check
// Whenever the check returns true, we increase the left index and decrease the right index
// Break the loop if check returns false or if the leftIndex >= rightIndex
class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 1) {
            return true;
        }
        boolean isPalindrome = true;
        int leftIndex = 0;
        int rightIndex = s.length() - 1;
        while(true) {
            boolean shouldCheck = true;
            char leftCharacter = Character.toLowerCase(s.charAt(leftIndex));
            char rightCharacter = Character.toLowerCase(s.charAt(rightIndex));
            if(!(Character.isAlphabetic(leftCharacter) || Character.isDigit(leftCharacter))) {
                if(leftIndex + 1 < rightIndex) {
                    leftIndex++;
                    shouldCheck = false;
                } else {
                    break;
                }
            }
            if(!(Character.isAlphabetic(rightCharacter) || Character.isDigit(rightCharacter))) {
                if(rightIndex - 1 > leftIndex) {
                    rightIndex--;
                    shouldCheck = false;
                } else {
                    break;
                }
            }
            if(shouldCheck) {
                if(leftCharacter != rightCharacter) {
                    isPalindrome = false;
                    break;
                } else {
                    leftIndex++;
                    rightIndex--;
                    if(leftIndex >= rightIndex) {
                        break;
                    }
                }
            }
        }
        return isPalindrome;
    }
}