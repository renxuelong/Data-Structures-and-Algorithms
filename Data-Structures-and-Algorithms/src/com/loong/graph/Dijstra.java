package com.loong.graph;

/**
 * 迪克斯特拉算法 - 求图的最段路径
 * 
 * @author loong
 *
 */
public class Dijstra {
	public static final int MAX_WEIGHT = Integer.MAX_VALUE - 1000;

	public static void main(String[] args) {
		System.out.println("Graph");

		Graph graph = new Graph(9);
		int[] a1 = new int[] { 0, 1, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a2 = new int[] { 1, 0, 3, 7, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a3 = new int[] { 5, 3, 0, MAX_WEIGHT, 1, 7, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a4 = new int[] { MAX_WEIGHT, 7, MAX_WEIGHT, 0, 2, MAX_WEIGHT, 3, MAX_WEIGHT, MAX_WEIGHT };
		int[] a5 = new int[] { MAX_WEIGHT, 5, 1, 2, 0, 3, 6, 9, MAX_WEIGHT };
		int[] a6 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 7, MAX_WEIGHT, 3, 0, MAX_WEIGHT, 5, MAX_WEIGHT };
		int[] a7 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 3, 6, MAX_WEIGHT, 0, 2, 7 };
		int[] a8 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 9, 5, 2, 0, 4 };
		int[] a9 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 7, 4, 0 };

		graph.matrix[0] = a1;
		graph.matrix[1] = a2;
		graph.matrix[2] = a3;
		graph.matrix[3] = a4;
		graph.matrix[4] = a5;
		graph.matrix[5] = a6;
		graph.matrix[6] = a7;
		graph.matrix[7] = a8;
		graph.matrix[8] = a9;
		
		Dijstra dijstra = new Dijstra();
		dijstra.dijstra(graph);
	}

	/**
	 * 迪克斯特拉算法 - 通过邻接矩阵的数据结构
	 * 
	 * 求从 V0 顶点到其他顶点的最短路径
	 * 
	 * 如果题目不是从 V0 开始那么则需要先将 matrix 数组中对应顶点与 V0 顶点位置互换，注意需要修改邻接矩阵中的与其他顶点关联的值
	 * 
	 * 如果是求到某一顶点的最短路径，isFind 数组中对应位置为 true 以后，就可以 return 了，不需要找到所有顶点的最短路径
	 * 
	 * 因为在解题过程中，每一步都是找到距离起始顶点的最短距离，所以 isFind 数组中对应顶点位置为 true 以后，shortTablePath 中对应下标处的值肯定是最短路径
	 * 
	 * @param graph
	 */
	public void dijstra(Graph graph) {
		int[] shortTablePath = new int[graph.vertexSize]; // 存放从 0 到其他顶点的最短距离
		boolean[] isFind = new boolean[graph.vertexSize]; // 存放 shortTablePath 数组中对应的值是否已经是最小值

		for (int i = 0; i < graph.vertexSize; i++) {
			shortTablePath[i] = graph.matrix[0][i]; // 将 matrix[0] 中对应的值一次
		}
		isFind[0] = true;

		for (int i = 1; i < graph.vertexSize; i++) {
			int min = MAX_WEIGHT, minIndex = 0;
			
			// 找到 shortTablePath 中 距离起始顶点最近的顶点
			for (int j = 1; j < graph.vertexSize; j++) {
				if (!isFind[j] && shortTablePath[j] < min) {
					min = shortTablePath[j];
					minIndex = j;
				}
			}
			// 将当前位置的最点路径状态置为已找到
			isFind[minIndex] = true;
			
			// 由 起始顶点距离其他顶点的值 shortTablePath[j] 与 起始顶点到当前顶点的值加上当前顶点到其他顶点的值 min + graph.matrix[minIndex][j] 做比较
			// 如果前者大，说明通过当前顶点再到其他顶点的路径更短，此时替换 shortTablePath 中对应位置的值
			for (int j = 1; j < graph.vertexSize; j++) {
				int value = min + graph.matrix[minIndex][j];
				if (!isFind[j] && value < shortTablePath[j]) {
					shortTablePath[j] = value;
				}
			}
		}
		
		for (int i = 0; i < graph.vertexSize; i++) {
			System.out.println("顶点 0 到顶点 " + i + " 的最短路径: " + shortTablePath[i]);
		}
	}
}
