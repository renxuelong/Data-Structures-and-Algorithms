package com.loong.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 
 * @author loong
 *
 */
public class Solution22 {
	
	public static void main(String[] args) {
		Solution22 solution = new Solution22();
		List<String> results = solution.generateParenthesis(2);
		for(String result: results) {
			System.out.println(result);
		}
	}
	
	public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList();
        doadd(n,n,list,"");
        return list;    
    }
    
    public static void doadd(int left, int right, List<String> list, String path){
 
        if (left == 0 && right == 0){
            list.add(path);
            return;
        }
        if (left != 0)
            doadd(left-1,right,list,path+"(");
        if (right != 0 && right > left)
            doadd(left,right-1,list,path+")");
    }
}
