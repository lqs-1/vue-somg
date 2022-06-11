import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Main from '../views/Main'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/main',
    name: 'main',
    component: Main
  }
]

const router = new VueRouter({
  routes
})

//全局前置守卫：初始化时执行、每次路由切换前执行
// router.beforeEach((to,from,next)=>{
//   if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
//     if(localStorage.getItem('school') === 'atguigu'){ //权限控制的具体规则
//       next() //放行
//     }else{
//       alert('暂无权限查看')
//       // next({name:'guanyu'})
//     }
//   }else{
//     next() //放行
//   }
// })

// //全局后置守卫：初始化时执行、每次路由切换后执行
// router.afterEach((to,from)=>{
//   console.log('afterEach',to,from)
//   if(to.meta.title){
//     document.title = to.meta.title //修改网页的title
//   }else{
//     document.title = 'vue_test'
//   }
// })


export default router
