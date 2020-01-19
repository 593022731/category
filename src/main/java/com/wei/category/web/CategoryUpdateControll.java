package com.wei.category.web;

import com.wei.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分类 数量更新control
 * 
 * @author : weihui
 * @createTime : 2016年8月24日 上午10:22:48
 * @version : 1.0
 * @description :
 *
 */
@Controller
@RequestMapping(value="/categoryUpdate")
public class CategoryUpdateControll {
    
    @Autowired
    CategoryService categoryService;
    
    /**
     * 更新人脉通商品数
     * @param data [{"code":"1/4","count":100},{"code":"1/4/10","count":200}]
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月30日 上午11:35:09
     */
    @RequestMapping(value="/rmtProduct")
    @ResponseBody
    public String rmtProduct (String data){
        categoryService.updateRmtProduct(data);
        return "success";
    }
    
    /**
     * 更新人脉通采购数
     * @param data [{"code":"1/4","count":100},{"code":"1/4/10","count":200}]
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月30日 上午11:35:09
     */
    @RequestMapping(value="/rmtPurchase")
    @ResponseBody
    public String rmtPurchase (String data){
        categoryService.updateRmtProduct(data);
        return "success";
    }
    
    
    /**
     * 更新找布商品数
     * @param data [{"code":"1/4","count":100},{"code":"1/4/10","count":200}]
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月30日 上午11:35:09
     */
    @RequestMapping(value="/zmlProduct")
    @ResponseBody
    public String zmlProduct (String data){
        categoryService.updateZmlProduct(data);
        return "success";
    }
    
    
    /**
     * 更新找布库存商品数
     * @param data [{"code":"1/4","count":100},{"code":"1/4/10","count":200}]
     * @return
     *
     * @author : weihui
     * @createTime : 2016年8月30日 上午11:35:09
     */
    @RequestMapping(value="/zmlStockProduct")
    @ResponseBody
    public String zmlStockProduct (String data){
        categoryService.updateZmlStockProduct(data);
        return "success";
    }
    
}
