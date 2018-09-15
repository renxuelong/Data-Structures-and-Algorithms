package ShareWine;

/**
 * 穷举法 - 泊松分酒
 * 
 * 三个杯子 12L、8L、5L，倒出 6L 的酒。初始酒量 12、0、0
 * 
 * @author loong
 *
 */
public class ShareWine {

	private static final int B1 = 12; // 杯子 1 的容量
	private static final int B2 = 8; // 杯子 1 的容量
	private static final int B3 = 5; // 杯子 1 的容量
	private static final int M = 6; // 目标容量

	public static void main(String[] args) {
		ShareWine shareWine = new ShareWine();
		shareWine.shareWine(12, 0, 0);
	}

	/**
	 * 倒酒
	 * 
	 * 解题思路：穷举法就是按照一定规则进行尝试，所以要先制定规则
	 * 
	 * 指定倒酒规则：
	 * 
	 * 1. 1 --> 2 --> 3 --> 1，1 只能向 2 倒，2 只能向 3 倒，3 只能向 4 倒
	 * 
	 * 2. 每次倒酒必须要倒满，如果倒不满就必须倒完不能剩
	 * 
	 * @param bb1
	 * @param bb2
	 * @param bb3
	 */
	public void shareWine(int bb1, int bb2, int bb3) {
		System.out.println("目前杯中容量：bb1:" + bb1 + "  --  bb2:" + bb2 + "  --  bb3:" + bb3);
		if (bb1 == M || bb2 == M || bb2 == M) {
			System.out.println("倒出了目标容量");
			return;
		}

		// 倒酒，谁先给谁倒无所谓
		if (bb2 != 0 && bb3 != B3) { // b2 往 b3 倒
			if (bb2 > B3 - bb3) {
				shareWine(bb1, bb2 - (B3 - bb3), B3);
			} else {
				shareWine(bb1, 0, bb2 + bb3);
			}
		} else if (bb3 == B3) { // b3 满了往 b1 倒
			if (bb3 > B1 - bb1) {
				shareWine(B1, bb2, bb3 - (B1 - bb1));
			} else {
				shareWine(bb1 + bb3, bb2, 0);
			}
		} else if (bb2 == 0) {
			if (bb1 > B2) {
				shareWine(bb1 - B2, B2, bb3);
			} else {
				shareWine(0, bb1, bb3);
			}
		}
	}
}
