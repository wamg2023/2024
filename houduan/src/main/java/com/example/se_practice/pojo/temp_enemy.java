package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("temp_enemy")
public class temp_enemy {
    @Id
    private int enemy_id;
    private int hit_point;
    private int enemy_li_id;
}
