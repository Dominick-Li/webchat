package com.ljm.chat.enums;

import com.ljm.chat.enums.converter.AbstractEnumConverter;
import com.ljm.chat.enums.converter.PersistEnum2DB;

/**
 * @author Dominick Li
 * @createTime 2020/3/6 0:31
 * @description 通讯录好友标识
 **/
public enum FriendStatu implements PersistEnum2DB<Integer> {

    Added(1, "已添加"),
    Blacklist(2, "黑名单"),
    TobeAdded(3, "待对方同意好友申请"),
    ToAgree(4, "待同意对方好友申请");

    private Integer statuCode;
    private String statuName;


    private FriendStatu(Integer statuCode, String statuName) {
        this.statuCode = statuCode;
        this.statuName = statuName;
    }

    public Integer getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(Integer statuCode) {
        this.statuCode = statuCode;
    }

    public String getStatuName() {
        return statuName;
    }

    public void setStatuName(String statuName) {
        this.statuName = statuName;
    }

    public static FriendStatu valueOf(int value) {
        for (FriendStatu friendStatu : values()) {
            if (friendStatu.getStatuCode().equals(value)) {
                return friendStatu;
            }
        }
        return null;
    }


    @Override
    public Integer getData() {
        return statuCode;
    }

    public static class Converter extends AbstractEnumConverter<FriendStatu, Integer> {

        public Converter() {
            super(FriendStatu.class);
        }
    }
}

