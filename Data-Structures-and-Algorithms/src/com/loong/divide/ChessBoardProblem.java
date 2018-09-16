package com.loong.divide;

/**
 * 棋盘覆盖问题 - 分治法
 * 
 * @author loong
 *
 */
public class ChessBoardProblem {
	private int[][] board; // 使用二维矩阵表示棋盘
	private int specialRow; // 特殊点的行的坐标
	private int specialCol; // 特殊点的列的坐标
	private int size; // 行数或列数
	private int type; // L 骨牌的类型，四种

	public ChessBoardProblem(int[][] board, int specialRow, int specialCol) {
		this.board = board;
		this.specialRow = specialRow;
		this.specialCol = specialCol;
		this.size = board.length;
	}

	public static void main(String[] args) {
		int num = 2;
		ChessBoardProblem chessBoard = new ChessBoardProblem(new int[num][num], 3, 3);
		chessBoard.chessBoard(chessBoard.specialRow, chessBoard.specialCol, 0, 0, num);
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				System.out.print(chessBoard.board[i][j] + (j == num - 1 ? "" : "-"));
			}
			System.out.println("");
		}
	}

	public void chessBoard(int specialRow, int specialCol, int leftRow, int leftCol, int size) {
		if (size == 1)
			return;
		int subSize = size / 2;

		type = type % 4 + 1;

		// 如果在左上区域
		if (specialRow < leftRow + subSize && specialCol < leftCol + subSize) {
			// 直接递归
			chessBoard(specialRow, specialCol, leftRow, leftCol, subSize);
		} else {
			// 将左下角设置为特殊点
			board[leftRow + subSize - 1][leftCol + subSize - 1] = type;
			// 然后递归左上部分
			chessBoard(leftRow + subSize - 1, leftCol + subSize - 1, leftRow, leftCol, subSize);
		}

		// 如果在右上区域
		if (specialRow < leftRow + subSize && specialCol >= leftCol + subSize) {
			chessBoard(specialRow, specialCol, leftRow, leftCol + subSize, subSize);
		} else {
			board[leftRow + subSize - 1][leftCol + subSize] = type;
			chessBoard(leftRow + subSize - 1, leftCol + subSize, leftRow, leftCol + subSize, subSize);
		}

		// 如果在左下区域
		if (specialRow >= leftRow + subSize && specialCol < leftCol + subSize) {
			chessBoard(specialRow, specialCol, leftRow + subSize, leftCol, subSize);
		} else {
			board[leftRow + subSize][leftCol + subSize - 1] = type;
			chessBoard(leftRow + subSize, leftRow + subSize - 1, leftRow + subSize, leftCol, subSize);
		}

		// 如果在右下区域
		if (specialRow >= leftRow + subSize && specialCol >= leftCol + subSize) {
			chessBoard(specialRow, specialCol, leftRow + subSize, leftCol + subSize, subSize);
		} else {
			board[leftRow + subSize][leftCol + subSize] = type;
			chessBoard(leftRow + subSize, leftCol + subSize, leftRow + subSize, leftCol + subSize, subSize);
		}
	}
}
