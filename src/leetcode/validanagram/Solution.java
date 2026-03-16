package leetcode.validanagram;

import java.util.*;

class Solution {
    // I am thinking if I can use a HashMap to contain data of the number of occurrences
    // first pass to store s in space. O(s) space + O(s) performance
    // second pass to check number of usage O(t) performance
    // total performance: O(s) + O(t) = O(s)
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> data = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            Character characterAppearance = s.charAt(i);
            if(data.containsKey(characterAppearance)) {
                Integer existingData = data.get(characterAppearance);
                existingData++;
                data.put(characterAppearance, existingData);
            } else {

                data.put(characterAppearance, 1);
            }
        }

        for(int j = 0; j < t.length(); j++) {
            Character characterAppearance = t.charAt(j);
            if(data.containsKey(characterAppearance)) {
                Integer existingData = data.get(characterAppearance);
                if(existingData == 0) {
                    return false;
                }
                existingData--;
                data.put(characterAppearance, existingData);
            } else {
                return false;
            }
        }
        return true;
    }
}