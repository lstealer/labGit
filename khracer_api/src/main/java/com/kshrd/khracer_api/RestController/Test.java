package com.kshrd.khracer_api.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class Test {
    @GetMapping("/admin")
    public String test(){
        return "test admin";
    }
    @GetMapping("/using")
    public String test2(){
        return "test user";
    }
    @GetMapping("/image/1fefefe3 -3r3rf.abc")
    public String test3(){
        return "test both";
    }
}
