package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("role_initial_skill_table")
public class role_initial_skill_table {
    @Id
    private int li_id;
    private int role_initial_skill_table_id;
    private int skill_id;
}
