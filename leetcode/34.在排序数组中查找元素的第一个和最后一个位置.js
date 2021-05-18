/*
 * @lc app=leetcode.cn id=34 lang=javascript
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 */

// @lc code=start
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function (nums, target) {
  const ret = [-1, -1];
  const n = nums.length - 1;

  let left = 0;
  let right = n;

  while (left <= right) {
    const mid = left + ((right - left) >> 1);
    if (nums[mid] >= target) {
      right = mid - 1;
      if (nums[mid] === target && nums[mid - 1] !== target) ret[0] = mid;
    } else {
      left = mid + 1;
    }
  }

  left = 0;
  right = n;
  while (left <= right) {
    const mid = left + ((right - left) >> 1);
    if (nums[mid] <= target) {
      left = mid + 1;
      if (nums[mid] === target && nums[mid + 1] !== target) ret[1] = mid;
    } else {
      right = mid - 1;
    }
  }

  return ret;
};
// @lc code=end
