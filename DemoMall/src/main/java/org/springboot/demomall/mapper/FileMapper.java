package org.springboot.demomall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springboot.demomall.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapper<MyFile> {
}