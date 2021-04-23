import Vue from 'vue'
import Router from 'vue-router'
import Index from './views/index.vue'
import List from './views/list.vue'
import Detail from './views/detail.vue'
import Search from './views/search.vue'

Vue.use(Router);

export default new Router({
    base: process.env.NODE_ENV === 'prod' ? '/web/' : '/',
    mode: 'history',
    routes : [{
        path: '*',
        redirect: "/index",
    },{
        path: '/index',
        component: Index
    },{
        path: '/list',
        component: List
    },{
        path: '/detail',
        component: Detail
    },{
        path: '/search',
        component: Search
    }]
})