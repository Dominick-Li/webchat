package com.ljm.chat.model;

import com.ljm.chat.model.base.BaseModel;
import com.ljm.chat.util.DateUtils;

import javax.persistence.*;
import java.util.List;

/**
 * @author Dominick Li
 * @createTime 2020/3/1 14:53
 * @description 群聊信息表
 **/
@Entity
@Table( name ="group_chat" )
public class GroupChat  extends BaseModel {


    public GroupChat(){

    }

    public GroupChat(String groupName,String groupHeadImg){
        this.groupName=groupName;
        this.groupHeadImg=groupHeadImg;
        this.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 群名称
     */
    private String  groupName;
    /**
     * 群聊头像 由群内前9个用户的头像组成合照
     */
    private String groupHeadImg;

    /**
     * 群聊里的成员
     * 一对多name代表的附表的外键, referencedColumnName代表本表的主键, 一对一是反的
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="groupChatId",referencedColumnName="id",insertable = false, updatable = false)
    private List<GroupChatUser> groupChatUsers;

    /**
     * 群聊里的消息记录
     */
    @OneToMany(fetch = FetchType.LAZY)
    @OrderBy("createTime ASC")
    @JoinColumn(name="groupChatId",referencedColumnName="id",insertable = false, updatable = false)
    private List<GroupMessage> groupMessages;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHeadImg() {
        return groupHeadImg;
    }

    public void setGroupHeadImg(String groupHeadImg) {
        this.groupHeadImg = groupHeadImg;
    }

    public List<GroupChatUser> getGroupChatUsers() {
        return groupChatUsers;
    }

    public void setGroupChatUsers(List<GroupChatUser> groupChatUsers) {
        this.groupChatUsers = groupChatUsers;
    }

    public List<GroupMessage> getGroupMessages() {
        return groupMessages;
    }

    public void setGroupMessages(List<GroupMessage> groupMessages) {
        this.groupMessages = groupMessages;
    }

    /**
     * 最近1条的消息时间
     */
    @Transient
    public long getLastTime(){
        if(!groupMessages.isEmpty()){
            GroupMessage groupMessage= groupMessages.get(groupMessages.size()-1);
            return groupMessage.getCreateTime();
        }
        return 0;
    }

    /**
     * 最新1条的消息内容
     */
    @Transient
    public String getLastMessage(){
        if(!groupMessages.isEmpty()){
            GroupMessage groupMessage= groupMessages.get(groupMessages.size()-1);
            return groupMessage==null?"":groupMessage.getContent();
        }
        return "";
    }

    @Transient
    public String getCreateDate(){
       return DateUtils.timesToDateStr(getCreateTime());
    }
}
