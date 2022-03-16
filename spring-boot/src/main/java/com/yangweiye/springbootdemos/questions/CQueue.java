package com.yangweiye.springbootdemos.questions;

public class CQueue {
    private int[] stack1 = new int[10000];
    private int[] stack2 = new int[10000];
    private int index1 = -1;
    private int index2 = -1;

    public CQueue() {

    }

    public void appendTail(int value) {
        stack1[++index1] = value;
    }

    public int deleteHead() {
        if (-1 == index2) {
            if (-1 == index1) {
                return -1;
            }
            while (index1 > -1) {
                stack2[++index2] = stack1[index1--];
            }
        }

        return stack2[index2--];
    }
}
