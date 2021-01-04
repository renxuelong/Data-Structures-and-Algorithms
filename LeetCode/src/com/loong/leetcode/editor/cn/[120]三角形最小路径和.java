package com.loong.leetcode.editor.cn;

//给定一个三角形 triangle ，找出自顶向下的最小路径和。
//
// 每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
// 
//
// 示例 2： 
//
// 
//输入：triangle = [[-10]]
//输出：-10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= triangle.length <= 200 
// triangle[0].length == 1 
// triangle[i].length == triangle[i - 1].length + 1 
// -104 <= triangle[i][j] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？ 
// 
// Related Topics 数组 动态规划 
// 👍 667 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        // 解法三，从下往上递推解法
        if (triangle == null) {
            return Integer.MAX_VALUE;
        }

        int[] cache = new int[triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = triangle.get(triangle.size() - 1).get(i);
        }

        solute(cache, triangle.size() - 2, triangle);
        return cache[0];
    }

    public void solute(int[] cache, int h, List<List<Integer>> triangle) {
        if (h < 0) {
            return;
        }
        for (int i = 0; i < triangle.get(h).size(); i++) {
            cache[i] = Math.min(cache[i], cache[i + 1]) + triangle.get(h).get(i);
        }
        solute(cache, h - 1, triangle);
    }

    /**
     * 递归 + 动态规划 一般解
     * @param triangle
     * @return
     */
    public int minimumTotalNormal(List<List<Integer>> triangle) {
        int result = Integer.MAX_VALUE;
        if (triangle == null) {
            return result;
        }

        List<List<Integer>> temp = new ArrayList<>(triangle.size());
        for (int i = 0; i < triangle.size(); i++) {
            temp.add(i, new ArrayList<>(triangle.size()));
            for (int i1 = 0; i1 < triangle.get(i).size(); i1++) {
                temp.get(i).add(i1, Integer.MAX_VALUE);
            }
        }

        // 解法一，递归+记忆化，题解为最后一行中每个位置的解法最小值，可以从最后一行递推向前推，保存每个位置的最小值，最后得出解
//        for (int i = 0; i < triangle.size(); i++) {
//            result = Math.min(solute(triangle.size() - 1, i, triangle, temp), result);
//        }

        // 解法二，动态规划
        // 1. 递归 + 记忆化 -> 递推
        // 2. 状态定义
        // 3. 状态转移方程
        // 4. 最优子结构

        // 1. 递归解法解决后，可以考虑动态规划解法可以通过上一行的解法，得到下一行中每个位置的最小路径
        // 2. 状态定义：f(h, l)
        // 3. 状态转移方程：f(h, l) = Math.min(f(h-1, l), f(h-1, l-1))
        // 4. 最优子结构，递推结束后，所有位置的最优解也可以计算出

        // 逐行递推
        temp.get(0).set(0, triangle.get(0).get(0));
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 行起始，行结束，普通
                if (j == 0) {
                    temp.get(i).set(j, temp.get(i - 1).get(j) + triangle.get(i).get(j));
                } else if (j == triangle.get(i).size() - 1) {
                    temp.get(i).set(j, temp.get(i - 1).get(j - 1) + triangle.get(i).get(j));
                } else {
                    int i1 = temp.get(i - 1).get(j);
                    int i2 = temp.get(i - 1).get(j - 1);
                    temp.get(i).set(j, Math.min(i1, i2) + triangle.get(i).get(j));
                }
            }
        }

        for (int i = 0; i < triangle.size(); i++) {
            result = Math.min(temp.get(triangle.size() - 1).get(i), result);
        }

        return result;
    }

    /**
     * 递归
     *
     * @param h 行
     * @param l 列
     * @return 当前位置最小值
     */
    public int solute(int h, int l, List<List<Integer>> triangle, List<List<Integer>> temp) {
        if (temp.get(h).get(l) != Integer.MAX_VALUE) {
            return temp.get(h).get(l);
        }

        // 递归终止
        if (h == 0 && l == 0) {
            return triangle.get(0).get(0);
        }

        // 行起始，行结束，普通
        if (l == 0) {
            int r = solute(h - 1, l, triangle, temp) + triangle.get(h).get(l);
            temp.get(h).set(l, r);
            return r;
        } else if (h == l) {
            int r = solute(h - 1, l - 1, triangle, temp) + triangle.get(h).get(l);
            temp.get(h).set(l, r);
            return r;
        } else {
            int r = Math.min(solute(h - 1, l, triangle, temp), solute(h - 1, l - 1, triangle, temp)) + +triangle.get(h).get(l);
            temp.get(h).set(l, r);
            return r;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
