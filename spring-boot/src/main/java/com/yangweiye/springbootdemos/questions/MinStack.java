package com.yangweiye.springbootdemos.questions;

public class MinStack {
    private int[] stack1 = new int[10000];
    private int[] stack2 = new int[10000];
    private int index1 = -1;
    private int index2 = -1;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        stack1[++index1] = x;
        if (-1 == index2 || stack2[index2] >= x) {
            stack2[++index2] = x;
        }
    }

    public void pop() {
        if (stack1[index1--] == stack2[index2]) {
            index2--;
        }
    }

    public int top() {
        return stack1[index1];
    }

    public int min() {
        return stack2[index2];
    }
}
