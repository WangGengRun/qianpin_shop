package com.itbuka.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.utils.StringUtils;
import com.itbuka.damian.DetailIndex;
import com.itbuka.search.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public Map search(Map<String, String> paramMap)  throws Exception{
        Map<String, Object> resultMap = new HashMap<>();

        //有条件才查询Es
        if (null != paramMap) {
            //组合条件对象
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //0:关键词
            if (!StringUtils.isEmpty(paramMap.get("keywords"))) {
                boolQuery.must(QueryBuilders.matchQuery("name", paramMap.get("keywords")).operator(Operator.AND));
            }
            //1:条件查询
            if (!StringUtils.isEmpty(paramMap.get("salesModel"))) {
                boolQuery.filter(QueryBuilders.termQuery("salesModel", paramMap.get("salesModel")));
            }
            //2：价格查询
            if (!StringUtils.isEmpty(paramMap.get("price"))) {
                String[] prices = paramMap.get("price").split("-");
                if (prices.length == 2) {
                    boolQuery.filter(QueryBuilders.rangeQuery("price").lte(prices[1]));
                }
                boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[0]));
            }


            //4. 原生搜索实现类
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(boolQuery);

            //5:高亮
            HighlightBuilder.Field field = new HighlightBuilder
                    .Field("name")
                    .preTags("<span style='color:red'>")
                    .postTags("</span>");
            nativeSearchQueryBuilder.withHighlightFields(field);


            //5、分页
            String page = paramMap.get("page");
            if (StringUtils.isEmpty(page)) {
                page = "1";
            }
            String size = paramMap.get("size");
            if (StringUtils.isEmpty(size)) {
                size = "10";
            }
            nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.valueOf(page)-1,Integer.valueOf(size)));

            //排序搜索
            if (!StringUtils.isEmpty(paramMap.get("orderField")) && !StringUtils.isEmpty(paramMap.get("rule"))) {
                if (paramMap.get("rule").equals("ASC")) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(paramMap.get("orderField")).order(SortOrder.ASC));
                } else if (paramMap.get("rule").equals("DESC")){
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(paramMap.get("orderField")).order(SortOrder.DESC));
                }
            }

            //10: 执行查询, 返回结果对象
            AggregatedPage<DetailIndex> aggregatedPage = esTemplate.queryForPage(nativeSearchQueryBuilder.build(), DetailIndex.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                    List<T> list = new ArrayList<>();

                    SearchHits hits = searchResponse.getHits();
                    if (null != hits) {
                        for (SearchHit hit : hits) {
                            DetailIndex skuInfo = JSON.parseObject(hit.getSourceAsString(), DetailIndex.class);
                            String high = "<span style='color:red'>"+paramMap.get("keywords")+"</span>";
                            String keywords = skuInfo.getName().replace(paramMap.get("keywords"),high);
                            skuInfo.setName(keywords);
                            list.add((T) skuInfo);
                        }
                    }
                    return new AggregatedPageImpl<T>(list, pageable, hits.getTotalHits(), searchResponse.getAggregations());
                }
            });

            //11. 总条数
            resultMap.put("total", aggregatedPage.getTotalElements());
            //12. 总页数
            resultMap.put("totalPages", aggregatedPage.getTotalPages());
            //13. 查询结果集合
            resultMap.put("rows", aggregatedPage.getContent());

            return resultMap;
        }
        return null;
    }
}
