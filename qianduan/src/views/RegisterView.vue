<template>
  <div class="loginContain">
    <el-container>
      <el-header><span style="color: white;opacity: 0.9">注 册</span></el-header>
      <el-main>
        <div class="mdiv">
          <span style="font-size: 28px;color: white;opacity:0.9">账 号</span>
          <el-input placeholder="请输入账号" prefix-icon="el-icon-user-solid" v-model="username"></el-input>
        </div>
        <div class="mdiv">
          <span style="font-size: 28px;color: white;opacity:0.9">密 码</span>
          <el-input show-password placeholder="请输入密码" prefix-icon="el-icon-lock" v-model="password"></el-input>
        </div>
        <div class="mdiv">
          <span style="font-size: 28px;color: white;opacity:0.9">游戏角色名</span>
          <el-input placeholder="请输入游戏角色名" prefix-icon="el-icon-star-on" v-model="player"></el-input>
          <div style="margin-top: 20px">
            <el-button style="margin-left: -0px" @click="query">确认</el-button>
            <el-button style="margin-left: 60px" @click="space">取消</el-button>
          </div>
        </div>
      </el-main>
      <el-footer>
        <el-button @click="goRegister">返回登录</el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script >
import {register} from "@/utils/api/role";

export default {

  methods:{
    goRegister(){
      this.$router.push({
        path: "/contain/login",

      })
    },
    space(){
      this.password=''
      this.username=''
    },
    async query(){
      const res=await register(this.username,this.password)
      if(res.data.code==200){
        console.log("注册成功")
        this.$message.success("注册成功，三秒后跳转至登录页面")
        await new Promise(resolve => setTimeout(resolve, 3000));
        await this.$router.push("/contain/login")
      }
      else{
        console.log("注册失败")
        this.$message.error("用户名重复！")
      }
    }
  },
  data(){
    return{
      username:'',
      password:'',
      player:''
    }
  }
}
</script>

<style scoped>
.loginContain{
  height: 100%;
  width: 100%;
  background-color: black;
}

.mdiv{
  padding-top: 50px;
  height: 20%;
  width: 40%;
  padding-left: 30%;
}
.el-header{
  font-family: xs;
  font-size: 50px;
  background-color: black;
  color: #333;
  width: 100%;
  text-align: center;
  border: 1px solid #1296db;
}

.el-footer {
  font-family: xs;
  font-size: 50px;
  background-color: black;
  color: #333;
  width: 100%;
  text-align: center;
  border: 1px solid #1296db;
  margin-top: 50px;
}

.el-main {
  font-family: xs;
  background-color: black;
  color: #333;
  height: 50%;
  text-align: center;
  //line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

/deep/input::-webkit-input-placeholder {
  color: #17a1e5;
  font-size: 15px;
}

/deep/.el-input__inner {
  background-color: transparent !important;
  border: 1px solid #1296db;
  color: red;
}

.el-button {
  opacity: 0.9;
  margin-top: 0.5%;
  height: 45px;
  border-radius: 0px;
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
    background: black;
    color: red;
  }
}
</style>