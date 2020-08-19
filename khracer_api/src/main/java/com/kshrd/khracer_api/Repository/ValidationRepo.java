package com.kshrd.khracer_api.Repository;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepo {
    @Select("SELECT user_name FROM Users WHERE user_name=#{name}")
    public String checkUserNameAvailable(String name);

    @Select("SELECT user_email FROM Users WHERE user_email=#{email}")
    public String checkEmailAvailable(String email);
}
