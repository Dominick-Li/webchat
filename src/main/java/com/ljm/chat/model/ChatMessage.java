package com.ljm.chat.model;

import com.ljm.chat.model.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author Dominick Li
 * @createTime 2020/3/4 20:21
 * @description 聊天消息详情表
 **/
@Entity
@Table(name = "chat_message", indexes = {
        @Index(name = "chat_message_chatMainId", columnList = "chatMainId")
})
public class ChatMessage extends BaseModel {

    public ChatMessage(){

    }

    public ChatMessage(Integer chatMainId,String content,long time){
        this.chatMainId=chatMainId;
        this.content=content;
        this.setCreateTime(time);
    }

    /**
     * 关联chanMain实体类的id
     */
    private Integer chatMainId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否删除
     */
    private boolean deleted;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getChatMainId() {
        return chatMainId;
    }

    public void setChatMainId(Integer chatMainId) {
        this.chatMainId = chatMainId;
    }
}
