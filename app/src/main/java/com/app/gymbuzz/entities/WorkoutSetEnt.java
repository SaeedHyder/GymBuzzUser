package com.app.gymbuzz.entities;

public class WorkoutSetEnt {
    private int minWeight = 0;
    private int maxWeight = 0;
    private int minReps = 0;
    private int maxReps = 0;
    private int initialReps = 0;
    private int initialWeight = 0;

    public WorkoutSetEnt(int minWeight, int maxWeight, int minReps, int maxReps, int initialReps, int initialWeight) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.minReps = minReps;
        this.maxReps = maxReps;
        this.initialReps = initialReps;
        this.initialWeight = initialWeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public int getMinReps() {
        return minReps;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(int maxReps) {
        this.maxReps = maxReps;
    }

    public int getInitialReps() {
        return initialReps;
    }

    public void setInitialReps(int initialReps) {
        this.initialReps = initialReps;
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(int initialWeight) {
        this.initialWeight = initialWeight;
    }
}
