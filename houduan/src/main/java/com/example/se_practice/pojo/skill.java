package com.example.se_practice.pojo;

/*
* 本表是用来描述技能相关的信息
* 由于技能只有1，2，3，4这四个
* 我们可以默认技能蓝耗为skill_id*10
*
* */
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("skill")
public class skill {
    @Id
    private int skill_id;
    private String name;
    private String description;
    private int self_effect_id;
    private int enemy_effect_id;
}
