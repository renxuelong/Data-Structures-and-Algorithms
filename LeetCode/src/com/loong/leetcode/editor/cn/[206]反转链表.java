package com.loong.leetcode.editor.cn;//反转一个单链表。
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1357 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution206 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1. 将原链表放入栈，然后出栈后返回新链表

        // 2. 迭代法
//        {
//            // 每次循环都只完成一个节点反转
//            ListNode pre = head;
//            ListNode cur = head.next;
//            ListNode next = cur.next;
//            // 一定要处理原始链表头的 next 置空
//            pre.next = null;
//            while (true) {
//                cur.next = pre;
//                pre = cur;
//                if (next == null) {
//                    return cur;
//                }
//                cur = next;
//                next = next.next;
//            }
//        }
        // 3. 递归
        {
            // 递归解法，递归下一个节点后都当作当前节点之后的链表都已经成功完成了反转
            // 此时，把反转后的链表的最后一个节点也就是当前节点的 next 的 next 指向当前节点
            // 当前节点的 next 指针需要处理，由于是递归操作，当前节点的指针在上一层递归中能拿到
            // 直接将当前节点 next 处理为空，上一层递归会处理具体指向的节点
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;

            // 需要返回反转后链表的头
            return p;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
