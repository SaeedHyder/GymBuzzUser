package com.app.gymbuzz.entities;

import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.helpers.DateHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @Expose
    @SerializedName("language")
    private String language;
    @Expose
    @SerializedName("notification")
    private String notification;
    @Expose
    @SerializedName("about")
    private String about;
    @Expose
    @SerializedName("gender")
    private int gender;
    @Expose
    @SerializedName("dob")
    private String dob;
    @Expose
    @SerializedName("weight")
    private String weight;
    @Expose
    @SerializedName("height")
    private String height;
    @Expose
    @SerializedName("isVerified")
    private boolean isverified;
    @Expose
    @SerializedName("roleID")
    private int roleid;
    @Expose
    @SerializedName("profileImagePath")
    private String profileimagepath;
    @Expose
    @SerializedName("zipCode")
    private String zipcode;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("countryCode")
    private int countrycode;
    @Expose
    @SerializedName("phoneNumber")
    private String phonenumber;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("fullName")
    private String fullname;
    @Expose
    @SerializedName("lastName")
    private String lastname;
    @Expose
    @SerializedName("firstName")
    private String firstname;
    @Expose
    @SerializedName("userId")
    private int userid;
    @Expose
    @SerializedName("authToken")
    private String authtoken;
    @Expose
    @SerializedName("gymID")
    private String gymID;
    @Expose
    @SerializedName("gymAssigned")
    private boolean gymAssigned;

    public String getLanguage() {
        return language;
    }

    public String getNotification() {
        return notification;
    }

    public String getAbout() {
        return about;
    }

    public int getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getUserAge() {
        return DateHelper.getAge(AppConstants.LOG_DATE_FORMAT, dob);
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public boolean isVerified() {
        return isverified;
    }

    public int getRoleid() {
        return roleid;
    }

    public String getProfileimagepath() {
        return profileimagepath;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getCountrycode() {
        return countrycode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getUserid() {
        return userid;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public String getGymID() {
        return gymID;
    }

    public boolean isGymAssigned() {
        return gymAssigned;
    }
}
