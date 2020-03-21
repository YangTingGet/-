package com.yt.bishe.service.impl;

import com.yt.bishe.dao.UserDao;
import com.yt.bishe.entity.User;
import com.yt.bishe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean saveUserInfo(User user){

       return userDao.insertUserInfo(user);
    }

    @Override
    public boolean loginCheck(String userName,String password){


        if (password.equals(userDao.selectPassword(userName)))
            return true;
        else
            return false;
    }

    @Override
    public boolean checkUserName(String userName){

        if (userDao.queryUserInfo(userName) == null)
            return false;
        else
            return true;
    }

    @Override
    public User getUserInfo(String userName) {
        return userDao.queryUserInfo(userName);
    }

    @Override
    public boolean reviseUserInfo(User user){
        return userDao.updateUserInfo(user);
    }

    @Override
    public boolean revisePasswrod(String userName,String password){
        return userDao.updatePassword(userName,password);
    }

}
