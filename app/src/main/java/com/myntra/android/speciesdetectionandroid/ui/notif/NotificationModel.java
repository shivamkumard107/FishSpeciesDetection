package com.myntra.android.speciesdetectionandroid.ui.notif;

public class NotificationModel {
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getNotifType() {
        return notifType;
    }

    private String timestamp;
    private String notifType; //warning, alert or good
}
