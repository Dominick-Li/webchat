package com.ljm.chat.repository;

import com.ljm.chat.model.GroupChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Dominick Li
 * @CreateTime 2020/3/9 17:12
 * @description
 **/
public interface GroupChatUserRepository extends JpaRepository<GroupChatUser, GroupChatUser.PK> {

    @Query("select gcu from  GroupChatUser  gcu  where gcu.id.groupChatId=?1")
    List<GroupChatUser>  findAllByGroupChatId(Integer groupChatId);
}
