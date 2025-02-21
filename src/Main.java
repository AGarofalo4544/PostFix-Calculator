import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
    // private static final String DefInput = "2 * ( 4 + 3 + ( 3 - 2 ) ) + -2 + 3 - 3 + 5 + 7";
    // DefInput equals 26
    private static Scanner scan;
    // public static LinkedList<String> expression;
    public static String expression;
    public static LinkedList<Double> values;
    public static LinkedList<String> opates;
    private static Double[] operands;
    private static String[] operators;

    public static void main(String[] args)
    {
        scan = new Scanner(System.in);
        System.out.println();
        myMenu();

        // operands = new Double[]{1.0, 4.0, 5.0};
        // operators = new String[]{"+", "^"};
        // equation(operands, operators);

        // String infix = DefInput;
        // String infix = equation(operands, operators);
        // if (args != null && args.length > 0)
        //     infix = args[0];

        // // Passes the Infix expression to be converted to PostFix.
        // String postfix = PostfixCalculator.infix2postfix(infix);
        // // The converted PostFix expression is now solved for and result is returned.
        // double result = PostfixCalculator.solve(postfix);
        // System.out.println("Answer: " + result);
    }

    /**
     * Menu system.
     * @return 
     */
    private static int myMenu()
    {
        // Menu choices
        System.out.println("1) Operands");
        System.out.println("2) Operators");

        System.out.println("\tMake your selection by entering in the associated number. ");
        int choice = Integer.valueOf(scan.nextLine());
        switch (choice)
        {
            case 1:
                totalNumber();
                break;
        }
        return choice;
    }

    /**
     * Method that accepts User Input for number of Operands to be present in calculation.
     * @return The number of Operands the User wishes to have in their expression.
     */
    // private static LinkedList<String> totalNumber()
    private static String totalNumber()
    {
        int amount = 0;
        System.out.println("How many operands are in your equation? (Max allowed: 26)");
        amount = Integer.valueOf(scan.nextLine());
        return operandAssignment(amount);
    }

    /**
     * Method that asks User to specify the value of each Operand.
     * @param amount The number of Operands the User wishes to select.
     * @return       An array of the selected Operands.
     */
    // private static LinkedList<String> operandAssignment(int amount)
    private static String operandAssignment(int amount)
    {
        values = new LinkedList<Double>();
        operands = new Double[amount];
        try
        {
            for (int i=0; i<operands.length; i++)
            {
                System.out.println("What is the value of operand " + i + " ? ");
                operands[i] = Double.valueOf(scan.nextLine());
                System.out.println(Arrays.toString(operands));
                values.add(operands[i]);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("\"NumberFormatException\" detected.");
            operandAssignment(amount);
        }
        return operatorAssignment(amount);
    }

    /**
     * Method that asks User to specify each Operator.
     * @param operator The number of Operators selected by the User.
     * @return         The Arrays for the selected Operands and Operators.
     */
    // private static LinkedList<String> operatorAssignment(int operator)
    private static String operatorAssignment(int operator)
    {
        // The number of Operators in the expression will be 
        // 1 less than the number of Operands.
        // Thus, the Array for Operators will have this length.
        int ops = operator - 1;
        opates = new LinkedList<String>();
        operators = new String[ops];
        try
        {
            for (int i=0; i<operators.length; i++)
            {
                
                System.out.println("What is the symbol for operator " + i + " ? ");
                System.out.println("Available symbols are the following: ^ * / + - ");
                operators[i] = scan.nextLine();
                // // For some reason, it is not hitting this if clause but instead goes straight to the else clause.
                if (operators[i].equals("^") ||
                        operators[i].equals("*") ||
                        operators[i].equals("/") ||
                        operators[i].equals("+") ||
                        operators[i].equals("-") )
                {
                    // Purpose of printing Operands: 
                    // So User can see where the next inputted Operator will be placed.
                    System.out.println(Arrays.toString(operands));
                    System.out.println(Arrays.toString(operators));
                    opates.add(operators[i]);
                }
                else
                {
                    System.out.println("Please choose an appropriate symbol. ");
                    return operatorAssignment(operator);
                }
            }
        }
        // catch (Exception e)
        // {
        //     System.out.println("\"Exception\" detected.");
        //     scan = new Scanner(System.in);
        //     operatorAssignment(operator);
        // }
        catch (InputMismatchException e)
        {
            System.out.println("\"InputMismatchException\" detected.");
            operatorAssignment(operator);
        }
        // catch (NumberFormatException e)
        // {
        //     System.out.println("\"NumberFormatException\" detected.");
        //     operatorAssignment(operator);
        // }
        return equation(operands, operators);
    }

    /**
     * 
     * @param operands  
     * @param operators 
     * @return          
     */
    private static String equation(Double[] operands, String[] operators)
    {
        System.out.println("--------------------------------");
        System.out.println("Operands:  " + Arrays.toString(operands));
        System.out.println("Operators: " + Arrays.toString(operators));
        int cherry = operands.length -1;
        expression = "";

        for (int i=0; i<operators.length; i++)
        {
            // expression.add(String.valueOf(operands[i]));
            // expression.add(String.valueOf(operators[i]));
            // System.out.println(expression.toString());

            expression += String.valueOf(operands[i]) + " ";
            expression += String.valueOf(operators[i]) + " ";
        }
        // expression.add(String.valueOf(operands[cherry]));
        expression += String.valueOf(operands[cherry]);

        // System.out.println("expression.size(): " + expression.size());
        // System.out.println("expression: " + expression.toString());
        System.out.println("Infix:    " + expression);
        return conversion(expression);
    }

    /**
     * 
     * @param expression 
     * @return           
     */
    private static String conversion(String expression)
    {
        String infix = expression;
        // if (args != null && args.length > 0)
        //     infix = args[0];

        // Passes the Infix expression to be converted to PostFix.
        String postfix = PostfixCalculator.infix2postfix(infix);
        // The converted PostFix expression is now solved for and result is returned.
        double result = PostfixCalculator.solve(postfix);
        System.out.println("Answer:   " + result);
        return "Answer: " + result;
    }
}
