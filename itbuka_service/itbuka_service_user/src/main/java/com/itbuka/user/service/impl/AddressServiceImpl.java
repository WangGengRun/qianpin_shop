package com.itbuka.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.Address;
import com.itbuka.user.mapper.AddressMapper;
import com.itbuka.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_address】的数据库操作Service实现
* @createDate 2024-08-13 10:59:47
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService {

    @Autowired
    private AddressMapper iAddressMapper;

    @Override
    public List<Address> selectAll() {
        LambdaQueryWrapper<Address> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Address::getIsDelete,0);
        return iAddressMapper.selectList(lqw);
    }


    @Override
    public List<Address> selectList(Address iAddress) {
        LambdaQueryWrapper <Address> lqw = this.lqw(iAddress);
        return iAddressMapper.selectList(lqw);
    }

    @Override
    public Integer insert(Address iAddress) {
        return iAddressMapper.insert(iAddress);
    }

    @Override
    @Transactional
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Address iAddress = new Address();
                iAddress.setId(Long.parseLong(id));
                iAddress.setIsDelete(1);
                iAddressMapper.updateById(iAddress);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public Integer update(Address iAddress) {
        return iAddressMapper.updateById(iAddress);
    }

    @Override
    public Page<Address> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Address>)this.selectAll();
    }

    @Override
    public Page<Address> pageList(Address iAddress, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Address>)this.selectList(iAddress);
    }

    public LambdaQueryWrapper lqw(Address iAddress){
        LambdaQueryWrapper <Address> lqw = new LambdaQueryWrapper<>();
        if (iAddress.getId() != null){
            lqw.eq(Address::getId,iAddress.getId());
        }
        if (iAddress.getUserName() != null){
            lqw.eq(Address::getUserName,iAddress.getUserName());
        }
        if (iAddress.getAddressee() != null){
            lqw.eq(Address::getAddressee,iAddress.getAddressee());
        }
        if (iAddress.getReceivingArea() != null){
            lqw.eq(Address::getReceivingArea,iAddress.getReceivingArea());
        }
        if (iAddress.getFullAddress() != null){
            lqw.eq(Address::getFullAddress,iAddress.getFullAddress());
        }
        if (iAddress.getPhoneNum() != null){
            lqw.eq(Address::getPhoneNum,iAddress.getPhoneNum());
        }
        if (iAddress.getDefaultAddress() != null){
            lqw.eq(Address::getDefaultAddress,iAddress.getDefaultAddress());
        }
        if (iAddress.getCreateTime() != null){
            lqw.eq(Address::getCreateTime,iAddress.getCreateTime());
        }
        if (iAddress.getUpdateTime() != null){
            lqw.eq(Address::getUpdateTime,iAddress.getUpdateTime());
        }
        if (iAddress.getIsDelete() != null){
            lqw.eq(Address::getIsDelete,iAddress.getIsDelete());
        }
        return lqw;
    }
}




