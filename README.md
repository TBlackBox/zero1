# zero
争取做一个企业级别，快速开发的项目的工程。


# 参考项目
https://github.com/zebra-ruoyi-plus/ruoyi-plus

https://github.com/stylefeng/Guns

https://github.com/enilu/guns-lite
# 软件架构
- zero-common模块:通用的工具类这些,包含缓存，文件上传等解决方案
- zero-common-db模块:数据源,主要是多数据源和数据库读写分离
- zero-common-core模块:业务逻辑核心
- zero-api模块:api服务架构，支持集群
- zero-manager-view模块:后台管理系统,模板渲染版
- zero-manager-api模块:后台管理系统,前后端分离版
- zero-quartz模块:定时任务相关

# 技术选型
- 核心框架：spring boot
- 数据库层：mybatis 版本和jpa 版本
- 安全框架：Shiro
- 数据库连接池：Druid
- 缓存：Ehcache redis 多种缓存

