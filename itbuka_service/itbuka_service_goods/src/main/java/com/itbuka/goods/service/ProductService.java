package com.itbuka.goods.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.Product;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_product(商品表)】的数据库操作Service
* @createDate 2024-07-31 14:22:43
*/
public interface ProductService extends IService<Product> {

    List<Product> selectAll();

    List<Product> selectList(Product iProduct);

    int insert(Product iProduct);

    int delete(String ids);

    int update(Product iProduct);

    Page<Product> pageAll(Integer page, Integer size);

    Page<Product> pageList(Product iProduct, Integer page, Integer size);

    Integer audit(Long id, Integer audit);

    Integer status(Long id, Integer status);
}
