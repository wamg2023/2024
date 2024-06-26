package com.example.se_practice.controller;

import com.example.se_practice.common.ItemInfo;
import com.example.se_practice.common.Result;
import com.example.se_practice.pojo.*;
import com.example.se_practice.service.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;
    @RequestMapping("/takeItem")
    public Result takeItem(@RequestBody Map<String, String> data){

        int item_id = Integer.parseInt(data.get("item_id"));//前端传来物品id
        int game_saved_id = itemService.getGameSavedId();//在缓存表找到game_saved_id
        int room_id = itemService.getRoomId(game_saved_id);//通过game——saved——id找到房间id
        int saved_graph_id = itemService.getSavedGraphId(game_saved_id);
        int inventory_id_room = itemService.getInventoryId_room(room_id,saved_graph_id);//找到房间的物品栏id

        int inventory_id_player = itemService.getInventoryId_player(game_saved_id);//找到玩家的物品栏id

        List<Integer> item_id_player = itemService.getItem_id(inventory_id_player);//找到玩家对应的所有物品


        List<Integer> ItemAttributeId = new ArrayList<>();
        System.out.println(item_id_player.size());
        System.out.println(item_id_player.size());
        System.out.println(item_id_player.size());
        System.out.println(item_id_player.size());
        System.out.println(item_id_player.size());
        System.out.println(item_id_player.size());

        if (item_id_player.size() == 0){
            int totalweight = 0;
            int insert_item_num = itemService.insertItem_num_room(inventory_id_player, item_id);
            int item_num_room = itemService.getItemnumRoom(inventory_id_room,item_id);
            if (item_num_room == 1){
                int delete = itemService.deleteItem(inventory_id_room,item_id);
            }else {
                int item_num_room_new = itemService.updateItem_num_room(inventory_id_room, item_id);
            }
        }else {
            int totalweight = 0;
            //计算玩家当前身上物品的总重量，找到所有物品描述id
            for (Integer itemId : item_id_player) {
                int item_attribute_id = itemService.getItemAttributeId(itemId);
                int item_num = itemService.getItemnum(inventory_id_player,itemId);
                int item_weight = itemService.getItemWeight(item_attribute_id);
                int weight = item_weight * item_num;
                totalweight += weight;
                ItemAttributeId.add(item_attribute_id);
            }
            //确定玩家最大承重
            int player_attribute_id = itemService.getPlayerAttributeId(game_saved_id);
            int maxweight = itemService.getMaxweight(player_attribute_id);

            int target_item_weight=0;
            //如果总重量小于承重，则判断拾取目标物品后是否超重
            if (totalweight<maxweight){
                int target_item_attribute_id = itemService.getItemAttributeId(item_id);
                int item_weight = itemService.getItemWeight(target_item_attribute_id);
                target_item_weight = item_weight;
            };

            int item_num_room = itemService.getItemnumRoom(inventory_id_room,item_id);


            if (totalweight + target_item_weight < maxweight){
                if (item_id_player.contains(item_id)) {
                    int item_num_player = itemService.updateItem_num_player(inventory_id_player, item_id);
                } else {
                    int insert_item_num = itemService.insertItem_num_room(inventory_id_player, item_id);
                }
                if (item_num_room == 1){
                    int delete = itemService.deleteItem(inventory_id_room,item_id);
                }else {
                    int item_num_room_new = itemService.updateItem_num_room(inventory_id_room, item_id);
                }
            }
        }

        return Result.Success("aaa");
    }

    @RequestMapping("/getItem")
    public Result getItem(@RequestBody Map<String, String> data){

        int item_id = Integer.parseInt(data.get("item_id"));//前端传来物品id
        int game_saved_id = itemService.getGameSavedId();//在缓存表找到game_saved_id
        int room_id = itemService.getRoomId(game_saved_id);//通过game——saved——id找到房间id
        int saved_graph_id = itemService.getSavedGraphId(game_saved_id);
        int inventory_id_room = itemService.getInventoryId_room(room_id,saved_graph_id);//找到房间的物品栏id

        int inventory_id_player = itemService.getInventoryId_player(game_saved_id);//找到玩家的物品栏id
        List<Integer> item_id_player = itemService.getItem_id(inventory_id_player);//找到玩家对应的所有物品
        List<Integer> ItemAttributeId = new ArrayList<>();
        int totalweight = 0;
        //计算玩家当前身上物品的总重量，找到所有物品描述id
        for (Integer itemId : item_id_player) {
            int item_attribute_id = itemService.getItemAttributeId(itemId);
            int item_num = itemService.getItemnum(inventory_id_player,itemId);
            int item_weight = itemService.getItemWeight(item_attribute_id);
            int weight = item_weight * item_num;
            totalweight += weight;
            ItemAttributeId.add(item_attribute_id);
        }
        //确定玩家最大承重
        int player_attribute_id = itemService.getPlayerAttributeId(game_saved_id);
        int maxweight = itemService.getMaxweight(player_attribute_id);

        int target_item_weight=0;
        //如果总重量小于承重，则判断拾取目标物品后是否超重
        if (totalweight<maxweight){
            int target_item_attribute_id = itemService.getItemAttributeId(item_id);
            int item_weight = itemService.getItemWeight(target_item_attribute_id);
            target_item_weight = item_weight;
        };
        if (totalweight + target_item_weight < maxweight){
            int item_num_player = itemService.updateItem_num_player(inventory_id_player,item_id);
        }
        return Result.Success("aaa");
    }
    @RequestMapping("/queryItem")
    //@RequestBody Map<String, String> data
    public Result queryItem(){
        int game_saved_id = itemService.getGameSavedId();
        int inventory_id = itemService.getInventoryId(game_saved_id);
        List<inventory> Inventory= itemService.getInventory(inventory_id);
        List<Integer> ItemAttributeId = new ArrayList<>();
        List<Integer> ItemEffectId = new ArrayList<>();

        List<Integer> ItemIds =itemService.getItem_id(inventory_id);

        int totalweight = 0;
        //计算玩家当前身上物品的总重量，找到所有物品描述id
        for (Integer itemId : ItemIds) {
            int item_attribute_id = itemService.getItemAttributeId(itemId);
            int item_num = itemService.getItemnum(inventory_id,itemId);
            int item_weight = itemService.getItemWeight(item_attribute_id);
            int weight = item_weight * item_num;
            totalweight += weight;
        }

        for (Integer item_id : ItemIds) {
            int item_attribute_id = itemService.getItemAttributeId(item_id);
            int item_effect_id = itemService.getItemEffectId(item_id);
            ItemAttributeId.add(item_attribute_id);
            ItemEffectId.add(item_effect_id);
        }

        List<item_attribute> ItemAttributes = new ArrayList<>();
        for (Integer item_attribute_id:ItemAttributeId) {
            List<item_attribute> ItemAttribute = itemService.getItemAttribute(item_attribute_id);
            ItemAttributes.addAll(ItemAttribute);
        }

        List<item_effect> ItemEffects = new ArrayList<>();
        for (Integer item_effect_id:ItemEffectId) {
            List<item_effect> ItemEffect = itemService.getItemEffect(item_effect_id);
            ItemEffects.addAll(ItemEffect);
        }
        if (Inventory != null && ItemAttributes != null && ItemEffects != null) {
            // 构建一个包含两者信息的复合对象
            ItemInfo itemInfo = new ItemInfo(totalweight, Inventory, ItemAttributes, ItemEffects);
            return Result.Success("aaa",itemInfo);
        } else {
            return Result.Error("Failed to retrieve room and saved graph information");
        }
    }

    @RequestMapping("/useItem")
    public Result useItem(@RequestBody Map<String, String> data){

        int item_id = Integer.parseInt(data.get("item_id"));
        int game_saved_id = itemService.getGameSavedId();//找到游戏保存id
        int player_attribute_id = itemService.getPlayerAttributeId(game_saved_id);//找到玩家属性栏
        int player_status_id = itemService.getPlayerStatusId(game_saved_id);//找到玩家状态栏
        int inventory_id_player = itemService.getInventoryId_player(game_saved_id);//找到玩家物品栏
        int item_attribute_id = itemService.getItemAttributeId(item_id);//找到物品属性栏
        String type = itemService.getType(item_attribute_id);//找到对应物品的类型
        //如果类型是食物，则改变玩家的血量和蓝量，该物品数量减一
        //如果是武器则改变玩家的所有属性，包括全局属性和当前状态，武器数量不变

           if (type.equals("食物")){
               //食物造成的影响数值
               int item_effect_id = itemService.getItemEffectId(item_id);
               int hit_point_food = itemService.getHitpoint(item_effect_id);
               int magic_point_food = itemService.getMagicpoint(item_effect_id);

               //找到玩家当前状体数值
               int old_hit_point_status = itemService.getHitpointStatus(player_status_id);
               int old_magic_point_status = itemService.getMagicpointStatus(player_status_id);

               int new_hit_status = hit_point_food + old_hit_point_status;
               int new_magic_status = magic_point_food + old_magic_point_status;

               //食物吃下之后的数值改变
               int hit_point = itemService.updateHitpointStatus_food(new_hit_status, player_status_id);
               int magic_point = itemService.updateMagicpointStatus_food(new_magic_status, player_status_id);

               //获取改变之后的血量和蓝量
               int now_hit_point = itemService.getHitpointStatus(player_status_id);
               int now_magic_point = itemService.getMagicpointStatus(player_status_id);
               //找到玩家血量和蓝量上限
               int max_hit_point = itemService.getMaxhitpoint(player_attribute_id);
               int max_magic_point = itemService.getMaxmagicpoint(player_attribute_id);

               //如果吃下食物之后血量或者蓝量高于上限，则直接等于上限
               if(now_hit_point >= max_hit_point){
                  int gai_hit_point = itemService.updateHitpointAttribute_food(max_hit_point, player_status_id);
                  int ga_hit_point = itemService.getHitpointStatus(player_status_id);
                   System.out.println(ga_hit_point);
               }
               if(now_magic_point>=max_magic_point){
                  int gai_magic_point = itemService.updateMagicpointAttribute_food(max_magic_point, player_status_id);
               }
               //玩家背包物品数量减一，这里room是减一的sql语句
               int item_num_player = itemService.updateItem_num_room(inventory_id_player,item_id);
               int item_num = itemService.getItemnumbyID(inventory_id_player,item_id);
               if (item_num <= 0)
               {
                   int deleteitem_id = itemService.deleteItem(inventory_id_player,item_id);
               }

           }else if (type.equals("武器")){
               //找到武器影响的数值
               int item_effect_id = itemService.getItemEffectId(item_id);
               int hit_point_weapon = itemService.getHitpoint(item_effect_id);
               int magic_point_weapon = itemService.getMagicpoint(item_effect_id);
               int attack_weapon = itemService.getAttack(item_effect_id);
               int defence_weapon = itemService.getDefence(item_effect_id);
               int speed_weapon = itemService.getSpeed(item_effect_id);

               //找到玩家当前状体数值
               int old_hit_point_status = itemService.getHitpointStatus(player_status_id);
               int old_magic_point_status = itemService.getMagicpointStatus(player_status_id);

               int old_hit_point = itemService.getHitpointAttribute(player_attribute_id);
               int old_magic_point = itemService.getMagicpointAttribute(player_attribute_id);
               int old_attack = itemService.getoldAttack(player_attribute_id);
               int old_defence = itemService.getoldDefence(player_attribute_id);
               int old_speed = itemService.getoldSpeed(player_attribute_id);

               int hit_point = hit_point_weapon + old_hit_point;
               int magic_point = magic_point_weapon + old_magic_point;
               int attack = attack_weapon + old_attack;
               int defence = defence_weapon + old_defence;
               int speed = speed_weapon + old_speed;
               //首先修改玩家全局属性的数值
               int hit_point_attribute = itemService.updateHitpoint(hit_point, player_attribute_id);
               int magic_point_attribute = itemService.updateMagicpoint(magic_point, player_attribute_id);
               int attack_attribute = itemService.updateAttack(attack, player_attribute_id);
               int defence_attribute = itemService.updateDefence(defence, player_attribute_id);
               int speed_attribute= itemService.updateSpeed(speed, player_attribute_id);

//               //然后再修改玩家当前属性的数值，按百分比修改玩家的数值
//               float new_hit_point_status = old_hit_point_status * ((float) old_hit_point / hit_point);
//               float new_magic_point_status = old_magic_point_status * ((float)old_magic_point / magic_point);
//               int hitpoint = (int) new_hit_point_status;
//               int magicpoint = (int) new_magic_point_status;
//               int hit_point_status = itemService.updateHitpointStatus_food(hitpoint, player_status_id);
//               int magic_point_status = itemService.updateMagicpointStatus_food(magicpoint, player_status_id);
               //玩家背包物品数量减一，这里room是减一的sql语句
               int item_num_player = itemService.updateItem_num_room(inventory_id_player,item_id);
               int item_num = itemService.getItemnumbyID(inventory_id_player,item_id);
               if (item_num <= 0)
               {
                   int deleteitem_id = itemService.deleteItem(inventory_id_player,item_id);
               }
           }

        return Result.Success("aaa");

    }


    @RequestMapping("/removeEquipment")
    public Result removeEquipment(@RequestBody Map<String, String> data){

        int item_id = Integer.parseInt(data.get("item_id"));
        int game_saved_id = itemService.getGameSavedId();//找到游戏保存id
        int player_attribute_id = itemService.getPlayerAttributeId(game_saved_id);//找到玩家属性栏
        int player_status_id = itemService.getPlayerStatusId(game_saved_id);//找到玩家状态栏
        int inventory_id_player = itemService.getInventoryId_player(game_saved_id);//找到玩家物品栏
        int item_attribute_id = itemService.getItemAttributeId(item_id);//找到物品属性栏
        String type = itemService.getType(item_attribute_id);//找到对应物品的类型
        if (type.equals("武器") ){
            //找到武器影响的数值
            int item_effect_id = itemService.getItemEffectId(item_id);
            int hit_point_weapon = itemService.getHitpoint(item_effect_id);
            int magic_point_weapon = itemService.getMagicpoint(item_effect_id);
            int attack_weapon = itemService.getAttack(item_effect_id);
            int defence_weapon = itemService.getDefence(item_effect_id);
            int speed_weapon = itemService.getSpeed(item_effect_id);

            //找到玩家当前状体数值
            int old_hit_point_status = itemService.getHitpointStatus(player_status_id);
            int old_magic_point_status = itemService.getMagicpointStatus(player_status_id);

            int old_hit_point = itemService.getHitpointAttribute(player_attribute_id);
            int old_magic_point = itemService.getMagicpointAttribute(player_attribute_id);
            int old_attack = itemService.getoldAttack(player_attribute_id);
            int old_defence = itemService.getoldDefence(player_attribute_id);
            int old_speed = itemService.getoldSpeed(player_attribute_id);

            int hit_point = old_hit_point - hit_point_weapon;
            int magic_point = old_magic_point - magic_point_weapon;
            int attack = old_attack - attack_weapon;
            int defence = old_defence - defence_weapon;
            int speed = old_speed - speed_weapon;
            //首先修改玩家全局属性的数值
            int hit_point_attribute = itemService.updateHitpoint(hit_point, player_attribute_id);
            int magic_point_attribute = itemService.updateMagicpoint(magic_point, player_attribute_id);
            int attack_attribute = itemService.updateAttack(attack, player_attribute_id);
            int defence_attribute = itemService.updateDefence(defence, player_attribute_id);
            int speed_attribute= itemService.updateSpeed(speed, player_attribute_id);

            //如果当前血量大于脱下装备之后的上限，直接变成上限，蓝量同理
            //如果吃下食物之后血量或者蓝量高于上限，则直接等于上限
            if(old_hit_point_status > hit_point){
                int gai_hit_point = itemService.updateHitpointAttribute_food(hit_point, player_status_id);
                int ga_hit_point = itemService.getHitpointStatus(player_status_id);
                System.out.println(ga_hit_point);
            }
            if(old_magic_point_status>=magic_point){
                int gai_magic_point = itemService.updateMagicpointAttribute_food(magic_point, player_status_id);
            }

//            //然后再修改玩家当前属性的数值，按百分比修改玩家的数值
//            float new_hit_point_status = old_hit_point_status * ((float)old_hit_point / hit_point);
//            float new_magic_point_status = old_magic_point_status * ((float)old_magic_point / magic_point);
//            int hitPoint = (int) new_hit_point_status;
//            int magicePoint = (int) new_magic_point_status;
//            int hit_point_status = itemService.updateHitpointStatus_food(hitPoint, player_status_id);
//            int magic_point_status = itemService.updateMagicpointStatus_food(magicePoint, player_status_id);
            //玩家背包物品数量加一，这里player是加一的sql语句
            Integer item_id_num = itemService.getItemIdNum(inventory_id_player,item_id);
            if (item_id_num == null){
                int insert_item_num = itemService.insertItem_num_room(inventory_id_player,item_id);
            }else {
                int item_num_player = itemService.updateItem_num_player(inventory_id_player,item_id);
            }

        }

        return Result.Success("aaa");
    }
    @RequestMapping("/dropItem")
    public Result dropItem(@RequestBody Map<String, String> data){
        int item_id = Integer.parseInt(data.get("item_id"));//前端传来物品id
        int game_saved_id = itemService.getGameSavedId();//在缓存表找到game_saved_id
        int room_id = itemService.getRoomId(game_saved_id);//通过game——saved——id找到房间id
        int saved_graph_id = itemService.getSavedGraphId(game_saved_id);
        int inventory_id_room = itemService.getInventoryId_room(room_id,saved_graph_id);//找到房间的物品栏id

        int inventory_id_player = itemService.getInventoryId_player(game_saved_id);//找到玩家的物品栏id
        int item_num_player = itemService.getItemnumRoom(inventory_id_player,item_id);
        if (item_num_player==1){
            int delete_player = itemService.deleteItem(inventory_id_player,item_id);
        }else {
            int item_num_player_new = itemService.updateItem_num_room(inventory_id_player,item_id);//玩家物品减一
        }
        List<Integer> Item_ids = itemService.getItem_id(inventory_id_room);

        if (Item_ids.contains(item_id)){
            int item_num_room = itemService.updateItem_num_player(inventory_id_room, item_id);
        }else {
            int item_num_room = itemService.insertItem_num_room(inventory_id_room,item_id);
        }

        return Result.Success("aaa");
    }







}
