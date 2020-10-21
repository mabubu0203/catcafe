import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'

Vue.config.productionTip = false

const app =new Vue({
    router,
    render: h => h(App),
    store
}).$mount('#app')