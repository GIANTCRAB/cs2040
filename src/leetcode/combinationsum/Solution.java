package leetcode.combinationsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Bruteforce method that managed to pass
// TODO: check if there's a better way to do this?
public class Solution {
    final List<List<Integer>> fullList = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.getValidList(List.of(), Arrays.stream(candidates).sorted().toArray(), target, 0);

        return fullList;
    }

    private void getValidList(List<Integer> currentResults, int[] candidates, int target, int startingIndex) {
        final List<Integer> cloneOfList = new ArrayList<>(currentResults);
        for (int i = startingIndex; i < candidates.length; i++) {
            final int amount = candidates[i];
            cloneOfList.add(amount);
            final int sum = this.getSumOfArray(cloneOfList);
            if (sum >= target) {
                if (sum == target) {
                    this.fullList.add(new ArrayList<>(cloneOfList));
                }
                cloneOfList.remove(cloneOfList.size() - 1);
                break;
            } else {
                cloneOfList.remove(cloneOfList.size() - 1);
            }
        }

        for (int i = startingIndex; i < candidates.length; i++) {
            final List<Integer> secondCloneOfList = new ArrayList<>(cloneOfList);
            secondCloneOfList.add(candidates[i]);
            final int sum = this.getSumOfArray(secondCloneOfList);
            if (sum < target) {
                this.getValidList(secondCloneOfList, candidates, target, i);
            } else {
                break;
            }
        }
    }

    private Integer getSumOfArray(List<Integer> givenArray) {
        return givenArray.stream().reduce(Integer::sum).orElse(0);
    }
}