package com.lagou.dao;

import com.lagou.domain.Test;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/18
 */
public interface TestDao {

    /**
     * 查找所有
     * @return
     */
    public List<Test> findAll();
    
    void save();

    void test11();
    void test21();

    void test1();
    void test2();
}
