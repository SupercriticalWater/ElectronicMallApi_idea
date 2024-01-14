package org.springboot.demomall.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("favorite")
public class Favorite extends Model<Favorite> {
    /**
     * 主键
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Long fid;

    /**
     * 买家编号
     */
    private Integer uid;

    /**
     * 商品编号
     */
    private Long gid;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String imgs;


    public Long getFid() {
        return fid;
    }

    public void setFid(Long cid) {
        this.fid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "fid=" + fid +
                ", uid=" + uid +
                ", gid=" + gid +
                ", name='" + name + '\'' +
                ", images='" + imgs + '\'' +
                '}';
    }
}
