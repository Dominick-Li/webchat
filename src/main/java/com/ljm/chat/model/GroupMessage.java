package com.ljm.chat.model;

import com.ljm.chat.model.base.BaseModel;

import javax.persistence.*;

/**
 * @author Dominick Li
 * @createTime 2020/3/1 15:11
 * @description 群消息
 **/
@Entity
@Table( name ="group_message" )
public class GroupMessage extends BaseModel {

    public  GroupMessage(){

    }

    public GroupMessage(Integer groupChatId,Integer userId,String content,long lastTime){
        this.groupChatId=groupChatId;
        this.userId=userId;
        this.content=content;
        this.setCreateTime(lastTime);
    }

    /**
     * 对应GroupChat的主键id
     */
    private Integer groupChatId;

    /**
     * 消息发送者的id
     */
    private Integer userId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 级联加载用户信息
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId",referencedColumnName="id",insertable = false, updatable = false)
    private User user;

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(Integer groupChatId) {
        this.groupChatId = groupChatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
