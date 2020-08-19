package com.kshrd.khracer_api.Repository;


import com.kshrd.khracer_api.Repository.Model.ImageDto;
import com.kshrd.khracer_api.Repository.Provider.ImageProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository {
    @InsertProvider(value = ImageProvider.class, method = "postImage")
    Boolean postImage(String imgpath);
    @SelectProvider(value = ImageProvider.class, method = "getImageById")
    @Result(property = "id",column = "image_id")
    @Result(property = "imgpath",column = "image_path")
    ImageDto getImageById(int id);
    @SelectProvider(value = ImageProvider.class, method = "getImagesSQL")
    @Result(property = "id",column = "image_id")
    @Result(property = "imgpath",column = "image_path")
    List<ImageDto> getImages();
}
