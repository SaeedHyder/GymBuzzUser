package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

public class WorkoutModel {
    @Expose
    @SerializedName("minRep")
    private String minrep;
    @Expose
    @SerializedName("maxRep")
    private String maxrep;
    @Expose
    @SerializedName("minWeight")
    private String minweight;
    @Expose
    @SerializedName("maxWeightByUser")
    private String maxWeightByUser;
    @Expose
    @SerializedName("maxWeight")
    private String maxweight;
    @SerializedName("gymMachineId")
    @Expose
    private Integer gymMachineId;
    @SerializedName("gymId")
    @Expose
    private Integer gymId;
    @SerializedName("machineId")
    @Expose
    private Integer machineId;
    @SerializedName("machineNumber")
    @Expose
    private String machineNumber;
    @SerializedName("qrCode")
    @Expose
    private String qrCode;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("zoneID")
    @Expose
    private Integer zoneID;
    @SerializedName("floorID")
    @Expose
    private Integer floorID;
    @SerializedName("machinePoint")
    @Expose
    private String machinePoint;
    @SerializedName("machineName")
    @Expose
    private String machineName;
    @SerializedName("gym")
    @Expose
    private Object gym;
    @SerializedName("machine")
    @Expose
    private Object machine;
    @SerializedName("exercises")
    @Expose
    private ArrayList<MachineExerciseDetailModel> exercises = null;
    @SerializedName("userExercises")
    @Expose
    private ArrayList<UserExerciseModel> userExercises = null;
    @SerializedName("floor")
    @Expose
    private Object floor;
    @SerializedName("zone")
    @Expose
    private Object zone;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("updatedIp")
    @Expose
    private String updatedIp;
    @SerializedName("lastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;

    public String getMaxWeightByUser() {
        return maxWeightByUser == null ? "0" : maxWeightByUser;
    }

    public Integer getFloorID() {
        return floorID;
    }

    public Integer getGymMachineId() {
        return gymMachineId;
    }

    public Integer getGymId() {
        return gymId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getMachinePoint() {
        return machinePoint;
    }

    public String getMachineName() {
        return machineName;
    }

    public ArrayList<MachineExerciseDetailModel> getExercises() {
        return exercises == null ? new ArrayList<>() : exercises;
    }

    public ArrayList<UserExerciseModel> getUserExercises() {
        return userExercises;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Integer getMinrep() {
        try {
            return Integer.parseInt(minrep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getMaxrep() {
        try {
            return Integer.parseInt(maxrep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getMinweight() {
        try {
            return Integer.parseInt(minweight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getMaxweight() {
        try {
            return Integer.parseInt(maxweight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(gymId).append(machineId).append(qrCode).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WorkoutModel)) {
            return false;
        }
        WorkoutModel rhs = ((WorkoutModel) other);
        return new EqualsBuilder().append(machineId, rhs.machineId).isEquals();
    }

    public static class UserExerciseDetailModel {

        @SerializedName("machineSettingsID")
        @Expose
        private Integer machineSettingsID;
        @SerializedName("reps")
        @Expose
        private String reps;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("setNumber")
        @Expose
        private Integer setNumber;

        public UserExerciseDetailModel(String reps, String weight, Integer setNumber) {
            this.reps = reps;
            this.weight = weight;
            this.setNumber = setNumber;
        }

        public Integer getMachineSettingsID() {
            return machineSettingsID;
        }

        public int getReps() {
            try {
                return Integer.parseInt(reps);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        public void setReps(String reps) {
            this.reps = reps;
        }

        public int getWeight() {
            try {
                return Integer.parseInt(weight);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public Integer getSetNumber() {
            return setNumber;
        }

        public void setSetNumber(Integer setNumber) {
            this.setNumber = setNumber;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(machineSettingsID).toHashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof UserExerciseDetailModel)) {
                return false;
            }
            UserExerciseDetailModel rhs = ((UserExerciseDetailModel) other);
            return new EqualsBuilder().append(machineSettingsID, rhs.machineSettingsID).isEquals();
        }
    }

    public class MachineExerciseDetailModel {
        @SerializedName("machineExerciseID")
        @Expose
        private Integer machineExerciseId;
        @SerializedName("exerciseID")
        @Expose
        private Integer exerciseID;
        @SerializedName("exerciseName")
        @Expose
        private String exerciseName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("bodyPartID")
        @Expose
        private Integer bodyPartID;
        @SerializedName("createdBy")
        @Expose
        private String createdBy;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("updatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("updatedIp")
        @Expose
        private String updatedIp;
        @SerializedName("lastUpdatedDate")
        @Expose
        private String lastUpdatedDate;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("isDeleted")
        @Expose
        private Boolean isDeleted;

        public Integer getMachineExerciseId() {
            return machineExerciseId;
        }

        public Integer getExerciseID() {
            return exerciseID;
        }

        public String getExerciseName() {
            return exerciseName;
        }

        public String getDescription() {
            return description;
        }

        public Integer getBodyPartID() {
            return bodyPartID;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(machineExerciseId).toHashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof MachineExerciseDetailModel)) {
                return false;
            }
            MachineExerciseDetailModel rhs = ((MachineExerciseDetailModel) other);
            return new EqualsBuilder().append(machineExerciseId, rhs.machineExerciseId).isEquals();
        }
    }

    public class UserExerciseModel {
        @SerializedName("userExerciseID")
        @Expose
        private Integer userExerciseID;
        @SerializedName("userID")
        @Expose
        private Integer userID;
        @SerializedName("machineExerciseID")
        @Expose
        private Integer machineExerciseID;
        @SerializedName("timeSpent")
        @Expose
        private String timeSpent;
        @SerializedName("isSaveForFuture")
        @Expose
        private Boolean isSaveForFuture;
        @SerializedName("gymMachineID")
        @Expose
        private Integer gymMachineID;
        @SerializedName("machineExercise")
        @Expose
        private Object machineExercise;
        @SerializedName("userExerciseDetails")
        @Expose
        private Object userExerciseDetails;
        @SerializedName("exerciseDetails")
        @Expose
        private ArrayList<UserExerciseDetailModel> exerciseDetails = null;
        @SerializedName("createdBy")
        @Expose
        private String createdBy;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("updatedBy")
        @Expose
        private Object updatedBy;
        @SerializedName("updatedIp")
        @Expose
        private Object updatedIp;
        @SerializedName("lastUpdatedDate")
        @Expose
        private String lastUpdatedDate;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("isDeleted")
        @Expose
        private Boolean isDeleted;

        public Integer getUserExerciseID() {
            return userExerciseID;
        }

        public Integer getUserID() {
            return userID;
        }

        public Integer getMachineExerciseID() {
            return machineExerciseID;
        }

        public String getTimeSpent() {
            return timeSpent;
        }

        public Boolean getSaveForFuture() {
            return isSaveForFuture;
        }

        public Integer getGymMachineID() {
            return gymMachineID;
        }

        public ArrayList<UserExerciseDetailModel> getExerciseDetails() {
            return exerciseDetails;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(userExerciseID).toHashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof UserExerciseModel)) {
                return false;
            }
            UserExerciseModel rhs = ((UserExerciseModel) other);
            return new EqualsBuilder().append(userExerciseID, rhs.userExerciseID).isEquals();
        }
    }

}
