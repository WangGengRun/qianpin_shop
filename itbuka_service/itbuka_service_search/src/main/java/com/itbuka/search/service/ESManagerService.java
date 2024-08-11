package com.itbuka.search.service;

public interface ESManagerService {
    /**
     * 创建索引库结构
     */
    void createMappingAndIndex();
    /**
     * 导入全部数据到ES索引库
     */
    void importById(String id);
    /**
     *  删除数据到ES索引库
     */
    void deleteById(String id);
}

