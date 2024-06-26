package com.example.se_practice.service;

import com.example.se_practice.dao.NewGameDao;
import com.example.se_practice.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewGameService {
    @Resource
    private NewGameDao newGameDao;
    public List<Integer> getSavedSelectionById(int playerId) {
        return newGameDao.getSavedSelectionById(playerId);
    }

    public int getGameSavedId(int playerId, int selection) {
        return newGameDao.getGameSavedId(playerId ,selection);
    }

    public int getPlayerAttributeId(int gameSavedId) {
        return newGameDao.getPlayerAttributeId(gameSavedId);
    }

    public int getPlayerStatusId(int gameSavedId) {
        return newGameDao.getPlayerStatusId(gameSavedId);
    }

    public int getInventoryId(int gameSavedId) {
        return newGameDao.getInventoryId(gameSavedId);
    }

    public List<Integer> getItemId(int inventoryId) {
        return newGameDao.getItemId(inventoryId);
    }

    public int getSavedGraphId(int gameSavedId) {
        return newGameDao.getSavedGraphId(gameSavedId);
    }

    public int deleteInventory(int inventoryId, Integer itemId) {
        return newGameDao.deleteInventory(inventoryId,itemId);
    }

    public int insertInventory(int inventoryId) {
        return newGameDao.insertInventory( inventoryId);
    }

    public int updatePlayerSkillTableId(int gameSavedId) {
        return newGameDao.updatePlayerSkillTableId(gameSavedId);
    }

    public List<initial_graph> getInitialGraph() {
        return newGameDao.getInitialGraph();
    }

    public List<saved_graph> updateSavedGraph(initial_graph roomId, int savedGraphId) {
        return newGameDao.updateSavedGraph(roomId, savedGraphId);
    }

    public int updateInRoomId(int game_saved_id) {
        return newGameDao.updateInRoomId(game_saved_id)
;    }

    public List<Integer> getAllGameSavedId() {
        return newGameDao.getAllGameSavedId();
    }

    public int insertGameSavedId(int playerId, int selection, int randomGameSavedId) {
        return newGameDao.insertGameSavedId(playerId,selection,randomGameSavedId);
    }

    public List<Integer> getAllPlayerAttributeId() {
        return newGameDao.getAllPlayerAttributeId();
    }

    public List<Integer> getAllPlayerStatusId() {
        return newGameDao.getAllPlayerStatusId();
    }

    public List<Integer> getAllInventoryId() {
        return newGameDao.getAllInventoryId();
    }

    public List<Integer> getAllSavedGraphId() {
        return newGameDao.getAllSavedGraphId();
    }

    public int insertGameSaved(int gameSavedId, int randomPlayerAttributeId, int randomPlayerStatusId, int randomInventoryId, int randomSavedGraphId) {

        return newGameDao.insertGameSaved(gameSavedId,randomPlayerAttributeId,randomPlayerStatusId,randomInventoryId,randomSavedGraphId);
    }

    public String getInitialDescription() {
        return newGameDao.getInitialDescription();
    }

    public String getInitialType() {
        return newGameDao.getInitialType();
    }

    public int getInitialHitPoint() {
        return newGameDao.getInitialHitPoint();
    }

    public int getInitialMagicPoint() {
        return newGameDao.getInitialMagicPoint();
    }

    public int getInitialAttack() {
        return newGameDao.getInitialAttack();
    }

    public int getInitialDefence() {
        return newGameDao.getInitialDefence();
    }

    public int getInitialSpeed() {
        return newGameDao.getInitialSpeed();
    }

    public int getInitialMaxweight() {
        return newGameDao.getInitialMaxweight();
    }

    public int updatePlayerAttributeDescription(String description, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeDescription(description,playerAttributeId);
    }

    public int updatePlayerAttributeType(String type, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeType(type,playerAttributeId);
    }

    public int updatePlayerAttributeHitPoint(int hitPoint, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeHitPoint(hitPoint,playerAttributeId);
    }

    public int updatePlayerAttributeMagicPoint(int magicPoint, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeMagicPoint(magicPoint,playerAttributeId);
    }

    public int updatePlayerAttributeAttack(int attack, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeAttack(attack,playerAttributeId);
    }

    public int updatePlayerAttributeDefence(int defence, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeDefence(defence,playerAttributeId);
    }

    public int updatePlayerAttributeSpeed(int speed, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeSpeed(speed,playerAttributeId);
    }

    public int updatePlayerAttributeMaxweight(int maxWeight, int playerAttributeId) {
        return newGameDao.updatePlayerAttributeMaxweight(maxWeight,playerAttributeId);
    }

    public int updatePlayerStatusHitPoint(int hitPoint, int playerStatusId) {
        return newGameDao.updatePlayerStatusHitPoint(hitPoint,playerStatusId);
    }

    public int updatePlayerStatusMagicPoint(int magicPoint, int playerStatusId) {
        return newGameDao.updatePlayerStatusMagicPoint(magicPoint,playerStatusId);
    }

    public List<Integer> getRoomId() {
        return newGameDao.getRoomId();

    }

    public int getSavedGraphRoomFlag(Integer roomId) {
        return newGameDao.getSavedGraphRoomFlag(roomId);
    }

    public int getSavedGraphInventoryId(Integer roomId) {
        return newGameDao.getSavedGraphInventoryId(roomId);
    }

    public int getSavedGraphEastRoom(Integer roomId) {
        return newGameDao.getSavedGraphEastRoom(roomId);
    }

    public int getSavedGraphWestRoom(Integer roomId) {
        return newGameDao.getSavedGraphWestRoom(roomId);
    }

    public int getSavedGraphSouthRoom(Integer roomId) {
        return newGameDao.getSavedGraphSouthRoom(roomId);
    }

    public int getSavedGraphNorthRoom(Integer roomId) {
        return newGameDao.getSavedGraphNorthRoom(roomId);
    }



    public int updateSavedGraphRoomFlag(int room_flag, Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphRoomFlag(room_flag,roomId,savedGraphId);
    }

    public int updateSavedGraphInventoryId(int inventory_id_initial, Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphInventoryId(inventory_id_initial,roomId,savedGraphId);
    }

    public int updateSavedGraphEastRoom(int east_room_id, Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphEastRoom(east_room_id,roomId,savedGraphId);
    }

    public int updateSavedGraphWestRoom(int west_room_id, Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphWestRoom(west_room_id,roomId,savedGraphId);
    }

    public int updateSavedGraphSouthRoom(int south_room_id, Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphSouthRoom(south_room_id,roomId,savedGraphId);
    }

    public int updateSavedGraphNorthRoom(int north_room_id, Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphNorthRoom(north_room_id,roomId,savedGraphId);
    }

    public int updateSavedGraphLastRoom( Integer roomId, int savedGraphId) {
        return newGameDao.updateSavedGraphLastRoom(roomId,savedGraphId);
    }

    public int insertPlayerAttribute(int randomPlayerAttributeId) {
        return newGameDao.insertPlayerAttribute(randomPlayerAttributeId);
    }

    public int insertPlayerStatus(int randomPlayerStatusId) {
        return newGameDao.insertPlayerStatus(randomPlayerStatusId);
    }

    public int insertGraphId(int randomSavedGraphId, Integer roomId) {
        return newGameDao.insertGraphId(randomSavedGraphId,roomId);
    }

    public int updateTempPlayerSaved(int playerId, int selection) {
        return newGameDao.updateTempPlayerSaved(playerId,selection);
    }

    public int updateGameSavedId(int game_saved_id) {
        return newGameDao.updateGameSavedId(game_saved_id);
    }
}
