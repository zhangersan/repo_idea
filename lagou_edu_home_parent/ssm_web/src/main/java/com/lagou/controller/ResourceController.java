package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ersan
 * @date 2021/12/23
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 多条件分页查询成功
     * @param resourceVO
     * @return
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVO);
        return new ResponseResult(true,200,"多条件分页查询成功!",pageInfo);
    }

    /**
     * 根据id查询(回显)
     * @param id
     * @return
     */
    @RequestMapping("/findResourceById")
    public ResponseResult findResourceById(Integer id){

        Map<String,Object> map = new HashMap<String,Object>();

        List<ResourceCategory> resourceCategoryList = resourceCategoryService.findAllResourceCategory();
        map.put("resourceCategoryList",resourceCategoryList);

        //如果id为空说明为添加操作,只查询资源信息即可
        if (id == null){
            map.put("resource",null);
            return new ResponseResult(true,200,"添加查询资源信息成功!",map);
        } else {
            //否则为修改操作,根据id查询回显信息
            Resource resource = resourceService.findResourceById(id);
            map.put("resource",resource);
        }
        return new ResponseResult(true,200,"修改查询资源信息成功!",map);

    }

    /**
     * 修改或添加
     * @param resource
     * @return
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId() == null){
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"添加成功!",null);
        } else {
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改成功!",null);
        }
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除成功!",null);
    }
}
