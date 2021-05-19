/*
 * @lc app=leetcode.cn id=154 lang=typescript
 *
 * [154] 寻找旋转排序数组中的最小值 II
 */

// @lc code=start
function findMin(nums: number[]): number {
    let l: number = 0;
    let r: number = nums.length - 1;

    while (l < r) {
        const mid = l + ((r - l) >> 1);
        if (nums[mid] === nums[r]) r--;
        else if (nums[mid] < nums[r]) r = mid;
        else l = mid + 1;
    }

    return nums[r];
};
// @lc code=end

