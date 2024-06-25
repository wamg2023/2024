<template>

    <div class="contain2">
      <div class="head">
        <div class="screen" >
          <div class="playerStatus">
            <div style="width: 18%;float: left;margin-top:11px;font-size: 15px;">Hp:</div>
            <div style="width: 65%;float: left">
              <el-progress :text-inside="true"  :stroke-width="15"
                           v-if="!isNaN(parseInt(Math.round(this.currentplayer_status.hit_point*100/this.currentplayer_att.hit_point)))"
                           :percentage="Math.round(this.currentplayer_status.hit_point*100/this.currentplayer_att.hit_point)"
                           :format="format" status="exception">
              </el-progress>
            </div>
            <div style="width: 17%;float: left;font-size: 15px;margin-top: 10px;">{{ this.currentplayer_status.hit_point }}/{{this.currentplayer_att.hit_point}}</div>
            <div style="width: 18%;float: left;margin-top:11px;font-size: 15px;">Mp:</div>
            <div style="width: 65%;float: left">
              <el-progress :text-inside="true" color="aqua" :stroke-width="15"
                           v-if="!isNaN(parseInt(Math.round(this.currentplayer_status.magic_point*100/this.currentplayer_att.magic_point)))"
                           :percentage="Math.round(this.currentplayer_status.magic_point*100/this.currentplayer_att.magic_point)"
                           :format="format" status="exception"></el-progress>
            </div>
            <div style="width: 17%;float: left;font-size: 15px;margin-top: 10px;">{{this.currentplayer_status.magic_point}}/{{this.currentplayer_att.magic_point}}</div>
            <div style="float: left;width:60%;margin-top: 18px;">atk:{{this.currentplayer_att.attack}}</div>
            <div style="float: left;width:60%;margin-top: 18px;">def:{{this.currentplayer_att.defence}}</div>
            <div style="float: left;width:60%;margin-top: 18px;">spd:{{this.currentplayer_att.speed}}</div>
          </div>
          <div class="enemyStatus" :hidden="existEimg">
            <div>{{0}}</div>
            <div style="width: 18%;float: left;margin-top:11px;font-size: 15px;">Hp:</div>
            <div style="width: 65%;float: left">
              <el-progress :text-inside="true"  :stroke-width="15"
                           v-if="!isNaN(parseInt(Math.round(this.dyEnemy.hp*100/this.currentEnemy.hit_point)))"
                           :percentage="Math.round(this.dyEnemy.hp*100/this.currentEnemy.hit_point)<=0?0:
                           Math.round(this.dyEnemy.hp*100/this.currentEnemy.hit_point)"
                           :format="format" status="exception"></el-progress>
            </div>
            <div style="width: 17%;float: left;font-size: 15px;margin-top: 10px;">{{ this.dyEnemy.hp }}/{{ this.currentEnemy.hit_point }}</div>
            <div style="width: 18%;float: left;margin-top:11px;font-size: 15px;" v-if="false">Mp:</div>
            <div style="width: 65%;float: left" v-if="false">
              <el-progress :text-inside="true" color="aqua" :stroke-width="15"
                           v-if="!isNaN(parseInt(Math.round(this.currentEnemy.magic_point/this.currentEnemy.magic_point)))"
                           :percentage="Math.round(this.currentEnemy.magic_point/this.currentEnemy.magic_point)"
                           :format="format" status="exception"></el-progress>
            </div>
            <div style="width: 17%;float: left;font-size: 15px;margin-top: 10px;" v-if="false">{{ this.currentEnemy.magic_point }}/{{ this.currentEnemy.magic_point }}</div>
            <div style="float: left;margin-top: 18px;width: 100%">atk:{{this.currentEnemy.attack}}</div>
            <div style="float: left;margin-top: 18px;width: 100%">def:{{this.currentEnemy.defence}}</div>
            <div style="float: left;margin-top: 18px;width: 100%">spd:{{this.currentEnemy.speed}}</div>
          </div>
          <div class="pimgContain">
            <img :src="require('../img/' + pimageUrl)" class="pimg" :hidden="false"/>
          </div>
          <div class="eimgContain">
            <img :src="require('../img/' + eimageUrl)" class="eimg" :hidden="existEimg"/>
          </div>
        </div>
      </div>
      <div class="foot">
        <el-tabs type="card" class="tabcard" @tab-click="tabsClick" v-model="active">
          <el-tab-pane label="事件框" name="0" class="tablable">
            <div class="eventGame" id="msg"></div>
            <el-button  class="eventButton1" :style="{display:existButton1}" @click="eventResult1(btnflag)" >{{btntext1}}</el-button>
            <el-button  class="eventButton2" :style="{display:existButton2}" @click="eventResult2()">{{btntext2}}</el-button>
          </el-tab-pane>
          <el-tab-pane label="移动" name="1" class="tablable" :disabled="open1">
            <el-button class="actionButton" @click="goo(roomPos.east_room_id)" :disabled="eastBtn">向东前进</el-button>
            <el-button class="actionButton" @click="goo(roomPos.south_room_id)" :disabled="southBtn">向南前进</el-button>
            <el-button class="actionButton" @click="goo(roomPos.west_room_id)" :disabled="westBtn">向西前进</el-button>
            <el-button class="actionButton" @click="goo(roomPos.north_room_id)" :disabled="northBtn">向北前进</el-button>
          </el-tab-pane>
          <el-tab-pane label="物品" name="2" class="tablable"  :disabled="open2">
            <div style="float: left;width: 50%;">
              <el-table
                  :data="litemList"
                  border
                  empty-text="-"
                  :cell-style="{padding:'0px'}"
                  :row-style="{height:'20px'}"
                  :header-row-style="{height:'20px'}"
                  :header-cell-style="{background:'black', padding:'0px',color:'white', }"
                  >
                <el-table-column
                    prop="name"
                    label="名称"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="id"
                    v-if="false"
                    label="物品ID"
                    width="1">
                </el-table-column>
                <el-table-column
                    prop="weight"
                    label="单个重量"
                    width="85">
                </el-table-column>
                <el-table-column
                    prop="num"
                    label="数量"
                    width="50">
                </el-table-column>
                <el-table-column
                    prop="descri"
                    label="描述"
                    width="287">
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="86">
                  <template slot-scope="scope">
                    <el-button @click="useitem(scope.row)" type="text" size="small">使用</el-button>
                    <el-button @click="dropitem(scope.row)" type="text" size="small">丢弃</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div style="float: left;width: 70%;">
              <el-table
                  :data="ritemList"
                  border
                  empty-text="-"
                  :cell-style="{padding:'0px'}"
                  :row-style="{height:'20px'}"
                  :header-row-style="{height:'20px'}"
                  :header-cell-style="{background:'black', padding:'0px',color:'white', }"
              >
                <el-table-column
                    prop="name"
                    label="名称"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="id"
                    v-if="false"
                    label="物品ID"
                    width="1">
                </el-table-column>
                <el-table-column
                    prop="weight"
                    label="单个重量"
                    width="85">
                </el-table-column>
                <el-table-column
                    prop="num"
                    label="数量"
                    width="50">
                </el-table-column>
                <el-table-column
                    prop="descri"
                    label="描述"
                    width="285">
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="86">
                  <template slot-scope="scope">
                    <el-button @click="useitem(scope.row)" type="text" size="small">使用</el-button>
                    <el-button @click="dropitem(scope.row)" type="text" size="small">丢弃</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div class="block" style="width: 100%;margin-left:-120%;margin-top:10%;background-color: black">
              <el-pagination
                             background
                             @current-change="getList"
                             :current-page="queryParams.page"
                             :page-sizes="[1, 3, 5, 10]"
                             :page-size="queryParams.size"
                             layout=" prev, pager, next"
                             :total="total">
              </el-pagination>
            </div>
          </el-tab-pane>
          <el-tab-pane label="战斗技能" name="3" class="tablable" :disabled="open3">
            <el-button class="actionButton" @click="useSkill(1)">撞击<br><br><br>伤害2<br><br><br>mp消耗</el-button>
            <el-button class="actionButton" @click="useSkill(2)">拳击<br><br><br>伤害3<br><br><br>mp消耗</el-button>
            <el-button class="actionButton" @click="useSkill(3)">扫腿<br><br><br>伤害1<br><br><br>mp消耗</el-button>
            <el-button class="actionButton" @click="useSkill(4)">背摔<br><br><br>伤害4<br><br><br>hp消耗</el-button>
          </el-tab-pane>
          <el-tab-pane label="角色当前状态" name="6" class="tablable" >
            <div style="color: white;background-color: black;width: 100%;float: left">
              <div style="height: 15%;float: left;width: 80%;">&nbsp;武&nbsp; 器&nbsp:{{this.weapons.hand}}</div>
              <div style="width: 20%;float: right"><el-button class="weaponbtn" :style="{display:weapon1}" @click="removeEquip(1)">脱下</el-button></div>
              <div style="margin-top: 5px;height: 15%;float: left;width: 80%;">头部防具:{{this.weapons.head}}</div>
              <div style="width: 20%;float: right;"><el-button class="weaponbtn" :style="{display:weapon2}" @click="removeEquip(2)">脱下</el-button></div>
              <div style="margin-top: 5px;height: 15%;float: left;width: 80%;">身体防具:{{this.weapons.body}}</div>
              <div style="width: 20%;float: right"><el-button class="weaponbtn" :style="{display:weapon3}" @click="removeEquip(3)">脱下</el-button></div>
              <div style="margin-top: 5px;height: 15%;float: left;width: 80%;">腿部防具:{{this.weapons.leg}}</div>
              <div style="width: 20%;float: right"><el-button class="weaponbtn" :style="{display:weapon4}" @click="removeEquip(4)">脱下</el-button></div>
              <div style="margin-top: 10px;height: 15%;float: left;width: 80%">当前负重:{{0}}/{{this.currentplayer_att.max_weight}}</div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="检查本房间" name="5"  class="tablable"  :disabled="open5">
            <div style="float: left;width: 50%;">
              <el-table
                  :data="boxitemList"
                  border
                  empty-text="该房间无物品"
                  :cell-style="{padding:'0px'}"
                  :row-style="{height:'20px'}"
                  :header-row-style="{height:'20px'}"
                  :header-cell-style="{background:'black', padding:'0px',color:'white', }"
              >
                <el-table-column
                    prop="name"
                    label="名称"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="id"
                    v-if="false"
                    label="物品ID"
                    width="1">
                </el-table-column>
                <el-table-column
                    prop="weight"
                    label="单个重量"
                    width="85">
                </el-table-column>
                <el-table-column
                    prop="num"
                    label="数量"
                    width="50">
                </el-table-column>
                <el-table-column
                    prop="descri"
                    label="描述"
                    width="318">
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="56">
                  <template slot-scope="scope">
                    <el-button @click="handitem(scope.row)" type="text" size="small">捡起</el-button>

                  </template>
                </el-table-column>
              </el-table>
              <div class="block" style="position:absolute;left:-28%; top:5%;width: 100%;margin-left:0%;margin-top:10%;background-color: black">
                <el-pagination
                    background
                    @current-change="getCurrentBox"
                    :current-page="boxPage"
                    :page-size="3"
                    layout=" prev, pager, next"
                    :total="currenRoom.box.length">
                </el-pagination>
              </div>
            </div>
            <div style="float: right;width: 50%;color: white" id="roommsg">这里是右半部分</div>
          </el-tab-pane>
          <el-tab-pane label="游戏选项" name="4" class="tablable"  :disabled="open4">
            <el-button class="actionButton" @click="proBack">返回上个房间</el-button>
            <el-button class="actionButton" @click="saveVisble=true">&nbsp;&nbsp;&nbsp;存&nbsp;档&nbsp;&nbsp;&nbsp;</el-button>
            <el-button class="actionButton" @click="exit">退出当前游戏</el-button>
          </el-tab-pane>
        </el-tabs>
      </div>
      <el-dialog
          title="请选择存档位置"
          :visible.sync="saveVisble"
          width="30%"
          append-to-body
          center>
        <el-button @click="saveCurrent(1)">存档一</el-button>
        <el-button @click="saveCurrent(2)">存档二</el-button>
        <el-button @click="saveCurrent(3)">存档三</el-button>
        <el-button @click="saveVisble=false" style="width: 16%;margin-left: 70px;font-size: 13px;">取消</el-button>
      </el-dialog>
    </div>

