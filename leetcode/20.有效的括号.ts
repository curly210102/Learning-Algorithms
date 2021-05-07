/*
 * @lc app=leetcode.cn id=20 lang=typescript
 *
 * [20] 有效的括号
 */

// @lc code=start
function isValid(s: string): boolean {
  if (!s) {
    return true;
  }
  if (s.length % 2 === 1) {
    return false;
  }
  const pairs: Map<String, String> = new Map([
    [")", "("],
    ["}", "{"],
    ["]", "["],
  ]);

  const stack: string[] = [];

  for (const ch of s) {
    if (pairs.has(ch)) {
      if (stack.length > 0 && stack[stack.length - 1] === pairs.get(ch)) {
        stack.pop();
      } else {
        return false;
      }
    } else {
      stack.push(ch);
    }
  }

  return stack.length === 0;
}
// @lc code=end
