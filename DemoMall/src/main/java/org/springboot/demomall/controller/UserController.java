package org.springboot.demomall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import org.springboot.demomall.annotation.Authority;
import org.springboot.demomall.common.Result;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.entity.AuthorityType;
import org.springboot.demomall.entity.LoginForm;
import org.springboot.demomall.entity.User;
import org.springboot.demomall.entity.dto.UserDTO;
import org.springboot.demomall.service.UserService;
import org.springboot.demomall.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
这个注解表示该控制器下所有接口都可以通过跨域访问，注解内可以指定某一域名
也可以配置config类
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        UserDTO dto = userService.login(loginForm);
        if(dto!=null){
            return Result.success(dto,"登录成功！");
        }else{
            return Result.error("123","账号或密码错误！");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody LoginForm loginForm) {
        User user = userService.register(loginForm);
        if(user!=null){
            return Result.success(user,"注册成功！");
        }else{
            return Result.error("123","注册失败！");
        }
    }

    @GetMapping("/userinfo/{username}")
    public Result getUserInfoByName(@PathVariable String username) {
        User one = userService.getOne(username);
        return Result.success(one,"");
    }

    @GetMapping("/userid")
    public long getUserId() {
        return TokenUtils.getCurrentUser().getUid();
    }

    @GetMapping("/user/")
    public Result findAll() {
        List<User> list = userService.list();
        return Result.success(list,"");
    }

    @PostMapping("/user")
    public Result save(@RequestBody User user) {

        return userService.saveUpdate(user);
    }

    @Authority(AuthorityType.requireAuthority)
    @DeleteMapping("/delete/user/{id}")
    public Result deleteById(@PathVariable int id) {
        boolean isSuccessful = userService.removeById(id);
        if (isSuccessful) {
            return Result.success();
        } else {
            return Result.error(Constants.CODE_500, "删除失败");
        }
    }

    @Authority(AuthorityType.requireAuthority)
    @PostMapping("/user/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        boolean isSuccessful = userService.removeBatchByIds(ids);
        if (isSuccessful) {
            return Result.success();
        } else {
            return Result.error(Constants.CODE_500, "删除失败");
        }
    }

    @GetMapping("/userinfo/uid/{uid}")
    public Result getUserInfoByUid(@PathVariable Integer uid) {
        User one = userService.getOneByUid(uid);
        return Result.success(one,"查询成功");
    }

   @GetMapping("/user/page")
    public Result findPage(@RequestParam int pageNum,
                          @RequestParam int pageSize,
                          String id,
                          String username
                         // String nickname
                                        ) {
        IPage<User> userPage = new Page<>(pageNum, pageSize);
       QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (!Util.isEmptyString(id)) {
            userQueryWrapper.like("id", id);
        }
       if (!Util.isEmptyString(username)) {
           userQueryWrapper.like("username", username);
        }
        //if (!Util.isEmptyString(nickname)) {
//        //    userQueryWrapper.like("nickname", nickname);
//        //}
        userQueryWrapper.orderByDesc("id");
        System.out.println("============" + TokenUtils.getCurrentUser());
        return Result.success(userService.page(userPage, userQueryWrapper),"分页查询成功");
    }

    /**
     * 重置密码
     *
     * @param uid          用户id
     * @param newPassword 新密码
     * @return 结果
     */
    @GetMapping("/user/resetPassword")
    public Result resetPassword(@RequestParam Integer uid, @RequestParam String newPassword) {
        userService.resetPassword(uid, newPassword);
        return Result.success();
    }
}