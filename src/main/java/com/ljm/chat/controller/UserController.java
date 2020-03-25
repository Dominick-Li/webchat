package com.ljm.chat.controller;

import com.ljm.chat.enums.Gender;
import com.ljm.chat.model.User;
import com.ljm.chat.model.response.ResResult;
import com.ljm.chat.service.ChatService;
import com.ljm.chat.service.UserService;
import com.ljm.chat.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Dominick Li
 * @createTime 2020/3/1 0:31
 * @description 用户操作控制器
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Value("${netty.ws}")
    private String ws;

    /**
     * 查询用户拥有的好友信息
     */
    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User user= CurrentUser.getUser(session);
        model.addAttribute("user", user);
        //通讯录信息
        model.addAttribute("mailLists",chatService.findByMailListByUserId(user.getId()));
        //注入websocket端口
        model.addAttribute("ws",ws);
        //组装群聊和私聊信息
        chatService.findChatViewByUserId(user.getId(),model);
        return "index";
    }

    /**
     * 登录
     * @param username  可以是用户名,手机号,或者邮箱帐号
     * @param password  登录结果
     * @return          登录是否成功,登录成功把用户的令牌信息存入session中
     */
    @PostMapping("/login")
    @ResponseBody
    public ResResult login(String username, String password, HttpSession httpSession){
          return  userService.login(username,password,httpSession);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    @ResponseBody
    public ResResult register(String username, String password, String nickname, HttpSession session, HttpServletRequest request){
        //String contentPath=request.getServletContext().getRealPath("/");
        //idea环境写死,部署在服务器上面的话就根据request去拿扣下
        String contentPath="D:/163@github/webchat/src/main/resources/";
        return  userService.register(new User(username,password,nickname, Gender.Male),session,contentPath);
    }


}
