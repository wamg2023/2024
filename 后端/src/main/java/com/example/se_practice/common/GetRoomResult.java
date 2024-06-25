package com.example.se_practice.common;

import com.example.se_practice.pojo.inventory;
import com.example.se_practice.pojo.item_attribute;
import com.example.se_practice.pojo.room;
import com.example.se_practice.pojo.saved_graph;

import java.util.List;

public class GetRoomResult {
    private saved_graph savedGraph;
    private room roomInfo;
    private List<inventory> AllitemInfo;
    private List<item_attribute> room_item_attribute;

    public List<item_attribute> getRoom_item_attribute() {
        return room_item_attribute;
    }

    public void setRoom_item_attribute(List<item_attribute> room_item_attribute) {
        this.room_item_attribute = room_item_attribute;
    }

    public List<inventory> getAllitemInfo() {
        return AllitemInfo;
    }

    public void setAllitemInfo(List<inventory> allitemInfo) {
        AllitemInfo = allitemInfo;
    }

    public room getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(room roomInfo) {
        this.roomInfo = roomInfo;
    }

    public saved_graph getSavedGraph() {
        return savedGraph;
    }

    public void setSavedGraph(saved_graph savedGraph) {
        this.savedGraph = savedGraph;
    }

    // 内部类，用于包装保存图和房间信息
    public GetRoomResult(saved_graph savedGraph, room roomInfo, List<inventory> AllitemInfo, List<item_attribute> room_item_attribute) {
        this.savedGraph = savedGraph;
        this.roomInfo = roomInfo;
        this.AllitemInfo = AllitemInfo;
        this.room_item_attribute = room_item_attribute;
    }
}
