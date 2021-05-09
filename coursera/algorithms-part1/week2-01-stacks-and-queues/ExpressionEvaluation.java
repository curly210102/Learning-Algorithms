import edu.princeton.cs.algs4.StdIn;

/**
 * ExpressionEvaluation
 */
public class ExpressionEvaluation {

    public static void main(String[] args) {
        ArrayStack<String> ops = new ArrayStack<>();
        ArrayStack<Double> vals = new ArrayStack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            switch (s) {
                case "(":
                    break;
                case "*":
                case "+":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    if (op.equals("*"))
                        vals.push(vals.pop() * vals.pop());
                    if (op.equals("+"))
                        vals.push(vals.pop() + vals.pop());
                    break;
                default:
                    vals.push(Double.parseDouble(s));
            }
        }

        System.out.println(vals.pop());
    }
}