package leetcode.repeateddnasequences;

import java.util.*;

// How I came into this solution:
// I thought about ways we could hash something.
// the use case is a fixed length of 10, this made it very simple.
// I can iterate through the string and keep slicing out from index n to n + 10
// Initially, I was thinking of using hash map with a boolean.
// Then I realise, I have no use for a boolean, can we be more simple?
// Then I realise we can use Set.
// Then I think about how we could store repeated sequences
// I went with one set with one list initially. then I encountered testcases that failed me because I am supposed to return unique sequences.
// Then I use 2 sets, and then converted the repeated sequences into a list after Set.
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        final Set<String> data = new HashSet<>();
        final Set<String> repeatedSequences = new HashSet<>();

        final var stringLength = s.length();
        for (var startIndex = 0; startIndex < stringLength; startIndex++) {
            final var endIndex = startIndex + 10;
            if(endIndex <= stringLength) {
                final var splitString = s.substring(startIndex, endIndex);
                if(data.contains(splitString)) {
                    repeatedSequences.add(splitString);
                } else {
                    data.add(splitString);
                }
            }
        }

        return List.copyOf(repeatedSequences);
    }
}
