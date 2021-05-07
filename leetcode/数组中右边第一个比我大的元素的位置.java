import edu.princeton.cs.algs4.Stack;

class Solution {
    public static int[] findRightBig(int[] A) {
        int[] ans = new int[A.length];

        Stack<Integer> stack = new Stack();

        for (int i = 0; i < A.length; i++) {
            final int x = A[i];
            while (!stack.isEmpty() && stack.peek() < x) {
                ans[stack.pop()] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }
        return ans;
    }
}
