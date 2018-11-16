package com.loong.leetcode;

public class Solution114 {
	
	public static void main(String[] args) {
		Solution114 solution = new Solution114();
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		
		node1.left = node2;
		node2.left = node3;
		node2.right = node4;
		node1.right = node5;
		node5.right = node6;
		
		solution.flatten(node1);
		
		while(node1 != null) {
			System.out.println(node1.val);
			node1 = node1.right;
		}
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public void flatten(TreeNode root) {
		if (root == null)
			return;
		TreeNode right = root;
		while (right.right != null) {
			right = right.right;
		}

		TreeNode head = root;
		while (head != null) {
			if (head.left != null) {
				right.right = head.left;
				head.left = null;
				right = right.right;
				while (right.right != null) {
					right = right.right;
				}

				head = head.right;
			} else {
				head = head.right;
			}
		}
	}
}
