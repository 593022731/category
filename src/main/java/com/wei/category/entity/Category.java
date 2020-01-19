package com.wei.category.entity;

public class Category {
    String code;
    String title;
    String parentCode;
    int level;//节点深度(索引从0开始)
    int flag;//0:正常节点，1：扩展属性，只读标签
    String oldCode;//冗余字段，上一版本的code，如果此节点没更新，等于code字段
    int id;//节点命名
    int orderNo;//排序
    String sourceVersion;//上一个版本
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
    public String getOldCode() {
        return oldCode;
    }
    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
    public String getSourceVersion() {
        return sourceVersion;
    }
    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }
    
}
