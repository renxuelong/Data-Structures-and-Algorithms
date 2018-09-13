package algorithms;

/**
 * 选择排序算法
 * 
 * @author loong
 *
 */
public class SelectSort {

	public static void main(String[] args) {
		SelectSort sort = new SelectSort();
		sort.simpleSelectSort(new int[] {3,6,2,4,5,0,3,45,23});
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
		int min,minIndex;
		for (int i = 0; i < array.length; i++) {
			min = array[i];
			minIndex = i;
			for (int j = i; j < array.length; j++) {
				if(array[j] < min) {
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
}
