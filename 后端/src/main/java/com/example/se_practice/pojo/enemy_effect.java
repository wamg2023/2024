package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("enemy_effect")
public class enemy_effect {
    @Id
    private int enemy_effect_id;
    private int power;
    private int attack;
    private int defence;
    private int speed;
}
