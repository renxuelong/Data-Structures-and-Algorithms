package com.loong.leetcode;

/**
 * 解码方法 https://leetcode-cn.com/problems/decode-ways/description/
 * 
 * @author loong
 *
 */
public class DecodeWays91 {

	public static void main(String[] args) {
		DecodeWays91 decodeWays = new DecodeWays91();
		System.out.println(decodeWays.numDecodings("1101"));
	}

	public int numDecodings(String s) {
		int check = checkStr(s);
		if (check == 0)
			return 0;

		return numDecoding(s);
	}

	private int numDecoding(String s) {
		if (s.length() == 1) {
			if ("0".equals(s))
				return 0;
			return 1;
		}
		int[] result = getChildNum(s);
		return result[0];
	}

	/**
	 * 返回字符串 s 及移除前 1 位时的计算结果
	 * 
	 * @param s
	 * @return
	 */
	private int[] getChildNum(String s) {
		if (s.length() == 2) {
			int m = 0;
			int n = 0;
			if (s.charAt(1) == '0') {
				n = 0;
			} else
				n = 1;
			if (s.charAt(0) == '0') {
				m = 0;
			} else {
				int sum = Integer.parseInt(s.substring(0, 2));
				if (sum > 26) {
					m = 1;
				} else {
					if (s.charAt(1) == '0') {
						m = 1;
					} else {
						m = 2;
					}
				}
			}
			return new int[] { m, n };
		} else {
			int[] child = getChildNum(s.subSequence(1, s.length()).toString());
			if (s.startsWith("0")) {
				child[1] = child[0];
				child[0] = 0;
				return child;
			}
			int num = getNumber(s, child[0], child[1]);
			int c = child[0];
			child[0] = num;
			child[1] = c;
			return child;
		}
	}

	private int getNumber(String s, int childNum, int childNum2) {
		int sum = Integer.parseInt(s.substring(0, 2));
		if (sum > 26) {
			return childNum;
		} else {
			if (s.charAt(1) == '0') {
				return childNum2;
			}
			
			return childNum + childNum2;
		}
	}

	private int checkStr(String s) {
		if ("".equals(s) || s == null || s.contains("00") || s.startsWith("0"))
			return 0;
		if (s.length() == 1) {
			return 1;
		}

		char[] array = s.toCharArray();
		for (int i = 0; i < array.length; i++) {
			Character c = array[i];
			if (Integer.parseInt(c.toString()) == 0) {
				Character p = array[i - 1];
				if (Integer.parseInt(p.toString()) > 2)
					return 0;
			}
		}
		return 1;
	}
}
