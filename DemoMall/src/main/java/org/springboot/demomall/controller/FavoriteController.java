package org.springboot.demomall.controller;

import org.springboot.demomall.common.Result;
import org.springboot.demomall.entity.Favorite;
import org.springboot.demomall.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//@Authority(AuthorityType.requireLogin)
@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {
    @Resource
    private FavoriteService favoriteService;

    @GetMapping("/{fid}")
    public Result selectByFid(@PathVariable Long fid){
        return Result.success(favoriteService.getById(fid),"查找成功");
    }

    //查找某个用户的收藏夹
    @GetMapping("/uid/{uid}")
    public Result selectByUid(@PathVariable Integer uid) {
        //List<Favorite>
        return Result.success(favoriteService.selectByUid(uid),"查询成功");
    }

    //将商品加入收藏夹
    @PostMapping
    public Result insert(@RequestBody Favorite favorite){
        favoriteService.saveOrUpdate(favorite);
        return Result.success(favorite,"加入收藏夹成功");
    }
    /*
    删除
     */
    //将某个商品从收藏夹移除
    @DeleteMapping("deleteone/{gid}")
    public Result delete(@PathVariable Long gid){
        favoriteService.delete(gid);
        return Result.success(gid,"删除成功");
    }

    //清空收藏夹
    @DeleteMapping("/{uid}")
    public Result deleteAll(@PathVariable Long uid){
        favoriteService.deleteAll(uid);
        return Result.success();
    }

}
