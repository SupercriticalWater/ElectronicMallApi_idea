package org.springboot.demomall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.Standard;
import org.springboot.demomall.mapper.StandardMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StandardService extends ServiceImpl<StandardMapper, Standard> {
    @Resource
    private StandardMapper standardMapper;

    public boolean delete(Standard standard) {
        QueryWrapper<Standard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid",standard.getGid());
        queryWrapper.eq("value",standard.getValue());
        return remove(queryWrapper);
    }

    public void deleteAll(int GoodId) {
        QueryWrapper<Standard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid",GoodId);
        remove(queryWrapper);
    }



}
