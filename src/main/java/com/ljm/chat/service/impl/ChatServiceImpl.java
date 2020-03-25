package com.ljm.chat.service.impl;

import com.ljm.chat.enums.FriendStatu;
import com.ljm.chat.model.ChatMain;
import com.ljm.chat.model.ChatMessage;
import com.ljm.chat.model.GroupChat;
import com.ljm.chat.model.MailList;
import com.ljm.chat.model.view.ChatView;
import com.ljm.chat.model.view.MailListView;
import com.ljm.chat.repository.ChatMainRepository;
import com.ljm.chat.repository.GroupChatRepository;
import com.ljm.chat.repository.MailListRepository;
import com.ljm.chat.service.ChatService;
import com.ljm.chat.util.MailSortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

/**
 * @Author Dominick Li
 * @DateTime 2020/3/5 19:08
 * @Description 聊天业务处理
 **/
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private MailListRepository mailListRepository;

    @Autowired
    private ChatMainRepository chatMainRepository;

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Override
    public LinkedHashMap<String, ArrayList<MailListView>> findByMailListByUserId(Integer userId) {
        List<MailList> mailLists=mailListRepository.findByUserIdAndFriendStatu(userId, FriendStatu.Added);
        return MailSortUtil.MailListOrders(mailLists);
    }

    @Override
    public List<ChatMain> findChatByUserId(Integer userId) {
        List<ChatMain> chatMainList=chatMainRepository.findByUserId(userId);
        ChatMain fiendChatMain=null;
        MailList mailList=null;
        ChatMessage lastMessage=null;
        for(ChatMain chatMain:chatMainList){
            //根据聊天标识id去查聊天详情表,取出对方的的聊天记录id
            fiendChatMain= chatMainRepository.findByUserIdAndFriendId(chatMain.getFriendId(),userId);
            //查询好友的备注和头像
            mailList=mailListRepository.findByUserIdAndFriendId(userId,chatMain.getFriendId());
            chatMain.setFriendHeadImg(mailList.getUser().getHeadImg());
            if(mailList.getNameRemarks()!=null){
              chatMain.setFriendnameRemarks(mailList.getNameRemarks());
            }else{
                chatMain.setFriendnameRemarks(mailList.getUser().getNickname());
            }
            //合并2个集合
            if(chatMain.getChatMessageList()==null){
                chatMain.setChatMessageList(new ArrayList<ChatMessage>());
            }
            chatMain.getChatMessageList().addAll(fiendChatMain.getChatMessageList());
            //聊天内容根据时间升序
            Collections.sort(chatMain.getChatMessageList(), new Comparator<ChatMessage>() {
                @Override
                public int compare(ChatMessage o1, ChatMessage o2) {
                   return (int)(o1.getCreateTime()-o2.getCreateTime());
                }
            });
        }
        return chatMainList;
    }

    @Override
    public List<GroupChat> findGroupChatByUserId(Integer userId) {
        List<GroupChat> groupChats=groupChatRepository.findByUserId(userId);
        return groupChats;
    }

    @Override
    public void  findChatViewByUserId(Integer userId, Model model) {
        List<ChatMain> chatMainList=this.findChatByUserId(userId);
        List<GroupChat> groupChatList=this.findGroupChatByUserId(userId);
        List<ChatView> chatViewList=new ArrayList<>(chatMainList.size()+groupChatList.size());
        for(ChatMain chatMain: chatMainList){
            chatViewList.add(new ChatView(chatMain));
        }
        for(GroupChat groupChat: groupChatList){
            chatViewList.add(new ChatView(groupChat));
        }
        Collections.sort(chatViewList, new Comparator<ChatView>() {
            @Override
            public int compare(ChatView o1, ChatView o2) {
                return (int)(o2.getLastTime()-o1.getLastTime());
            }
        });
        model.addAttribute("chatMainList",chatMainList);
        model.addAttribute("groupChats",(groupChatList));
        model.addAttribute("chatViews",chatViewList);
    }
}
