package com.app.gymbuzz.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GymDetailModel {

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
    @SerializedName("gymCMS")
    private ArrayList<GymCMS> gymcms;
    @Expose
    @SerializedName("gymImages")
    private ArrayList<GymImages> gymimages;
    @Expose
    @SerializedName("gymQrCode")
    private String gymqrcode;
    @Expose
    @SerializedName("logoPath")
    private String logopath;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("countryCode")
    private int countrycode;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("phoneNumber")
    private String phonenumber;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("gymName")
    private String gymname;
    @Expose
    @SerializedName("gymID")
    private int gymid;

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

    public ArrayList<GymCMS> getGymcms() {
        return gymcms;
    }

    public ArrayList<GymImages> getGymimages() {
        return gymimages;
    }

    public String getGymqrcode() {
        return gymqrcode;
    }

    public String getLogopath() {
        return logopath;
    }

    public String getDescription() {
        return description;
    }

    public int getCountrycode() {
        return countrycode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGymname() {
        return gymname;
    }

    public int getGymid() {
        return gymid;
    }

    public static class GymCMS {
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
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("cmsType")
        private int cmstype;
        @Expose
        @SerializedName("gymID")
        private int gymid;
        @Expose
        @SerializedName("gymCMSID")
        private int gymcmsid;

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

        public String getDescription() {
            return description;
        }

        public int getCmstype() {
            return cmstype;
        }

        public int getGymid() {
            return gymid;
        }

        public int getGymcmsid() {
            return gymcmsid;
        }
    }

    public static class GymImages {
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
        @SerializedName("imagePath")
        private String imagepath;
        @Expose
        @SerializedName("gymId")
        private int gymid;
        @Expose
        @SerializedName("gymImagesId")
        private int gymimagesid;

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

        public String getImagepath() {
            return imagepath;
        }

        public int getGymid() {
            return gymid;
        }

        public int getGymimagesid() {
            return gymimagesid;
        }
    }
}
