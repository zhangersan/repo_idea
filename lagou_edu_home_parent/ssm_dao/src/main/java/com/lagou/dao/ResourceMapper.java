package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/23
 */
public interface ResourceMapper {

    /**
     * 多条件分页查询资源信息
     * @param resourceVO
     * @return
     */
    public List<Resource> findAllResource(ResourceVO resourceVO);

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
     * 根据id删除对应资源分类信息
     * @param id
     */
    void deleteResource(Integer id);
}
