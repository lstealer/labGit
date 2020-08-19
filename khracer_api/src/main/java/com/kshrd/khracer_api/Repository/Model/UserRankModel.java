package com.kshrd.khracer_api.Repository.Model;

import java.util.Date;

public class UserRankModel {
    private String userName;
    private double userTopScore;
    private double userAccuracy;
    private Date matchDate;

    public UserRankModel() {
    }

    public UserRankModel(String userName, double userTopScore, double userAccuracy, Date matchDate) {
        this.userName = userName;
        this.userTopScore = userTopScore;
        this.userAccuracy = userAccuracy;
        this.matchDate = matchDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUserTopScore() {
        return userTopScore;
    }

    public void setUserTopScore(double userTopScore) {
        this.userTopScore = userTopScore;
    }

    public double getUserAccuracy() {
        return userAccuracy;
    }

    public void setUserAccuracy(double userAccuracy) {
        this.userAccuracy = userAccuracy;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
}
