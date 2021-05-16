/*
 * @lc app=leetcode.cn id=704 lang=typescript
 *
 * [704] 二分查找
 */

// @lc code=start
function search(nums: number[], target: number): number {
  let left: number = 0;
  let right: number = nums.length;

  while (left < right) {
    const mid = left + ((right - left) >> 1);
    if (nums[mid] < target) {
      left = mid + 1;
    } else if (nums[mid] > target) {
      right = mid;
    } else {
      return mid;
    }
  }

  return -1;
}
// @lc code=end
