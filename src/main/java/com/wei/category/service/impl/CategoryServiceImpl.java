package com.wei.category.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wei.category.dao.CategoryDao;
import com.wei.category.entity.Category;
import com.wei.category.entity.CategoryDTO;
import com.wei.category.entity.CategoryTable;
import com.wei.category.entity.TreeNode;
import com.wei.category.service.CategoryService;
import com.wei.common.tools.CommonUtils;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    
    /**
     * 分类缓存
     */
    static List<CategoryDTO> categorys;
    /**
     * 标示是否最新分类
     */
    static long categoryModifyDT;

    @Autowired
    CategoryDao categoryDao;
    
    /**
     * 初始化分类
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        String tableName = this.categoryDao.getUseCategoryTable();
        categorys = categoryDao.getCategoryDTO(tableName);
        categoryModifyDT = System.currentTimeMillis();
    }
    
    @Override
    public List<TreeNode> getTreeNodes(String tableName) {
        List<Category> allCategorys = categoryDao.getAllCategorys(tableName);
        List<TreeNode> resList = new ArrayList<TreeNode>();
        for(Category item : allCategorys){
            TreeNode node = new TreeNode();
            node.setId(item.getCode());
            node.setpId(item.getParentCode());
            node.setName(item.getTitle());
            node.setOldCode(item.getCode());
            node.setMaxId(item.getId());
            if(item.getParentCode().equals("0")){
                node.setOpen(true);
            }
            if(item.getFlag() == 1){
                node.setChecked(true);
            }
            resList.add(node);
        }
        return resList;
    }

    @Override
    public int getMaxId(String tableName) {
        return categoryDao.getMaxId(tableName);
    }

    @Transactional
    @Override
    public String saveCategory(String tableComment,String sourceVersion, String categoryStr) {
        String tableName = "v_"+CommonUtils.getDateString("yyyyMMddHHmmss", System.currentTimeMillis());
        categoryDao.createNewTable(tableName, "'"+tableComment+"'");
        String [] categorys = categoryStr.split(";");//id+","+name+","+pId+","+level+","+flag+","+oldCode+","+maxId+";"
        List<Category> categories = new ArrayList<Category>();
        int orderNo = 1;
        for(String category : categorys){
            if(CommonUtils.isNotEmpty(category)){
                String[] nodes = category.split(",");
                String code = nodes[0];
                String title = nodes[1];
                String parentCode = nodes[2];
                int level = Integer.valueOf(nodes[3]);
                int flag = Integer.valueOf(nodes[4]);
                int maxId = Integer.valueOf(nodes[6]);
                String oldCode = nodes[5];
                if(oldCode.equals("0")){
                    oldCode = code;
                }
                Category item = new Category();
                item.setCode(code);
                item.setTitle(title);
                item.setParentCode(parentCode);
                item.setLevel(level);
                item.setFlag(flag);
                item.setOldCode(oldCode);
                item.setId(maxId);
                item.setOrderNo(orderNo++);
                item.setSourceVersion(sourceVersion);
                categories.add(item);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", tableName);
        map.put("categories", categories);
        categoryDao.insertBatch(map);
        
        categoryDao.saveCategoryTable(tableName, tableComment);
        return tableName;
    }

    @Override
    public List<CategoryTable> getAllCategoryTable() {
        return categoryDao.getAllCategoryTable();
    }

    @Override
    public List<TreeNode> compareCategory(String sourceVersion, String compareVersion) {
        List<Category> allCategorys = categoryDao.getAllCategorys(sourceVersion);
        
        List<Category> difCategorys = categoryDao.getDifCategory(sourceVersion, compareVersion);
        
        List<TreeNode> resList = new ArrayList<TreeNode>();
        for(Category item : allCategorys){
            TreeNode node = new TreeNode();
            node.setId(item.getCode());
            node.setpId(item.getParentCode());
            node.setName(item.getTitle());
            node.setOldCode(item.getCode());
            node.setMaxId(item.getId());
            node.setOpen(true);
            if(item.getFlag() == 1){
                node.setChecked(true);
            }
            for(Category add : difCategorys){
                if(item.getCode().equals(add.getCode())){
                    node.setDifFlag(1);
                    break;
                }
            }
            resList.add(node);
        }
        return resList;
    }

    @Override
    public  Map<String,Object> getCategoryDTO(long lastModifyTime) {
        Map<String,Object> map  = new HashMap<String,Object>();
        if(categoryModifyDT > lastModifyTime){
            map.put("allCategory", categorys);
            map.put("hasNew", true);
            map.put("lastTime", categoryModifyDT);
        }else{
            map.put("hasNew", false);
        }
        return map;
    }

    @Transactional
    @Override
    public int activeCategory(String tableName) {
        this.categoryDao.updateUse();
        this.categoryDao.updateUseByTableName(tableName);
        
        categorys = categoryDao.getCategoryDTO(tableName);
        categoryModifyDT = System.currentTimeMillis();
        
        return 1;
    }

    @Override
    public void updateRmtProduct(String data) {
        boolean isUpdate = false;
        JSONArray arr = JSONArray.fromObject(data);
        for(int i = 0;i<arr.size();i++){
            JSONObject obj = (JSONObject)arr.get(i);
            String code = obj.getString("code");
            int count = obj.getInt("count");
            for(CategoryDTO item : categorys){
                if(item.getCode().equals(code)){
                    if(item.getRmtProduct() != count){
                        isUpdate = true;
                    }
                    item.setRmtProduct(count);
                    break;
                }
            }
        }
        if(isUpdate){
            categoryModifyDT = System.currentTimeMillis();
        }
    }

    @Override
    public void updateRmtPurchase(String data) {
        boolean isUpdate = false;
        JSONArray arr = JSONArray.fromObject(data);
        for(int i = 0;i<arr.size();i++){
            JSONObject obj = (JSONObject)arr.get(i);
            String code = obj.getString("code");
            int count = obj.getInt("count");
            for(CategoryDTO item : categorys){
                if(item.getCode().equals(code)){
                    if(item.getRmtPurchase() != count){
                        isUpdate = true;
                    }
                    item.setRmtPurchase(count);
                    break;
                }
            }
        }
        if(isUpdate){
            categoryModifyDT = System.currentTimeMillis();
        }
    }

    @Override
    public void updateZmlProduct(String data) {
        boolean isUpdate = false;
        JSONArray arr = JSONArray.fromObject(data);
        for(int i = 0;i<arr.size();i++){
            JSONObject obj = (JSONObject)arr.get(i);
            String code = obj.getString("code");
            int count = obj.getInt("count");
            for(CategoryDTO item : categorys){
                if(item.getCode().equals(code)){
                    if(item.getZmlProduct() != count){
                        isUpdate = true;
                    }
                    item.setZmlProduct(count);
                    break;
                }
            }
        }
        if(isUpdate){
            categoryModifyDT = System.currentTimeMillis();
        }
    }

    @Override
    public void updateZmlStockProduct(String data) {
        boolean isUpdate = false;
        JSONArray arr = JSONArray.fromObject(data);
        for(int i = 0;i<arr.size();i++){
            JSONObject obj = (JSONObject)arr.get(i);
            String code = obj.getString("code");
            int count = obj.getInt("count");
            for(CategoryDTO item : categorys){
                if(item.getCode().equals(code)){
                    if(item.getZmlStockProduct() != count){
                        isUpdate = true;
                    }
                    item.setZmlStockProduct(count);
                    break;
                }
            }
        }
        if(isUpdate){
            categoryModifyDT = System.currentTimeMillis();
        }
    }

}
