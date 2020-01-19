package com.wei.category.entity;

import java.io.Serializable;

/**
 * 传输给各业务系统的分类对象
 * 
 * @author : weihui
 * @createTime : 2016年8月29日 下午4:12:45
 * @version : 1.0
 * @description :
 *
 */
public class CategoryDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    String code;
    String title;
    String parentCode;
    int level;//节点深度(索引从0开始)
    int flag;//0:正常节点，1：扩展属性，只读标签
    
    int rmtProduct;//人脉通商品数
    int rmtPurchase;//人脉通采购数
    int zmlProduct;//找布商品数
    int zmlStockProduct;//找布库存商品数
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public int getRmtProduct() {
        return rmtProduct;
    }
    public void setRmtProduct(int rmtProduct) {
        this.rmtProduct = rmtProduct;
    }
    public int getRmtPurchase() {
        return rmtPurchase;
    }
    public void setRmtPurchase(int rmtPurchase) {
        this.rmtPurchase = rmtPurchase;
    }
    public int getZmlProduct() {
        return zmlProduct;
    }
    public void setZmlProduct(int zmlProduct) {
        this.zmlProduct = zmlProduct;
    }
    public int getZmlStockProduct() {
        return zmlStockProduct;
    }
    public void setZmlStockProduct(int zmlStockProduct) {
        this.zmlStockProduct = zmlStockProduct;
    }
    
}
