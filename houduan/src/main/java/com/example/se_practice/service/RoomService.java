package com.example.se_practice.service;

import com.example.se_practice.common.RoomInventResult;
import com.example.se_practice.dao.PlayerDao;
import com.example.se_practice.dao.RoleDao;
import com.example.se_practice.dao.RoomDao;
import com.example.se_practice.dao.TempDao;
import com.example.se_practice.exception.CustomException;
import com.example.se_practice.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class RoomService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private TempDao tempDao;
    @Resource
    private PlayerDao playerDao;
    @Resource
    private RoomDao roomDao;
    private game_saved game_saved1;
    //获取临时存档ID
    private int getTempSavedID(){
        return tempDao.getTempSavedID();
    }
    //获取当前所在房间ID
    private int getNowInRoomId(int game_saved_id){
        return tempDao.getNowInRoomId(game_saved_id);
    }
    //获取当前地图存储ID
    private int getSavedGraphID(int game_saved_id){
        return tempDao.getSavedGraphID(game_saved_id);
    }
    //获取当前房间事件ID
    private int getInRoomEventID(int in_room_id){
        return roomDao.getRoom_event_id(in_room_id);
    }
    //获取当前房间敌人ID
    private int getEnemyID(int room_envent_id){
        return roomDao.getRoomEnemyID(room_envent_id);
    }
    //获取房间内事件状态
    private int getRoom_flag(int saved_graph_id,int room_id){
        return roomDao.getRoomFlag(saved_graph_id,room_id);
    }
    //获取房间数量总数
    private int getRoom_num(){
        return roomDao.getRoomNum();
    }
    public RoomInventResult getRoomEvent() {
        RoomInventResult roomInventResult=new RoomInventResult();
        //获取存档ID
        int game_saved_id=this.getTempSavedID();
        //获取地图存储ID
        int saved_graph_id=this.getSavedGraphID(game_saved_id);
        //获取当前所在房间ID
        int in_room_id=this.getNowInRoomId(game_saved_id);
        //获取当前房间状态，1代表未访问，0代表已访问
        int room_flag=this.getRoom_flag(saved_graph_id,in_room_id);
        //System.out.println(room_flag);
        int room_event_id=this.getInRoomEventID(in_room_id);
        //获取房间内敌人ID
        int enemy_id=this.getEnemyID(room_event_id);
        if(room_flag==1&&room_event_id==4){
            //找出该房间的事件
            room_event NowRoomEvent=roomDao.getRoom_event(room_event_id);
            roomInventResult.setRoomEvent(NowRoomEvent);
            //表示进入了随机房间，在传送事件之间需要先将玩家随机传送到某地
            //获取房间数量总数
            // 创建 Random 对象
            // 生成不等于该房间ID的随机数
            int Room_num=this.getRoom_num();
            Random random = new Random();
            int randomRoomId;
            do {
                randomRoomId = random.nextInt(Room_num )+ 1;
            }while (randomRoomId==in_room_id);
            //先用当前房间ID更新要去的房间的上一个房间的ID
            tempDao.updateNexRoom_LastRoom(saved_graph_id,randomRoomId,in_room_id);
            //将下一个房间置为已访问状态
            tempDao.updateRoomFlag(game_saved_id,randomRoomId);
            //更新玩家当前所在房间信息
            tempDao.updateNowInRoomId(randomRoomId,game_saved_id);
            //将当前房间置为已访问状态
            //前后端对接v1修改
            tempDao.updateRoomFlag(game_saved_id,in_room_id);
            roomInventResult.setEnemy(null);
            return roomInventResult;
        }else if(room_flag==1){
            role_initial_attribute tempEnemy=roleDao.getRoleInitialAttribute(enemy_id);
            roomInventResult.setEnemy(tempEnemy);
            room_event NowRoomEvent=roomDao.getRoom_event(room_event_id);
            roomInventResult.setRoomEvent(NowRoomEvent);
            tempDao.updateRoomFlag(game_saved_id,in_room_id);
            return roomInventResult;
        }
        else
            return null;
    }

    //刘航编写
    public int getGameSavedId(int player_id, int selection) {

        int game_saved_id=roomDao.getGameSavedId(player_id, selection);;
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(game_saved_id==0){
            throw new CustomException("角色名或存档号输入错误");
        }
        return game_saved_id;

    }

    public int getInRoomId(int game_saved_id) {
        int in_room_id =roomDao.getInRoomId(game_saved_id);;
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(in_room_id==0){
            throw new CustomException("game_saved_id输入错误");
        }
        return in_room_id;
    }


    public int getSavedGraphId(int game_saved_id) {
        int saved_graph_id =roomDao.getSavedGraphId(game_saved_id);;
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(saved_graph_id==0){
            throw new CustomException("game_saved_id输入错误");
        }
        return saved_graph_id;
    }



    public room getRoomById(int roomId) {
        return roomDao.RoomfindById(roomId);
    }

    public saved_graph getSavedGraphById(int savedGraphId,int room_id) {
        return roomDao.SavedGraphfindById(savedGraphId,room_id);
    }


    public int getInventoryId(int savedGraphId, int room_id) {
        return roomDao.getInventoryId(savedGraphId,room_id);
    }

    public List<inventory> getInventoryInfo(int inventoryId) {
        return roomDao.getInventoryInfo(inventoryId);
    }

    public List<Integer> getItemId(int inventoryId) {
        return roomDao.getItemId(inventoryId);
    }

    public int getItemAttributeId(Integer roomItemId) {
        return roomDao.getItemAttributeId(roomItemId);
    }

    public item_attribute getItemAttribute(int itemAttributeId) {
        return roomDao.getItemAttribute(itemAttributeId);
    }
}
