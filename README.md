#项目名称  
项目名称:webchat  
项目描述:仿照微信写的网页版聊天工具  
项目使用JAVA语音开发,基于netty加websocket实现的聊天功能  

#功能特效  
包含登录,注册,私聊,群聊等功能,后续会更新加好友,创建群聊等功能.以下是当前核心功能截图  
登录注册页面  
![登录注册](http://152.136.120.234:4412/login.png) 

私聊效果图  
![私聊](http://152.136.120.234:4412/chat.png) 

群聊效果图  
![群聊1](http://152.136.120.234:4412/groupChat1.png) 

![群聊2](http://152.136.120.234:4412/groupChat2.png) 

#后续功能增加
加好友功能
消息置顶功能
修改好友备注功能
修改自己性别和昵称信息

#环境依赖  
jdk 1.8以上  
mysql  5.5以上  
netty 4.1.36  
springboot 2.2.4.RELEASE  

#目录结构  
release                         编译后应用包路径
│  server.sh                    linux系统启动脚本   
│  startup.bat                  window系统启动脚本  
│  test.sql                     测试的sql数据  
│  webchat.jar                  程序包  
│  
└─conf                         存放配置文件的目录  
       application.yml         程序包的配置文件  
       logback-spring.xml      log配置文件  

#部署步骤  
1.安装mysql  
2.创建 webchat数据库  
3.执行mvn clean package 命令打包应用
4.把release目录上传到服务器上
5.修改配置文件,把数据库连接信息替换下  
vi application.yml  
6.启动  
   5.1 window    
    双击startup.bat        启动程序  
   5.2 linux系统    
   cd  /release  目录下  
   执行 ./server.sh start  启动程序  

7.插入测试数据  
启动程序后会自动生成表结构,然后把test.sql文件里的数据插入到webchat数据库中  

8.访问   
http://localhost:8081/  
测试帐号  
admin   123  
min     123  
ming    123  
