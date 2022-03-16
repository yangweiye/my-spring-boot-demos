package com.yangweiye.springbootdemos.questions;

public class StringQuestions {
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ')
                stringBuilder.append("%20");
            else
                stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public String reverseLeftWords(String s, int n) {
        int length = s.length();
        if (length < 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n; i < length; i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
