package org.springboot.demomall.entity;

public class GoodStandard {
    private int gid;
    private String value;
    private double price;
    private int processTime;
    private int store;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void setProcessTime(int process_time) {
        this.processTime = process_time;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "GoodStandard{" +
                "gid=" + gid +
                ", value='" + value + '\'' +
                ", price=" + price +
                ", processTime=" + processTime +
                ", store=" + store +
                '}';
    }
}
