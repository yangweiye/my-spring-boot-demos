package com.yangweiye.springbootdemos.questions;

import java.util.*;

public class PrintBinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(-4);
        TreeNode treeNode4 = new TreeNode(-3);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;

        TreeNode treeNode5 = new TreeNode(1);
        TreeNode treeNode6 = new TreeNode(0);
        treeNode5.left = treeNode6;

        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
//        System.out.println(printBinaryTree.isSubStructure(treeNode, treeNode5));
//        System.out.println(printBinaryTree.isEqualTo(treeNode, treeNode5));
//        int[] ints = printBinaryTree.levelOrder(treeNode);
//        System.out.println(Arrays.toString(ints));
        System.out.println(printBinaryTree.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode current;
        while (!queue.isEmpty()) {
            LinkedList<Integer> line = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                current = queue.poll();
                if ((result.size() & 1) == 1)
                    line.addFirst(current.val);
                else
                    line.add(current.val);
                if (null != current.left)
                    queue.add(current.left);
                if (null != current.right)
                    queue.add(current.right);
            }
            result.add(line);
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root)
            return result;

        Queue<TreeNode> temp = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        temp.add(root);

        TreeNode current;
        while (!temp.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            queue.addAll(temp);
            temp.clear();
            while (!queue.isEmpty()) {
                current = queue.poll();
                line.add(current.val);
                if (null != current.left)
                    temp.add(current.left);
                if (null != current.right)
                    temp.add(current.right);
            }
            result.add(line);
        }
        return result;
    }

    public int[] levelOrder1(TreeNode root) {
        if (null == root)
            return new int[0];
        TreeNode[] queue = new TreeNode[1000];
        int s = 0, e = 0;
        int[] result = new int[1000];
        int index = 0;
        queue[e++] = root;
        while (s != e) {
            if (null != queue[s].left)
                queue[e++] = queue[s].left;
            if (null != queue[s].right)
                queue[e++] = queue[s].right;
            result[index++] = queue[s].val;
            s++;
        }

        return Arrays.copyOf(result, e);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (null == A && null == B) {
            return true;
        } else if (null == A || null == B) {
            return false;
        }

        //判断是否相等
        if (isEqualTo(A, B)) {
            return true;
        } else {
            //用子串判断
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
    }

    public boolean isEqualTo(TreeNode A, TreeNode B) {
        if (null == A && null == B) {
            return true;
        } else if (null == A) {
            return false;
        } else if (null == B) {
            return true;
        }

        if (A.val == B.val)
            return isEqualTo(A.left, B.left) && isEqualTo(A.right, B.right);
        else
            return false;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (null == root)
            return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (checkSymmetric(root.left, root.right)) return true;
        return false;
    }

    public boolean checkSymmetric(TreeNode l, TreeNode r) {
        if (null == l && null == r) return true;
        else if (null == l || null == r) return false;
        if (l.val == r.val) {
            return checkSymmetric(l.left, r.right) && checkSymmetric(l.right, r.left);
        }

        return false;
    }

    //7,1,5,3,6,4]
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int result = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
