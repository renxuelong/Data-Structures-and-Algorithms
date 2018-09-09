package com.loong.graph;

import java.util.Stack;

/**
 * 图的拓扑排序 - 使用邻接表的数据结构
 * 
 * @author loong
 *
 */
public class GraphTopological {
	/**
	 * 邻接顶点的一维数组
	 */
	private VertexNode[] adjList;

	public static void main(String[] args) {
		GraphTopological topological = new GraphTopological();
		topological.createGraph();
		topological.toplogicalSort();
	}

	private void createGraph() {
		VertexNode node0 = new VertexNode(0, "v0");
		VertexNode node1 = new VertexNode(0, "v1");
		VertexNode node2 = new VertexNode(2, "v2");
		VertexNode node3 = new VertexNode(0, "v3");
		VertexNode node4 = new VertexNode(2, "v4");
		VertexNode node5 = new VertexNode(3, "v5");
		VertexNode node6 = new VertexNode(1, "v6");
		VertexNode node7 = new VertexNode(2, "v7");
		VertexNode node8 = new VertexNode(2, "v8");
		VertexNode node9 = new VertexNode(1, "v9");
		VertexNode node10 = new VertexNode(1, "v10");
		VertexNode node11 = new VertexNode(2, "v11");
		VertexNode node12 = new VertexNode(1, "v12");
		VertexNode node13 = new VertexNode(2, "v13");
		adjList = new VertexNode[14];
		adjList[0] = node0;
		adjList[1] = node1;
		adjList[2] = node2;
		adjList[3] = node3;
		adjList[4] = node4;
		adjList[5] = node5;
		adjList[6] = node6;
		adjList[7] = node7;
		adjList[8] = node8;
		adjList[9] = node9;
		adjList[10] = node10;
		adjList[11] = node11;
		adjList[12] = node12;
		adjList[13] = node13;
		node0.firstEdge = new EdgeNode(11);
		node0.firstEdge.next = new EdgeNode(5);
		node0.firstEdge.next.next = new EdgeNode(4);
		node1.firstEdge = new EdgeNode(8);
		node1.firstEdge.next = new EdgeNode(4);
		node1.firstEdge.next.next = new EdgeNode(2);
		node2.firstEdge = new EdgeNode(9);
		node2.firstEdge.next = new EdgeNode(6);
		node2.firstEdge.next.next = new EdgeNode(5);
		node3.firstEdge = new EdgeNode(13);
		node3.firstEdge.next = new EdgeNode(2);
		node4.firstEdge = new EdgeNode(7);
		node5.firstEdge = new EdgeNode(12);
		node5.firstEdge.next = new EdgeNode(8);
		node6.firstEdge = new EdgeNode(5);
		node8.firstEdge = new EdgeNode(7);
		node9.firstEdge = new EdgeNode(11);
		node9.firstEdge.next = new EdgeNode(10);
		node10.firstEdge = new EdgeNode(13);
		node12.firstEdge = new EdgeNode(9);
	}

	/**
	 * 拓扑排序
	 * 
	 * 思路:
	 * 
	 * 1. 将所有入度为 0 的顶点入栈，如果栈不为空就开始出栈
	 * 
	 * 2. 遍历出栈的顶点的出度顶点，并且对应的出度顶点入度 -1
	 * 
	 * 3. 遍历完一个顶点的出栈顶点后，再将新出现的入度为 0 的顶点入栈
	 * 
	 * 4. 继续出栈直到栈为空
	 * 
	 * 5. 注意：如果栈为空以后顶点没有全部遍历到，说明拓扑排序序列构造失败
	 */
	public void toplogicalSort() {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < adjList.length; i++) {
			if (adjList[i].in == 0) {
				stack.push(i);
			}
		}

		int account = 0;
		while (!stack.isEmpty()) {
			int index = stack.pop();
			System.out.println(index);
			account++;
			for (EdgeNode node = adjList[index].firstEdge; node != null; node = node.next) {
				if (--adjList[node.adjVert].in == 0) {
					stack.push(node.adjVert);
				}
			}
		}

		if (account != adjList.length) {
			System.out.println("拓扑排序失败");
		}
	}

	/**
	 * 边表顶点
	 */
	class EdgeNode {
		/**
		 * 对应邻接顶点在一维数组中的位置
		 */
		private int adjVert;
		private EdgeNode next;

		EdgeNode(int adjVert) {
			this.adjVert = adjVert;
		}
	}

	/**
	 * 邻接顶点
	 */
	class VertexNode {
		/**
		 * 入度
		 */
		private int in;
		private EdgeNode firstEdge;
		private String data;

		public VertexNode(int in, String data) {
			this.in = in;
			this.data = data;
		}
	}
}
