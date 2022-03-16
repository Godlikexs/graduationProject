package com.xxs.graduationproject.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-16
 */
public class XeGoods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 外键-商铺id
     */
    private Integer shopId;

    /**
     * 外键-分类id
     */
    private Integer cateId;

    /**
     * 外键-地区id
     */
    private Integer locaId;

    /**
     * 商品主标题
     */
    private String mainTitle;

    /**
     * 商品副标题
     */
    private String subTitle;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 购买人数
     */
    private Integer buy;

    /**
     * 商品图
     */
    private String goodsImg;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getLocaId() {
        return locaId;
    }

    public void setLocaId(Integer locaId) {
        this.locaId = locaId;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Override
    public String toString() {
        return "XeGoods{" +
        "id=" + id +
        ", shopId=" + shopId +
        ", cateId=" + cateId +
        ", locaId=" + locaId +
        ", mainTitle=" + mainTitle +
        ", subTitle=" + subTitle +
        ", price=" + price +
        ", oldPrice=" + oldPrice +
        ", buy=" + buy +
        ", goodsImg=" + goodsImg +
        "}";
    }
}
