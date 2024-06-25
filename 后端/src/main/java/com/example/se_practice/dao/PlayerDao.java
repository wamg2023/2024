package com.example.se_practice.dao;

import com.example.se_practice.pojo.game_saved;
import com.example.se_practice.pojo.player_attribute;
import com.example.se_practice.pojo.player_status;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PlayerDao  {

    //正式编程
    @Select("select  * from game_saved where game_saved_id=#{game_saved_id}")
    game_saved getGame_saved(@Param("game_saved_id")int game_saved_id);
    @Select("select  * from player_attribute where player_attribute_id=#{player_attribute_id}")
    player_attribute getPlayer_attribute(@Param("player_attribute_id")int player_attribute_id);
    @Select("select  * from player_status where player_status_id=#{player_status_id}")
    player_status getPlayer_status(@Param("player_status_id")int player_status_id);
    @Select("select magic_point from player_status where player_status_id=#{player_status_id}")
    int getRoleMagicPoint(@Param("player_status_id")int player_status_id);
    @Select("select hit_point from player_status where player_status_id=#{player_status_id}")
    int getRoleHitPoint(@Param("player_status_id")int player_status_id);
    @Select("select attack from player_attribute where player_attribute_id=#{player_attribute_id}")
    int getPlayerAttack(@Param("player_attribute_id")int player_attribute_id);
    @Select("select defence from player_attribute where player_attribute_id=#{player_attribute_id}")
    int getPlayerDefence(@Param("player_attribute_id")int player_attribute_id);
    @Select("select speed from player_attribute where player_attribute_id=#{player_attribute_id}")
    int getPlayerSpeed(@Param("player_attribute_id")int player_attribute_id);
    @Update("update player_status set magic_point = #{magic_point} where player_status_id = #{player_status_id}")
    void updateRoleMagicPoint(@Param("player_status_id") int player_status_id, @Param("magic_point") int magic_point);
    @Update("update player_status set hit_point = #{hit_point} where player_status_id = #{player_status_id}")
    void updateRoleHitPoint(@Param("player_status_id") int player_status_id, @Param("hit_point") int hit_point);
    @Update("update game_saved set in_room_id = #{in_room_id} where game_saved_id=#{game_saved_id}")
    void updateNowInRoom(@Param("game_saved_id")int game_saved_id,@Param("in_room_id") int in_room_id);
}
