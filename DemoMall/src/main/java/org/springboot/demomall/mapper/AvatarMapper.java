package org.springboot.demomall.mapper;

import org.apache.ibatis.annotations.*;
import org.springboot.demomall.entity.Avatar;

import java.util.List;

@Mapper
public interface AvatarMapper {
    @Insert("insert into avatar(type, size, url, md5) values(#{type},#{size},#{url},#{md5})")
    public void save(Avatar avatar);

    @Select("select * from avatar where md5 = #{md5}")
    public Avatar selectByMd5(String md5);

    @Select("select * from avatar where url = #{url}")
    public Avatar selectByUrl(String url);

    @Select("select * from avatar where id = #{id}")
    public Avatar selectById(int id);

    @Delete("delete from avatar where id = #{id}")
    int delete(int id);
    @Select("select * from avatar limit #{pageNum},#{pageSize}")
    List<Avatar> selectPage(@Param("pageNum") int PageNum,@Param("pageSize") int pageSize);
    @Select("select count(*) from avatar")
    int getTotal();
}
