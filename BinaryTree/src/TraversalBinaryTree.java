import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TraversalBinaryTree {
	public static void main(String[] args) {
		TraversalBinaryTree tree = new TraversalBinaryTree();
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
		List<List<Integer>> list = new ArrayList<>();
		if(root == null) return list;
		
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		LinkedList<TreeNode> queueChild = new LinkedList<>();
		TreeNode node;

		while (!queue.isEmpty()) {
			List<Integer> listChild = new ArrayList<>();
			int len = queue.size();
			for(int i = 0;i < len;i++) {
				node = queue.poll();
				if(node == null) continue;
				listChild.add(node.val);
				if (node.left != null)
					queueChild.add(node.left);
				if (node.right != null)
					queueChild.add(node.right);
			}
			queue.addAll(queueChild);
			queueChild.clear();
			
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
