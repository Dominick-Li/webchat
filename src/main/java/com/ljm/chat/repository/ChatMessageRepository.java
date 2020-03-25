package com.ljm.chat.repository;


import com.ljm.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Integer> {

        List<ChatMessage>  findByChatMainId(Integer chatMainId);



}
