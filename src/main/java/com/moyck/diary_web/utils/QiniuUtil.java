package com.moyck.diary_web.utils;

import com.qiniu.util.Auth;

public class QiniuUtil {

    private static Auth auth;
    private static String ak = "2L6-CeyogGa9dsq0bsq_0yU6moiABKKVIQ9Sd5_g";
    private static String sk = "pa4qHY5vQTqPqjp4V4ewoeH3tmHpeEu0AS1itPlD";

    private static QiniuUtil instance;

    public static QiniuUtil getInstance(){
        if (instance == null){
            instance = new QiniuUtil();
            auth = Auth.create(ak, sk);
        }
        return instance;
    }

    public String getUploadToken(){
        return auth.uploadToken("diary-moyck", null, 60 * 60 * 24 * 15, null);
    }


}
