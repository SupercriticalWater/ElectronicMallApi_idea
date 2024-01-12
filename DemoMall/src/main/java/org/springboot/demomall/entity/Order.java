package org.springboot.demomall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;

@TableName("t_order")
public class Order extends Model<Order> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 用户id
     */
    private int uid;

    /**
     * 联系人
     */
    private String linkUser;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 地址
     */
    private String linkAddress;

    /**
     * 订单状态
     */
    private String state;

    /**
     * 创建时间
     */
    private String createTime;
    private String deadline;

    //该订单包含的商品信息
    @TableField(exist = false)
    private String goods;

    // 对应购物车id
    @TableField(exist = false)
    private Long cartId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(String linkUser) {
        this.linkUser = linkUser;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", totalPrice=" + totalPrice +
                ", uid=" + uid +
                ", linkUser='" + linkUser + '\'' +
                ", linkPhone='" + linkPhone + '\'' +
                ", linkAddress='" + linkAddress + '\'' +
                ", state='" + state + '\'' +
                ", createTime='" + createTime + '\'' +
                ", deadline='" + deadline + '\'' +
                ", goods='" + goods + '\'' +
                ", cartId=" + cartId +
                '}';
    }
}
