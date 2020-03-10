package com.ljm.chat.listen;


import com.ljm.chat.model.User;
import com.ljm.chat.util.CurrentUser;
import com.ljm.chat.netty.MyWebSocketHandler;
import io.netty.channel.ChannelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

/**
 * @author Dominick Li
 * @createTime 2020/3/8  16:07
 * @description session超时,移除 websocket对应的channel
 **/
public class MySessionListener implements HttpSessionListener {


    private  final Logger logger = LoggerFactory.getLogger(MySessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("sessionCreated sessionId={}", httpSessionEvent.getSession().getId());
        MySessionContext.AddSession(httpSessionEvent.getSession());
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user= CurrentUser.getUser(session);
        //销毁时重websocket channel中移除
        if(user!=null) {
          ChannelId channelId= MyWebSocketHandler.userMap.get(user.getId());
          if(channelId!=null){
              //移除了私聊的channel对象, 群聊的还未移除
              MyWebSocketHandler.userMap.remove(user.getId());
              MyWebSocketHandler.channelGroup.remove(channelId);
              logger.info("session timeout,remove channel, username={}",user.getUsername());
          }

        }
        MySessionContext.DelSession(session);
        logger.info("session destroyed  .... ");
    }


    public static class MySessionContext {

        private static HashMap mymap = new HashMap();

        public static synchronized void AddSession(HttpSession session) {
            if (session != null) {
                mymap.put(session.getId(), session);
            }
        }

        public static synchronized void DelSession(HttpSession session) {
            if (session != null) {
                mymap.remove(session.getId());
            }
        }

        public static synchronized HttpSession getSession(String session_id) {
            if (session_id == null){
                return null;
            }
            return (HttpSession) mymap.get(session_id);
        }
    }
}
