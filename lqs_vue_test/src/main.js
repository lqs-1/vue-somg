/*
* 该文件是整个项目的入口文件
* */
// 引入Vue
import Vue from 'vue'
// 引入App组件，它是所有组件的父组件
import App from './App.vue'
// 关闭vue的生产提示
Vue.config.productionTip = false

// 定义了不用就起不来，语法检查不合理，关闭语法检查就行b
let person = {"name": 12}

// 创建vue实例对象  ==== vm
new Vue({
  //
  render: h => h(App),
}).$mount('#app')
