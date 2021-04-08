import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from "axios"
import filter from './filter/filter'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log("请求：", config);
    let token = Tool.getLoginUser().token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config;
}, error => {});
axios.interceptors.response.use(function (response) {
    console.log("返回结果：", response);
    return response;
}, error => {
    let token = Tool.getLoginUser().token;
    if (error.response) {
        switch (error.response.status) {
            case 401:
                let path = error.response.config.url;
                //Toast.error("接口没有权限");
                console.log("接口: "+path+" 没有权限或token："+token+"已过期");
                // 跳出提示框，清除会话token信息并跳转到登录页面
                alert ('没有权限或token已过期，请重新登陆')
                Tool.setLoginUser(null);
                router.push("/login")
                location.reload()
        }
    }
    return Promise.reject(error.response)   // 返回接口返回的错误信息
});

// 全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});

// 路由登录拦截
router.beforeEach((to, from, next) => {
    // 要不要对meta.loginRequire属性做监控拦截
    if (to.matched.some(function (item) {
        return item.meta.loginRequire
    })) {
        let loginUser = Tool.getLoginUser();
        if (Tool.isEmpty(loginUser)) {
            next('/login');
        } else {
            next();
        }
    } else {
        next();
    }
});

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');