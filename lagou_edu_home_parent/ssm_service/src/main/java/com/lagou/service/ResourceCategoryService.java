package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/23
 */
public interface ResourceCategoryService {

    /**
     * 查询所有资源分类信息
     * @return
     */
    List<ResourceCategory> findAllResourceCategory();

    /**
     * 新增
     * @param resourceCategory
     */
    void saveResourceCategory(ResourceCategory resourceCategory);

    /**
     * 修改时回显
     * @param id
     * @return
     */
    ResourceCategory findResourceCategoryById(Integer id);

    /**
     * 修改
     * @param resourceCategory
     */
    void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 根据id删除
     * @param id
     */
    void deleteResourceCategory(Integer id);
}
