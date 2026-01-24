package com.akyro;

import java.util.Scanner;

public class UserInterface {

    // ===== Color Constants =====
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    private Scanner scanner;
    private final AnalyticsEngine engine;
    private final WorkoutStorage storage;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.engine = new AnalyticsEngine();
        this.storage = new WorkoutStorage();

    }

    public void start() {

        System.out.print(CYAN + "Name of Workout: " + RESET);
        String workoutName = scanner.nextLine();

        while (workoutName.isBlank()) {
            System.out.println(RED + "Invalid workout name" + RESET);
            System.out.print(CYAN + "Name of Workout: " + RESET);
            workoutName = scanner.nextLine();
        }

        Workout workout = new Workout(workoutName);

        System.out.println();
        commandList();

        while (true) {
            System.out.println();
            System.out.print(YELLOW + "Command: " + RESET);
            String commandInput = scanner.nextLine();
            commandProcessor(workout, commandInput);
        }
    }

    public void commandProcessor(Workout workout, String commandInput) {

        if (commandInput.equalsIgnoreCase("help")) {
            System.out.println();
            commandList();
            return;
        } else if (commandInput.equalsIgnoreCase("quit")) {
            System.out.println(YELLOW + "Exiting program..." + RESET);
            System.exit(0);
        }

        if (isInteger(commandInput)) {
            int command = Integer.parseInt(commandInput);

            switch (command) {

                case 1:
                    String name = readNonBlankString("Name: ");
                    int sets = readPositiveInteger("Sets: ");
                    int reps = readPositiveInteger("Reps: ");
                    double weight = readNonNegativeDouble("Weight: ");
                    String muscleGroup = readNonBlankString("Muscle Group: ");

                    Exercise exercise = new Exercise(name, sets, reps, weight, muscleGroup);
                    workout.addExercise(exercise);

                    System.out.println(GREEN + "Exercise added" + RESET);
                    break;

                case 2:
                    if (emptyWorkoutErrorMessage(workout)) {
                        return;
                    }
                    System.out.println(CYAN + "\n=== Workout List ===" + RESET);
                    workout.printWorkout();
                    break;

                case 3:
                    if (emptyWorkoutErrorMessage(workout)) {
                        return;
                    }
                    prepareAnalytics(workout);
                    Exercise e = engine.getHighestVolumeExercise();

                    System.out.println(CYAN + "\n=== Workout Summary ===" + RESET);
                    System.out.println("Workout: " + workout.getName());
                    System.out.println("Total Sets: " + workout.totalSets());
                    System.out.println("Total Reps: " + workout.totalReps());
                    System.out.println("Total Volume: " + workout.calculateTotalWorkoutVolume() + " lbs");
                    System.out.println("Highest Volume Exercise: " +
                            GREEN + e.getName() + RESET +
                            " (" + e.calculateTotalVolume() + " lbs)");
                    break;

                case 4:
                    if (emptyWorkoutErrorMessage(workout)) {
                        return;
                    }
                    prepareAnalytics(workout);
                    showWorkoutAnalytics(workout);
                    break;
                
                case 5: 
                 if (emptyWorkoutErrorMessage(workout)) {
                        return;
                    }
                    if (storage.saveWorkout(workout)) {
                        System.out.println(GREEN + "Workout Saved!" + RESET);
                    } else {
                        System.out.println(RED + "Could not save workout" + RESET);
                    }
                    break;

                default:
                    System.out.println(RED + "Unknown command. Type 'help' to see available options" + RESET);
            }

        } else {
            System.out.println(RED + "Invalid command number. Type 'help' to see valid commands." + RESET);
        }
    }

    private void commandList() {
        System.out.println(CYAN + "=== Commands ===" + RESET);
        System.out.println("1: Add exercise");
        System.out.println("2: List workout");
        System.out.println("3: Print workout summary");
        System.out.println("4: Show workout analytics");
        System.out.println("5: Save workout");
        System.out.println("help - List commands again");
        System.out.println("quit - Quit the program");
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String readNonBlankString(String prompt) {
        while (true) {
            System.out.print(YELLOW + prompt + RESET);
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println(RED + "Please enter a non blank name" + RESET);
                continue;
            }
            return input;
        }
    }

    private int readPositiveInteger(String prompt) {
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

    private double readNonNegativeDouble(String prompt) {
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

    private boolean emptyWorkoutErrorMessage(Workout workout) {
        if (workout.size() == 0) {
            System.out.println(RED + "The workout has no exercises added." + RESET);
            return true;
        }
        return false;
    }

    private void prepareAnalytics(Workout workout) {
        engine.calculateVolumeBreakdown(workout);
    }

    private void showWorkoutAnalytics(Workout workout) {

        System.out.println(CYAN + "\n=== Workout Analytics ===" + RESET);

        var top3 = engine.topNExercises(workout, 3);
        System.out.println(YELLOW + "Top 3 Exercises by Volume:" + RESET);
        for (var entry : top3) {
            System.out.println(" - " + entry.getKey().getName() + ": " + formatPercent(entry.getValue()));
        }

        var bottom3 = engine.bottomNExercises(workout, 3);

        System.out.println(YELLOW + "\nBottom 3 Exercises by Volume:" + RESET);

        if (bottom3.isEmpty()) {
            System.out.println(RED + "  Not enough exercises to display the bottom 3." + RESET);
        } else {
            for (var entry : bottom3) {
                System.out.println(" - " + entry.getKey().getName() + ": " + formatPercent(entry.getValue()));
            }
        }

        var ppl = engine.volumePercentageSplit();
        System.out.println(YELLOW + "\nPush / Pull / Legs Split:" + RESET);
        System.out.println(" - Push: " + formatPercent(ppl.get("Push")));
        System.out.println(" - Pull: " + formatPercent(ppl.get("Pull")));
        System.out.println(" - Legs: " + formatPercent(ppl.get("Legs")));

        Exercise highest = engine.getHighestVolumeExercise();
        System.out.println(YELLOW + "\nHighest Volume Exercise:" + RESET);
        System.out.println(" - " + GREEN + highest.getName() + RESET +
                " (" + highest.calculateTotalVolume() + " lbs)");
    }

    private String formatPercent(double value) {
        return String.format("%.2f%%", value * 100);
    }
}
