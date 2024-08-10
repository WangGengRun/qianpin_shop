package com.itbuka.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itbuka.goods.domain.*;
import com.itbuka.goods.mapper.ClassifyMapper;
import com.itbuka.goods.mapper.ProductMapper;
import com.itbuka.goods.mapper.SpecMapper;
import com.itbuka.goods.service.DetailService;
import com.itbuka.goods.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailServiceImpl implements DetailService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ClassifyMapper classifyMapper;
    @Autowired
    private ProductDetailsService productDetailsService;
    @Autowired
    private SpecMapper specMapper;



    //id查询分类对象
    public Classify findClassify(Long id) {
        LambdaQueryWrapper<Classify> lqw = new LambdaQueryWrapper();
        lqw.eq(Classify::getId, id);
        lqw.eq(Classify::getIsDelete, 0);
        Classify classify = classifyMapper.selectOne(lqw);
        return classify;
    }

    @Override
    public Detail findDetail(Long id) {
        Detail detail=new Detail();
        //根据id查询商品对象
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getId, id);
        lqw.eq(Product::getIsDelete, 0);
        Product product = productMapper.selectOne(lqw);
        Classify classify = this.findClassify(product.getClassifyId());
        //分类对象名称
        StringBuilder classifyName = new StringBuilder();
        if (classify != null) {
            classifyName.append(classify.getClassifyName());
            //如果有上级id，继续查询
            if (classify.getIsuperiorId() != 0) {
                Classify classify1 = this.findClassify(classify.getIsuperiorId());
                classifyName.insert(0, classify1.getClassifyName() + ">");
                //如果有上级id，继续查询
                if (classify1.getIsuperiorId() != 0) {
                    Classify classify2 = this.findClassify(classify1.getIsuperiorId());
                    classifyName.insert(0, classify2.getClassifyName() + ">");
                }
            }
        }
        //把specid转换成specname
        ProductDetails p = new ProductDetails();
        p.setProductId(id);
        p.setIsDelete(0);
        List<ProductDetails> list = productDetailsService.selectList(p);
        for (ProductDetails productDetails : list) {
            String specId = productDetails.getSpecId();
            String[] split = specId.split(",");
            String name = "";
            int count = 0;
            for (String s : split) {
                Spec spec = specMapper.selectById(Long.valueOf(s));
                if (count == 0) {
                    name = name + spec.getSpecValue();
                } else {
                    name = name + " " + spec.getSpecValue();
                }
                count++;
            }
            productDetails.setSpecId(name);
        }
        //基本信息
        detail.setId(Long.valueOf(product.getId().toString()));
        detail.setCategory(classifyName.toString());
        detail.setName(product.getName());
        detail.setSelling(product.getSelling());
        detail.setParameter(product.getParameter());
        //商品交易信息
        detail.setUnitMeasure(product.getUnitMeasure());
        detail.setSalesModel(product.getSalesModel());
        //商品规格及图片
        detail.setPrice(product.getPrice().doubleValue());
        detail.setImg(product.getImg());
        detail.setVideo(product.getVideo());
        Map map= new HashMap<>();
        map.put("spec",list);
        detail.setSpec(map);
        detail.setDescription(product.getDescription());
        return  detail;
    }
}
