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
}
