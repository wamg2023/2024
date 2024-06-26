<template>
  <div class="contain3">
    <div class="title3">
        巨 洞 探 险
    </div>
    <div class="gameAbout">
      <el-button @click="startGame(1)">开始新游戏</el-button><br>
      <el-button @click="startGame(2)">继续游戏</el-button><br>
      <el-button @click="">设置</el-button><br>
      <el-button @click="gologin">退出</el-button>
    </div>
  </div>
</template>

<script>
import {newGame} from "@/utils/api/newgame";

export default {
  methods:{
    startGame(flag){
      this.$router.push({
        path: "/contain/loads",
        query:{
          player_id:this.$route.query.player_id,
          flag:flag
        }
      })
    },
    gologin(){
      this.$router.push({
        path: "/contain/login"
      })
    },

  },
  data(){
    return {
      id:this.$route.query.id
    }
  }
}
</script>

<style scoped>
.contain3{
  align-items: center;
  background: url("../img/cave.png");
  height: 90%;
  width: 80%;
  position: fixed;
  background-size: 100% 100%;
}
.title3{

  font-family: xs;
  font-size: 80px;
  margin-top:7%;
  margin-left:0%;
  width: 100%;
  color: aqua;
  opacity: 0.9;
}
.gameAbout{
  font-family: xs;
  font-size: 30px;
  margin-top:15%;
  margin-left:0%;
  width: 100%;
  color: white;
  opacity: 0.9;
}

.el-button {
  margin-top: 0.5%;
  height: 45px;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0);
  font-size: 26px;
  font-family: xs;
  color: rgba(255, 255, 255, 1);
  border: transparent;
  &:hover{
    background: black;
    color: red;
  }
  &:focus{
    background: white;
    color: black;
  }
}
</style>