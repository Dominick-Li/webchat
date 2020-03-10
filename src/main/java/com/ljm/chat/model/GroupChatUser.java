package com.ljm.chat.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dominick Li
 * @createTime 2020/3/1 15:11
 * @description 群聊和用户关系对应表
 **/
@Entity
@Table( name ="group_chat_user" )
public class GroupChatUser implements Serializable {

    public GroupChatUser() {

    }

    public GroupChatUser(Integer groupChatId, Integer userId, long createTime) {
        this.setId(new PK(groupChatId, userId));
        this.setCreateTime(createTime);
    }



    /**
     * 联合主键
     */
    @EmbeddedId
    private PK id;

    /**
     * 消息置顶编号
     */
    private Integer msgTop;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 级联加载用户信息
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId",referencedColumnName="id",insertable = false, updatable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getMsgTop() {
        return msgTop;
    }

    public void setMsgTop(Integer msgTop) {
        this.msgTop = msgTop;
    }



    @Embeddable
    public static class PK implements Serializable {
        public PK() {

        }

        public PK(Integer groupChatId, Integer userId) {
            this.groupChatId = groupChatId;
            this.userId = userId;
        }

        /**
         * 对应GroupCHat类的id
         */
        private Integer groupChatId;

        /**
         * 对应User类的id
         */
        private Integer userId;

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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((groupChatId == null) ? 0 : groupChatId.hashCode());
            result = prime * result + ((userId == null) ? 0 : userId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            PK other = (PK) obj;
            if (groupChatId == null) {
                if (other.groupChatId != null) {
                    return false;
                }
            } else if (!groupChatId.equals(other.groupChatId)) {
                return false;
            }
            if (userId == null) {
                if (other.userId != null) {
                    return false;
                }
            } else if (!userId.equals(other.userId)) {
                return false;
            }
            return true;
        }
    }

}
