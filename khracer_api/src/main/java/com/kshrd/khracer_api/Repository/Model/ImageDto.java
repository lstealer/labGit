package com.kshrd.khracer_api.Repository.Model;

public class ImageDto {
    private int id;
    private String imgpath;

    public ImageDto(){}

    public ImageDto(int id, String imgpath) {
        this.id = id;
        this.imgpath = imgpath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}
