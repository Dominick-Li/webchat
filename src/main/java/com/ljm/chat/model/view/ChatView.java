package com.ljm.chat.model.view;


import com.ljm.chat.model.ChatMain;
import com.ljm.chat.model.GroupChat;
import com.ljm.chat.util.DateUtils;

/**
 * @author Dominick Li
 * @CreateTime 2020/3/8 19:20
 * @description 把群聊信息和私聊消息组合成一起
 **/
public class ChatView {

    /**
     * 私聊构造器
     */
    public ChatView(ChatMain chatMain){
        this.id=chatMain.getId();
        this.friendId=chatMain.getFriendId();
        this.name=chatMain.getFriendnameRemarks();
        this.headImg=chatMain.getFriendHeadImg();
        this.lastTime=chatMain.getLastTime();
        this.message=chatMain.getLastMessage();
        this.type="mail";
    }

    /**
     * 群聊构造器
     */
    public ChatView(GroupChat groupChat){
        this.id=groupChat.getId();
        this.name=groupChat.getGroupName();
        this.headImg=groupChat.getGroupHeadImg();
        this.lastTime=groupChat.getLastTime();
        this.message=groupChat.getLastMessage();
        this.type="group";
    }

    /**
     *群聊或者私聊的唯一id
     */
    private Integer id;
    /**
     * 好友id
     */
    private Integer friendId;

    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String headImg;
    /**
     * 类型  true群聊,false私聊
     */
    private String type;
    /**
     * 最新的消息内容
     */
    private String    message;
    /**
     * 最新消息的时间戳
     */
    private long lastTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastDay() {
        return DateUtils.getDayNow(lastTime);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }


}
