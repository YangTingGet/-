package com.yt.bishe.service;

import com.yt.bishe.entity.User;


public interface UserService {
    /**
     * 存储用户信息
     */
    boolean saveUserInfo(User user);

    /**
     * 登录校验密码
     */
    boolean loginCheck(String userName,String password);

    /**
     * 校验用户名
     */
    boolean checkUserName(String userName);

    /**
     * 获取用户信息
     */
    User getUserInfo(String userName);

    /**
     * 修改用户信息
     */
    boolean reviseUserInfo(User user);

    /**
     * 修改用户密码
     */
    boolean revisePasswrod(String userName,String password);
}
