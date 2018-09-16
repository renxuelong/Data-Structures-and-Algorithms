package com.loong.algorithms;

/**
 * 大数相乘
 * 
 * @author loong
 *
 */
public class BigCountMultiply {

	public static void main(String[] args) {
		BigCountMultiply bigCountMultiply = new BigCountMultiply();
		System.out.println(bigCountMultiply.multiply("503467234823947927498", "62342390423894823040"));
	}

	/**
	 * 大数相乘，直接相乘会超出 int 最大值，溢出
	 * 
	 * 解题思路
	 * 
	 * 1. 先将两个数使用两个 int 类型的数组存放，并将数组反向构造
	 * 
	 * 2. 位数为 n 的数和位数为 m 的数相乘的结果位数不会超过 m + n,构造长度为 m+n 的 int 类型数组存储计算结果
	 * 
	 * 3. 遍历 int 类型的乘数数组，使每一位都分别相乘，i 和 j 位相乘的结果加到 i + j 位置
	 * 
	 * 4. 如果 i+j 位置超过了 10 ，需要进位，进位的数为 i+j 位置的数对 10 取商，将这个值加到 i+j+1 的位置即可完成进位
	 * 
	 * 5. i+j 位置的值对 10 取模，就是这个位置正确的值
	 * 
	 * 6. 最后将结果数组反向输出就得到结果
	 */
	public String multiply(String num1, String num2) {

		int n = num1.length();
		int m = num2.length();

		int[] c1 = new int[n];
		int[] c2 = new int[m];

		for (int i = 0; i < n; i++) {
			c1[n - 1 - i] = num1.charAt(i) - '0';
		}

		for (int i = 0; i < m; i++) {
			c2[m - 1 - i] = num2.charAt(i) - '0';
		}

		int[] result = new int[n + m];

		// 不考虑进位
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i + j] += (c1[i]) * (c2[j]);
			}
		}

		// 处理进位问题
		for (int i = 0; i < n + m; i++) {
			if (result[i] > 10) {
				result[i + 1] += result[i] / 10;
				result[i] = result[i] % 10;
			}
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = result.length - 1; i >= 0; i--) {
			stringBuilder.append(result[i]);
		}

		return stringBuilder.toString();
	}

}
