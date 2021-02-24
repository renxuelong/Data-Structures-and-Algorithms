package com.loong.leetcode.editor.cn;

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1418 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution72 {
    public int minDistance(String word1, String word2) {
        // dp【i][j] 表示 word1 前 i 个字符和 word2 前 j 个字符匹配时所需要的最少步数, 注意前 i 个不是第 i 个
        // dp[i, j] = if (word1[i] == word2[j]) dp[i-1, j-1]
        // else min(add, del, replace)
        // word1 删除一个字符，1 + dp[i-1, j]; 解释：word1 前 i-1 个字符到word 2 前 j 个字符的距离为 dp[i-1, j], 计算 word1 的 前 i 个字符时，相当于多增加了一个，此时使用一次删除操作即可
        // word1 1 + dp[i, j-1]；解释，word1 前 i 个字符到 word2 前 j-1 个字符的距离为 dp[i, j-1], 计算 word2 再增加一个字符时，word1 再增加一个字符即可匹配，此时使用的是一次增加操作
        // word1 替换 i 为 word2 j 处的字符，1 + dp[i-1, j-1]

        // 初始值 dp[0, j] = j; dp[i, 0] = i

        int n = word1.length();
        int m = word2.length();

        int[][] solute = new int[n+1][m+1];
        for (int i = 1; i <= word1.length(); i++) {
            solute[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            solute[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    solute[i][j] = solute[i - 1][j - 1];
                } else {
                    int n1 = solute[i - 1][j];
                    int n2 = solute[i][j - 1];
                    int n3 = solute[i - 1][j - 1];
                    int min = Math.min(n1, n2);
                    min = Math.min(min, n3);
                    solute[i][j] = 1 + min;
                }
            }
        }
        return solute[n][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
