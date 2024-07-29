package com.itbuka.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.goods.domain.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【goods_classify(分类表)】的数据库操作Mapper
 * @createDate 2024-07-27 11:34:17
 * @Entity generator.domain.Classify
 */
@Mapper
public interface ClassifyMapper extends BaseMapper<Classify> {
    /**
     * 根据分类名称查询品牌列表
     * @param categoryName
     * @return
     */
    List<Map> findListByCategoryName(@Param("name") String categoryName);
}

