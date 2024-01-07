package org.springboot.demomall.entity.dto;

import java.math.BigDecimal;

public class GoodDTO {
    private Long gid;
    private int uid;
    private String name;
    private String imgs;
    private BigDecimal price;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoodDTO{" +
                "gid=" + gid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", imgs='" + imgs + '\'' +
                ", price=" + price +
                '}';
    }
}
