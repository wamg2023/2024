package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("game_saved")
public class game_saved {
    @Id
    private int game_saved_id;
    private int player_attribute_id;
    private int player_status_id;
    private int inventory_id;
    private int player_skill_table_id;
    private int saved_graph_id;
    private int in_room_id;

    public int getSaved_graph_id() {
        return saved_graph_id;
    }

    public void setSaved_graph_id(int saved_graph_id) {
        this.saved_graph_id = saved_graph_id;
    }

    public int getGame_saved_id() {
        return game_saved_id;
    }

    public void setGame_saved_id(int game_saved_id) {
        this.game_saved_id = game_saved_id;
    }

    public int getPlayer_attribute_id() {
        return player_attribute_id;
    }

    public void setPlayer_attribute_id(int player_attribute_id) {
        this.player_attribute_id = player_attribute_id;
    }

    public int getPlayer_status_id() {
        return player_status_id;
    }

    public void setPlayer_status_id(int player_status_id) {
        this.player_status_id = player_status_id;
    }

    public int getPlayer_skill_table_id() {
        return player_skill_table_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getIn_room_id() {
        return in_room_id;
    }

    public void setIn_room_id(int in_room_id) {
        this.in_room_id = in_room_id;
    }
}
