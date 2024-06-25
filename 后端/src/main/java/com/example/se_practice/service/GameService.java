package com.example.se_practice.service;

import com.example.se_practice.common.CastSkillResult;
import com.example.se_practice.dao.*;
import com.example.se_practice.exception.CustomException;
import com.example.se_practice.pojo.game_saved;
import com.example.se_practice.pojo.temp_enemy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GameService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private SkillDao skillDao;
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

    //获取当前所在房间的上一个房间的ID
    private int getLastRoomID(int saved_graph_id,int in_room_id){
        return tempDao.getLastRoomID(saved_graph_id, in_room_id);
    }

    //获取当前地图存储ID
    private int getSavedGraphID(int game_saved_id){
        return tempDao.getSavedGraphID(game_saved_id);
    }

    //获取当前玩家状态存储ID
    private int getPlayerStatusID(int game_saved_id){
        return tempDao.getPlayerStatusID(game_saved_id);
    }

    //获取当前玩家状态存储ID
    private int getPlayerAttributeID(int game_saved_id){
        return tempDao.getPlayerAttributeID(game_saved_id);
    }

    //获取当前房间事件ID
    private int getInRoomEventID(int in_room_id){
        return roomDao.getRoom_event_id(in_room_id);
    }

    //获取房间内事件状态
    private int getRoom_flag(int saved_graph_id,int room_id){
        return roomDao.getRoomFlag(saved_graph_id,room_id);
    }

    //获取房间内敌人ID
    private int getRoomEnemyID(int room_event_id){
        return roomDao.getRoomEnemyID(room_event_id);
    }

    //获取敌人血量
    private int getEnemyNowBlood(int enemyID) {
        return roleDao.getEnemyNowBlood(enemyID);
    }

    //获取释放技能后的敌人数据
    private temp_enemy getTempEnemy(){
        return tempDao.getTempEnemy();
    }

    //获取角色血量
    private int getPlayerHitPoint(int player_status_id){
        return playerDao.getRoleHitPoint(player_status_id);
    }

    //获取角色蓝量
    private int getPlayerMagicPoint(int player_status_id){
        return playerDao.getRoleMagicPoint(player_status_id);
    }

    //获取玩家攻击力
    private int getPlayerAttack(int player_attribute_id){
        return playerDao.getPlayerAttack(player_attribute_id);
    }

    //获取玩家防御力
    private int getPlayerDefence(int player_attribute_id){
        return playerDao.getPlayerDefence(player_attribute_id);
    }

    //获取玩家速度
    private int getPlayerSpeed(int player_attribute_id){
        return playerDao.getPlayerSpeed(player_attribute_id);
    }


    //获取角色攻击力
    private int getRoleAttack(int role_initial_attribute_id){
        return roleDao.getRoleAttack(role_initial_attribute_id);
    }

    //获取角色防御力
    private int getRoleDefence(int role_initial_attribute_id){
        return roleDao.getRoleDefence(role_initial_attribute_id);
    }

    //获取角色速度
    private int getRoleSpeed(int role_initial_attribute_id){
        return roleDao.getRoleSpeed(role_initial_attribute_id);
    }

    //获取对敌效果ID
    private int getEnemyEffectID(int skill_id){
        return skillDao.getEnemyEffectID(skill_id);
    }

    //获取技能power
    private int getSkillPower(int enemy_effect_id){
        return skillDao.getSkillPower(enemy_effect_id);
    }

    //获取技能伤害
    private int getSkillEffect(int power,int self_attack,int enemy_defence){
        return power*(self_attack-enemy_defence);
    }



    //移动功能
    public void moveTo(int next_room_id) {
        int game_saved_id=this.getTempSavedID();
        int OldInRoomId=this.getNowInRoomId(game_saved_id);
        int GraphSavedId=this.getSavedGraphID(game_saved_id);
        //先用当前房间ID更新要去的房间的上一个房间的ID
        tempDao.updateNexRoom_LastRoom(GraphSavedId,next_room_id,OldInRoomId);
        /*//将下一个房间置为已访问状态
        tempDao.updateRoomFlag(game_saved_id,next_room_id);*/
        //更新玩家当前所在房间信息
        tempDao.updateNowInRoomId(next_room_id,game_saved_id);
    }
    //后退功能
    public void back() {
        int game_saved_id=this.getTempSavedID();
        int in_room_id=this.getNowInRoomId(game_saved_id);
        int saved_graph_id=this.getSavedGraphID(game_saved_id);
        //获取当前房间的上一个房间的ID
        int now_last_room_id=this.getLastRoomID(saved_graph_id,in_room_id);
        if(now_last_room_id!=-1){
            int temp=now_last_room_id;
            tempDao.updateNexRoom_LastRoom(saved_graph_id,in_room_id,-1);
            tempDao.updateNowInRoomId(temp,game_saved_id);
        }
        else throw new CustomException("您已回到最初的起点，无路可退！");
    }

    //战斗准备功能
    public temp_enemy startFight() {
        //获取存档ID
        int game_saved_id=this.getTempSavedID();
        //获取地图存储ID
        int saved_graph_id=this.getSavedGraphID(game_saved_id);
        //获取当前所在房间ID
        int in_room_id=this.getNowInRoomId(game_saved_id);
        //在准备阶段将房间状态置为1,表示战斗事件开始
        tempDao.updateRoomFlagTo1(game_saved_id,in_room_id);
        //获取当前房间状态，1代表未访问，0代表已访问
        int room_flag=this.getRoom_flag(saved_graph_id,in_room_id);
        if(room_flag==1){
            int room_event_id=this.getInRoomEventID(in_room_id);
            int enemyID=this.getRoomEnemyID(room_event_id);
            //获取敌人血量,并将当前敌人ID及其血量存到缓存表temp_enemy中
            int Enemy_now_blood=this.getEnemyNowBlood(enemyID);
            tempDao.updateTempEnemy(enemyID,Enemy_now_blood);
            temp_enemy tempEnemy=tempDao.getTempEnemy();
            return tempEnemy;
        }
        //理论上本情况不应该发生，room_flag不为1时才会触发本情况，而战斗准备时已经将房间flg设为了1
        // 理论上必定发生上面的那种情况，本句只是以防万一
        else {
            tempDao.updateRoomFlag(game_saved_id,in_room_id);
            throw new CustomException("敌人已经被勇敢的你打跑了~~~");
        }
    }

    //释放技能的功能
    public CastSkillResult CastSkill(int skill_id) {
        CastSkillResult castSkillResult=new CastSkillResult();
        //获取存档ID
        int game_saved_id=this.getTempSavedID();
        //查找一些变量，为被打死之后返回上一个房间做准备
        //获取地图ID
        int saved_graph_id=this.getSavedGraphID(game_saved_id);
        //获取当前所在房间ID
        int in_room_id=this.getNowInRoomId(game_saved_id);
        //获取当前房间所在的上一个房间ID
        int last_room_id=this.getLastRoomID(saved_graph_id,in_room_id);
        //查找一些变量，为释放技能做准备
        //获取玩家属性ID
        int  player_attribute_id=this.getPlayerAttributeID(game_saved_id);
        //获取玩家当前攻击力
        int player_attack=this.getPlayerAttack(player_attribute_id);
        //获取玩家当前防御力
        int player_defence=this.getPlayerDefence(player_attribute_id);
        //获取玩家当前速度
        int player_speed=this.getPlayerSpeed(player_attribute_id);
        //获取玩家状态存储ID
        int player_status_id=this.getPlayerStatusID(game_saved_id);
        //获取玩家血量
        int player_hit_point=this.getPlayerHitPoint(player_status_id);
        //获取玩家蓝量
        int player_magic_point=this.getPlayerMagicPoint(player_status_id);
        //获取怪物ID
        int enemy_id=tempDao.getTempEnemyID();
        //获取怪物攻击力
        int enemy_attack=this.getRoleAttack(enemy_id);
        //获取怪物防御力
        int enemy_defence=this.getRoleDefence(enemy_id);
        //获取怪物速度
        int enemy_speed=this.getRoleSpeed(enemy_id);
        //获取怪物血量
        int enemy_hit_point=tempDao.getTempEnemyNowBlood();
        //获取技能名字
        String skill_name=skillDao.getSkillName(skill_id);
        //获取技能对敌效果ID
        int skill_enemy_effect_id=this.getEnemyEffectID(skill_id);
        //获取技能power
        int skill_power=this.getSkillPower(skill_enemy_effect_id);
        //获取玩家技能威力，技能威力的计算公式为：skill.power*(self.attack-enemy.defence))
        int player_skill_effect=this.getSkillEffect(skill_power,player_attack,enemy_defence);
        // 确保最小值为1,即强制出伤为1
        player_skill_effect = Math.max(player_skill_effect, 1);
        //获取敌人攻击威力，规定：敌人的power默认为2
        int enemy_skill_effect=this.getSkillEffect(2,enemy_attack,player_defence);
        // 确保最小值为1,即强制出伤为1
        enemy_skill_effect=Math.max(enemy_skill_effect, 1);
        if(player_magic_point>=skill_id*10) {
            if (player_speed >= enemy_speed) {
                //计算玩家蓝量
                player_magic_point = player_magic_point - skill_id * 10;
                player_magic_point = Math.max(player_magic_point, 0);
                //计算敌人的血量，并设置消息描述
                enemy_hit_point = enemy_hit_point - player_skill_effect;
                String first_effect = String.format("你使用技能%s对敌人造成了%d点伤害!", skill_name, player_skill_effect);
                castSkillResult.setFirst_effect(first_effect);
                if (enemy_hit_point > 0) {
                    //更新缓存表中敌人的血量
                    tempDao.updateTempEnemyHitPoint(enemy_hit_point);
                    //敌人发动攻击
                    //计算玩家血量
                    player_hit_point=player_hit_point-enemy_skill_effect;
                    player_hit_point = Math.max(player_hit_point, 0);
                    //如果玩家血量不为0，更新表，返回伤害信息
                    if(player_hit_point>0){
                        //更新玩家血量和蓝量，并设置消息描述
                        playerDao.updateRoleHitPoint(player_status_id,player_hit_point);
                        playerDao.updateRoleMagicPoint(player_status_id,player_magic_point);
                        String second_effect=String.format("敌人对您造成了%d点伤害!", enemy_skill_effect);
                        castSkillResult.setSecond_effect(second_effect);
                        temp_enemy tempEnemy=this.getTempEnemy();
                        castSkillResult.setTempEnemy(tempEnemy);
                        castSkillResult.setFightIsContinue(true);
                    }else {
                        //如果玩家血量减为0，更新表，并发送被击杀信息
                        playerDao.updateRoleHitPoint(player_status_id,player_hit_point);
                        playerDao.updateRoleMagicPoint(player_status_id,player_magic_point);
                        temp_enemy tempEnemy=this.getTempEnemy();
                        castSkillResult.setTempEnemy(tempEnemy);
                        castSkillResult.setFightIsContinue(false);
                        //玩家被击杀，将玩家所在位置更新到上一个房间
                        playerDao.updateNowInRoom(game_saved_id,last_room_id);
                        //将玩家血量置为1
                        playerDao.updateRoleHitPoint(player_status_id,1);
                        castSkillResult.setSecond_effect("敌将凶猛，您被击杀了！！勇者还请更换装备重新来过！");
                    }
                } else {
                    //更新玩家蓝量
                    playerDao.updateRoleMagicPoint(player_status_id,player_magic_point);
                    //更新缓存表中敌人的血量
                    tempDao.updateTempEnemyHitPoint(enemy_hit_point);
                    //将房间状态置为0，表示战斗事件结束
                    tempDao.updateRoomFlag(game_saved_id,in_room_id);
                    castSkillResult.setSecond_effect("无畏的勇士，你成功战胜了敌人！");
                    temp_enemy tempEnemy=this.getTempEnemy();
                    castSkillResult.setTempEnemy(tempEnemy);
                    castSkillResult.setFightIsContinue(false);
                }
            } else {
                //玩家速度小于敌人时
                //敌人发动攻击
                //计算玩家血量，并设置消息描述
                player_hit_point=player_hit_point-enemy_skill_effect;
                player_hit_point = Math.max(player_hit_point, 0);
                String first_effect=String.format("敌人对您造成了%d点伤害!", enemy_skill_effect);
                castSkillResult.setFirst_effect(first_effect);
                if (player_hit_point > 0) {
                    //更新玩家血量
                    playerDao.updateRoleHitPoint(player_status_id,player_hit_point);
                    //玩家发动攻击
                    //计算玩家蓝量
                    player_magic_point = player_magic_point - skill_id * 10;
                    player_magic_point = Math.max(player_magic_point, 0);
                    //计算敌人的血量，
                    enemy_hit_point = enemy_hit_point - player_skill_effect;
                    //如果敌人血量不为0，更新表，返回伤害信息
                    if(enemy_hit_point>0){
                        //更新敌人血量和玩家蓝量，并设置消息描述
                        //更新缓存表中敌人的血量
                        playerDao.updateRoleMagicPoint(player_status_id,player_magic_point);
                        tempDao.updateTempEnemyHitPoint(enemy_hit_point);
                        String second_effect=String.format("你使用技能%s对敌人造成了%d点伤害!", skill_name, player_skill_effect);
                        castSkillResult.setSecond_effect(second_effect);
                        temp_enemy tempEnemy=this.getTempEnemy();
                        castSkillResult.setTempEnemy(tempEnemy);
                        castSkillResult.setFightIsContinue(true);
                    }else {
                        //如果敌人血量减为0，更新表，并发送被击杀信息
                        playerDao.updateRoleMagicPoint(player_status_id,player_magic_point);
                        tempDao.updateTempEnemyHitPoint(enemy_hit_point);
                        //将房间状态置为0，表示战斗事件结束
                        tempDao.updateRoomFlag(game_saved_id,in_room_id);
                        castSkillResult.setSecond_effect("无畏的勇士，你成功战胜了敌人！");
                        temp_enemy tempEnemy=this.getTempEnemy();
                        castSkillResult.setTempEnemy(tempEnemy);
                        castSkillResult.setFightIsContinue(false);
                    }
                } else {
                    //更新玩家血量
                    playerDao.updateRoleHitPoint(player_status_id,player_hit_point);
                    //玩家被击杀，将玩家所在位置更新到上一个房间
                    playerDao.updateNowInRoom(game_saved_id,last_room_id);
                    //将玩家血量置为1
                    playerDao.updateRoleHitPoint(player_status_id,1);
                    castSkillResult.setSecond_effect("敌将凶猛，您被击杀了！！勇者还请更换装备重新来过！");
                    temp_enemy tempEnemy=this.getTempEnemy();
                    castSkillResult.setTempEnemy(tempEnemy);
                    castSkillResult.setFightIsContinue(false);
                }

            }
        }else{
            //蓝量不够时释放技能时，等死！
            //敌人发动攻击
            //计算玩家血量，并设置消息描述
            player_hit_point=player_hit_point-enemy_skill_effect;
            player_hit_point = Math.max(player_hit_point, 0);
            String first_effect=String.format("敌人对您造成了%d点伤害!", enemy_skill_effect);
            castSkillResult.setFirst_effect(first_effect);
            if (player_hit_point > 0) {
                //更新玩家血量
                playerDao.updateRoleHitPoint(player_status_id,player_hit_point);
                castSkillResult.setSecond_effect("蓝量不足以支持您释放技能了，等死吧~~~");
                temp_enemy tempEnemy=this.getTempEnemy();
                castSkillResult.setTempEnemy(tempEnemy);
                castSkillResult.setFightIsContinue(true);

            } else {
                //更新玩家血量
                playerDao.updateRoleHitPoint(player_status_id,player_hit_point);
                //玩家被击杀，将玩家所在位置更新到上一个房间
                playerDao.updateNowInRoom(game_saved_id,last_room_id);
                //将玩家血量置为1
                playerDao.updateRoleHitPoint(player_status_id,1);
                castSkillResult.setSecond_effect("敌将凶猛，您被击杀了！！勇者还请更换装备重新来过！");
                temp_enemy tempEnemy=this.getTempEnemy();
                castSkillResult.setTempEnemy(tempEnemy);
                castSkillResult.setFightIsContinue(false);
            }

        }
        return castSkillResult;
    }



    //刘航编写

}
