package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetExerciseLogAppModel {
    @Expose
    @SerializedName("userExerciseDetails")
    private List<UserExerciseDetails> userexercisedetails;
    @Expose
    @SerializedName("exerciseName")
    private String exercisename;
    @Expose
    @SerializedName("gymMachineID")
    private int gymmachineid;
    @Expose
    @SerializedName("isSaveForFuture")
    private boolean issaveforfuture;
    @Expose
    @SerializedName("timeSpent")
    private String timespent;
    @Expose
    @SerializedName("machineExerciseID")
    private int machineexerciseid;
    @Expose
    @SerializedName("userExerciseID")
    private int userexerciseid;

    public List<UserExerciseDetails> getUserexercisedetails() {
        return userexercisedetails;
    }

    public String getExercisename() {
        return exercisename;
    }

    public int getGymmachineid() {
        return gymmachineid;
    }

    public boolean getIssaveforfuture() {
        return issaveforfuture;
    }

    public String getTimespent() {
        return timespent;
    }

    public int getMachineexerciseid() {
        return machineexerciseid;
    }

    public int getUserexerciseid() {
        return userexerciseid;
    }
}
