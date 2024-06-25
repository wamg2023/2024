package com.example.se_practice.common;
/*
* 这是一个特殊的数据返回类型
* 需要返回的数据包含多种数据类型，无法用一个类型进行存储
* gameSaved,存放游戏总存档索引
* playerAttribute,存放角色总体描述
* player_status,存放角色状态
* */

import com.example.se_practice.pojo.game_saved;
import com.example.se_practice.pojo.player_attribute;
import com.example.se_practice.pojo.player_status;
public class GetPlayerResult {
    private game_saved gameSaved;
    private player_attribute playerAttribute;
    private player_status player_status;

    public game_saved getGameSaved() {
        return gameSaved;
    }

    public void setGameSaved(game_saved gameSaved) {
        this.gameSaved = gameSaved;
    }

    public player_attribute getPlayerAttribute() {
        return playerAttribute;
    }

    public void setPlayerAttribute(player_attribute playerAttribute) {
        this.playerAttribute = playerAttribute;
    }

    public com.example.se_practice.pojo.player_status getPlayer_status() {
        return player_status;
    }

    public void setPlayer_status(com.example.se_practice.pojo.player_status player_status) {
        this.player_status = player_status;
    }
    public GetPlayerResult(game_saved gameSaved,player_attribute playerAttribute,player_status player_status){
        this.gameSaved = gameSaved;
        this.playerAttribute = playerAttribute;
        this.player_status = player_status;
    }
}
