package com.akyro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;


public class WorkoutStorage {
    private ObjectMapper mapper;
    private static final Pattern ILLEGAL_FILENAME_CHARS = Pattern.compile("[\\\\/:*?\"<>]");
    private static final String REPLACEMENT_CHAR = "-";
    private static final String DATA_DIR = "data";

    public WorkoutStorage() {
        this.mapper = new ObjectMapper();
    }

   public boolean saveWorkout(Workout workout) {
    if (!createDirectory()) {
        return false;
    }
    String fileName = sanitizeFileName(workout.getName());
    File file = new File(DATA_DIR, fileName);

    try {
        mapper.writeValue(file, workout);
        return true;
    } catch(IOException e) {
        System.err.println("Failed to save workout: " + e.getMessage());
        return false;
    }

   }

   private boolean createDirectory() {
    String dirname = DATA_DIR;
    Path path = Paths.get(dirname);

    try {
       Files.createDirectories(path);
       return true;
    } catch (IOException e) {
        System.err.println("Failed to create data directory: " + e.getMessage());
        return false;
    }
   }
   private String sanitizeFileName(String workoutName) {
    String sanitizedName = ILLEGAL_FILENAME_CHARS.matcher(workoutName).replaceAll(REPLACEMENT_CHAR);
    sanitizedName = sanitizedName.trim();
    sanitizedName = sanitizedName.replaceAll("-+", REPLACEMENT_CHAR); //Remove potential double hyphens
    sanitizedName = sanitizedName.replaceAll("^-+", ""); //Remove leading hyphens
    sanitizedName = sanitizedName.replaceAll("-+$", "");

    if (sanitizedName.isEmpty()) { //If sanitization removes all characters use default filename
        sanitizedName = "Workout";
    }

    return sanitizedName + ".json";
   }
}
