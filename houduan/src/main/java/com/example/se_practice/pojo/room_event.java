package com.example.se_practice.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Alias("room_event")
public class room_event {
    @Id
    private int room_event_id;
    private String type;
    private String description;
    private float probability;
    private int enemy_id;
    private int teleportation_room_id;
    private int gotted_item_id;
}
