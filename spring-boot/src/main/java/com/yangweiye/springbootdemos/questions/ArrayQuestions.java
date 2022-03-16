package com.yangweiye.springbootdemos.questions;

public class ArrayQuestions {
    public int findRepeatNumber(int[] nums) {
        //[2, 3, 1, 0, 2, 5, 3]
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]])
                    return nums[i];
                temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println('a' - 97);
    }

    //[8] 9
    //[5,7,7,8,8,10]
    //[5,7,7,8,8,9]
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        //这个要用二分查找查找边界 边界：不包含 因为有target不存在nums里这种情况.
        int l = 0, h = nums.length - 1, m = 0;

        while (h >= l) {
            m = l + (h - l >> 1);
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }

        if (h >= 0 && nums[h] != target) return 0;

        int e = l, s = 0;

        l = 0;
        h = e;

        //[5,7,7,8,8,10] 6
        while (h >= l) {
            m = l + (h - l >> 1);
            if (nums[m] >= target) {
                h = m - 1;
            } else {
                if (h == l)
                    break;
                l = m + 1;
            }
        }

        s = h;

        return e - s - 1;
    }

    public int missingNumber(int[] nums) {
        if (nums.length == 0)
            return -1;
        int l = 0, h = nums.length - 1, m = 0;

        while (h >= l) {
            m = l + (h - l >> 1);
            if (nums[m] == m) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l;
        //m就是边界
        //突然发现个规律 找右边界就是 左下标 反之 则是 右下标
        //[5,6,7,9]
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int high = matrix.length;
        if (high == 0)
            return false;
        int width = matrix[0].length;
        high--;
        width--;
        int line = 0;

        while (line <= high && width >= 0) {
            if (target > matrix[line][width]) {
                line++;
            } else if (target < matrix[line][width]) {
                width--;
            } else {
                return true;
            }
        }

        return false;
    }

    //3,4,5,1,2
    //2 2 2
    //大 前
    //小 后
    //等
    public int minArray(int[] numbers) {
        //整体思想为 有序数组旋转后 即使移除一些数依然保持该特性
        //至于解法 不容想到 记下来！！
        if (numbers.length == 0)
            return -1;
        int s = 0, e = numbers.length - 1, m = 0;

        while (s <= e) {

            m = s + (e - s >> 1);
            if (numbers[e] > numbers[m]) {
                e = m;
            } else if (numbers[e] < numbers[m]) {
                s = m + 1;
            } else {
                e--;
            }
        }

        //根据经验得出 右边界 返回左下标
        return numbers[s];
    }

    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] list = new int[26];
        for (char c : chars) {
            list[c - 97]++;
        }
        for (char c : chars) {
            if (list[c - 97] > 1) {
                return c;
            }
        }

        return ' ';
    }
}
