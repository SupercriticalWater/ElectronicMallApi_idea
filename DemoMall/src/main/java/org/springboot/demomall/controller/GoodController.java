package org.springboot.demomall.controller;

import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.Good;
import org.springboot.demomall.entity.Standard;
import org.springboot.demomall.entity.GoodStandard;
import org.springboot.demomall.service.GoodService;
import org.springboot.demomall.service.StandardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/good")
public class GoodController {

    @Resource
    private GoodService goodService;

    @Resource
    private StandardService standardService;

    @PostMapping("/{uid}")
    public Result save(@RequestBody Good good,@PathVariable Integer uid) {
        System.out.println(good);
        return Result.success(goodService.saveOrUpdateGood(good),"成功保存！");
    }

    @PutMapping
    public Result update(@RequestBody Good good) {
        goodService.update(good);
        return Result.success();
    }

    @DeleteMapping("/delete/{uid}/{gid}")
    public Result delete(@PathVariable Long gid, @PathVariable Integer uid) {
        goodService.deleteGood(gid,uid);
        return Result.success();
    }

    @GetMapping("/good/{gid}")
    public Result findByGid(@PathVariable Long gid) {
        return Result.success(goodService.getGoodByGid(gid), "查询成功！");
    }

    @GetMapping("/user/{uid}")
    public Result findByUid(@PathVariable Integer uid) {
        return Result.success(goodService.getGoodByUid(uid), "查询成功！");
    }

    @GetMapping
    public Result findFrontgoods() {

        return Result.success(goodService.findFrontGoods(),"查询成功！");
    }

    @GetMapping("/all")
    public Result findAll() {

        return Result.success(goodService.findAllGoods(),"查询成功！");
    }


    @GetMapping("/rank")
    public Result getSaleRank(@RequestParam int num){
        return Result.success(goodService.getSaleRank(num), "查询成功！");
    }
    //修改商品的推荐字段
    @Authority(AuthorityType.requireAuthority)
    @GetMapping("/recommend")
    public Result setRecommend(@RequestParam Long gid,@RequestParam Boolean isRecommend){
        return Result.success(goodService.setRecommend(gid,isRecommend),"推荐成功！");
    }

    @GetMapping("/page")
    public Result findPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String searchText,
            @RequestParam(required = false) Integer categoryId) {

        return Result.success(goodService.findPage(pageNum,pageSize,searchText,categoryId),"分页查询成功！");
    }
    @GetMapping("/fullPage")
    public Result findFullPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String searchText,
            @RequestParam(required = false) Integer categoryId) {

        return Result.success(goodService.findFullPage(pageNum,pageSize,searchText,categoryId),"查询成功！");
    }
    // 获取商品的规格信息
    @GetMapping("/standard/{gid}")
    public Result getStandard(@PathVariable int gid) {
        List<GoodStandard> standards = goodService.getStandard(gid);
        if (standards.isEmpty()) {
            // 没有查询到规格信息
            return Result.error(Constants.NO_RESULT, "未找到相关规格信息");
        }
        return Result.success(standards, "查询成功");
    }


    //保存商品的规格信息
    @PostMapping("/standardAll")
    public Result saveStandardAll(@RequestBody List<Standard> standards, @RequestParam int gid) {
        //先删除全部旧记录
        standardService.deleteAll(gid);
        //然后插入新记录
        for (Standard standard : standards) {
            standard.setGid(gid);

            if(!standardService.save(standard)){
                return Result.error(Constants.CODE_500,"保存失败");
            }
        }
        List<GoodStandard> standards_res = goodService.getStandard(gid);
        return Result.success(standards_res,"保存成功");
    }

    //保存商品的规格信息
    @PostMapping("/standard")
    public Result saveStandard(@RequestBody Standard standard, @RequestParam int gid, @RequestParam String value) {

        Standard oldstandard = standardService.FindStandardByPrikey(gid, value);
        System.out.println(oldstandard);
        //先删除全部旧记录
        standardService.delete(oldstandard);
        //然后插入新记录
        standard.setGid(gid);

        if (!standardService.save(standard)) {
            return Result.error(Constants.CODE_500, "保存失败");
        }

        return Result.success();
    }

    //新增商品的规格信息

    //插入一条新规格信息
    @PostMapping("/standardone")
    public Result insertStandard(@RequestBody Standard standard, @RequestParam int gid) {

        if(standardService.FindStandard(standard) != null){
            return Result.error(Constants.CODE_500,"规格信息已存在");
        }
        //standard.setGid(gid);
        return Result.success(standardService.addOne(standard),"新增成功");
    }

    //删除商品的规格信息
    @Authority(AuthorityType.requireAuthority)
    @DeleteMapping("/standard")
    public Result delStandard(@RequestBody Standard standard) {
        boolean delete = standardService.delete(standard);
        if(delete) {
            return Result.success(standard,"删除成功");
        }else {
            return Result.error(Constants.CODE_500,"删除失败");
        }
    }

}
