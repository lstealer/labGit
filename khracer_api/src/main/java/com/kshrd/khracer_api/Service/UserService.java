package com.kshrd.khracer_api.Service;


import com.kshrd.khracer_api.Repository.Model.*;
import com.kshrd.khracer_api.RestController.RequestModel.UserModelRM;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    List<Role> findRolesByUserId(int id);

    //mine
    ResponseEntity<BaseApiResponse<UserModelRS>> insertUser(UserModelRM userModel);

    boolean insertRole(int id);

    int selectUserIDByEmail(String email);


    List<Integer> selectTopID(int i);

    UserRankModel selectTopUserDetail(int i);

    List<UserDRSM> getAllUser(int limit, int page);
}

