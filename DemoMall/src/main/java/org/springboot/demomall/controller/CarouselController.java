package org.springboot.demomall.controller;

import org.springboot.demomall.common.Result;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.Carousel;
import org.springboot.demomall.service.CarouselService;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/carousel")
public class CarouselController {
    @Resource
    private CarouselService carouselService;

    /*
    查询
     */
    @GetMapping("/{carouselId}")
    public Result findById(@PathVariable Long carouselId){
        return Result.success(carouselService.getById(carouselId),"查询成功");
    }

    @GetMapping
    public Result findAll(){
        List<Carousel> list = carouselService.getAllCarousel();
        return Result.success(list,"查询成功");
    }

    /*
    保存轮播图
     */
    //@Authority(AuthorityType.requireAuthority)
    @PostMapping
    public Result save(@RequestBody Carousel carousel){
        carouselService.saveOrUpdate(carousel);
        return Result.success(carousel,"保存成功");
    }

    //@Authority(AuthorityType.requireAuthority)
    @PutMapping
    public Result update(@RequestBody Carousel carousel){
        carouselService.updateById(carousel);
        return Result.success(carousel,"更新成功");
    }

    /*
    删除轮播图
     */
    //@Authority(AuthorityType.requireAuthority)
    @DeleteMapping("/delete/{carouselId}")
    public Result delete(@PathVariable Long carouselId){
        Carousel carousel = carouselService.getById(carouselId);
        carouselService.removeById(carouselId);
        return Result.success(carousel,"删除成功");
    }
}