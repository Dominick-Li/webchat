package com.ljm.chat.service;

import com.ljm.chat.model.User;
import com.ljm.chat.model.response.ResResult;

import javax.servlet.http.HttpSession;

public interface UserService {

    ResResult login(String userName, String password, HttpSession httpSession);

    ResResult register(User user,HttpSession session,String contentPath);
}
