package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("initial_inventory")
public class initial_inventory {
    @Id
    private int li_id;
    private int initial_inventory_id;
    private int item_id;
    private int item_num;
}
