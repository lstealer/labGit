package com.kshrd.khracer_api.Service.Imp;

import com.kshrd.khracer_api.Repository.Model.*;
import com.kshrd.khracer_api.Repository.UserRepository;
import com.kshrd.khracer_api.Repository.ValidationRepo;
import com.kshrd.khracer_api.RestController.Message.InvalidRequestMessage;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.RestController.Message.UnsuccessMessage;
import com.kshrd.khracer_api.RestController.RequestModel.UserModelRM;
import com.kshrd.khracer_api.RestController.javaTools.RegexProvider;
import com.kshrd.khracer_api.RestController.javaTools.Validator;
import com.kshrd.khracer_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private ValidationRepo validationRepo;
    Validator validator=new Validator();


    @Autowired
    public void setValidationRepo(ValidationRepo validationRepo){
        this.validationRepo=validationRepo;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Role> findRolesByUserId(int id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(email);
        return user;
    }


    //mine


    //Register Page
    //not allow any null
    //Email and username Unique
    //name no space
    //
    @Override
    public ResponseEntity<BaseApiResponse<UserModelRS>> insertUser(UserModelRM userModel) {
        BaseApiResponse<UserModelRS> response = new BaseApiResponse();
        if(userModel.getName()==null
                ||userModel.getName().equals("")
                ||!validator.regexInput(userModel.getName(),RegexProvider.NO_SPACE_VERIFY.value())
        ){
            response.setResponse(InvalidRequestMessage.NAME_ERROR.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response,
                    HttpStatus.BAD_REQUEST
            );
        }
        if(validationRepo.checkUserNameAvailable(userModel.getName())!=null){
            response.setResponse(InvalidRequestMessage.NAME_ALREADY_USED.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response,
                    HttpStatus.BAD_REQUEST
            );
        }
        if(userModel.getEmail()==null
                ||userModel.getEmail().equals("")
                ||!validator.regexInput(userModel.getEmail(),RegexProvider.EMAIL_VERIFY.value())
        ){
            response.setResponse(InvalidRequestMessage.EMAIL_ERROR.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response,
                    HttpStatus.BAD_REQUEST
            );
        }
        if(validationRepo.checkEmailAvailable(userModel.getEmail())!=null){
            response.setResponse(InvalidRequestMessage.EMAIL_ALREADY_USED.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response,
                    HttpStatus.BAD_REQUEST
            );
        }
        if(userModel.getDob()==null){
            response.setResponse(InvalidRequestMessage.DATE_ERROR.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response,
                    HttpStatus.BAD_REQUEST
            );
        }
        boolean isSuccess = userRepository.insertUser(userModel);
        if (isSuccess) {
            userRepository.insertRole(
                    userRepository.selectUserIDByEmail(
                            userModel.getEmail()
                    ));
            response.setMessage(SuccessMessage.REGISTER_SUCCESS.value());
        } else {
            response.setData(null);
            response.setMessage(UnsuccessMessage.REGISTER_UNSUCCESS.value());
        }
        UserModelRS userModelRS=userRepository.selectUserRegisterDetail(userModel.getName());
        userModelRS.setGenderRS(userModelRS.getGender()?"Male":"Female");
        System.out.println(userModelRS);
        response.setData(userModelRS);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public boolean insertRole(int id) {
        return userRepository.insertRole(id);
    }

    @Override
    public int selectUserIDByEmail(String email) {
        System.out.println(userRepository.selectUserIDByEmail(email));
        return userRepository.selectUserIDByEmail(email);
    }

    @Override
    public List<Integer> selectTopID(int i) {
        return userRepository.selectTopID(10);
    }

    @Override
    public UserRankModel selectTopUserDetail(int id) {
        return userRepository.selectTopUserDetail(id);
    }

    @Override
    public List<UserDRSM> getAllUser(int limit, int page) {

        return userRepository.selectAllUser(limit,page);
    }


}
