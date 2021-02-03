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

    public NotificationModel(String title, String desc, String timestamp, String notifType) {
        this.title = title;
        this.desc = desc;
        this.timestamp = timestamp;
        this.notifType = notifType;
    }

    private String timestamp;
    private String notifType; //warning, alert or good
}
