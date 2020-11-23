package com.loong.leetcode.editor.cn;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1345 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution70 {

    public int climbStairs(int n) {
        // f(n) = f(n-1) + f(n-2) 斐波那契数列
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        // 1. 递归
        {
            // 问题，递归太多次，时间复杂度太高，会超时
//            return climbStairs(n - 1) + climbStairs(n - 2);
            // 优化递归：解决方法为使用一个数组记录，如果递归到计算过的值，直接返回即可
//            {
//                int[] array = new int[n + 1];
//                return deal(n, array);
//            }
        }
        // 2. 非递归，用两个值记录前两个位置的值，直接计算到 n
        {
            int temp = 1, pre = 2;
            int cur;
            for (int i = 3; i <= n; i++) {
                cur = temp + pre;
                temp = pre;
                pre = cur;
            }
            return pre;
        }
    }

    public int deal(int n, int[] array) {
        if (array[n] != 0) {
            return array[n];
        }
        if (n == 1) {
            return array[1] = 1;
        } else if (n == 2) {
            return array[2] = 2;
        } else {
            return array[n] = deal(n - 1, array) + deal(n - 2, array);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
