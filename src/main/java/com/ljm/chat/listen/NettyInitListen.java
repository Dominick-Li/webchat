package com.ljm.chat.listen;

import com.ljm.chat.netty.NettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Dominick Li
 * @CreateTime 2020/3/8 16:42
 * @description 容器启动后初始化netty-websocket服务
 **/
@Component
public class NettyInitListen implements CommandLineRunner {

    @Value("${netty.port}")
    Integer nettyPort;
    @Value("${server.port}")
    Integer serverPort;
    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("nettyServer starting ...");
            System.out.println("http://127.0.0.1:8081/");
            new NettyServer(nettyPort).start();
        } catch (Exception e) {
            System.out.println("NettyServerError:" + e.getMessage());
        }
    }
}
