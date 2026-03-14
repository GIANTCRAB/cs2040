package leetcode.validparentheses;
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        // My initial thinking is that we can have a O(n) space that stores the number of open brackets for the corresponding data.
        // Maybe a Map<Character, Integer>
        // actually on 2nd thought, i think we can just have 3 different variables
        // Hmm it seems like the opening and closing of bracket order matters
        // Perhaps we should use a stack instead
        Stack<Character> expectedClosingBrackets = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if(currentChar == '(') {
                expectedClosingBrackets.push(')');
            }
            if(currentChar == '[') {
                expectedClosingBrackets.push(']');
            }
            if(currentChar == '{') {
                expectedClosingBrackets.push('}');
            }

            if(currentChar == ')' || currentChar == ']' || currentChar == '}') {
                if(expectedClosingBrackets.isEmpty()) {
                    return false;
                }
                char expectedClosingBracket = expectedClosingBrackets.pop();
                if(currentChar != expectedClosingBracket) {
                    return false;
                }
            }
        }

        return expectedClosingBrackets.isEmpty();
    }
}
