package com.loong.leetcode.editor.cn;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 二分查找 动态规划 
// 👍 1256 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution300 {
    public int lengthOfLIS(int[] nums) {
        // 1. 暴力递归
        // 每个位置的值都有选择与不选择，如果选择，就需要判断已经构成的数组是不是递增，如果递增，最大值加 1，如果不递增，用远来递增的继续递归
//        return solute(0, nums, new int[nums.length], 0);

        // 动态规划
        // DP 定义 f(i, j) 第 i 天，包含 i 时子序列前一个为 j 位置时最大长度, i = j 时为到 i 处最长子序列
        // f(i, j) = nums[i]>nums[j] ? f(j,j)+1 : 1
//        int[][] result = new int[nums.length][nums.length];
//        result[0][0] = 1;
//        int solute = 1;
//        for (int i = 1; i < nums.length; i++) {
//            int max = 1;
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]) {
//                    result[i][j] = result[j][j] + 1;
//                } else {
//                    result[i][j] = 1;
//                }
//                max = Math.max(max, result[i][j]);
//            }
//            result[i][i] = max;
//            solute = Math.max(max, solute);
//        }
//        return solute;
        int[] result = new int[nums.length];
        result[0] = 1;
        int solute = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, result[j] + 1);
                }
            }
            result[i] = max;
            solute = Math.max(solute, max);
        }
        return solute;
    }

    /**
     * @param index  当前下标
     * @param nums
     * @param result 递增数组
     * @param size   递增数组元素个数
     * @return
     */
    public int solute(int index, int[] nums, int result[], int size) {
        if (index >= nums.length) {
            return size;
        }
        if (size == 0 || (nums[index] > result[size - 1])) {
            // 符合条件
            result[size] = nums[index];
            return Math.max(solute(index + 1, nums, result, size + 1), solute(index + 1, nums, result, size));
        }
        return solute(index + 1, nums, result, size);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
