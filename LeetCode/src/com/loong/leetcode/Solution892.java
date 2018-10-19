package com.loong.leetcode;

public class Solution892 {
	public static void main(String[] args) {
		Solution892 solution892 = new Solution892();
		System.out.println(solution892.surfaceArea(new int[][] { { 1, 2 }, { 3, 4 } }));
		System.out.println(solution892.surfaceArea(new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } }));
		System.out.println(solution892.surfaceArea(new int[][] { { 2, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 } }));

		System.out.println(solution892.surfaceAreaB(new int[][] { { 1, 2 }, { 3, 4 } }));
		System.out.println(solution892.surfaceAreaB(new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } }));
		System.out.println(solution892.surfaceAreaB(new int[][] { { 2, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 } }));
	}

	/**
	 * O(n^2)
	 * 
	 * @param grid
	 * @return
	 */
	public int surfaceAreaB(int[][] grid) {
		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int curH = grid[i][j];
				if (curH == 0)
					continue;
				sum += 2;
				// 前
				int f = curH - (i == grid.length - 1 ? 0 : grid[i + 1][j]);
				sum += (f > 0 ? f : 0);
				// 后
				int b = curH - (i == 0 ? 0 : grid[i - 1][j]);
				sum += (b > 0 ? b : 0);
				// 左
				int l = curH - (j == 0 ? 0 : grid[i][j - 1]);
				sum += (l > 0 ? l : 0);
				// 右
				int r = curH - (j == grid[i].length - 1 ? 0 : grid[i][j + 1]);
				sum += (r > 0 ? r : 0);
			}
		}
		return sum;
	}

	/**
	 * O(n^3)
	 * 
	 * @param grid
	 * @return
	 */
	public int surfaceArea(int[][] grid) {
		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				for (int m = 1; m <= grid[i][j]; m++) {
					// 底
					sum += m == 1 ? 1 : 0;
					// 顶
					sum += m == grid[i][j] ? 1 : 0;
					// 前
					if (i == grid.length - 1 || grid[i + 1][j] < m) {
						sum += 1;
					}
					// 后
					if (i == 0 || grid[i - 1][j] < m) {
						sum += 1;
					}
					// 左
					if (j == 0 || grid[i][j - 1] < m) {
						sum += 1;
					}
					// 右
					if (j == grid[i].length - 1 || grid[i][j + 1] < m) {
						sum += 1;
					}
				}
			}
		}
		return sum;
	}
}
