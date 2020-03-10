package com.ljm.chat.repository;

import com.ljm.chat.model.ChatMain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatMainRepository extends JpaRepository<ChatMain,Integer> {

        List<ChatMain>  findByUserId(Integer userId);

        ChatMain findByUserIdAndFriendId(Integer friendId,Integer userId);
}
