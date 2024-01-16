package org.springboot.demomall.controller;

import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.News;
import org.springboot.demomall.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    /*
    查询
     */
    @GetMapping("/{nid}")
    public Result findByNid(@PathVariable Long nid){
        return Result.success(newsService.getById(nid),"查询成功");
    }

    @GetMapping
    public Result findAll(){
        List<News> list = newsService.getAllNews();
        return Result.success(list,"查询成功");
    }

    /*
    保存新闻图
     */
    //@Authority(AuthorityType.requireAuthority)
    @PostMapping
    public Result save(@RequestBody News news){
        newsService.saveOrUpdate(news);
        return Result.success();
    }

    //@Authority(AuthorityType.requireAuthority)
    @PutMapping
    public Result update(@RequestBody News news){
        newsService.updateById(news);
        return Result.success();
    }

    /*
    删除新闻
     */
    //@Authority(AuthorityType.requireAuthority)
    @DeleteMapping("/delete/{nid}")
    public Result delete(@PathVariable Long nid){
        newsService.removeById(nid);
        return Result.success();
    }
}
