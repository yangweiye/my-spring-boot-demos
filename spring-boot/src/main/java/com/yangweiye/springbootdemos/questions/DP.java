package com.yangweiye.springbootdemos.questions;

import java.util.HashMap;
import java.util.Map;

public class DP {
//    "aabcabcbb"
//    "abcdcbb"

    public int lengthOfLongestSubstring(String s) {
        int result = 0, temp = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            Integer last = map.getOrDefault(chars[i], -1);
            if (i - last > temp + 1) {
                temp++;
            } else {
                temp = i - last;
            }
            result = Math.max(temp, result);
            map.put(chars[i], i);
        }
        return result;
    }

}
