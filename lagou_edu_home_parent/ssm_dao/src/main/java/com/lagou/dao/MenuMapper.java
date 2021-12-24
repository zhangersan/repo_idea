package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/21
 */
public interface MenuMapper {

    /**
     * 根据parent_id查询所有父子菜单
     * @return
     */
    public List<Menu> findAllMenuByPid(int pid);

    /**
     * 查询所有菜单
     * @return
     */
    public List<Menu> findAllMenu();

    /**
     * 添加菜单信息
     * @param menu
     */
    public void saveMenu(Menu menu);

    /**
     * 根据id 查询菜单信息(回显信息)
     * @param id
     * @return
     */
    public Menu findMenuInfoById(Integer id);

    /**
     * 修改菜单信息
     * @param menu
     */
    public void updateMenu(Menu menu);
}
