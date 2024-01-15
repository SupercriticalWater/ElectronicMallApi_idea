package org.springboot.demomall.controller;

import cn.hutool.core.date.DateUtil;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.Message;
import org.springboot.demomall.service.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springboot.demomall.service.UserService;

import javax.annotation.Resource;
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/{mid}")
    public Result selectByMid(@PathVariable int mid){
        return Result.success(messageService.selectByMid(mid),"查找成功");
    }

    //查找某个用户的发送的消息
    @GetMapping("/sender/{uid}")
    public Result selectBySender(@PathVariable Integer uid) {
        //List<Favorite>
        return Result.success(messageService.selectBySender(uid),"查询成功");
    }

    //查找某个用户的发送的消息
    @GetMapping("/receiver/{uid}")
    public Result selectByReceiver(@PathVariable Integer uid) {
        //List<Favorite>
        return Result.success(messageService.selectByReceiver(uid),"查询成功");
    }

    @PostMapping
    public Result save(@RequestBody Message message) {
        message.setCreateTime(DateUtil.now());
        messageService.save(message);
        return Result.success();
    }

    @DeleteMapping("/{mid}")
    public Result delete(@PathVariable Long mid) {
        messageService.removeById(mid);
        return Result.success();
    }

}
