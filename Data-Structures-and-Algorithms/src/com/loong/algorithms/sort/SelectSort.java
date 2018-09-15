package com.loong.algorithms.sort;

/**
 * 选择排序算法
 * 
 * @author loong
 *
 */
public class SelectSort {

	public static void main(String[] args) {
		SelectSort sort = new SelectSort();
		sort.heapSort(new int[] { 3, 6, 2, 4, 5, 0, 3, 45, 23, 1, 48, 98, 36, 74, 3, 6, 7, 8 });
	}

	/**
	 * 简单选择排序
	 * 
	 * 对待排序数组第一次遍历，找出最小的数字在数组中的位置，然后将该位置的数字与第一个位置的数字交换
	 * 
	 * 第二遍从第二个位置开始找，再找到最小的，然后与第二个位置的数字交换
	 * 
	 * 直到找到最后一位
	 * 
	 * @param array
	 */
	public void simpleSelectSort(int[] array) {
		if (array == null || array.length == 0)
			return;
		int min, minIndex;
		for (int i = 0; i < array.length; i++) {
			min = array[i];
			minIndex = i;
			for (int j = i; j < array.length; j++) {
				if (array[j] < min) {
					min = array[j];
					minIndex = j;
				}
			}
			array[minIndex] = array[i];
			array[i] = min;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 堆排序
	 * 
	 * 一个二叉树，如果根节点比左右子节点都大，称作大堆
	 * 
	 * 将数组看作存储大堆的数据结构，并看作一个完全二叉树
	 * 
	 * 由二叉树性质得， i 位置的元素的左节点索引为 left = 2i + 1，右节点索引为 right = 2i + 2
	 * 
	 * 解题思路：
	 * 
	 * 1. 构造二叉树堆，在构造时只需要遍历数组的一半即可，因为由上面的公式可以看出，遍历一半时也能遍历到另一半的节点
	 * 
	 * 2. 遍历到一个元素时，需要跟左右节点都进行比较，选出三者中最大的交换到该节点
	 * 
	 * 3. 遍历到一个元素时，如果需要交换，说明该元素至少小于左右节点中的一个，与子节点交换后，还可能小于子节点的子节点，所以需要继续向下进行比较和交换
	 * 
	 * 4. 遍历完成后，构造成了标准的大堆，这时下标为 0 的位置就是最大值，将 0 位置与最后一个位置的元素进行交换，将最大元素换到最后一个位置
	 * 
	 * 5. 然后 0 位置的元素看作大堆构造过程中遍历到的元素，与子节点进行比较，使大堆平衡，这时 0 位置就又是剩余的书中的最大值了，继续将索引 0
	 * 位置的值交换到倒数第二个位置
	 * 
	 * 6. 继续这个过程，直到所有元素都完成排序
	 * 
	 */
	public void heapSort(int[] array) {
		if (array == null || array.length == 1)
			return;

		buildMaxHeap(array); // 构造大堆,构造完成后每个节点都会比自己的左右节点都大

		for (int i = array.length - 1; i > 0; i--) {
			exchangeElements(array, 0, i); // 将最大值依次交换都后面

			// 将 0 位置后移以后，就需要使用现在 0 位置的值看作大堆构造过程中遍历到的值，与堆中其他值进行比较，使大堆平衡
			maxHeap(array, 0, i);
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 构造大堆
	 * 
	 * @param array
	 * @param length
	 *            需要参与构造的数组长度
	 */
	private void buildMaxHeap(int[] array) {
		int len = (array.length - 1) / 2;

		for (int i = len; i >= 0; i--) {
			maxHeap(array, i, array.length);
		}
	}

	/**
	 * 将遍历到的节点与左右子节点比较
	 * 
	 * @param array
	 * @param i
	 * @param length
	 */
	public void maxHeap(int[] array, int i, int length) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int maxIndex = i;

		if (right < length && array[i] < array[left]) {
			maxIndex = left;
		}

		if (right < length && array[maxIndex] < array[right]) {
			maxIndex = right;
		}

		if (maxIndex != i) {
			exchangeElements(array, i, maxIndex);
			maxHeap(array, maxIndex, length);
		}
	}

	/**
	 * 交换数组中两个索引的元素
	 * 
	 * @param array
	 * @param i
	 * @param maxIndex
	 */
	private void exchangeElements(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
