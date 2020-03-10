package com.ljm.chat.enums;

/**
 * @author Dominick Li
 * @createTime 2020/3/6 0:31
 * @description 通讯录好友标识
 **/
public enum FriendStatu {

    Added(1,"已添加"),
    Blacklist(2,"黑名单"),
    TobeAdded(3,"待对方同意好友申请"),
    ToAgree(4,"待同意对方好友申请");

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

    public static FriendStatu valueOf(int value) {    //手写的从int到enum的转换函数
        switch (value) {
            case 1:
                return Added;
            case 2:
                return Blacklist;
            case 3:
                return TobeAdded;
            case 4:
                return ToAgree;
            default:
                return null;
        }
    }
}

