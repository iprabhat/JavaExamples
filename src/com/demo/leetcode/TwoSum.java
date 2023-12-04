package com.demo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
       
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n1 = nums[i];
            int n2 = target - n1;
            int idx = m.getOrDefault(n2, -1);
            if (idx >= 0) {
                result[0] = idx;
                result[1] = i;
                break;
            } else{
                m.put(n1, i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 26;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
