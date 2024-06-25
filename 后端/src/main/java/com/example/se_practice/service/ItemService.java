package com.example.se_practice.service;

import com.example.se_practice.dao.ItemDao;
import com.example.se_practice.exception.CustomException;
import com.example.se_practice.pojo.inventory;
import com.example.se_practice.pojo.item_attribute;
import com.example.se_practice.pojo.item_effect;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemService {
    @Resource
    private ItemDao itemDao;


    public int getGameSavedId() {
        int game_saved_id =itemDao.getGameSavedId();
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(game_saved_id==0){
            throw new CustomException("game_saved_id输入错误");
        }
        return game_saved_id;
    }

    public int getInventoryId_room(int room_id,int saved_graph_id) {
        int inventory_id =itemDao.getInventoryId_room(room_id,saved_graph_id);
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(inventory_id==0){
            throw new CustomException("room_id输入错误");
        }
        return inventory_id;
    }

    public int getInventoryId_player(int game_saved_id) {
        int inventory_id =itemDao.getInventoryId_player(game_saved_id);
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(inventory_id==0){
            throw new CustomException("game_saved_id输入错误");
        }
        return inventory_id;
    }

    public int updateItem_num_room(int inventoryId, int itemId) {
        int item_num =itemDao.updateItem_num_room(inventoryId, itemId);
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());

        return item_num;
    }
    public int updateItem_num_player(int inventoryId, int itemId) {
        int item_num =itemDao.updateItem_num_player(inventoryId, itemId);
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());

        return item_num;
    }
    public int getRoomId(int game_saved_id) {
        int getRoomId =itemDao.getRoomId(game_saved_id);
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(getRoomId==0){
            throw new CustomException("game_saved_id输入错误");
        }
        return getRoomId;
    }

//查询物品
    public int getInventoryId(int gameSavedId) {
        int inventory_id =itemDao.getInventoryId(gameSavedId);
//        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        if(inventory_id==0){
            throw new CustomException("game_saved_id输入错误");
        }
        return inventory_id;
    }

    public List<inventory> getInventory(int inventoryId) {
        return itemDao.getInventory(inventoryId);
    }

    public int getItemAttributeId(int itemId) {
        int item_attribute_id=itemDao.getItemAttributeId(itemId);
        return item_attribute_id;
    }

    public int getItemEffectId(int itemId) {
        int item_effect_id=itemDao.getItemEffectId(itemId);
        return item_effect_id;
    }

    public List<item_attribute> getItemAttribute(int itemAttributeId) {
        return itemDao.getItemAttribute(itemAttributeId);
    }

    public List<item_effect> getItemEffect(int itemEffectId) {
        return itemDao.getItemEffect(itemEffectId);
    }

    public List<Integer> getItem_id(int inventory_id) {
        return itemDao.getItem_id(inventory_id);
    }

    public int getItemnum(int inventory_id,Integer itemId) {
        return itemDao.getItemnum(inventory_id,itemId);
    }

    public int getItemWeight(int itemAttributeId) {
        return itemDao.getItemWeight(itemAttributeId);
    }

    public int getPlayerAttributeId(int gameSavedId) {
        return itemDao.getPlayerAttributeId(gameSavedId);
    }

    public int getMaxweight(int playerAttributeId) {
        return itemDao.getMaxweight(playerAttributeId);
    }

    public int getPlayerStatusId(int gameSavedId) {
        return itemDao.getPlayerStatusId(gameSavedId);
    }

    public String getType(int itemAttributeId) {
        return itemDao.getType(itemAttributeId);
    }

    public int getHitpoint(int itemEffectId) {
        return itemDao.getHitpoint(itemEffectId);
    }

    public int getMagicpoint(int itemEffectId) {
        return itemDao.getMagicpoint(itemEffectId);
    }

    public int updateHitpointStatus_food(int newHitStatus, int playerStatusId) {
        return itemDao.updateHitpointStatus_food(newHitStatus, playerStatusId);
    }

    public int updateMagicpointStatus_food(int newMagicStatus, int playerStatusId) {
        return itemDao.updateMagicpointStatus_food(newMagicStatus, playerStatusId);
    }

    public int getHitpointStatus(int playerStatusId) {
        return itemDao.getHitpointStatus(playerStatusId);
    }

    public int getMagicpointStatus(int playerStatusId) {
        return itemDao.getMagicpointStatus(playerStatusId);
    }

    public int getMaxhitpoint(int playerAttributeId) {
        return itemDao.getMaxhitpoint(playerAttributeId);
    }

    public int getMaxmagicpoint(int playerAttributeId) {
        return itemDao.getMaxmagicpoint(playerAttributeId);
    }

    public int getAttack(int itemEffectId) {
        return itemDao.getAttack(itemEffectId);
    }

    public int getDefence(int itemEffectId) {
        return itemDao.getDefence(itemEffectId);
    }

    public int getSpeed(int itemEffectId) {
        return itemDao.getSpeed(itemEffectId);
    }

    public int getoldAttack(int playerAttributeId) {
        return itemDao.getoldAttack(playerAttributeId);
    }

    public int getoldDefence(int playerAttributeId) {
        return itemDao.getoldDefence(playerAttributeId);
    }

    public int getoldSpeed(int playerAttributeId) {
        return itemDao.getoldSpeed(playerAttributeId);
    }

    public int updateHitpoint(int hitPoint, int playerAttributeId) {
        return itemDao.updateHitpoint(hitPoint, playerAttributeId);
    }

    public int updateMagicpoint(int magicPoint, int playerAttributeId) {
        return itemDao.updateMagicpoint(magicPoint, playerAttributeId);
    }

    public int updateAttack(int attack, int playerAttributeId) {
        return itemDao.updateAttack(attack, playerAttributeId);
    }

    public int updateDefence(int defence, int playerAttributeId) {
        return itemDao.updateDefence(defence, playerAttributeId);
    }

    public int updateSpeed(int speed, int playerAttributeId) {
        return itemDao.updateSpeed(speed, playerAttributeId);
    }

    public int getHitpointAttribute(int playerAttributeId) {
        return itemDao.getHitpointAttribute(playerAttributeId);
    }

    public int getMagicpointAttribute(int playerAttributeId) {
        return itemDao.getMagicpointAttribute(playerAttributeId);
    }

    public int insertItem_num_room(int inventoryId, int itemId) {
        return itemDao.insertItem_num_room(inventoryId ,itemId);
    }

    public int updateHitpointAttribute_food(int maxHitPoint, int playerStatusId) {
        return itemDao.updateHitpointAttribute_food(maxHitPoint,playerStatusId);
    }

    public int updateMagicpointAttribute_food(int maxMagicPoint, int playerStatusId) {
        return itemDao.updateMagicpointAttribute_food(maxMagicPoint,playerStatusId);
    }

    public int getItemnumbyID(int inventoryIdPlayer, int itemId) {
        return itemDao.getItemnumbyID(inventoryIdPlayer,itemId);
    }

    public int deleteItem(int inventoryIdPlayer, int itemId) {
        return itemDao.deleteItem(inventoryIdPlayer,itemId);
    }

    public int getItemnumRoom(int inventoryIdRoom, int itemId) {
        return itemDao.getItemnumRoom(inventoryIdRoom,itemId);
    }

    public int getSavedGraphId(int gameSavedId) {
        return itemDao.getSavedGraphId(gameSavedId);
    }

    public Integer getItemIdNum(int inventoryIdPlayer, int itemId) {
        return itemDao.getItemIdNum(inventoryIdPlayer,itemId);
    }
}
