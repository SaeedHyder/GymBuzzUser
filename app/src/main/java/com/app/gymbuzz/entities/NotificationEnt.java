package com.app.gymbuzz.entities;

import com.app.gymbuzz.helpers.DateHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NotificationEnt {

    @Expose
    @SerializedName("receiverEmail")
    private String receiveremail;
    @Expose
    @SerializedName("receiverName")
    private String receivername;
    @Expose
    @SerializedName("senderEmail")
    private String senderemail;
    @Expose
    @SerializedName("senderName")
    private String sendername;
    @Expose
    @SerializedName("notification")
    private Notification notification;

    public String getReceiveremail() {
        return receiveremail;
    }

    public String getReceivername() {
        return receivername;
    }

    public String getSenderemail() {
        return senderemail;
    }

    public String getSendername() {
        return sendername;
    }

    public Notification getNotification() {
        return notification;
    }

    public static class Notification {
        @Expose
        @SerializedName("isDeleted")
        private boolean isdeleted;
        @Expose
        @SerializedName("isActive")
        private boolean isactive;
        @Expose
        @SerializedName("status")
        private int status;
        @Expose
        @SerializedName("actionType")
        private int actiontype;
        @Expose
        @SerializedName("actionID")
        private int actionid;
        @Expose
        @SerializedName("message")
        private String message;
        @Expose
        @SerializedName("recevierID")
        private int recevierid;
        @Expose
        @SerializedName("senderID")
        private int senderid;
        @Expose
        @SerializedName("notificationsID")
        private int notificationsid;
        @Expose
        @SerializedName("createdDate")
        private String createdDate;

        public boolean getIsdeleted() {
            return isdeleted;
        }

        public boolean getIsactive() {
            return isactive;
        }

        public int getStatus() {
            return status;
        }

        public int getActiontype() {
            return actiontype;
        }

        public int getActionid() {
            return actionid;
        }

        public String getMessage() {
            return message;
        }

        public int getRecevierid() {
            return recevierid;
        }

        public int getSenderid() {
            return senderid;
        }

        public int getNotificationsid() {
            return notificationsid;
        }

        public String getCreatedDate() {
            return DateHelper.getLocalTimeDateRequst(createdDate);
        }
    }
}