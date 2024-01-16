package org.springboot.demomall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("carousel")
public class Carousel extends Model<Carousel> {
    /**
     * 主键
     */
    @TableId(value = "carousel_id", type = IdType.AUTO)
    private Long carouselId;


    /**
     * 轮播图片url
     */
    private String fileUrl;

    /**
     * 轮播图文字信息
     */
    private String title;


    public Long getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }



    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "carouselId=" + carouselId +
                ", fileUrl='" + fileUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

