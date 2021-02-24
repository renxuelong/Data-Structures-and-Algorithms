package com.loong.leetcode.editor.cn;//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 735 👎 0


import java.util.LinkedList;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack155 {

    // 1. 两个栈辅助
//    LinkedList<Integer> stack;
//    LinkedList<Integer> minStack;
//
//    /**
//     * initialize your data structure here.
//     */
//    public MinStack() {
//        stack = new LinkedList<>();
//        minStack = new LinkedList<>();
//    }
//
//    public void push(int x) {
//        stack.push(x);
//        if (minStack.isEmpty()) {
//            minStack.push(x);
//        } else {
//            int y = minStack.peek();
//            minStack.push(Math.min(x, y));
//        }
//    }
//
//    public void pop() {
//        if (stack.isEmpty() || minStack.isEmpty()) {
//            return;
//        }
//        minStack.pop();
//        stack.pop();
//    }
//
//    public int top() {
//        if (stack.isEmpty()) {
//            return 0;
//        }
//        return stack.peek();
//    }
//
//    public int getMin() {
//        if (minStack.isEmpty()) {
//            return 0;
//        } else {
//            return minStack.peek();
//        }
//    }


    // 2. 一个栈完成, 入栈时，如果是小于等于 min 的值，把原来最小值入栈再入栈新数，更新 min
    // 出栈时如果栈顶元素等于 min，还需要再次出栈原来的最小值并赋值给 min

    Stack<Integer> stack;
    int min = Integer.MAX_VALUE;

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            if (stack.pop() == min) {
                min = stack.pop();
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        } else {
            return 0;
        }
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)
