package com.ljm.chat.model;

import com.ljm.chat.model.base.BaseModel;
import com.ljm.chat.util.DateUtils;

import javax.persistence.*;
import java.util.List;


/**
 * @author Dominick Li
 * @createTime 2020/3/5 16:10
 * @description 聊天消息主表
 **/
@Entity
@Table(name = "chat_main")
public class ChatMain  extends BaseModel {

    public ChatMain(){

    }

    public ChatMain(Integer userId,Integer friendId){
        this.userId=userId;
        this.friendId=friendId;
        this.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 用户Id
     */
    private Integer userId;
    /**
     *好友的id
     */
    private Integer friendId;

    /**
     *好友的备注或昵称
     */
    @Transient
    private String friendnameRemarks;
    /**
     *好友的头像
     */
    @Transient
    private String friendHeadImg;

    /**
     *关联聊天记录详情
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="chatMainId")
    private List<ChatMessage> chatMessageList;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private List<User> users;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public String getFriendnameRemarks() {
        return friendnameRemarks;
    }

    public void setFriendnameRemarks(String friendnameRemarks) {
        this.friendnameRemarks = friendnameRemarks;
    }

    public String getFriendHeadImg() {
        return friendHeadImg;
    }

    public void setFriendHeadImg(String friendHeadImg) {
        this.friendHeadImg = friendHeadImg;
    }


    @Transient
    public long getLastTime(){
        if(!chatMessageList.isEmpty()){
            ChatMessage chatMessage=  chatMessageList.get(chatMessageList.size()-1);
            return chatMessage==null?0:chatMessage.getCreateTime();
        }
        return 0;
    }

    /**
     * 最新1条的消息内容
     */
    @Transient
    public String getLastMessage() {
        if(!chatMessageList.isEmpty()){
            ChatMessage chatMessage=  chatMessageList.get(chatMessageList.size()-1);
            return chatMessage==null?"":chatMessage.getContent();
        }
        return "";
    }
}
