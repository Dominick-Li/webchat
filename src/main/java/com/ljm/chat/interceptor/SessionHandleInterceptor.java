package com.ljm.chat.interceptor;

import com.ljm.chat.model.User;
import com.ljm.chat.util.CurrentUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Dominick Li
 * @CreateTime 2020/3/10 15:44
 * @description 由于项目太简单了,就不使用安全框架了
 **/
public class SessionHandleInterceptor  implements HandlerInterceptor {

    /**
     * 如果未登录,跳转到登录页面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = CurrentUser.getUser(session);
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
