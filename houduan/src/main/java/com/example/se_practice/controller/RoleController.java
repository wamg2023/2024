package com.example.se_practice.controller;

import com.example.se_practice.common.GetPlayerResult;
import com.example.se_practice.common.Result;
import com.example.se_practice.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;

    /*
     * 登录功能
     * 前端传入的数据：用户名，密码，对应数据库中的对应表里的account、password
     * 返回数据类型：玩家id，即player_id
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> data) {
            String account = (String) data.get("user_name");
            String password = (String) data.get("password");
            int player_id = roleService.login(account, password);
            return Result.Success("登录成功！",player_id);
    }

    /*
     * 注册功能
     * 前端传入数据：用户名，密码，对应数据库中的对应表里的account、password
     * 后端将传入数据插入对应表项
     */
    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> data) {
            String account = (String) data.get("user_name");
            String password = (String) data.get("password");
            roleService.register(account, password);
            return Result.Success("注册成功！");
    }

    /*
     * 展示用户页面--获取本存档的玩家信息
     * 前端传入数据：player_id、save_id
     * 后端将传玩家当前存档全部信息全部传给前端
     */
    @PostMapping("/getRole")
    public Result getPlayer(@RequestBody Map<String, String> data) {
        String player_id_str = (String) data.get("player_id");
        int player_id=Integer.parseInt(player_id_str);
        String save_id_str = (String) data.get("save_id");
        int save_id=Integer.parseInt(save_id_str);
        GetPlayerResult getPlayerResult= roleService.getPlayer(player_id, save_id);
        return Result.Success("存档读取成功",getPlayerResult);
    }



}
