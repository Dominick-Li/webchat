package com.ljm.chat.service.impl;

import com.ljm.chat.enums.FriendStatu;
import com.ljm.chat.model.*;
import com.ljm.chat.model.response.ResResult;
import com.ljm.chat.repository.*;
import com.ljm.chat.service.UserService;
import com.ljm.chat.util.UpdateGroupLogoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Dominick Li
 * @DateTime 2020/3/1 0:37
 * @Description
 **/
@Service
public class UserServiceImpl implements UserService {

    //管理员的id
    private final int ADMINID=1;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailListRepository mailListRepository;

    @Autowired
    ChatMainRepository chatMainRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    GroupChatRepository groupChatRepository;

    @Autowired
    GroupChatUserRepository groupChatUserRepository;

    @Autowired
    GroupMessageRepository groupMessageRepository;

    //群聊投降保存的路径地址
    private final String GROUPIMGPATH="/static/images/grouphead/";

    private final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResResult login(String userName, String password, HttpSession httpSession) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return new ResResult(false, "帐号不存在!");
        } else if (!user.getPassword().equals(password)) {
            return new ResResult(false, "用户密码错误!");
        }
        user.setPassword(null);
        httpSession.setAttribute("user",user);
        return new ResResult(true, "登录成功");
    }

    /**
     * 注册完,默认添加管理员
     */
    @Override
    @Transactional
    public ResResult register(User user,HttpSession session,String contentPath) {
        long time=System.currentTimeMillis();
        User exists = userRepository.findByUsername(user.getUsername());
        if (exists != null) {
            return new ResResult(false,"用户名已被注册,请更换!");
        }
        exists=userRepository.save(user);
        session.setAttribute("user",exists);
        //添加管理员
        MailList userMail=new MailList(exists.getId(),ADMINID,"管理员", FriendStatu.Added);
        MailList admiMail=new MailList(ADMINID,exists.getId(),null,FriendStatu.Added);
        mailListRepository.save(userMail);
        mailListRepository.save(admiMail);
        //添加聊天窗口
        ChatMain userChatMain=new ChatMain(user.getId(),ADMINID);
        ChatMain adminChatMain=new ChatMain(ADMINID,user.getId());
        chatMainRepository.save(userChatMain);
        ChatMain adminCM= chatMainRepository.save(adminChatMain);
        //添加一条管理员欢迎注册的消息
        ChatMessage chatMessage=new ChatMessage(adminCM.getId(),"欢迎注册hope webchat聊天系统,我是该系统管理员,有什么问题可以联系我!",time);
        chatMessageRepository.save(chatMessage);
        GroupChat groupChat= groupChatRepository.findTopById(ADMINID);
        if(groupChat==null){
            groupChat=new GroupChat("新手福利群","/static/images/grouphead/default.png");
            groupChat= groupChatRepository.save(groupChat);
        }
        //当注册加完群后,查询群里人数是否超过9人,如果小于9,生成头像
        List<GroupChatUser>  groupChatUsers=groupChatUserRepository.findAllByGroupChatId(ADMINID);
        if(groupChatUsers.size()<9){
            //生成头像
            ArrayList imgPathList=new ArrayList(groupChatUsers.size());
            for(GroupChatUser groupChatUser:groupChatUsers){
                imgPathList.add(contentPath+groupChatUser.getUser().getHeadImg());
            }
            imgPathList.add(contentPath+"static/images/userhead/h4.png");
            String groupName=GROUPIMGPATH+groupChat.getId()+".png";
            String outPath=contentPath+groupName;
            try {
                UpdateGroupLogoUtil.generate(imgPathList,outPath);
                groupChat.setGroupHeadImg(groupName);
                groupChatRepository.save(groupChat);
            }catch (Exception e){
                logger.error("save group img error:{}",e);
            }

        }
        GroupChatUser chatUser=new GroupChatUser(groupChat.getId(),user.getId(),time);
        groupChatUserRepository.save(chatUser);
        GroupMessage groupMessage=new GroupMessage(groupChat.getId(),ADMINID,String.format("欢迎%s注册系统!",user.getNickname()),time);
        groupMessageRepository.save(groupMessage);
        return new ResResult(true, "注册成功");
    }
}
