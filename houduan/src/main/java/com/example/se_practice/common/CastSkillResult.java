package com.example.se_practice.common;
/*
* 这是一个特殊的数据返回类型
* self_effect,标示己方施法效果
* enemy_effect,标示地方施法效果
* */

import com.example.se_practice.pojo.temp_enemy;

public class CastSkillResult {
    private String first_effect;
    private String second_effect;
    private temp_enemy tempEnemy;
    private boolean FightIsContinue;

    public temp_enemy getTempEnemy() {
        return tempEnemy;
    }

    public void setTempEnemy(temp_enemy tempEnemy) {
        this.tempEnemy = tempEnemy;
    }

    public boolean isFightIsContinue() {
        return FightIsContinue;
    }

    public void setFightIsContinue(boolean fightIsContinue) {
        FightIsContinue = fightIsContinue;
    }

    public String getFirst_effect() {
        return first_effect;
    }

    public void setFirst_effect(String first_effect) {
        this.first_effect = first_effect;
    }

    public String getSecond_effect() {
        return second_effect;
    }

    public void setSecond_effect(String second_effect) {
        this.second_effect = second_effect;
    }
}
