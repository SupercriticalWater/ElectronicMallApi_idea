package org.springboot.demomall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.Category;
import org.springboot.demomall.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {
    @Resource
    private CategoryMapper categoryMapper;

    /*
    删除分类
     */
    public boolean delete(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("categoryId",category.getCategoryId());
        return remove(queryWrapper);
    }


}
