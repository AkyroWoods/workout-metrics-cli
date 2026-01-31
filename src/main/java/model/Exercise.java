package com.akyro;

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private double weight;
    private String muscleGroup;

    public Exercise() {

    }

    public Exercise(String name, int sets, int reps, double weight, String muscleGroup)
            throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Exercise name cannot be blank");
        }
        if (sets < 1) {
            throw new IllegalArgumentException("Sets cannot be less than 1");
        }
        if (reps < 1) {
            throw new IllegalArgumentException("Reps cannot be less than 1");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if (muscleGroup == null || muscleGroup.isBlank()) {
            throw new IllegalArgumentException("Muscle group cannot be blank");
        }
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.muscleGroup = muscleGroup.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Exercise name cannot be blank");
        }
        this.name = name;
    }

    public void setSets(int sets) {
        if (sets < 1) {
            throw new IllegalArgumentException("Sets must be at least 1");
        }
        this.sets = sets;
    }

    public void setReps(int reps) {
        if (reps < 1) {
            throw new IllegalArgumentException("Reps must be at least 1");
        }
        this.reps = reps;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public void setMuscleGroup(String muscleGroup) {
        if (muscleGroup == null || muscleGroup.isBlank()) {
            throw new IllegalArgumentException("Muscle group cannot be blank");
        }
        this.muscleGroup = muscleGroup.toLowerCase();
    }

    public double calculateTotalVolume() {
        return weight * (sets * reps);
    }

    public String classifyExercise() {
        String muscleGroup = this.muscleGroup;

        return switch (muscleGroup) {
            case "chest", "shoulders", "triceps" -> "Push";
            case "lats", "biceps", "erectors", "back", "traps", "forearms", "pull" -> "Pull";
            case "quads", "hamstrings", "calves", "glutes", "legs", "adductors", "abductors" -> "Legs";
            default -> "Other";
        };

    }

    @Override
    public String toString() {
        return name + " - " + "(" + sets + "x" + reps + " @ " + FormatUtils.formatNumber(weight) + " lbs)";
    }

}
