package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("item_effect")
public class item_effect {
    @Id
    private int item_effect_id;
    private int hit_point;
    private int magic_point;
    private int attack;
    private int defence;
    private int speed;
}
