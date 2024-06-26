import Vue from 'vue'
import VueRouter from 'vue-router'
import ContainView from '../views/ContainView.vue'
import GameView from '../views/GameView.vue'
import startView from "@/views/StartView.vue";
import LoginView from "@/views/LoginView.vue"
import RegisterView from "@/views/RegisterView.vue"
import LoadsView from "@/views/LoadsView.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'init',
    component: ContainView,
    redirect: '/contain/login'
  },

  {
    path: '/contain',
    name: 'contain',
    component: ContainView,
    redirect:'/contain/game',
    children:[
      {
        path:'/contain/start',
        name:'startView',
        component:startView
      },
      {
        path:'/contain/game',
        name:'gameView',
        component:GameView
      },
      {
        path:'/contain/login',
        name:'loginView',
        component:LoginView
      },
      {
        path:'/contain/register',
        name:'registerView',
        component:RegisterView
      },
      {
        path:'/contain/loads',
        name:'loadsView',
        component:LoadsView
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
