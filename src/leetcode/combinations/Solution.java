package leetcode.combinations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public List<List<Integer>> combine(int n, int maxSizeLeft) {
        // form the smallest possible number with combination first
        final List<List<Integer>> fullList = new ArrayList<>();
        final List<Integer> leftArray = IntStream.rangeClosed(1, maxSizeLeft).collect(ArrayList::new, List::add, List::addAll);
        final List<Integer> rightArray = IntStream.rangeClosed(maxSizeLeft, n).collect(ArrayList::new, List::add, List::addAll);
        fullList.add(new ArrayList<>(leftArray));
        int currentIndexLeft = maxSizeLeft - 1;

        while (currentIndexLeft != -1) {
            final boolean hasMutated = this.compareAndReplace(currentIndexLeft, currentIndexLeft, leftArray, rightArray);

            if (!hasMutated) {
                while (true) {
                    // shift left index
                    currentIndexLeft--;
                    if (currentIndexLeft != -1) {
                        // Remove everything behind the currentIndexLeft
                        this.removeBack(currentIndexLeft, leftArray, rightArray);
                        final boolean toReset = this.compareAndReplace(currentIndexLeft, currentIndexLeft, leftArray, rightArray);
                        // reset all left values after index in leftArray
                        // move from left to right manner
                        for (int resetIndexLeft = currentIndexLeft; resetIndexLeft < maxSizeLeft - 1; resetIndexLeft++) {
                            this.compareAndAdd(resetIndexLeft, leftArray, rightArray);
                        }
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
                // sort right array
                rightArray.sort(Comparator.naturalOrder());
                hasMutated = true;
                break;
            }
        }

        return hasMutated;
    }

    private void removeBack(int leftIndexSeparator, List<Integer> leftArray, List<Integer> rightArray) {
        while (leftIndexSeparator + 1 < leftArray.size()) {
            final int tempValue = leftArray.get(leftIndexSeparator + 1);
            leftArray.remove(leftIndexSeparator + 1);
            rightArray.add(tempValue);
        }

        // sort right array
        rightArray.sort(Comparator.naturalOrder());
    }

    private void compareAndAdd(int leftIndexToCompare, List<Integer> leftArray, List<Integer> rightArray) {
        for (int i = 0; i < rightArray.size(); i++) {
            final int rightValue = rightArray.get(i);
            final int leftValue = leftArray.get(leftIndexToCompare);
            if (leftValue < rightValue) {
                leftArray.add(rightValue);
                rightArray.remove(i);
                // sort right array
                rightArray.sort(Comparator.naturalOrder());
                break;
            }
        }
    }
}