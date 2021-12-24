package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author ersan
 * @date 2021/12/21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){

        PageInfo<User> pageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true,200,"分页多条件查询成功!",pageInfo);
    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){

        userService.updateUserStatus(id,status);
        return new ResponseResult(true,200,"修改用户状态成功!",null);
    }

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1 != null){
            //把登录的用户存在session会话中,access_token相当于标识牌
            String access_token = UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            map.put("user",user1);
            return new ResponseResult(true,1,"登录成功",map);
        } else {
            return new ResponseResult(true,400,"登录失败",null);
        }
    }

    /**
     * 分配角色:回显
     * 根据用户id查询所有角色信息
     * @param userId
     * @return
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(@RequestParam("id") Integer userId){
        List<Role> roleList= userService.findUserRoleById(userId);
        return new ResponseResult(true,200,"响应成功",roleList);
    }

    /**
     * 分配角色:清空和新增
     * @param userVO
     * @return
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.userContextRole(userVO);
        return new ResponseResult(true,200,"响应成功",null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //1.获取页面请求头Authorization中的token信息
        String token = request.getHeader("Authorization");
        //2.获取session中的access_token信息
        String access_token = (String) request.getSession().getAttribute("access_token");

        //对比token和access_token是否一致,一致则说明是同个用户登录,展示对应的动态菜单
        if (token.equals(access_token)){
            //根据登录时保存的user_id可以通过以下方式获取userId并进行查询
            return userService.getUserPermissions((Integer) request.getSession().getAttribute("user_id"));
        } else {
            return new ResponseResult(false,400,"获取失败","");
        }
    }


}
