package com.ljm.chat.model.view;

/**
 * @author Dominick Li
 * @createTime 2020/3/4 19:58
 * @description socket接消息实体
 **/
public class SocketMessage {
    /**
     * 好友私聊主表id或者群聊消息主表id
     */
    private Integer chatMainId;
    /**
     * 消息发送者id
     */
    private Integer userId;
    /**
     *消息接受者id
     */
    private Integer friendId;
    /**
     *发送者头像
     */
    private String headImg;
    /**
     *消息类型
     */
    private String messageType;
    /**
     *消息内容
     */
    private String message;
    /**
     *最新消息的时间
     */
    private String LastDay;

    public Integer getChatMainId() {
        return chatMainId;
    }

    public void setChatMainId(Integer chatMainId) {
        this.chatMainId = chatMainId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastDay() {
        return LastDay;
    }

    public void setLastDay(String lastDay) {
        LastDay = lastDay;
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
}
