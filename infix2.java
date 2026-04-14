import java.util.Scanner;
import java.util.Stack;

public class infix2 {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    // Check if operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Count operands
    static int countOperands(String exp) {
        int count = 0;
        for (char ch : exp.toCharArray()) {
            if (isOperand(ch)) {
                count++;
            }
        }
        return count;
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (isOperand(ch)) {
                result.append(ch);
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        StringBuilder input = new StringBuilder(exp);
        input.reverse();

        // Swap brackets
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                input.setCharAt(i, ')');
            else if (input.charAt(i) == ')')
                input.setCharAt(i, '(');
        }

        String postfix = infixToPostfix(input.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an infix expression (with 4 operands): ");
        String infix = scanner.nextLine().replaceAll("\\s+", "");

        // Validate number of operands
        int operandCount = countOperands(infix);

        if (operandCount != 4) {
            System.out.println("Error: Expression must contain exactly 4 operands.");
        } else {
            System.out.println("Infix Expression  : " + infix);
            System.out.println("Postfix Expression: " + infixToPostfix(infix));
            System.out.println("Prefix Expression : " + infixToPrefix(infix));
        }

        scanner.close();
    }
}
