package com.kshrd.khracer_api.Repository.Model;

import java.util.Date;

public class UserHistoryRS {
    private int id;
    private double wpm;
    private double accuracy;
    private int rank;
    private Date date;

    public UserHistoryRS(){}

    public UserHistoryRS(int id, double wpm, double accuracy, int rank, Date date) {
        this.id = id;
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.rank = rank;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWpm() {
        return wpm;
    }

    public void setWpm(double wpm) {
        this.wpm = wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
