package com.loong.leetcode.editor.cn;

//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 712 👎 0


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution51 {
    int count = 0;

    public List<List<String>> solveNQueens(int n) {
        count = 0;
        // 解法数量
        dfs(n, 0, 0, 0, 0);
        System.out.println("解法数量" + count);
        return null;
    }

    public void dfs(int num, int h, int l, int p, int n) {
        // 递归终止，如果行已经结束，说明当前方案合理
        if (h >= num) {
            count++;
            return;
        }

        // 1. 通过列撇捺得到可以占位的bit
        // (l | p | n) 得到的是根据列撇捺已经占了的 bit 位，取反得到的结果为 1 的比特位都是当前行皇后可以占的
        // 取反后，有效位前面的位数都为 1，所以需要通过跟 00001111 来做与操作，消除前面的无效 1
        int bits = (~(l | p | n)) & ((1 << num) - 1);

        // 2. 遍历空位，如果有不可理的摆放，这里会得到 0，会返回当前摆放方案，不会继续递归
        while (bits != 0) {
            // 得到最后一位 1
            int temp = bits & -bits;
            // 向下一行继续
            // 行为当前行 +1
            // 列：为当前皇后占的位置或上其他皇后已经占的位置，得到的比特位为 1 的值表示已经占了
            // 撇：撇为其他皇后撇已经影响了的位，或上 当前皇后占了的位置，到一下行影响其他皇后时需要左移 1 位，因为斜角方向上的影响，没下一行就更偏一格
            // 捺：同撇一样，只不过在下一行影响时时右移 1 位
            dfs(num, h + 1, l | temp, (temp | p) << 1, (temp | n) >> 1);
            // 消除最后一位 1
            bits &= (bits - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
