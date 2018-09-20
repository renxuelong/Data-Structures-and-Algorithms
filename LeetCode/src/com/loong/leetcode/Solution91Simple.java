package com.loong.leetcode;

public class Solution91Simple {
	public static void main(String[] args) {
		Solution91Simple decodeWays = new Solution91Simple();
		System.out.println(decodeWays.numDecodingB("101"));
	}

	public int numDecodingB(String s) {
		int len = s.length();
		int[] values = new int[len];
		char pre = s.charAt(0);
		values[0] = pre == '0' ? 0 : 1;
		if (len == 1)
			return values[0];

		char cur = s.charAt(1);
		// 在判断条件较多时可以不进行那么多判断，可以只选取自己需要的情况的条件，其他只要不满足就设初始值
		values[1] = cur != '0' ? values[0] : 0;
		values[1] += pre == '1' || pre == '2' && cur < '7' ? 1 : 0;
		if(values[1] == 0) return 0;
		for (int i = 2; i < len; i++) {
			pre = cur;
			cur = s.charAt(i);
			values[i] = cur != '0' ? values[i - 1] : 0; // 当前字符单独组合
			values[i] += pre == '1' || pre == '2' && cur < '7' ? values[i - 2] : 0; // 当前字符可以和前一个字符组合
			if(values[i] == 0) return 0;
		}
		return values[len - 1];
	}
}
