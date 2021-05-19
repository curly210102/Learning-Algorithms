/*
 * @lc app=leetcode.cn id=4 lang=typescript
 *
 * [4] 寻找两个正序数组的中位数
 */

// @lc code=start
function findMedianSortedArrays(nums1: number[], nums2: number[]): number {
    if (nums1.length > nums2.length) {
        const temp = nums1;
        nums1 = nums2;
        nums2 = temp;
    }

    const m: number = nums1.length;
    const n: number = nums2.length;
    const totalLeft: number = (m + n + 1) >> 1;

    let l: number = 0;
    let r: number = m;

    while (l < r) {
        const i = l + ((r - l + 1) >> 1);
        const j = totalLeft - i;

        if (nums1[i - 1] > nums2[j]) {
            r = i - 1;
        } else {
            l = i;
        }
    }

    const mid1: number = l;
    const mid2: number = totalLeft - l;
    const nums1Left = mid1 === 0 ? -Infinity : nums1[mid1 - 1];
    const nums1Right = mid1 === m ? Infinity : nums1[mid1];
    const nums2Left = mid2 === 0 ? -Infinity : nums2[mid2 - 1];
    const nums2Right = mid2 === n ? Infinity : nums2[mid2];

    if ((m + n) % 2 === 1) return Math.max(nums1Left, nums2Left);
    else return (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2;
};
// @lc code=end

