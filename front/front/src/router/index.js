import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'

Vue.use(Router)

// eslint-disable-next-line
/* eslint-disable */
export default new Router({

  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    }
  ]
})
