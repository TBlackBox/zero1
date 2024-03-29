package com.zero.base.db.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.base.common.core.util.ToolUtil;
import com.zero.base.common.db.context.SqlSessionFactoryContext;
import com.zero.base.common.db.entity.DatabaseInfo;
import com.zero.base.common.db.exception.DataSourceInitException;
import com.zero.base.common.db.model.params.DatabaseInfoParam;
import com.zero.base.common.db.model.result.DatabaseInfoResult;
import com.zero.base.common.db.service.DatabaseInfoService;
import com.zero.base.common.pojo.page.LayuiPageFactory;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.common.tenant.consts.TenantConstants;
import com.zero.base.db.mapper.DatabaseInfoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 数据库信息表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-15
 */
@Service
public class DatabaseInfoServiceImpl extends ServiceImpl<DatabaseInfoMapper, DatabaseInfo> implements DatabaseInfoService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(DatabaseInfoParam param) {

        //判断数据库连接是否可用
        Connection conn = null;
        try {
            Class.forName(param.getJdbcDriver());
            conn = DriverManager.getConnection(
                    param.getJdbcUrl(), param.getUserName(), param.getPassword());
        } catch (Exception e) {
            throw new DataSourceInitException(DataSourceInitException.ExEnum.INIT_DATASOURCE_ERROR);
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //判断dbName是否重复
        String dbName = param.getDbName();
        List<DatabaseInfo> db_name = this.list(new QueryWrapper<DatabaseInfo>().eq("db_name", dbName));
        if (db_name.size() > 0) {
            throw new DataSourceInitException(DataSourceInitException.ExEnum.REPEAT_ERROR);
        }

        //数据库中插入记录
        DatabaseInfo entity = getEntity(param);
        this.save(entity);

        //先判断context中是否有了这个数据源名称
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryContext.getSqlSessionFactorys().get(param.getDbName());
        if (sqlSessionFactory != null) {
            throw new DataSourceInitException(DataSourceInitException.ExEnum.NAME_REPEAT_ERROR);
        }

        //往上下文中添加数据源
        SqlSessionFactoryContext.addSqlSessionFactory(param.getDbName(), entity);
    }

    @Override
    public void delete(DatabaseInfoParam param) {

        //如果是租户数据库不能删除
        DatabaseInfo databaseInfo = this.getById(param.getDbId());
        if (databaseInfo.getDbName().startsWith(TenantConstants.TENANT_DB_PREFIX)) {
            throw new DataSourceInitException(DataSourceInitException.ExEnum.DELETE_TENANT_ERROR);
        }

        this.removeById(getKey(param));
    }

    @Override
    public void update(DatabaseInfoParam param) {
        DatabaseInfo oldEntity = getOldEntity(param);
        DatabaseInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DatabaseInfoResult findBySpec(DatabaseInfoParam param) {
        return null;
    }

    @Override
    public List<DatabaseInfoResult> findListBySpec(DatabaseInfoParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DatabaseInfoParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DatabaseInfoParam param) {
        return param.getDbId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private DatabaseInfo getOldEntity(DatabaseInfoParam param) {
        return this.getById(getKey(param));
    }

    private DatabaseInfo getEntity(DatabaseInfoParam param) {
        DatabaseInfo entity = new DatabaseInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
