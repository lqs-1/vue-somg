//引入Vue核心库
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)

//准备actions对象——响应组件中用户的动作
const actions = {}
//准备mutations对象——修改state中的数据
const mutations = {
    // 在vuex中存储user
    STORAGE_USER(state, user){
        sessionStorage.setItem(`user`, user)
        state.user = user
        state.user = user
    },
    // 清除本地vuex中的user
    CLEAN_USER(state, user){
        state.user =
        sessionStorage.setItem("user", user)
    }
}
//准备state对象——保存具体的数据
const state = {
    user: sessionStorage.getItem(`user`)
}

//创建并暴露store
let store = new Vuex.Store({
    actions,
    mutations,
    state
})

export default store