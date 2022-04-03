package leetcode.combinationsumtwo;

import java.util.*;

// Bruteforce method that managed to pass
// TODO: check if there's a better way to do this?
public class Solution {
    final List<List<Integer>> fullList = new ArrayList<>();
    final Map<Integer, Integer> allowedUses = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for (final int amount : candidates) {
            allowedUses.put(amount, allowedUses.getOrDefault(amount, 0) + 1);
        }
        this.getValidList(List.of(), Arrays.stream(candidates).distinct().sorted().toArray(), target, 0);

        return fullList;
    }

    private void getValidList(List<Integer> currentResults, int[] candidates, int target, int startingIndex) {
        final List<Integer> cloneOfList = new ArrayList<>(currentResults);
        for (int i = startingIndex; i < candidates.length; i++) {
            final int amount = candidates[i];
            if (cloneOfList.stream().filter(result -> result == amount).count() < allowedUses.get(amount)) {
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
        }

        for (int i = startingIndex; i < candidates.length; i++) {
            final int amount = candidates[i];
            if (cloneOfList.stream().filter(result -> result == amount).count() < allowedUses.get(amount)) {
                final List<Integer> secondCloneOfList = new ArrayList<>(cloneOfList);
                secondCloneOfList.add(amount);
                final int sum = this.getSumOfArray(secondCloneOfList);
                if (sum < target) {
                    this.getValidList(secondCloneOfList, candidates, target, i);
                } else {
                    break;
                }
            }
        }
    }

    private Integer getSumOfArray(List<Integer> givenArray) {
        return givenArray.stream().reduce(Integer::sum).orElse(0);
    }
}