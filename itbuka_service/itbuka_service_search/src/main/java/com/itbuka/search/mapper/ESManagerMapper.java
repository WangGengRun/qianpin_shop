package com.itbuka.search.mapper;

import com.itbuka.damian.DetailIndex;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


@Mapper
public interface ESManagerMapper extends ElasticsearchRepository<DetailIndex,String> {
}
