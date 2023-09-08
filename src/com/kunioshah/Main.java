package com.kunioshah;
import java.lang.Math;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please enter your expression as Number+Expression+Number, e.g. 2*2 or 2^3 or 8root3. Type exit to leave.");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            input = input.replaceAll("\\s", "");
            if (input.equals("exit")) {
                System.exit(0);
                scanner.close();
            }

            double output = 0;
            boolean invalidInput = false;

            if (input.contains("+")) {
                output = Operation.PLUS.Calculate(input);
            } else if (input.contains("-")) {
                output = Operation.MINUS.Calculate(input);
            } else if (input.contains("*")) {
                output = Operation.MULTIPLY.Calculate(input);
            } else if (input.contains("/")) {
                output = Operation.DIVIDE.Calculate(input);
            } else if (input.contains("^")) {
                output = Operation.EXPONENT.Calculate(input);
            } else if (input.contains("root")) {
                output = Operation.ROOT.Calculate(input);
            } else if (input.contains("log")) {
                output = Operation.LOG.Calculate(input);
            } else {
                invalidInput = true;
            }

            System.out.println(invalidInput ? "Invalid input, try again. Enter exit to leave." : "Value = " + output);
        }
    }

    enum Operation {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        EXPONENT,
        ROOT,
        LOG;

        private double number1;
        private double number2;

        double Calculate(String input) {
            switch (this) {
                case PLUS:
                    Parse(input, "\\+");
                    return number1 + number2;
                case MINUS:
                    Parse(input, "\\-");
                    return number1 - number2;
                case MULTIPLY:
                    Parse(input, "\\*");
                    return number1 * number2;
                case DIVIDE:
                    Parse(input, "\\/");
                    return number1 / number2;
                case EXPONENT:
                    Parse(input, "\\^");
                    return Math.pow(number1, number2);
                case ROOT:
                    Parse(input, "root");
                    return Math.pow(number1, 1.0 / number2);
                case LOG:
                    Parse(input, "log");
                    return Math.log(number2) / Math.log(number1);
                default:
                    throw new ArithmeticException("Not a valid operator.");
            }
        }

        private void Parse(String input, String operator) {
            String[] values = input.split(operator);
            this.number1 = Double.parseDouble(values[0]);
            this.number2 = Double.parseDouble(values[1]);
        }
    }
}