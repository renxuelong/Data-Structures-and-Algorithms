package com.loong.leetcode.editor.cn;

//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 884 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution152 {
    public int maxProduct(int[] nums) {
        if (nums == null) {
            return Integer.MIN_VALUE;
        }

        // 状态定义  dp[i， j] 表示，到第 i 个数字时包含该数字的最大值和最小值
        // 状态转移方程
        // 当前值 >=0, max = max(dp[i-1, max] * nums[i], nums[i]) min = min(dp[i-1, min] * nums[i], nums[i])
        // 当前值 <0，max = max[dp[i-1, min], nums[i]) min = min(dp[i-1, max] * nums[i], nums[i])

        int result;
        int[][] temp = new int[nums.length][2];
        result = temp[0][0] = temp[0][1] = nums[0];
        for (int i = 1; i < temp.length; i++) {
            if (nums[i] >= 0) {
                temp[i][0] = Math.min(temp[i - 1][0] * nums[i], nums[i]);
                temp[i][1] = Math.max(temp[i - 1][1] * nums[i], nums[i]);
            } else {
                temp[i][0] = Math.min(temp[i - 1][1] * nums[i], nums[i]);
                temp[i][1] = Math.max(temp[i - 1][0] * nums[i], nums[i]);
            }
            result = Math.max(result, temp[i][1]);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
