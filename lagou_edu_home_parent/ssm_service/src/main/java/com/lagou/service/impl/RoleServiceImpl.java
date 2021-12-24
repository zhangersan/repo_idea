package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public List<String> findMenuIdByRoleId(Integer roleId) {
        return roleMapper.findMenuIdByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        //1.先清空原有的菜单列表
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        for (Integer menuId : roleMenuVO.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            role_menu_relation.setMenuId(menuId);
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            //2.再添加新的菜单
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        //删除角色信息前需要清空角色菜单中间表关联信息
        roleMapper.deleteRoleContextMenu(id);
        //然后再删除角色表对应信息
        roleMapper.deleteRole(id);
    }
}
