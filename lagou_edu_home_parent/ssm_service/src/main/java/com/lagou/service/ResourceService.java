package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/23
 */
public interface ResourceService {

    /**
     * 多条件分页查询资源信息
     * @param resourceVO
     * @return
     */
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

    /**
     * 新增
     * @param resource
     */
    void saveResource(Resource resource);

    /**
     * 根据id查询资源信息
     * @param id
     * @return
     */
    Resource findResourceById(Integer id);

    /**
     * 修改
     * @param resource
     */
    void updateResource(Resource resource);

    /**
     * 删除
     * @param id
     */
    void deleteResource(Integer id);
}
