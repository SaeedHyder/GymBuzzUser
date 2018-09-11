package com.app.gymbuzz.entities;

import java.util.ArrayList;

public class WorkoutServerModel {
    public static class WorkoutServerWrapper{
        ArrayList<WorkoutServerModel> machineExercises;

        public WorkoutServerWrapper(ArrayList<WorkoutServerModel> machineExercises) {
            this.machineExercises = machineExercises;
        }
    }
    private int gymMachineID;
    private boolean isSaveForFuture;
    private String timeSpent;
    private int machineExerciseID;
    private ArrayList<WorkoutModel.UserExerciseDetailModel> userExerciseDetails;

    private WorkoutServerModel(Builder builder) {
        gymMachineID = builder.gymMachineID;
        isSaveForFuture = builder.isSaveForFuture;
        timeSpent = builder.timeSpent;
        machineExerciseID = builder.machineExerciseID;
        userExerciseDetails = builder.userExerciseDetails;
    }

    public static final class Builder {
        private int gymMachineID;
        private boolean isSaveForFuture;
        private String timeSpent;
        private int machineExerciseID;
        private ArrayList<WorkoutModel.UserExerciseDetailModel> userExerciseDetails;

        public Builder() {
        }

        public Builder gymMachineID(int val) {
            gymMachineID = val;
            return this;
        }

        public Builder isSaveForFuture(boolean val) {
            isSaveForFuture = val;
            return this;
        }

        public Builder timeSpent(String val) {
            timeSpent = val;
            return this;
        }

        public Builder machineExerciseID(int val) {
            machineExerciseID = val;
            return this;
        }

        public Builder userExerciseDetails(ArrayList<WorkoutModel.UserExerciseDetailModel> val) {
            userExerciseDetails = val;
            return this;
        }

        public WorkoutServerModel build() {
            return new WorkoutServerModel(this);
        }
    }
}
