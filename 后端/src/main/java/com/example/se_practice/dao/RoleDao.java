package com.example.se_practice.dao;
/*
* 玩家id，与role_id
*
 */
import com.example.se_practice.pojo.role;
import com.example.se_practice.pojo.role_initial_attribute;
import com.example.se_practice.pojo.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select player_id from user where account=#{account} and password=#{password} ")
    int findID(@Param("account") String account,@Param("password") String password);
    @Select("select count(*) from user")
    int getNum();
    @Select("select player_id from user where account=#{account}  ")
    user findUserByName(@Param("account")String account);
    @Insert("insert into user(account,password,player_id) VALUES (#{account},#{password},#{player_id})")
    void addUser(@Param("account")String account, @Param("password")String password, @Param("player_id")int player_id);
    @Select("select game_saved_id from player__saved where player_id=#{player_id} and selection=#{selection} ")
    int getSaveID(@Param("player_id")int player_id,@Param("selection")int selection);
    @Select("select hit_point from role_initial_attribute where role_initial_attribute_id=#{role_initial_attribute_id}")
    int getEnemyNowBlood(@Param("role_initial_attribute_id")int role_initial_attribute_id);
    @Select("select attack from role_initial_attribute where role_initial_attribute_id=#{role_initial_attribute_id}")
    int getRoleAttack(@Param("role_initial_attribute_id")int role_initial_attribute_id);
    @Select("select defence from role_initial_attribute where role_initial_attribute_id=#{role_initial_attribute_id}")
    int getRoleDefence(@Param("role_initial_attribute_id")int role_initial_attribute_id);
    @Select("select speed from role_initial_attribute where role_initial_attribute_id=#{role_initial_attribute_id}")
    int getRoleSpeed(@Param("role_initial_attribute_id")int role_initial_attribute_id);
    @Select("select * from role_initial_attribute where role_initial_attribute_id=#{role_initial_attribute_id}")
    role_initial_attribute getRoleInitialAttribute(@Param("role_initial_attribute_id")int role_initial_attribute_id);
}