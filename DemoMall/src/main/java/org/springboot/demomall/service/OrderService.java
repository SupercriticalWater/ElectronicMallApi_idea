package org.springboot.demomall.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.entity.*;
import org.springboot.demomall.exception.ServiceException;
import org.springboot.demomall.mapper.GoodMapper;
import org.springboot.demomall.mapper.OrderGoodsMapper;
import org.springboot.demomall.mapper.OrderMapper;
import org.springboot.demomall.mapper.GoodMapper;
import org.springboot.demomall.service.StandardService;
import org.springboot.demomall.mapper.StandardMapper;
import org.springboot.demomall.utils.TokenUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import static org.springboot.demomall.constants.RedisConstants.GOOD_TOKEN_KEY;

@Component
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderGoodsMapper orderGoodsMapper;
    @Resource
    private StandardService standardService;
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private CartService cartService;

    @Resource
    private StandardMapper standardMapper;



    @Resource
    private RedisTemplate<String, Good> redisTemplate;

    @Transactional
    public String saveOrder(Order order) {
       // order.setUid(TokenUtils.getCurrentUser().getUid());
        String orderNo = DateUtil.format(new Date(), "yyyyMMddHHmmss") + RandomUtil.randomNumbers(6);
        order.setOrderNo(orderNo);
        order.setCreateTime(DateUtil.now());
        order.setState("待付款");

        //int daysToAdd = 5;
        orderMapper.insert(order);
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(order.getId());
        //遍历order里携带的goods数组，并用orderItem对象来接收
        String goods = order.getGoods();
        List<OrderItem> orderItems = JSON.parseArray(goods, OrderItem.class);
        for (OrderItem orderItem : orderItems) {
            int good_id = (int) orderItem.getGid();
            String standard = orderItem.getStandard();
            Standard st = standardService.FindStandardByPrikey(good_id,standard);
            int daysToAdd = st.getProcessTime();

            int count = orderItem.getCount();


            // 计算当前时间过n天后的时间
            LocalDateTime futureDateTime = LocalDateTime.now().plusDays(daysToAdd);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String deadline = futureDateTime.format(formatter);
            order.setDeadline(deadline);
            LambdaUpdateWrapper<Order> orderLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            orderLambdaUpdateWrapper.eq(Order::getOrderNo, orderNo)
                    .set(Order::getDeadline, deadline);
            update(orderLambdaUpdateWrapper);
            orderGoods.setGid(good_id);
            orderGoods.setCount(count);
            orderGoods.setStandard(standard);
            //插入到order_good表
            orderGoodsMapper.insert(orderGoods);
        }
        // 清除购物车
        cartService.removeById(order.getCid());
        return orderNo;
    }

    //给订单付款
    @Transactional
    public void payOrder(String orderNo) {
        //更改状态为已付款
        orderMapper.payOrder(orderNo);

        //给对应规格减库存
        Map<String, Object> orderMap = orderMapper.selectByOrderNo(orderNo);
      //  System.out.println(orderMap);

        int count = (int) orderMap.get("count");
        Object gidObj = orderMap.get("gid");
      //  System.out.println(gidObj);

        Long gid = null;
        if(gidObj instanceof Long) {
            gid = (Long) gidObj;
          //  System.out.println("*****test");
        } else if(gidObj != null) {
            try {
                gid = Long.parseLong(gidObj.toString());
                System.out.println(gid);

            } catch (NumberFormatException e) {
                throw new ServiceException(Constants.CODE_500, "商品ID不正确");
            }
        }

        if(gid == null) {
            throw new ServiceException(Constants.CODE_500, "商品ID不存在");
        }
        String standard = (String) orderMap.get("standard");
        //System.out.println(standard);
        int store = standardMapper.getStore(gid, standard);
        if (store < count) {
            throw new ServiceException(Constants.CODE_500, "库存不足");
        }
        standardMapper.deductStore(gid, standard, store - count);

        //给对应商品加销量和销售额
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getOrderNo, orderNo);
        Order one = getOne(orderLambdaQueryWrapper);
        BigDecimal totalPrice = one.getTotalPrice();
        goodMapper.saleGood(gid, count, totalPrice);

        // redis 增销量
        String redisKey = GOOD_TOKEN_KEY + gid;
        ValueOperations<String, Good> valueOperations = redisTemplate.opsForValue();
        Good good = valueOperations.get(redisKey);
        if(!ObjectUtils.isEmpty(good)) {
            good.setSales(good.getSales() + count);
            valueOperations.set(redisKey, good);
        }
    }
    //每隔5秒自动更新超时订单的状态为“已超时”
    @Scheduled(cron="0/5 * *  * * ? ")
    public void AutoUpdating(){
        String t = DateUtil.now();
        //System.out.println(t);
          List<String> res = orderMapper.selectLate(t);
          for (String item: res){
              orderMapper.Belate(item);
              System.out.println(t);
              System.out.println("查询到超时订单，已自动更新订单状态 [" + item + "]");
          }

    }

    public List<Map<String, Object>> selectByUid(int uid) {
        return orderMapper.selectByUid(uid);
    }

    public List<Map<String, Object>> selectByUidAndState(@Param("uid")int uid, @Param("state")String state){
        return orderMapper.selectByUidAndState(uid, state);
    }

    public List<Map<String, Object>> selectBySeller(int seller) {
        return orderMapper.selectBySeller(seller);
    }

    public List<Map<String, Object>> selectBySellerAndState(@Param("seller")int seller, @Param("state")String state){
        return orderMapper.selectBySellerAndState(seller, state);
    }

    public boolean receiveOrder(String orderNo) {
        return orderMapper.receiveOrder(orderNo);
    }

    public boolean startMaking(String orderNo) {
        return orderMapper.startMaking(orderNo);
    }

    public boolean FinishOrder(String orderNo) {
        return orderMapper.FinishOrder(orderNo);
    }

    public boolean Belate(String orderNo) {
        return orderMapper.Belate(orderNo);
    }

    public boolean ReturnOrder(String orderNo) {
        return orderMapper.ReturnOrder(orderNo);
    }

    public boolean AfterSales(String orderNo) {
        return orderMapper.AfterSalesOrder(orderNo);
    }

    public Map<String, Object> selectByOrderNo(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    public void delivery(String orderNo) {
        LambdaUpdateWrapper<Order> orderLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        orderLambdaUpdateWrapper.eq(Order::getOrderNo, orderNo)
                .set(Order::getState, "已发货");
        update(orderLambdaUpdateWrapper);
    }


}
