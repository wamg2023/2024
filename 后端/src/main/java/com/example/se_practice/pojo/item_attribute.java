package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("item_attribute")
public class item_attribute {
    @Id
    private int item_attribute_id;
    private String name;
    private String type;
    private int weight;
    private String description;
}
