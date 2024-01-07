package org.springboot.demomall.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springboot.demomall.constants.Constants;
import org.springboot.demomall.entity.Good;
import org.springboot.demomall.entity.GoodStandard;
import org.springboot.demomall.entity.dto.GoodDTO;
import org.springboot.demomall.exception.ServiceException;
import org.springboot.demomall.mapper.GoodMapper;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.springboot.demomall.constants.RedisConstants.GOOD_TOKEN_KEY;
import static org.springboot.demomall.constants.RedisConstants.GOOD_TOKEN_TTL;

@Service
public class GoodService extends ServiceImpl<GoodMapper, Good>{
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private RedisTemplate<String, Good> redisTemplate;

    //查询一个商品的信息
    public Good getGoodByGid(Long gid) {
        String redisKey = GOOD_TOKEN_KEY + gid;
        //从redis中查，若有则返回
        ValueOperations<String, Good> valueOperations = redisTemplate.opsForValue();
        Good redisGood = valueOperations.get(redisKey);
        if(redisGood!=null){
            redisTemplate.expire(redisKey,GOOD_TOKEN_TTL, TimeUnit.MINUTES);
            return redisGood;
        }

        //若redis中没有则去数据库查
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Good::getDelete,false);
        queryWrapper.eq(Good::getGid,gid);
        Good dbGood = getOne(queryWrapper);
        if(dbGood!=null){
            //将商品信息存入redis
            valueOperations.set(redisKey,dbGood);
            redisTemplate.expire(redisKey,GOOD_TOKEN_TTL, TimeUnit.MINUTES);
            return dbGood;
        }
        //数据库中没有则返回异常
        throw new ServiceException(Constants.NO_RESULT,"无结果");

    }

    //查询商品的规格
    public String getStandard(int gid){
        List<GoodStandard> standards = goodMapper.getStandardByGid(gid);
        if(standards.size()==0){
            throw new ServiceException(Constants.NO_RESULT,"无结果");
        }
        return JSON.toJSONString(standards);
    }

    //查询某商品的最低规格价
    public BigDecimal getMinPrice(Long id){
        return goodMapper.getMinPrice(id);
    }

    //查询全部（首页推荐商品）
    public List<GoodDTO> findFrontGoods() {
        return goodMapper.findFrontGoods();
    }

    //假删除
    public void deleteGood(Long gid,Integer uid) {
        redisTemplate.delete(GOOD_TOKEN_KEY+gid);
        goodMapper.fakeDelete(gid, uid);
    }
    //保存商品信息
    public Long saveOrUpdateGood(Good good) {
        System.out.println(good);
        if(good.getGid()==null){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            good.setCreateTime(df.format(LocalDateTime.now()));
            goodMapper.insertGood(good);
        }else{
            saveOrUpdate(good);
            redisTemplate.delete(GOOD_TOKEN_KEY + good.getGid());
        }
        return good.getGid();
    }

    public boolean setRecommend(Long gid,Boolean isRecommend) {
        LambdaUpdateWrapper<Good> goodsLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        goodsLambdaUpdateWrapper.eq(Good::getGid,gid)
                .set(Good::getRecommend,isRecommend);
        return update(goodsLambdaUpdateWrapper);
    }

    public List<Good> getSaleRank(int num) {
        return goodMapper.getSaleRank(num);
    }

    public void update(Good good) {
        updateById(good);
        redisTemplate.delete(GOOD_TOKEN_KEY + good.getUid());
    }

    //分页查询
    public IPage<GoodDTO> findPage(Integer pageNum, Integer pageSize, String searchText, Integer categoryId) {
        LambdaQueryWrapper<Good> query = Wrappers.<Good>lambdaQuery().orderByDesc(Good::getUid);
        //对名称和描述进行模糊查询
        if (StrUtil.isNotBlank(searchText)) {
            query.like(Good::getName, searchText).or().like(Good::getDescription,searchText).or().eq(Good::getUid,searchText);
        }
        if(categoryId != null){
            query.eq(Good::getCategoryId,categoryId);
        }
        //筛除掉已被删除的商品
        query.eq(Good::getDelete,false);
        IPage<Good> page = this.page(new Page<>(pageNum, pageSize), query);
        //把good转为dto
        IPage<GoodDTO> goodDTOPage = page.convert(good -> {
            GoodDTO goodDTO = new GoodDTO();
            BeanUtil.copyProperties(good, goodDTO);
            return goodDTO;
        });
        for (GoodDTO good : goodDTOPage.getRecords()) {
            //附上最低价格
            good.setPrice(getMinPrice(good.getGid()));
        }
        return goodDTOPage;
    }
}
