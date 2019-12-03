import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import productlist from '@/components/productlist'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/s/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'productlist',
      component: productlist
    }
  ]
})
