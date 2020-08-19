package com.kshrd.khracer_api.Repository.Model;

public class KHContentModel {
    private int id;
    private String khContent;

    public KHContentModel(){}

    public KHContentModel(int id, String khContent) {
        this.id = id;
        this.khContent = khContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKhContent() {
        return khContent;
    }

    public void setKhContent(String khContent) {
        this.khContent = khContent;
    }
}
