package leetcode.groupanagrams;

import java.util.*;

class Solution {
    // i am thinking we can solve them by lengths first
    // since only the same string lengths can be grouped together
    // maybe we can do Sets of int[]?
    // this is because characters probably map towards a number
    // the big issue here is that we are forming groups and this will iterate multiple times
    // is it possible we do a reduction of groups?
    // every string is their own group and then we match their groups and merge
    // let's go with Map<int[], List<String>> anagrams and the transform it into a List<List<String>> thereafter
    // ended up with Map<List<Integer>, List<String>> since we cannot use arrays as key
    // Actually, we since we know the key is definitely fixed size, maybe we can do a single string?
    // Better idea, just cast the string to characters
    // sort the character array using Arrays.sort
    // transform the character array back into a single string
    // use that single string as the key
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();
        for(String str : strs) {
            char[] characters = str.toCharArray();
            Arrays.sort(characters);
            String groupKey = String.valueOf(characters);

            if(anagrams.containsKey(groupKey)) {
                List<String> anagramGroup = anagrams.get(groupKey);
                anagramGroup.add(str);
                anagrams.put(groupKey, anagramGroup);
            } else {
                List<String> anagramGroup = new ArrayList<>();
                anagramGroup.add(str);
                anagrams.put(groupKey, anagramGroup);
            }
        }

        return new ArrayList<>(anagrams.values());
    }
}