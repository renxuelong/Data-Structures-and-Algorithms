package com.loong.algorithms.sort;

/**
 * 归并排序
 * 
 * @author loong
 *
 */
public class MergeSort {
	public static void main(String[] args) {
		MergeSort sort = new MergeSort();
		int[] array = new int[] { -1, -10, 3, 6, 8, 45, 34, 26, 83, 90, 2, 4, 5, 5, 5, 5, 5, 0, 3, 45, 23 };
		sort.mergeSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 归并排序
	 * 
	 * @param array
	 */
	public void mergeSort(int[] array) {
		if (array == null || array.length < 2)
			return;
		mergeSort(array, 0, array.length - 1);
	}

	/**
	 * 归并排序解题思路
	 * 
	 * 1. 将数组从中间分成两份，然后左右两边分别递归这个操作，一直分到每份只有一个元素，然后分成的两份开始合并
	 * 
	 * 2. 两份合并时保证小的在前，大的在后
	 * 
	 * @param array
	 * @param left
	 * @param right
	 */
	private void mergeSort(int[] array, int left, int right) {
		if (left >= right)
			return;
		int mid = (left + right) / 2;
		mergeSort(array, left, mid);
		mergeSort(array, mid + 1, right);
		merge(array, left, mid, right);
	}

	/**
	 * 合并操作
	 * 
	 * @param array
	 * @param left
	 * @param mid
	 * @param right
	 */
	private void merge(int[] array, int left, int mid, int right) {
		int rightStart = mid + 1;
		int startIndex = left;
		int temp = left;
		int[] merge = new int[array.length];

		while (left <= mid && rightStart <= right) {
			if (array[left] < array[rightStart]) {
				merge[temp++] = array[left++];
			} else {
				merge[temp++] = array[rightStart++];
			}
		}

		while (left <= mid) {
			merge[temp++] = array[left++];
		}

		while (rightStart <= right) {
			merge[temp++] = array[rightStart++];
		}

		while (startIndex <= right) {
			array[startIndex] = merge[startIndex++];
		}
	}
}
