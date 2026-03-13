package leetcode.wordbreak;

import java.util.*;

class Solution {
    record QueueItem(int leftIndex) {
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        // First I am thinking of whether or not we can use Set here
        // i am thinking that it seems like all words from wordDict must be used up
        // we must use up wordDict effectively
        // In a way, it seems like checking for valid sudoku
        // now i am thinking, wordDict strings need to have a priority
        // maybe the longest string should be given priority to fit, since that's the hardest to fit
        // So we do a single pass first
        // and then once single pass goes through, it is FFA to fit
        // I also just realised that my understanding of the question might be incorrect
        // I want to see if it is as i understood
        // I'll write test case with the word "leetscode" and worddict = "leet", "code"
        // - Test case failed
        // I'll write test case with the word "leetcode" and worddict = "code"
        // - Test case failed
        // I'll write test case with the word "leetcode" and worddict = "leet", "code", "lmao"
        // - Test case pass
        // Seems like i understood it incorrectly. we don't actually have to use up all the words in wordDict
        // Let's go with a "two pointer" style, start from left of the string and the most right side of string
        // My greedy method of small window expanding larger did not work as there were cases that required non-greedy
        // Tried greedy method of large window shrinking. did not work either as there were cases that required non-greedy
        // Combination of both small/large window also did not work
        // This seems to be a BFS or DFS problem
        // I think this might be BFS
        // The initial solution with LinkedList queue worked but TLE
        // I add an optimisation that uses PriorityQueue with comparator but it is still insufficient
        // I add an additional Set called processedIndices to indicate the visited nodes
        // This seem to work
        Set<String> wordSet = Set.copyOf(wordDict);
        Queue<QueueItem> processingQueue = new PriorityQueue<>((item1, item2) -> item2.leftIndex() - item1.leftIndex());
        Set<Integer> processedIndices = new HashSet<>();

        processingQueue.add(new QueueItem(0));
        while (!processingQueue.isEmpty()) {
            QueueItem queueItem = processingQueue.poll();
            int leftIndex = queueItem.leftIndex();
            for(int rightIndex = leftIndex; rightIndex < s.length(); rightIndex++) {
                String subString = s.substring(leftIndex, rightIndex + 1);
                if(wordSet.contains(subString)) {
                    if((rightIndex + 1) == s.length()) {
                        return true;
                    } else {
                        if(!processedIndices.contains(rightIndex + 1)) {
                            processedIndices.add(rightIndex + 1);
                            processingQueue.add(new QueueItem(rightIndex + 1));
                        }
                    }
                }
            }
        }
        return false;
    }
}
