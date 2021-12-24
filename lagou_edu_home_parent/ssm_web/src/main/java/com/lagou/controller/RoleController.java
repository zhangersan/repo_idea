package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ersan
 * @date 2021/12/21
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有role
     * @param role
     * @return
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询成功",allRole);
    }

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有父子级菜单
     * @return
     */
    @RequestMapping("/findAllMenuByPid")
    public ResponseResult findAllMenuByPid(){
        List<Menu> allMenu = menuService.findAllMenuByPid(-1);
        Map<String,Object> map = new HashMap();
        map.put("parentMenuList",allMenu);
        return new ResponseResult(true,200,"查询成功",map);

    }

    /**
     * 根据roleId查找menuId
     * @param roleId
     * @return
     */
    @RequestMapping("/findMenuIdByRoleId")
    public ResponseResult findMenuIdByRoleId(Integer roleId){
        List<String> menuIdList = roleService.findMenuIdByRoleId(roleId);
        return new ResponseResult(true,200,"查询成功",menuIdList);
    }

    /**
     * 为角色分配菜单
     * @param roleMenuVO
     * @return
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.roleContextMenu(roleMenuVO);
        return new ResponseResult(true,200,"分配成功",null);
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除成功",null);
    }

    /**
     * 根据角色id查询当前对应的资源信息(资源分类和资源信息)
     * @param roleId
     * @return
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){

        List<ResourceCategory> resourceCategoryList = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true,200,"响应成功!",resourceCategoryList);
    }

    /**
     * 根据角色id查询对应的资源id
     * @param roleId
     * @return
     */
    @RequestMapping("/findResourceIdByRoleId")
    public ResponseResult findResourceIdByRoleId(@RequestParam("id") Integer roleId){
        List<Integer> resourceIdList = roleService.findResourceIdByRoleId(roleId);
        return new ResponseResult(true,200,"查询成功",resourceIdList);
    }

    /**
     * 为角色分配资源
     * @param RoleResourceVO
     * @return
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVO RoleResourceVO){
        roleService.roleContextResource(RoleResourceVO);
        return new ResponseResult(true,200,"分配成功",null);
    }
}
