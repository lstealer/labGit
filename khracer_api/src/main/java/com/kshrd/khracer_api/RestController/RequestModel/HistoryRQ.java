package com.kshrd.khracer_api.RestController.RequestModel;

public class HistoryRQ {
    private int userId;
    private int contentId;
    private double wpm;
    private int rank;
    private double accuracy;

    public HistoryRQ(){}
    public HistoryRQ(int userId, int contentId, double wpm, int rank, double accuracy) {
        this.userId = userId;
        this.contentId = contentId;
        this.wpm = wpm;
        this.rank = rank;
        this.accuracy = accuracy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public double getWpm() {
        return wpm;
    }

    public void setWpm(double wpm) {
        this.wpm = wpm;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
