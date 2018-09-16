package com.loong.divide;

/**
 * 分治法 - 求解球队比赛安排
 * 
 * n 只球队( n = 2^m ,m 为正整数)，两两打比赛，完成 n-1 天打完
 * 
 * ![](http://odgw9c93i.bkt.clouddn.com//FusDxiot_rzhT7ZniXec6YI_w5Sf)
 * 
 * @author loong
 *
 */
public class SportSchedule {
	public static void main(String[] args) {
		SportSchedule schedule = new SportSchedule();
		int num = 18;
		int[][] table = new int[num][num];
		schedule.sportSchedule(num, table);
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				System.out.print(table[i][j] + (j == num - 1 ? "" : "-"));
			}
			System.out.println("");
		}
	}

	/**
	 * 分治法 - 求解球队比赛安排
	 * 
	 * 解题思路：
	 * 
	 * 1. 先从小规模入手，可以将图中的内容左右上下一分为 2 成了 4 份
	 * 
	 * 2. 图中可以看到，右上部分、左下部分 各位置的值正好是左上部分对应位置的值加上每份的长度
	 * 
	 * 3. 右下部分各部分的值正好是左上部分对应位置的值
	 * 
	 * 4. 有这个规律我们可以递归求解，当长度最小为 1 时值是 1
	 * 
	 * @param n
	 * @param table
	 */
	public void sportSchedule(int n, int[][] table) {
		if (n == 0) {
			table[0][0] = 1;
		} else {
			// 计算左上区域，递归
			int m = n / 2;
			sportSchedule(m, table);
			// 计算右上区域,其中每个位置的值都为左上角中对应的值 +m
			for (int i = 0; i < m; i++) {
				for (int j = m; j < n; j++) {
					table[i][j] = table[i][j - m] + m;
				}
			}
			// 计算左下区域
			for (int i = m; i < n; i++) {
				for (int j = 0; j < m; j++) {
					table[i][j] = table[i - m][j] + m;
				}
			}
			// 计算右下区域
			for (int i = m; i < n; i++) {
				for (int j = m; j < n; j++) {
					table[i][j] = table[i - m][j - m];
				}
			}
		}
	}
}
