package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/23
 */
public interface ResourceCategoryMapper {

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
     * 根据id删除资源分类
     * @param id
     */
    void deleteResourceCategory(Integer id);

    /**
     * 根据资源分类id删除对应资源
     * @param categoryId
     */
    void deleteResourceByCategoryId(Integer categoryId);
}
