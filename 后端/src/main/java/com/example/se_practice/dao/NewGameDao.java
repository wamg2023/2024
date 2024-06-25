package com.example.se_practice.dao;

import com.example.se_practice.pojo.initial_graph;
import com.example.se_practice.pojo.player_attribute;
import com.example.se_practice.pojo.role_initial_attribute;
import com.example.se_practice.pojo.saved_graph;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface NewGameDao {
    @Update("update temp_player__saved set player_id=#{player_id}, selection=#{selection} where li_id=1")
    int updateTempPlayerSaved(@Param("player_id") int player_id,@Param("selection") int selection);
    @Select("select selection from player__saved where player_id=#{player_id}")
    List<Integer> getSavedSelectionById(@Param("player_id") int player_id);
    @Select("select game_saved_id from player__saved where player_id=#{player_id} and selection=#{selection}")
    int getGameSavedId(@Param("player_id") int player_id, @Param("selection") int selection);
    @Select("select player_attribute_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getPlayerAttributeId(@Param("game_saved_id") int game_saved_id);
    @Select("select player_status_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getPlayerStatusId(@Param("game_saved_id") int game_saved_id);
    @Select("select inventory_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getInventoryId(@Param("game_saved_id") int game_saved_id);
    @Select("select item_id from inventory where inventory_id=#{inventory_id} ")
    List<Integer> getItemId(@Param("inventory_id") int inventory_id);
    @Select("select saved_graph_id from game_saved where game_saved_id=#{game_saved_id}")
    int getSavedGraphId(@Param("game_saved_id") int game_saved_id);
    @Select("select description from role_initial_attribute where role_initial_attribute_id=1")
    String getInitialDescription();
    @Select("select type from role_initial_attribute where role_initial_attribute_id=1")
    String getInitialType();
    @Select("select hit_point from role_initial_attribute where role_initial_attribute_id=1")
    int getInitialHitPoint();
    @Select("select magic_point from role_initial_attribute where role_initial_attribute_id=1")
    int getInitialMagicPoint();
    @Select("select attack from role_initial_attribute where role_initial_attribute_id=1")
    int getInitialAttack();
    @Select("select defence from role_initial_attribute where role_initial_attribute_id=1")
    int getInitialDefence();
    @Select("select speed from role_initial_attribute where role_initial_attribute_id=1")
    int getInitialSpeed();
    @Select("select max_weight from role_initial_attribute where role_initial_attribute_id=1")
    int getInitialMaxweight();
    @Update("update player_attribute set description=#{description} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeDescription(@Param("description") String description, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set type=#{type} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeType(@Param("type") String type, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set hit_point=#{hit_point} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeHitPoint(@Param("hit_point") int hit_point, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set magic_point=#{magic_point} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeMagicPoint(@Param("magic_point") int magic_point, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set attack=#{attack} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeAttack(@Param("attack") int attack, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set defence=#{defence} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeDefence(@Param("defence") int defence, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set speed=#{speed} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeSpeed(@Param("speed") int speed, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set max_weight=#{max_weight} where player_attribute_id=#{player_attribute_id}")
    int updatePlayerAttributeMaxweight(@Param("max_weight") int max_weight, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_status set hit_point=#{hit_point} where player_status_id=#{player_status_id}")
    int updatePlayerStatusHitPoint(@Param("hit_point") int hit_point, @Param("player_status_id") int player_status_id);
    @Update("update player_status set magic_point=#{magic_point} where player_status_id=#{player_status_id}")
    int updatePlayerStatusMagicPoint(@Param("magic_point") int magic_point, @Param("player_status_id") int player_status_id);
    @Delete("delete from inventory where inventory_id=#{inventory_id} and item_id=#{item_id}")
    int deleteInventory(@Param("inventory_id") int inventory_id, @Param("item_id") int item_id);
    @Insert("INSERT INTO inventory (inventory_id) VALUES (#{inventory_id})")
    int insertInventory(@Param("inventory_id") int inventory_id);
    @Update("update game_saved set player_skill_table_id=1 where game_saved_id=#{game_saved_id}")
    int updatePlayerSkillTableId(@Param("game_saved_id") int game_saved_id);
    @Select("select * from initial_graph where initial_graph_id=1")
    List<initial_graph> getInitialGraph();
    @Update("update saved_graph set room_id=#{room_id} where saved_graph_id=#{saved_graph_id}")
    List<saved_graph> updateSavedGraph(@Param("room_id") initial_graph room_id, @Param("saved_graph_id") int saved_graph_id);
    @Select("select room_id from initial_graph where initial_graph_id=1")
    List<Integer> getRoomId();
    @Select("select room_flag from initial_graph where room_id=#{room_id} ")
    int getSavedGraphRoomFlag(@Param("room_id") Integer roomId);
    @Select("select initial_inventory_id from initial_graph where room_id=#{room_id} ")
    int getSavedGraphInventoryId(@Param("room_id") Integer roomId);
    @Select("select east_room_id from initial_graph where room_id=#{room_id}")
    int getSavedGraphEastRoom(@Param("room_id") Integer roomId);
    @Select("select west_room_id from initial_graph where room_id=#{room_id} ")
    int getSavedGraphWestRoom(@Param("room_id") Integer roomId);
    @Select("select south_room_id from initial_graph where room_id=#{room_id} ")
    int getSavedGraphSouthRoom(@Param("room_id") Integer roomId);
    @Select("select north_room_id from initial_graph where room_id=#{room_id} ")
    int getSavedGraphNorthRoom(@Param("room_id") Integer roomId);

    @Update("update saved_graph set room_flag=#{room_flag} where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphRoomFlag(@Param("room_flag") int room_flag, @Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update saved_graph set inventory_id=#{inventory_id} where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphInventoryId(@Param("inventory_id") int inventory_id, @Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update saved_graph set east_room_id=#{east_room_id} where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphEastRoom(@Param("east_room_id") int east_room_id, @Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update saved_graph set west_room_id=#{west_room_id} where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphWestRoom(@Param("west_room_id") int west_room_id, @Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update saved_graph set south_room_id=#{south_room_id} where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphSouthRoom(@Param("south_room_id") int south_room_id, @Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update saved_graph set north_room_id=#{north_room_id} where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphNorthRoom(@Param("north_room_id") int north_room_id, @Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update saved_graph set last_room_id=-1 where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int updateSavedGraphLastRoom(@Param("room_id") Integer room_id, @Param("saved_graph_id") int saved_graph_id);
    @Update("update game_saved set in_room_id=11 where game_saved_id=#{game_saved_id}")
    int updateInRoomId(@Param("game_saved_id") int game_saved_id);
    @Select("select game_saved_id from game_saved ")
    List<Integer> getAllGameSavedId();
    @Insert("INSERT INTO player__saved (player_id,selection,game_saved_id) VALUES (#{player_id},#{selection},#{game_saved_id})")
    int insertGameSavedId(@Param("player_id") int player_id,@Param("selection") int selection,@Param("game_saved_id") int game_saved_id);
    @Select("select player_attribute_id from player_attribute ")
    List<Integer> getAllPlayerAttributeId();
    @Select("select player_status_id from player_status ")
    List<Integer> getAllPlayerStatusId();
    @Select("select inventory_id from inventory ")
    List<Integer> getAllInventoryId();
    @Select("select saved_graph_id from saved_graph ")
    List<Integer> getAllSavedGraphId();
    @Insert("INSERT INTO game_saved (game_saved_id,player_attribute_id,player_status_id,inventory_id,player_skill_table_id,saved_graph_id,in_room_id) VALUES (#{game_saved_id},#{player_attribute_id},#{player_status_id},#{inventory_id},1,#{saved_graph_id},11)")
    int insertGameSaved(@Param("game_saved_id") int game_saved_id,@Param("player_attribute_id") int player_attribute_id,@Param("player_status_id") int player_status_id,@Param("inventory_id") int inventory_id,@Param("saved_graph_id") int saved_graph_id);
    @Insert("INSERT INTO player_attribute (player_attribute_id,description,type,hit_point,magic_point,attack,defence,speed,max_weight) VALUES (#{player_attribute_id},'ww','ww',1,1,1,1,1,1)")
    int insertPlayerAttribute(@Param("player_attribute_id") int player_attribute_id);
    @Insert("INSERT INTO player_status (player_status_id,hit_point,magic_point) VALUES (#{player_status_id},1,1)")
    int insertPlayerStatus(@Param("player_status_id") int player_status_id);
    @Insert("INSERT INTO saved_graph (saved_graph_id,room_id,room_flag,inventory_id,east_room_id,west_room_id,south_room_id,north_room_id,last_room_id) VALUES (#{saved_graph_id},#{room_id},1,1,1,1,1,1,-1)")
    int insertGraphId(@Param("saved_graph_id") int saved_graph_id,@Param("room_id") int room_id);
    @Update("update temp_player__saved set game_saved_id=#{game_saved_id} where li_id=1")
    int updateGameSavedId(@Param("game_saved_id") int game_saved_id);

}
