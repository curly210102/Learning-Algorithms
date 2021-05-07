class Solution {
    public static int[] findLeftSmall(int[] A) {
        int[] ans = new int[A.length];

        Stack<Integer> stack = new Stack();

        for (int i = 0; i < A.length; i++) {
            final int x = A[i];
            while (!stack.isEmpty() && stack.peek() >= x) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return ans;
    }
}
