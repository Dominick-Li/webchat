package com.ljm.chat.util;

import com.ljm.chat.model.User;

import javax.servlet.http.HttpSession;

/**
 * @author Dominick Li
 * @createTime 2020/3/1  13:57
 * @description 获取当前登录的用户信息
 **/
public class CurrentUser {

    private CurrentUser(){

    }
    private final static String  USER="user";
    private final static String ADMIN="admin";

    public static User getUser(HttpSession session){
          return   session.getAttribute(USER)==null?null:(User)session.getAttribute(USER);
    }
    public static User getAdmin(HttpSession session) {
        return session.getAttribute(ADMIN) == null ? null : (User) session.getAttribute(ADMIN);
    }
}
