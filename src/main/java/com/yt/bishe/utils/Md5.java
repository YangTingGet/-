package com.yt.bishe.utils;

import org.springframework.util.DigestUtils;

public class Md5 {
    private static final String salt = "&%5123***&&%%$$#@";

    public static String getMd5(String str){
        String base = str +"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


}
