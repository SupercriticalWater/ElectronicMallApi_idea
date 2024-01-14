package org.springboot.demomall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.springboot.demomall.entity.Favorite;

import java.util.List;
import java.util.Map;

public interface FavoriteMapper extends BaseMapper<Favorite> {
    @MapKey("uid")
    List<Map<String, Object>> selectByUid(Integer uid);
}
