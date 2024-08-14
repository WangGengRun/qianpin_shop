package com.itbuka.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.Address;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_address】的数据库操作Service
* @createDate 2024-08-13 10:59:47
*/
public interface AddressService extends IService<Address> {
    /**
     * 查询全部
     * @param
     * @return
     */
    List<Address> selectAll();

    /**
     * 条件查询
     * @param iAddress
     * @return
     */
    List<Address> selectList(Address iAddress);

    /**
     * 插入一条数据
     * @param iAddress
     * @return
     */
    Integer insert(Address iAddress);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iAddress
     * @return
     */
    Integer update(Address iAddress);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Address> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iAddress
     * @param page
     * @param size
     * @return
     */
    Page<Address> pageList(Address iAddress, Integer page, Integer size);
}
