package com.example.se_practice.dao;

import com.example.se_practice.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TempDao {
    @Update("update temp_player__saved set player_id = #{player_id} where li_id=1")
    void updatePlayerID(@Param("player_id")int player_id);
    @Update("update temp_player__saved set selection = #{selection} where li_id=1")
    void updateSelection(@Param("selection")int selection);
    @Update("update temp_player__saved set game_saved_id = #{game_saved_id} where li_id=1")
    void updateGameSavedID(@Param("game_saved_id")int game_saved_id);
    @Select("select game_saved_id from temp_player__saved where li_id=1")
    int getTempSavedID();
    @Select("select in_room_id from game_saved where game_saved_id=#{game_saved_id}")
    int getNowInRoomId(@Param("game_saved_id")int game_saved_id);
    @Select("select saved_graph_id from game_saved where game_saved_id=#{game_saved_id}")
    int getSavedGraphID(@Param("game_saved_id")int game_saved_id);
    @Update("update game_saved set in_room_id = #{in_room_id} where game_saved_id=#{game_saved_id}")
    void updateNowInRoomId(@Param("in_room_id")int in_room_id,@Param("game_saved_id")int game_saved_id);
    @Update("update saved_graph set last_room_id = #{last_room_id} where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    void updateNexRoom_LastRoom(@Param("saved_graph_id")int saved_graph_id,@Param("room_id")int room_id,@Param("last_room_id")int last_room_id);
    @Update("update saved_graph set room_flag = 0 where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    void updateRoomFlag(@Param("saved_graph_id")int saved_graph_id,@Param("room_id")int room_id);
    @Select("select last_room_id from saved_graph where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    int getLastRoomID(@Param("saved_graph_id")int saved_graph_id, @Param("room_id")int room_id);
    @Update("update temp_enemy set enemy_id=#{enemy_id},hit_point=#{hit_point} where enemy_li_id=1")
    void updateTempEnemy(@Param("enemy_id")int enemy_id, @Param("hit_point")int hit_point);
    @Select("select * from temp_enemy where enemy_li_id=1")
    temp_enemy getTempEnemy();
    @Select("select player_status_id from game_saved where game_saved_id=#{game_saved_id}")
    int getPlayerStatusID(@Param("game_saved_id")int game_saved_id);
    @Select("select enemy_id from temp_enemy where enemy_li_id=1")
    int getTempEnemyID();
    @Select("select player_attribute_id from game_saved where game_saved_id=#{game_saved_id}")
    int getPlayerAttributeID(@Param("game_saved_id")int game_saved_id);
    @Select("select hit_point from temp_enemy where enemy_li_id=1")
    int getTempEnemyNowBlood();
    @Update("update temp_enemy set hit_point = #{hit_point} where enemy_li_id = 1")
    void updateTempEnemyHitPoint(@Param("hit_point") int hit_point);
    @Update("update saved_graph set room_flag = 1 where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    void updateRoomFlagTo1(@Param("saved_graph_id")int saved_graph_id,@Param("room_id")int room_id);

}
