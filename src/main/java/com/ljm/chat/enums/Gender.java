package com.ljm.chat.enums;

import com.ljm.chat.enums.converter.AbstractEnumConverter;
import com.ljm.chat.enums.converter.PersistEnum2DB;

/**
 * @Author Dominick Li
 * @DateTime 2020/3/1 17:21
 * @Description 性别枚举
 **/
public enum  Gender implements PersistEnum2DB<Integer> {

    Male(0,"男","male.png"),
    FeMale(1,"女","female.png"),
    Simon(2,"人妖","simon.png");

    private Integer code;
    private String name;
    private String image;

    private Gender(Integer code, String name,String image) {
        this.code = code;
        this.name = name;
        this.image=image;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 手写的从int到enum的转换函数
     */
    public static Gender valueOf(int value) {
        switch (value) {
            case 0:
                return Male;
            case 1:
                return FeMale;
            case 2:
                return Simon;
            default:
                return null;
        }
    }

    @Override
    public Integer getData() {
        return code;
    }

    public static class Converter extends AbstractEnumConverter<Gender, Integer> {

        public Converter() {
            super(Gender.class);
        }
    }
}
