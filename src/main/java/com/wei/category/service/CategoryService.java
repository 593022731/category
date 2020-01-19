package com.wei.category.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.wei.category.entity.CategoryTable;
import com.wei.category.entity.TreeNode;

/**
 * 分类service
 * 
 * @author : weihui
 * @createTime : 2016年8月24日 上午10:08:29
 * @version : 1.0
 * @description :
 *
 */
public interface CategoryService extends InitializingBean{
   
    /**
     * 所有分类版本
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午12:12:42
     */
    List<CategoryTable> getAllCategoryTable();
    
    /**
     * 所有分类节点(按版本即表名)
     * @param tableName
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 上午10:07:18
     */
    List<TreeNode> getTreeNodes(String tableName);
    
    /**
     * 最大id值
     * @param tableName
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 下午4:27:04
     */
    int getMaxId(String tableName);
    
    /**
     * 保存数据
     * @param tableComment
     * @param tableName
     * @param categoryStr
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 下午4:59:54
     */
    String saveCategory(String tableComment,String tableName,String categoryStr);
    
    /**
     * 比对版本
     * @param sourceVersion
     * @param compareVersion
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午4:43:42
     */
    List<TreeNode> compareCategory(String sourceVersion,String compareVersion);
    
    /**
     * 提供给业务系统的分类数据
     * @param lastModifyTime
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午4:18:52
     */
    Map<String,Object> getCategoryDTO(long lastModifyTime);
    
    /**
     * 激活版本使用
     * @param tableName
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午5:01:17
     */
    int activeCategory(String tableName);
    
    /**
     * 更新人脉通商品数量
     * @param data
     *
     * @author : weihui
     * @createTime : 2016年8月30日 上午11:37:25
     */
    void updateRmtProduct(String data);
    
    /**
     * 更新人脉通采购数量
     * @param data
     *
     * @author : weihui
     * @createTime : 2016年8月30日 下午2:55:45
     */
    void updateRmtPurchase(String data);
    
    /**
     * 更新找布商品数量
     * @param data
     *
     * @author : weihui
     * @createTime : 2016年8月30日 下午2:55:45
     */
    void updateZmlProduct(String data);
    
    /**
     * 更新找布库存商品数量
     * @param data
     *
     * @author : weihui
     * @createTime : 2016年8月30日 下午2:55:45
     */
    void updateZmlStockProduct(String data);
}
