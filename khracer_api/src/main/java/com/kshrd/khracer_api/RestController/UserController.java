package com.kshrd.khracer_api.RestController;


import com.kshrd.khracer_api.Configuration.JwtConfiguration.JwtTokenUtil;
import com.kshrd.khracer_api.Repository.Model.*;
import com.kshrd.khracer_api.Repository.UserRepository;
import com.kshrd.khracer_api.RestController.Message.InvalidRequestMessage;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.RestController.RequestModel.UserModelRM;
import com.kshrd.khracer_api.RestController.javaTools.RegexProvider;
import com.kshrd.khracer_api.RestController.javaTools.Validator;
import com.kshrd.khracer_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kh-racer/v1")

public class UserController {

    private AuthenticationManager authenticationManager;

    JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    Validator validator=new Validator();
    UserRepository userRepository;

    @Autowired
    void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Autowired
    void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //for admin side
    //only admin upper lv can access
    //limit is record count to response once
    //page is page of records you want to go
    @GetMapping("/users")
    ResponseEntity<BaseApiResponse<List<UserDRSM>>> getAllUser(
            @RequestParam(value = "limit",defaultValue="5") int limit,
            @RequestParam(value = "page",defaultValue = "1")int page
    ){
        BaseApiResponse<List<UserDRSM>> response=new BaseApiResponse<>();
        int recordCount=userRepository.getAllUserCount();
        if(recordCount==0||!(page<=recordCount/limit+1)){
            response.setResponse(
                    InvalidRequestMessage.PAGE_NOT_AVAILABLE.value(),
                    true,
                    HttpStatus.NO_CONTENT,
                    null,
                    recordCount
                    );
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response.setResponse(
                SuccessMessage.GET_USERS_SUCCESS.value(),
                true,
                HttpStatus.OK,
                userService.getAllUser(limit,(page-1)*limit),
                recordCount
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//   Register page
//    name can not be null "" & have space
    @PostMapping("/user")
    ResponseEntity<BaseApiResponse<UserModelRS>> insertUser(@RequestBody UserModelRM userModel) {
        userModel.setPassword(bCryptPasswordEncoder.encode(
                userModel.getPassword()
        ));
       return userService.insertUser(userModel);
    }


//    HomePage Top Rank Players Records
//    100% on alpha
//    If the Record not enough for the limit number => response no content
    @GetMapping("/top-players")
    public ResponseEntity<BaseApiResponse<UserRankModel>> getHome() {
        BaseApiResponse response = new BaseApiResponse();
        List<UserRankModel> topPlayer = new ArrayList<>();
        List<Integer> id = userService.selectTopID(10);
        UserRankModel tmpUserRankModel;
        for (int i : id) {
            tmpUserRankModel = userService.selectTopUserDetail(i);
            if (tmpUserRankModel == null) {
                response.setResponse(SuccessMessage.HAS_NO_RECORD.value(),
                        false,
                        HttpStatus.NO_CONTENT,
                        null
                );
                System.out.println(response);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else
                topPlayer.add(tmpUserRankModel);
        }
        response.setMessage("You have login successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setData(topPlayer);
        return ResponseEntity.ok(response);
    }
}
