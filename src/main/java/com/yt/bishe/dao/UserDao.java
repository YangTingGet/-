package com.yt.bishe.dao;


import com.yt.bishe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    /**
     * 插入用户信息
     * @param user
     */
    boolean insertUserInfo(User user) ;

    /**
     * 查询用户密码
     * @param userName
     */
    String selectPassword(String userName);

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    User queryUserInfo(String userName);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

    /**
     * 更新用户密码
     * @param userName
     * @param password
     * @return
     */
    boolean updatePassword(String userName,String password);

}


