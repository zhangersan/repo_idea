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
}
