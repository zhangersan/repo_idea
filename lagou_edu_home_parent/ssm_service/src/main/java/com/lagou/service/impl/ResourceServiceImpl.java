package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ersan
 * @date 2021/12/23
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {

        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResource(resourceVO);
        PageInfo<Resource> pageInfo = new PageInfo<Resource>(resourceList);
        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        resourceMapper.saveResource(resource);
    }

    @Override
    public Resource findResourceById(Integer id) {
        return resourceMapper.findResourceById(id);
    }

    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("system");

        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
