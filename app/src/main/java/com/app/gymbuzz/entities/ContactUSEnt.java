package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUSEnt {

    @Expose
    @SerializedName("email")
    private String email;
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

    public String getEmail() {
        return email;
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
}
