package com.yangweiye.springbootdemos.questions;

import java.util.ArrayList;

public class TreeQuestions {
    public static void main(String[] args) {
        TreeQuestions treeQuestions = new TreeQuestions();
        treeQuestions.buildTree(new int[]{1,2}, new int[]{1,2});
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return _buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode _buildTree(int[] preorder, int[] inorder, int s1, int e1, int s2, int e2) {
        TreeNode root = new TreeNode(preorder[s1]);
        int i = s2, c = 0;
        while (i <= e2) {
            if (preorder[s1] == inorder[i]) {
                break;
            }
            i++;
            c++;
        }
        if (c != 0)
            root.left = _buildTree(preorder, inorder, s1 + 1, s1 + c, s2, i - 1);
        if (s1 + c < e1)
            root.right = _buildTree(preorder, inorder, s1 + 1 + c, e1, i + 1, e2);
//        root.left
        return root;
    }
}
