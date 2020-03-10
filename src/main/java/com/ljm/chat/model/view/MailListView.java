package com.ljm.chat.model.view;

import com.ljm.chat.model.MailList;

import java.io.Serializable;

/**
 * @author Dominick Li
 * @createTime 2020/3/4 19:58
 * @description 通讯录视图显示
 **/
public class MailListView implements Serializable {

    private Integer userId;
    private String headImg;
    private String nickName;

    public MailListView() {

    }

    public MailListView(MailList mailList) {
        this.userId = mailList.getFriendId();
        this.headImg = mailList.getUser().getHeadImg();
        this.nickName = mailList.getNameRemarks() == null ? mailList.getUser().getNickname() : mailList.getNameRemarks();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
