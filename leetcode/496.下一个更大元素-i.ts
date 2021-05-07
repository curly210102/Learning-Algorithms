/*
 * @lc app=leetcode.cn id=496 lang=typescript
 *
 * [496] 下一个更大元素 I
 */

// @lc code=start
function nextGreaterElement(nums1: number[], nums2: number[]): number[] {
  const nextGreater: Map<number, number> = new Map();
  const stack: number[] = [];

  for (let i = 0, len = nums2.length; i < len; i++) {
    const x = nums2[i];
    while (stack.length > 0 && stack[stack.length - 1] < x) {
      nextGreater.set(stack.pop(), x);
    }
    stack.push(x);
  }

  return nums1.map((num) => (nextGreater.has(num) ? nextGreater.get(num) : -1));
}
// @lc code=end
