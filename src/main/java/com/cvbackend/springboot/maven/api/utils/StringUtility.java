package com.cvbackend.springboot.maven.api.utils;

import java.util.regex.Pattern;

public class StringUtility {

    public static boolean IsPatternMatching(String matcher, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(regexPattern).matches();
    }

}