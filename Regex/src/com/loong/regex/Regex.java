package com.loong.regex;

/**
 * 正则表达式
 * 
 * 1. 匹配 - 其实就是 String 类中的 matches 方法
 * 
 * 2. 切割 - 其实就是 String 类中的 splist 方法
 * 
 * 3. 替换 - 其实就是 String 类中的 replaceAll 方法
 * 
 * 4. 获取 - 使用正则表达式 Pattern 的实例获取匹配器再拿到匹配字符串
 * 
 * @author loong
 * 
 */
public class Regex {

	public static void main(String[] args) {
		Regex regex = new Regex();
		System.out.println(regex.checkQQ("28393"));
		System.out.println(regex.checkPhone("18976534567"));
		regex.fun2();
	}

	/**
	 * 1. 匹配 - 手机号
	 */
	public boolean checkPhone(String num) {
		// 规则 - 符合一定规则的正则表达式
		String regex = "1[358][0-9]{9}";
		return num.matches(regex);
	}

	/**
	 * 2. 切割
	 */
	public void fun2() {
		String str = "a  b c d     e";
		// 以空格切割，并且支持多个空格切割
		String[] result = str.split(" +");
		for (String s : result) {
			System.out.println(s);
		}
		System.out.println("  ----  ");
		String s1 = "a.b.c";

		// 以 . 切割
		String[] r1 = s1.split("[.]");
		for (String s : r1) {
			System.out.println(s);
		}

		// 以 . 切割
		String[] r2 = s1.split("\\.");
		for (String s : r2) {
			System.out.println(s);
		}

		// 以 . 切割
		String s3 = "zhangsanyyyyyyyxiaoqiaongzzzzzzdolao";

		// 不确定字符分割 - 使用函数名
		String[] r3 = s3.split("(.)\\1+");
		for (String s : r3) {
			System.out.println(s);
		}
	}

	/**
	 * 校验 QQ 号
	 * 
	 * 规则：
	 * 
	 * 1. 长度： 5-15 位
	 * 
	 * 2. 全部是数字
	 * 
	 * 3. 0 不能开头
	 * 
	 * @param str
	 * @return
	 */
	public boolean checkQQ(String qq) {
		// 第一位是 1-9，第二位是 0-9，与第二位一样规则的数字要有 4-14 个
		String regex = "[1-9][0-9]{4,14}"; // {} 是用来修饰前面最近的 []
		return qq.matches(regex);
	}
}
