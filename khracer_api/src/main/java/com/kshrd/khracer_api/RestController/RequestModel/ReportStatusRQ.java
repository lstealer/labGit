package com.kshrd.khracer_api.RestController.RequestModel;

public class ReportStatusRQ {
    private int id;
    private boolean isRead;
    private boolean isDisabled;

    public ReportStatusRQ() {
    }

    public ReportStatusRQ(int id, boolean isRead, boolean isDisabled) {
        this.id = id;
        this.isRead = isRead;
        this.isDisabled = isDisabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
