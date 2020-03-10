package com.ljm.chat.model;

import com.ljm.chat.model.base.BaseModel;
import com.ljm.chat.enums.FriendStatu;
import javax.persistence.*;

/**
 * @author Dominick Li
 * @createTime 2020/3/1  13:57
 * @description 通讯录
 **/
@Entity
@Table( name ="mail_list",indexes = {
        @Index(name="mail_list_uid", columnList = "userId")
})
public class MailList extends BaseModel {

    public MailList(){

    }

    public MailList(Integer userId,Integer friendId,String nameRemarks,FriendStatu friendStatu){
        this.userId=userId;
        this.friendId=friendId;
        this.friendStatu=friendStatu.getStatuCode();
        this.nameRemarks=nameRemarks;
        this.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 好友id
     */
    private Integer friendId;
    /**
     *备注
     */
    private String nameRemarks;
    /**
     *消息置顶编号
     */
    private Integer msgTop;
    /**
     *是否添加,0未添加,1已添加,2黑名单
     */
    private int friendStatu;


    /**
     *级联加载用户信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="friendId",referencedColumnName="id",insertable = false, updatable = false)
    private User user;

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

    public String getNameRemarks() {
        return nameRemarks;
    }

    public void setNameRemarks(String nameRemarks) {
        this.nameRemarks = nameRemarks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMsgTop() {
        return msgTop;
    }

    public void setMsgTop(Integer msgTop) {
        this.msgTop = msgTop;
    }

    public int getFriendStatu() {
        return friendStatu;
    }

    public void setFriendStatu(int friendStatu) {
        this.friendStatu = friendStatu;
    }
}
