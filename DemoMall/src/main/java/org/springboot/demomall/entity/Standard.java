package org.springboot.demomall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;

@TableName("good_standard")
public class Standard extends Model<Standard>{
    /**
     * 主键：商品编号
     */
    @TableId
    private Integer gid;
    /**
     * 商品规格
     */
    private String value;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 工期
     */
    private Integer processTime;
    /**
     * 库存
     */
    private Integer store;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public void setProcess_time(Integer processTime) {
        this.processTime = processTime;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Standard{" +
                "gid=" + gid +
                ", value='" + value + '\'' +
                ", price=" + price +
                ", processTime=" + processTime +
                ", store=" + store +
                '}';
    }
}
