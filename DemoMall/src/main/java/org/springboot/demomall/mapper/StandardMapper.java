package org.springboot.demomall.mapper;

import org.springboot.demomall.entity.Standard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface StandardMapper extends BaseMapper<Standard> {
    @Update("update good_standard set store = #{num} where gid = #{gid} and value = #{standard}")
    void deductStore(@Param("gid") long gid, @Param("standard") String standard, @Param("num") int num);

    @Select("select store from good_standard where gid = #{gid} and value = #{standard}")
    int getStore(@Param("gid") long gid, @Param("standard")String standard);


}
