package leetcode.combinations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public List<List<Integer>> combine(int n, int maxSizeLeft) {
        final List<List<Integer>> fullList = new ArrayList<>();
        // Form the smallest possible number with combination first
        final List<Integer> leftArray = IntStream.rangeClosed(1, maxSizeLeft).collect(ArrayList::new, List::add, List::addAll);
        int currentIndexLeft = maxSizeLeft - 1;
        final List<Integer> rightArray = IntStream.rangeClosed(maxSizeLeft, n).collect(ArrayList::new, List::add, List::addAll);
        fullList.add(new ArrayList<>(leftArray));

        while (currentIndexLeft != -1) {
            final boolean hasMutated = this.compareAndReplace(currentIndexLeft, currentIndexLeft, leftArray, rightArray);

            if (!hasMutated) {
                while (true) {
                    currentIndexLeft--;
                    // Is it still possible to mutate? Or are we out of options?
                    if (currentIndexLeft != -1) {
                        this.removeBack(currentIndexLeft, leftArray, rightArray);
                        final boolean toReset = this.compareAndReplace(currentIndexLeft, currentIndexLeft, leftArray, rightArray);
                        // Reset all left values after index in leftArray
                        // Move from left to right manner
                        for (int resetIndexLeft = currentIndexLeft; resetIndexLeft < maxSizeLeft - 1; resetIndexLeft++) {
                            this.compareAndAdd(resetIndexLeft, leftArray, rightArray);
                        }
                        // Check if everything has been added back
                        // If not everything has been added back, this means we need to increase the depth of mutations to make
                        // Depth of mutation is controlled by currentIndexLeft
                        if (leftArray.size() == maxSizeLeft) {
                            // everything added back just fine
                            if (toReset) {
                                fullList.add(new ArrayList<>(leftArray));
                                // reset currentIndexLeft
                                currentIndexLeft = maxSizeLeft - 1;
                            }
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                fullList.add(new ArrayList<>(leftArray));
            }
        }

        return fullList;
    }

    private boolean compareAndReplace(int leftIndexToCompare, int leftIndexToReplace, List<Integer> leftArray, List<Integer> rightArray) {
        boolean hasMutated = false;

        if (leftIndexToCompare == -1) {
            leftIndexToCompare = 0;
        }
        final int leftValue = leftArray.get(leftIndexToCompare);

        for (int i = 0; i < rightArray.size(); i++) {
            final int rightValue = rightArray.get(i);
            if (leftValue < rightValue) {
                final int tempValue = leftArray.get(leftIndexToReplace);
                leftArray.set(leftIndexToReplace, rightValue);
                rightArray.remove(i);
                rightArray.add(tempValue);
                rightArray.sort(Comparator.naturalOrder());
                hasMutated = true;
                break;
            }
        }

        return hasMutated;
    }

    // Everything after the leftIndexSeparator is removed
    private void removeBack(int leftIndexSeparator, List<Integer> leftArray, List<Integer> rightArray) {
        while (leftIndexSeparator + 1 < leftArray.size()) {
            final int tempValue = leftArray.get(leftIndexSeparator + 1);
            leftArray.remove(leftIndexSeparator + 1);
            rightArray.add(tempValue);
        }

        rightArray.sort(Comparator.naturalOrder());
    }

    // Keep adding to the leftArray until it is full or until we are out of options
    private void compareAndAdd(int leftIndexToCompare, List<Integer> leftArray, List<Integer> rightArray) {
        for (int i = 0; i < rightArray.size(); i++) {
            final int rightValue = rightArray.get(i);
            final int leftValue = leftArray.get(leftIndexToCompare);
            if (leftValue < rightValue) {
                leftArray.add(rightValue);
                rightArray.remove(i);
                rightArray.sort(Comparator.naturalOrder());
                break;
            }
        }
    }
}