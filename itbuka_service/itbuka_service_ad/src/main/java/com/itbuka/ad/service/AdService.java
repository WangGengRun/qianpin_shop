package com.itbuka.ad.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.ad.domain.Ad;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【ad(广告表)】的数据库操作Service
* @createDate 2024-08-03 09:45:17
*/
public interface AdService extends IService<Ad> {

    List<Ad> selectAll();

    List<Ad> selectList(Ad iAd);

    int insert(Ad iAd);

    int delete(String ids);

    int update(Ad iAd);

    Page<Ad> pageAll(Integer page, Integer size);

    Page<Ad> pageList(Ad iAd, Integer page, Integer size);
}
