package org.springboot.demomall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("cart")
public class Cart extends Model<Cart> {
    /**
     * 主键
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;
    /**
     * 买家编号
     */
    private Integer uid;

    private Integer seller;

    /**
     * 商品编号
     */
    private Long gid;

    /**
     * 商品规格
     */
    private String standard;

    /**
     * 加入购物车时间
     */
    private String createTime;
    /**
     * 加入购物车商品数量
     */
    private Integer count;


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", seller=" + seller +
                ", gid=" + gid +
                ", standard='" + standard + '\'' +
                ", createTime='" + createTime + '\'' +
                ", count=" + count +
                '}';
    }
}
