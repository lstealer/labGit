package com.kshrd.khracer_api.Repository.Provider;

import com.kshrd.khracer_api.Repository.Model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String insertUser(@Param("user") User user){
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("username,email,pwd,status","#{user.username},#{user.email},#{user.pwd},#{user.status}");
        }}.toString();
    }

    //mine
    public String insertRoleSQL(){
        return new SQL(){{
            INSERT_INTO("Role_Users");
            VALUES("User_ID","#{id}");
        }}.toString();
    }
    public  String selectUserIDByEmailSQL(){
        return new SQL(){{
            SELECT("User_ID");
            FROM("users");
            WHERE("user_email= #{email}");
        }}.toString();
    }
    public String insertUserSQL(){
        return new SQL(){{
            INSERT_INTO("Users");
            VALUES("User_Name","#{name}");
            VALUES("User_Email","#{email}");
            VALUES("password","#{password}");
            VALUES("User_gender","#{gender}");
            VALUES("user_dob","#{dob}");
        }}.toString();
    }

    public String selectUserRegisterDetailSQL(){
        return new SQL(){{
            SELECT("*");
            FROM("Users");
            WHERE("User_Name= #{name}");
        }}.toString();
    }
}
