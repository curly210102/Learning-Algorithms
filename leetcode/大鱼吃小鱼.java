// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
import java.util.Stack;

class Solution {
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        Stack<Integer> stack = new Stack<Integer>();
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            int currentFishSize = A[i];
            if (B[i] == 0) {
                while (!stack.isEmpty() && stack.peek() < currentFishSize) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    count++;
                }
            } else {
                stack.push(currentFishSize);
            }
        }

        return count + stack.size();
    }
}