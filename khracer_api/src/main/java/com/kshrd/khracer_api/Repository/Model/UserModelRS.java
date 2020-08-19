package com.kshrd.khracer_api.Repository.Model;


import java.util.Date;

public class UserModelRS {
    private int id;
    private String name;
    private String email;
    private String genderRS;
    private boolean gender;
    private Date dob;

    public UserModelRS() {
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


    public boolean getGender(){
        return this.gender;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGenderRS(String genderRS){
        this.genderRS=genderRS;
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

    public UserModelRS(int id, String name, String email, boolean gender, Date dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.genderRS=this.gender?"Male":"Female";
    }

    @Override
    public String toString() {
        return "UserModelRS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + genderRS +
                ", dob=" + dob +
                '}';
    }
}
