package com.example.se_practice.dao;

import com.example.se_practice.pojo.inventory;
import com.example.se_practice.pojo.item_attribute;
import com.example.se_practice.pojo.item_effect;
import com.example.se_practice.pojo.room;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ItemDao {

    @Update("update inventory set item_num= item_num - 1 where inventory_id=#{inventory_id} and item_id=#{item_id} ")
    int updateItem_num_room(@Param("inventory_id") int inventory_id,@Param("item_id") int item_id);
    @Select("select item_num from inventory where inventory_id=#{inventory_id} and item_id=#{item_id} ")
    int getItemnumbyID(@Param("inventory_id") int inventory_id, @Param("item_id") int item_id);
    @Delete("delete from inventory where inventory_id=#{inventory_id} and item_id=#{item_id}")
    int deleteItem(@Param("inventory_id") int inventory_id, @Param("item_id") int item_id);

    @Select("select game_saved_id from temp_player__saved where li_id=1")
    int getGameSavedId();
    @Select("select item_num from inventory where inventory_id=#{inventory_id} and item_id=#{item_id}")
    Integer getItemIdNum(@Param("inventory_id") int inventory_id,@Param("item_id") int item_id);

    @Select("select inventory_id from saved_graph where room_id=#{room_id} and saved_graph_id=#{saved_graph_id}")
    int getInventoryId_room(@Param("room_id") int room_id,@Param("saved_graph_id") int saved_graph_id);
    @Select("select saved_graph_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getSavedGraphId(@Param("game_saved_id") int game_saved_id);

    @Select("select inventory_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getInventoryId_player(@Param("game_saved_id") int game_saved_id);

    @Update("update inventory set item_num= item_num+1 where inventory_id=#{inventory_id} and item_id=#{item_id} ")
    int updateItem_num_player(@Param("inventory_id") int inventory_id,@Param("item_id") int item_id);

    @Select("select in_room_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getRoomId(@Param("game_saved_id") int game_saved_id);


    @Select("select inventory_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getInventoryId(@Param("game_saved_id") int game_saved_id);
    @Select("select item_attribute_id from item where item_id=#{item_id} ")
    int getItemAttributeId(@Param("item_id") int item_id);
    @Select("select item_effect_id from item where item_id=#{item_id} ")
    int getItemEffectId(@Param("item_id") int item_id);
    @Select("select item_num from inventory where inventory_id=#{inventory_id} and item_id=#{item_id} ")
    int getItemnum(@Param("inventory_id") int inventory_id,@Param("item_id") int item_id);
    @Select("select weight from item_attribute where item_attribute_id=#{item_attribute_id} ")
    int getItemWeight(@Param("item_attribute_id") int item_attribute_id);
    @Select("select player_attribute_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getPlayerAttributeId(@Param("game_saved_id") int game_saved_id);
    @Select("select max_weight from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getMaxweight(@Param("player_attribute_id") int player_attribute_id);


    @Select("select item_id from inventory where inventory_id=#{inventory_id} ")
    List<Integer> getItem_id(@Param("inventory_id") int inventory_id);
    @Select("select * from inventory where inventory_id=#{inventory_id}")
    List<inventory> getInventory(@Param("inventory_id") int inventory_id);
    @Select("select * from item_attribute where item_attribute_id=#{item_attribute_id}")
    List<item_attribute> getItemAttribute(@Param("item_attribute_id") int item_attribute_id);
    @Select("select * from item_effect where item_effect_id=#{item_effect_id}")
    List<item_effect> getItemEffect(@Param("item_effect_id") int item_effect_id);


    @Select("select player_status_id from game_saved where game_saved_id=#{game_saved_id} ")
    int getPlayerStatusId(@Param("game_saved_id") int game_saved_id);
    @Select("select type from item_attribute where item_attribute_id=#{item_attribute_id} ")
    String getType(@Param("item_attribute_id") int item_attribute_id);
    @Select("select hit_point from item_effect where item_effect_id=#{item_effect_id} ")
    int getHitpoint(@Param("item_effect_id") int item_effect_id);
    @Select("select magic_point from item_effect where item_effect_id=#{item_effect_id} ")
    int getMagicpoint(@Param("item_effect_id") int item_effect_id);
    @Update("update player_status set hit_point=#{hit_point} where player_status_id=#{player_status_id}")
    int updateHitpointStatus_food(@Param("hit_point") int hit_point, @Param("player_status_id") int player_status_id);
    @Update("update player_status set magic_point=#{magic_point} where player_status_id=#{player_status_id}")
    int updateMagicpointStatus_food(@Param("magic_point") int magic_point, @Param("player_status_id") int player_status_id);
    @Select("select hit_point from player_status where player_status_id=#{player_status_id} ")
    int getHitpointStatus(@Param("player_status_id") int player_status_id);
    @Update("update player_status set hit_point=#{hit_point} where player_status_id=#{player_status_id}")
    int updateHitpointAttribute_food(@Param("hit_point") int hit_point, @Param("player_status_id") int player_status_id);
    @Update("update player_status set magic_point=#{magic_point} where player_status_id=#{player_status_id}")
    int updateMagicpointAttribute_food(@Param("magic_point") int magic_point, @Param("player_status_id") int player_status_id);
    @Select("select magic_point from player_status where player_status_id=#{player_status_id} ")
    int getMagicpointStatus(@Param("player_status_id") int player_status_id);
    @Select("select hit_point from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getHitpointAttribute(@Param("player_attribute_id") int player_attribute_id);
    @Select("select magic_point from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getMagicpointAttribute(@Param("player_attribute_id") int player_attribute_id);
    @Select("select hit_point from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getMaxhitpoint(@Param("player_attribute_id") int player_attribute_id);
    @Select("select magic_point from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getMaxmagicpoint(@Param("player_attribute_id") int player_attribute_id);
    @Select("select attack from item_effect where item_effect_id=#{item_effect_id} ")
    int getAttack(@Param("item_effect_id") int item_effect_id);
    @Select("select defence from item_effect where item_effect_id=#{item_effect_id} ")
    int getDefence(@Param("item_effect_id") int item_effect_id);
    @Select("select speed from item_effect where item_effect_id=#{item_effect_id} ")
    int getSpeed(@Param("item_effect_id") int item_effect_id);
    @Select("select attack from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getoldAttack(@Param("player_attribute_id") int player_attribute_id);
    @Select("select defence from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getoldDefence(@Param("player_attribute_id") int player_attribute_id);
    @Select("select speed from player_attribute where player_attribute_id=#{player_attribute_id} ")
    int getoldSpeed(@Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set hit_point=#{hit_point} where player_attribute_id=#{player_attribute_id}")
    int updateHitpoint(@Param("hit_point") int hit_point, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set magic_point=#{magic_point} where player_attribute_id=#{player_attribute_id}")
    int updateMagicpoint(@Param("magic_point") int magic_point, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set attack=#{attack} where player_attribute_id=#{player_attribute_id}")
    int updateAttack(@Param("attack") int attack, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set defence=#{defence} where player_attribute_id=#{player_attribute_id}")
    int updateDefence(@Param("defence") int defence, @Param("player_attribute_id") int player_attribute_id);
    @Update("update player_attribute set speed=#{speed} where player_attribute_id=#{player_attribute_id}")
    int updateSpeed(@Param("speed") int speed, @Param("player_attribute_id") int player_attribute_id);
    @Insert("INSERT INTO inventory (inventory_id, item_id, item_num) VALUES (#{inventory_id}, #{item_id}, 1)")
    int insertItem_num_room(@Param("inventory_id") int inventory_id, @Param("item_id") int item_id);
    @Select("select item_num from inventory where inventory_id=#{inventory_id} and item_id=#{item_id}")
    int getItemnumRoom(@Param("inventory_id") int inventory_id,@Param("item_id") int item_id);
}
