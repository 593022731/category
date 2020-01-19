package com.wei.category.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wei.category.service.CategoryService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.category.entity.CategoryTable;
import com.wei.category.entity.TreeNode;
import com.wei.common.tools.PropertiesUtils;

/**
 * 分类control
 * 
 * @author : weihui
 * @createTime : 2016年8月24日 上午10:22:48
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping(value="/category")
public class CategoryControll {
    
    @Autowired
    CategoryService categoryService;
    
    /**
     * 主页
     * @param request
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月24日 上午10:23:01
     */
    @RequestMapping(value="/index")
    public String index (HttpServletRequest request){
        List<CategoryTable> tables = categoryService.getAllCategoryTable();
        request.setAttribute("tables", tables);
        return "category/index";
    }
    
    /**
     * 编辑
     * @param request
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午2:33:16
     */
    @RequestMapping(value="/edit")
    public String edit (HttpServletRequest request){
        List<CategoryTable> tables = categoryService.getAllCategoryTable();
        request.setAttribute("tables", tables);
        
        
        String tableName = request.getParameter("tableName");
        int maxId = categoryService.getMaxId(tableName);
        request.setAttribute("maxId", maxId+1);
        request.setAttribute("tableName", tableName);
        return "category/category";
    }
    
    /**
     * 源数据
     * @param request
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午4:44:33
     */
    @RequestMapping(value="/data")
    @ResponseBody
    public List<TreeNode> data (HttpServletRequest request){
        String tableName = request.getParameter("tableName");
        List<TreeNode> resList = categoryService.getTreeNodes(tableName);
        return resList;
    }
    
    /**
     * 保存新版本
     * @param request
     * @param response
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午4:44:16
     */
    @RequestMapping(value = "/saveCategory")
    public void saveCategory(HttpServletRequest request,HttpServletResponse response) {
        try {
            String tableComment = request.getParameter("tableComment");
            String categoryStr = request.getParameter("categoryStr");
            String tableName = request.getParameter("tableName");
            String sourceVersion = categoryService.saveCategory(tableComment,tableName, categoryStr);
            response.getWriter().print(sourceVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 比对版本
     * @param request
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月25日 下午4:44:10
     */
    @RequestMapping(value="/compareCategory")
    @ResponseBody
    public List<TreeNode> compareCategory (HttpServletRequest request){
        String sourceVersion = request.getParameter("sourceVersion");
        String compareVersion = request.getParameter("compareVersion");
        List<TreeNode> resList = categoryService.compareCategory(sourceVersion, compareVersion);
        return resList;
    }
    
    /**
     * 激活最新版本
     * @param request
     * @param response
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午5:05:09
     */
    @RequestMapping(value="/activeCategory")
    public void activeCategory (HttpServletRequest request,HttpServletResponse response){
        try {
            String tableName = request.getParameter("tableName");
            this.categoryService.activeCategory(tableName);
            response.getWriter().print("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 影响的数据量
     * @param request
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 上午11:41:17
     */
    @RequestMapping(value="/changedata")
    public void changedata (HttpServletRequest request,HttpServletResponse response){
        try {
            String categoryID = request.getParameter("categoryID");
            
            String zmlUrl = PropertiesUtils.getProperties("domain.zml");
            String rmtUrl = PropertiesUtils.getProperties("domain.rmt");
            String zqpUrl = PropertiesUtils.getProperties("domain.zqp");
            
            JSONObject item = new JSONObject();
            item.put("商品数量", 34234123);
            item.put("采购数量", 1231);
            item.put("店铺主营数量", 34253242);
            String result = "找布["+item.toString()+"]";
            
            item = new JSONObject();
            item.put("商品数量", 23452412);
            item.put("采购数量", 123123);
            result += "<br />人脉通["+item.toString()+"]";
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 提供给业务系统的分类数据
     * @param lastModifyTime
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月29日 下午4:40:13
     */
    @RequestMapping(value="/categoryData/{lastModifyTime}")
    @ResponseBody
    public Map<String,Object> categoryData (@PathVariable("lastModifyTime") long lastModifyTime){
        return categoryService.getCategoryDTO(lastModifyTime);
    }
}
