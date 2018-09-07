package com.loong.leetcode;

public class Solution91 {
	public static void main(String[] args) {
		Solution91 decodeWays = new Solution91();
		System.out.println(decodeWays.numDecodingB("100"));
	}

	public int numDecodingB(String s) {
		int check = checkStr(s);
		if (check == 0)
			return 0;
		
		if(s.length() == 1) return 1;
		
		int[] result = new int[s.length()];
		result[0] = 1;
		if (s.charAt(1) == '0') {
			if (Integer.parseInt(s.substring(0, 2)) > 26) // 判断大于 2 的情况
				return 0;
			result[1] = 1;
		} else {
			if (Integer.parseInt(s.substring(0, 2)) > 26) 
				result[1] = 1;
			else
				result[1] = 2;
		}

		for (int i = 2; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				if ((Integer.parseInt(s.substring(i - 1, i + 1)) > 26))
					return 0;
				else
					result[i] = result[i - 2];
			} else {
				if (s.charAt(i - 1) == '0') {
					result[i] = result[i - 1];
				} else {
					if ((Integer.parseInt(s.substring(i - 1, i + 1)) > 26)) {
						result[i] = result[i - 1];
					} else {
						result[i] = result[i - 1] + result[i - 2];
					}
				}
			}
		}

		return result[s.length() - 1];
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

	public int numDecodings(String s) {
		int[] dp = new int[s.length() + 1];
		dp[1] = s.charAt(0) == '0' ? 0 : 1;
		dp[0] = 1;
		for (int i = 2; i <= s.length(); i++) {
			if (s.charAt(i - 1) != '0') {
				dp[i] += dp[i - 1];
			}
			if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')) {
				dp[i] += dp[i - 2];
			}
		}
		return dp[s.length()];
	}
}