</template>

<script>
import {getBag,pull,push,eventPull,useItem,remove} from "@/utils/api/item";
import {getRoom,getEvent} from "@/utils/api/room";
import {getRole} from "@/utils/api/role";
import {goBack,move,startFight,useSkill} from "@/utils/api/game";
export default {
  async mounted() {
    await this.gameInit()
    var y=document.getElementById("msg");
    y.innerHTML="<div style='margin-top: 15px;float: left'>这是史上最凶最恶的洞窟，开始你的大冒险吧！</div>"
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    async tabsClick(tab, event) {
      if(tab.name==2){
        await this.initBag()
        await this.getInventory()
        await this.getList(1)
        //await new Promise(resolve => setTimeout(resolve, 5000));
      }
      else if(tab.name==5){
        await this.getNow()
        await this.getStoreBox()
        await this.getCurrentBox(1)
      }
      else if(tab.name==6){

      }

    },
    format(percentage) {
      return percentage === 100 ? 'Max' : `${percentage}%`;
    },

    async gameInit(){
      await this.getPlayer()
      await this.initBag()
      await this.getNow()
    },
    async getPlayer(){
      const res=await getRole(this.$route.query.player_id,this.$route.query.save)
      this.currentplayer_status=res.data.data.player_status
      this.currentplayer_att=res.data.data.playerAttribute
      this.currentplayer_save=res.data.data.gameSaved
    },
    async initBag(){
      const res=await getBag()
      this.currentplayer_bag=res.data.data.itemAttributes
      const itemnum=res.data.data.inventory
      for(let key in this.currentplayer_bag){
        this.currentplayer_bag[key].num=itemnum[key].item_num
      }
    },
    async getNow(){
      const res=await getRoom(this.$route.query.player_id,this.$route.query.save)
      this.roomPos=res.data.data.savedGraph
      this.roomItem=res.data.data.room_item_attribute
      const itemNum=res.data.data.allitemInfo
      for(let key in this.roomItem){
        this.roomItem[key].num=itemNum[key].item_num
      }
      this.initPosBtn()
      console.log("此时的房间为"+this.roomPos.room_id)
    },
    async getInventory(){
      console.log("更新了一次背包")
      this.initList=[]
      this.litemList=[]
      this.ritemList=[]

      this.total=this.currentplayer_bag.length
      for(let key in this.currentplayer_bag){
        const box={
          name:this.currentplayer_bag[key].name,
          id:this.currentplayer_bag[key].item_attribute_id,
          weight:this.currentplayer_bag[key].weight,
          num:this.currentplayer_bag[key].num,
          descri:this.currentplayer_bag[key].description
        }
        this.initList.push(box)
        if(key%2==0)
          this.litemList.push(box)
        else
          this.ritemList.push(box)
      }

      this.getList(1)
    },
    async getStoreBox(){
      console.log("更新了一次房间物品")
      this.boxitemList=[]
      this.stitList=[]
      this.boxTotal=this.roomItem.length
      for(let key in this.roomItem){
        const box={
          name:this.roomItem[key].name,
          id:this.roomItem[key].item_attribute_id,
          weight:this.roomItem[key].weight,
          num:this.roomItem[key].num,
          descri:this.roomItem[key].description
        }
        this.boxitemList.push(box)
        this.stitList.push(box)
      }
      this.getCurrentBox(1)
    },
    async removeEquip(type){
      for(let key in this.usequip){
        if(this.usequip[key].type=='武器'&&type==1){
          await remove(this.$route.query.id,this.usequip[key].id)
          break;
        }
        else if(this.usequip[key].type=='头部防具'&&type==2){
          await remove(this.$route.query.id,this.usequip[key].id)

          break;
        }
        else if(this.usequip[key].type=='身体防具'&&type==3){
          await remove(this.$route.query.id,this.usequip[key].id)
          break;
        }
        else if(this.usequip[key].type=='腿部防具'&&type==4){
          await remove(this.$route.query.id,this.usequip[key].id)
          break;
        }
      }
    },
    async useitem(row){
      await useItem(row.id);
      await this.initBag()
      await this.getInventory()
      await this.getPlayer()
      this.getList(1)
    },
    async dropitem(row){
      await push(row.id)
      await this.initBag()
      await this.getInventory()
      this.getList(1)
    },
    async handitem(row){
      await pull(row.id)
      await this.getNow()
      await this.getStoreBox()
      this.getCurrentBox(1)
    },
    async goo(pos){
      var y=document.getElementById("msg");
      y.innerHTML="<div style='margin-top: 15px;width: 320px;height: 150px;background-color: black'>" +
          "<div style='background-color: black;position: absolute;top:0px;left:0px;width: 342px;height: 165px;'>" +
          "<img src=\""+this.imgLoading.url+"\" style='width: 100%;height: 100%;margin-top: -10px;margin-left: 0px;'/>" +
          "</div>"+
          "</div>"
      this.active='0'
      await move(pos)
      await this.getNow()
      await this.eventBegin()

    },
    async eventExist(){
      this.currentEvent=await eventnormal(this.currenRoom.id)
      console.log(this.currentEvent)
      console.log(this.currentEvent.data.code)
      if(this.currentEvent.data.code==200&&this.currentEvent.data.data.event_type=="概率事件"){
        if(this.currentEvent.data.data.eventText.event_options.length==2){
          this.existButton1=''
          this.existButton2=''
          this.btntext1=this.currentEvent.data.data.eventText.event_options[0].option_description
          this.btntext2=this.currentEvent.data.data.eventText.event_options[1].option_description
        }
        else{
          this.existButton1=''
          this.btntext1=this.currentEvent.data.data.eventText.event_options[0].option_description
        }
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>"+this.currentEvent.data.data.eventText.event_description+"</div>"
      }
      else if(this.currentEvent.data.code==200&&this.currentEvent.data.data.event_type=="战斗事件"){
        this.existButton1=''
        this.existButton2=''
        this.btntext1=this.currentEvent.data.data.eventText.event_options[1].option_description
        this.btntext2=this.currentEvent.data.data.eventText.event_options[0].option_description
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>"+this.currentEvent.data.data.eventText.event_description+"</div>"
      }
      else{
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>"+"这个房间你之前似乎来过"+"</div>"
      }
    },
    async eventBegin(){
      const res=await getEvent()
      console.log(res)
      if(res.data.data.roomEvent.type=="野怪出没"){
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>"+"面前出现了一只"+res.data.data.roomEvent.description+"</div>"
        console.log("当前强敌是"+res.data.data.roomEvent.description)
        this.currentEnemy=res.data.data.enemy
        this.dyEnemy.hp=this.currentEnemy.hit_point
        this.existEimg=false
        this.existButton1=''
        this.existButton2=''
        this.btntext1='战斗'
        this.btntext2='离开'
        this.btnflag=1
      }
      else{
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>当前房间事件尚未进行处理</div>"
        console.log("下次一定")
      }
    },
    async eventBattle(){
      await startFight()
      this.existButton1=''
      this.btntext1='释放技能'
      this.btnflag=2
      const flag=1
    },
    async eventResult1(flag){
      if(flag==0){
        this.existButton1='none'
        this.existButton2='none'
      }
      else if(flag==1){
        this.existButton1='none'
        this.existButton2='none'
        this.open3=false
        this.open1=true
        this.open5=true
        console.log("现在你进入了战斗")
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>"+"这将是一场恶战！！！"+"</div>"
        this.btnflag=2
        await this.eventBattle()
      }
      else if(flag==2){
        this.existButton1='none'
        this.existButton2='none'
        this.active='3'
        this.btnflag=-1
      }
      else{
        console.log("事件结束")
        this.btnflag=0
        this.existButton1='none'
        this.existButton2='none'
        var y=document.getElementById("msg");
        y.innerHTML="<div  style='margin-top: 15px;float: left'>"+"整顿装备或是前往下一个未知的方向..."+"</div>"
      }
    },
    async eventResult2(){
      this.existEimg=true
      this.open3=true
      this.open1=false
      this.open5=false
      this.existButton1='none'
      this.existButton2='none'
      var y=document.getElementById("msg");
      y.innerHTML="<div  style='margin-top: 15px;float: left'>"+"此洞穴甚是危险,还是小心为妙。"+"</div>"+
          "<div style='margin-top: 15px;float: left'>你回到了上一个房间</div>"
      await goBack()
      await this.getNow()

    },
    async useSkill(id){
      const res=await useSkill(id)
      await this.getPlayer()
      this.active='0'
      this.dyEnemy.hp=res.data.data.tempEnemy.hit_point
      var y=document.getElementById("msg");
      y.innerHTML="<div  style='margin-top: 15px;float: left'>"+res.data.data.first_effect+res.data.data.second_effect+"</div>"
      if(res.data.data.fightIsContinue==true){
        this.existButton1=''
        this.btntext1='继续追击'
        this.existButton2=''
        this.btntext2='逃跑'
        this.btnflag=2
      }
      else{
        this.existEimg=true
        this.open3=true
        this.open1=false
        this.open5=false
      }
    },
    initPosBtn(){
      if(this.roomPos.south_room_id==-1)
        this.southBtn=true;
      else
        this.southBtn=false;
      if(this.roomPos.north_room_id==-1)
        this.northBtn=true;
      else
        this.northBtn=false;
      if(this.roomPos.east_room_id==-1)
        this.eastBtn=true;
      else
        this.eastBtn=false;
      if(this.roomPos.west_room_id==-1)
        this.westBtn=true;
      else
        this.westBtn=false;
    },
    async proBack(){
      const res=await goBack()
      console.log(res.data.msg)
      await this.getNow()
    },
    async saveCurrent(save_id){
      this.$message.success("保存成功")
    },
    getList(page) {
      this.litemList = [];
      this.ritemList = [];
      this.queryParams.page = Number(page)
      this.litemList.length = 0
      this.ritemList.length = 0
      let start = (this.queryParams.page - 1) * (this.queryParams.size)
      let end = this.total < (this.queryParams.page) * this.queryParams.size ?
          this.total : (this.queryParams.page) * this.queryParams.size

      for (let i = start; i < end; i++) {
        if(i%2==0)
        this.litemList.push(this.initList[i])
        else
        this.ritemList.push(this.initList[i])
      }
    },
    getCurrentBox(page){
      this.boxitemList = [];
      this.boxitemList.length = 0
      this.boxPage = Number(page)
      let start = (this.boxPage - 1) * 3
      let end = this.roomItem.length < (this.boxPage) * 3 ?
          this.roomItem.length : (this.boxPage) * 3

      for (let i = start; i < end; i++) {
          this.boxitemList.push(this.stitList[i])
      }
    },
    getEquip(){
      this.weapons.head='(暂无)'
      this.weapons.hand='(暂无)'
      this.weapons.body='(暂无)'
      this.weapons.leg='(暂无)'
      this.weapon1='none'
      this.weapon2='none'
      this.weapon3='none'
      this.weapon4='none'
      const weapon={
        id:1,
        type:'1',
        name:''
      }
      for(let key in this.currentequip){
        if(this.currentequip[key].weapon_type=='武器'){
          weapon.id=this.currentequip[key].weapon_id
          weapon.type='武器'
          weapon.name=this.currentequip[key].weapon_name
          this.weapons.hand=this.currentequip[key].weapon_name
          this.usequip.push(weapon)
          this.weapon1=''
        }
        else if(this.currentequip[key].weapon_type=='头部防具'){
          weapon.id=this.currentequip[key].weapon_id
          weapon.type='头部防具'
          weapon.name=this.currentequip[key].weapon_name
          this.weapons.head=this.currentequip[key].weapon_name
          this.usequip.push(weapon)
          this.weapon2=''
        }
        else if(this.currentequip[key].weapon_type=='身体防具'){
          weapon.id=this.currentequip[key].weapon_id
          weapon.type='身体防具'
          weapon.name=this.currentequip[key].weapon_name
          this.weapons.body=this.currentequip[key].weapon_name
          this.usequip.push(weapon)
          this.weapon3=''
        }
        else if(this.currentequip[key].weapon_type=='腿部防具'){
          weapon.id=this.currentequip[key].weapon_id
          weapon.type='腿部防具'
          weapon.name=this.currentequip[key].weapon_name
          this.weapons.leg=this.currentequip[key].weapon_name
          this.usequip.push(weapon)
          this.weapon4=''
        }
      }
    },
    exit(){
      this.$router.push({
        path: "/contain/game",
        query:{
          player_id:this.$route.query.player_id
        }
      })
    },

  },
  data() {
    return {
      description:'',
      active: '0',
      imgLoading:{url:require('../img/loading2.gif')},
      eimageUrl:'ghost.png',
      pimageUrl:'player.png',
      existEimg:true,
      existPimg:false,
      weapon1:'none',
      weapon2:'none',
      weapon3:'none',
      weapon4:'none',
      open1:false,
      open2:false,
      open3:true,
      open4:false,
      open5:false,
      existButton1:'none',
      existButton2:'none',
      btntext1:'文本1',
      btntext2:'文本2',
      btnflag:0,
      northBtn:false,
      southBtn:false,
      westBtn:false,
      eastBtn:false,
      saveVisble:false,

      queryParams: {
        page: 1,
        size: 6,
        name:'',
      },
      boxPage:1,

      litemList:[],
      ritemList:[],
      boxitemList:[],
      total: 0,
      boxTotal:0,
      initList:[],
      stitList:[],

      currentplayer_save:{},
      currentplayer_att:{},
      currentplayer_status:{},
      currentplayer_bag:{},

      roomItem:[],
      roomPos:[],
      playerItem:[],

      usequip:[],
      weapons: {
        head:'-',
        hand:'-',
        body:'-',
        leg:'-'
      },
      currenRoom:{
        id:-99,
        eid:-99,
        flag:-1,
        north:-99,
        south:-99,
        east:-99,
        west:-99,
        box:[],
      },
      currentEvent:[],
      currentEnemy:{},
      dyEnemy:{
        hp:'0'
      },
      eventRoom:{},
      roomList:[],
      eventList:[],
      enemyList:[],
    }
  }
}
</script>

