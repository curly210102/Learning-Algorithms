import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=503 lang=java
 *
 * [503] 下一个更大元素 II
 */

// @lc code=start
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            final int x = nums[i];
            while (!stack.isEmpty() && stack.peek() < x) {
                ans[stack.pop()] = x;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }

        return ans;
    }
}
// @lc code=end
