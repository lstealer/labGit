package com.kshrd.khracer_api.Repository.Model;

import java.util.Date;

public class ReportRS {
    private int id;
    private int userId;
    private String name;
    private String email;
    private String reportText;
    private Date date;
    private boolean isRead;

    public ReportRS() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public ReportRS(int id, int userId, String name, String email, String reportText, Date date, boolean isRead) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.reportText = reportText;
        this.date = date;
        this.isRead = isRead;
    }
}
