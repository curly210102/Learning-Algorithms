/*
 * @lc app=leetcode.cn id=540 lang=typescript
 *
 * [540] 有序数组中的单一元素
 */

// @lc code=start
function singleNonDuplicate(nums: number[]): number {
    let l: number = 0;
    let r: number = nums.length - 1;

    while (l <= r) {
        const mid = l + ((r - l) >> 1);

        if (mid % 2 === 1) {
            if (nums[mid] === nums[mid - 1]) l = mid + 1;
            else r = mid - 1;
        } else {
            if (nums[mid] === nums[mid - 1]) r = mid - 1;
            else l = mid + 1;
        }
    }

    return nums[r];
};
// @lc code=end

