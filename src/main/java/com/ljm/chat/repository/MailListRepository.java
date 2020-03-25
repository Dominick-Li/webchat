package com.ljm.chat.repository;

import com.ljm.chat.enums.FriendStatu;
import com.ljm.chat.model.MailList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailListRepository extends JpaRepository<MailList,Integer> {

    List<MailList> findByUserIdAndFriendStatu(Integer userId, FriendStatu friendStatu);

    MailList findByUserIdAndFriendId(Integer userId,Integer friendId);

}
