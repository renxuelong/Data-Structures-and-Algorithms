package com.loong.algorithms.dynamic;

/**
 * 两个字符串的最长公共子序列 - 由动态规划法求解
 * 
 * @author loong
 *
 */
public class Lcs {

	public static void main(String[] args) {
		Lcs lcs = new Lcs();
		System.out.println("最长子序列长度：" + lcs.lcs("android", "diordna"));
	}

	public int lcs(String A, String B) {
		if (A == null || B == null || A.length() == 0 || B.length() == 0)
			return 0;

		int n = A.length();
		int m = B.length();
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();

		int[][] matrix = new int[n][m];
		String[][] s = new String[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i] == b[j]) {
					if (i > 0 && j > 0) {
						matrix[i][j] = matrix[i - 1][j - 1] + 1;
						s[i][j] = (s[i - 1][j - 1] == null ? "" : s[i - 1][j - 1]) + "" + ((Character) a[i]).toString();
					} else { // 第一行或第一列的情况
						matrix[i][j] = 1;
						s[i][j] = ((Character) a[i]).toString();
					}
				} else {
					if (i > 0 && j > 0) {
						matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
						s[i][j] = matrix[i - 1][j] > matrix[i][j - 1] ? s[i - 1][j] : s[i][j - 1];
					} else if (i > 0) { // 第一列
						matrix[i][j] = matrix[i - 1][j];
						s[i][j] = s[i - 1][j];
					} else if (j > 0) { // 第一行
						matrix[i][j] = matrix[i][j - 1];
						s[i][j] = s[i][j - 1];
					} else {
						matrix[i][j] = 0;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(s[i][j] == null ? "-" : s[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("最长的子串：" + s[n - 1][m - 1]);

		return matrix[n - 1][m - 1];
	}
}
