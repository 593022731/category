package com.wei.category.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wei.category.entity.Category;
import com.wei.category.entity.CategoryDTO;
import com.wei.category.entity.CategoryTable;

/**
 * 分类dao，xml实现接口
 * 
 * @author : weihui
 * @createTime : 2016年8月24日 上午10:08:41
 * @version : 1.0
 * @description :
 *
 */
public interface CategoryDao {
    
    /**
     * 添加新版本分类表
     * @param tableName
     * @param tableComment
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午12:12:07
     */
    int saveCategoryTable(@Param("tableName") String tableName,@Param("tableComment") String tableComment);
    
    /**
     * 所有分类版本
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午12:12:42
     */
    List<CategoryTable> getAllCategoryTable();
    
    /**
     * 所有分类(按版本即表名)
     * @param tableName
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 上午10:07:18
     */
    List<Category> getAllCategorys(@Param("tableName") String tableName);
    
    /**
     * 提供给业务系统的分类数据
     * @param tableName
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午4:14:39
     */
    List<CategoryDTO> getCategoryDTO(@Param("tableName") String tableName);
    
    /**
     * 最大id值
     * @param tableName
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 下午4:27:04
     */
    int getMaxId(@Param("tableName") String tableName);
    
    /**
     * 批量插入
     * @param tableName
     * @param categories
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 下午5:23:14
     */
    int insertBatch(Map<String, Object> map);
    
    /**
     * 创建表
     * @param tableName
     * @param tableComment
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 上午11:44:43
     */
    int createNewTable(@Param("tableName") String tableName,@Param("tableComment") String tableComment);
    
    /**
     * 获取差异
     * @param sourceVersion
     * @param compareVersion
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午5:00:40
     */
    List<Category> getDifCategory(@Param("sourceVersion") String sourceVersion,@Param("compareVersion") String compareVersion);
    
    /**
     * 获取激活的分类版本
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午4:31:38
     */
    String getUseCategoryTable();
    
    /**
     * 更新激活标示
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午5:03:44
     */
    int updateUse();
    int updateUseByTableName(String tableName);
    
}
