package com.itbuka.search.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itbuka.damian.DetailIndex;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Detail;
import com.itbuka.goods.feign.GoodsFeign;
import com.itbuka.search.mapper.ESManagerMapper;
import com.itbuka.search.service.ESManagerService;
import com.itbuka.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        String token = TokenUtils.getToken();
        //参数
        Map param = new HashMap();
        param.put("id",id);
        //请求头
        Map header = new HashMap();
        header.put("Authorization","Bearer "+token);
        //获取商品数据
        String body = HttpUtil.createGet("http://localhost:9011/detail/findDetail").addHeaders(header).form(param).execute().body();
        //格式转换
        Detail data = JSONObject.parseObject(JSONObject.parseObject(body, Result.class).getData().toString(), Detail.class);
        //商品转成索引类型
        DetailIndex detailIndex = JSONObject.parseObject(JSONObject.toJSONString(data), DetailIndex.class);
        //保存索引库
        esManagerMapper.save(detailIndex);

    }


    @Override
    public void deleteById(String id) {
        esManagerMapper.deleteById(id);
    }
}

