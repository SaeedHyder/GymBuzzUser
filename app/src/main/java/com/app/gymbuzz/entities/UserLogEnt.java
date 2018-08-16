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
    private ArrayList<GetExerciseLogAppModel> getexerciselogappmodel;
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

    public ArrayList<GetExerciseLogAppModel> getGetexerciselogappmodel() {
        return getexerciselogappmodel;
    }

    public Date getExercisedate() {
        return DateHelper.getDateFromString(AppConstants.LOG_DATE_FORMAT,exercisedate);
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

    public  class GetExerciseLogAppModel {
        @Expose
        @SerializedName("userExerciseDetails")
        private ArrayList<UserExerciseDetails> userexercisedetails;
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

        public ArrayList<UserExerciseDetails> getUserexercisedetails() {
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

    public  class UserExerciseDetails {
        @Expose
        @SerializedName("userExerciseDetail")
        private ArrayList<UserExerciseDetail> userexercisedetail;

        public ArrayList<UserExerciseDetail> getUserexercisedetail() {
            return userexercisedetail;
        }
    }

    public  class UserExerciseDetail {
        @Expose
        @SerializedName("setNumber")
        private int setnumber;
        @Expose
        @SerializedName("value")
        private String value;
        @Expose
        @SerializedName("machineSettingsID")
        private int machinesettingsid;

        public int getSetnumber() {
            return setnumber;
        }

        public String getValue() {
            return value;
        }

        public int getMachinesettingsid() {
            return machinesettingsid;
        }
    }
}
