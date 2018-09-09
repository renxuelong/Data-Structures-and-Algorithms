package com.loong.graph;

/**
 * 克鲁斯卡尔算法 - 求图的最小生成树
 * 
 * @author loong
 *
 */
public class GraphKrusKal {

	private int edgeSize;
	private Edge[] edges;

	public GraphKrusKal(int edgeSize) {
		this.edgeSize = edgeSize;
		edges = new Edge[edgeSize];
	}

	public static void main(String[] args) {
		GraphKrusKal krusKal = new GraphKrusKal(15);
		krusKal.ceateEdgesArray();
		krusKal.kruskal();
	}

	/**
	 * 克鲁斯卡尔算法
	 * 
	 * 解题思路：
	 * 
	 * 1. 将所有的边放入一个数组中，并按照权重大小排序，然后遍历所有的边
	 * 
	 * 2. 遍历过程中还有一个新的数组，这个数组中的索引表示边的起点顶点，值表示将该边加入后，起点所能
	 * 
	 * 3. 每遍历一条边时，都会在这个数组中找到该边顶点和终点所能连接到的最后一个顶点
	 * 
	 * 4. 如果边的起点和终点连接到的顶点是同一个，那么说明形成回环，这条边多余，否则说明该边满足条件，将其加入数组
	 * 
	 * 问题：如何保证找到的最终都是连接的？
	 * 
	 * 答：在解体过程中会遍历每一条边，如果这条边两头都还没有没连接的节点，则会将该边加入集合，所以在解题过程结束后可以保证找到边都是连接的
	 */
	public void kruskal() {
		int[] parent = new int[edgeSize]; // 下标为起点，值为终点
		int sum = 0;
		for (int i = 0; i < edgeSize; i++) {
			int m = find(parent, edges[i].begin);
			int n = find(parent, edges[i].end);
			if (m == n) { // 形成回环
				System.out.println("第 " + i + "条边形成回环");
			} else {
				parent[m] = n;
				sum += edges[i].weight;
			}
		}

		for (int i = 0; i < parent.length; i++)
			System.out.println(parent[i]);

		System.out.println(sum);
	}

	/**
	 * 找到 parent 数组中指定的点可以连接到的最后一个点
	 * 
	 * 在以有的 parent 数组中不可能出现回环，因为 parent 中每个值赋值时都先判断了是否有回环
	 * 
	 * @param parent
	 * @param begin
	 * @return
	 */
	private int find(int[] parent, int f) {
		while (parent[f] != 0) {
			f = parent[f];
		}
		return f;
	}

	public void ceateEdgesArray() {
		Edge edge0 = new Edge(4, 7, 7);
		Edge edge1 = new Edge(2, 8, 8);
		Edge edge2 = new Edge(0, 1, 10);
		Edge edge3 = new Edge(0, 5, 11);
		Edge edge4 = new Edge(1, 8, 12);
		Edge edge5 = new Edge(3, 7, 16);
		Edge edge6 = new Edge(1, 6, 16);
		Edge edge7 = new Edge(5, 6, 17);
		Edge edge8 = new Edge(1, 2, 18);
		Edge edge9 = new Edge(6, 7, 19);
		Edge edge10 = new Edge(3, 4, 20);
		Edge edge11 = new Edge(3, 8, 21);
		Edge edge12 = new Edge(2, 3, 22);
		Edge edge13 = new Edge(3, 6, 24);
		Edge edge14 = new Edge(4, 5, 26);
		edges[0] = edge0;
		edges[1] = edge1;
		edges[2] = edge2;
		edges[3] = edge3;
		edges[4] = edge4;
		edges[5] = edge5;
		edges[6] = edge6;
		edges[7] = edge7;
		edges[8] = edge8;
		edges[9] = edge9;
		edges[10] = edge10;
		edges[11] = edge11;
		edges[12] = edge12;
		edges[13] = edge13;
		edges[14] = edge14;
	}

	public class Edge {
		int begin;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.begin = start;
			this.end = end;
			this.weight = weight;
		}
	}
}
