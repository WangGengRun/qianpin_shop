package com.itbuka.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.Product;
import com.itbuka.goods.domain.ProductDetails;
import com.itbuka.goods.mapper.ProductDetailsMapper;
import com.itbuka.goods.mapper.ProductMapper;
import com.itbuka.goods.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_product_details(商品详情表)】的数据库操作Service实现
* @createDate 2024-08-10 12:18:23
*/
@Service
public class ProductDetailsServiceImpl extends ServiceImpl<ProductDetailsMapper, ProductDetails>
    implements ProductDetailsService {
@Autowired
private ProductDetailsMapper iProductDetailsMapper;
@Autowired
private ProductMapper productMapper;
    @Override
    public List<ProductDetails> selectAll() {
        LambdaQueryWrapper <ProductDetails> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ProductDetails::getIsDelete,0);
        return iProductDetailsMapper.selectList(lqw);
    }

    @Override
    public List<ProductDetails> selectList(ProductDetails iProductDetails) {
        LambdaQueryWrapper <ProductDetails> lqw = this.lqw(iProductDetails);
        return iProductDetailsMapper.selectList(lqw);
    }

    @Override
    public int insert(ProductDetails iProductDetails) {
        //商品规格最小价格当做商品价格
        //1.查询所有商品规格表
        LambdaQueryWrapper<ProductDetails> lqw = new LambdaQueryWrapper();
        lqw.eq(ProductDetails::getIsDelete,0);
        lqw.eq(ProductDetails::getProductId,iProductDetails.getProductId());
        lqw.orderByAsc(ProductDetails::getPrice);
        List<ProductDetails> list = iProductDetailsMapper.selectList(lqw);
        //判断有没有
        if (list.size() > 0) {
            //判断谁是最小价格
            Product product = new Product();
            product.setId(iProductDetails.getProductId());
            if (iProductDetails.getPrice().compareTo(list.get(0).getPrice()) > 0) {
                product.setPrice(list.get(0).getPrice());
            }else {
                product.setPrice(iProductDetails.getPrice());
            }
            productMapper.updateById(product);
        }

        return iProductDetailsMapper.insert(iProductDetails);

    }

    @Override
    @Transactional
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                ProductDetails iProductDetails = new ProductDetails();
                iProductDetails.setId(Long.parseLong(id));
                iProductDetails.setIsDelete(1);
                iProductDetailsMapper.updateById(iProductDetails);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int update(ProductDetails iProductDetails) {
        return iProductDetailsMapper.updateById(iProductDetails);
    }

    @Override
    public Page<ProductDetails> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<ProductDetails>)this.selectAll();
    }

    @Override
    public Page<ProductDetails> pageList(ProductDetails iProductDetails, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<ProductDetails>)this.selectList(iProductDetails);
    }

    @Override
    public Integer reduceStock(Long productId, Integer num) {
        return iProductDetailsMapper.reduceStock(productId,num);
    }
    public LambdaQueryWrapper lqw(ProductDetails iProductDetails){
        LambdaQueryWrapper <ProductDetails> lqw = new LambdaQueryWrapper<>();
        if (iProductDetails.getId() != null){
            lqw.eq(ProductDetails::getId,iProductDetails.getId());
        }
        if (iProductDetails.getSpecId() != null){
            lqw.eq(ProductDetails::getSpecId,iProductDetails.getSpecId());
        }
        if (iProductDetails.getNum() != null){
            lqw.eq(ProductDetails::getNum,iProductDetails.getNum());
        }
        if (iProductDetails.getWeight() != null){
            lqw.eq(ProductDetails::getWeight,iProductDetails.getWeight());
        }
        if (iProductDetails.getPrice() != null){
            lqw.eq(ProductDetails::getPrice,iProductDetails.getPrice());
        }
        if (iProductDetails.getInventory() != null){
            lqw.eq(ProductDetails::getInventory,iProductDetails.getInventory());
        }
        if (iProductDetails.getImg() != null){
            lqw.eq(ProductDetails::getImg,iProductDetails.getImg());
        }
        if (iProductDetails.getProductId() != null){
            lqw.eq(ProductDetails::getProductId,iProductDetails.getProductId());
        }
        if (iProductDetails.getIsDelete() != null){
            lqw.eq(ProductDetails::getIsDelete,iProductDetails.getIsDelete());
        }
        if (iProductDetails.getCreateTime() != null){
            lqw.eq(ProductDetails::getCreateTime,iProductDetails.getCreateTime());
        }
        if (iProductDetails.getUpdateTime() != null){
            lqw.eq(ProductDetails::getUpdateTime,iProductDetails.getUpdateTime());
        }
        return lqw;
    }
}




