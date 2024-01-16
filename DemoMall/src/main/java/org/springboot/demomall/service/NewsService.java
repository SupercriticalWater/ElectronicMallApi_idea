package org.springboot.demomall.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.News;
import org.springboot.demomall.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsService extends ServiceImpl<NewsMapper, News> {

    @Resource
    private NewsMapper newsMapper;

    public List<News> getAllNews(){
        return newsMapper.getAllNews();
    }

}
