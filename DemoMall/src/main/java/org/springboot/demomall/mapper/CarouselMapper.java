package org.springboot.demomall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springboot.demomall.entity.Carousel;

import java.util.List;

public interface CarouselMapper extends BaseMapper<Carousel> {

    List<Carousel> getAllCarousel();
}
