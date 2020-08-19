package com.kshrd.khracer_api.Repository.Model;

import java.util.Date;

public class UserDRSM {
    private int id;
    private String name;
    private String email;
    private boolean gender;
    private Date dob;
    private double topScore;
    private int playedMatch;
    private boolean isDisabled;
    private Date createAt;
    private String UserImage;

    public UserDRSM() {
    }

    public UserDRSM(int id, String name, String email, boolean gender, Date dob, double topScore, int playedMatch, boolean isDisabled, Date createAt, String userImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.topScore = topScore;
        this.playedMatch = playedMatch;
        this.isDisabled = isDisabled;
        this.createAt = createAt;
        UserImage = userImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getTopScore() {
        return topScore;
    }

    public void setTopScore(double topScore) {
        this.topScore = topScore;
    }

    public int getPlayedMatch() {
        return playedMatch;
    }

    public void setPlayedMatch(int playedMatch) {
        this.playedMatch = playedMatch;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }
}
