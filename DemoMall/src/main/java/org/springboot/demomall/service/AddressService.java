package org.springboot.demomall.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.Address;
import org.springboot.demomall.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressService extends ServiceImpl<AddressMapper, Address> {

    @Resource
    private AddressMapper addressMapper;

    public List<Address> findAllByUid(Long uid) {
        QueryWrapper<Address> listQueryWrapper = new QueryWrapper<>();
        listQueryWrapper.eq("uid",uid);
        return list(listQueryWrapper);
    }


}
