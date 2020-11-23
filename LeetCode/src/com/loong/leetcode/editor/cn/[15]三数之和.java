package com.loong.leetcode.editor.cn;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针 
// 👍 2764 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        // 1. 暴力求解，三重 for 循环
//        {
//        for (int i = 0; i < nums.length - 2; i++) {
//            for (int j = 1; j < nums.length - 1; j++) {
//                for (int w = 2; w < nums.length; w++) {
//                    if (nums[i] + nums[j] + nums[w] == 0) {
//                        List<Integer> deal = new ArrayList<>();
//                        deal.add(nums[i]);
//                        deal.add(nums[j]);
//                        deal.add(nums[w]);
//                        // TODO 去重比较难
//                        result.add(deal);
//                    }
//                }
//            }
//        }
//        }
        // 2. 双重循环，第二层循环通过散列表找到目标值，去重，引申 -> 两数相加


        // 3. 三指针法 O(n^2)
        // 先排序 nlog(n)
        // 遍历数组，遍历时：第一个指针指向当前值右侧，第二个指针指向最右侧，两个指针左右夹逼
        // 遍历时及左右夹逼是跳过相同元素，如果遍历到的值 >0 直接跳过
        Arrays.sort(nums);
        for (int temp = 0; temp < nums.length - 2; temp++) {
            // 跳过 temp 大于 0 情况
            if (nums[temp] > 0) {
                return result;
            }
            // 跳过重复
            if (temp > 0 && nums[temp] == nums[temp - 1]) {
                continue;
            }
            int i = temp + 1, j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[temp];
                if (sum > 0) {
                    // 跳过重复，耗时较久
//                    do {
//                    } while (i < j && nums[j] == nums[j + 1]);
                    j--;
                } else if (sum < 0) {
                    // 跳过重复，耗时较久
//                    do {
//                    } while (i < j && nums[i] == nums[i - 1]);
                    i++;
                } else {
                    // 得到想要的组合时，i，j 都需要移动
                    List<Integer> deal = new ArrayList<>();
                    deal.add(nums[temp]);
                    deal.add(nums[i]);
                    deal.add(nums[j]);
                    result.add(deal);
                    do {
                        i++;
                    } while (i < j && nums[i] == nums[i - 1]);
                    do {
                        j--;
                    } while (i < j && nums[j] == nums[j + 1]);
                }
            }
        }
        return result;
    }

//    List<List<Integer>> lists = new ArrayList<>();
//    //排序
//        Arrays.sort(nums);
//    //双指针
//    int len = nums.length;
//        for(int i = 0;i < len;++i) {
//        if(nums[i] > 0) return lists;
//
//        if(i > 0 && nums[i] == nums[i-1]) continue;
//
//        int curr = nums[i];
//        int L = i+1, R = len-1;
//        while (L < R) {
//            int tmp = curr + nums[L] + nums[R];
//            if(tmp == 0) {
//                List<Integer> list = new ArrayList<>();
//                list.add(curr);
//                list.add(nums[L]);
//                list.add(nums[R]);
//                lists.add(list);
//                while(L < R && nums[L+1] == nums[L]) ++L;
//                while (L < R && nums[R-1] == nums[R]) --R;
//                ++L;
//                --R;
//            } else if(tmp < 0) {
//                ++L;
//            } else {
//                --R;
//            }
//        }
//    }
//        return lists;
}
//leetcode submit region end(Prohibit modification and deletion)
