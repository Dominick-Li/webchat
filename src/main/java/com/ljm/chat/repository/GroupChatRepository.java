package com.ljm.chat.repository;

import com.ljm.chat.model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Dominick Li
 * @DateTime 2020/3/6 19:49
 * @Description
 **/
public interface GroupChatRepository extends JpaRepository<GroupChat,Integer> {

    /**
     * 当前访问会级联加载群聊下的成员信息和群聊消息
     */
    @Query("select gc from GroupChat gc left join  GroupChatUser gcu on gc.id=gcu.id.groupChatId  where gcu.id.userId=?1")
    List<GroupChat> findByUserId(Integer userId);

    /**
     * 只查询用户加入的群id信息
     *  此处使用原生的sql
     */
    @Query(value = "select gcu.groupChatId from group_chat_user gcu where gcu.userId=?1",nativeQuery = true)
    List<Integer> findGroupIdByUserId(Integer userId);

    GroupChat findTopById(Integer id);
}
