package com.itbuka.ad.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.ad.domain.Ad;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王庚润
* @description 针对表【ad(广告表)】的数据库操作Mapper
* @createDate 2024-08-03 09:45:17
* @Entity com.itbuka.ad.domain.Ad
*/
@Mapper
public interface AdMapper extends BaseMapper<Ad> {

}




