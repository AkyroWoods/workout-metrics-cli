package com.akyro;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readMenuChoice(String prompt, int min, int max) {
        while (true) {
            int input = readPositiveInteger(prompt);
            if (input >= min && input <= max) {
                return input;
            }
            System.out.println(RED + "Please enter a number betwen " + min + " and " + max + RESET);
        }
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String readNonBlankString(String prompt) {
        while (true) {
            System.out.print(YELLOW + prompt + RESET);
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println(RED + "Please enter a non blank name" + RESET);
                continue;
            }
            if (!input.matches(".*[a-zA-Z].*")) {
                System.out.println(RED + "Exercise name must contain at least one letter" + RESET);
                continue;
            }
            return input;
        }
    }

    public int readPositiveInteger(String prompt) {
        while (true) {
            System.out.print(YELLOW + prompt + RESET);
            String input = scanner.nextLine();

            if (!isInteger(input)) {
                System.out.println(RED + "Please enter a whole number" + RESET);
                continue;
            }

            int value = Integer.parseInt(input);

            if (value < 1) {
                System.out.println(RED + "Please enter a positive number" + RESET);
                continue;
            }
            return value;
        }
    }

    public double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(YELLOW + prompt + RESET);
            String input = scanner.nextLine();

            try {
                double weight = Double.parseDouble(input);
                if (weight < 0) {
                    System.out.println(RED + "Please enter a non negative number" + RESET);
                    continue;
                }
                return weight;

            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a number" + RESET);
            }
        }
    }
}
