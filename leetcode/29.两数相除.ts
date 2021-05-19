/*
 * @lc app=leetcode.cn id=29 lang=typescript
 *
 * [29] 两数相除
 */

// @lc code=start
function divide(dividend: number, divisor: number): number {
    function mul (a: number, k: number): number {
        let ans: number = 0;
        while (k > 0) {
            if ((k & 1) === 1) ans += a;
            k >>>= 1;
            a += a;
        }
        return ans;
    }

    function _divide(x: number, y: number): number {
        let l = 0;
        let r = x;

        while (l <= r) {
            const mid = l + ((r - l) >>> 1);
            const product = mul(mid, y);

            if (product == x) {
                return mid;
            }
            if (product < x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l - 1;
    }

    let x = dividend;
    let y = divisor;
    let isNeg: boolean = (x ^ y) < 0;
    if (x < 0) x = -x;
    if (y < 0) y = -y;

    const ans: number = _divide(x, y) * (isNeg ? -1: 1);


    if (ans > (Math.pow(2, 31) - 1) || ans < Math.pow(-2, 31)) return Math.pow(2, 31) - 1;

    return ans;

};


// @lc code=end

