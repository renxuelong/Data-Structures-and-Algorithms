package com.loong.tree;

import com.loong.tree.BinaryTreeTraversal.TreeNode;

public class SearchBinaryTree {

	private TreeNode root;

	public static void main(String[] args) {
		SearchBinaryTree tree = new SearchBinaryTree();

		tree.put(410);
		tree.put(20);
		tree.put(140);
		tree.put(450);
		tree.put(400);
		tree.put(340);
		tree.put(140);
		tree.midTraversal(tree.root);
		TreeNode node = tree.getTreeNodeByValue(40);
		System.out.println(node == null ? "null" : node.value + "-----");
		tree.delete(410);
		tree.midTraversal(tree.root);
	}

	/**
	 * 查找二叉树的删除
	 */
	public TreeNode delete(int data) {
		TreeNode node = getTreeNodeByValue(data);
		if (node == null)
			return node;
		TreeNode parent = node.parent;
		if (node.left == null && node.right == null) { // 无左无右
			if (parent == null) {
				root = null;
			} else {
				if (node == parent.left) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
		} else if (node.left == null) { // 有右无左
			if (parent == null) {
				root = node.right;
			} else {
				if (node == parent.left) {
					parent.left = node.right;
				} else {
					parent.right = node.right;
				}
				node.right.parent = parent;
			}
		} else if (node.right == null) { // 无左有右
			if (parent == null) {
				root = node.left;
			} else {
				if (node == parent.left) {
					parent.left = node.left;
				} else {
					parent.right = node.left;
				}
				node.left.parent = parent;
			}
		} else { // 有左有右
			TreeNode next = findNextNode(node);
			TreeNode nextP = next.parent;
			if (next == nextP.left) {
				nextP.left = null;
			} else {
				nextP.right = null;
			}
			int value = node.value;
			node.value = next.value;
			next.value = value;
			node = next;
		}

		node.parent = null;
		node.left = null;
		node.right = null;
		return node;
	}

	/**
	 * 寻找后继节点
	 * 
	 * @param node
	 * @return
	 */
	private TreeNode findNextNode(TreeNode node) {
		if (node == null)
			return null;
		TreeNode next;
		if (node.right != null) {
			next = node.right;
			while (next.left != null) {
				next = next.left;
			}
		} else {
			next = node.parent;
			if (node.parent != null) {
				while (next == next.parent.left) {
					next = next.parent;
				}
			} else {
				next = null;
			}
		}
		return next;
	}

	public TreeNode getTreeNodeByValue(int data) {
		return getTreeNodeByValue(root, data);
	}

	/**
	 * 查找二叉树的查找
	 */
	public TreeNode getTreeNodeByValue(TreeNode parent, int data) {
		if (parent == null)
			return null;
		if (parent.value == data) {
			return parent;
		} else if (data < parent.value) {
			return getTreeNodeByValue(parent.left, data);
		} else {
			return getTreeNodeByValue(parent.right, data);
		}
	}

	/**
	 * 查找二叉树的构建
	 * 
	 * 左孩子比自己小，右孩子比自己大
	 * 
	 * @return
	 */
	public TreeNode put(int data) {
		if (root == null) {
			root = new TreeNode(data, null, null);
		} else {
			// TreeNode parent = root;
			// while(parent != null) {
			// if(parent.value > data) {
			// if(parent.left == null) {
			// parent.left = node;
			// node.parent = parent;
			// break;
			// } else {
			// parent = parent.left;
			// continue;
			// }
			// } else if(parent.value < data){
			// if(parent.right == null) {
			// parent.right = node;
			// node.parent = parent;
			// break;
			// } else {
			// parent = parent.right;
			// continue;
			// }
			// } else {
			// return root;
			// }
			// }

			TreeNode parent = root;
			TreeNode node = parent;
			while (node != null) {
				parent = node;
				if (data < node.value) {
					node = parent.left;
				} else if (data > node.value) {
					node = parent.right;
				} else {
					return root;
				}
			}
			node = new TreeNode(data, null, null);
			node.parent = parent;
			if (data < parent.value) {
				parent.left = node;
			} else {
				parent.right = node;
			}
		}
		return root;
	}

	/**
	 * 中序遍历
	 * 
	 * @param root
	 */
	private void midTraversal(TreeNode root) {
		if (root == null)
			return;
		midTraversal(root.left);
		System.out.println(root.value);
		midTraversal(root.right);
	}

	class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;
		TreeNode parent;

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public TreeNode getParent() {
			return parent;
		}

		public TreeNode(int value, TreeNode left, TreeNode right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}
