package org.springboot.demomall.mapper;

import org.springboot.demomall.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface OrderMapper extends BaseMapper<Order> {
    @Update("update t_order set state = '已支付' where order_no = #{orderNo}")
    void payOrder(String orderNo);

    @Update("update t_order set state = '制作中' where order_no = #{orderNo}")
    boolean startMaking(String orderNo);

    @Update("update t_order set state = '已超时' where order_no = #{orderNo}")
    boolean Belate(String orderNo);

   @MapKey("id")
    List<Map<String, Object>> selectByUid(int uid);

 //   @MapKey("id")
    List<Map<String, Object>> selectBySeller(int seller);


    @Update("update t_order set state = '已收货' where order_no = #{orderNo}")
    boolean receiveOrder(String orderNo);

    @Update("update t_order set state = '售后中' where order_no = #{orderNo}")
    boolean AfterSalesOrder(String orderNo);

    @Update("update t_order set state = '已完成' where order_no = #{orderNo}")
    boolean FinishOrder(String orderNo);

    Map<String, Object> selectByOrderNo(String orderNo);

}
