/*添加用户信息*/
INSERT INTO sys_user (id, createTime, address, emill, gender, headImg, lastLoginTime, loginIp, mobile, nickname, password, username, wxCode) VALUES (1, 0, '江西  南昌', null, 0, '/static/images/userhead/h1.png', null, null, null, 'Dominick-Li', '123', 'admin', 'm1n9GG1224');
INSERT INTO sys_user (id, createTime, address, emill, gender, headImg, lastLoginTime, loginIp, mobile, nickname, password, username, wxCode) VALUES (2, 0, '江西  南昌', null, 1, '/static/images/userhead/h3.png', null, null, null, '断桥残雪', '123', 'min', 'm1nGG1224');
INSERT INTO sys_user (id, createTime, address, emill, gender, headImg, lastLoginTime, loginIp, mobile, nickname, password, username, wxCode) VALUES (3, 0, '江西  南昌', null, 2, '/static/images/userhead/h4.png', null, null, null, '秋殇别恋', '123', 'ming', 'm1GG1224');

/*添加用户和好友关联信息*/
INSERT INTO mail_list (id, createTime, friendId, friendStatu, msgTop, nameRemarks, userId) VALUES (1, 1, 2, 1, null, '女朋友', 1);
INSERT INTO mail_list (id, createTime, friendId, friendStatu, msgTop, nameRemarks, userId) VALUES (2, 2, 3, 1, null, '路人甲', 1);
INSERT INTO mail_list (id, createTime, friendId, friendStatu, msgTop, nameRemarks, userId) VALUES (3, 3, 1, 1, null, '男朋友', 2);
INSERT INTO mail_list (id, createTime, friendId, friendStatu, msgTop, nameRemarks, userId) VALUES (4, 4, 1, 1, null, null, 3);

/*添加聊天信息关联*/
INSERT INTO chat_main (id, createTime, friendId, userId) VALUES (1, 1, 2, 1);
INSERT INTO chat_main (id, createTime, friendId, userId) VALUES (2, 3, 1, 2);
INSERT INTO chat_main (id, createTime, friendId, userId) VALUES (3, 1, 3, 1);
INSERT INTO chat_main (id, createTime, friendId, userId) VALUES (4, 3, 1, 3);

/*添加群组信息*/
INSERT INTO group_chat (id, createTime, groupHeadImg, groupName) VALUES (1, 1, '/static/images/grouphead/2.png', '新手福利群');
INSERT INTO group_chat (id, createTime, groupHeadImg, groupName) VALUES (2, 2, '/static/images/grouphead/1.png', '单身狗群');

/*关联群组和用户信息*/
INSERT INTO group_chat_user (groupChatId, userId, createTime, msgTop) VALUES (1, 1, 1583766765671, 1);
INSERT INTO group_chat_user (groupChatId, userId, createTime, msgTop) VALUES (1, 2, 1583766765671, 2);
INSERT INTO group_chat_user (groupChatId, userId, createTime, msgTop) VALUES (1, 3, 1583766765671, 1);
INSERT INTO group_chat_user (groupChatId, userId, createTime, msgTop) VALUES (2, 1, 1583766765671, 1);
INSERT INTO group_chat_user (groupChatId, userId, createTime, msgTop) VALUES (2, 2, 1583766765671, 1);


