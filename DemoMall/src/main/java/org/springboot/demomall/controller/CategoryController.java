package org.springboot.demomall.controller;

import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.Category;
import org.springboot.demomall.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")

public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /*
   查询
   */
    //查询单个商品类别
    @GetMapping("/{categoryId}")
    public Result findByCid(@PathVariable Long categoryId) {
        return Result.success(categoryService.getById(categoryId),"查询成功");
    }

    //查询所有商品类别
    @GetMapping
    public Result findAll() {
        List<Category> list = categoryService.list();
        return Result.success(list,"查询成功");
    }

    /*
    保存
     */
    @PostMapping
    public Result save(@RequestBody Category category) {
        categoryService.saveOrUpdate(category);
        return Result.success(category,"保存成功");
    }

    /*
    删除
    */
    @DeleteMapping("/{categoryId}")
    public Result delete(@PathVariable Long categoryId) {
        Category category = categoryService.getById(categoryId);
        categoryService.removeById(categoryId);
        return Result.success(category,"删除成功");
    }
}
