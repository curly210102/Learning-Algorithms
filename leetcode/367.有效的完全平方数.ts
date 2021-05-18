/*
 * @lc app=leetcode.cn id=367 lang=typescript
 *
 * [367] 有效的完全平方数
 */

// @lc code=start
function isPerfectSquare(num: number): boolean {
    let left = 0;
    let right = num;

    while (left <= right) {
        const mid = left + ((right - left) >> 1);
        const product = mid * mid;
        if (product === num) return true;
        if (product < num) left = mid + 1;
        else right = mid - 1;
    }

    return false;
};
// @lc code=end

