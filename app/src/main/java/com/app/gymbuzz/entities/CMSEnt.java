package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CMSEnt {

    @Expose
    @SerializedName("isDeleted")
    private boolean isdeleted;
    @Expose
    @SerializedName("isActive")
    private boolean isactive;
    @Expose
    @SerializedName("lastUpdatedDate")
    private String lastupdateddate;
    @Expose
    @SerializedName("updatedIp")
    private String updatedip;
    @Expose
    @SerializedName("updatedBy")
    private String updatedby;
    @Expose
    @SerializedName("createdDate")
    private String createddate;
    @Expose
    @SerializedName("createdBy")
    private String createdby;
    @Expose
    @SerializedName("socialMediaLinks")
    private List<Socialmedialinks> socialmedialinks;
    @Expose
    @SerializedName("instagramURL")
    private String instagramurl;
    @Expose
    @SerializedName("youtubeURL")
    private String youtubeurl;
    @Expose
    @SerializedName("twitterURL")
    private String twitterurl;
    @Expose
    @SerializedName("facebookURL")
    private String facebookurl;
    @Expose
    @SerializedName("snapChatURL")
    private String snapChatURL;
    @Expose
    @SerializedName("gymID")
    private String gymid;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("terms")
    private String terms;
    @Expose
    @SerializedName("about")
    private String about;
    @Expose
    @SerializedName("cmsID")
    private int cmsid;

    public boolean getIsdeleted() {
        return isdeleted;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public String getLastupdateddate() {
        return lastupdateddate;
    }

    public String getUpdatedip() {
        return updatedip;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public String getCreateddate() {
        return createddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public List<Socialmedialinks> getSocialmedialinks() {
        return socialmedialinks;
    }

    public String getInstagramurl() {
        return instagramurl;
    }

    public String getYoutubeurl() {
        return youtubeurl;
    }

    public String getTwitterurl() {
        return twitterurl;
    }

    public String getFacebookurl() {
        return facebookurl;
    }

    public String getGymid() {
        return gymid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTerms() {
        return terms;
    }

    public String getAbout() {
        return about;
    }

    public String getSnapChatURL() {
        return snapChatURL;
    }

    public int getCmsid() {
        return cmsid;
    }

    public  class Socialmedialinks {
        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("socialMediaID")
        private int socialmediaid;

        public String getUrl() {
            return url;
        }

        public int getSocialmediaid() {
            return socialmediaid;
        }
    }
}
