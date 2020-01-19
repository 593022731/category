package com.wei.category.entity;

/**
 * 树形节点对象，对应Category对象
 * 
 * @author : weihui
 * @createTime : 2016年8月24日 上午11:15:29
 * @version : 1.0
 * @description :
 *
 */
public class TreeNode {
    String id;//对应code
    String name;//对应title
    String pId;//对应parent_code
    int level;//对应level
    
    boolean checked;//是否选中，选中代表flag=1，未选中代表flag=0
    boolean open;//默认是否展开
    
    String oldCode;// 对应oldCode
    int maxId;//对应id
    
    int difFlag;//差异性(0:相同的,1:不同的)
    
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getpId() {
        return pId;
    }
    public void setpId(String pId) {
        this.pId = pId;
    }
    public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }
    public String getOldCode() {
        return oldCode;
    }
    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }
    public int getMaxId() {
        return maxId;
    }
    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public int getDifFlag() {
        return difFlag;
    }
    public void setDifFlag(int difFlag) {
        this.difFlag = difFlag;
    }
    
}
