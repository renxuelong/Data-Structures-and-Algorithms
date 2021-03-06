package com.loong.leetcode.editor.cn;

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 链表 
// 👍 747 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1. 迭代
//        {
//            ListNode result = head.next;
//
//            ListNode pre = null;
//            ListNode cur1 = head;
//            ListNode cur2 = head.next;
//            ListNode next = cur2.next;
//
//            while (cur1 != null && cur2 != null) {
//                if (pre != null) {
//                    pre.next = cur2;
//                }
//                cur2.next = cur1;
//                cur1.next = next;
//
//                pre = cur1;
//                cur1 = next;
//                if (cur1 != null) {
//                    cur2 = cur1.next;
//                }
//                if (cur2 != null) {
//                    next = cur2.next;
//                }
//            }
//            return result;
//        }
        // 递归
        {
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
    }

    public class ListNode {
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
