package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogExerciseDetail {
    @Expose
    @SerializedName("setNumber")
    private int setnumber;
    @Expose
    @SerializedName("weight")
    private String weight;
    @Expose
    @SerializedName("reps")
    private String reps;
    @Expose
    @SerializedName("machineSettingsID")
    private int machinesettingsid;

    public int getSetnumber() {
        return setnumber;
    }

    public String getWeight() {
        return weight;
    }

    public String getReps() {
        return reps;
    }

    public int getMachinesettingsid() {
        return machinesettingsid;
    }
}
