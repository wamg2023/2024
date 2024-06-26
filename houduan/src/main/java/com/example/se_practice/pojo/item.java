package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("item")
public class item {
    @Id
    private int item_id;
    private int item_attribute_id;
    private int item_effect_id;
}
