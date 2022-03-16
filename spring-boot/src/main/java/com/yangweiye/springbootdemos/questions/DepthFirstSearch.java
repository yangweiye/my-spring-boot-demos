package com.yangweiye.springbootdemos.questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DepthFirstSearch {
    public static void main(String[] args) {
        char[][] chars = new char[3][4];
        chars[0] = new char[]{'C', 'A', 'A'};
        chars[1] = new char[]{'A', 'A', 'A'};
        chars[2] = new char[]{'B', 'C', 'D'};

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
//        boolean abcced = depthFirstSearch.exist(chars, "AAB");
//        System.out.println(abcced);
        int i = depthFirstSearch.movingCount(1, 2, 1);
        System.out.println(i);
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length, line = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < line; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int row, int line, String word, int i) {
        boolean result = false;
        //边界检查
        if (row < 0 || row >= board.length || line < 0 || line >= board[0].length) return false;
        if (board[row][line] == word.charAt(i)) {
            if (i == word.length() - 1) return true;
            board[row][line] = ' ';
            result = dfs(board, row + 1, line, word, i + 1) ||
                    dfs(board, row, line + 1, word, i + 1) ||
                    dfs(board, row - 1, line, word, i + 1) ||
                    dfs(board, row, line - 1, word, i + 1);
            //复原数组
            board[row][line] = word.charAt(i);
        }

        return result;
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] booleans = new boolean[m][n];
        return dfs1(booleans, 0, 0, k);
    }

    private int dfs1(boolean[][] table, int row, int line, int k) {
        if (row < 0 || row >= table.length || line < 0 || line >= table[0].length) return 0;
        if (!table[row][line] && isReachable(row, line, k)) {
            table[row][line] = true;
            return 1 + dfs1(table, row + 1, line, k) +
                    dfs1(table, row, line + 1, k) +
                    dfs1(table, row - 1, line, k) +
                    dfs1(table, row, line - 1, k);
        }
        return 0;
    }

    private boolean isReachable(int row, int line, int k) {
        return (row / 10) + (row % 10) + (line / 10) + (line % 10) <= k;
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        LinkedList<Integer> temp = new LinkedList<>();
        dfs2(root, result, temp, target);

        return result;
    }

    private void dfs2(TreeNode root, List<List<Integer>> result, LinkedList<Integer> temp, int target) {
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                temp.addLast(root.val);
                result.add(new ArrayList<>(temp));
                temp.removeLast();
            }
            return;
        }
        temp.addLast(root.val);
        if (null != root.left)
            dfs2(root.left, result, temp, target - root.val);
        if (null != root.right)
            dfs2(root.right, result, temp, target - root.val);
        temp.removeLast();
    }

    private Node pre = null;
    private Node head = null;

    public Node treeToDoublyList(Node root) {
        dfs3(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs3(Node root) {
        if (null == root) return;
        dfs3(root.left);
        if (null != pre) {
            pre.right = root;
            root.left = pre;
        } else {
            head = root;
        }
        pre = root;
        dfs3(root.right);
    }

    private int result;
    private int k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs4(root);
        return this.result;
    }

    private void dfs4(TreeNode root) {
        if (null == root) return;
        dfs4(root.right);
        if (--k == 0) {
            result = root.val;
            return;
        }
        dfs4(root.left);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
