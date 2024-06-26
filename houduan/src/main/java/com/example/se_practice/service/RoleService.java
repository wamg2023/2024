package com.example.se_practice.service;

import com.example.se_practice.common.GetPlayerResult;
import com.example.se_practice.dao.PlayerDao;
import com.example.se_practice.dao.RoleDao;
import com.example.se_practice.dao.TempDao;
import com.example.se_practice.exception.CustomException;
import com.example.se_practice.pojo.game_saved;
import com.example.se_practice.pojo.player_attribute;
import com.example.se_practice.pojo.player_status;
import com.example.se_practice.pojo.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private TempDao tempDao;
    @Resource
    private PlayerDao playerDao;
    private game_saved game_saved1;

    //生成新的玩家ID
    public  int getNewPlayerID() {
        int newplayerID=roleDao.getNum()+1;
        return newplayerID;
    }

    //获取存档ID
    public int getSaveID(int player_id, int save_id){
        int saveID=roleDao.getSaveID(player_id,save_id);
        return saveID;
    }

    //根据存档ID，获取存档信息索引
    public game_saved getGame_saved(int SaveID){
        game_saved temp= playerDao.getGame_saved(SaveID);
        return temp;
    }

    //登录
    public int login(String account, String password){
        //1.进行一些非空判断。
        //2.从数据库查询该用户信息。
        if(account==null||account.equals(" ")){
            throw new CustomException("用户名不能为空");
        }
        if(password==null||password.equals(" ")){
            throw new CustomException("密码不能为空");
        }
        int player_id=roleDao.findID(account, password);
        if(player_id==0){
            throw new CustomException("用户名或密码输入错误");
        }
        tempDao.updatePlayerID(player_id);
        return player_id;
    }

    //注册
    public void register(String account, String password) {
        //1.用户名及密码必须存在
        //2.用户名不得与先前已经存在的用户名相同
        if(account==null||account.equals(" ")){
            throw new CustomException("用户名不能为空");
        }
        if(password==null||password.equals(" ")){
            throw new CustomException("密码不能为空");
        }
        user User;
        User=roleDao.findUserByName(account);
        int player_id=this.getNewPlayerID();
        if(User!=null){
            //重复
            throw new CustomException("该用户已存在，请更换用户名");
        }
        roleDao.addUser(account,password,player_id);
    }
    //展示用户界面，即选择存档
    public GetPlayerResult getPlayer(int player_id, int save_id) {
        //更新缓存表，便于后续操作
        tempDao.updateSelection(save_id);
        //先获取存档ID
        int saveID=this.getSaveID(player_id,save_id);
        System.out.println(saveID);
        //更新缓存表中的存档ID，便于后续操作
        tempDao.updateGameSavedID(saveID);
        //根据存储ID获取信息索引
        game_saved temp=this.getGame_saved(saveID);
        //获取玩家属性存储ID
        int player_attribute_id=temp.getPlayer_attribute_id();
        System.out.println(player_attribute_id);
        //获取玩家状态存储ID
        int player_status_id=temp.getPlayer_status_id();
        System.out.println(player_status_id);
       /* //获取物品栏存储ID
        int inventory_id=temp.getInventory_id();
        //获取地图存储ID
        int in_room_id=temp.getIn_room_id();*/
        //获取玩家属性
        player_attribute player_attribute1=playerDao.getPlayer_attribute(player_attribute_id);
        //获取玩家状态
        player_status player_status1=playerDao.getPlayer_status(player_status_id);
        //封装返回数据
        GetPlayerResult playerResult=new GetPlayerResult(temp, player_attribute1,player_status1);
        return playerResult;
    }
}
