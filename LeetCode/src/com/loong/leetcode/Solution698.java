package com.loong.leetcode;

/**
 * 给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等 1 <= k <= len(nums) <=
 * 16 0 < nums[i] < 10000
 * 
 * @author loong
 */
class Solution698 {
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int num : nums)
			sum += num;

		if (sum % k != 0)
			return false;
		boolean[] flag = new boolean[nums.length];
		return help1(nums, flag, sum / k, k, sum / k, 0);
	}

	public boolean help(int[] nums, boolean[] flag, int avg, int k, int temp, int index) {
		if (k == 0)
			return true;
		if (temp == 0)
			return help(nums, flag, avg, k - 1, avg, 0);

		for (int i = index; i < nums.length; i++) {
			if (flag[i] == true)
				continue;

			flag[i] = true;
			if (temp - nums[i] >= 0 && help(nums, flag, avg, k, temp - nums[i], index + 1)) {
				return true;
			}
			flag[i] = false;
		}
		return false;
	}

	public boolean help1(int[] nums, boolean[] flag, int avg, int k, int temp, int index) {
		if (k == 0)
			return true;
		if (temp == avg)
			return help1(nums, flag, avg, k - 1, 0, 0);

		for (int i = index; i < nums.length; i++) {
			if (flag[i] == true)
				continue;

			flag[i] = true;
			if (temp + nums[i] <= avg && help1(nums, flag, avg, k, temp + nums[i], index + 1)) {
				return true;
			}
			flag[i] = false;
		}
		return false;
	}
	
	private boolean dfs(int[] nums, int k, boolean[] visited, int target, int start, int curSum) {
		if (k == 1) {
			return true;
		}

		if (curSum == target) {
			return dfs(nums, k - 1, visited, target, 0, 0);
		}

		for (int i = start; i < nums.length; i++) {
			if (!visited[i] && curSum + nums[i] <= target) {
				visited[i] = true;
				if (dfs(nums, k, visited, target, i + 1, curSum + nums[i]))
					return true;
				visited[i] = false;
			}
		}
		return false;
	}
	
	public boolean canPartitionKSubsets1(int[] nums, int k) {
		if (k == 1) {
			return true;
		}
		
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % k != 0)
			return false;
		boolean[] visited = new boolean[nums.length];
		return dfs1(nums, k, visited, 0, 0, sum / k);

//		 return dfs(nums, k, visited, sum / k, 0, 0);
	}

	private boolean dfs1(int nums[], int k, boolean[] flags, int start, int curSum, int avg) {

		if (k == 1) { // 如果 k == 1 说明遍历到了最后一组，由于每组和相等，最后一组的和必定为 avg
			return true;
		}
		
		if (avg == curSum) {
			return dfs1(nums, k - 1, flags, 0, 0, avg);
		}

		for (int i = start; i < nums.length; i++) {
			if (!flags[i] && curSum + nums[i] <= avg) {
				flags[i] = true;
				if (dfs1(nums, k, flags, i + 1, curSum + nums[i], avg)) {
					return true;
				}
				flags[i] = false;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Solution698 solution = new Solution698();
		boolean result = solution.canPartitionKSubsets1(new int[] { 4, 3, 2, 3, 4, 2, 1 }, 4);
		System.out.println(result);
	}
}
