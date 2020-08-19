package com.kshrd.khracer_api.Repository.Provider;

import org.apache.ibatis.jdbc.SQL;

public class ImageProvider {
    public String getImageById(){
        return new SQL(){{
            SELECT("*");
            FROM("Images");
            WHERE("image_id=#{id}");
        }}.toString();
    }
    public String getImagesSQL(){
        return new SQL(){{
            SELECT("*");
            FROM("Images");
        }}.toString();
    }
    public String postImage(){
        return new SQL(){{
            INSERT_INTO("images");
            VALUES("image_path","#{imgpath}");
        }}.toString();
    }
}
