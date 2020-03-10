package com.ljm.chat.repository;

import com.ljm.chat.model.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Dominick Li
 * @DateTime 2020/3/6 22:46
 * @Description 群聊消息
 **/
public interface GroupMessageRepository extends JpaRepository<GroupMessage,Integer> {


}
