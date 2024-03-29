package com.zero.base.common.db.dao;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.zero.base.common.core.config.properties.DruidProperties;
import com.zero.base.common.db.dao.sqls.AddDatabaseInfoSql;
import com.zero.base.common.db.dao.sqls.DatabaseListSql;
import com.zero.base.common.db.dao.sqls.DeleteDatabaseInfoSql;
import com.zero.base.common.db.exception.DataSourceInitException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.zero.base.common.db.context.DataSourceContext.MASTER_DATASOURCE_NAME;

/**
 * 操作数据源信息的dao
 *
 * @author fengshuonan
 * @date 2019-06-12-14:02
 */
@Slf4j
public class DataBaseInfoDao {

    private DruidProperties druidProperties;

    public DataBaseInfoDao(DruidProperties druidProperties) {
        this.druidProperties = druidProperties;
    }

    /**
     * 查询所有数据源列表
     *
     * @author fengshuonan
     * @Date 2019-05-04 20:30
     */
    public Map<String, DruidProperties> getAllDataBaseInfo() {

        Map<String, DruidProperties> dataSourceList = new HashMap<>();

        try {
            Class.forName(druidProperties.getDriverClassName());
            Connection conn = DriverManager.getConnection(
                    druidProperties.getUrl(), druidProperties.getUsername(), druidProperties.getPassword());

            PreparedStatement preparedStatement = conn.prepareStatement(new DatabaseListSql().getSql(druidProperties.getUrl()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DruidProperties druidProperties = createDruidProperties(resultSet);
                String dbName = resultSet.getString("db_name");
                dataSourceList.put(dbName, druidProperties);
            }

            return dataSourceList;

        } catch (Exception ex) {
            throw new DataSourceInitException(DataSourceInitException.ExEnum.QUERY_DATASOURCE_INFO_ERROR);
        }
    }

    /**
     * 初始化master的数据源，要和properties配置的数据源一致
     *
     * @author fengshuonan
     * @Date 2019-06-15 10:20
     */
    public void createMasterDatabaseInfo() {
        try {
            Class.forName(druidProperties.getDriverClassName());
            Connection conn = DriverManager.getConnection(
                    druidProperties.getUrl(), druidProperties.getUsername(), druidProperties.getPassword());

            PreparedStatement preparedStatement = conn.prepareStatement(new AddDatabaseInfoSql().getSql(druidProperties.getUrl()));

            preparedStatement.setLong(1, IdWorker.getId());
            preparedStatement.setString(2, MASTER_DATASOURCE_NAME);
            preparedStatement.setString(3, druidProperties.getDriverClassName());
            preparedStatement.setString(4, druidProperties.getUsername());
            preparedStatement.setString(5, druidProperties.getPassword());
            preparedStatement.setString(6, druidProperties.getUrl());
            preparedStatement.setString(7, "主数据源，项目启动数据源！");
            preparedStatement.setString(8, DateUtil.formatDateTime(new Date()));

            int i = preparedStatement.executeUpdate();
            log.info("初始化master的databaseInfo信息！初始化" + i + "条！");
        } catch (Exception ex) {
            log.error("初始化master的databaseInfo信息错误！", ex);
            throw new DataSourceInitException(DataSourceInitException.ExEnum.QUERY_DATASOURCE_INFO_ERROR);
        }
    }

    /**
     * 删除master的数据源信息
     *
     * @author fengshuonan
     * @Date 2019-06-15 10:20
     */
    public void deleteMasterDatabaseInfo() {
        try {
            Class.forName(druidProperties.getDriverClassName());
            Connection conn = DriverManager.getConnection(
                    druidProperties.getUrl(), druidProperties.getUsername(), druidProperties.getPassword());

            PreparedStatement preparedStatement = conn.prepareStatement(new DeleteDatabaseInfoSql().getSql(druidProperties.getUrl()));
            preparedStatement.setString(1, MASTER_DATASOURCE_NAME);
            int i = preparedStatement.executeUpdate();
            log.info("删除master的databaseInfo信息！删除" + i + "条！");
        } catch (Exception ex) {
            log.info("删除master的databaseInfo信息失败！", ex);
            throw new DataSourceInitException(DataSourceInitException.ExEnum.QUERY_DATASOURCE_INFO_ERROR);
        }
    }

    /**
     * 通过查询结果组装druidProperties
     *
     * @author fengshuonan
     * @Date 2019-06-12 14:22
     */
    private DruidProperties createDruidProperties(ResultSet resultSet) {

        DruidProperties druidProperties = new DruidProperties();

        druidProperties.setTestOnBorrow(true);
        druidProperties.setTestOnReturn(true);

        try {
            druidProperties.setDriverClassName(resultSet.getString("jdbc_driver"));
            druidProperties.setUrl(resultSet.getString("jdbc_url"));
            druidProperties.setUsername(resultSet.getString("user_name"));
            druidProperties.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            throw new DataSourceInitException(DataSourceInitException.ExEnum.QUERY_DATASOURCE_INFO_ERROR);
        }

        return druidProperties;
    }

}
