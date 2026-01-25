package com.akyro;

import java.util.ArrayList;

public class Workout {
    private ArrayList<Exercise> exercises;
    private String name;

    public Workout () {
        
    }
    public Workout(String name) {
        this.name = name;
        this.exercises = new ArrayList<>();
    }
    

    public String getName() {
        return name;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }
    public double calculateTotalWorkoutVolume() {
        double totalVolume = 0;
        for (Exercise e: exercises) {
            totalVolume += e.calculateTotalVolume();
        }
        return totalVolume;
    }

    public void printWorkout() {
        int exerciseCounter = 1;
        for (Exercise e: exercises) {
            System.out.println(exerciseCounter + ". " + e.toString());
            exerciseCounter++;
        }
    }
    public int totalSets() {
        int sets = 0;
        for (Exercise e: exercises) {
            sets += e.getSets();
        }
        return sets;
    }
    
    public int totalReps() {
        int reps = 0;
        for (Exercise e: exercises) {
            reps += e.getReps();
        }
        return reps;
    }
    

    public int size() {
        return exercises.size();
    }


    
}
