package com.example.se_practice.dao;
/*
*
*
 */
import com.example.se_practice.pojo.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillDao {
    @Select("select enemy_effect_id from skill where skill_id=#{skill_id}")
    int getEnemyEffectID(@Param("skill_id") int skill_id);
    @Select("select power from enemy_effect where enemy_effect_id=#{enemy_effect_id}")
    int getSkillPower(@Param("enemy_effect_id")int enemy_effect_id);
    @Select("select name from skill where skill_id=#{skill_id}")
    String getSkillName(@Param("skill_id")int skill_id);
}