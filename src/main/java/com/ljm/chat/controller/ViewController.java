package com.ljm.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Dominick Li
 * @createTime 2020/3/1 0:31
 * @description 视图挑战
 **/
@Controller
public class ViewController {

    @GetMapping(value = {"/login","/"})
    public String login(){
        return "login";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
