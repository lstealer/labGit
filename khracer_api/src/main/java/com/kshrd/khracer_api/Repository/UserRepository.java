package com.kshrd.khracer_api.Repository;


import com.kshrd.khracer_api.Repository.Model.*;
import com.kshrd.khracer_api.Repository.Provider.HomeProvider;
import com.kshrd.khracer_api.Repository.Provider.UserProvider;
import com.kshrd.khracer_api.RestController.RequestModel.UserModelRM;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    @Select("select * from users u where u.user_email  = #{email}")
    @Results({
            @Result(property = "id", column = "user_id"),
            @Result(property = "username",column = "user_name"),
            @Result(property = "email",column ="user_email"),
            @Result(property = "pwd",column = "password"),
            @Result(property = "roles", column = "user_id", many = @Many(select = "findRolesByUserId"))
    })
    User findByEmail(String email);

    @Select("SELECT r.role_id, r.role_name FROM roles r INNER JOIN role_users ur ON r.role_id= ur.role_id WHERE ur.user_id =#{id}")
    @Result(property = "id",column = "role_id")
    @Result(property = "name",column = "role_name")
    List<Role> findRolesByUserId(int id);



    //mine
    @InsertProvider(value = UserProvider.class,method = "insertUserSQL")
    @Result(column = "User_ID",property = "id")
    boolean insertUser(UserModelRM userModel);
    @InsertProvider(value = UserProvider.class,method = "insertRoleSQL")
    boolean insertRole(int id);
    @SelectProvider(value = UserProvider.class,method = "selectUserIDByEmailSQL")
    int selectUserIDByEmail(String email);

    //TOP player
    @SelectProvider(value = HomeProvider.class,method = "selectTopIDSQL")
    public List<Integer> selectTopID(int limit);
    @Select("select m.match_wpm,m.accuracy,m.match_date,u.user_name from match_history m inner join users u ON u.user_id=m.user_id where u.user_id=#{id} order by match_wpm desc,accuracy desc  limit 1;")
    @Result(property = "userName",column = "user_name")
    @Result(property = "userTopScore",column = "match_wpm")
    @Result(property = "userAccuracy",column = "accuracy")
    @Result(property = "matchDate",column = "match_date")
    public UserRankModel selectTopUserDetail(int id);

    @SelectProvider(value = UserProvider.class,method = "selectUserRegisterDetailSQL")
    @Result(property = "name",column = "user_name")
    @Result(property = "id",column = "user_id")
    @Result(property = "email",column = "user_email")
    @Result(property = "gender",column = "user_gender")
    @Result(property = "dob",column = "user_dob")
    UserModelRS selectUserRegisterDetail(String name);

    @Select("SELECT COUNT(*) FROM users")
    int getAllUserCount();

    @Select("SELECT * FROM users LIMIT #{limit} OFFSET #{page}")
    @Result(property = "id",column = "user_id")
    @Result(property = "name",column = "user_name")
    @Result(property = "email",column = "user_email")
    @Result(property = "gender",column = "user_gender")
    @Result(property = "dob",column = "user_dob")
    @Result(property = "topScore", column="user_top_score")
    @Result(property = "playedMatch",column = "user_played_count")
    @Result(property = "isDisabled",column = "is_disabled")
    @Result(property = "createAt",column = "created_at")
    @Result(property = "UserImage",column = "user_image")
    List<UserDRSM> selectAllUser(int limit, int page);

    @Select("SELECT user_id FROM users WHERE user_id=#{id}")
    public String selectUserId(int id);

}
