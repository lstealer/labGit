package com.kshrd.khracer_api.Service.Imp;


import com.kshrd.khracer_api.Repository.IImageRepository;
import com.kshrd.khracer_api.Repository.Model.ImageDto;
import com.kshrd.khracer_api.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private IImageRepository iImageRepository;
    @Autowired
    public void setiImageRepository(IImageRepository iImageRepository) {
        this.iImageRepository = iImageRepository;
    }

    @Override
    public List<ImageDto> getImages() {
        return iImageRepository.getImages();
    }

    @Override
    public ImageDto getImageById(int id) {
        return iImageRepository.getImageById(id);
    }

    @Override
    public boolean postImage(String imgpath) {
        return iImageRepository.postImage(imgpath);
    }
}
