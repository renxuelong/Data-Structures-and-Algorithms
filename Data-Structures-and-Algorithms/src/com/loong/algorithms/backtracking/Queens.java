package com.loong.algorithms.backtracking;

/**
 * 八皇后为题 - 回溯法求解
 * 
 * @author loong
 *
 */
public class Queens {

	private static final int MAXQUETE = 8;

	/**
	 * 存放每列中皇后的位置
	 */
	private int[] rows = new int[MAXQUETE];
	private int num;

	public static void main(String[] args) {
		Queens queens = new Queens();
		queens.getCount(0);
		System.out.println("八皇后解法：" + queens.num);
	}

	/**
	 * 获取八皇后接法输了
	 * 
	 * @param n
	 *            该放第几列了
	 */
	public void getCount(int n) {
		if (n >= MAXQUETE)
			return;

		// 存放该列可以放的位置，可以放为 false
		boolean[] cols = new boolean[MAXQUETE];

		// 根据已有的皇后计算处当前列能放的位置
		for (int i = 0; i < n; i++) {
			int r = rows[i];
			cols[r] = true;

			for (int j = 0; j < MAXQUETE; j++) {
				// 当前列减已经存放的列跟当前行减已经存放的行绝对值相同，说明在对角线
				if (Math.abs(n - i) == Math.abs(j - r)) {
					cols[j] = true;
				}
			}
		}

		// 根据可以放的位置放皇后
		for (int i = 0; i < MAXQUETE; i++) {
			if (cols[i])
				continue;
			
			// 记录当前列放的位置
			rows[n] = i;
			
			// 可以放
			if (n == MAXQUETE - 1) { // 如果是最后一列
				num++;
				print();
			} else {
				// 下一列
				getCount(n + 1);
			}
		}
	}

	/**
	 * 打印
	 */
	private void print() {
		for (int i = 0; i < MAXQUETE; i++) {
			int n = rows[i];
			for (int j = 0; j < MAXQUETE; j++) {
				System.out.print(j == n ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
