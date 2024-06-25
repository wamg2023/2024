package com.example.se_practice.controller;

import com.example.se_practice.common.Result;
import com.example.se_practice.pojo.*;
import com.example.se_practice.service.NewGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class NewGameController {

    @Resource
    private NewGameService newGameService;
    @RequestMapping("/newGame")
    public Result NewGame(@RequestBody Map<String, String> data) {

        int player_id = Integer.parseInt(data.get("player_id"));
        int selection = Integer.parseInt(data.get("save_id"));
        int temp_player_saved = newGameService.updateTempPlayerSaved(player_id,selection);

        //找到所有的selection
        List<Integer> savedSelection = newGameService.getSavedSelectionById(player_id);
        //如果有对应的selection就修改对应的表，没有则插入
        if (savedSelection.contains(selection)){
            int game_saved_id = newGameService.getGameSavedId(player_id, selection);//获取存档id
            int player_attribute_id = newGameService.getPlayerAttributeId(game_saved_id);
            int player_status_id = newGameService.getPlayerStatusId(game_saved_id);//找到玩家状态栏
            int inventory_id = newGameService.getInventoryId(game_saved_id);
            List<Integer> ItemId = newGameService.getItemId(inventory_id);
            int saved_graph_id = newGameService.getSavedGraphId(game_saved_id);
            int in_room_id = 11;
            //role_initial_attribute_id=1
            //保存初始化的role的所有数据
            //String description = newGameService.getInitialDescription();
            //String type = newGameService.getInitialType();
            int hit_point = newGameService.getInitialHitPoint();
            int magic_point = newGameService.getInitialMagicPoint();
            int attack = newGameService.getInitialAttack();
            int defence = newGameService.getInitialDefence();
            int speed = newGameService.getInitialSpeed();
            int max_weight = newGameService.getInitialMaxweight();

            // 更新 player_attribute和player_status 的各个属性
            int description_attribute = newGameService.updatePlayerAttributeDescription("description",player_attribute_id);
            int type_attribute = newGameService.updatePlayerAttributeType("type",player_attribute_id);
            int hit_point_attribute = newGameService.updatePlayerAttributeHitPoint(hit_point,player_attribute_id);
            int magic_point_attribute = newGameService.updatePlayerAttributeMagicPoint(magic_point,player_attribute_id);
            int attack_attribute = newGameService.updatePlayerAttributeAttack(attack,player_attribute_id);
            int defence_attribute = newGameService.updatePlayerAttributeDefence(defence,player_attribute_id);
            int speed_attribute = newGameService.updatePlayerAttributeSpeed(speed,player_attribute_id);
            int max_weight_attribute = newGameService.updatePlayerAttributeMaxweight(max_weight,player_attribute_id);

            int hit_point_status = newGameService.updatePlayerStatusHitPoint(hit_point,player_status_id);
            int magic_point_status = newGameService.updatePlayerStatusMagicPoint(magic_point,player_status_id);



            //查询物品栏，如果有就删掉，插入新的物品栏
            for (Integer itemId : ItemId) {
                int delete = newGameService.deleteInventory(inventory_id, itemId );
            }
           // int insertinventory = newGameService.insertInventory(inventory_id);

            //技能表
            int insertskill = newGameService.updatePlayerSkillTableId(game_saved_id);

            //存档地图
            List<initial_graph> InitialGraph = newGameService.getInitialGraph();
            List<Integer> room_ids = newGameService.getRoomId();
            for (Integer room_id : room_ids){

                int room_flag = newGameService.getSavedGraphRoomFlag(room_id);
                int inventory_id_initial = newGameService.getSavedGraphInventoryId(room_id);
                int east_room_id = newGameService.getSavedGraphEastRoom(room_id);
                int west_room_id = newGameService.getSavedGraphWestRoom(room_id);
                int south_room_id = newGameService.getSavedGraphSouthRoom(room_id);
                int north_room_id = newGameService.getSavedGraphNorthRoom(room_id);

                int room_flag_saved = newGameService.updateSavedGraphRoomFlag(room_flag,room_id,saved_graph_id);
                int inventory_id_saved = newGameService.updateSavedGraphInventoryId(inventory_id_initial,room_id,saved_graph_id);
                int east_room_id_saved = newGameService.updateSavedGraphEastRoom(east_room_id,room_id,saved_graph_id);
                int west_room_id_saved = newGameService.updateSavedGraphWestRoom(west_room_id,room_id,saved_graph_id);
                int south_room_id_saved = newGameService.updateSavedGraphSouthRoom(south_room_id,room_id,saved_graph_id);
                int north_room_id_saved = newGameService.updateSavedGraphNorthRoom(north_room_id,room_id,saved_graph_id);
                int last_room_id_saved = newGameService.updateSavedGraphLastRoom(room_id,saved_graph_id);
            }
            //初始所在房间
            int RoomID = newGameService.updateInRoomId(game_saved_id);
            int temp_game_saved_id = newGameService.updateGameSavedId(game_saved_id);

        }else {
            //如果数据库里面没有这个存档，就直接插入一个新的selection
            Random random = new Random();
            //生成一个1到99的随机数

            List<Integer> ListGameSavedId = newGameService.getAllGameSavedId();
            int randomGameSavedId;
            do {
                randomGameSavedId = random.nextInt(99) + 1; // 生成一个1到99之间的随机数
            } while (ListGameSavedId.contains(randomGameSavedId));
            int insertgamesavedid = newGameService.insertGameSavedId(player_id, selection , randomGameSavedId);
            int game_saved_id = newGameService.getGameSavedId(player_id, selection);//获取存档id


            //开始插入数据，插入player_attribute=1
            List<Integer> ListPlayerAttributeId = newGameService.getAllPlayerAttributeId();
            int randomPlayerAttributeId;
            do {
                randomPlayerAttributeId = random.nextInt(99) + 1; // 生成一个1到99之间的随机数
            } while (ListPlayerAttributeId.contains(randomPlayerAttributeId));

            List<Integer> ListPlayerStatusId = newGameService.getAllPlayerStatusId();
            int randomPlayerStatusId;
            do {
                randomPlayerStatusId = random.nextInt(99) + 1; // 生成一个1到99之间的随机数
            } while (ListPlayerStatusId.contains(randomPlayerStatusId));

            List<Integer> ListInventoryId = newGameService.getAllInventoryId();
            int randomInventoryId;
            do {
                randomInventoryId = random.nextInt(99) + 1; // 生成一个1到99之间的随机数
            } while (ListInventoryId.contains(randomInventoryId));

            List<Integer> ListSavedGraphId = newGameService.getAllSavedGraphId();
            int randomSavedGraphId;
            do {
                randomSavedGraphId = random.nextInt(99) + 1; // 生成一个1到99之间的随机数
            } while (ListSavedGraphId.contains(randomSavedGraphId));

            int allInsert = newGameService.insertGameSaved(game_saved_id,randomPlayerAttributeId,randomPlayerStatusId,randomInventoryId,randomSavedGraphId);
            //role_initial_attribute_id=1
            //保存初始化的role的所有数据
            //String description = newGameService.getInitialDescription();

//            System.out.println(description);
//            System.out.println(description);
//            System.out.println(description);
//            System.out.println(description);
//            System.out.println(description);
            //String type = newGameService.getInitialType();
            int hit_point = newGameService.getInitialHitPoint();
            int magic_point = newGameService.getInitialMagicPoint();
            int attack = newGameService.getInitialAttack();
            int defence = newGameService.getInitialDefence();
            int speed = newGameService.getInitialSpeed();
            int max_weight = newGameService.getInitialMaxweight();

            // 更新 player_attribute 的各个属性
            int AllPlayerAttribute = newGameService.insertPlayerAttribute(randomPlayerAttributeId);
            int description_attribute = newGameService.updatePlayerAttributeDescription("description",randomPlayerAttributeId);
            int type_attribute = newGameService.updatePlayerAttributeType("type",randomPlayerAttributeId);
            int hit_point_attribute = newGameService.updatePlayerAttributeHitPoint(hit_point,randomPlayerAttributeId);
            int magic_point_attribute = newGameService.updatePlayerAttributeMagicPoint(magic_point,randomPlayerAttributeId);
            int attack_attribute = newGameService.updatePlayerAttributeAttack(attack,randomPlayerAttributeId);
            int defence_attribute = newGameService.updatePlayerAttributeDefence(defence,randomPlayerAttributeId);
            int speed_attribute = newGameService.updatePlayerAttributeSpeed(speed,randomPlayerAttributeId);
            int max_weight_attribute = newGameService.updatePlayerAttributeMaxweight(max_weight,randomPlayerAttributeId);


            int PlayerStatus = newGameService.insertPlayerStatus(randomPlayerStatusId);
            int hit_point_status = newGameService.updatePlayerStatusHitPoint(hit_point,randomPlayerStatusId);
            int magic_point_status = newGameService.updatePlayerStatusMagicPoint(magic_point,randomPlayerStatusId);
            //newGameService.insertInventory(randomInventoryId);


            List<initial_graph> InitialGraph = newGameService.getInitialGraph();
            List<Integer> room_ids = newGameService.getRoomId();
            for (Integer room_id : room_ids){
                int room_flag = newGameService.getSavedGraphRoomFlag(room_id);
                int inventory_id_initial = newGameService.getSavedGraphInventoryId(room_id);
                int east_room_id = newGameService.getSavedGraphEastRoom(room_id);
                int west_room_id = newGameService.getSavedGraphWestRoom(room_id);
                int south_room_id = newGameService.getSavedGraphSouthRoom(room_id);
                int north_room_id = newGameService.getSavedGraphNorthRoom(room_id);


                int insert_savedGraphId = newGameService.insertGraphId(randomSavedGraphId,room_id);
                int room_flag_saved = newGameService.updateSavedGraphRoomFlag(room_flag,room_id,randomSavedGraphId);
                int inventory_id_saved = newGameService.updateSavedGraphInventoryId(inventory_id_initial,room_id,randomSavedGraphId);
                int east_room_id_saved = newGameService.updateSavedGraphEastRoom(east_room_id,room_id,randomSavedGraphId);
                int west_room_id_saved = newGameService.updateSavedGraphWestRoom(west_room_id,room_id,randomSavedGraphId);
                int south_room_id_saved = newGameService.updateSavedGraphSouthRoom(south_room_id,room_id,randomSavedGraphId);
                int north_room_id_saved = newGameService.updateSavedGraphNorthRoom(north_room_id,room_id,randomSavedGraphId);
                int last_room_id_saved = newGameService.updateSavedGraphLastRoom(room_id,randomSavedGraphId);
            }

            int temp_game_saved_id = newGameService.updateGameSavedId(randomGameSavedId);

        }



        return Result.Success("aaaa");
    }

}
