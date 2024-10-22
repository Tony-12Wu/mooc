import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login'
import Admin from './views/admin'
import Welcome from './views/admin/welcome'
import Category from './views/admin/category'
import Course from './views/admin/course'
import Chapter from './views/admin/chapter'
import Section from './views/admin/section'
import Teacher from './views/admin/teacher'
import File from './views/admin/file'
import Content from './views/admin/content'
import User from './views/admin/user'
import Resource from './views/admin/resource'
import Role from './views/admin/role'
import Member from './views/admin/member'
import Sms from './views/admin/sms'
import CourseResource from './views/admin/courseResource'

Vue.use(Router);

export default new Router({
    //基本路径
    base: process.env.NODE_ENV === 'prod' ? '/mooc/' : '/',
    mode: 'history',
    routes : [{
        path: '*',
        redirect: "/login",
    },{
        path: '/login',
        component: Login
    },{
        path: '',
        component: Login
    },{
        path: '/',
        name: 'admin',
        component: Admin,
        meta: {
            loginRequire: true
        },
        children : [{
            path: 'welcome',
            name: 'welcome',
            component: Welcome
        },{
            path: 'business/category',
            name: 'business/category',
            component: Category
        },{
            path: 'business/course',
            name: 'business/course',
            component: Course
        },{
            path: 'business/chapter',
            name: 'business/chapter',
            component: Chapter
        },{
            path: 'business/section',
            name: 'business/section',
            component: Section
        },{
            path: 'business/teacher',
            name: 'business/teacher',
            component: Teacher
        },{
            path: 'business/content',
            name: 'business/content',
            component: Content
        },{
            path: 'business/member',
            name: 'business/member',
            component: Member
        },{
            path: 'business/sms',
            name: 'business/sms',
            component: Sms
        },{
            path: 'business/courseResource',
            name: 'business/courseResource',
            component: CourseResource
        },{
            path: 'file/file',
            name: 'file/file',
            component: File
        },{
            path: 'system/user',
            name: 'system/user',
            component: User
        },{
            path: 'system/resource',
            name: 'system/resource',
            component: Resource
        },{
            path: 'system/role',
            name: 'system/role',
            component: Role
        }]
    }]
    }

)