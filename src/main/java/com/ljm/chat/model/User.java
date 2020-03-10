package com.ljm.chat.model;

import com.ljm.chat.model.base.BaseModel;
import com.ljm.chat.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Dominick Li
 * @createTime 2020/3/1  13:57
 * @description 用户表
 **/
@Entity
@Table( name ="sys_user",indexes = {
        @Index(name="sys_user_username",columnList = "username")
})
public class User  extends BaseModel {

    public User(){

    }
    public User(String username,String password,String nickname,Integer gender){
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.gender=gender;
        this.address="中国 大陆";
        this.wxCode=username;
        this.headImg="/static/images/userhead/h4.png";
        this.setCreateTime(System.currentTimeMillis());
    }
    /**
     * 用户名
     */
    private String username;
    /**
     *密码
     */
    private String password;
    /**
     *用户头像
     */
    private String headImg;
    /**
     *昵称
     */
    private String nickname;
    /**
     * 最后一次登录时间
     */
    private String lastLoginTime;
    /**
     *登录的ip地址
     */
    private String loginIp;
    /**
     *手机号
     */
    private String mobile;
    /**
     *邮箱号
     */
    private String emill;
    /**
     *地址
     */
    private String address;
    /**
     *微信号
     */
    private String wxCode;
    /**
     *性别 0男 1女
     */
    private Integer gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmill() {
        return emill;
    }

    public void setEmill(String emill) {
        this.emill = emill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Transient
    public Gender getGenderEnum(){
        return Gender.valueOf(gender);
    }
}
