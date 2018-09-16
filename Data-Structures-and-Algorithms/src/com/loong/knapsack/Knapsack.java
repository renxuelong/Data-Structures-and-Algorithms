package com.loong.knapsack;

/**
 * 背包问题 - 贪心算法
 * 
 * 所谓贪心算法是指，在对问题求解时，总是做出在当前看来是最好的选择。
 * 
 * 也就是说，不从整体最优上加以考虑，他所做出的仅是在某种意义上的局部最优解
 * 
 * 所以在背包问题中，每次拿取的物品为当前性价比最高的，可能出现先拿了体积且性价比大的但是背包装不满
 * 
 * 而放弃后面两个体积小性价比小但两者加起来价值要超过前面那个性价比大的情况
 * 
 * 要想获取整体最优解，可以使用动态规划分解决这个问题
 * 
 * @author loong
 *
 */
public class Knapsack {

	public static int[] WEIGHTS = { 20, 5, 3, 6, 9, 10, 43, 35, 27, 48 };
	public static int[] VALUES = { 10, 20, 15, 19, 24, 32, 47, 70, 20, 90 };
	public static int MAX_WEIGHT = 150;

	public static void main(String[] args) {
		Knapsack knapsack = new Knapsack();
		knapsack.knapsack();
	}

	public void knapsack() {
		// 存放性价比的数组
		double[] worth = new double[WEIGHTS.length];
		for (int i = 0; i < worth.length; i++) {
			worth[i] = (double) WEIGHTS[i] / VALUES[i];
		}

		// 对性价比数组排序，并记录 WEIGHTS 中按性价比排序时的索引顺序
		int[] index = new int[worth.length];
		for (int i = 0; i < index.length; i++) {
			index[i] = i;
		}

		for (int i = 0; i < worth.length - 1; i++) {
			for (int j = i; j < worth.length - 1; j++) {
				if (worth[j] < worth[j + 1]) {
					double temp = worth[j];
					worth[j] = worth[j + 1];
					worth[j + 1] = temp;

					// 下标数组同时排序
					int in = index[j];
					index[j] = index[j + 1];
					index[j + 1] = in;
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < worth.length; i++) {
			if (sum + VALUES[i] < MAX_WEIGHT) {
				sum += VALUES[i];
				System.out.println("选择物品" + WEIGHTS[i] + "---" + VALUES[i]);
			}
		}

		System.out.println(sum);
	}
}
