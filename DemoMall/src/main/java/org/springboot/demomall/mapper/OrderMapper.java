package org.springboot.demomall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface OrderMapper extends BaseMapper<OrderMapper> {
    @Update("update t_order set state = '已支付' where order_no = #{orderNo}")
    void payOder(String orderNo);

    @MapKey("id")
    List<Map<String, Object>> selectByUid(int uid);

    @Update("update t_order set state = '已收货' where order_no = #{orderNo}")
    boolean receiveOrder(String orderNo);

    Map<String, Object> selectByOrderNo(String orderNo);

}
