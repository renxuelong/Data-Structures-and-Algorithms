package com.loong.leetcode.editor.cn;

//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 798 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution25 {
//
//    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2, node1);
//        ListNode node3 = new ListNode(3, node2);
//        ListNode node4 = new ListNode(4, node3);
//        ListNode node5 = new ListNode(5, node4);
//
//        Solution solution = new Solution();
//        ListNode node = solution.reverseKGroup(node5, 2);
//        while(node != null) {
//            System.out.println(" --- " + node.val);
//            node = node.next;
//        }
//    }


    public ListNode reverseKGroup(ListNode head, int k) {
        return reverse(head, k, null);
    }

    private ListNode reverse(ListNode head, int k, ListNode pre) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode[] result = new ListNode[3];
        reverseK(head, k, result);
        if (pre != null) {
            pre.next = result[0];
        }
        if (result[2] != null) {
            reverse(result[2], k, result[1]);
        }
        return result[0];
    }

    /**
     * 反转 k 个节点
     *
     * @param head
     * @param k    return 0 位置为反转后的头，1 位置为反转后的尾，2 为下一段的头
     */
    public void reverseK(ListNode head, int k, ListNode[] result) {
        if (head == null || k <= 1) {
            return;
        }
        boolean enough = listLenEnough(head, k);
        if (enough) {
            ListNode pre = null;
            ListNode curr = head;
            ListNode next = head.next;
            for (int i = 0; i < k; i++) {
                curr.next = pre;

                pre = curr;
                curr = next;
                if (next != null) {
                    next = next.next;
                }
            }

            result[0] = pre;
            result[1] = head;
            result[2] = curr;
        } else {
            result[0] = head;
            ListNode next = head.next;

            if (next != null) {
                while (next.next != null) {
                    next = next.next;
                }
                result[1] = next;
            } else {
                result[1] = head;
            }
            result[2] = null;
        }
    }

    /**
     * 计算剩余链表是否还满足一次反转条件
     *
     * @param head
     * @return
     */
    private boolean listLenEnough(ListNode head, int k) {
        for (int i = 0; i < k; i++) {
            if (head == null) {
                return false;
            } else {
                head = head.next;
            }
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
