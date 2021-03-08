package com.zero.base.common.db.factory;


import com.zero.base.common.core.config.properties.DruidProperties;
import com.zero.base.common.db.entity.DatabaseInfo;

/**
 * 配置文件的创建
 *
 * @author fengshuonan
 * @date 2019-06-15-20:05
 */
public class DruidFactory {

    /**
     * 创建druid配置
     *
     * @author fengshuonan
     * @Date 2019-06-15 20:05
     */
    public static DruidProperties createDruidProperties(DatabaseInfo databaseInfo) {

        DruidProperties druidProperties = new DruidProperties();

        druidProperties.setDriverClassName(databaseInfo.getJdbcDriver());
        druidProperties.setUsername(databaseInfo.getUserName());
        druidProperties.setPassword(databaseInfo.getPassword());
        druidProperties.setUrl(databaseInfo.getJdbcUrl());

        return druidProperties;

    }

}
