package com.loong.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	public static final int MAX_WEIGHT = Integer.MAX_VALUE;

	public int vertexSize; // 顶点数量
	public int[] vertexs; // 顶点数组
	public int[][] matrix; // 邻接矩阵

	public boolean[] isVisited;

	public Graph(int vertexSize) {
		this.vertexSize = vertexSize;
		vertexs = new int[vertexSize];
		matrix = new int[vertexSize][vertexSize];
		this.isVisited = new boolean[vertexSize];

		for (int i = 0; i < vertexSize; i++) {
			vertexs[i] = i;
		}
	}

	public static void main(String[] args) {
		System.out.println("Graph");

		Graph graph = new Graph(9);
		int[] a1 = new int[] { 0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a2 = new int[] { 10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12 };
		int[] a3 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8 };
		int[] a4 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21 };
		int[] a5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT };
		int[] a6 = new int[] { 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT };
		int[] a7 = new int[] { MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT };
		int[] a8 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT };
		int[] a9 = new int[] { MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0 };

		graph.matrix[0] = a1;
		graph.matrix[1] = a2;
		graph.matrix[2] = a3;
		graph.matrix[3] = a4;
		graph.matrix[4] = a5;
		graph.matrix[5] = a6;
		graph.matrix[6] = a7;
		graph.matrix[7] = a8;
		graph.matrix[8] = a9;

		System.out.println(graph.getInnerDegree(0));
		System.out.println(graph.getOutDegree(1));
		System.out.println(graph.getWeight(5, 6));
		System.out.println("-------深度遍历---------");
		graph.depthFirstSearch();
		System.out.println("-------广度遍历---------");
		graph.broadFirstSearch();
		System.out.println("-------普里姆算法最小生成树---------");
		graph.prim();
	}

	/**
	 * 获取一个顶点的出度
	 */
	public int getOutDegree(int index) {
		int degree = 0;
		int weight;
		for (int i = 0; i < vertexSize; i++) {
			weight = matrix[index][i];
			if (weight > 0 && weight < MAX_WEIGHT) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * 获取一个顶点的入度
	 */
	public int getInnerDegree(int index) {
		int degree = 0;
		int weight;
		for (int i = 0; i < vertexSize; i++) {
			weight = matrix[i][index];
			if (weight > 0 && weight < MAX_WEIGHT) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * 获取两个顶点之间的权值
	 */
	public int getWeight(int v1, int v2) {
		int weight = matrix[v1][v2];
		return weight == MAX_WEIGHT ? -1 : weight;
	}

	/**
	 * 寻找一个顶点的第一个邻接顶点
	 */
	public int findFirstNeibor(int index) {
		return findNextNeibor(index, -1);
	}

	/**
	 * 寻找一个顶点行对于当前邻接顶点的下一个邻接顶点
	 * 
	 * @param index
	 * @param pre
	 * @return
	 */
	private int findNextNeibor(int index, int pre) {
		for (int i = pre + 1; i < vertexSize; i++) {
			if (matrix[index][i] > 0 && matrix[index][i] < MAX_WEIGHT)
				return i;
		}
		return -1;
	}

	/**
	 * 图的深度优先遍历 - 深度优先搜索 类似树的前序遍历 向外变暴露的方法
	 */
	public void depthFirstSearch() {
		isVisited = new boolean[vertexSize];
		for (int i = 0; i < vertexSize; i++) {
			if (!isVisited[i]) {
				System.out.println(i);
				isVisited[i] = true;
				depthFirstSearch(i);
			}
		}
		isVisited = new boolean[vertexSize];
	}

	private void depthFirstSearch(int index) {
		int next = findFirstNeibor(index);
		while (next != -1) {
			if (!isVisited[next]) {
				System.out.println(next);
				isVisited[next] = true;
				depthFirstSearch(next);
			}
			next = findNextNeibor(index, next);
		}
	}

	/**
	 * 图的广度优先遍历 - 广度优先搜索，向外暴露的方法 类似书的层次遍历
	 */
	private void broadFirstSearch() {
		isVisited = new boolean[vertexSize];
		for (int i = 0; i < vertexSize; i++) {
			if (!isVisited[i]) {
				broadFistSearch(i);
			}
		}
		isVisited = new boolean[vertexSize];
	}

	/**
	 * 实现广度优先遍历
	 * 
	 * @param index
	 */
	private void broadFistSearch(int index) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(index);

		while (!queue.isEmpty()) {
			int v = queue.poll();
			if (!isVisited[v]) {
				System.out.println(v);
				isVisited[v] = true;

				int next = findFirstNeibor(v);
				while (next != -1) {
					queue.offer(next);
					next = findNextNeibor(v, next);
				}
			}
		}
	}

	/**
	 * prim 普里姆算法 - 最小生成树
	 */
	public void prim() {
		int[] lowcost = new int[vertexSize]; // 遍历过程中最小代价顶点权值的数组，为 0 表示已经找到
		int[] adjvex = new int[vertexSize]; // 最小生成树顶点的权值的数组，存的是最终权值
		int min, minIndex = 0, sum = 0;

		for (int i = 1; i < vertexSize; i++) {
			lowcost[i] = matrix[0][i]; // 为顶点权值数组赋值初始值，不知直接设置为 matrix[0]，因为 matrix[0] 是一个数组对象
		}

		for (int i = 1; i < vertexSize; i++) {
			min = MAX_WEIGHT;
			minIndex = 0;
			for (int j = 1; j < vertexSize; j++) {
				if (lowcost[j] > 0 && min > lowcost[j]) {
					min = lowcost[j];
					minIndex = j; // 找到当前顶点所连接的未被遍历的权重最小的顶点
				}
			}

			lowcost[minIndex] = 0; // 已找到的就设置为找到状态
			adjvex[minIndex] = min; // 记录权重
			sum += min;

			for (int j = 1; j < vertexSize; j++) { // 记录当前顶点连接的其他顶点，已经遍历过的不再记录
				if (lowcost[j] > 0 && matrix[minIndex][j] < lowcost[j] && matrix[minIndex][j] > 0) {
					lowcost[j] = matrix[minIndex][j];
				}
			}
			System.out.println("顶点：" + minIndex + " 权值 " + min);
		}
		System.out.println(sum);
	}
}
