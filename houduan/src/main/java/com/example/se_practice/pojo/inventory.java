package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("inventory")
public class inventory {
    @Id
    private int li_id;
    private int inventory_id;
    private int item_id;
    private int item_num;
}
