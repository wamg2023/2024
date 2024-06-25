package com.example.se_practice.controller;

/*
* 房间相关操作的接口
*
*
*/
import com.example.se_practice.common.GetPlayerResult;
import com.example.se_practice.common.GetRoomResult;
import com.example.se_practice.common.Result;
import com.example.se_practice.common.RoomInventResult;
import com.example.se_practice.pojo.inventory;
import com.example.se_practice.pojo.room;
import com.example.se_practice.pojo.room_event;
import com.example.se_practice.pojo.saved_graph;
import com.example.se_practice.pojo.*;
import com.example.se_practice.service.GameService;
import com.example.se_practice.service.RoleService;
import com.example.se_practice.service.RoomService;
import org.slf4j.Logger;
import lombok.Getter;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private RoomService roomService;

    /*
     * 触发事件功能
     * 前端发起roomEvent请求，
     * 后端需将数据库中的该房间所存储的事件传递给前端
     * 如果该房间事件未被触发过，则将该事件传给前端，否则，返回前端成功消息，并告诉前端已经访问过该房间的消息
     */
    @GetMapping("/roomEvent")
    public Result getRoomEvent() {
        RoomInventResult thisEvent=roomService.getRoomEvent();
        if(thisEvent!=null)
            return Result.Success("事件获取成功",thisEvent);
        else
            return Result.Success("似曾相识的地方，你无比确信曾来过这里");
    }

    //刘航编写
    @PostMapping("/getRoom")
    public Result getRoom(@RequestBody Map<String, String> data) {
        int player_id = Integer.parseInt(data.get("player_id"));
        int selection = Integer.parseInt(data.get("save_id"));
        int game_saved_id = roomService.getGameSavedId(player_id, selection);//获取存档id
        int in_room_id= roomService.getInRoomId(game_saved_id);//获取当前房间id

        int saved_graph_id= roomService.getSavedGraphId(game_saved_id);//获取当前地图id
        int inventory_id = roomService.getInventoryId(saved_graph_id,in_room_id);
        saved_graph savedGraph= roomService.getSavedGraphById(saved_graph_id,in_room_id);

        List<Integer> RoomItemID = roomService.getItemId(inventory_id);
        List<item_attribute> room_item_attribute = new ArrayList<>();
        for (Integer room_item_id : RoomItemID){
            int item_attribute_id = roomService.getItemAttributeId(room_item_id);
            item_attribute room_item = roomService.getItemAttribute(item_attribute_id);
            room_item_attribute.add(room_item);
        }
        room roomInfo = roomService.getRoomById(in_room_id);

        List<inventory> itemInfo = roomService.getInventoryInfo(inventory_id);
        if (savedGraph != null && roomInfo != null) {
            // 构建一个包含两者信息的复合对象
            GetRoomResult getRoomResult = new GetRoomResult(savedGraph, roomInfo, itemInfo, room_item_attribute);
            return Result.Success("aaa",getRoomResult);
        } else {
            return Result.Error("Failed to retrieve room and saved graph information");
        }

    }



}

