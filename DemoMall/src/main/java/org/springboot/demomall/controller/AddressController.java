package org.springboot.demomall.controller;


import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.entity.Address;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Authority(AuthorityType.requireLogin)
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    /*
    查询
    */
    @GetMapping("/{uid}")
    public Result findAllByUid(@PathVariable Long uid) {
        return Result.success(addressService.findAllByUid(uid),"查询成功");
    }

    //所有地址
    @GetMapping
    public Result findAll() {
        List<Address> list = addressService.list();
        return Result.success(list,"查询成功");
    }


    /*
    保存
    */
    @PostMapping
    public Result save(@RequestBody Address address) {
        boolean b = addressService.saveOrUpdate(address);
        if(b){
            return Result.success(address,"保存成功");
        }else{
            return Result.error(Constants.CODE_500,"保存地址失败");
        }

    }

    @PutMapping
    public Result update(@RequestBody Address address) {
        addressService.updateById(address);
        return Result.success();
    }

    /*
    删除
    */
    @DeleteMapping("/{aid}")
    public Result delete(@PathVariable Long aid) {
        addressService.removeById(aid);
        return Result.success(aid,"删除成功");
    }

}
