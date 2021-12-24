package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
public interface UserMapper {

    /**
     * 分页多条件查询
     *
     * @param userVO
     * @return
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 更改用户状态
     *
     * @param user
     */
    public void updateUserStatus(User user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 分配角色:回显
     * 根据用户id查询所有角色信息
     *
     * @param userId
     * @return
     */
    List<Role> findUserRoleById(Integer userId);

    /**
     * 清空中间表
     *
     * @param userId
     */
    void deleteUserContextRole(Integer userId);

    /**
     * 分配角色:新增
     *
     * @param user_role_relation
     */
    void userContextRole(User_Role_relation user_role_relation);

    /*动态菜单*/
    //1.根据用户id查询所有角色信息

    /**
     * 2.根据角色id查询对应一级菜单
     *
     * @param roleIds
     * @return
     */
    List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    /**
     * 3.根据一级菜单的id查询对应的子菜单(对应的也就是parentId)
     *
     * @param pid
     * @return
     */
    List<Menu> findSubMenuByParentId(Integer pid);

    /**
     * 3.根据角色id查询对应的权限信息
     *
     * @param roleIds
     * @return
     */
    List<Resource> findResourceByRoleId(List<Integer> roleIds);
    /*动态菜单*/
}