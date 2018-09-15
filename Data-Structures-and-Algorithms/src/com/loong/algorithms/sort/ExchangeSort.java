package com.loong.algorithms.sort;

/**
 * 
 * 交换排序
 * 
 * 1. 冒泡排序
 * 
 * 2. 快速排序
 * 
 * @author loong
 *
 */
public class ExchangeSort {

	public static void main(String[] args) {
		ExchangeSort sort = new ExchangeSort();
		int[] array = new int[] { -1, -10, 3, 6, 2, 4, 5, 5, 5, 5, 5, 0, 3, 45, 23 };
		sort.quickSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 快速排序
	 * 
	 * 解题思路
	 * 
	 * 1. 选定基数，可以选择待排序数组中的第 0 位置的值或者最后一个值，通过比较数组中比基数大的值和比基数小的值来确定基数的位置
	 * 
	 * 2. 从数组中索引为 0 和索引为 len -1 的位置开始与基数比较，low 初始为 0，high 初始为 len - 1
	 * 
	 * 3. 如果 low 比基数小，low++ ,直到遇到比基数大的值，就将 high 处的值设置为 low 处的值
	 * 
	 * 4. 如果 high 比基数大，high--，直到遇到比基数小的值，就将 low 处的值设置为 high 处的值
	 * 
	 * 5. 注意，如果基数是第 0 位置值，那么要从 high 开始比较；如果基数是选择的最后一个值，就要从 low
	 * 开始比较,要保证第一次被替换的索引为基数所在的位置
	 * 
	 * 6. 直到 low 大于等于 high 时，low 的位置就时基数应该在排序完成的数组中的位置
	 * 
	 * 7. 找到基数位置后，将数组中该位置设置为基数的值，然后从基数左右两部分再进行排序
	 * 
	 * @param array
	 *            待排序数组
	 */
	public void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	/**
	 * 快速排序 - 核心思想通过迭代，找到一个基数来进行分段
	 * 
	 * @param array
	 * @param low
	 * @param high
	 */
	private void quickSort(int[] array, int low, int high) {
		if (low >= high)
			return;

		int index = getIndex(array, low, high);
		quickSort(array, 0, index - 1); // 左边排序
		quickSort(array, index + 1, high); // 右边排序
	}

	/**
	 * 找到一个基数所在的索引
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	private int getIndex(int[] array, int low, int high) {
		// 选择基数
		int temp = array[high];
		while (low < high) {
			while (low < high && array[low] <= temp) {
				low++;
			}
			array[high] = array[low];

			while (low < high && array[high] >= temp) {
				high--;
			}
			array[low] = array[high];
		}

		array[low] = temp; // 将基数放入正确位置
		return low;
	}
}
