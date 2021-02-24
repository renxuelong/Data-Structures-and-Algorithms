package com.loong.leetcode.editor.cn;

//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 动态规划 
// 👍 1077 👎 0


import com.sun.tools.javadoc.Start;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution322 {

    private int minNum = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // 暴力求解，每次选 取都可以从硬币中任意选择，每中可能都遍历到，选择数量水少的数量返回
        // 暴力选择返回方案为如果选择一个硬币后总量大于要求的值则返回，正好时更新最小值
        // 提交后超时
        // extracted(coins, 0, 0, amount);

        // 动态规划 -- 类似上台阶，每次可以走 num1,num2,num3... 步，求第 n 阶最少步数
        // 1. 状态定义；dp[i] 表示金额为 i 的时候所需要的最小数量
        // 2. dp[0] = 0, dp[i] = min(dp[i-num1, i-num2...])
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                int min = Integer.MAX_VALUE;
                if (i - coin >= 0 && dp[i-coin] != Integer.MAX_VALUE) {
                    min = Math.min(dp[i - coin] + 1, min);
                }
                dp[i] = Math.min(dp[i], min);
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }

    private void extracted(int[] coins, int curSum, int curNum, int target) {
        if (curSum > target || curNum > minNum) {
            return;
        }
        for (int coin : coins) {
            int curS = curSum + coin;
            int curN = curNum + 1;
            if (curS == target) {
                minNum = Math.min(minNum, curN);
            } else if (curS < target) {
                extracted(coins, curS, curN, target);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)




















