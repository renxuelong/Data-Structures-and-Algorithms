package com.loong.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution433 {

	public static final int MAX_WEIGHT = 100;

	public static void main(String[] args) {
		Solution433 solution433 = new Solution433();
		System.out.println(solution433.minMutation("AACCGGTT", "AAACGGTA",
				new String[] { "AACCGGTA", "AACCGGTT", "AAACGGTA", "AACCGCTA" }));
	}

	/**
	 * 回溯解法
	 * 
	 * 回溯法是一种可以找出所有（或一部分）解的一般性算法，尤其适用于约束满足问题
	 * 
	 * (在解决约束满足问题时，我们逐步构造更多的候选解，并且在确定某一部分候选解不可能补全成正确解之后
	 * 
	 * 放弃继续搜索这个部分候选解本身及其可以拓展出的子候选解，转而测试其他的部分候选解)。
	 * 
	 * 1. 从第二位开始比较，通过 boolean 数组保存遍历过的基因下标
	 * 
	 * 2. 如果找到则返回距离，如果未找到返回 -1
	 * 
	 * 3.
	 * 
	 * @param start
	 * @param end
	 * @param bank
	 * @return
	 */
	public int minMutation1(String start, String end, String[] bank) {
		boolean[] isVisited = new boolean[bank.length];
		fun(start, end, bank, isVisited, 0);
		return sum;
	}

	int sum = -1;

	private void fun(String start, String end, String[] bank, boolean[] isVisited, int num) {
		for (int i = 0; i < isVisited.length; i++) {
			if (!isVisited[i] && helper(start, bank[i])) { // 找到下一个未访问的且可达的字符串
				if (end.equals(bank[i])) { // 找到了
					System.out.println(num + 1);
					sum = (sum == -1) ? (num + 1) : Math.min(sum, num + 1);
				} else {
					isVisited[i] = true;
					fun(bank[i], end, bank, isVisited, num + 1);
					isVisited[i] = false;
				}
			}
		}
	}

	/**
	 * 用于判断两个字符串是否只相差一个字符
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean helper(String from, String to) {
		int num = 0;
		for (int n = 0; n < 8; n++) {
			if (from.charAt(n) != to.charAt(n))
				num += 1;
		}
		return num == 1;
	}

	/**
	 * 使用图的最短路径求解
	 * 
	 * @param start
	 * @param end
	 * @param bank
	 * @return
	 */
	public int minMutation(String start, String end, String[] bank) {
		if (bank.length == 0)
			return -1;
		ArrayList<String> list = new ArrayList<>(Arrays.asList(bank));
		if (!list.contains(start))
			list.add(0, start);
		else {
			// 其实基因在基因库中需要先放到第一位
			int i = list.indexOf(start);
			String s = list.get(0);
			list.set(0, start);
			list.set(i, s);
		}
		int endIndex = list.indexOf(end);
		if (endIndex == -1)
			return -1;

		int[][] matrix = buildGraph(list);

		// 求顶点 0 到顶点 endIndex 的最短路径
		boolean[] isFind = new boolean[list.size()];
		int[] val = new int[list.size()];
		isFind[0] = true;

		for (int i = 0; i < list.size(); i++) {
			val[i] = matrix[0][i];
		}

		for (int i = 0; i < list.size(); i++) {
			int min = MAX_WEIGHT;
			int minIndex = 0;

			if (val[endIndex] > 0 && val[endIndex] != MAX_WEIGHT) {
				return val[endIndex];
			}

			// 找到下一个距离最小的顶点
			for (int j = 0; j < list.size(); j++) {
				if (!isFind[j] && val[j] < min && val[j] > 0) {
					min = val[j];
					minIndex = j;
				}
			}

			// 找到该顶点连接的顶点
			if (min != MAX_WEIGHT) {
				isFind[minIndex] = true;
				for (int m = 0; m < list.size(); m++) {
					// 将新连接到的顶点在距离数组中赋值
					if (!isFind[m] && val[m] == MAX_WEIGHT && matrix[minIndex][m] < MAX_WEIGHT) {
						val[m] = val[minIndex] + 1;
					}
				}
			}
			if (isFind[endIndex])
				break;
		}

		return isFind[endIndex] ? val[endIndex] : -1;
	}

	/**
	 * 构建图
	 * 
	 * @param bank
	 * @return
	 */
	private int[][] buildGraph(ArrayList<String> bank) {
		int[][] result = new int[bank.size()][bank.size()];

		for (int i = 0; i < bank.size(); i++) {
			String from = bank.get(i);
			for (int j = i; j < bank.size(); j++) {
				if (i == j) {
					result[i][j] = 0;
					continue;
				}
				String to = bank.get(j);
				int num = 0;
				for (int n = 0; n < 8; n++) {
					if (from.charAt(n) != to.charAt(n)) {
						num += 1;
						if (num >= 2)
							break;
					}
				}
				if (num >= 2) { // 不满足
					result[i][j] = MAX_WEIGHT;
					result[j][i] = MAX_WEIGHT;
				} else {
					result[i][j] = 1;
					result[j][i] = 1;
				}
			}
		}

		for (int i = 0; i < bank.size(); i++) {
			for (int j = 0; j < bank.size(); j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

		return result;
	}
}
