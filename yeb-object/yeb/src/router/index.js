import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Main from '../views/Main'
import UserList from "../views/userViews/UserList";
import SystemIndex from "../views/userViews/SystemIndex";
import { Message } from 'element-ui'

// 自己定义的请求包。只能在组件中使用，如果非要在这里使用，必须引入

Vue.use(VueRouter)

import store from "../store/index";

const routes = [
  {
    path: '/',
    name: 'login',
    meta:{
      isExist:true
    },
    component: Login
  },
  {
    path: '/main',
    name: 'main',
    component: Main,
    children: [
      {
        path: '/userList',
        name: 'userList',
        meta: {
          isLogin: true
        },
        component: UserList
      },
      {
        path: '/index',
        name: 'systemIndex',
        component: SystemIndex
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

//全局前置守卫：初始化时执行、每次路由切换前执行
router.beforeEach((to,from,next) =>{

  if (to.meta.isExist){
    if (store.state.user == null){
      next()
    }else{
      next({
        path: '/index'
      })
    }
  }


  if(to.meta.isLogin){ //判断当前路由是否需要进行权限控制
    // console.log(store.state.user)
    if (store.state.user == null){
      Message.error("请先登录")
      next({
        path: '/index',
      })
    }
    next()
  }else{
    next() //放行
  }
})

// //全局后置守卫：初始化时执行、每次路由切换后执行
// router.afterEach((to,from)=>{
//   console.log('afterEach',to,from)
//   if(to.meta.title){
//     document.title = to.meta.title //修改网页的title
//   }else{
//     document.title = 'vue_test'
//   }
// })

/* 解决导航重复问题 */
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}


export default router