<style scoped>
.actionButton{
  color: white;
  background-color: black;
  font-family: xs;
}
.contain2{
  height: 100%;
  width: 100%;

  text-align: center;
  background-color: black;
  display: flex;
  border-style: solid;
  border-width: 2px;
  border-color: aqua;
}
.head{
  line-height: 80%;
}
.foot{
  position: absolute;
  line-height: 20%;
  top: 65%;
  left: 10%;


}

.tabcard{
  height: 100px;
  width: 1221px;
  font-family: xs;
  background-color: black;
  border-style: solid;
  border-width: 1px;
  border-color: white;

}
.tablable{
  font-family: xs;
  line-height: 100%;
  text-align: center;
  display: flex;
  background-color: white;
}
::v-deep .el-tabs__item {
  color:white;
  opacity: 1;
}
::v-deep .el-tabs__item.is-active {
  color: red;
  opacity: 1;
}
::v-deep .el-tabs__item:hover {
  color: red;
  cursor: pointer;
}
::v-deep .el-tab-pane {
  background-color: black;
  padding-left: 2px;
  width: 100%;
  height: 160px;
}
::v-deep .el-table td{
  background-color: black;
  color: white;
}
::v-deep .el-table th{
  background-color: black;
  color: black;
}
::v-deep .el-table header{
  background-color: black;
  color: black;
}
::v-deep .el-table tbody tr:hover>td {
  background-color:red!important
}
.screen{
  margin-top: 10px;
  font-family: xs;
  font-size: 30px;
  color: white;
}
.eventGame{
  margin-top: -8px;
  font-family: xs;
  font-size: 30px;
  color: white;
  float: left;
}
.eventMessage{
  color: red;
  background-color: aqua;
}
::v-deep .el-pagination.is-background .el-pager li:not(.disabled) {
  background-color: black;
  color: #fff;
  border-style: solid;
  border-width: 1px;
  border-color: white;
}
::v-deep .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: red;
    color: #fff;

}
::v-deep .el-pagination.is-background .btn-next, ::v-deep .el-pagination.is-background .btn-prev{
  background-color: black;
  color: red;
  border-style: solid;
  border-width: 1px;
  border-color: white;
}
::v-deep .el-pagination.is-background .btn-next:disabled, ::v-deep .el-pagination.is-background .btn-prev:disabled{
  color: gray;
}
.eventButton1{
  position: absolute;
  height: 80px;
  width: 110px;
  top: 35%;
  left: 2%;
  color: white;
  background-color: black;
}
.eventButton2{
  position: absolute;
  height: 80px;
  width: 110px;
  top: 35%;
  left: 12%;
  color: white;
  background-color: black;
}
.eimgContain{
  position: absolute;
  top: 25%;
  left: 60%;
  height: 200px;
  width: 200px;
}
.eimg{
  height: 100%;
  width: 100%;
  object-fit: cover;
}
.pimgContain{
  position: absolute;
  top: 13%;
  left: 11%;
  height: 400px;
  width: 250px;
  background-color: black;
}
.pimg{
  height: 100%;
  width: 100%;
  object-fit: cover;
}
.el-button.is-disabled {
  background-color: #909399 !important; /* 设置禁用状态的背景颜色 */
  border-color: #909399 !important; /* 设置禁用状态的边框颜色 */
  color: #fff !important; /* 设置禁用状态的文本颜色 */
}
::v-deep .el-button{
  background-color: black;
  color: white;
  font-family: xs;
  span {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
  }

}
.el-button:hover {
  background-color:#42b983 !important;
  border-color: #909399 !important;
  color: red !important;
}
.el-button:focus{
  background-color:black !important;
  border-color: #909399 !important;
  color: red !important;
}
.playerStatus{
  position: absolute;
  top: 42%;
  left: 28%;
  width: 200px;
  height: 200px;
  background-color: black;
  border-style: solid;
  border-width: 1px;
  border-color: black;
}
.enemyStatus{
  position: absolute;
  top: 42%;
  left: 75%;
  width: 200px;
  height: 200px;
}
.progress-percentage {
  position: absolute; /* 设置绝对定位 */
  top: 40%; /* 将进度条百分比显示置于进度条中间 */
  left: 50%; /* 将进度条百分比显示置于进度条中间 */

}
::v-deep .el-progress-bar__innerText {
  position: absolute; /* 设置绝对定位 */
  top: 20%; /* 根据需求设置进度条内容的位置 */
  right: 0%;
}
::v-deep.el-progress {
  text-align: center !important;
  width: 100% !important
}

::v-deep .el-table__empty-block {
  background-repeat: no-repeat;
  background-position: center;
  background-size: 50px;
  height: 100px;
  margin-top: -11px;
  margin-bottom: -17px;
  background-color: black;
  span {
    position: relative;
    top: 50px;
    height: 10px;
    background-color: white;
  }
}
.weaponbtn{
  position: relative;
  width: 36px;
  height:10px;
  top:0px;
  left: -400px;
  margin-top: 0px;
  margin-left: 0px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>