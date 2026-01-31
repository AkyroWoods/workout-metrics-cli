package com.akyro;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

public class WorkoutEditorTest {
    @Test
    public void addExerciseToWorkoutTest() {
        String fakeInput = String.join("\n",
                "Squat",
                "5",
                "5",
                "225",
                "quads");
        Scanner scanner = new Scanner(fakeInput);
        InputReader reader = new InputReader(scanner);
        WorkoutEditor editor = new WorkoutEditor(reader);
        Workout workout = new Workout("Test");

        editor.addExerciseToWorkout(workout);
        assertEquals(1, workout.size());

        Exercise e = workout.getExercises().get(0);
        assertEquals("Squat", e.getName());
        assertEquals(5, e.getSets());
        assertEquals(5, e.getReps());
        assertEquals(225, e.getWeight(), .001);
        assertEquals("quads", e.getMuscleGroup());
    }

    @Test
    public void editExerciseTest() {
        Workout workout = new Workout("Test");
        Exercise exercise = new Exercise("Leg Press",
                3, 12, 135, "legs");
        workout.addExercise(exercise);
        String fakeInput = String.join("\n",
                "1",
            //Edit name
                "1",
                "Squat",
            //Edit sets
                "2",
                "5",
            //Edit reps
                "3",
                "5",
            //Edit weight
                "4",
                "225",
            //Edit Muscle group
                "5",
                "quads",
            // Done editing
                "6"
    );
        Scanner scanner = new Scanner(fakeInput);
        InputReader reader = new InputReader(scanner);
        WorkoutEditor editor = new WorkoutEditor(reader);
        editor.editExercise(workout);
        Exercise e = workout.getExercises().get(0);
        assertEquals("Squat", e.getName());
        assertEquals(5, e.getSets());
        assertEquals(5, e.getReps());
        assertEquals(225, e.getWeight(), .001);
        assertEquals("quads", e.getMuscleGroup());
    }
}
