package org.springboot.demomall.mapper;

import org.springboot.demomall.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;
public interface CartMapper extends BaseMapper<Cart> {
    @MapKey("uid")
    List<Map<String, Object>> selectByUid(Integer uid);
}
