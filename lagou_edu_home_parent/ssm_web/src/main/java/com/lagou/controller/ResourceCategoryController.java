package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/23
 */
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询所有资源分类信息
     * @return
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"响应成功",list);
    }

    /**
     * 编辑时回显
     * @param id
     * @return
     */
    @RequestMapping("/findResourceCategoryById")
    public ResponseResult findResourceCategoryById(Integer id){

        ResourceCategory resourceCategory = resourceCategoryService.findResourceCategoryById(id);
        return new ResponseResult(true,200,"响应成功",resourceCategory);
    }

    /**
     * 添加或修改
     * @param resourceCategory
     * @return
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        //id为空说明为添加操作
        if (resourceCategory.getId() == null){
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"添加成功",null);
        } else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true,200,"删除成功",null);
    }
}
