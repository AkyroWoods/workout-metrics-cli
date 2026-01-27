package com.akyro;

import java.util.List;

public class WorkoutComparison {
    private double volumeA;
    private double volumeB;
    private double volumeDifference;

    private List<String> uniqueToA;
    private List<String> uniqueToB;
    private List<String> commonExercises;

    public WorkoutComparison(double volumeA, double volumeB, double volumeDifference,
        List<String> uniqueToA, List<String> uniqueToB, List<String> commonExercises)
    {
        this.volumeA = volumeA;
        this.volumeB = volumeB;
        this.volumeDifference = volumeDifference;
        this.uniqueToA = uniqueToA;
        this.uniqueToB = uniqueToB;
        this.commonExercises = commonExercises;
    
    }

    public double getVolumeA() {
        return volumeA;
    }

    public void setVolumeA(double volumeA) {
        this.volumeA = volumeA;
    }

    public double getVolumeB() {
        return volumeB;
    }

    public void setVolumeB(double volumeB) {
        this.volumeB = volumeB;
    }

    public double getVolumeDifference() {
        return volumeDifference;
    }

    public void setVolumeDifference(double volumeDifference) {
        this.volumeDifference = volumeDifference;
    }

    public List<String> getUniqueToA() {
        return uniqueToA;
    }

    public void setUniqueToA(List<String> uniqueToA) {
        this.uniqueToA = uniqueToA;
    }

    public List<String> getUniqueToB() {
        return uniqueToB;
    }

    public void setUniqueToB(List<String> uniqueToB) {
        this.uniqueToB = uniqueToB;
    }

    public List<String> getCommonExercises() {
        return commonExercises;
    }

    public void setCommonExercises(List<String> commonExercises) {
        this.commonExercises = commonExercises;
    }
    

}
