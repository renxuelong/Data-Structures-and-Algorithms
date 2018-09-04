package com.loong.leetcode;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * @author loong
 *
 */
public class TraversalBinaryTree107 {
	public static void main(String[] args) {
		TraversalBinaryTree107 tree = new TraversalBinaryTree107();
		List<List<Integer>> list = tree.traversalBinaryTree(tree.buildTree());
		for (int i = 0; i < list.size(); i++) {
			List<Integer> l = list.get(i);
			if(l == null || l.isEmpty()) continue;
			StringBuilder builder = new StringBuilder();
			for(int j = 0;j < l.size();j++) {
				builder.append(l.get(j) + "  ");
			}
			System.out.println(builder.toString());
		}
	}

	private TreeNode buildTree() {
		TreeNode treeNode15 = new TreeNode(null, null, 15);
		TreeNode treeNode7 = new TreeNode(null, null, 7);
		TreeNode treeNode9 = new TreeNode(null, null, 9);
		TreeNode treeNode20 = new TreeNode(treeNode15, treeNode7, 20);
		TreeNode treeNode3 = new TreeNode(treeNode9, treeNode20, 3);
		return treeNode3;
	}

	public List<List<Integer>> traversalBinaryTree(TreeNode root) {
		List<List<Integer>> list = new LinkedList<>();
		if(root == null) return list;
		
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root); 
		TreeNode node;

		while (!queue.isEmpty()) {
			List<Integer> listChild = new LinkedList<>();
			int len = queue.size();
			for(int i = 0;i < len;i++) {
				node = queue.poll();
				listChild.add(node.val);
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
			list.add(0,listChild);
		}
		return list;
	}

	class TreeNode {
		TreeNode left, right;
		int val;

		TreeNode(TreeNode left, TreeNode right, int val) {
			this.left = left;
			this.right = right;
			this.val = val;
		}
	}
}
