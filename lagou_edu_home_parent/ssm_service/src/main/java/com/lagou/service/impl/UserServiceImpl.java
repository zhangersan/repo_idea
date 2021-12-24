package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        userMapper.updateUserStatus(user);

    }

    @Override
    public User login(User user) throws Exception {

        //登陆后的密码加密
        User user2 = userMapper.login(user);
        //进行判断
        if (user2 != null && Md5.verify(user.getPassword(),Md5.md5key,user2.getPassword())){
            return user2;
        } else {
            return null;
        }
    }

    @Override
    public List<Role>  findUserRoleById(Integer userId) {
        return userMapper.findUserRoleById(userId);
    }

    @Override
    public void userContextRole(UserVO userVO) {

        //1.先清空中间表
        userMapper.deleteUserContextRole(userVO.getUserId());

        Date date = null;
        User_Role_relation userRoleRelation = null;
        String userBy = "system";
        //2.再添加新的数据
        //由于获取到的roleId是一个集合,所以需要遍历
        for (Integer roleId:userVO.getRoleIdList()) {
            date = new Date();
            userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVO.getUserId());
            userRoleRelation.setRoleId(roleId);
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);

            userRoleRelation.setCreatedBy(userBy);
            userRoleRelation.setUpdatedBy(userBy);

            userMapper.userContextRole(userRoleRelation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //1.根据userId查询对应的角色信息,得到roleId集合并封装
        List<Role> roleList = userMapper.findUserRoleById(userId);
        ArrayList<Integer> roleIds = new ArrayList<>();
        //封装
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //2.根据得到的roleId查询对应的父菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(roleIds);

        //3.根据parentMenuList中的id进行查询子菜单信息也就是select... where parent_id = id
        for (Menu menu : parentMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByParentId(menu.getId());
            //封装到menu中
            menu.setSubMenuList(subMenuList);
        }

        //4.根据userId查询对应的权限信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //5.封装到map集合中
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"响应成功",map);
    }
}
