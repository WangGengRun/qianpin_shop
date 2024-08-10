package com.itbuka.search.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itbuka.damian.DetailIndex;
import com.itbuka.goods.domain.Detail;
import com.itbuka.goods.feign.GoodsFeign;
import com.itbuka.search.mapper.ESManagerMapper;
import com.itbuka.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class ESManagerServiceImpl implements ESManagerService {
@Autowired
private ESManagerMapper esManagerMapper;
@Autowired
private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GoodsFeign goodsFeign;
    @Override
    public void createMappingAndIndex() {
        //创建索引
        elasticsearchTemplate.createIndex(DetailIndex.class);
        //创建映射
        elasticsearchTemplate.putMapping(DetailIndex.class);
    }

    @Override
    public void importById(String id) {
        //查询商品数据
        Detail data=goodsFeign.findDetail(Long.valueOf(id)).getData();
        //商品转成索引库类型
        DetailIndex detailIndex = JSONObject.parseObject(JSONObject.toJSONString(data), DetailIndex.class);
        //保存索引库
        esManagerMapper.save(detailIndex);

    }
}

