package com.kshrd.khracer_api.RestController.javaTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    Matcher matcher;
    public boolean regexInput(String letter, String regex) {
        Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(letter);
        return matcher.matches();
    }
}
