package com.loong.algorithms.recursive;

/**
 * 递归算法
 * 
 * @author loong
 *
 */
public class HanNota {

	private int num = 1;

	public static void main(String[] args) {
		HanNota recursive = new HanNota();
		recursive.hanNota(3, 'A', 'B', 'C');
	}

	/**
	 * 
	 * 汉诺塔
	 * 
	 * 盘子从下往上为 1 ··· n 
	 * 
	 * @param n
	 *            移动几个盘子
	 * @param from
	 *            从哪个柱子挪
	 * @param to
	 *            移动到哪个柱子
	 * @param dependOn
	 *            依赖的柱子
	 * 
	 */
	public void hanNota(int n, char from, char dependOn, char to) {
		if (n == 1) {
			// 直接移动
			move(1, from, to);
		} else {
			// 将盘子 1 到 n-1 看为一体，先将盘子 1 到 n-1 通过 to 为依赖，从 from 挪动到 dependOn
			hanNota(n - 1, from, to, dependOn);

			// 挪动盘子 n 从 from 到 to
			move(n, from, to);

			// 将盘子 1 到 n-1 通过 from 从 dependOn 挪动到 to
			hanNota(n - 1, dependOn, from, to);
		}
	}

	private void move(int i, char from, char to) {
		System.out.println("第 " + num++ + " 步将盘子 " + i + " 从 " + from + " ---> " + to);
	}
}
