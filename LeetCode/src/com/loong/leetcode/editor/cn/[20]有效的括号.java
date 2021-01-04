package com.loong.leetcode.editor.cn;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 2006 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution20 {
    public boolean isValid(String s) {
        // 1. 替换法，迭代替换字符串中的 "() [] {}"，每次循环后如果 string 没有变化就返回 false，最后成空返回 true


        // 2. 通过栈解决
        if (s == null || "".equals(s) || s.length() % 2 == 1) return false;

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i = 1; i < s.length(); i++) {
            // 第一版，直接对比
//            char n = s.charAt(i);
//            if (!stack.isEmpty()) {
//                Character peek = stack.peek();
//                if (peek == '}' || peek == ']' || peek == ')') {
//                    return false;
//                }
//                if (n == '}') {
//                    if (peek == '{') {
//                        stack.pop();
//                    } else {
//                        return false;
//                    }
//                } else if (n == ']') {
//                    if (peek == '[') {
//                        stack.pop();
//                    } else {
//                        return false;
//                    }
//                } else if (n == ')') {
//                    if (peek == '(') {
//                        stack.pop();
//                    } else {
//                        return false;
//                    }
//                } else if (n == '{' || n == '[' || n == '(') {
//                    stack.push(n);
//                } else {
//                    return false;
//                }
//        } else {
//            stack.push(n);
//        }

            // 第二版，通过 Map 来快速判断遍历到的值是否有效, 如果有效还能快速判断栈顶元素是否匹配
            char n = s.charAt(i);
            if (map.containsKey(n) && !stack.isEmpty() && map.get(n) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(n);
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
