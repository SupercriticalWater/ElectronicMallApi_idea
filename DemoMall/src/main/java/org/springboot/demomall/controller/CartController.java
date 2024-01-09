package org.springboot.demomall.controller;

import cn.hutool.core.date.DateUtil;
import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.Cart;
import org.springboot.demomall.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
//@Authority(AuthorityType.requireLogin)
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Resource
    private CartService cartService;

    @GetMapping("/{cid}")
    public Result selectById(@PathVariable Long cid) {
        return Result.success(cartService.getById(cid),"查询成功");
    }

    //查找所有用户的购物车
    @GetMapping
    public Result findAll() {
        List<Cart> list = cartService.list();
        return Result.success(list,"查询成功");
    }
    //查找某个用户的购物车
    @GetMapping("/userid/{userId}")
    public Result selectByUserId(@PathVariable Integer userId) {
        return Result.success(cartService.selectByUid(userId),"查询成功") ;
    }

    /*
    保存
    */
    @PostMapping
    public Result save(@RequestBody Cart cart) {
        cart.setCreateTime(DateUtil.now());
        cartService.saveOrUpdate(cart);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Cart cart) {
        cartService.updateById(cart);
        return Result.success();
    }

    /*
    删除
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        cartService.removeById(id);
        return Result.success();
    }





}
