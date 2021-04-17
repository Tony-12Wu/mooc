import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：", config);
  let token = Tool.getLoginMember().token;
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
  let token = Tool.getLoginMember().token;
  if (error.response) {
    if (error.response.status === 401) {
      let path = error.response.config.url;
      console.log("token为空或者token："+token+"已过期");
      alert ('token为空或者token已过期，请重新登陆')
      Tool.setLoginMember(null);
      router.push("/index")
      location.reload()
    }
  }
  return Promise.reject(error.response)   // 返回接口返回的错误信息
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
