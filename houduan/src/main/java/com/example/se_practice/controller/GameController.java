package com.example.se_practice.controller;

import com.example.se_practice.common.CastSkillResult;
import com.example.se_practice.common.GetPlayerResult;
import com.example.se_practice.common.Result;
import com.example.se_practice.pojo.temp_enemy;
import com.example.se_practice.service.GameService;
import com.example.se_practice.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private GameService gameService;

    /*
     * 移动功能
     * 前端传入的数据，移向房间的ID，假设其为A
     * 后端需将数据库中的A房间的last_room_id更改为当前所在房间的ID，同时用下一个房间的ID更新当前所在房间的ID
     * 返回前端成功消息
     */
    @PostMapping("/move")
    public Result move(@RequestBody Map<String, String> data) {
        String room_id_str = (String) data.get("room_id");
        int room_id=Integer.parseInt(room_id_str);
        gameService.moveTo(room_id);
        return Result.Success("您已进入下一个房间！加油，探索者！");
    }

    /*
     * 后退功能
     * 前端发起后退请求
     * 后端需将数据库中的A房间的last_room_id更改为当前所在房间的ID，同时用下一个房间的ID更新当前所在房间的ID
     * 返回前端成功消息
     */
    @PostMapping("/back")
    public Result back() {
        gameService.back();
        return Result.Success("成功返回先前所在房间！");
    }

    /*
     * 进入战斗功能，即获取怪物信息
     * 前端发起进入战斗请求
     * 后端需将数据库中的当前房间对应的怪物的信息返回给前端
     * 返回前端成功消息
     */
    @GetMapping("/startFight")
    public Result startFight() {
        temp_enemy tempEnemy =gameService.startFight();
        return Result.Success("怪物来啦",tempEnemy);
    }

    /*
     * 释放技能功能，
     * 前端发起释放技能请求
     * 后端需将双方攻击效果描述返回给前端
     * 返回前端成功消息
     */
    @PostMapping("/CastSkill")
    public Result CastSkill(@RequestBody Map<String, String> data) {
        String skill_id_str = (String) data.get("skill_id");
        int skill_id=Integer.parseInt(skill_id_str);
        CastSkillResult castSkillResult =gameService.CastSkill(skill_id);
        return Result.Success("施法成功",castSkillResult);
    }

}
