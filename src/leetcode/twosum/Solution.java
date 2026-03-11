package leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int diff = target - value;
            if(data.containsKey(diff)) {
                int secondIndex = data.get(diff);
                result[0] = i;
                result[1] = secondIndex;
                return result;
            }

            if(!data.containsKey(value)) {
                data.put(value, i);
            }
        }

        return result;
    }
}
