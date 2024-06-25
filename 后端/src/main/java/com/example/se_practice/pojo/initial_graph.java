package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
@Data
@Component
@Alias("initial_graph")
public class initial_graph {
    @Id
    private int li_id;
    private int initial_graph_id;
    private int room_id;
    private int room_flag;
    private int initial_inventory_id;
    private int east_room_id;
    private int west_room_id;
    private int south_room_id;
    private int north_room_id;
}
