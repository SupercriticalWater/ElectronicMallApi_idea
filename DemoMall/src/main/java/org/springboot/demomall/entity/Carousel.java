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
     * 轮播顺序
     */
    private Long showOrder;

    /**
     * 轮播图片url
     */
    private String fileUrl;


    public Long getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }


    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "carouselId=" + carouselId +
                ", showOrder=" + showOrder +
                ", fileImage='" + fileUrl + '\'' +
                '}';
    }
}

