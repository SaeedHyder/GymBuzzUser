package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserExerciseDetails {
    @Expose
    @SerializedName("userExerciseDetail")
    private UserExerciseDetail userexercisedetail;

    public UserExerciseDetail getUserexercisedetail() {
        return userexercisedetail;
    }
}
