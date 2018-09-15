package com.loong.algorithms.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 基数排序 - 只适用于整数
 * 
 * @author loong
 *
 */
public class BasicSort {

	public static void main(String[] args) {
		BasicSort sort = new BasicSort();
		int[] array = new int[] { 3, 6, 2, 4, 5, 5, 5, 5, 5, 0, 3, 45, 23 };
		sort.basicSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * 基数排序
	 * 
	 * 下面的排序未考虑负数的情况，如果有负数，可以将负数单独作为一个数组，排序后反过来即可，最后添加到正数数组的前面即可 *
	 * 
	 * http://odgw9c93i.bkt.clouddn.com//FnD1PHjhKxeP3CJJn_JecIApJEA1
	 * 
	 * @param array
	 */
	public void basicSort(int[] array) {
		// 获取最大值
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}

		// 根据最大值，确定排序循环次数
		int num = 0;
		while (max > 0) {
			num++;
			max /= 10;
		}

		// 构造二维数组存储遍历到的元素
		List<LinkedList<Integer>> lists = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			lists.add(new LinkedList<>());
		}

		for (int i = 0; i < num; i++) {
			for (int j = 0; j < array.length; j++) {
				int temp = array[j];
				// 求出对应的值( i 为 0 是个位，1 是 十位，2 是百位···)
				int x = temp % ((int) Math.pow(10, i + 1)) / ((int) Math.pow(10, i));
				lists.get(x).offer(temp);
			}

			int count = 0;
			for (int j = 0; j < 10; j++) {
				while (!lists.get(j).isEmpty()) {
					array[count] = lists.get(j).poll();
					count++;
				}
			}
		}
	}
}
