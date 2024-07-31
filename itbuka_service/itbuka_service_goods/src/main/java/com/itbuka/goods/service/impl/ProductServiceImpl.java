package com.itbuka.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.Product;
import com.itbuka.goods.mapper.ProductMapper;
import com.itbuka.goods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_product(商品表)】的数据库操作Service实现
* @createDate 2024-07-31 14:22:43
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService {
@Autowired
private ProductMapper iProductMapper;
    @Override
    public List<Product> selectAll() {
        LambdaQueryWrapper <Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getIsDelete,0);
        return iProductMapper.selectList(lqw);
    }

    @Override
    public List<Product> selectList(Product iProduct) {
        LambdaQueryWrapper <Product> lqw = this.lqw(iProduct);
        return iProductMapper.selectList(lqw);
    }

    @Override
    public int insert(Product iProduct) {
        return iProductMapper.insert(iProduct);
    }

    @Override
    @Transactional
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Product iProduct = new Product();
                iProduct.setId(Long.parseLong(id));
                iProduct.setIsDelete(1);
                iProductMapper.updateById(iProduct);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int update(Product iProduct) {
        return iProductMapper.updateById(iProduct);
    }


    @Override
    public Page<Product> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Product>)this.selectAll();
    }

    @Override
    public Page<Product> pageList(Product iProduct, Integer page, Integer size) {

        PageHelper.startPage(page,size);
        return (Page<Product>)this.selectList(iProduct);
    }

    @Override
    public Integer audit(Long id, Integer audit) {
        Product product = new Product();
        product.setId(id);
        Product product1 = iProductMapper.selectById(id);
        if (product1.getIsDelete() == 0) {
            if (audit == 2) {
                //审核拒绝
                product.setAudit(audit);
                iProductMapper.updateById(product);
                return 2;
            }else if (audit == 1){
                //审核通过
                product.setAudit(audit);
                product.setStatus(1);
                iProductMapper.updateById(product);
                return 1;
            }
        }
        return -1;
    }

    @Override
    public Integer status(Long id, Integer status) {
        Product product1 = iProductMapper.selectById(id);
        if (product1 == null || product1.getIsDelete() == 1) {
            return -1;
        }
        //上架
        if (status == 1) {
            Integer audit = product1.getAudit();
            if (audit != 1) {
                return -1;
            }
        }
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        return iProductMapper.updateById(product);
    }
    public LambdaQueryWrapper lqw(Product iProduct){
        LambdaQueryWrapper <Product> lqw = new LambdaQueryWrapper<>();
        if (iProduct.getId() != null){
            lqw.eq(Product::getId,iProduct.getId());
        }
        if (iProduct.getName() != null){
            lqw.eq(Product::getName,iProduct.getName());
        }
        if (iProduct.getPrice() != null){
            lqw.eq(Product::getPrice,iProduct.getPrice());
        }
        if (iProduct.getSalesModel() != null){
            lqw.eq(Product::getSalesModel,iProduct.getSalesModel());
        }
        if (iProduct.getType() != null){
            lqw.eq(Product::getType,iProduct.getType());
        }
        if (iProduct.getStatus() != null){
            lqw.eq(Product::getStatus,iProduct.getStatus());
        }
        if (iProduct.getAudit() != null){
            lqw.eq(Product::getAudit,iProduct.getAudit());
        }
        if (iProduct.getShopName() != null){
            lqw.eq(Product::getShopName,iProduct.getShopName());
        }
        if (iProduct.getClassifyId() != null){
            lqw.eq(Product::getClassifyId,iProduct.getClassifyId());
        }
        if (iProduct.getSelling() != null){
            lqw.eq(Product::getSelling,iProduct.getSelling());
        }
        if (iProduct.getParameter() != null){
            lqw.eq(Product::getParameter,iProduct.getParameter());
        }
        if (iProduct.getUnitMeasure() != null){
            lqw.eq(Product::getUnitMeasure,iProduct.getUnitMeasure());
        }
        if (iProduct.getImg() != null){
            lqw.eq(Product::getImg,iProduct.getImg());
        }
        if (iProduct.getVideo() != null){
            lqw.eq(Product::getVideo,iProduct.getVideo());
        }
        if (iProduct.getDescription() != null){
            lqw.eq(Product::getDescription,iProduct.getDescription());
        }
        if (iProduct.getIsDelete() != null){
            lqw.eq(Product::getIsDelete,iProduct.getIsDelete());
        }
        if (iProduct.getCreateTime() != null){
            lqw.eq(Product::getCreateTime,iProduct.getCreateTime());
        }
        if (iProduct.getUpdateTime() != null){
            lqw.eq(Product::getUpdateTime,iProduct.getUpdateTime());
        }
        if (iProduct.getBrandId() != null){
            lqw.eq(Product::getBrandId,iProduct.getBrandId());
        }
        return lqw;
    }
}




