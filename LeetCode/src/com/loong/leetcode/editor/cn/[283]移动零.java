package com.loong.leetcode.editor.cn;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 866 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution283 {
    public void moveZeroes(int[] nums) {
        // N^2 解法：遍历数组，遇到 0 则将该位置之后的所有元素向前移动一位，将最后一位设置为 0
        // O(n) 解法：快慢指针，遇到 0 时慢指针停止移动，快指针往前移动，快指针遇到非 0 时与慢指针处元素交换，直到快指针到达数组末尾
        if (nums == null || nums.length < 2) {
            return;
        }
        int i, j;
        i = j = 0;
        while (j < nums.length) {
            // TODO 如何让 i 更优雅的移动到 0
            // 虽然时双重循环嵌套，但是 i 最多移动 N 次，整体复杂度还是 O(n)
//            {
//                while (nums[i] != 0 && i < j) {
//                    i++;
//                }
//                if (nums[j] != 0 && i != j) {
//                    nums[i] = nums[j];
//                    nums[j] = 0;
//                }
//                j++;
//            }

            // 如何让 i 更优雅的移动到 0, 只要 j 不为 0 且 i 不等于 j，i 也要 ++，保证 i 能移动到 0 位置再进行交换
            // 为什么不会出现 i 指向非 0 时也发生交换的情况，因为只要不遇到 0，i 和 j 会同步进行增加，只有遇到 0 时才会出现 i,j 不相等
            if (nums[j] != 0) {
                if (i != j) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i++;
            }
            j++;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
