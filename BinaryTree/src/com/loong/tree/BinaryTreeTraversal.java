package com.loong.tree;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTreeTraversal {
	public static void main(String[] args) {
		BinaryTreeTraversal tool = new BinaryTreeTraversal();
		TreeNode root = tool.buildBinaryTree();
		System.out.println("二叉树高度为：" + tool.getBinaryTreeHeight(root));
		System.out.println("二叉树节点数：" + tool.getBinaryTreeCount(root));
		tool.nonRecOrder(root);
	}

	/**
	 * 前序遍历
	 * 
	 * @param root
	 */
	private void preTraversal(TreeNode root) {
		if (root == null)
			return;
		System.out.println(root.value);
		preTraversal(root.left);
		preTraversal(root.right);
	}

	/**
	 * 中序遍历
	 * 
	 * @param root
	 */
	private void midTraversal(TreeNode root) {
		if (root == null)
			return;
		preTraversal(root.left);
		System.out.println(root.value);
		preTraversal(root.right);
	}

	/**
	 * 后序遍历
	 * 
	 * @param root
	 */
	private void PostOrderTraversal(TreeNode root) {
		if (root == null)
			return;
		preTraversal(root.left);
		preTraversal(root.right);
		System.out.println(root.value);
	}

	/**
	 * 层次遍历
	 * 
	 * @param root
	 */
	private void hierarchicalTraversal(TreeNode root) {
		if (root == null)
			return;

		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				TreeNode node = queue.poll();
				System.out.println(node.value);
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
		}
	}

	/**
	 * 前序遍历 - 非迭代形式
	 */
	public void nonRecOrder(TreeNode node) {
		if (node == null)
			return;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			TreeNode n = stack.pop();
			System.out.println(n.value);
			if (n.right != null)
				stack.push(n.right);
			if (n.left != null)
				stack.push(n.left);
		}
	}

	/**
	 * 二叉树高度
	 */
	private int getBinaryTreeHeight(TreeNode node) {
		if (node == null)
			return 0;
		int leftH = getBinaryTreeHeight(node.left);
		int rightH = getBinaryTreeHeight(node.right);
		return (leftH > rightH ? leftH : rightH) + 1;
	}

	/**
	 * 二叉树节点数
	 */
	private int getBinaryTreeCount(TreeNode node) {
		if (node == null)
			return 0;
		return getBinaryTreeCount(node.left) + getBinaryTreeCount(node.right) + 1;
	}

	/**
	 * 构建二叉树 1 2 3 4 5 6 7
	 */
	private TreeNode buildBinaryTree() {
		TreeNode node7 = new TreeNode(7, null, null);
		TreeNode node6 = new TreeNode(6, null, null);
		TreeNode node5 = new TreeNode(5, null, null);
		TreeNode node4 = new TreeNode(4, null, null);
		TreeNode node3 = new TreeNode(3, node6, node7);
		TreeNode node2 = new TreeNode(2, node4, node5);
		TreeNode node1 = new TreeNode(1, node2, node3);
		return node1;
	}

	class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value, TreeNode left, TreeNode right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}
