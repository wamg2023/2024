package com.example.se_practice.pojo;
/*
* 本表用于存储地图信息，
* 约定：
* saved_graph_id用于标识存档
* room_id标志房间号
* room_flag标志该房间有无被访问，1代表未被访问，0代表已被访问
* inventory_id标志房间对应的物品栏
* east_room_id，标志东边相连的房间，其值为对应的房间号，west_room_id等三个同本例
* last_room_id，标志进入该房间之前所在的房间，即玩家通过哪个房间进入本房间
* */
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
@Alias("saved_graph")
public class saved_graph {
    @Id
    private int li_id;
    private int saved_graph_id;
    private int room_id;
    private int room_flag;
    private int inventory_id;
    private int east_room_id;
    private int west_room_id;
    private int south_room_id;
    private int north_room_id;
    private int last_room_id;
}
