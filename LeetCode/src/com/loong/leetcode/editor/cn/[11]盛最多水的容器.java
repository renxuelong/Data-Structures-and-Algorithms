package com.loong.leetcode.editor.cn;

//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 数组 双指针 
// 👍 1995 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution11 {
    public int maxArea(int[] nums) {
        // 1. 暴力求解，两两组合找到最大值返回

        // 2. 双指针左右夹逼: 计算两个指针指向柱子组合水容量，与最大值比较取大的，矮的柱子移动贴近高柱子
        // 问题：会不会出现低柱子与其他柱子组合更高情况？-> 不会，因为计算水量是以低柱子高度为准，此时是宽度最大的情况
        if (nums == null || nums.length == 1) {
            return 0;
        }
        int max = 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int height = Math.min(nums[i], nums[j]);
            max = Math.max(max, (j - i) * height);
            if (nums[i] > nums[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
