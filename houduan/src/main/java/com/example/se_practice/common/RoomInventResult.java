package com.example.se_practice.common;

import com.example.se_practice.pojo.role_initial_attribute;
import com.example.se_practice.pojo.room_event;

public class RoomInventResult {
    private room_event roomEvent;
    private role_initial_attribute enemy;

    public room_event getRoomEvent() {
        return roomEvent;
    }

    public void setRoomEvent(room_event roomEvent) {
        this.roomEvent = roomEvent;
    }

    public role_initial_attribute getEnemy() {
        return enemy;
    }

    public void setEnemy(role_initial_attribute enemy) {
        this.enemy = enemy;
    }
}
