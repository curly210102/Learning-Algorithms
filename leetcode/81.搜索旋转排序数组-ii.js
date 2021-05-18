/*
 * @lc app=leetcode.cn id=81 lang=javascript
 *
 * [81] 搜索旋转排序数组 II
 */

// @lc code=start
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {boolean}
 */
var search = function(nums, target) {
    let left  = 0;
    let right  = nums.length - 1;

    while (left <= right) {
        const mid = left + ((right - left) >> 1);

        if (nums[mid] === target) return true;

        if (nums[left] === nums[mid]) left++;
        else if (nums[left] < nums[mid]) {
            // 左区间增序
            if (nums[mid] > target && nums[left] <= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            // 右区间增序
            if (nums[mid] < target && nums[right] >= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return false;
};
// @lc code=end

