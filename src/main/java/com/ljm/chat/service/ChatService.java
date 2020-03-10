package com.ljm.chat.service;

import com.ljm.chat.model.ChatMain;
import com.ljm.chat.model.GroupChat;
import com.ljm.chat.model.view.ChatView;
import com.ljm.chat.model.view.MailListView;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public interface ChatService {

    /**
     * 查询用户的好友
     */
    LinkedHashMap<String, ArrayList<MailListView>> findByMailListByUserId(Integer userId);

    /**
     * 查询用户的聊天主表
     */
    List<ChatMain>  findChatByUserId(Integer userId);

    /**
     * 查询群聊信息
     */
    List<GroupChat> findGroupChatByUserId(Integer userId);

    /**
     * 合并群聊消息和
     */
     void findChatViewByUserId(Integer userId, Model model);
}
