import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import "./axios"
Vue.use(Element)


Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
