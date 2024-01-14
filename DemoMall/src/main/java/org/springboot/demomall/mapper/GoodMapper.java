package org.springboot.demomall.mapper;

import org.springboot.demomall.entity.Good;
import org.springboot.demomall.entity.GoodStandard;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springboot.demomall.entity.dto.GoodDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
public interface GoodMapper extends BaseMapper<Good> {
    @Select("select * from good_standard where gid = #{gid}")
    List<GoodStandard> getStandardByGid(int gid);

    @Select("select * from good where gid = #{gid} and is_delete = 0")
    List<GoodDTO> getGoodByGid(int gid);

    @Select("select * from good where uid = #{uid} and is_delete = 0")
    List<Good> getGoodByUid(int uid);
    @Select("select * from good where is_delete = 0")
    List<Good> findAllGoods();
    List<GoodDTO> findFrontGoods();

    @Update("update good set is_delete = 1 where gid = #{gid} and uid = #{uid}  ")
    void fakeDelete(@Param("gid")Long gid,@Param("uid")Integer uid);
    void insertGood(@Param("good") Good good);
    @Select("SELECT MIN(price) FROM good_standards, good WHERE good.gid = gs.gid AND good.gid = #{gid} ")
    BigDecimal getMinPrice(Long gid);
    boolean saleGood(@Param("gid")Long gid,@Param("count") int count,@Param("money") BigDecimal totalPrice);


    @Select("SELECT * FROM `good` WHERE is_delete = 0 ORDER BY sale_money DESC LIMIT 0,#{num}")
    List<Good> getSaleRank(int num);



}
