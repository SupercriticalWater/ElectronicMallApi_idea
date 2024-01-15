package org.springboot.demomall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springboot.demomall.entity.Good;
import org.springboot.demomall.entity.GoodStandard;
import org.springboot.demomall.entity.Message;
import org.springboot.demomall.entity.OrderGoods;

import java.util.List;
import java.util.Map;

public interface MessageMapper extends BaseMapper<Message> {

    Map<String, Object> selectByMid(int mid);

    List<Map<String, Object>> selectBySender(int sender);

    List<Map<String, Object>> selectByReceiver(int receiver);

    @Insert("insert into message(sender, receiver, title, content) values(#{sender},#{receiver},#{title},#{content})")
    Boolean insertMessage(Message message );
}
