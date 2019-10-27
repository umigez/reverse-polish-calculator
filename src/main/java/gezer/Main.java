package gezer;

import gezer.calc.Calculator;
import gezer.calc.NotEnoughNumbersException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Calculator calculator = new Calculator();
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Reverse Polish Calc, please enter input:");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                calculator.processInput(input);
            } catch (NotEnoughNumbersException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(calculator.toString());
        }
    }
}
