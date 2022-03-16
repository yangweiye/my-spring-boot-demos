package com.yangweiye.springbootdemos.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MockQuestions {
    public static void main(String[] args) {
        MockQuestions mockQuestions = new MockQuestions();
//        mockQuestions.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
        int[][] a = new int[3][3];
        a[0] = new int[]{1, 2, 3};
        a[1] = new int[]{4, 5, 6};
        a[2] = new int[]{7, 8, 9};
        int[] ints = mockQuestions.spiralOrder(a);
        System.out.println(Arrays.toString(ints));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) return true;
        int i = 0, j = 0;

        Stack<Integer> integers = new Stack<>();
        integers.push(pushed[i++]);

        while (i < pushed.length && j < popped.length) {
            if (!integers.isEmpty() && integers.peek() == popped[j]) {
                j++;
                integers.pop();
            } else {
                integers.push(pushed[i++]);
            }
        }
        while (!integers.isEmpty()) {
            if (integers.peek() == popped[j]) {
                j++;
                integers.pop();
                continue;
            }
            break;
        }

        return j == popped.length;
    }

    public int[] spiralOrder(int[][] matrix) {
        int row = matrix.length, cloumn = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        //顶         底
        int rs = 0, re = row - 1;
        //左         右
        int cs = 0, ce = cloumn - 1;

        while (true) {
            for (int i = cs; i <= ce; i++) {
                result.add(matrix[rs][i]);
            }
            rs++;
            if (rs > re) break;

            for (int i = rs; i <= re; i++) {
                result.add(matrix[i][ce]);
            }
            ce--;
            if (cs > ce) break;

            for (int i = ce; i >= cs; i--) {
                result.add(matrix[re][i]);
            }
            re--;
            if (rs > re) break;

            for (int i = re; i >= rs; i--) {
                result.add(matrix[i][cs]);
            }
            cs++;
            if (cs > ce) break;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
