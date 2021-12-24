package com.lagou.service;

import com.lagou.domain.Test;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/18
 */
public interface TestService {

    /**
     * 查找所有
     * @return
     */
    public List<Test> findAll();
}
