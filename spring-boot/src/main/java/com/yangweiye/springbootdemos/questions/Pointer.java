package com.yangweiye.springbootdemos.questions;

import java.util.Arrays;

public class Pointer {
    public static void main(String[] args) {
        Pointer pointer = new Pointer();
        System.out.println(pointer.reverseWords("the sky is blue"));
    }

    public int[] exchange(int[] nums) {
        int odd = 0, even = nums.length - 1, temp = 0;
        while (even > odd) {
            while (even > odd) {
                if ((nums[odd] & 1) != 1)
                    break;
                odd++;
            }
            while (even > odd) {
                if ((nums[even] & 1) == 1) {
                    break;
                }
                even--;
            }
            temp = nums[odd];
            nums[odd++] = nums[even];
            nums[even--] = temp;
        }

        return nums;
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        int i = s.length() - 1, j = i;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                if (s.charAt(j) != ' ') {
                    sb.append(s, i + 1, j + 1);
                    sb.append(' ');
                    do {
                        i--;
                    } while (s.charAt(i) == ' ');
                }
                j = i;
            }
            i--;
        }
        sb.append(s, i + 1, j + 1);
        return sb.toString();
    }
}
