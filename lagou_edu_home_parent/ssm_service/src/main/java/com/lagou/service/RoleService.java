package com.lagou.service;

import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
public interface RoleService {

    /**
     * 查询所有role
     * @param role
     * @return
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据roleId查找menuId
     * @param roleId
     * @return
     */
    public List<String> findMenuIdByRoleId(Integer roleId);

    /**
     * 4.分配菜单列表
     * @param roleMenuVO
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /**
     * 删除角色信息
     * @param id
     */
    public void deleteRole(Integer id);
}
