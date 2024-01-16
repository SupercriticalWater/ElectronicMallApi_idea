package org.springboot.demomall.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.Message;
import org.springboot.demomall.entity.Order;
import org.springboot.demomall.service.OrderService;
import org.springboot.demomall.service.MessageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.sun.xml.internal.fastinfoset.stax.events.Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private MessageService messageService;

    /*
 查询
 */
    @GetMapping("/userid/{uid}")
    public Result selectByUserId(@PathVariable int uid) {
        return Result.success(orderService.selectByUid(uid),"查询成功！");
    }

    @GetMapping("/uidAndState")
    public Result selectByUidAndState(@RequestParam int uid,@RequestParam String state) {
        return Result.success(orderService.selectByUidAndState(uid,state),"查询成功！");
    }

    @GetMapping("/seller/{seller}")
    public Result selectBySeller(@PathVariable int seller) {
        return Result.success(orderService.selectBySeller(seller),"查询成功！");
    }

    @GetMapping("/sellerAndState")
    public Result selectBySellerAndState(@RequestParam int seller,@RequestParam String state) {
        return Result.success(orderService.selectBySellerAndState(seller,state),"查询成功！");
    }

    @GetMapping("/orderNo/{orderNo}")
    public Result selectByOrderNo(@PathVariable String orderNo) {
        return Result.success(orderService.selectByOrderNo(orderNo),"查询成功！");
    }

    @GetMapping
    public Result findAll() {
        List<Order> list = orderService.list();
        return Result.success(list,"查询成功！");
    }
    /*
     分页查询
     */
   /* @GetMapping("/page")
    public Result findPage(@RequestParam int pageNum,
                           @RequestParam int pageSize,
                           String orderNo,String state){
        IPage<Order> orderPage = new Page<>(pageNum,pageSize);
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.ne("state","待付款");
        if(!Util.isEmptyString(state)){
            orderQueryWrapper.eq("state",state);
        }
        if(!Util.isEmptyString(orderNo)){
            orderQueryWrapper.like("order_no",orderNo);
        }

        orderQueryWrapper.orderByDesc("create_time");
        return Result.success(orderService.page(orderPage,orderQueryWrapper),"分页查询成功！");
    }*/

    @PostMapping
    public Result save(@RequestBody Order order) {
        String orderNo = orderService.saveOrder(order);
        return Result.success(orderNo,"订单保存成功！");

    }

    //支付订单
    @GetMapping("/paid/{orderNo}")
    public Result payOrder(@PathVariable String orderNo){
        orderService.payOrder(orderNo);
        //买家通知
        Message message = new Message();
        message.setTitle("支付成功通知");
        message.setContent("亲爱的顾客，您的订单 【" + orderNo + "】已成功支付，请等待商家开始制作！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);

        //卖家通知
        Message message1 = new Message();
        message1.setTitle("订单生成通知");
        message1.setContent("您有新的订单 【" + orderNo + "】，请您及时处理并开始制作！");
        message1.setSender(1);//默认管理员编号
        u = (Number) map.get("seller");
        message1.setReceiver(u.intValue());
        message1.setCreateTime(DateUtil.now());
        messageService.save(message1);
        return Result.success();
    }

    @GetMapping("/making/{orderNo}")
    public Result StartMaking(@PathVariable String orderNo){
        orderService.startMaking(orderNo);
        //买家通知
        Message message = new Message();
        message.setTitle("售后服务通知");
        message.setContent("亲爱的顾客，您的订单 【" + orderNo + "】已开始制作，请耐心等待！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);
        return Result.success();
    }

    @GetMapping("/Belate/{orderNo}")
    public Result Belate(@PathVariable String orderNo){
        orderService.Belate(orderNo);
        Message message = new Message();
        message.setTitle("订单超时通知");
        message.setContent("很抱歉，您的订单 【" + orderNo + "】已超时，您可联系商家进行协商与处理, 感谢您的理解！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);

        //卖家通知
        Message message1 = new Message();
        message1.setTitle("订单超时通知");
        message1.setContent("您的订单 【" + orderNo + "】已超时，请您及时处理并联系买家！");
        message1.setSender(1);//默认管理员编号
        u = (Number) map.get("seller");
        message1.setReceiver(u.intValue());
        message1.setCreateTime(DateUtil.now());
        messageService.save(message1);

        return Result.success();
    }


    //退货
    @GetMapping("/ReturnOrder/{orderNo}")
    public Result ReturnOrder(@PathVariable String orderNo){
        orderService.ReturnOrder(orderNo);
        Message message = new Message();
        message.setTitle("退货成功通知");
        message.setContent("亲爱的顾客，您的订单 【" + orderNo + "】已成功退货，退款将原路退回您的账户！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);
        return Result.success();
    }



    //发货
  //  @Authority(AuthorityType.requireAuthority)
    @GetMapping("/delivery/{orderNo}")
    public Result delivery(@PathVariable String orderNo){
        orderService.delivery(orderNo);
        Message message = new Message();
        message.setTitle("发货通知");
        message.setContent("亲爱的顾客，您的订单 【" + orderNo + "】已发货，请您在收到商品后及时确认！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);
        return Result.success();
    }
    //确认收货
    @GetMapping("/received/{orderNo}")
    public Result receiveOrder(@PathVariable String orderNo){
        if(orderService.receiveOrder(orderNo)){
            Message message = new Message();
            message.setTitle("收货成功通知");
            message.setContent("商家您好，您的订单 【" + orderNo + "】已成功被买家确认收货，请您及时核实并选择完成订单！");
            message.setSender(1);//默认管理员编号
            Map<String,Object> map = orderService.selectByOrderNo(orderNo);
            Number u = (Number) map.get("seller");
            message.setReceiver(u.intValue());
            message.setCreateTime(DateUtil.now());
            messageService.save(message);
            return Result.success();
        }
        else {
            return Result.error(Constants.CODE_500,"确认收货失败");
        }
    }

    @GetMapping("/aftersales/{orderNo}")
    public Result AfterSales(@PathVariable String orderNo){
        orderService.AfterSales(orderNo);
        //买家通知
        Message message = new Message();
        message.setTitle("售后服务通知");
        message.setContent("亲爱的顾客，您的订单 【" + orderNo + "】已进入售后状态，请您耐心等待商家处理！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);
        //卖家通知
        Message message1 = new Message();
        message1.setTitle("售后服务通知");
        message1.setContent("您的订单 【" + orderNo + "】已进入售后状态，请您及时处理！");
        message1.setSender(1);//默认管理员编号
        u = (Number) map.get("seller");
        message1.setReceiver(u.intValue());
        message1.setCreateTime(DateUtil.now());
        messageService.save(message1);
        return Result.success();
    }

    @GetMapping("/finish/{orderNo}")
    public Result FinishOrder(@PathVariable String orderNo){
        orderService.FinishOrder(orderNo);
        //买家通知
        Message message = new Message();
        message.setTitle("订单完成通知");
        message.setContent("亲爱的顾客，您的订单 【" + orderNo + "】已完成，感谢您的使用！");
        message.setSender(1);//默认管理员编号
        Map<String,Object> map = orderService.selectByOrderNo(orderNo);
        Number u = (Number) map.get("uid");
        message.setReceiver(u.intValue());
        message.setCreateTime(DateUtil.now());
        messageService.save(message);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        orderService.removeById(id);
        return Result.success();
    }




}
