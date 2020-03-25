package com.ljm.chat.enums.converter;

import javax.persistence.AttributeConverter;

/**
 * @author Dominick Li
 * @CreateTime 2020/3/24 10:33
 * @description jpa持久化的时候,转换枚举对象的值
 **/
public abstract  class AbstractEnumConverter <ATTR extends Enum<ATTR> & PersistEnum2DB<DB>, DB> implements AttributeConverter<ATTR, DB> {

    private final Class<ATTR> clazz;

    public AbstractEnumConverter(Class<ATTR> clazz) {
        this.clazz = clazz;
    }

    @Override
    public DB convertToDatabaseColumn(ATTR attribute) {
        return attribute != null ? attribute.getData() : null;
    }

    @Override
    public ATTR convertToEntityAttribute(DB dbData) {
        if (dbData == null) {
            return null;
        }

        ATTR[] enums = clazz.getEnumConstants();

        for (ATTR e : enums) {
            if (e.getData().equals(dbData)) {
                return e;
            }
        }
        throw new UnsupportedOperationException("枚举转化异常。枚举【" + clazz.getSimpleName() + "】,数据库库中的值为：【" + dbData + "】");
    }

}
