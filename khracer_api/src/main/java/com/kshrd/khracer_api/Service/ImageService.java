package com.kshrd.khracer_api.Service;


import com.kshrd.khracer_api.Repository.Model.ImageDto;

import java.util.List;

public interface ImageService {
    List<ImageDto> getImages();
    ImageDto getImageById(int id);
    boolean postImage(String imgpath);
}
