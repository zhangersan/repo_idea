package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据角色id查询对应资源分类信息
     * @param roleId
     * @return
     */
    List<ResourceCategory> findAllResourceCategoryByRoleId(Integer roleId);

    /**
     * 根据角色id和分类id查询对应属于哪个资源分类下的资源信息
     * @param roleId
     * @param categoryId
     * @return
     */
    List<Resource> findAllResourceByRoleIdAndCategoryId(@Param("roleId") Integer roleId,@Param("categoryId") Integer categoryId);

    /*为角色分配资源*/
    //根据角色查询所有资源信息

    /**
     * 根据角色id查询对应的资源id
     * @param roleId
     * @return
     */
    List<Integer> findResourceIdByRoleId(Integer roleId);

    /**
     * 根据角色id删除角色资源中间表
     * @param roleId
     */
    void deleteRoleResourceRelation(Integer roleId);

    /**
     * 为角色分配资源信息
     * @param role_resource_relation
     */
    void roleContextResource(Role_resource_relation role_resource_relation);

    /*为角色分配资源*/
}
