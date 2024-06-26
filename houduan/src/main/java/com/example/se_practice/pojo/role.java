package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("role")
public class role {
    @Id
    private int role_id;
    private int role_initial_attribute_id;
    private int role_initial_skill_table_id;
}
