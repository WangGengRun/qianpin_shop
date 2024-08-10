package com.itbuka.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.Spec;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_spec(规格表)】的数据库操作Service
* @createDate 2024-08-10 12:48:58
*/
public interface SpecService extends IService<Spec> {

    Page<Spec> pageAll(Integer page, Integer size);

    List<Spec> selectAll();

    List<Spec> selectList(Spec iSpec);

    int insert(Spec iSpec);

    int delete(String ids);

    int update(Spec iSpec);

    Page<Spec> pageList(Spec iSpec, Integer page, Integer size);
}
