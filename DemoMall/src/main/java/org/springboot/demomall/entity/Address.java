package org.springboot.demomall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("address")
public class Address extends Model<Address> {
    /**
     * 主键
     */
    @TableId(value = "aid", type = IdType.AUTO)
    private Long aid;

    /**
     * 联系人
     */
    private String linkUser;

    /**
     * 联系地址
     */
    private String linkAddress;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 所属用户
     */
    private Long uid;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(String linkUser) {
        this.linkUser = linkUser;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", linkUser='" + linkUser + '\'' +
                ", linkAddress='" + linkAddress + '\'' +
                ", linkPhone='" + linkPhone + '\'' +
                ", uid=" + uid +
                '}';
    }
}
