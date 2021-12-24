package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
public interface UserService {

    /**
     * 分页多条件查询
     * @param userVO
     * @return
     */
    public PageInfo<User> findAllUserByPage(UserVO userVO);

    /**
     * 更改用户状态
     * @param id
     * @param status
     */
    public void updateUserStatus(int id,String status);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user) throws Exception;

    /**
     * 分配角色:回显
     * @param userId
     * @return
     */
    List<Role>  findUserRoleById(Integer userId);


    /**
     * 分配角色:新增
     * @param userVO
     */
    void userContextRole(UserVO userVO);

    /**
     * 动态菜单
     * @param userId
     * @return
     */
    ResponseResult getUserPermissions(Integer userId);
}
