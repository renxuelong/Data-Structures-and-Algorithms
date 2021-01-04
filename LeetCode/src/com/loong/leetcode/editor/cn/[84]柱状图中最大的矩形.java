package com.loong.leetcode.editor.cn;

//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组 
// 👍 1024 👎 0


import org.omg.CORBA.MARSHAL;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution84 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) {
            return 0;
        } else if (heights.length == 1) {
            return heights[0];
        }

        int max = 0;
        // 1. 暴力，每根柱子都跟后面的所有柱子求一次最大面积, 复杂度 O(n^2)
        {
            for (int i = 0; i < heights.length; i++) {
                int mi = heights[i];
                int min = mi;
                for (int j = i + 1; j < heights.length; j++) {
                    int mj = heights[j];
                    min = Math.min(mj, min);
                    max = Math.max(min * (j - i + 1), max);
                }
                max = Math.max(max, mi);
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
