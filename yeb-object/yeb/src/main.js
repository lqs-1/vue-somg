import Vue from 'vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// axios挂载
import httpRequest from './http/index'
Vue.prototype.httpRequest = httpRequest


Vue.config.productionTip = false

import App from './App.vue'

Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
