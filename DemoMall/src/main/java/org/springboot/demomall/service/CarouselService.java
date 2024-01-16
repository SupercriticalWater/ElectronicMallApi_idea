package org.springboot.demomall.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.Carousel;
import org.springboot.demomall.mapper.CarouselMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CarouselService extends ServiceImpl<CarouselMapper, Carousel> {

    @Resource
    private CarouselMapper carouselMapper;

    public List<Carousel> getAllCarousel() {
        return carouselMapper.getAllCarousel();
    }


}


