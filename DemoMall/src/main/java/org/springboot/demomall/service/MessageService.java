package org.springboot.demomall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.Cart;
import org.springboot.demomall.entity.Message;
import org.springboot.demomall.mapper.CartMapper;
import org.springboot.demomall.mapper.FavoriteMapper;
import org.springboot.demomall.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message>{

    @Resource
    private MessageMapper messageMapper;

    public List<Map<String, Object>> selectBySender(int uid) {
        return messageMapper.selectBySender(uid);
    }

    public List<Map<String, Object>> selectByReceiver(int uid) {
        return messageMapper.selectByReceiver(uid);
    }

    public Map<String, Object> selectByMid(int mid) {
        return messageMapper.selectByMid(mid);
    }





}
