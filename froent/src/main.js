// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'es6-promise/auto'
import Vuex from 'vuex'
import filters from './filters'
import Settings from './settings'
import {Getitem, Removeitem} from './services/common'
import VueLodash from 'vue-lodash'

Vue.use(VueAxios, axios)
Vue.use(ElementUI)
Vue.use(Vuex)
Vue.use(filters)
Vue.use(VueLodash, {name: 'lodash'})

Vue.config.productionTip = false

// axios setting
axios.defaults.baseURL = Settings.domain.domain
axios.interceptors.request.use((config) => {
  const token = Getitem(Settings.constant.lsTokenName)
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  } else {
    Removeitem(Settings.constant.lsTokenName)
  }
  // add domain
  config.baseURL = Settings.domain.domain
  return config
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
