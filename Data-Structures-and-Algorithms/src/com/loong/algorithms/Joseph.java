package com.loong.algorithms;

/**
 * 约瑟夫杀人法 - 约瑟夫环问题
 * 
 * @author loong
 *
 */
public class Joseph {

	public static void main(String[] args) {
		Joseph joseph = new Joseph();
		joseph.joseph(20, 5);
	}

	public void joseph(int sum, int num) {
		Node head = new Node(1);
		Node cur = head;

		for (int i = 2; i <= sum; i++) {
			cur = (cur.next = new Node(i));
			cur.next = head;
		}

		// 开始
		while (cur != cur.next) { // 直到最后一个人
			for (int i = 1; i < num; i++) {
				cur = cur.next;
			}
			System.out.println(cur.next.val + " 被干掉");
			cur.next = cur.next.next;
		}

		System.out.println("幸运儿是：" + cur.val);
	}

	class Node {
		int val;
		Node next;

		public Node(int val) {
			this.val = val;
		}
	}
}
