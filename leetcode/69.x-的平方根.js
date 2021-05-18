/*
 * @lc app=leetcode.cn id=69 lang=javascript
 *
 * [69] x 的平方根
 */

// @lc code=start
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
    let left = 0;
    let right = x;
    
    while (left <= right) {
        const mid = left + ((right - left) >> 1);
        const product = mid * mid;
        if (product === x) return mid;
        if (product < x) left = mid + 1;
        else right = mid - 1;
    }

    return left - 1;
};
// @lc code=end

