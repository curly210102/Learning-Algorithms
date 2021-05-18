/*
 * @lc app=leetcode.cn id=81 lang=typescript
 *
 * [81] 搜索旋转排序数组 II
 */

// @lc code=start
function search(nums: number[], target: number): boolean {
    let left: number = 0;
    let right: number = nums.length - 1;

    while (left <= right) {
        const mid = left + ((right - left) >> 1);
        if (nums[mid] === target) return true;
        if (nums[mid] === nums[left]) left++;
        else if (nums[mid] > nums[left]) {
            // 左区间有序
            if (nums[mid] > target && nums[left] <= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target  && nums[right] >= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    
    return false;
};
// @lc code=end

