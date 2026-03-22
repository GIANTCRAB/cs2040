package leetcode.validanagram;

class Solution {
    // I am thinking if I can use a HashMap to contain data of the number of occurrences
    // first pass to store s in space. O(s) space + O(s) performance
    // second pass to check number of usage O(t) performance
    // total performance: O(s) + O(t) = O(s)
    // actually, now that i think about it
    // we can transform them into simple string
    // use a fixed size array char[26]
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        char[] data = new char[26]; // only 26 characters
        for(int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            data[(character - 97)]++;
        }

        char[] secondData = new char[26]; // only 26 characters
        for(int i = 0; i < t.length(); i++) {
            char character = t.charAt(i);
            secondData[(character - 97)]++;
        }

        return String.valueOf(data).equals(String.valueOf(secondData));
    }
}