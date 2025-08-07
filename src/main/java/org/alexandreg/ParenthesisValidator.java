package org.alexandreg;

import java.util.Scanner;
import java.util.Stack;

public class ParenthesisValidator {
    //This program has the goal of verifying if the parenthesis (and others symbols e.g({}, []) are correctly balanced in an expression.
    //I'm using stack to perform it.
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String expression;

        while (true) {
            System.out.print("\nEnter expression to validate (or type 'x' to quit): ");
            expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("x") || expression.isBlank()) {
                System.out.println("Exiting ParenthesisValidator. Bye!");
                break;
            }
            boolean valid = isValid(expression);
            if(valid){
                System.out.println("✅ The expression is balanced!");
            }else {
                System.out.println("❌ The expression isn't balanced.");
            }
        }
        scanner.close();
    }

    private static boolean isValid(String expression) {
        Stack<Character> stackCharacter = new Stack<>();
        Stack<Integer> stackPosition = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            //if it's a opening symbol it stacks
            if (ch == '(' || ch == '{' || ch == '[') {
                stackCharacter.push(ch);
                stackPosition.push(i);

            //if it's a closure symbol verifies the match
            } else if (ch == ')' || ch == '}' || ch == ']') {
                //no matching opening symbol
                if (stackCharacter.isEmpty()) {
                    System.out.printf("Error: '%c' at index %d has no matching opening.%n", ch, i);
                    return false;
                }
                char open = stackCharacter.pop();
                int position = stackPosition.pop();

                //the opening symbol doesn't match the closure symbol
                if ((ch == ')' && open != '(') ||
                        (ch == '}' && open != '{') ||
                        (ch == ']' && open != '[')) {
                    System.out.printf(
                            "Mismatch: '%c' at index %d does not match '%c' at index %d.%n",
                            ch, i, open, position
                    );
                    return false;
                }else {
                    continue;
                }
            }
        }

        if(!stackCharacter.isEmpty()){
            char open = stackCharacter.pop();
            int position = stackPosition.pop();
            System.out.printf("Error: Opening '%c' at index %d was never closed.%n", open, position);
            return false;
        }

        return true;
    }

}