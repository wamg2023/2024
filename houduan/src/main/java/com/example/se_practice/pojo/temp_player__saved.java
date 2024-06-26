package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("temp_player__saved")
public class temp_player__saved {
    @Id
    private int player_id;
    private int selection;
    private int game_saved_id;
}
