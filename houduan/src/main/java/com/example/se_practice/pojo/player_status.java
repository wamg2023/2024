package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("player_status")
public class player_status {
    @Id
    private int player_status_id;
    private int hit_point;
    private int magic_point;
}
