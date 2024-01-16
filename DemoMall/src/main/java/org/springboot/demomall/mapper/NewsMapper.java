package org.springboot.demomall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springboot.demomall.entity.News;

import java.util.List;

public interface NewsMapper extends BaseMapper<News>{
    List<News> getAllNews();

}
