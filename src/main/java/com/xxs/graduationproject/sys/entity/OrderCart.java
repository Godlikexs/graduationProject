package com.xxs.graduationproject.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-15
 */
public class OrderCart extends MyPage implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer skuId;

      @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    private Integer spuId;

    private Integer shopId;

    private String goodsName;

    private Integer goodsNumber;

    private BigDecimal goodsPrice;//购物车小计

    /**
     * 商品图
     */
    @TableField(exist = false)//从产品表中查询图片
    private String goodsImg;

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * 商品价格
     */
    @TableField(exist = false)//从产品表中查询单价
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 下单后就不在购物车；0 未下单  1.下单 2.删除
     */
    private Integer state;


    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderCart{" +
                "skuId=" + skuId +
                ", cartId=" + cartId +
                ", spuId=" + spuId +
                ", shopId=" + shopId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNumber=" + goodsNumber +
                ", goodsPrice=" + goodsPrice +
                ", goodsImg='" + goodsImg + '\'' +
                ", price=" + price +
                ", state=" + state +
                '}';
    }
}
