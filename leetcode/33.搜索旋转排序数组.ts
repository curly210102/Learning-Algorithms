/*
 * @lc app=leetcode.cn id=33 lang=typescript
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
function search(nums: number[], target: number): number {
    let left: number = 0;
    let right: number = nums.length - 1;

    while (left <= right) {
        const mid = left + ((right - left) >> 1);
        if (nums[mid] === target) return mid;
        if (nums[mid] >= nums[left]) {
            if (target >= nums[left] && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            if (target <= nums[right] && target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
};
// @lc code=end

