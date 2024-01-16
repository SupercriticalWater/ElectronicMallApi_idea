package org.springboot.demomall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.entity.Favorite;
import org.springboot.demomall.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteService extends ServiceImpl<FavoriteMapper, Favorite> {
    @Resource
    private FavoriteMapper favoriteMapper;

    public List<Map<String,Object>> selectByUid(Integer uid){
        return favoriteMapper.selectByUid(uid);
    }
    public void delete(long fid){
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid",fid);
        remove(queryWrapper);
    }

    public void deleteAll(long uid){
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        remove(queryWrapper);
    }


    public Favorite findFavorite(long uid, long gid){
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("gid", gid);
        return favoriteMapper.selectOne(queryWrapper);
    }

}
