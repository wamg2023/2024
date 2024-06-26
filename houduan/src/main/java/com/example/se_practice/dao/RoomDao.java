package com.example.se_practice.dao;

import com.example.se_practice.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao {
    @Select("select room_flag from saved_graph where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    int getRoomFlag(@Param("saved_graph_id")int saved_graph_id, @Param("room_id")int room_id);
    @Select("select room_event_id from room where room_id=#{room_id}")
    int getRoom_event_id(@Param("room_id")int room_id);
    @Select("select * from room_event where room_event_id=#{room_event_id}")
    room_event getRoom_event(@Param("room_event_id")int room_event_id);
    @Select("select count(*) from room")
    int getRoomNum();
    @Select("select enemy_id from room_event where room_event_id=#{room_event_id}")
    int getRoomEnemyID(@Param("room_event_id")int room_event_id);

    //刘航编写
    @Select("select game_saved_id from player__saved where player_id=#{player_id} and selection=#{selection} ")
    int getGameSavedId(@Param("player_id") int player_id, @Param("selection") int selection);
    @Select("select in_room_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getInRoomId(@Param("game_saved_id") int game_saved_id);
    @Select("select saved_graph_id from game_saved where game_saved_id=#{game_saved_id}")
    int getSavedGraphId(@Param("game_saved_id") int game_saved_id);
    @Select("select room_id from saved_graph where room_id=#{in_room_id} ")
    int getRoomId(@Param("in_room_id") int in_room_id);
    @Select("select * from room where room_id=#{room_id}")
    room RoomfindById(@Param("room_id") int room_id);
    @Select("select * from saved_graph where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    saved_graph SavedGraphfindById(@Param("saved_graph_id") int saved_graph_id,@Param("room_id") int room_id);
    @Select("select inventory_id from saved_graph where saved_graph_id=#{saved_graph_id} and room_id=#{room_id}")
    int getInventoryId(@Param("saved_graph_id") int saved_graph_id,@Param("room_id") int room_id);
    @Select("select * from inventory where inventory_id=#{inventory_id}")
    List<inventory> getInventoryInfo(@Param("inventory_id") int inventory_id);
    @Select("select item_id from inventory where inventory_id=#{inventory_id}")
    List<Integer> getItemId(@Param("inventory_id") int inventory_id);
    @Select("select item_attribute_id from item where item_id=#{item_id}")
    int getItemAttributeId(@Param("item_id") int item_id);
    @Select("select * from item_attribute where item_attribute_id=#{item_attribute_id}")
    item_attribute getItemAttribute(@Param("item_attribute_id") int item_attribute_id);

}
