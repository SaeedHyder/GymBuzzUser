package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserModel {


    @Expose
    @SerializedName("weight")
    public String weight;
    @Expose
    @SerializedName("height")
    public String height;
    @Expose
    @SerializedName("isVerified")
    public boolean isverified;
    @Expose
    @SerializedName("roleID")
    public int roleid;
    @Expose
    @SerializedName("profileImagePath")
    public String profileimagepath;
    @Expose
    @SerializedName("zipCode")
    public String zipcode;
    @Expose
    @SerializedName("city")
    public String city;
    @Expose
    @SerializedName("address")
    public String address;
    @Expose
    @SerializedName("countryCode")
    public int countrycode;
    @Expose
    @SerializedName("phoneNumber")
    public String phonenumber;
    @Expose
    @SerializedName("email")
    public String email;
    @Expose
    @SerializedName("fullName")
    public String fullname;
    @Expose
    @SerializedName("lastName")
    public String lastname;
    @Expose
    @SerializedName("firstName")
    public String firstname;
    @Expose
    @SerializedName("userId")
    public int userid;
    @Expose
    @SerializedName("authToken")
    public String authtoken;
    @Expose
    @SerializedName("userSettings")
    private ArrayList<UserSettings> usersettings;


    public ArrayList<UserSettings> getUsersettings() {
        return usersettings;
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

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public boolean isIsverified() {
        return isverified;
    }

    public class UserSettings {
        @Expose
        @SerializedName("userID")
        private int userid;
        @Expose
        @SerializedName("settingValue")
        private String settingvalue;
        @Expose
        @SerializedName("settingName")
        private String settingname;
        @Expose
        @SerializedName("userSettingID")
        private int usersettingid;

        public int getUserid() {
            return userid;
        }

        public String getSettingvalue() {
            return settingvalue;
        }

        public String getSettingname() {
            return settingname;
        }

        public int getUsersettingid() {
            return usersettingid;
        }
    }
}
