package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
public interface RoleMapper {

    /**
     * 查询所有role
     * @param role
     * @return
     */
    public List<Role> findAllRole(Role role);

    /*为角色分配菜单*/
    //1.查询所有菜单列表
    /**
     * 2.根据roleId查找menuId
     * @param roleId
     * @return
     */
    public List<String> findMenuIdByRoleId(Integer roleId);

    /**
     * 3.根据角色id清空对应菜单列表
     * @param roleId
     */
    public void deleteRoleContextMenu(Integer roleId);

    /**
     * 4.分配菜单列表
     * @param role_menu_relation
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*为角色分配菜单*/

    /**
     * 删除角色信息
     * @param id
     */
    public void deleteRole(Integer id);


}
