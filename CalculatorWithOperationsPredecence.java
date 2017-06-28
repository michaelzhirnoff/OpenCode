package opencode;

import java.io.*;
import java.util.*;

public class CalculatorWithOperationsPredecence {
    public static void main(String[] args) throws Exception {
        BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
        String consoleInput;

        try {
            System.out.println("Введите ваше выражение для расчёта");
            consoleInput = myReader.readLine();
            consoleInput = RPN(consoleInput);
            System.out.println(calculate(consoleInput));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String RPN(String consoleInput) {
        StringBuilder stringBuilderStack = new StringBuilder("");
        StringBuilder stringBuilderOutput = new StringBuilder("");
        char characterAtIndex;
        char temporaryCharacter;

        for (int i = 0; i < consoleInput.length(); i++) {
            characterAtIndex = consoleInput.charAt(i);
            if (isOperator(characterAtIndex)) {
                while (stringBuilderStack.length() > 0) {
                    temporaryCharacter = stringBuilderStack.substring(stringBuilderStack.length() - 1).charAt(0);
                    if (isOperator(temporaryCharacter) && (operatorPrecedence(characterAtIndex) <= operatorPrecedence(temporaryCharacter))) {
                        stringBuilderOutput.append(" ").append(temporaryCharacter).append(" ");
                        stringBuilderStack.setLength(stringBuilderStack.length() - 1);
                    } else {
                        stringBuilderOutput.append(" ");
                        break;
                    }
                }
                stringBuilderOutput.append(" ");
                stringBuilderStack.append(characterAtIndex);
            } else if ('(' == characterAtIndex) {
                stringBuilderStack.append(characterAtIndex);
            } else if (')' == characterAtIndex) {
                temporaryCharacter = stringBuilderStack.substring(stringBuilderStack.length() - 1).charAt(0);
                while ('(' != temporaryCharacter) {
                    stringBuilderOutput.append(" ").append(temporaryCharacter);
                    stringBuilderStack.setLength(stringBuilderStack.length() - 1);
                    temporaryCharacter = stringBuilderStack.substring(stringBuilderStack.length() - 1).charAt(0);
                }
                stringBuilderStack.setLength(stringBuilderStack.length() - 1);
            } else {
                stringBuilderOutput.append(characterAtIndex);
            }
        }

        while (stringBuilderStack.length() > 0) {
            stringBuilderOutput.append(" ").append(stringBuilderStack.substring(stringBuilderStack.length() - 1));
            stringBuilderStack.setLength(stringBuilderStack.length() - 1);
        }

        return stringBuilderOutput.toString();
    }

    private static boolean isOperator(char c) {
        switch (c) {
            case '-':
            case '+':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    private static byte operatorPrecedence(char precedence) {
        switch (precedence) {
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return 1;
    }

    private static double calculate(String consoleInput) {
        double element1 = 0;
        double element2 = 0;
        String temporaryString;
        ArrayDeque<Double> stack = new ArrayDeque<Double>();
        StringTokenizer myTokenizer = new StringTokenizer(consoleInput);
        while (myTokenizer.hasMoreTokens()) {
            temporaryString = myTokenizer.nextToken().trim();
            if (1 == temporaryString.length() && isOperator(temporaryString.charAt(0))) {
                element2 = stack.pop();
                element1 = stack.pop();
                switch (temporaryString.charAt(0)) {
                    case '+':
                        element1 += element2;
                        break;
                    case '-':
                        element1 -= element2;
                        break;
                    case '/':
                        element1 /= element2;
                        break;
                    case '*':
                        element1 *= element2;
                        break;
                    case '%':
                        element1 %= element2;
                        break;
                    case '^':
                        element1 = Math.pow(element1, element2);
                        break;
                }
                stack.push(element1);
            } else {
                element1 = Double.parseDouble(temporaryString);
                stack.push(element1);
            }
        }

        return stack.pop();
    }
}