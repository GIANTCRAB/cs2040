package leetcode.longestsubstringwithoutrepeatingcharacters;

import java.util.*;

// Sliding window problem
// Started off with a 2 pointer without proper caching. It worked and did not TLE.
// Thereafter, spent some time optimising and got a proper solution
// I remove the characters on left if we are shrinking the window
// And append if we are increasing the window
// If the character on the right hand side already exists, we switch to decreasing the window
// Whenever we decrease the window size, we also check if we can go back to increasing the window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) {
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        int longestSubStringLength = 1;
        Set<Character> currentSubstringCharacters = new HashSet<>();
        int leftIndex = 0;
        int rightIndex = 1;
        currentSubstringCharacters.add(s.charAt(leftIndex));
        boolean increasingRight = true;
        while(rightIndex < s.length()) {
            if(increasingRight) {
                if(currentSubstringCharacters.contains(s.charAt(rightIndex))) {
                    // duplicate, stop increasing right.
                    increasingRight = false;
                } else {
                    currentSubstringCharacters.add(s.charAt(rightIndex));
                    rightIndex++;
                }
                if(currentSubstringCharacters.size() > longestSubStringLength) {
                    longestSubStringLength = currentSubstringCharacters.size();
                }
            } else {
                // Check if we can start increase right
                if(currentSubstringCharacters.contains(s.charAt(rightIndex))) {
                    // Shrinking the window by increasingLeft
                    currentSubstringCharacters.remove(s.charAt(leftIndex));
                    leftIndex++;
                } else {
                    // Yes we can
                    increasingRight = true;
                }
            }

            if(leftIndex == rightIndex) {
                increasingRight = true;
            }
        }
        return longestSubStringLength;
    }
}
