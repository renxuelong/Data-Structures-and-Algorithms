package com.loong.algorithms.recursive;

/**
 * 欧几里得算法 - 求两个整数的最大公约数
 * 
 * m 和 n (m > n) 的最大公约数等于 n 和 (m % n) 的最大公约数
 * 
 * @author loong
 *
 */
public class Gcd {

	public static void main(String[] args) {
		Gcd gcd = new Gcd();
		System.out.println(gcd.gcd(36, 12));
	}

	public int gcd(int m, int n) {
		if (n == 0) {
			return m;
		} else {
			return gcd(n, m % n);
		}
	}
}
