package org.springboot.demomall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_file")
public class MyFile {
    @TableId(
            type = IdType.AUTO
    )
    private Integer id;
    private String name;
    private String type;
    private long size;
    private String url;
    @TableField("is_delete")
    private boolean isDelete;
    private boolean enable;
    private String md5;

    public MyFile() {
    }

    public MyFile(int id, String name, String type, long size, String url, boolean isDelete, boolean enable, String md5) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
        this.isDelete = isDelete;
        this.enable = enable;
        this.md5 = md5;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public void setDelete(boolean delete) {
        this.isDelete = delete;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String toString() {
        return "MyFile{id=" + this.id + ", name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", size=" + this.size + ", url='" + this.url + '\'' + ", isDelete=" + this.isDelete + ", enable=" + this.enable + ", md5='" + this.md5 + '\'' + '}';
    }
}
