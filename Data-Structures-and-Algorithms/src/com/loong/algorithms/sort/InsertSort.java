package com.loong.algorithms.sort;

/**
 * 插入排序
 * 
 * @author loong
 *
 */
public class InsertSort {

	public static void main(String[] args) {
		InsertSort sort = new InsertSort();
		sort.shellSort(new int[] { -1, -10, 3, 6, 2, 4, 5, 0, 3, 45, 23 });
	}

	/**
	 * 直接插入排序
	 */
	public void insetSort(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (array[j] > temp) {
					array[j + 1] = array[j];
				} else {
					break;
				}
			}
			array[j + 1] = temp;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 二分法插入排序
	 */
	public void binaryInsetSort(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int left = 0, right = i - 1, mid = 0;
			while (left <= right) {
				mid = (left + right) / 2;
				if (array[mid] > temp) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}

			for (int j = i - 1; j >= left; j--) {
				array[j + 1] = array[j];
			}

			array[left] = temp;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 希尔排序 - 不稳定排序
	 * 
	 */
	private void shellSort(int[] array) {
		if (array == null || array.length == 0)
			return;

		int d = array.length / 2;
		while (d >= 1) {
			for (int i = 0; i < array.length; i++) {
				int temp = array[i];
				int j = i - d;
				// 与组内数据进行插入排序
				while (j > 0 && array[j] > temp) { // 从小到大排序
					array[j + d] = array[j];
					j -= d;
				}
				array[j + d] = temp;
			}

			// 步长递减
			d /= 2;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
