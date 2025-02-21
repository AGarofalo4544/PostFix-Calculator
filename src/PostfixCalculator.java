// import java.util.Arrays;
import java.util.StringTokenizer;


public class PostfixCalculator
{
    // PostFix is when the Operator comes after the Operands.
    // Example:
    //   4 3 2 * + 11 -
    //   4 (3 * 2) + 11 -
    //   4 6 + 11 -
    //   (4 + 6) 11 -
    //   10 11 -
    //   (10 - 11)
    //   -1

    public PostfixCalculator()
    {
    }

    /**
     * Returns the provided Infix expression in PostFix.
     * Uses a Stack to store and handle the proper placement of operators.
     * Uses a Tokenizer to parse each part of the expression.
     * @param infix The expression to be converted from Infix to PostFix and solved for.
     * @return      The PostFix version of the original Infix expression.
     */
    public static String infix2postfix(String infix)
    {
        String operator = " ";
        String postfix = " ";
        AlexStackClass<String> infixes = new AlexStackClass<String>();
        StringTokenizer tokenizer = new StringTokenizer(infix, " ");

        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();

            // Checks if token is an open parenthesis "("
            if (token.equals("("))
            {
                infixes.push(token);
            }
            // Checks if token is a closed parenthesis ")"
            else if (token.equals(")"))
            {
                // while infixes.top() is not an open parenthesis "("
                while (!infixes.top().equals("("))
                {
                    // Why are we popping the operators out of infixes?
                    // To get back to the open parenthesis that is "paired"
                    // with this closed parenthesis. We have handled all
                    // operators within this pair of parentheses.
                    operator = infixes.pop();
                    postfix += operator + " ";
                }
                // If the while statement returns false, or fails, then 
                // the original open parenthesis "(" is popped out of the stack.
                operator = infixes.pop();
            }
            // Check if token is an Operator.
            else if (operatorCheck(token))
            {
                // Checks Operator Precedence.
                if ((!infixes.isEmpty()) &&
                        (operationPrecedence(token) <= operationPrecedence(infixes.top())))
                {
                    postfix += infixes.pop() + " ";
                }
                infixes.push(token);
            }
            else
            {
                postfix += token + " ";
            }
        }
        // Ensures that all data in the stack has been appended to postfix for it to be passed to solve()
        while (!infixes.isEmpty())
        {
            postfix += infixes.pop() + " ";
        }
        // For DefInput, current output should be: "2 4 3 + * -2 + 3 + 3 - 5 + 7 +"
        System.out.println("PostFix: " + postfix);
        return postfix;
//    Done// Algorithm to convert infix string to postfix string
        //
//    Done// create an empty string for postfix expression
//    Done// create a stack of strings
//    Done// tokenize the infix expression with a delimiter of space
        //
//    Done// while tokenizer has more tokens
//    Done//   token = get next token from tokenizer
        //
//    Done//   if token is an open parenthesis
//    Done//   push token on stack
        //
//    Done//   else if token is a close parenthesis
//    Done//   operator = pop top from stack
//    Done//   while operator doesn't equal open parenthesis
//    Done//       append operator and a single space to postfix expression
//    Done//       operator = pop top from stack
        //
//    Done//   else if token is an operator
//    Done//   while stack is not empty and precedence of token is less than top of stack (peek)
//    Done//     pop top from stack and append it and single space to postfix expression
//    Done//   after loop, push operator token onto stack
        //
//    Done//   else
//    Done//   append token and single space to postfix expression
        //
//    Done// while stack is not empty
//    Done//   pop and append operator and single space to postfix expression
        //
//    Done//  return postfix expression
    }

    /**
     * Returns the answer of the mathematical expression after having been
     * arranged into PostFix.
     * Uses a Stack and Tokenizer to store and handle the PostFix calculations.
     * The Stack stores the result of each operation as well as the final answer.
     * The Tokenizer reads each token popped from the stack. Determines if
     * popped token is either an Operator or an Operand. Handles it accordingly.
     * @param postfix The Infix expression arranged into PostFix.
     * @return        The answer of the PostFix expression.
     */
    public static double solve(String postfix)
    {
        double operand1, operand2, result, answer;
        String operator;
        AlexStackClass<Double> postfixes = new AlexStackClass<Double>();
        StringTokenizer tokenizer = new StringTokenizer(postfix, " ");

        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            if (operatorCheck(token))
            {
                operator = token;
                operand2 = postfixes.pop();
                operand1 = postfixes.pop();
                // result utilizes switch statement as defined in operationSelection()
                result = operationSelection(operator, operand2, operand1);
                postfixes.push(result);
            }
            else
            {
                postfixes.push(Double.valueOf(token));
            }
        }
        answer = postfixes.pop();
        // For DefInput, output for answer should be: 26.0
        return answer;
//    Done// Algorithm to solve postfix expression
        //
//    Done// create a stack of doubles
//    Done// tokenize the postfix expression with a delimiter of space
        //
//    Done// while tokenizer has more tokens
//    Done//   token = get next token from tokenizer
        //
//    Done//   if token is an operator
//    Done//     operand b = pop top of stack
//    Done//     operand a = pop top of stack
//    Done//     result = a operator b
//    Done//     push result onto stack
        //
//    Done//   else
//    Done//     convert token to double and push onto stack
        //
//    Done// there should only be 1 element left in the stack now
//    Done// if there is more than 1 element or the stack is empty, you have a bug
//    Done// answer = pop off top of stack
        //
//    Done// return answer
    }

    /**
     * Returns a Boolean to ensure that the Operator token being passed
     * in is a valid Operator and is one that is handled by the
     * method "operationSelection()".
     * In short, returns true if Operator is one of the following: ^ * / + -
     * NOTE: No tokens with numerical values, such as ints, doubles, longs, 
     * or floats, should be getting passed into this method.
     * @param token The Operator being passed in.
     * @return      A Boolean.
     */
    private static boolean operatorCheck(String token)
    {
        return (token.equals("^") || // Exponents
                token.equals("*") || // Multiplication
                token.equals("/") || // Division
                token.equals("+") || // Addition
                token.equals("-") ); // Subtraction
    }

    /**
     * Returns the result of the Operation.
     * Uses a switch-case statement to run the numerical operation that 
     * corresponds to the Operator.
     * @param operator The symbol corresponding to the desired numerical operation.
     * @param operand2 The second numerical value.
     * @param operand1 The first numerical value.
     * @return         The result of the numerical operation.
     */
    private static double operationSelection(String operator,
                                             double operand2,
                                             double operand1)
    {
        double result = 0;
        switch (operator)
        {
            case "^": // Exponents
                result = Math.pow(operand1, operand2);
                break;
            case "*": // Multiplication
                result = operand1 * operand2;
                break;
            case "/": // Division
                result = operand1 / operand2;
                break;
            case "+": // Addition
                result = operand1 + operand2;
                break;
            case "-": // Subtraction
                result = operand1 - operand2;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Returns a numerical value based on the concept of Order of Operations
     * in order to determine the precedence of the Operation within the expression.
     * @param operator The Operator being passed in from the expression.
     * @return         The numerical precedence of the operator.
     */
    private static int operationPrecedence(String operator)
    {
        if (operator.equals("^")) // Exponents
            return 3;
        else if (operator.equals("*") || operator.equals("/")) // Multiplication and Division
            return 2;
        else if (operator.equals("+") || operator.equals("-")) // Addition and Subtraction
            return 1;
        else
            return -1;
    }
}
