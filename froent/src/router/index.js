import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Index from '@/components/Index'
import Login from '@/components/Login'
import { Getitem } from '../services/common'
import Settings from '../settings'

Vue.use(Router)

let routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      requiresAuth: false
    }
  }, {
    path: '/',
    name: 'Index',
    component: Index,
    meta: {
      requiresAuth: true
    }
  }
]

const router = new Router({
  routes
})

router.beforeEach((to, from, next) => {
  const token = Getitem(Settings.constant.lsTokenName)
  if (to.meta.requiresAuth) {
    if (token) {
      next()
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    }
  } else {
    // 如果已经登录再去访问不需要登录的页面就直接跳转到首页
    if (token) {
      next({
        path: '/'
      })
    }
    next()
  }
})

export default router
