import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static final Stack<Double> numberStack = new Stack<>();
    private static final Stack<String> operatorStack = new Stack<>();

    public static void main(String[] args) {
        double result = evaluateExpression("5 + ( 5 * 5 ) / 5");
        System.out.println(result);
    }

    public static double evaluateExpression(String arg) {
        Scanner input = new Scanner(arg);

        String token = getNextToken(input);

        while (token != null) {
            processToken(token);
            token = getNextToken(input);
        }

        while (!operatorStack.isEmpty()) {
            processOneOperation();
        }

        return numberStack.peek();
    }

    public static String getNextToken(Scanner input) {
        if (input.hasNext()) {
            return input.next();
        } else {
            return null;
        }
    }

    public static double evaluateOperation(double first, double second, String operator) {
        return switch (operator) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> {
                System.out.println("!!! Invalid Operator !!!");
                yield 0.0;
            }
        };
    }

    public static void processOneOperation() {
        double second = numberStack.pop();
        double first = numberStack.pop();

        String operator = operatorStack.pop();

        double result = evaluateOperation(first, second, operator);

        numberStack.push(result);
    }

    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }

    public static boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    public static void processToken(String token) {
        if (isNumber(token)) {
            processNumber(token);
        } else if (isOperator(token)) {
            processOperator(token);
        } else if (isParenthesis(token)) {
            processParenthesis(token);
        }
    }

    public static void processNumber(String token) {
        numberStack.push(toNumber(token));

        if (!operatorStack.isEmpty() && isHighPrecedence(operatorStack.peek())) {
            processOneOperation();
        }
    }

    public static double toNumber(String token) {
        return Double.parseDouble(token);
    }

    public static void processOperator(String token) {
        if (isLowPrecedence(token) && !operatorStack.isEmpty() && !isParenthesis(operatorStack.peek())) {
            processOneOperation();
        }

        operatorStack.push(token);
    }

    public static boolean isHighPrecedence(String operator) {
        return "*/".contains(operator);
    }

    public static boolean isLowPrecedence(String operator) {
        return "+-".contains(operator);
    }

    public static boolean isParenthesis(String token) {
        return "()".contains(token);
    }

    public static void processParenthesis(String token) {
        if (token.equals("(")) {
            operatorStack.push(token);
        } else {
            if (isOperator(operatorStack.peek())) {
                processOneOperation();
            }

            operatorStack.pop();
        }
    }
}
