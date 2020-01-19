package com.wei.category.entity;

public class CategoryTable {
    String tableName;
    String tableComment;
    int isUse;
    String createDT;
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableComment() {
        return tableComment;
    }
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
    public int getIsUse() {
        return isUse;
    }
    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }
    public String getCreateDT() {
        return createDT;
    }
    public void setCreateDT(String createDT) {
        this.createDT = createDT;
    }
    
}
