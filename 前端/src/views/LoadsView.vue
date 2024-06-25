<template>
<div style="width: 100%;height: 100%;background-color: black;">
  <span style="font-family: xs;font-size: 50px;color: aqua;opacity: 0.9">选择一个存档开始</span>
  <div style="margin-top: 20px">
    <el-button class="bone" @click="load(1)" :disabled="save1">
      {{btnText1}}<div id="saveDescription1"></div>
    </el-button>
    <el-button class="btwo" @click="load(2)" :disabled="save2">
      {{btnText2}}<div id="saveDescription2"></div>
    </el-button>
    <el-button class="bthree" @click="load(3)" :disabled="save3">
      {{btnText3}}<div id="saveDescription3"></div>
    </el-button>
  </div>
  <div style="position: absolute;top:5%;left:13%;"><el-button @click="goback" style="width: 100px;height: 50px;margin-top: 15px;margin-right: 30px;">返回</el-button></div>

</div>
</template>

<script>
import {getRole} from "@/utils/api/role";
import {newGame} from "@/utils/api/newgame"

export default {
  async mounted() {
    await this.getAllData()
  },
  methods:{
    goback(){
      this.$router.push({
        path: "/contain/game",
        query:{
          player_id:this.$route.query.player_id
        }
      })
    },
    async getAllData(){
      const res1=await getRole(this.$route.query.player_id,1)
      const res2=await getRole(this.$route.query.player_id,2)
      const res3=await getRole(this.$route.query.player_id,3)
      if(res1.data.code==200){
        this.btnText1='游戏状态'
        this.save1=false
        var y=document.getElementById("saveDescription1");
        y.innerHTML=
            "<div>hp:"+res1.data.data.player_status.hit_point+"/"+res1.data.data.playerAttribute.hit_point+"</div>"+
            "<div>mp:"+res1.data.data.player_status.magic_point+"/"+res1.data.data.playerAttribute.magic_point+"</div>"+
            "<div>atk:"+res1.data.data.playerAttribute.attack+"</div>"+
            "<div>def:"+res1.data.data.playerAttribute.defence+"</div>"+
            "<div>spd:"+res1.data.data.playerAttribute.speed+"</div>"

      }
      if(res2.data.code==200){
        this.btnText2='游戏状态'
        this.save2=false
        var y=document.getElementById("saveDescription2");
        y.innerHTML=
            "<div>hp:"+res2.data.data.player_status.hit_point+"/"+res2.data.data.playerAttribute.hit_point+"</div>"+
            "<div>mp:"+res2.data.data.player_status.magic_point+"/"+res2.data.data.playerAttribute.magic_point+"</div>"+
            "<div>atk:"+res2.data.data.playerAttribute.attack+"</div>"+
            "<div>def:"+res2.data.data.playerAttribute.defence+"</div>"+
            "<div>spd:"+res2.data.data.playerAttribute.speed+"</div>"
      }
      if(res3.data.code==200){
        this.btnText3='游戏状态'
        this.save3=false
        var y=document.getElementById("saveDescription3");
        y.innerHTML=
            "<div>hp:"+res3.data.data.player_status.hit_point+"/"+res3.data.data.playerAttribute.hit_point+"</div>"+
            "<div>mp:"+res3.data.data.player_status.magic_point+"/"+res3.data.data.playerAttribute.magic_point+"</div>"+
            "<div>atk:"+res3.data.data.playerAttribute.attack+"</div>"+
            "<div>def:"+res3.data.data.playerAttribute.defence+"</div>"+
            "<div>spd:"+res3.data.data.playerAttribute.speed+"</div>"
      }
      if(this.$route.query.flag==1){
        this.save1=false
        this.save2=false
        this.save3=false
      }
    },
    async load(save_id){
      if(this.$route.query.flag==1){
        await newGame(this.$route.query.player_id,save_id)
      }
      await getRole(this.$route.query.player_id,save_id)
      await this.$router.push({
        path: "/contain/start",
        query:{
          player_id:this.$route.query.player_id,
          save:save_id
        }
      })
    },
  },
  data(){
    return{
      btnText1:'此处暂无存档',
      btnText2:'此处暂无存档',
      btnText3:'此处暂无存档',
      save1:true,
      save2:true,
      save3:true
    }
  }
}
</script>

<style scoped>
.el-button.is-disabled {
  background-color: #909399 !important; /* 设置禁用状态的背景颜色 */
  border-color: #909399 !important; /* 设置禁用状态的边框颜色 */
  color: #fff !important; /* 设置禁用状态的文本颜色 */
}
::v-deep.el-button {
  opacity: 0.9;
  margin-top: 0.5%;
  width: 30%;
  height: 500px;
  border-radius: 20px;
  background-color: rgba(0, 0, 0, 0);
  font-size: 26px;
  font-family: xs;
  color: rgba(255, 255, 255, 1);
  //border: transparent;
  border-style: solid;
  border-width: 2px;
  border-color: aqua;
  &:hover{
    background: black;
    color: red;
  }
  &:focus{
    background: black;
    color: red;
  }
  ::v-deep.set-other-btn.is-disabled{
    background-color: #FE83C6;
    border-color: #FE83C6;
  }

}
</style>