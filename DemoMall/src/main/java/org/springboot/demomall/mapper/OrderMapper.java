package org.springboot.demomall.mapper;

import cn.hutool.core.date.DateTime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springboot.demomall.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderMapper extends BaseMapper<Order> {
    @Update("update t_order set state = '已支付' where order_no = #{orderNo}")
    void payOrder(String orderNo);

    @Update("update t_order set state = '已退货' where order_no = #{orderNo}")
    boolean ReturnOrder(String orderNo);


    @Update("update t_order set state = '制作中' where order_no = #{orderNo}")
    boolean startMaking(String orderNo);

    @Update("update t_order set state = '已超时' where order_no = #{orderNo}")
    boolean Belate(String orderNo);

    @Update("update t_order  set state = '已超时' where id in (select t_order.id from (select id from t_order  where  #{t} > deadline and  state = '制作中' as a)  ) ")
    boolean AutoUpdating(DateTime t);

    @Select("select order_no from t_order  where #{t} > deadline and  (state = '制作中' or state = '已支付') ")
    List<String> selectLate(String t);

   @MapKey("id")
    List<Map<String, Object>> selectByUid(int uid);

    List<Map<String, Object>> selectByUidAndState(@Param("uid")int uid, @Param("state")String state);

 //   @MapKey("id")
    List<Map<String, Object>> selectBySeller(int seller);

    List<Map<String, Object>> selectBySellerAndState(@Param("seller")int seller, @Param("state")String state);

    @Update("update t_order set state = '已收货' where order_no = #{orderNo}")
    boolean receiveOrder(String orderNo);

    @Update("update t_order set state = '售后中' where order_no = #{orderNo}")
    boolean AfterSalesOrder(String orderNo);

    @Update("update t_order set state = '已完成' where order_no = #{orderNo}")
    boolean FinishOrder(String orderNo);

    Map<String, Object> selectByOrderNo(String orderNo);

}
