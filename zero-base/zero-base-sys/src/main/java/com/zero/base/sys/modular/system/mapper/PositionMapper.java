package com.zero.base.sys.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.base.sys.modular.system.entity.Position;
import com.zero.base.sys.modular.system.model.params.PositionParam;
import com.zero.base.sys.modular.system.model.result.PositionResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位表 Mapper 接口
 * </p>
 *
 * @author yongzhi
 * @since 2019-06-27
 */
public interface PositionMapper extends BaseMapper<Position> {

    /**
     * 获取map列表
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    List<Map<String, Object>> getAllPositionMap();

    /**
     * 获取列表
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    List<PositionResult> customList(@Param("paramCondition") PositionParam paramCondition);

    /**
     * 获取map列表
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PositionParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    Page<PositionResult> customPageList(@Param("page") Page page, @Param("paramCondition") PositionParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PositionParam paramCondition);

}
