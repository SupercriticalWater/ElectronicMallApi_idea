package org.springboot.demomall.entity;

import java.awt.*;

public class OrderItem {
    /*
    商品id
     */
    private long gid;

    /*
    商品规格
     */
    private String standard;

    /*
    商品数量
     */
    private int num;

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "gid=" + gid +
                ", standard='" + standard + '\'' +
                ", num=" + num +
                '}';
    }
}
