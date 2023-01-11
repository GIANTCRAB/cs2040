package leetcode.subsets;

import java.util.*;

// TODO: Improve runtime from 2ms to 0ms?
// TODO: Is there a way to use more primitives?

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        final List<List<Integer>> fullList = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            choose(nums, i, new ArrayList<>(), 0, fullList);
        }
        return fullList;
    }

    private static void choose(int[] totalSet, int howManyToChoose, List<Integer> currentlyChosen, Integer lastChosenIndex, List<List<Integer>> results) {
        if(totalSet.length != 0) {
            if(currentlyChosen.size() < howManyToChoose) {
                if(currentlyChosen.size() != 0) {
                    final List<Integer> children = getChildren(totalSet, lastChosenIndex);
                    for (int i = 0; i < children.size(); i++) {
                        final List<Integer> cloneList = new ArrayList<>(currentlyChosen);
                        cloneList.add(children.get(i));
                        // because it added itself, we need to increment by a + 1 and a + i (for its position)
                        choose(totalSet, howManyToChoose, cloneList, lastChosenIndex + i + 1, results);
                    }
                } else {
                    for (int i = 0; i < totalSet.length; i++) {
                        final List<Integer> cloneList = new ArrayList<>(currentlyChosen);
                        cloneList.add(totalSet[i]);
                        // as we are just starting off, we only just need to increment by a + i (for its position)
                        choose(totalSet, howManyToChoose, cloneList, lastChosenIndex + i, results);
                    }
                }
            } else {
                results.add(new ArrayList<>(currentlyChosen));
            }
        } else {
            results.add(new ArrayList<>(currentlyChosen));
        }
    }

    private static List<Integer> getChildren(int[] totalSet, int index) {
        final List<Integer> children = new ArrayList<>();
        // To exclude itself from the children list, we need to increment by +1
        for (int i = index + 1; i < totalSet.length; i++) {
            children.add(totalSet[i]);
        }
        return children;
    }
}