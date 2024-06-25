package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("player_attribute")
public class player_attribute {
    @Id
    private int player_attribute_id;
    private String description;
    private String type;
    private int hit_point;
    private int magic_point;
    private int attack;
    private int defence;
    private int speed;
    private int max_weight;
}
