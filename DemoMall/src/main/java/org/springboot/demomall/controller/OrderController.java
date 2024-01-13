package org.springboot.demomall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.Order;
import org.springboot.demomall.service.OrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /*
 查询
 */
    @GetMapping("/userid/{uid}")
    public Result selectByUserId(@PathVariable int uid) {
        return Result.success(orderService.selectByUid(uid),"查询成功！");
    }

    @GetMapping("/seller/{seller}")
    public Result selectBySeller(@PathVariable int seller) {
        return Result.success(orderService.selectBySeller(seller),"查询成功！");
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
    @GetMapping("/page")
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
    }

    @PostMapping
    public Result save(@RequestBody Order order) {
        String orderNo = orderService.saveOrder(order);
        return Result.success(orderNo,"订单保存成功！");

    }

    //支付订单
    @GetMapping("/paid/{orderNo}")
    public Result payOrder(@PathVariable String orderNo){
        orderService.payOrder(orderNo);
        return Result.success();
    }

    @GetMapping("/making/{orderNo}")
    public Result StartMaking(@PathVariable String orderNo){
        orderService.startMaking(orderNo);
        return Result.success();
    }

    @GetMapping("/Belate/{orderNo}")
    public Result Belate(@PathVariable String orderNo){
        orderService.Belate(orderNo);
        return Result.success();
    }



    //发货
  //  @Authority(AuthorityType.requireAuthority)
    @GetMapping("/delivery/{orderNo}")
    public Result delivery(@PathVariable String orderNo){
        orderService.delivery(orderNo);
        return Result.success();
    }
    //确认收货
    @GetMapping("/received/{orderNo}")
    public Result receiveOrder(@PathVariable String orderNo){
        if(orderService.receiveOrder(orderNo)){
            return Result.success();
        }
        else {
            return Result.error(Constants.CODE_500,"确认收货失败");
        }
    }

    @GetMapping("/aftersales/{orderNo}")
    public Result AfterSales(@PathVariable String orderNo){
        orderService.AfterSales(orderNo);
        return Result.success();
    }

    @GetMapping("/finish/{orderNo}")
    public Result FinishOrder(@PathVariable String orderNo){
        orderService.FinishOrder(orderNo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        orderService.removeById(id);
        return Result.success();
    }




}
