package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogExerciseDetails {
    @Expose
    @SerializedName("userExerciseDetail")
    private UserLogExerciseDetail userexercisedetail;

    public UserLogExerciseDetail getUserexercisedetail() {
        return userexercisedetail;
    }
}
