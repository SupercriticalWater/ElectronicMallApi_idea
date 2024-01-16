package org.springboot.demomall.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("news")
public class News extends Model<News> {
    /**
     * 主键
     */
    @TableId(value = "nid",type = IdType.AUTO)
    private Long nid;

    /**
     * 新闻url
     */
    private String fileUrl;

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
