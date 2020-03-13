package com.ljm.chat.model.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dominick Li
 * @createTime 2020/3/1 0:31
 * @description 用户操作控制器
 * 基类,用于重写toString,把javaBean转成json字符串
 */
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)//选择继承策略
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public  class BaseModel implements Serializable {
    private static final long serialVersionUID = 2863256929817929825L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  long createTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
