package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ersan
 * @date 2021/12/21
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单信息
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"响应成功",allMenu);
    }

    /**
     * 回显菜单信息(根据parent_id判断是否-1决定回显信息)
     * @param id
     * @return
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        //查询所有父子菜单
        List<Menu> menuList = menuService.findAllMenuByPid(-1);
        Map<String,Object> map = null;
        if (id == -1) {
            map = new HashMap<String,Object>();
            map.put("menuInfo",null);
        } else {
            //回显对应编辑菜单的信息
            Menu menu = menuService.findMenuInfoById(id);
            map = new HashMap<String,Object>();
            //存到map中
            map.put("menuInfo",menu);
        }
        //把父子菜单信息存到map中(只需要遍历出父菜单即可)
        map.put("parentMenuList",menuList);
        return new ResponseResult(true,200,"菜单信息回显成功!",map);
    }

    /**
     * 新增或修改菜单信息
     * @param menu
     * @return
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if (menu.getId() == null) {
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"添加成功",null);
        } else {
            menuService.updateMenu(menu);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }
}
