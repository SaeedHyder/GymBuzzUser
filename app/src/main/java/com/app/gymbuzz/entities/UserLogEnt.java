package com.app.gymbuzz.entities;

import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.helpers.DateHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLogEnt {

    @Expose
    @SerializedName("getExerciseLogAppModel")
    private List<GetExerciseLogAppModel> getexerciselogappmodel;
    @Expose
    @SerializedName("exerciseDate")
    private String exercisedate;
    @Expose
    @SerializedName("bodyPartImage")
    private String bodypartimage;
    @Expose
    @SerializedName("bodyPartName")
    private String bodypartname;
    @Expose
    @SerializedName("bodyPartID")
    private int bodypartid;

    public List<GetExerciseLogAppModel> getGetexerciselogappmodel() {
        return getexerciselogappmodel;
    }
    public Date getExerciseDateInDateFormat() {
        return DateHelper.getDateFromString(AppConstants.LOG_DATE_FORMAT,exercisedate);
    }
    public String getExerciseDate() {
        return exercisedate;
    }

    public String getExercisedate() {
        return exercisedate;
    }

    public String getBodypartimage() {
        return bodypartimage;
    }

    public String getBodypartname() {
        return bodypartname;
    }

    public int getBodypartid() {
        return bodypartid;
    }





}

