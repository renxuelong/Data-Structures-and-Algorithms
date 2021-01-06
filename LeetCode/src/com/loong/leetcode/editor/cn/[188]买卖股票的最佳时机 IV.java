package com.loong.leetcode.editor.cn;

//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 415 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) {
            return 0;
        }
        // 解法 1，递归，超时
        // return solute(k, prices,0, 0, false);

        // 解法 2，动态规划，三阶

        // k 买的次数，最多占天数一半
        k = Math.min(k, prices.length / 2);

        int[][][] result = new int[prices.length][k+1][2];
        for (int i = 0; i <= k; i++) {
            result[0][i][0] = 0;
            result[0][i][1] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            result[i][0][0] = 0;
            result[i][0][1] = -prices[i];
            for (int j = 1; j <= k; j++) {
                // 不持有，前一天不持有/前一天持有卖出，卖出计算次数
                result[i][j][0] = Math.max(result[i - 1][j][0], result[i - 1][j][1] + prices[i]);
                // 持有，前一天持有/前一天不持有买入，买入不计算次数
                result[i][j][1] = Math.max(result[i - 1][j][1], result[i - 1][j - 1][0] - prices[i]);
            }
        }

        int max = 0;
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, result[prices.length - 1][i][0]);
        }
        return max;
    }

    /**
     * @param k       剩余操作次数
     * @param prices  价格数组
     * @param index   当前天
     * @param profits 盈利
     * @return
     */
    public int solute(int k, int[] prices, int index, int profits, boolean isHold) {
        if (k == 0) {
            return profits;
        } else if (index == prices.length - 1) {
            // 最后一天只能卖
            if (isHold) {
                profits += prices[index];
            }
            return profits;
        } else {
            // 不动
            int max1 = solute(k, prices, index + 1, profits, isHold);
            if (!isHold) {
                // 非持有需要买
                profits -= prices[index];
                max1 = Math.max(max1, solute(k, prices, index + 1, profits, true));
            } else {
                // 持有需要卖
                // 卖出时计算盈利
                profits += prices[index];
                k -= 1;
                max1 = Math.max(max1, solute(k, prices, index + 1, profits, false));
            }
            return max1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
