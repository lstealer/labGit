package com.kshrd.khracer_api.RestController;

import com.kshrd.khracer_api.Configuration.JwtConfiguration.JwtTokenUtil;
import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.JwtResponse;
import com.kshrd.khracer_api.Repository.Model.User;
import com.kshrd.khracer_api.Repository.UserRepository;
import com.kshrd.khracer_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "*")
public class JwtRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "*")
    public ResponseEntity<BaseApiResponse<?>> createAuthenticationToken(@RequestBody User user)throws Exception {
        BaseApiResponse response = new BaseApiResponse();
//        System.out.println(user.getPwd());
//        System.out.println(bCryptPasswordEncoder.encode(user.getPassword()));
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPwd()));
            System.out.println("i in");
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect email or password",e);
        }
        final User userDetails =(User) userService.loadUserByUsername(user.getEmail());
        System.out.println("UserDetail:"+userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);
        response.setMessage("You have login successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setData(new JwtResponse(token));
        return ResponseEntity.ok(response);
    }
}
