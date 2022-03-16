package com.yangweiye.springbootdemos.questions;

public class BitQuestions {
    public static void main(String[] args) {
        BitQuestions bitQuestions = new BitQuestions();
        System.out.println(bitQuestions.add(11, 2));
    }

    public int add(int a, int b) {
        //不 加号 做加法
        // a + b = 非进位 + 进位
        // 如果进位 为0 则直接返回非进位
        while (b != 0) {
            int c = (a & b) << 1;
            a = a ^ b;
            b = c;
        }

        return a;
    }
}
