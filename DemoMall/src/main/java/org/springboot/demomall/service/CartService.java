package org.springboot.demomall.service;

import org.springboot.demomall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.mapper.CartMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {
    @Resource
    private CartMapper cartMapper;

    public List<Map<String, Object>> selectByUid(Integer uid) {
        return cartMapper.selectByUid(uid);
    }
}
